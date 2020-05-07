package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Course;

import java.util.List;

public interface ICourseService {

    PageResult findAll();

    Course findById(long id);

    long insert(Course course);

    void delect(long id);

    void update(Course course);

    PageResult getLists(int currentPage, int pageSize);

    void batchesDeletes(long[] ids);

    PageResult selbyname(String name,int currentPage, int pageSize);

}