package com.mate.im.user.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

import com.mate.im.datasource.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author yuanxmo
 * @since 2025-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("users")
public class Users extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 密码哈希
     */
    @TableField("password_hash")
    private String passwordHash;

    /**
     * 用户状态
     */
    @TableField("state")
    private String state;

    /**
     * 邀请码
     */
    @TableField("invite_code")
    private String inviteCode;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号
     */
    @TableField("telephone")
    private String telephone;

    /**
     * 邀请人用户ID
     */
    @TableField("inviter_id")
    private Long inviterId;

    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private OffsetDateTime lastLoginTime;

    /**
     * 用户头像url
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 用户角色
     */
    @TableField("user_role")
    private String userRole;
}
