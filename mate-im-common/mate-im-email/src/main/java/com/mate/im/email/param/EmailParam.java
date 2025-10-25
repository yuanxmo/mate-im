package com.mate.im.email.param;

import com.mate.im.email.constants.EmailType;
import com.mate.im.email.util.EmailCaptchaUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mate.im.email.constants.EmailConstant.CAPTCHA_TEMPLATE;

/**
 * 邮件参数
 *
 * @author yuanxmo
 */
@Getter
@Setter
@ToString
public class EmailParam {
    /**
     * 接收人地址
     */
    private String toAddress;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 是否为 HTML 邮件
     */
    private Boolean isHtml;

    /**
     * HTML 邮件模板
     */
    private String htmlTemplate;

    /**
     * HTML 邮件模板参数
     */
    private Map<String, String> templateParams;

    /**
     * 抄送
     */
    private String cc;

    /**
     * 密送
     */
    private String bcc;

    /**
     * 邮件附件
     */
    private List<File> files;

    /**
     * 创建验证码邮件
     * @param emailType 类型
     * @param email 收件人
     * @param captcha 验证码
     * @param expireMinutes 有效时间
     */
    public void createCaptcha(EmailType emailType, String email, String captcha, Integer expireMinutes) {
        // 设置收件人
        this.setToAddress(email);
        // 设置发送 HTML 邮件
        this.setIsHtml(true);
        // 设置 HTML 邮件模板路径
        this.setHtmlTemplate(CAPTCHA_TEMPLATE.getName());
        // 设置 HTML 邮件模板参数
        this.setTemplateParams(new HashMap<>() {{
            put(EmailCaptchaUtil.CAPTCHA, captcha);
            put(EmailCaptchaUtil.TYPE, EmailCaptchaUtil.getType(emailType));
            put(EmailCaptchaUtil.EXPIRY_TIME, expireMinutes.toString());
        }});
        // 设置邮件主题
        this.setSubject(EmailCaptchaUtil.getSubject(emailType));
    }
}
