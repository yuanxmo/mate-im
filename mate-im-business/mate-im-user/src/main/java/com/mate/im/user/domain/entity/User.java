package com.mate.im.user.domain.entity;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.github.houbb.sensitive.annotation.strategy.SensitiveStrategyEmail;
import com.github.houbb.sensitive.annotation.strategy.SensitiveStrategyPhone;
import com.mate.im.api.user.constants.UserRole;
import com.mate.im.api.user.constants.UserStateEnum;
import com.mate.im.datasource.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author yuanxmo
 * @since 2025-09-10
 */
@Getter
@Setter
@TableName("users")
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 密码哈希
     */
    private String passwordHash;

    /**
     * 用户头像url
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String telePhone;

    /**
     * 男
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 用户签名
     */
    private String userSignature;

    /**
     * 邀请人ID
     */
    private Long inviterId;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 用户角色
     */
    private String userRole;

    /**
     * 用户状态
     */
    private Boolean state;

}
