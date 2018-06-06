package com.service;

import com.bean.PageBean;
import com.dao.Feedback.FeedbackDao;
import com.entity.TbFeedbackEntity;
import com.entity.TbUserEntity;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
public class FeedbackService {
    private FeedbackDao feedbackDao;

    public void setFeedbackDao(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    public void add(TbFeedbackEntity feedbackEntity) {
        feedbackDao.addItem(feedbackEntity);
    }

    public void delete(TbFeedbackEntity feedbackEntity) {
        TbFeedbackEntity existFeedbackEntity = feedbackDao.findById(feedbackEntity.getFeedbackId());
        feedbackDao.deleteItem(existFeedbackEntity);
    }

    public PageBean<TbFeedbackEntity> findByPage(Integer currPage) {
        PageBean<TbFeedbackEntity> pageBean = new PageBean<TbFeedbackEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 10;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = feedbackDao.findCount();
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dFeedbackCount = totalCount;
        Double pageCount = Math.ceil(dFeedbackCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbFeedbackEntity> list = feedbackDao.findByPage(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    public void update(TbFeedbackEntity feedbackEntity) {
        feedbackDao.updateItem(feedbackEntity);
    }

    public TbFeedbackEntity findByFeedbackId(String feedbackId) {
        return feedbackDao.findById(feedbackId);
    }

    public PageBean<TbFeedbackEntity> findByUserAndPage(String userId, int currPage) {
        PageBean<TbFeedbackEntity> pageBean = new PageBean<TbFeedbackEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 10;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = feedbackDao.findCountOfUser(userId);
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dFeedbackCount = totalCount;
        Double pageCount = Math.ceil(dFeedbackCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbFeedbackEntity> list = feedbackDao.findByUserAndPage(userId, begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }
}
