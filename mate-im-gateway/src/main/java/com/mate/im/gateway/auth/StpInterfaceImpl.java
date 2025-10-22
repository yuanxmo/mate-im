package com.mate.im.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.mate.im.api.user.constants.UserPermission;
import com.mate.im.api.user.constants.UserRole;
import com.mate.im.api.user.constants.UserStateEnum;
import com.mate.im.api.user.response.data.UserInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 自定义权限认证接口
 *
 * @author yuanxmo
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        UserInfo userInfo = (UserInfo) StpUtil.getSessionByLoginId(loginId).get((String) loginId);
        if (userInfo.getUserRole() == UserRole.ADMIN || userInfo.getState().equals(UserStateEnum.ACTIVE.name())) {
            return List.of(UserPermission.BASIC.name(), UserPermission.IM.name(), UserPermission.USER.name());
        }
        if (userInfo.getState().equals(UserStateEnum.IM_FROZEN.name())) {
            return List.of(UserPermission.BASIC.name(), UserPermission.USER.name());
        }
        if (userInfo.getState().equals(UserStateEnum.USER_FROZEN.name())) {
            return List.of(UserPermission.BASIC.name());
        }
        return List.of(UserPermission.NONE.name());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        UserInfo userInfo = (UserInfo) StpUtil.getSessionByLoginId(loginId).get((String) loginId);
        if (userInfo.getUserRole() == UserRole.ADMIN) {
            return List.of(UserRole.ADMIN.name());
        }
        return List.of(UserRole.NORMAL_USER.name());
    }
}
