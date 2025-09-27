package com.mate.im.user.domain.entity.convertor;

import com.mate.im.api.user.response.data.BaseUserInfo;
import com.mate.im.api.user.response.data.UserInfo;
import com.mate.im.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserConvertor {
    UserConvertor INSTANCE = Mappers.getMapper(UserConvertor.class);

    /**
     * 转换为 UserInfo
     *
     * @param user
     * @return
     */
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "createTime", source = "user.gmtCreate")
    UserInfo mapToUserInfo(User user);

    /**
     * 转换为 BaseUserInfo
     *
     * @param user
     * @return
     */
    @Mapping(target = "userId", source = "user.id")
    BaseUserInfo  mapToBaseUserInfo(User user);

    /**
     * 转换为实体类 User
     *
     * @param userInfo
     * @return
     */
    @Mapping(target = "id", source = "userInfo.userId")
    User maoToUser(UserInfo userInfo);

    /**
     * 转换为实体类列表 List<\User>
     *
     * @param userInfos
     * @return
     */
    @Mapping(target = "id", source = "userInfos.userId")
    List<User> mapToUserList(List<UserInfo> userInfos);
}
