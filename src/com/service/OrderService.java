package com.service;

import com.bean.PageBean;
import com.dao.Order.OrderDao;
import com.entity.TbOrderEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void add(TbOrderEntity orderEntity) {
        orderDao.addItem(orderEntity);
    }

    public void update(TbOrderEntity orderEntity) {
        orderDao.updateItem(orderEntity);
    }

    public PageBean<TbOrderEntity> findByPage(Integer currPage) {
        PageBean<TbOrderEntity> pageBean = new PageBean<TbOrderEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 10;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = orderDao.findCount();
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dOrderCount = totalCount;
        Double pageCount = Math.ceil(dOrderCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbOrderEntity> list = orderDao.findByPage(begin, pageSize);
        pageBean.setList(list);
        System.out.println(totalCount);
        System.out.println(pageBean.toString());
        return pageBean;
    }

    public PageBean<TbOrderEntity> findByUserId(String userId, Integer currPage) {
        PageBean<TbOrderEntity> pageBean = new PageBean<TbOrderEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 10;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = orderDao.findCountByUser(userId);
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dOrderCount = totalCount;
        Double pageCount = Math.ceil(dOrderCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbOrderEntity> list = orderDao.findByUserIdAndPage(userId, begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    public TbOrderEntity findById(String currOrderId) {
        return orderDao.findByID(currOrderId);
    }

    public PageBean<TbOrderEntity> findByUserAndNoPay(String userId, int currPage) {
        PageBean<TbOrderEntity> pageBean = new PageBean<TbOrderEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 10;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = orderDao.findCountByUserAndNoPay(userId);
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dOrderCount = totalCount;
        Double pageCount = Math.ceil(dOrderCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbOrderEntity> list = orderDao.findByUserAndPageAndNoPay(userId, begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    public PageBean<TbOrderEntity> findByUserAndNoSend(String userId, int currPage) {
        PageBean<TbOrderEntity> pageBean = new PageBean<TbOrderEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 10;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = orderDao.findCountByUserAndNoSend(userId);
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dOrderCount = totalCount;
        Double pageCount = Math.ceil(dOrderCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbOrderEntity> list = orderDao.findByUserAndPageAndNoSend(userId, begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    public PageBean<TbOrderEntity> findNoSend(int currPage) {
        PageBean<TbOrderEntity> pageBean = new PageBean<TbOrderEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 10;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = orderDao.findCountNoSend();
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dOrderCount = totalCount;
        Double pageCount = Math.ceil(dOrderCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbOrderEntity> list = orderDao.findNoSend(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    public PageBean<TbOrderEntity> findNoPay(int currPage) {
        PageBean<TbOrderEntity> pageBean = new PageBean<TbOrderEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 10;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = orderDao.findCountNoPay();
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dOrderCount = totalCount;
        Double pageCount = Math.ceil(dOrderCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbOrderEntity> list = orderDao.findNoPay(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }
}