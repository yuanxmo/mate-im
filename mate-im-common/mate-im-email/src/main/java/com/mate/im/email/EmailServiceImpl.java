package com.mate.im.email;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.mate.im.base.response.ResponseCode;
import com.mate.im.email.constants.EmailType;
import com.mate.im.email.util.EmailCaptchaUtil;
import com.mate.im.email.param.EmailParam;
import com.mate.im.email.response.EmailSendResponse;
import com.mate.im.lock.DistributeLock;
import jakarta.mail.internet.InternetAddress;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.mate.im.email.constants.EmailConstant.*;

/**
 * 邮箱服务
 *
 * @author yuanxmo
 */
@Slf4j
@Setter
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    private EmailSendResponse send(EmailParam emailParam) {
        EmailSendResponse emailSendResponse = new EmailSendResponse();
        try {
            //true表示支持复杂类型
            MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            //邮件发信人
            messageHelper.setFrom(new InternetAddress(EMAIL_FORM_NAME.getName() + "<" + EMAIL_FORM_ADDRESS.getName() + ">"));
            //邮件收信人
            messageHelper.setTo(emailParam.getToAddress().split(","));
            //邮件主题
            messageHelper.setSubject(emailParam.getSubject());
            //邮件内容
            if (emailParam.getIsHtml()) {
                Context context = new Context();
                if (emailParam.getTemplateParams() != null) {
                    for (Map.Entry<String, String> entry : emailParam.getTemplateParams().entrySet()) {
                        context.setVariable(entry.getKey(), entry.getValue());
                    }
                }
                messageHelper.setText(templateEngine.process(emailParam.getHtmlTemplate(), context), true);
            } else {
                messageHelper.setText(emailParam.getContent(), false);
            }
            //抄送
            if (!StringUtils.isEmpty(emailParam.getCc())) {
                messageHelper.setCc(emailParam.getCc().split(","));
            }
            //密送
            if (!StringUtils.isEmpty(emailParam.getBcc())) {
                messageHelper.setCc(emailParam.getBcc().split(","));
            }
            //添加邮件附件
            if (CollectionUtil.isNotEmpty(emailParam.getFiles())) {
                for (File file : emailParam.getFiles()) {
                    messageHelper.addAttachment(file.getName(), file);
                }
            }
            // 邮件发送时间
            messageHelper.setSentDate(new Date());
            //正式发送邮件
            javaMailSender.send(messageHelper.getMimeMessage());
            emailSendResponse.setSuccess(true);
        } catch (Exception e) {
            emailSendResponse.setSuccess(false);
            emailSendResponse.setResponseCode(ResponseCode.SYSTEM_ERROR.name());
            emailSendResponse.setResponseMessage("Email send error, e: " + StringUtils.substring(e.getMessage(), 0, 900));
        }
        return emailSendResponse;
    }

    @DistributeLock(scene = "SEND_EMAIL", keyExpression = "#email")
    public EmailSendResponse sendCaptcha(EmailType emailType, String email, String captcha) {
        EmailSendResponse emailSendResponse = new EmailSendResponse();
        // 空子段判断
        if (emailType == null || StringUtils.isAnyBlank(email, captcha)) {
            emailSendResponse.setSuccess(false);
            emailSendResponse.setResponseCode(ResponseCode.SYSTEM_ERROR.name());
            emailSendResponse.setResponseMessage("Email send error, params can not null");
            return emailSendResponse;
        }
        EmailParam emailParam = new EmailParam();
        emailParam.createCaptcha(emailType, email, captcha, expireMinutes);
        if (
                !ObjectUtil.isAllNotEmpty(emailParam.getToAddress(), emailParam.getSubject(), emailParam.getHtmlTemplate())
        ) {
            emailSendResponse.setSuccess(false);
            emailSendResponse.setResponseCode(ResponseCode.SYSTEM_ERROR.name());
            emailSendResponse.setResponseMessage("Email send error, emailParam has errors");
            return emailSendResponse;
        }
        return this.send(emailParam);
    }
}
