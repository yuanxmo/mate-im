package com.mate.im.user.domain.entity;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;

import com.mate.im.api.user.constants.UserRole;
import com.mate.im.api.user.constants.UserStateEnum;
import com.mate.im.datasource.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
     * 邀请码
     */
    private String inviteCode;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String telephone;

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

    /**
     * 注册（内测用户）
     * @param email 邮箱
     * @param nickName 昵称
     * @param password 密码
     * @param inviteCode 邀请码
     * @param inviterId 邀请人ID
     * @return 用户对象
     */
    public User registerInnerTest(String email, String nickName, String password, String inviteCode, String inviterId) {
        this.register(email, nickName, password, inviteCode, inviterId);
        this.setUserRole(UserRole.INTERNAL_TEST_USER);
        return this;
    }


}
