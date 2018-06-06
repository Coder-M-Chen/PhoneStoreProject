package com.dao.User;

import com.entity.TbUserEntity;

import java.util.List;

public interface IUserDao {
    /**
     * 添加用户
     * @param userEntity
     * @return
     */
    public boolean addItem(TbUserEntity userEntity);

    /**
     * 删除用户
     * @param id
     * @return
     */
    public boolean deleteItem(TbUserEntity userEntity);

    /**
     * 更新用户
     * @param userEntity
     * @return
     */
    public boolean updateItem(TbUserEntity userEntity);

    /**
     * 按照ID和密码查找用户
     * @param id
     * @return
     */
    public TbUserEntity findByIdAndPassword(TbUserEntity userEntity);

    /**
     * 通过用户id查询用户
     * @param userId
     * @return
     */
    public TbUserEntity findById(String userId);

    /**
     * 根据用户名查找用户
     * @param userEntity
     * @return
     */
    public List<TbUserEntity> findByUsername(TbUserEntity userEntity);

    /**
     * 查询所有申请成为管理员的用户
     * @return
     * @param begin
     * @param pageSize
     */
    List<TbUserEntity> findAll2Admin(int begin, int pageSize);

    /**
     * 查询申请成为管理员的用户数量
     * @return
     */
    int findAll2AdminCount();

    boolean isNewByPhone(String userId);
//
//    /**
//     * 查询所有用户
//     * @return
//     */
//    public List<TbUserEntity>find();
}
