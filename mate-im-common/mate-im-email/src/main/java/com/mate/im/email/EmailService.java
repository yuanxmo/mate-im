package com.mate.im.email;

import com.mate.im.email.constants.EmailType;
import com.mate.im.email.response.EmailSendResponse;

/**
 * 邮件服务
 *
 * @author yuanxmo
 */
public interface EmailService {

    /**
     * 发送验证码
     *
     * @param emailType 邮件类型
     * @param email     邮箱
     * @param captcha   验证码
     * @param expireMinutes 过期时间
     * @return 发送结果
     */
    EmailSendResponse sendCaptcha(EmailType emailType, String email, String captcha, int expireMinutes);
}
