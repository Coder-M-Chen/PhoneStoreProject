package com.dao.Goods;

import com.bean.ImageOperations;
import com.entity.TbGoodsEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class GoodsDao extends HibernateDaoSupport implements IGoodsDao{
    @Override
    public boolean addItem(TbGoodsEntity goodsEntity) {
        this.getHibernateTemplate().save(goodsEntity);
        ImageOperations.byte2image(goodsEntity.getGoodsImage(), "F:\\IDEA\\PhoneStore\\web\\images\\"+goodsEntity.getGoodsId()+".png");
        ImageOperations.byte2image(goodsEntity.getGoodsImage(), "F:\\IDEA\\PhoneStore\\out\\artifacts\\PhoneStore_war_exploded\\images\\"+goodsEntity.getGoodsId()+".png");
        return true;
    }

    @Override
    public boolean deleteItem(TbGoodsEntity goodsEntity) {
        this.getHibernateTemplate().delete(goodsEntity);
        return true;
    }

    @Override
    public boolean updateItem(TbGoodsEntity goodsEntity) {
        this.getHibernateTemplate().update(goodsEntity);
        if(goodsEntity.getGoodsImage()!=null) {
            ImageOperations.byte2image(goodsEntity.getGoodsImage(), "F:\\IDEA\\PhoneStore\\web\\images\\" + goodsEntity.getGoodsId() + ".png");
            ImageOperations.byte2image(goodsEntity.getGoodsImage(), "F:\\IDEA\\PhoneStore\\out\\artifacts\\PhoneStore_war_exploded\\images\\" + goodsEntity.getGoodsId() + ".png");
        }
        return true;
    }

    @Override
    public TbGoodsEntity findByID(String goodsId) {
        String hql = "from TbGoodsEntity where goodsId = ?";
        List<TbGoodsEntity> list = (List<TbGoodsEntity>) this.getHibernateTemplate().find(hql, goodsId);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<TbGoodsEntity> find() {
        String hql = "from TbGoodsEntity where goodsState = '在售'";
        List<TbGoodsEntity> existList = (List<TbGoodsEntity>) this.getHibernateTemplate().find(hql);

        for (TbGoodsEntity currGoodEntity :
                existList) {
            if(currGoodEntity.getGoodsImage()!=null) {
                ImageOperations.byte2image(currGoodEntity.getGoodsImage(), "F:\\IDEA\\PhoneStore\\web\\images\\" + currGoodEntity.getGoodsId() + ".png");
                ImageOperations.byte2image(currGoodEntity.getGoodsImage(), "F:\\IDEA\\PhoneStore\\out\\artifacts\\PhoneStore_war_exploded\\images\\" + currGoodEntity.getGoodsId() + ".png");
            }
        }
        return existList;
    }

    @Override
    public int findCount() {
        String hql = "select count(*) from TbGoodsEntity";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbGoodsEntity> findByPage(int begin, int pageSize) {

        String hql = "from TbGoodsEntity";
        List<TbGoodsEntity> existList = (List<TbGoodsEntity>) this.getHibernateTemplate().find(hql);

        for (TbGoodsEntity currGoodEntity :
                existList) {
            if(currGoodEntity.getGoodsImage()!=null) {
                ImageOperations.byte2image(currGoodEntity.getGoodsImage(), "F:\\IDEA\\PhoneStore\\web\\images\\" + currGoodEntity.getGoodsId() + ".png");
                ImageOperations.byte2image(currGoodEntity.getGoodsImage(), "F:\\IDEA\\PhoneStore\\out\\artifacts\\PhoneStore_war_exploded\\images\\" + currGoodEntity.getGoodsId() + ".png");
            }
        }

        List<TbGoodsEntity> list = new ArrayList<TbGoodsEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++){
            list.add(existList.get(begin + i));
        }

        DetachedCriteria criteria = DetachedCriteria.forClass(TbGoodsEntity.class);
        return (List<TbGoodsEntity>) this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
    }

    @Override
    public List<TbGoodsEntity> findByPageAndGoodsName(int begin, int pageSize, String goodsName)
    {
        String hql = "from TbGoodsEntity where goodsName like ? and goodsState = '在售'";
        List<TbGoodsEntity> existList = (List<TbGoodsEntity>) this.getHibernateTemplate().find(hql, "%"+goodsName+"%");
        List<TbGoodsEntity> list = new ArrayList<TbGoodsEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++){
            list.add(existList.get(begin + i));
        }
        return list;
    }

    @Override
    public List<TbGoodsEntity> findByPageAndTypeId(int begin, int pageSize, String typeId) {
        String hql = "from TbGoodsEntity where goodsType = ? and goodsState = '在售'";
        List<TbGoodsEntity> existList = (List<TbGoodsEntity>) this.getHibernateTemplate().find(hql, typeId);
        List<TbGoodsEntity> list = new ArrayList<TbGoodsEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++){
            list.add(existList.get(begin + i));
        }
        return list;
    }

    @Override
    public int findCountByGoodsName(String goodsName) {
        String hql = "select count(*) from TbGoodsEntity where goodsName like ? and goodsState = '在售'";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql,"%"+goodsName+"%");
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public int findCountByTypeId(String typeId) {
        String hql = "select count(*) from TbGoodsEntity where goodsType = ? and goodsState = '在售'";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, typeId);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbGoodsEntity> findByPriceAndPage(int begin, int pageSize) {
        String hql = "from TbGoodsEntity where goodsState = '在售' order by goodsPrice";
        List<TbGoodsEntity> existList = (List<TbGoodsEntity>) this.getHibernateTemplate().find(hql);
        List<TbGoodsEntity> list = new ArrayList<TbGoodsEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++){
            list.add(existList.get(begin + i));
        }
        return list;
    }

    @Override
    public List<TbGoodsEntity> findByTimeAndPage(int begin, int pageSize) {
        String hql = "from TbGoodsEntity where goodsState = '在售' order by goodsId asc";
        List<TbGoodsEntity> existList = (List<TbGoodsEntity>) this.getHibernateTemplate().find(hql);
        List<TbGoodsEntity> list = new ArrayList<TbGoodsEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++){
            list.add(existList.get(begin + i));
        }
        return list;
    }

    @Override
    public int findCountUseful() {
        String hql = "select count(*) from TbGoodsEntity where goodsState = '在售'";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbGoodsEntity> findByPageUseful(int begin, int pageSize) {

        String hql = "from TbGoodsEntity where goodsState = '在售'";
        List<TbGoodsEntity> existList = (List<TbGoodsEntity>) this.getHibernateTemplate().find(hql);

        for (TbGoodsEntity currGoodEntity :
                existList) {
            if(currGoodEntity.getGoodsImage()!=null) {
                ImageOperations.byte2image(currGoodEntity.getGoodsImage(), "F:\\IDEA\\PhoneStore\\web\\images\\" + currGoodEntity.getGoodsId() + ".png");
                ImageOperations.byte2image(currGoodEntity.getGoodsImage(), "F:\\IDEA\\PhoneStore\\out\\artifacts\\PhoneStore_war_exploded\\images\\" + currGoodEntity.getGoodsId() + ".png");
            }
        }

        List<TbGoodsEntity> list = new ArrayList<TbGoodsEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++){
            list.add(existList.get(begin + i));
        }

        DetachedCriteria criteria = DetachedCriteria.forClass(TbGoodsEntity.class);
        return (List<TbGoodsEntity>) this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
    }
}