package com.action;

import com.bean.GoodsList;
import com.bean.ImageOperations;
import com.bean.PageBean;
import com.entity.TbCartEntity;
import com.entity.TbGoodsEntity;
import com.entity.TbTypeEntity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.GoodsService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GoodsAction extends ActionSupport implements ModelDriven<TbGoodsEntity>{
    private TbGoodsEntity goodsEntity = new TbGoodsEntity();

    @Override
    public TbGoodsEntity getModel() {
        return goodsEntity;
    }

    private GoodsService goodsService;

    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    private String imagePath;
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
    public String add(){
        try{
            File destFile  = new File(destPath, myFileFileName);
            FileUtils.copyFile(myFile, destFile);
        }catch(IOException e){
            e.printStackTrace();
        }
        goodsEntity.setGoodsImage(ImageOperations.image2byte(destPath+myFileFileName));

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        goodsEntity.setGoodsId(dateFormat.format(date));
        try {
            goodsService.add(goodsEntity);
        }catch (Exception e){
            e.printStackTrace();
            ActionContext.getContext().getSession().remove("errorInfo");
            ActionContext.getContext().getSession().put("errorInfo","数据插入异常");
            return "addError";
        }
        return "addSuccess";
    }

    public String update(){
        TbGoodsEntity existGoodsEntity = goodsService.findById(goodsEntity.getGoodsId());
        if(myFile!=null&&existGoodsEntity!=null) {
            try {
                File destFile = new File(destPath, myFileFileName);
                FileUtils.copyFile(myFile, destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        existGoodsEntity.setGoodsImage(ImageOperations.image2byte(destPath+myFileFileName));
        existGoodsEntity.setGoodsName(goodsEntity.getGoodsName());
        existGoodsEntity.setGoodsInfo(goodsEntity.getGoodsInfo());
        existGoodsEntity.setGoodsType(goodsEntity.getGoodsType());
        existGoodsEntity.setGoodsPrice(goodsEntity.getGoodsPrice());
        existGoodsEntity.setGoodsDiscount(goodsEntity.getGoodsDiscount());
        existGoodsEntity.setGoodsAmount(goodsEntity.getGoodsAmount());
        existGoodsEntity.setGoodsState(goodsEntity.getGoodsState());
        goodsService.update(existGoodsEntity);
        return "updateSuccess";
    }

    public String delete(){
        goodsService.delete(goodsEntity);
        return "deleteSuccess";
    }

    private Integer currPage = 1;
    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public String initJsp(){
        PageBean<TbGoodsEntity> pageBean = goodsService.findByPage(currPage, 12);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "initJspSuccess";
    }

    public String findAll(){
        PageBean<TbGoodsEntity> pageBean = goodsService.findByPage(currPage, 12);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAllSuccess";
    }

    /**
     * 用户分页浏览所有商品
     * @return
     */
    public String findAllUser(){
        PageBean<TbGoodsEntity> pageBean = goodsService.findByPageUseful(currPage, 15);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAllUserSuccess";
    }

    private Integer key = 0;
    public void setKey(Integer key) {
        this.key = key;
    }
    private String keyString;
    public void setKeyString(String keyString){
        this.keyString = keyString;
    }
    public String find(){
        currPage = 1;
        if(key == 1){
            goodsEntity.setGoodsName(keyString);
            findByGoodsName();
        }else if(key == 2){
            List<TbTypeEntity> existTypeList = (List<TbTypeEntity>)ActionContext.getContext().getSession().get("typeList");
            for (TbTypeEntity currTypeEntity:
                 existTypeList) {
                if(currTypeEntity.getTypeName().equals(keyString)){
                    typeId = currTypeEntity.getTypeId();
                    break;
                }
            }
            findByTypeId();
        }
        return "findSuccess";
    }

    private String goodId;
    public void setGoodId(String goodId){
        this.goodId = goodId;
    }
    public String findById(){
        TbGoodsEntity existGoodsEntity = goodsService.findById(goodId);
        ActionContext.getContext().getSession().remove("goodsEntity");
        ActionContext.getContext().getSession().put("goodsEntity",existGoodsEntity);
        return "findByIdSuccess";
    }

    private String currGoodId;
    public void setCurrGoodId(String currGoodId){
        this.currGoodId = currGoodId;
    }
    public String toUpdatePage(){
        TbGoodsEntity existGoodsEntity = goodsService.findById(currGoodId);
        if(existGoodsEntity!=null){
            ActionContext.getContext().getSession().remove("goodsEntity");
            ActionContext.getContext().getSession().put("goodsEntity", existGoodsEntity);
        }
        return "toUpdatePageSuccess";
    }

    public String findByGoodsName(){
        currPage = 1;
        PageBean<TbGoodsEntity> pageBean = goodsService.findByGoodsName(currPage, goodsEntity.getGoodsName());
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findByGoodsNameSuccess";
    }

    private File myFile;
    private String myFileContentType;
    private String myFileFileName;
    private String destPath = "F:\\IDEA\\PhoneStore\\web\\images\\";
    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }
    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }
    public void setMyFileFileName(String myFileFileName) {
        this.myFileFileName = myFileFileName;
    }
    public File getMyFile() {
        return myFile;
    }
    public String getMyFileContentType() {
        return myFileContentType;
    }
    public String getMyFileFileName() {
        return myFileFileName;
    }

    private String typeId;
    public void setTypeId(String typeId){
        this.typeId=typeId;
    }
    public String findByTypeId(){
        currPage = 1;
        PageBean<TbGoodsEntity> pageBean = goodsService.findByTypeId(typeId, currPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findByTypeIdSuccess";
    }

    public String findAllUserPrice(){
        PageBean<TbGoodsEntity> pageBean = goodsService.findByPriceAndPage(currPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAllUserPriceSuccess";
    }

    public String findAllUserTime(){
        PageBean<TbGoodsEntity> pageBean = goodsService.findByTimeAndPage(currPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAllUserTimeSuccess";
    }

    public String updateCount(){
        GoodsList goodsList = (GoodsList)ActionContext.getContext().getSession().get("goodsList");
        List<TbCartEntity> list = goodsList.getList();
        for (TbCartEntity currCartEntity :
                list) {
            TbGoodsEntity currGoodsEntity = goodsService.findById(currCartEntity.getGoodId());
            Integer count = currGoodsEntity.getGoodsAmount();
            if(count<=currCartEntity.getGoodCount()){
                ActionContext.getContext().getSession().remove("errorInfo");
                ActionContext.getContext().getSession().put("errorInfo", "商品数量不足");
            }
            currGoodsEntity.setGoodsAmount(count - currCartEntity.getGoodCount());
            goodsService.update(currGoodsEntity);
        }
        return "updateCountSuccess";
    }

    public String toAddComment(){
        TbGoodsEntity existGoodEntity = goodsService.findById(goodId);
        if(ActionContext.getContext().getSession().containsKey("goodsEntity")){
            ActionContext.getContext().getSession().remove("goodsEntity");
        }
        ActionContext.getContext().getSession().put("goodsEntity", existGoodEntity);
        return "toAddCommentSuccess";
    }
}