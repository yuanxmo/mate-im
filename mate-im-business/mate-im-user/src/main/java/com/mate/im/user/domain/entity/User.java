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

    private String userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户状态
     */
    private UserStateEnum state;

    /**
     * 密码哈希
     */
    private String passwordHash;

    /**
     * 邮箱
     */
    @SensitiveStrategyEmail
    private String email;

    /**
     * 手机号
     */
    @SensitiveStrategyPhone
    private String telephone;

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

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 邀请人用户ID
     */
    private String inviterId;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 用户头像url
     */
    private String avatar;

    /**
     * 用户角色
     */
    private UserRole userRole;

    /**
     * 注册（普通用户）
     * @param email 邮箱
     * @param nickName 昵称
     * @param password 密码
     * @param inviteCode 邀请码
     * @param inviterId 邀请人ID
     * @return 用户对象
     */
    public User register(String email, String nickName, String password, String inviteCode, String inviterId) {
        this.setEmail(email);
        this.setNickName(nickName);
        this.setPasswordHash(DigestUtil.md5Hex(password));
        this.setState(UserStateEnum.INIT);
        this.setUserRole(UserRole.NORMAL_USER);
        this.setInviteCode(inviteCode);
        this.setInviterId(inviterId);
        return this;
    }
}
