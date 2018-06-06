package com.dao.News;

import com.entity.TbNewsEntity;

import java.util.List;

public interface INewsDao {

    /**
     * 添加新闻消息
     * @param newsEntity
     * @return
     */
    public boolean addItem(TbNewsEntity newsEntity);

    /**
     * 删除新闻消息
     * @param newsEntity 新闻实体
     * @return
     */
    public boolean deleteItem(TbNewsEntity newsEntity);

    /**
     * 更新新闻消息
     * @param newsEntity
     * @return
     */
    public boolean updateItem(TbNewsEntity newsEntity);

    /**
     * 根据其他条件查询
     * @param newsEntity
     * @return
     */
    public List<TbNewsEntity> findByOther(TbNewsEntity newsEntity);

    /**
     * 查询所有新闻消息
     * @return
     */
    public List<TbNewsEntity> find();

    /**
     * 查询新闻消息的总数
     * @return
     */
    int findCount();

    /**
     * 分页显示新闻消息
     * @param begin 其实新闻的索引编号
     * @param pageSize 每页能够显示的新闻数量
     * @return 返回该页新闻内容
     */
    List<TbNewsEntity> findByPage(int begin, int pageSize);

    TbNewsEntity findById(String newsId);

    List<TbNewsEntity> findTopFiveList();
}