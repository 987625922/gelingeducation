package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.AdminInfo;

import java.util.List;

public interface AdminInfoService {

    Object register(AdminInfo adminInfo);

    Object login(AdminInfo adminInfo);

    AdminInfo findById(long id);

    List<AdminInfo> findAll();

    void updateCoverImg(long id,String coverImg);

    void update(AdminInfo adminInfo);

    void updateInfo(long id, String userName, String eMail, int sex, String note);

    void updatePassword(long id,String oldPassword,String newPassword);
}
