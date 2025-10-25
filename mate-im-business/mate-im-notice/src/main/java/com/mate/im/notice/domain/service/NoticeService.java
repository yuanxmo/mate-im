package com.mate.im.notice.domain.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mate.im.base.exception.BizException;
import com.mate.im.base.exception.BizExceptionCode;
import com.mate.im.notice.domain.entity.Notice;
import com.mate.im.notice.infrastructure.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

/**
 * 通知服务
 *
 * @author yuanxmo
 */
@Service
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {
    private static final String EMAIL_NOTICE_TITLE = "mate-im 验证码";

    /**
     * 保存通知
     * @param email 邮箱
     * @param captcha 验证码
     * @return
     */
    public Notice saveCaptcha(String email, String captcha) {
        Notice notice = Notice.builder()
                .noticeTitle(EMAIL_NOTICE_TITLE)
                .noticeContent(captcha)
                .noticeType(NoticeType.EMAIL)
                .targetAddress(email)
                .build();
        boolean saveResult = this.save(notice);
        if (!saveResult) {
            throw new BizException(BizExceptionCode.NOTICE_SAVE_FAILED);
        }
        return notice;
    }
}
