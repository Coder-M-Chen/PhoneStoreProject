package com.action;

import com.bean.PageBean;
import com.entity.TbFeedbackEntity;
import com.entity.TbUserEntity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.FeedbackService;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FeedbackAction extends ActionSupport implements ModelDriven<TbFeedbackEntity> {
    private TbFeedbackEntity feedbackEntity = new TbFeedbackEntity();

    @Override
    public TbFeedbackEntity getModel() {
        return feedbackEntity;
    }

    private FeedbackService feedbackService;
    public void setFeedbackService(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    public String add(){
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");
        feedbackEntity.setFeedbackOwner(currUserEntity.getUserId());
        feedbackEntity.setFeedbackState("待受理");

        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        feedbackEntity.setFeedbackTime(timeStamp);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        feedbackEntity.setFeedbackId(dateFormat.format(date));
        feedbackEntity.setFeedbackExecutor("13000000000");
        feedbackService.add(feedbackEntity);
        return "addSuccess";
    }

    public String delete(){
        feedbackService.delete(feedbackEntity);
        return "deleteSuccess";
    }

    public String update(){
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");
        TbFeedbackEntity existFeedbackEntity = feedbackService.findByFeedbackId(feedbackEntity.getFeedbackId());
        if(existFeedbackEntity!=null){
            //管理员回复反馈
            existFeedbackEntity.setFeedbackState("已受理");
            existFeedbackEntity.setFeedbackExecutor(currUserEntity.getUserId());
            feedbackService.update(existFeedbackEntity);
            return "updateSuccess";
        }
        return "error";
    }

    private int currPage = 1;
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
    public String findAll(){
        PageBean<TbFeedbackEntity> pageBean = feedbackService.findByPage(currPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAllSuccess";
    }

    public String toUpdatePage(){
        TbFeedbackEntity existFeedbackEntity = feedbackService.findByFeedbackId(feedbackEntity.getFeedbackId());
        System.out.println(existFeedbackEntity.getFeedbackId()+" : "+existFeedbackEntity.getFeedbackBody());
        if(ActionContext.getContext().getSession().containsKey("feedbackEntity")) {
            ActionContext.getContext().getSession().remove("feedbackEntity");
        }
        ActionContext.getContext().getSession().put("feedbackEntity",existFeedbackEntity);
        return "toUpdateJsp";
    }

    public String toPageAdd(){
        return "toPageAddSuccess";
    }

    public String findAllUser(){
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");
        PageBean<TbFeedbackEntity> pageBean = feedbackService.findByUserAndPage(currUserEntity.getUserId(), currPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAllUserSuccess";
    }
}