package com.mate.im.notice.facade;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.mate.im.api.notice.constant.NoticeType;
import com.mate.im.api.notice.response.NoticeResponse;
import com.mate.im.api.notice.service.NoticeFacadeService;
import com.mate.im.base.exception.BizExceptionCode;
import com.mate.im.base.exception.SystemException;
import com.mate.im.email.EmailService;
import com.mate.im.email.constants.EmailType;
import com.mate.im.email.response.EmailSendResponse;
import com.mate.im.limiter.SlidingWindowRateLimiter;
import com.mate.im.notice.domain.constants.NoticeState;
import com.mate.im.notice.domain.entity.Notice;
import com.mate.im.notice.domain.service.NoticeService;
import com.mate.im.rpc.facade.Facade;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.mate.im.api.notice.constant.NoticeConstant.CAPTCHA_KEY_PREFIX;
import static com.mate.im.email.constants.EmailType.*;

/**
 * 通知服务
 *
 * @author yuanxmo
 */
@Slf4j
@DubboService(version = "1.0.0")
public class NoticeFacadeServiceImpl implements NoticeFacadeService {
    private static final int expireMinutes = 15;

    @Autowired
    private SlidingWindowRateLimiter slidingWindowRateLimiter;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private EmailService emailService;

    @Facade
    @Override
    public NoticeResponse generateAndSendEmailCaptcha(NoticeType noticeType, String email) {
        Boolean access = slidingWindowRateLimiter.tryAcquire(email, 1, 60);
        if (!access) {
            throw new SystemException(BizExceptionCode.SEND_NOTICE_DUPLICATED);
        }
        // 生成验证码
        String captcha = RandomUtil.randomNumbers(4);
        // 存入 Redis
        redisTemplate.opsForValue().set(CAPTCHA_KEY_PREFIX + email, captcha, expireMinutes * 60, TimeUnit.MINUTES);
        // 保存消息
        Notice notice = noticeService.saveCaptcha(email, captcha);

        // 通知类型转换为邮件类型
        EmailType emailType = switch (noticeType) {
            case REGISTER_CAPTCHA_EMAIL -> REGISTER_CAPTCHA;
            case LOGIN_CAPTCHA_EMAIL -> LOGIN_CAPTCHA;
            case PASSWORD_RESET_CAPTCHA_EMAIL -> PASSWORD_RESET_CAPTCHA;
            case FORGET_PASSWORD_CAPTCHA_EMAIL -> FORGET_PASSWORD_CAPTCHA;
            default -> throw new SystemException(BizExceptionCode.NOTICE_TYPE_ERROR);
        };

        // 虚拟线程发送邮件并推进邮件状态
        Thread.ofVirtual().start(() -> {
            EmailSendResponse result = emailService.sendCaptcha(
                    emailType,
                    notice.getTargetAddress(),
                    notice.getNoticeContent(),
                    expireMinutes
            );
            if (result.getSuccess()) {
                notice.setState(NoticeState.SUCCESS);
                notice.setSendSuccessTime(new Date());
                noticeService.updateById(notice);
            } else {
                notice.setState(NoticeState.FAILED);
                notice.addExtendInfo("executeResult", JSON.toJSONString(result));
            }
        });
        return new NoticeResponse.Builder().setSuccess(true).build();
    }
}
