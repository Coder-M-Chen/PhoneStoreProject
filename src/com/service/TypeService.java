package com.service;

import com.bean.PageBean;
import com.dao.Type.TypeDao;
import com.entity.TbTypeEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class TypeService {
    private TypeDao typeDao;

    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    /**
     * 按照页面查询
     * @param currPage
     * @return
     */
    public PageBean<TbTypeEntity> findByPage(Integer currPage){
        PageBean<TbTypeEntity> pageBean = new PageBean<TbTypeEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 10;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = typeDao.findCount();
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dTypeCount = totalCount;
        Double pageCount = Math.ceil(dTypeCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbTypeEntity> list = typeDao.findByPage(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    public void addItem(TbTypeEntity typeEntity) {
        typeDao.addItem(typeEntity);
    }

    public List<TbTypeEntity> findAllForList() {
        return typeDao.findAll();
    }

    public TbTypeEntity findById(String typeId) {
        return typeDao.findByID(typeId);
    }

    public void update(TbTypeEntity typeEntity) {
        typeDao.updateItem(typeEntity);
    }
}
