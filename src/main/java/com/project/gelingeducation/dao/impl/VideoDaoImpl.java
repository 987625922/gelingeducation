package com.project.gelingeducation.dao.impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.common.utils.BeanUtil;
import com.project.gelingeducation.dao.IVideoDao;
import com.project.gelingeducation.entity.Video;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @Author: LL
 * @Description: 视频的dao
 */
@Repository
public class VideoDaoImpl extends BaseDao implements IVideoDao {

    /**
     * 根据currentPage和pageSize返回page实体类
     *
     * @param currentPage
     * @param pageSize
     * @return 返回page实体类
     */
    @Override
    public PageResult queryAll(Integer currentPage, Integer pageSize) {
        Session session = getSession();
        //此处的Product是对象
        String hql = "select count(*) from Video";
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<Video> query = session.createQuery("from Video");
        //得到当前页
        query.setFirstResult((currentPage - 1) * pageSize);
        //得到每页的记录数
        query.setMaxResults(pageSize);

        long totalPage = (allrows - 1) / pageSize + 1;
        List<Video> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }

    /**
     * 返回所有的视频实体lists
     *
     * @return lists
     */
    @Override
    public List<Video> queryAll() {
        return getSession().createQuery("FROM Video").list();
    }

    /**
     * 根据id查找视频实体
     *
     * @param id 视频id
     * @return 视频实体
     */
    @Override
    public Video findById(Long id) {
        return getSession().get(Video.class, id);
    }

    /**
     * 添加视频
     *
     * @param video 视频实体
     * @return 视频实体
     */
    @Override
    public Video insert(Video video) {
        getSession().save(video);
        return video;
    }

    /**
     * 删除视频
     *
     * @param id 视频id
     */
    @Override
    public void delect(Long id) {
        getSession().delete(getSession().get(Video.class, id));
    }

    /**
     * 更新视频
     *
     * @param video 视频实体
     */
    @Override
    public void update(Video video) {
        Session session = getSession();
        Video findvideo = session.get(Video.class, video.getId());
        BeanUtil.copyPropertiesIgnoreNull(video, findvideo);
        session.update(findvideo);
    }

    /**
     * 批量删除视频
     *
     * @param ids 视频id 格式为 1,2,3
     */
    @Override
    public void delMore(String ids) {
        getSession()
                .createQuery("DELETE FROM Video WHERE id IN (" + ids + ")")
                .executeUpdate();
    }

    /**
     * 按条件搜索视频列表
     *
     * @param teacherId 教师id
     * @param name      视频名
     * @param courseIds 1,2,3格式的课程id字符串
     * @return 分页的视频list列表
     */
    @Override
    public Object searchByCriteria(String teacherId, String name,
                                   String courseIds) {
        Session session = getSession();
        String hql = "FROM Video WHERE 1=1";
        session.createQuery(hql);
        if (teacherId != null && !teacherId.equals("")) {
            hql += "and teacherId = " + teacherId;
        }
        if (name != null && !name.equals("")) {
            hql += "and name = " + name;
        }
        if (courseIds != null && !courseIds.equals("")) {
            hql += "and name = " + name;
        }
        return null;
    }
}
