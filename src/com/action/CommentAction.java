package com.action;

import com.bean.PageBean;
import com.entity.TbCommentEntity;
import com.entity.TbGoodsEntity;
import com.entity.TbOrderEntity;
import com.entity.TbUserEntity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.CommentService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentAction extends ActionSupport implements ModelDriven<TbCommentEntity> {
    private TbCommentEntity commentEntity = new TbCommentEntity();

    @Override
    public TbCommentEntity getModel() {
        return commentEntity;
    }

    private CommentService commentService;

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public String add(){
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");
        TbOrderEntity currOrderEntity = (TbOrderEntity)ActionContext.getContext().getSession().get("orderEntity");
        TbGoodsEntity currGoodEntity = (TbGoodsEntity)ActionContext.getContext().getSession().get("goodsEntity");

        commentEntity.setGoodsId(currGoodEntity.getGoodsId());
        commentEntity.setOrderId(currOrderEntity.getOrderId());
        commentEntity.setUserId(currUserEntity.getUserId());
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        commentEntity.setCommentId(dateFormat.format(date));

        commentService.add(commentEntity);
        return "addSuccess";
    }

    public String update(){
        commentService.update(commentEntity);
        return "updateSuccess";
    }

    private Integer key = 0;
    public void setKey(Integer key) {
        this.key = key;
    }
    public String find(){
        List<TbCommentEntity> list = commentService.find(commentEntity, key);
        ActionContext.getContext().getValueStack().push(list);
        return "findSuccess";
    }

    private String commentId;
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
    public String findById(){
        TbCommentEntity existCommentEntity = commentService.findById(commentId);
        ActionContext.getContext().getSession().remove("commentEntity");
        ActionContext.getContext().getSession().put("commentEntity", existCommentEntity);
        return "findByIdSuccess";
    }

    public String findFirstByGood(){
        TbGoodsEntity currGoodEntity = (TbGoodsEntity)ActionContext.getContext().getSession().get("goodsEntity");
        PageBean<TbCommentEntity> pageBean = commentService.findByGood(currGoodEntity.getGoodsId(), 1);
        TbCommentEntity firstCommentEntity = null;
        if(pageBean.getList().size()>0){
            firstCommentEntity = pageBean.getList().get(0);
        }
        if(ActionContext.getContext().getSession().containsKey("commentEntity")){
            ActionContext.getContext().getSession().remove("commentEntity");
        }
        ActionContext.getContext().getSession().put("commentEntity", firstCommentEntity);

        return "findFirstByGoodSuccess";
    }

    private Integer currPage = 1;
    public void setCurrPage(Integer currPage){
        this.currPage = currPage;
    }
    public String findByGood(){
        TbGoodsEntity currGoodEntity = (TbGoodsEntity)ActionContext.getContext().getSession().get("goodsEntity");
        PageBean<TbCommentEntity> pageBean = commentService.findByGood(currGoodEntity.getGoodsId(),currPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findByGoodSuccess";
    }
}