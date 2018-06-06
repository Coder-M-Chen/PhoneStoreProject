package com.dao.News;

import com.bean.ImageOperations;
import com.entity.TbNewsEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsDao extends HibernateDaoSupport implements INewsDao {
    @Override
    public boolean addItem(TbNewsEntity newsEntity) {
        this.getHibernateTemplate().save(newsEntity);
        ImageOperations.byte2image(newsEntity.getNewsImage(), "F:\\IDEA\\PhoneStore\\web\\images\\"+newsEntity.getNewsId()+".png");
        ImageOperations.byte2image(newsEntity.getNewsImage(), "F:\\IDEA\\PhoneStore\\out\\artifacts\\PhoneStore_war_exploded\\images\\"+newsEntity.getNewsId()+".png");
        return true;
    }

    @Override
    public boolean deleteItem(TbNewsEntity newsEntity) {
        this.getHibernateTemplate().delete(newsEntity);
        return true;
    }

    @Override
    public boolean updateItem(TbNewsEntity newsEntity) {
        this.getHibernateTemplate().update(newsEntity);
        ImageOperations.byte2image(newsEntity.getNewsImage(), "F:\\IDEA\\PhoneStore\\web\\images\\"+newsEntity.getNewsId()+".png");
        ImageOperations.byte2image(newsEntity.getNewsImage(), "F:\\IDEA\\PhoneStore\\out\\artifacts\\PhoneStore_war_exploded\\images\\"+newsEntity.getNewsId()+".png");
        return true;
    }

    @Override
    public List<TbNewsEntity> findByOther(TbNewsEntity newsEntity) {
        String hql = new String();
        List<TbNewsEntity> list;
        if(newsEntity.getNewsId()!=null){
            hql = "from TbNewsEntity where newsId = ?";
            list = (List<TbNewsEntity>) this.getHibernateTemplate().find(hql,newsEntity.getNewsId());
            if(list.size()>0) {
                return list;
            }
        }
        if(newsEntity.getNewsBody()!=null)
        {
            hql = "from TbNewsEntity where newsBody = ?";
            list = (List<TbNewsEntity>) this.getHibernateTemplate().find(hql,"%"+newsEntity.getNewsBody()+"%");
            if (list.size()>0){
                return list;
            }
        }
        return null;
    }

    @Override
    public List<TbNewsEntity> find() {
        String hql = "from TbNewsEntity";
        List<TbNewsEntity> list = (List<TbNewsEntity>) this.getHibernateTemplate().find(hql);
        for (TbNewsEntity newsEntity :
                list) {
            ImageOperations.byte2image(newsEntity.getNewsImage(), "F:\\IDEA\\PhoneStore\\web\\images\\" + newsEntity.getNewsId() + ".png");
            ImageOperations.byte2image(newsEntity.getNewsImage(), "F:\\IDEA\\PhoneStore\\out\\artifacts\\PhoneStore_war_exploded\\images\\" + newsEntity.getNewsId() + ".png");
        }
        return list;
    }

    @Override
    public int findCount() {
        String hql = "select count(*) from TbNewsEntity";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbNewsEntity> findByPage(int begin, int pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TbNewsEntity.class);
        return (List<TbNewsEntity>) this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
    }

    @Override
    public TbNewsEntity findById(String newsId) {
        String hql = "from TbNewsEntity where newsId = ?";
        List<TbNewsEntity> list = (List<TbNewsEntity>) this.getHibernateTemplate().find(hql, newsId);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<TbNewsEntity> findTopFiveList(){
        String hql = "from TbNewsEntity where newsEndTime > ? order by newsId desc";
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        List<TbNewsEntity> existList = (List<TbNewsEntity>)this.getHibernateTemplate().find(hql, date);
        List<TbNewsEntity> list = new ArrayList<>();
        int count = 0;
        for (TbNewsEntity currNewsEntity :
                existList) {
            list.add(currNewsEntity);
            count=count+1;
            if(count==5)break;
        }
        return list;
    }
}