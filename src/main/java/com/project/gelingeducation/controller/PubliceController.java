package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.authentication.JWTUtil;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.utils.MD5Util;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.service.IUserService;
import com.project.gelingeducation.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 不用登录就可以请求
 */
@RestController
public class PubliceController {

    @Autowired
    private IUserService UserService;
    @Autowired
    private LoginLogService loginLogService;

    /**
     * 登录接口
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user) {

        User reUser = UserService.findUserByAccount(user.getAccount());

        if (reUser == null) {
            throw new AllException(-101, "用户未注册");
        } else if (!reUser.getPassword().equals(MD5Util.encrypt(user.getAccount().toLowerCase(),
                user.getPassword()))) {
            throw new AllException(-101, "账号密码错误");
        }

        loginLogService.getByUserIdLoginUpdate(reUser.getId());

        HashMap userMap = new HashMap();
        userMap.put("id", reUser.getId());
        userMap.put("token", JWTUtil.sign(reUser.getAccount(), reUser.getPassword()));
        return JsonData.buildSuccess(userMap);
    }

    /**
     * 注册接口
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestBody User user) {
        return JsonData.buildSuccess(UserService.register(user));
    }

}
