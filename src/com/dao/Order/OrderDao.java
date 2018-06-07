package com.dao.Order;

import com.entity.TbOrderEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class OrderDao extends HibernateDaoSupport implements IOrderDao {
    @Override
    public boolean addItem(TbOrderEntity orderEntity) {
        this.getHibernateTemplate().save(orderEntity);
        return true;
    }

    @Override
    public boolean deleteItem(TbOrderEntity orderEntity) {
        this.getHibernateTemplate().delete(orderEntity);
        return false;
    }

    @Override
    public boolean updateItem(TbOrderEntity orderEntity) {
        this.getHibernateTemplate().update(orderEntity);
        return true;
    }

    @Override
    public TbOrderEntity findByID(String orderId) {
        String hql = "from TbOrderEntity where orderId = ?";
        List<TbOrderEntity> list = (List<TbOrderEntity>) this.getHibernateTemplate().find(hql, orderId);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<TbOrderEntity> findByOther(TbOrderEntity orderEntity) {
        String hql = "from TbOrderEntity where orderState = ? order by orderId desc";
        return (List<TbOrderEntity>) this.getHibernateTemplate().find(hql, orderEntity.getOrderState());
    }

    @Override
    public int findCount() {
        String hql = "select count(*) from TbOrderEntity";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbOrderEntity> findByPage(int begin, int pageSize) {
        String hql = "from TbOrderEntity order by orderId desc";
        List<TbOrderEntity> existList = (List<TbOrderEntity>) this.getHibernateTemplate().find(hql);
        List<TbOrderEntity> list = new ArrayList<TbOrderEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++){
            list.add(existList.get(begin + i));
        }
        return list;
    }

    @Override
    public List<TbOrderEntity> findByUserId(String userId) {
        String hql = "from TbOrderEntity where userId = ? order by orderId desc";
        return (List<TbOrderEntity>) this.getHibernateTemplate().find(hql,userId);
    }

    @Override
    public List<TbOrderEntity> findByUserIdAndPage(String userId, int begin, int pageSize) {

        String hql = "from TbOrderEntity where userId = ? order by orderId desc";

        List<TbOrderEntity> existList = (List<TbOrderEntity>) this.getHibernateTemplate().find(hql, userId);
        List<TbOrderEntity> list = new ArrayList<TbOrderEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++) {
            list.add(existList.get(begin + i));
        }
        return list;
    }

    @Override
    public int findCountByUserAndNoPay(String userId) {
        String hql = "select count(*) from TbOrderEntity where userId = ? and orderState = '待支付'";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, userId);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbOrderEntity> findByUserAndPageAndNoPay(String userId, int begin, int pageSize) {

        String hql = "from TbOrderEntity where userId = ? and orderState = '待支付' order by orderId desc";

        List<TbOrderEntity> existList = (List<TbOrderEntity>) this.getHibernateTemplate().find(hql, userId);
        List<TbOrderEntity> list = new ArrayList<TbOrderEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++) {
            list.add(existList.get(begin + i));
        }
        return list;
    }

    @Override
    public int findCountByUserAndNoSend(String userId) {
        String hql = "select count(*) from TbOrderEntity where userId = ? and orderState = '待发货'";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, userId);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbOrderEntity> findByUserAndPageAndNoSend(String userId, int begin, int pageSize) {
        String hql = "from TbOrderEntity where userId = ? and orderState = '待发货' order by orderId desc";

        List<TbOrderEntity> existList = (List<TbOrderEntity>) this.getHibernateTemplate().find(hql, userId);
        List<TbOrderEntity> list = new ArrayList<TbOrderEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++) {
            list.add(existList.get(begin + i));
        }
        return list;
    }

    @Override
    public int findCountByUser(String userId) {
        String hql = "select count(*) from TbOrderEntity where userId = ?";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, userId);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public int findCountNoSend() {
        String hql = "select count(*) from TbOrderEntity where orderState = '待发货'";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbOrderEntity> findNoSend(int begin, int pageSize) {
        String hql = "from TbOrderEntity where orderState = '待发货' order by orderId desc";

        List<TbOrderEntity> existList = (List<TbOrderEntity>) this.getHibernateTemplate().find(hql);
        List<TbOrderEntity> list = new ArrayList<TbOrderEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++) {
            list.add(existList.get(begin + i));
        }
        return list;
    }

    @Override
    public int findCountNoPay() {
        String hql = "select count(*) from TbOrderEntity where orderState = '待支付'";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbOrderEntity> findNoPay(int begin, int pageSize) {
        String hql = "from TbOrderEntity where orderState = '待支付' order by orderId desc";

        List<TbOrderEntity> existList = (List<TbOrderEntity>) this.getHibernateTemplate().find(hql);
        List<TbOrderEntity> list = new ArrayList<TbOrderEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++) {
            list.add(existList.get(begin + i));
        }
        return list;
    }
}