package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Teacher;

public interface ITeacherService {

    Teacher addTeacher(Teacher teacher);

    Teacher getById(Long id);

    Object queryAll(Integer currentPage, Integer pageSize);

    void delTeacher(Long id);

}
