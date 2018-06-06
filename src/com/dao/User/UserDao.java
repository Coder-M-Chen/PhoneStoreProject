package com.dao.User;

import com.bean.ImageOperations;
import com.entity.TbUserEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户表Dao类
 */
public class UserDao extends HibernateDaoSupport implements IUserDao {

    @Override
    public boolean addItem(TbUserEntity userEntity) {
        this.getHibernateTemplate().save(userEntity);
        return true;
    }

    @Override
    public boolean deleteItem(TbUserEntity userEntity) {
        TbUserEntity existUserEntity = findByIdAndPassword(userEntity);
        this.getHibernateTemplate().delete(existUserEntity);
        return true;
    }

    @Override
    public boolean updateItem(TbUserEntity userEntity) {
        this.getHibernateTemplate().update(userEntity);
        return true;
    }

    @Override
    public TbUserEntity findByIdAndPassword(TbUserEntity userEntity) {
        String hql = "from TbUserEntity where userId = ? and password = ?";
        List<TbUserEntity> list = (List<TbUserEntity>) this.getHibernateTemplate().find(hql, userEntity.getUserId(), userEntity.getPassword());

        if(list.size()>0){
            ImageOperations.byte2image(list.get(0).getImage(), "F:\\IDEA\\PhoneStore\\web\\images\\"+list.get(0).getUserId()+".png");
            return list.get(0);
        }
        return null;
    }

    @Override
    public TbUserEntity findById(String userId) {
        String hql = "from TbUserEntity where userId = ?";
        List<TbUserEntity> list = (List<TbUserEntity>) this.getHibernateTemplate().find(hql, userId);
        if(list.size()>0){
            ImageOperations.byte2image(list.get(0).getImage(), "F:\\IDEA\\PhoneStore\\web\\images\\"+list.get(0).getUserId()+".png");
            ImageOperations.byte2image(list.get(0).getImage(), "F:\\IDEA\\PhoneStore\\out\\artifacts\\PhoneStore_war_exploded\\images\\"+list.get(0).getUserId()+".png");
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<TbUserEntity> findByUsername(TbUserEntity userEntity)
    {
        String hql = "from TbUserEntity where vName like ?";
        List<TbUserEntity> list = (List<TbUserEntity>) this.getHibernateTemplate().find(hql, userEntity.getvName());
        for (TbUserEntity tempUserEntity: list) {
            ImageOperations.byte2image(list.get(0).getImage(), "F:\\IDEA\\PhoneStore\\web\\images\\"+tempUserEntity.getUserId()+".png");
        }
        if(list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public List<TbUserEntity> findAll2Admin(int begin, int pageSize) {
        String hql = "from TbUserEntity where userType = 'U' and certification = 'To Be An Admin'";
        List<TbUserEntity> existList = (List<TbUserEntity>) this.getHibernateTemplate().find(hql);
        List<TbUserEntity> list = new ArrayList<TbUserEntity>();
        for(int i = 0; i < pageSize&&begin + i < existList.size(); i++){
            list.add(existList.get(begin + i));
        }
        return list;
    }

    @Override
    public int findAll2AdminCount(){
        String hql = "select count(*) from TbUserEntity where userType = 'U' and certification = 'To Be An Admin'";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public boolean isNewByPhone(String userId) {
        String hql = "select count(*) from TbUserEntity where userId = ?";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, userId);
        if (list.size()>0){
            return false;
        }
        return true;
    }
}