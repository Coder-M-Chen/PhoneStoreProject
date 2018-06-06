package com.dao.Comment;

import com.entity.TbCommentEntity;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class CommentDao extends HibernateDaoSupport implements ICommentDao {
    @Override
    public boolean addItem(TbCommentEntity commentEntity) {
        this.getHibernateTemplate().save(commentEntity);
        return true;
    }

    @Override
    public boolean deleteItem(TbCommentEntity commentEntity) {
        this.getHibernateTemplate().delete(commentEntity);
        return true;
    }

    @Override
    public boolean updateItem(TbCommentEntity commentEntity) {
        this.getHibernateTemplate().update(commentEntity);
        return true;
    }

    @Override
    public TbCommentEntity findByID(String id) {
        String hql ="from TbCommentEntity where commentId = ?";
        List<TbCommentEntity> list = (List<TbCommentEntity>) this.getHibernateTemplate().find(hql, id);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<TbCommentEntity> findByOther(TbCommentEntity commentEntity) {
        return null;
    }

    @Override
    public List<TbCommentEntity> find() {
        String hql = "from TbCommentEntity";
        return (List<TbCommentEntity>) this.getHibernateTemplate().find(hql);
    }

    @Override
    public List<TbCommentEntity> findByGoodsId(String goodsId) {
        String hql = "from TbCommentEntity where goodsId = ?";
        return (List<TbCommentEntity>) this.getHibernateTemplate().find(hql, goodsId);
    }

    @Override
    public int findCountByGoodsId(String goodId) {
        String hql = "select count(*) from TbCommentEntity where goodsId = ?";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, goodId);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbCommentEntity> findByGoodAndPage(String goodsId, int begin, int pageSize) {
        String hql = "from TbCommentEntity where goodsId = ?";
        List<TbCommentEntity> existList = (List<TbCommentEntity>) this.getHibernateTemplate().find(hql, goodsId);
        List<TbCommentEntity> list = new ArrayList<TbCommentEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++){
            list.add(existList.get(begin + i));
        }
        return list;
    }
}