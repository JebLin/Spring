package indi.sword.spring.annotation.generic;

import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author rd_jianbin_lin
 * @Date 13:29 2017/10/8
 */
@Service(value = "userService1")
public class UserService extends BaseService<User> {
}
