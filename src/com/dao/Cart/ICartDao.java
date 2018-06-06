package com.dao.Cart;

import com.bean.StatisticsBean;
import com.entity.TbCartEntity;

import java.util.Date;
import java.util.List;

public interface ICartDao {
    /**
     * 添加到个人购物车
     * @param cartEntity
     */
    public void addItem(TbCartEntity cartEntity);

    /**
     * 从个人购物车删除
     * @param cartEntity
     */
    public void deleteItem(TbCartEntity cartEntity);

    /**
     * 更新购物车中的数据项
     * @param cartEntity
     */
    public void updateItem(TbCartEntity cartEntity);

    /**
     * 通过用户编号查询
     * @param id
     */
    public List<TbCartEntity> findByUserId(String userId);

    /**
     * 通过用户编号和商品编号查询
     * @param userId
     * @param goodId
     * @return
     */
    public TbCartEntity findByUserIdAndGoodId(String userId, String goodId);

    /**
     * 查询总记录数
     * @return
     */
    int findCount();

    /**
     * 查询个人购物车中的物品条目数量
     * @param userId
     * @return
     */
    int findCountByUserId(String userId);

    /**
     * 分页查询
     * @param begin
     * @param pageSize
     * @return
     */
    List<TbCartEntity> findByPage(int begin, int pageSize, String userId);

    /**
     * 根据购物车信息编号查询记录
     * @param cartId
     * @return
     */
    TbCartEntity findByCartId(String cartId);

    List<StatisticsBean> findTop();

    List<StatisticsBean> findBottom();

    List<StatisticsBean> findByDate(Date startDate, Date endDate, Integer orderKey);
}
