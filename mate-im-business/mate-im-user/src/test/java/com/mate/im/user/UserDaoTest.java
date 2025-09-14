package com.mate.im.user;

import com.mate.im.user.domain.entity.User;
import com.mate.im.user.infrastructure.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class UserDaoTest extends UserBaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testAddUser() {
        User user = new User();
        user = user.register(
                "123@123.com", "test", "123456",
                null, null
        );
        userMapper.insert(user);
//        log.info("{}", user);
    }

}
