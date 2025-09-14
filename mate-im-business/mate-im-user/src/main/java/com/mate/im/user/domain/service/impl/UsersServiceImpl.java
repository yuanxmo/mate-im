package com.mate.im.user.domain.service.impl;

import com.mate.im.user.domain.entity.User;
import com.mate.im.user.domain.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mate.im.user.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuanxmo
 * @since 2025-09-11
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UserMapper, User> implements IUsersService {
}
