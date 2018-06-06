package com.dao.Type;

import com.entity.TbTypeEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class TypeDao extends HibernateDaoSupport implements ITypeDao {
    @Override
    public boolean addItem(TbTypeEntity typeEntity) {
        this.getHibernateTemplate().save(typeEntity);
        return true;
    }

    @Override
    public boolean deleteItem(String id)
    {
        TbTypeEntity typeEntity = findByID(id);
        this.getHibernateTemplate().delete(typeEntity);
        return true;
    }

    @Override
    public boolean updateItem(TbTypeEntity typeEntity) {
        this.getHibernateTemplate().update(typeEntity);
        return true;
    }

    @Override
    public TbTypeEntity findByID(String id)
    {
        String hql = "from TbTypeEntity where typeId = ?";
        List<TbTypeEntity> list = (List<TbTypeEntity>) this.getHibernateTemplate().find(hql, id);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<TbTypeEntity> findByOther(TbTypeEntity typeEntity) {
        return null;
    }

    @Override
    public int findCount() {
        String hql = "select count(*) from TbTypeEntity ";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<TbTypeEntity> findAll() {
        String hql = "from TbTypeEntity";
        return (List<TbTypeEntity>) this.getHibernateTemplate().find(hql);
    }

    /**
     * 按页码分页查询所有商品类型
     * @param begin 该页记录的起始编号
     * @param pageSize 每页存储的数据量
     * @return 该页显示的内容
     */
    public List<TbTypeEntity> findByPage(int begin, int pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TbTypeEntity.class);
        return (List<TbTypeEntity>) this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
    }
}