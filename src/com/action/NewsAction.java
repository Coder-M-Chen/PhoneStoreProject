package com.action;

import com.bean.ImageOperations;
import com.bean.PageBean;
import com.entity.TbNewsEntity;
import com.entity.TbUserEntity;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.NewsService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAction extends ActionSupport implements ModelDriven<TbNewsEntity> {
    private TbNewsEntity newsEntity = new TbNewsEntity();

    @Override
    public TbNewsEntity getModel() {
        return newsEntity;
    }

    private NewsService newsService;

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * 添加新闻
     * @return
     */
    public String add(){
        TbUserEntity currUserEntity = (TbUserEntity) ActionContext.getContext().getSession().get("userEntity");
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        if (myFile!=null) {
            try {
                File destFile = new File(destPath, myFileFileName);
                FileUtils.copyFile(myFile, destFile);
                newsEntity.setNewsImage(ImageOperations.image2byte(destPath+myFileFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        newsEntity.setNewsId(dateFormat.format(date));
        newsEntity.setNewsOwner(currUserEntity.getUserId());
        newsEntity.setNewsUpdateTime(timeStamp);
        newsService.add(newsEntity);
        return "addSuccess";
    }

    /**
     * 管理员权限，分页显示新闻列表
     */
    private int currPage = 1;
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
    public String findAll(){
        PageBean<TbNewsEntity> pageBean = newsService.findByPage(currPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAllSuccess";
    }

    private String toJsp;
    public void setToJsp(String toJsp) {
        this.toJsp = toJsp;
    }
    public String toPage(){
        return "toJsp_"+toJsp;
    }

    private String currNewsId;
    public void setCurrNewsId(String currNewsId){
        this.currNewsId = currNewsId;
    }
    public String toUpdatePage(){
        TbNewsEntity existNewsEntity = newsService.findById(currNewsId);
        if(ActionContext.getContext().getSession().containsKey("newsEntity")){
            ActionContext.getContext().getSession().remove("newsEntity");
        }
        ActionContext.getContext().getSession().put("newsEntity", existNewsEntity);

        return "toUpdatePageSuccess";
    }

    public String update(){
        TbUserEntity currUserEntity = (TbUserEntity) ActionContext.getContext().getSession().get("userEntity");
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        TbNewsEntity currNewsEntity = (TbNewsEntity)ActionContext.getContext().getSession().get("newsEntity");
        TbNewsEntity existNewsEntity = newsService.findById(currNewsEntity.getNewsId());
        if (myFile != null&&existNewsEntity != null) {
            try {
                File destFile = new File(destPath, myFileFileName);
                FileUtils.copyFile(myFile, destFile);
                existNewsEntity.setNewsImage(ImageOperations.image2byte(destPath+myFileFileName));
            } catch (IOException e) {
                System.out.println("NewsAction update() 文件上传出错！");
                e.printStackTrace();
            }
        }
        existNewsEntity.setNewsUpdateTime(timeStamp);
        existNewsEntity.setNewsHeader(newsEntity.getNewsHeader());
        existNewsEntity.setNewsBody(newsEntity.getNewsBody());
        existNewsEntity.setNewsEndTime(newsEntity.getNewsEndTime());
        existNewsEntity.setNewsOwner(currUserEntity.getUserId());
        newsService.update(existNewsEntity);
        return "updateSuccess";
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


    public String toNewsList(){
        PageBean<TbNewsEntity> pageBean = newsService.findByPage(currPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "toNewsListSuccess";
    }

    public String initJsp(){
        List<TbNewsEntity> list = newsService.findTopFiveList();
        if(ActionContext.getContext().getSession().containsKey("newsTopFiveList")){
            ActionContext.getContext().getSession().remove("newsTopFiveList");
        }
        ActionContext.getContext().getSession().put("newsTopFiveList",list);
        return "initJspSuccess";
    }
}