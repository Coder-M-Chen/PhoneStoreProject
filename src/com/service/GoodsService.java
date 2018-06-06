package com.service;

import com.bean.PageBean;
import com.dao.Goods.GoodsDao;
import com.entity.TbGoodsEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class GoodsService {
    private GoodsDao goodsDao;

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    public void add(TbGoodsEntity goodsEntity) {
        goodsDao.addItem(goodsEntity);
    }

    public void delete(TbGoodsEntity goodsEntity) {
        TbGoodsEntity existGoodsEntity = goodsDao.findByID(goodsEntity.getGoodsId());
        goodsDao.deleteItem(existGoodsEntity);
    }

    public PageBean<TbGoodsEntity> findByPage(Integer currPage, Integer pageSize) {
        PageBean<TbGoodsEntity> pageBean = new PageBean<TbGoodsEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = goodsDao.findCount();
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dGoodsCount = totalCount;
        Double pageCount = Math.ceil(dGoodsCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbGoodsEntity> list = goodsDao.findByPage(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    public TbGoodsEntity findById(String goodsId) {
        return goodsDao.findByID(goodsId);
    }

    public void update(TbGoodsEntity existGoodsEntity) {
        goodsDao.updateItem(existGoodsEntity);
    }

    public PageBean<TbGoodsEntity> findByGoodsName(Integer currPage, String goodsName) {
        PageBean<TbGoodsEntity> pageBean = new PageBean<TbGoodsEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 15;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = goodsDao.findCountByGoodsName(goodsName);
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dGoodsCount = totalCount;
        Double pageCount = Math.ceil(dGoodsCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbGoodsEntity> list = goodsDao.findByPageAndGoodsName(begin, pageSize, goodsName);
        pageBean.setList(list);
        return pageBean;
    }

    public PageBean<TbGoodsEntity> findByTypeId(String typeId, Integer currPage) {
        PageBean<TbGoodsEntity> pageBean = new PageBean<TbGoodsEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 15;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = goodsDao.findCountByTypeId(typeId);
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dGoodsCount = totalCount;
        Double pageCount = Math.ceil(dGoodsCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbGoodsEntity> list = goodsDao.findByPageAndTypeId(begin, pageSize, typeId);
        pageBean.setList(list);
        return pageBean;
    }

    public PageBean<TbGoodsEntity> findByPriceAndPage(Integer currPage) {
        PageBean<TbGoodsEntity> pageBean = new PageBean<TbGoodsEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 15;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = goodsDao.findCount();
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dGoodsCount = totalCount;
        Double pageCount = Math.ceil(dGoodsCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbGoodsEntity> list = goodsDao.findByPriceAndPage(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    public PageBean<TbGoodsEntity> findByTimeAndPage(Integer currPage) {
        PageBean<TbGoodsEntity> pageBean = new PageBean<TbGoodsEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 15;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = goodsDao.findCount();
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dGoodsCount = totalCount;
        Double pageCount = Math.ceil(dGoodsCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbGoodsEntity> list = goodsDao.findByTimeAndPage(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    public PageBean<TbGoodsEntity> findByPageUseful(Integer currPage, int pageSize) {
        PageBean<TbGoodsEntity> pageBean = new PageBean<TbGoodsEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = goodsDao.findCountUseful();
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dGoodsCount = totalCount;
        Double pageCount = Math.ceil(dGoodsCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbGoodsEntity> list = goodsDao.findByPageUseful(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }
}