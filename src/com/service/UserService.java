package com.service;

import com.bean.MD5Encoding;
import com.bean.PageBean;
import com.dao.User.UserDao;
import com.entity.TbUserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserService {
    //业务层自动注入Dao的类
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public TbUserEntity login(TbUserEntity userEntity) {
        String userPassword = MD5Encoding.MD5(userEntity.getPassword());
        userEntity.setPassword(userPassword);
        return userDao.findByIdAndPassword(userEntity);
    }

    public void register(TbUserEntity userEntity){
        String userPassword = MD5Encoding.MD5(userEntity.getPassword());
        userEntity.setPassword(userPassword);
        userEntity.setPhone(userEntity.getUserId());
        userEntity.setvName(userEntity.getUserId());
        String phone = userEntity.getUserId();
        userEntity.setPhone(phone);
        userDao.addItem(userEntity);
    }

    public void update(TbUserEntity userEntity){
        userDao.updateItem(userEntity);
    }

    public List<TbUserEntity> find(TbUserEntity userEntity, Integer key) {
        List<TbUserEntity> list =null;
        switch (key){
            case 0:
                break;
            case 1:
                //通过用户名查找
                list = userDao.findByUsername(userEntity);
                break;
        }
        return list;
    }

    public TbUserEntity findById(String userId) {
        return userDao.findById(userId);
    }

    public PageBean<TbUserEntity> findAll2Admin(Integer currPage) {
        PageBean<TbUserEntity> pageBean = new PageBean<TbUserEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 10;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = userDao.findAll2AdminCount();
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dUser2AdminCount = totalCount;
        Double pageCount = Math.ceil(dUser2AdminCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbUserEntity> list = userDao.findAll2Admin(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

//    public void add2Cart(TbUserEntity userEntity, String goodId) {
//        TbUserEntity existUserEntity = userDao.findById(userEntity.getUserId());
//
//    }

    public boolean isNewByPhone(String userId) {
        return userDao.isNewByPhone(userId);
    }
}