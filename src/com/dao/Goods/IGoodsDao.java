package com.dao.Goods;

import com.entity.TbGoodsEntity;

import java.util.List;

public interface IGoodsDao {

    /**
     * 添加商品
     * @param goodsEntity
     * @return
     */
    public boolean addItem(TbGoodsEntity goodsEntity);

    /**
     * 删除商品
     * @param goodsEntity 商品实体
     * @return
     */
    public boolean deleteItem(TbGoodsEntity goodsEntity);

    /**
     * 更新商品
     * @param goodsEntity
     * @return
     */
    public boolean updateItem(TbGoodsEntity goodsEntity);

    /**
     * 按照ID查找商品
     * @param goodsId
     * @return
     */
    public TbGoodsEntity findByID(String goodsId);

    /**
     * 查询所有商品
     * @return
     */
    public List<TbGoodsEntity> find();

    /**
     * 查询数据库表中的记录数
     * @return
     */
    int findCount();

    /**
     * 分页查询所有商品
     * @param begin
     * @param pageSize
     * @return
     */
    List<TbGoodsEntity> findByPage(int begin, int pageSize);

    /**
     * 通过商品名称分页查询
     * @param begin
     * @param pageSize
     * @param goodsName
     * @return
     */
    List<TbGoodsEntity> findByPageAndGoodsName(int begin, int pageSize, String goodsName);

    List<TbGoodsEntity> findByPageAndTypeId(int begin, int pageSize, String typeId);

    int findCountByGoodsName(String goodsName);

    int findCountByTypeId(String typeId);

    List<TbGoodsEntity> findByPriceAndPage(int begin, int pageSize);

    List<TbGoodsEntity> findByTimeAndPage(int begin, int pageSize);

    int findCountUseful();

    List<TbGoodsEntity> findByPageUseful(int begin, int pageSize);
}
