package com.action;

import com.bean.ImageOperations;
import com.bean.MD5Encoding;
import com.bean.PageBean;
import com.entity.TbUserEntity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.UserService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserAction extends ActionSupport implements ModelDriven<TbUserEntity> {

    //模型驱动使用的实体类
    private TbUserEntity userEntity = new TbUserEntity();

    @Override
    public TbUserEntity getModel() {
        return userEntity;
    }

    //struts2和spring整合过程中按名称自动注入的业务层类
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     * @return
     */
    public String login() {
        TbUserEntity existUserEntity = userService.login(userEntity);
        if (existUserEntity==null){
            this.addActionError("账号或密码错误！");
            return "loginError";
        }else {
            if(ActionContext.getContext().getSession().containsKey("userEntity")) {
                ActionContext.getContext().getSession().remove("userEntity");
            }
            ActionContext.getContext().getSession().put("userEntity", existUserEntity); //将用户实体放入Session中
            if(existUserEntity.getUserType().equals("A")){
                return "loginAdminSuccess";
            }else{
                return "loginUserSuccess";
            }
        }
    }

    /**
     * 用户注册
     * @return
     */
    public String register(){
        if(userService.isNewByPhone(userEntity.getUserId())){
            userService.register(userEntity);
        }else {
            return "registerError";
        }
        TbUserEntity existUserEntity = userService.findById(userEntity.getUserId());
        if(existUserEntity!=null){
            if(ActionContext.getContext().getSession().containsKey("userEntity")){
                ActionContext.getContext().getSession().remove("userEntity");
            }
            ActionContext.getContext().getSession().put("userEntity",existUserEntity);
            return "registerSuccess";
        }
        return "registerError";
    }

    /**
     * 修改用户信息
     * @return
     */
    public String update() {
        TbUserEntity oldUserEntity = userService.findById(userEntity.getUserId());
        if (myFile!=null&&oldUserEntity!=null) {
            try {
                File destFile = new File(destPath, myFileFileName);
                FileUtils.copyFile(myFile, destFile);
                oldUserEntity.setImage(ImageOperations.image2byte(destPath+myFileFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(oldUserEntity!=null) {
            oldUserEntity.setvName(userEntity.getvName());
            oldUserEntity.setEmail(userEntity.getEmail());
            userService.update(oldUserEntity);
        }
        TbUserEntity existUserEntity = userService.findById(userEntity.getUserId());
        if(existUserEntity!=null){
            if(ActionContext.getContext().getSession().containsKey("userEntity")){
                ActionContext.getContext().getSession().remove("userEntity");
            }
            ActionContext.getContext().getSession().put("userEntity",existUserEntity);
            return "updateSuccess";
        }
        return "updateError";
    }

    private Integer key;
    public void setKey(Integer key) {
        this.key = key;
    }
    public String find(){
        //通过其他方式查找
        List<TbUserEntity> list = userService.find(userEntity, key);
        ActionContext.getContext().getValueStack().push(list);
        return "findSuccess";
    }

    public String findById(){
        return "findById";
    }

    public String logOff(){
        ActionContext.getContext().getSession().remove("userEntity");
        return "logOffSuccess";
    }

    private Integer currPage = 1;
    public void setCurrPage(Integer currPage){
        this.currPage = currPage;
    }
    public String all2Admin(){
        PageBean<TbUserEntity> pageBean = userService.findAll2Admin(currPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "all2AdminSuccess";
    }

    public String user2Admin(){
        TbUserEntity existUserEntity = (TbUserEntity) ActionContext.getContext().getSession().get("userEntity");
        if(existUserEntity!=null){
            existUserEntity.setCertification(userEntity.getCertification());
            userService.update(existUserEntity);
        }
        return "user2AdminSuccess";
    }

    public String toPageCheck(){
        return "toPageCheckSuccess";
    }

    public String toUpdatePwd(){
        return "toUpdatePwdSuccess";
    }

    public String updatePwd(){
        TbUserEntity existUserEntity = userService.findById(userEntity.getUserId());
        existUserEntity.setPassword(MD5Encoding.MD5(userEntity.getPassword()));
        userService.update(existUserEntity);
        return "updatePwdSuccess";
    }

    private String otherId;
    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }
    public String agreeUser2Admin(){
        TbUserEntity existUserEntity = userService.findById(otherId);
        if(existUserEntity!=null){
            existUserEntity.setUserType("A");
            userService.update(existUserEntity);
        }
        return "agreeUser2AdminSuccess";
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

}