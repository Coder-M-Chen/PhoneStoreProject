package com.service;

import com.bean.PageBean;
import com.dao.Comment.CommentDao;
import com.entity.TbCommentEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class CommentService {
    private CommentDao commentDao;

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<TbCommentEntity> find(TbCommentEntity commentEntity, Integer key) {
        List<TbCommentEntity> list = null;
        switch (key){
            case 0:
                //查询全部评论
                list = commentDao.find();
                break;
            case 1:
                //查询单条评论记录
                list =new ArrayList<TbCommentEntity>();
                list.add(commentDao.findByID(commentEntity.getCommentId()));
                break;
            case 2:
                //按商品查询评论记录
                list = commentDao.findByGoodsId(commentEntity.getGoodsId());
                break;
            default:
        }
        return list;
    }

    public void add(TbCommentEntity commentEntity) {
        commentDao.addItem(commentEntity);
    }

    public void update(TbCommentEntity commentEntity) {
        commentDao.updateItem(commentEntity);
    }

    public TbCommentEntity findById(String commentId) {
        return commentDao.findByID(commentId);
    }

    public PageBean<TbCommentEntity> findByGood(String goodsId, Integer currPage) {
        PageBean<TbCommentEntity> pageBean = new PageBean<TbCommentEntity>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        int pageSize = 10;
        pageBean.setPageSize(pageSize);
        //封装总记录数
        int totalCount = commentDao.findCountByGoodsId(goodsId);
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double dUser2AdminCount = totalCount;
        Double pageCount = Math.ceil(dUser2AdminCount / pageSize);
        pageBean.setTotalPage(pageCount.intValue());
        //封装每页显示的数据
        int begin = (currPage - 1)*pageSize;
        List<TbCommentEntity> list = commentDao.findByGoodAndPage(goodsId, begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }
}
