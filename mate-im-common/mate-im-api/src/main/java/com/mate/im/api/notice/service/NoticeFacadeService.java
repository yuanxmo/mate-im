package com.mate.im.api.notice.service;

import com.mate.im.api.notice.constant.NoticeType;
import com.mate.im.api.notice.response.NoticeResponse;

/**
 * @author yuanxmo
 */
public interface NoticeFacadeService {

    /**
     * 生成验证码并发送邮件
     *
     * @param noticeType 通知类型
     * @param email 邮箱
     * @return
     */
    NoticeResponse generateAndSendEmailCaptcha(NoticeType noticeType, String email);
}
