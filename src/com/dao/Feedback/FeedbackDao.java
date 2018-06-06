package com.dao.Feedback;

import com.entity.TbFeedbackEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class FeedbackDao extends HibernateDaoSupport implements IFeedbackDao {
    @Override
    public boolean addItem(TbFeedbackEntity feedbackEntity) {
        this.getHibernateTemplate().save(feedbackEntity);
        return true;
    }

    @Override
    public boolean deleteItem(TbFeedbackEntity feedbackEntity) {
        this.getHibernateTemplate().delete(feedbackEntity);
        return true;
    }

    @Override
    public boolean updateItem(TbFeedbackEntity feedbackEntity) {
        this.getHibernateTemplate().update(feedbackEntity);
        return true;
    }

    @Override
    public TbFeedbackEntity findById(String id) {
        String hql = "from TbFeedbackEntity where feedbackId = ?";
        List<TbFeedbackEntity> list = (List<TbFeedbackEntity>) this.getHibernateTemplate().find(hql,id);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<TbFeedbackEntity> findByOther(TbFeedbackEntity feedbackEntity) {
        return null;
    }

    @Override
    public List<TbFeedbackEntity> find() {
        String hql = "from TbFeedbackEntity";
        return (List<TbFeedbackEntity>) this.getHibernateTemplate().find(hql);
    }

    @Override
    public int findCount() {
        String hql = "select count(*) from TbFeedbackEntity ";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbFeedbackEntity> findByPage(int begin, int pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TbFeedbackEntity.class);
        return (List<TbFeedbackEntity>) this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
    }

    @Override
    public int findCountOfUser(String userId) {
        String hql = "select count(*) from TbFeedbackEntity where feedbackOwner = ?";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, userId);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbFeedbackEntity> findByUserAndPage(String userId, int begin, int pageSize) {
        String hql = "from TbFeedbackEntity where feedbackOwner = ?";
        List<TbFeedbackEntity> existList = (List<TbFeedbackEntity>) this.getHibernateTemplate().find(hql, userId);
        List<TbFeedbackEntity> list = new ArrayList<TbFeedbackEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++){
            list.add(existList.get(begin + i));
        }
        return list;
    }
}