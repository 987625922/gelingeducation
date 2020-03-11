package com.project.gelingeducation.service;

import com.project.gelingeducation.config.HibernateConfig;
import com.project.gelingeducation.domain.AdminInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Slf4j
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminServiceTest {

    @Autowired
    private AdminInfoService adminInfoService;

    @Test
    public void findById() {
//        for (int i = 0; i < 10; i++) {
        log.debug("findById获取的结果：" + adminInfoService.findById(1));
//        }
    }

    @Test
    public void insert() {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAccount("123456");
        adminInfo.setPassword("123456");
        log.debug("findById获取的结果：" + adminInfoService.addUser(adminInfo));
    }
}
