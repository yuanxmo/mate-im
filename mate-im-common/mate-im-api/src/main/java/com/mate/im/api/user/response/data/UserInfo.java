package com.mate.im.api.user.response.data;

import com.github.houbb.sensitive.annotation.strategy.SensitiveStrategyEmail;
import com.mate.im.api.user.constants.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.mate.im.api.user.constants.UserStateEnum;

import java.util.Date;

/**
 * 用户信息
 *
 * @author yuanxmo
 */
@Getter
@Setter
@NoArgsConstructor
public class UserInfo extends BaseUserInfo {
    private final static long serialVersionUID = 0L;

    /**
     * 邮箱
     */
    @SensitiveStrategyEmail
    private String email;

    /**
     * 用户状态
     *
     * @see UserStateEnum
     */
    private String state;

    /**
     * 用户角色
     */
    private UserRole userRole;

    /**
     * 是否能加群
     */
    private Boolean canJoinGroup;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 创建时间
     */
    private Date createTime;

    public boolean canJoinGroup() {
        if (this.getUserRole() != null && !this.getUserRole().equals(UserRole.NORMAL_USER)) {
            return false;
        }
        if (this.getState() != null && !this.getState().equals(UserStateEnum.ACTIVE.name())) {
            return false;
        }
        return this.getState() == null || this.getCanJoinGroup();
    }

}
