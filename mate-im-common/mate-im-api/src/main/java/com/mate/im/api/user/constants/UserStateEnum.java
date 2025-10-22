package com.mate.im.api.user.constants;

/**
 * 用户状态
 *
 * @author yuanxmo
 */
public enum UserStateEnum {
    /**
     * 创建成功
     */
    INIT,

    /**
     * 正常使用
     */
    ACTIVE,

    /**
     * 聊天功能冻结
     */
    IM_FROZEN,

    /**
     * 用户功能冻结
     */
    USER_FROZEN,

    /**
     * 冻结
     */
    FROZEN;
}
