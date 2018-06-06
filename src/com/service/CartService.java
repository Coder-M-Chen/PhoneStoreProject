package com.service;

import com.bean.PageBean;
import com.bean.StatisticsBean;
import com.dao.Cart.CartDao;
import com.entity.TbCartEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
public class CartService {
    private CartDao cartDao;

    public void setCartDao(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public void add(TbCartEntity cartEntity){
        cartDao.addItem(cartEntity);
    }

    public void delete(TbCartEntity cartEntity){
        cartDao.deleteItem(cartEntity);
    }

    public void update(TbCartEntity cartEntity){
        cartDao.updateItem(cartEntity);
    }

    public List<TbCartEntity> findByUserId(String userId){
        return cartDao.findByUserId(userId);
    }

    public TbCartEntity findByUserIdAndGoodId(String userId, String goodId){
        return cartDao.findByUserIdAndGoodId(userId, goodId);
    }

    public PageBean<TbCartEntity> findByPage(Integer currPage, String userId) {
        PageBean<TbCartEntity> pageBean = new PageBean<TbCartEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 10;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = cartDao.findCountByUserId(userId);
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dCartCount = totalCount;
        Double pageCount = Math.ceil(dCartCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbCartEntity> list = cartDao.findByPage(begin, pageSize, userId);
        pageBean.setList(list);
        System.out.println(totalCount);
        System.out.println(pageBean.toString());
        return pageBean;
    }

    public TbCartEntity findById(String cartId) {
        return cartDao.findByCartId(cartId);
    }

    public List<StatisticsBean> findTop() {
        List<StatisticsBean> topList = cartDao.findTop();
        return topList;
    }

    public List<StatisticsBean> findBottom() {
        return cartDao.findBottom();
    }

    public List<StatisticsBean> findByDate(Date startDate, Date endDate, Integer orderKey) {

        return cartDao.findByDate(startDate, endDate, orderKey);
    }
}
