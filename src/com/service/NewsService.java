package com.service;

import com.bean.PageBean;
import com.dao.News.NewsDao;
import com.entity.TbNewsEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class NewsService {
    private NewsDao newsDao;

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    public void add(TbNewsEntity newsEntity) {
        newsDao.addItem(newsEntity);
    }

    public PageBean<TbNewsEntity> findByPage(Integer currPage) {
        PageBean<TbNewsEntity> pageBean = new PageBean<TbNewsEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 10;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = newsDao.findCount();
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dNewsCount = totalCount;
        Double pageCount = Math.ceil(dNewsCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbNewsEntity> list = newsDao.findByPage(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    public TbNewsEntity findById(String currNewsId) {
        return newsDao.findById(currNewsId);
    }

    public List<TbNewsEntity> findTopFiveList() {
        return newsDao.findTopFiveList();
    }

    public void update(TbNewsEntity newsEntity) {
        newsDao.updateItem(newsEntity);
    }
}