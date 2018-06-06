package com.dao.Cart;

import com.bean.StatisticsBean;
import com.entity.TbCartEntity;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartDao extends HibernateDaoSupport implements ICartDao {
    @Override
    public void addItem(TbCartEntity cartEntity) {
        this.getHibernateTemplate().save(cartEntity);
    }

    @Override
    public void deleteItem(TbCartEntity cartEntity) {
        this.getHibernateTemplate().delete(cartEntity);
    }

    @Override
    public void updateItem(TbCartEntity cartEntity) {
        this.getHibernateTemplate().update(cartEntity);
    }

    @Override
    public List<TbCartEntity> findByUserId(String userId) {
        String hql = "from TbCartEntity where userId = ? and goodCheck != 'X'";
        return (List<TbCartEntity>) this.getHibernateTemplate().find(hql, userId);
    }

    @Override
    public TbCartEntity findByUserIdAndGoodId(String userId, String goodId) {
        String hql = "from TbCartEntity where userId = ? and goodId = ? and goodCheck != 'X'";
        List<TbCartEntity> list = (List<TbCartEntity>) this.getHibernateTemplate().find(hql, userId, goodId);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int findCount() {
        String hql = "select count(*) from TbCartEntity";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public int findCountByUserId(String userId) {
        String hql = "select count(*) from TbCartEntity where userId = ? and goodCheck != 'X'";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, userId);
        if (list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbCartEntity> findByPage(int begin, int pageSize, String userId) {
        String hql = "from TbCartEntity where userId = ? and goodCheck != 'X'";
        List<TbCartEntity> existList = (List<TbCartEntity>) this.getHibernateTemplate().find(hql, userId);
        List<TbCartEntity> list = new ArrayList<TbCartEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++){
            list.add(existList.get(begin + i));
        }
        return list;
    }

    @Override
    public TbCartEntity findByCartId(String cartId) {
        String hql = "from TbCartEntity where cartId = ?";
        List<TbCartEntity> list = (List<TbCartEntity>) this.getHibernateTemplate().find(hql, cartId);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<StatisticsBean> findTop() {
        String hql = "select sum(cart.goodCount) as saleCount,cart.goodName as goodsName,cart.goodPrice as goodsPrice from TbCartEntity cart where goodCheck = 'X' group by cart.goodId order by saleCount desc";
        List<Object[]> list = (List<Object[]>) this.getHibernateTemplate().find(hql);
        return topTenOfList(list);
    }

    @Override
    public List<StatisticsBean> findBottom() {
        String hql = "select sum(cart.goodCount) as saleCount,cart.goodName as goodsName,cart.goodPrice as goodsPrice from TbCartEntity cart where goodCheck = 'X' group by cart.goodId order by saleCount asc ";
        List<Object[]> list = (List<Object[]>) this.getHibernateTemplate().find(hql);
        return topTenOfList(list);
    }

    @Override
    public List<StatisticsBean> findByDate(Date startDate, Date endDate, Integer orderKey) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        if(orderKey==1){
            //销量前十
            String hql = "select sum(cart.goodCount) as saleCount,cart.goodName as goodsName,cart.goodPrice as goodsPrice from TbCartEntity cart where goodCheck = 'X' and cart.id between ? and ? group by cart.goodId order by saleCount desc";
            List<Object[]> list = (List<Object[]>) this.getHibernateTemplate().find(hql, dateFormat.format(startDate)+"%", dateFormat.format(endDate)+"%");
            return topTenOfList(list);
        }else if (orderKey==2){
            //销量后十
            String hql = "select sum(cart.goodCount) as saleCount,cart.goodName as goodsName,cart.goodPrice as goodsPrice from TbCartEntity cart where goodCheck = 'X' and cart.id between ? and ? group by cart.goodId order by saleCount asc ";
            List<Object[]> list = (List<Object[]>) this.getHibernateTemplate().find(hql, dateFormat.format(startDate)+"%", dateFormat.format(endDate)+"%");
            return topTenOfList(list);
        }
        return null;
    }

    private List<StatisticsBean> topTenOfList(List<Object[]> list){
        List<StatisticsBean> statisticsBeanList = new ArrayList<>();
        int count = 0;
        for (Object[] objects :
                list) {
            StatisticsBean tempStatistics = new StatisticsBean();
            tempStatistics.setCount(((Long)objects[0]).intValue());
            tempStatistics.setGoodsName((String) objects[1]);
            tempStatistics.setGoodsPrice((Integer) objects[2]);
            statisticsBeanList.add(tempStatistics);
            count = count + 1;
            if(count==10){
                break;
            }
        }
        return statisticsBeanList;
    }
}