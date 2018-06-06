package com.dao.Comment;

import com.entity.TbCommentEntity;

import java.util.List;

public interface ICommentDao {

    /**
     * 添加评论
     * @param commentEntity
     * @return
     */
    public boolean addItem(TbCommentEntity commentEntity);

    /**
     * 删除评论
     * @param commentEntity
     * @return
     */
    public boolean deleteItem(TbCommentEntity commentEntity);

    /**
     * 更新评论
     * @param commentEntity
     * @return
     */
    public boolean updateItem(TbCommentEntity commentEntity);

    /**
     * 按照ID查找评论
     * @param id
     * @return
     */
    public TbCommentEntity findByID(String id);

    /**
     * 根据其他条件查询
     * @param commentEntity
     * @return
     */
    public List<TbCommentEntity> findByOther(TbCommentEntity commentEntity);

    /**
     * 查询所有评论
     * @return
     */
    public List<TbCommentEntity> find();

    /**
     * 按商品编号查询评论
     * @param goodsId
     * @return
     */
    List<TbCommentEntity> findByGoodsId(String goodsId);

    int findCountByGoodsId(String goodId);

    List<TbCommentEntity> findByGoodAndPage(String goodsId, int begin, int pageSize);
}
