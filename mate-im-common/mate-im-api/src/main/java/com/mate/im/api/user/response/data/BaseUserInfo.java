package com.mate.im.api.user.response.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BaseUserInfo implements Serializable {
    private final static long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像url
     */
    private String avatar;
    /**
     * 性别
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birthDate;
    /**
     * 个性签名
     */
    private String userSignature;
}
