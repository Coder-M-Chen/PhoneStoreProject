package com.dao.Type;

import com.entity.TbTypeEntity;

import java.util.List;

public interface ITypeDao{

    /**
     * 添加商品类型
     * @param typeEntity
     * @return
     */
    public boolean addItem(TbTypeEntity typeEntity);

    /**
     * 删除商品类型
     * @param id
     * @return
     */
    public boolean deleteItem(String id);

    /**
     * 更新商品类型
     * @param typeEntity
     * @return
     */
    public boolean updateItem(TbTypeEntity typeEntity);

    /**
     * 按照ID查找商品类型
     * @param id
     * @return
     */
    public TbTypeEntity findByID(String id);

    /**
     * 根据其他条件查询
     * @param typeEntity
     * @return
     */
    public List<TbTypeEntity> findByOther(TbTypeEntity typeEntity);

    /**
     * 统计记录个数
     * @return
     */
    public int findCount();

    List<TbTypeEntity> findAll();
}
