package com.mate.im.user.param;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 用户信息修改
 *
 * @author yuanxmo
 */
@Getter
@Setter
public class UserInfoModifyParam {
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 出生日期
     */
    private LocalDate birthDate;

    /**
     * 个性签名
     */
    private String userSignature;
}
