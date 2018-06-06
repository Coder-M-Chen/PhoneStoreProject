package com.dao.Order;

import com.entity.TbOrderEntity;

import java.util.List;

public interface IOrderDao {

    /**
     * 添加订单
     * @param orderEntity
     * @return
     */
    public boolean addItem(TbOrderEntity orderEntity);

    /**
     * 删除订单
     * @param orderEntity
     * @return
     */
    public boolean deleteItem(TbOrderEntity orderEntity);

    /**
     * 更新订单
     * @param orderEntity
     * @return
     */
    public boolean updateItem(TbOrderEntity orderEntity);

    /**
     * 按照ID查找订单
     * @param id
     * @return
     */
    public TbOrderEntity findByID(String id);

    /**
     * 根据其他条件查询
     * @param orderEntity
     * @return
     */
    public List<TbOrderEntity> findByOther(TbOrderEntity orderEntity);

    /**
     * 返回表中的订单数量
     * @return
     */
    int findCount();

    /**
     * 分页显示所有订单信息
     * @param begin
     * @param pageSize
     * @return
     */
    List<TbOrderEntity> findByPage(int begin, int pageSize);

    /**
     * 根据用户id查询订单
     * @param userId
     * @return
     */
    List<TbOrderEntity> findByUserId(String userId);

    List<TbOrderEntity> findByUserIdAndPage(String userId, int begin, int pageSize);

    int findCountByUserAndNoPay(String userId);

    List<TbOrderEntity> findByUserAndPageAndNoPay(String userId, int begin, int pageSize);

    int findCountByUserAndNoSend(String userId);

    List<TbOrderEntity> findByUserAndPageAndNoSend(String userId, int begin, int pageSize);

    int findCountByUser(String userId);

    int findCountNoSend();

    List<TbOrderEntity> findNoSend(int begin, int pageSize);

    int findCountNoPay();

    List<TbOrderEntity> findNoPay(int begin, int pageSize);
}