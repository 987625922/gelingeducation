package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.common.utils.UrlDeconderUtil;
import com.project.gelingeducation.dao.ICourseDao;
import com.project.gelingeducation.entity.Course;
import com.project.gelingeducation.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Author: LL
 * @Description: 课程实体类的service
 */
@Service
@Transactional(readOnly = true)
public class CourseServiceImpl implements ICourseService {

    /**
     * 课程实体类的dao
     */
    @Autowired
    private ICourseDao courseDao;

    /**
     * 根据id获取课程
     *
     * @param id 课程id
     * @return 课程实体类
     */
    @Override
    public Course findById(Long id) {
        return courseDao.findById(id);
    }

    /**
     * 添加课程
     *
     * @param course 课程实体类
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(Course course) {
        Date date = new Date();
        course.setLastUpdateTime(date);
        course.setCreateTime(date);
        return courseDao.insert(course);
    }

    /**
     * 根据id删除课程
     *
     * @param id 视频id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delect(Long id) {
        courseDao.delect(id);
    }

    /**
     * 更新课程
     *
     * @param course 课程实体
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Course course) {
        course.setLastUpdateTime(new Date());
        courseDao.update(course);
    }

    /**
     * 获取课程的分页数据
     *
     * @param currentPage 页下标
     * @param pageSize    页数
     * @return 分页实体类
     */
    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return courseDao.queryAll(currentPage, pageSize);
        } else {
            return courseDao.queryAll();
        }
    }

    /**
     * 批量删除
     *
     * @param ids 格式为1,2,3的课程id字符串
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchesDeletes(String ids) {
        courseDao.delMore(ids);
    }

    /**
     * 根据条件搜索课程分页信息
     *
     * @param name        课程名
     * @param status      课程状态
     * @param startPrice  课程搜索开始的价格
     * @param endPrice    课程搜索结束的价格
     * @param teacherId   教师id
     * @param currentPage 页面下标
     * @param pageSize    页数
     * @return 分页实体类
     */
    @Override
    public Object selByNameOrStatusOrPriceOrTeacher(String name, Integer status, Double startPrice, Double endPrice,
                                                    Long teacherId, Integer currentPage, Integer pageSize) throws UnsupportedEncodingException {
        return courseDao.selByNameOrStatusOrPriceOrTeacher(UrlDeconderUtil.decode(name, "UTF-8"), status, startPrice, endPrice, teacherId, currentPage, pageSize);
    }

    /**
     * 通过专题id获取课程列表
     *
     * @param subjectId 专题id
     * @return
     */
    @Override
    public Object getCourseListBySubjectId(Long subjectId) {
        return courseDao.getCourseListBySubjectId(subjectId);
    }

    /**
     * 通过视频id获取课程列表
     *
     * @param videoId 专题id
     * @return
     */
    @Override
    public Object getCourseListByVideoId(Long videoId) {
        return courseDao.getCourseListByVideoId(videoId);
    }

    /**
     * 获取课程的数量
     *
     * @return 课程的数量
     */
    @Override
    public Long getTotalNumber() {
        return courseDao.getCourserNumber();
    }
}
