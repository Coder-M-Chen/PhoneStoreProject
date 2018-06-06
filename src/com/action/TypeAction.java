package com.action;

import com.bean.PageBean;
import com.entity.TbTypeEntity;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.TypeService;

import java.util.List;

public class TypeAction extends ActionSupport implements ModelDriven<TbTypeEntity> {

    private TypeService typeService;
    private TbTypeEntity typeEntity = new TbTypeEntity();
    private Integer currPage = 1;   //表示当前的页码
    private String toJsp;   //需要跳转的jsp文件名

    public String initJsp(){
        List<TbTypeEntity> list = typeService.findAllForList();
        if(list.size()>0){
            ActionContext.getContext().getSession().remove("typeList");
            ActionContext.getContext().getSession().put("typeList",list);
        }
        return "initJspSuccess";
    }

    @Override
    public TbTypeEntity getModel() {
        return typeEntity;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    /**
     * 分页查询所有的商品类型
     * @return 查询完成的标志
     */
    public String findAll(){
        PageBean<TbTypeEntity> pageBean = typeService.findByPage(currPage);
        //将pageBean存入到值栈中
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAllSuccess";
    }

    /**
     * 添加商品类型typeEntity
     * @return 添加完成的标志
     */
    public String add(){
        typeService.addItem(typeEntity);
        List<TbTypeEntity> list = typeService.findAllForList();
        if(list.size()>0){
            ActionContext.getContext().getSession().remove("typeList");
            ActionContext.getContext().getSession().put("typeList",list);
        }
        return "addItemSuccess";
    }

    /**
     * 注入要跳转的jsp文件名
     * @param toJsp jsp文件名
     */
    public void setToJsp(String toJsp) {
        this.toJsp = toJsp;
    }

    /**
     * 页面跳转
     * @return 页面跳转动作的结果
     */
    public String toAddPage(){
        List<TbTypeEntity> list = typeService.findAllForList();
        int typeCount = list.size();
        ActionContext.getContext().getSession().remove("typeCount");
        ActionContext.getContext().getSession().put("typeCount",typeCount);
        return "toJsp_typeAdd";
    }

    private String currTypeId;
    public void setCurrTypeId(String currTypeId){
        this.currTypeId = currTypeId;
    }
    public String toUpdatePage(){
        TbTypeEntity existTypeEntity = typeService.findById(currTypeId);
        if(ActionContext.getContext().getSession().containsKey("typeEntity")){
            ActionContext.getContext().getSession().remove("typeEntity");
        }
        ActionContext.getContext().getSession().put("typeEntity", existTypeEntity);
        return "toUpdatePageSuccess";
    }

    public String update(){
        currPage=1;
        if(typeEntity.getTypeName()!=null){
            typeService.update(typeEntity);

            List<TbTypeEntity> list = typeService.findAllForList();
            if(list.size()>0){
                ActionContext.getContext().getSession().remove("typeList");
                ActionContext.getContext().getSession().put("typeList",list);
            }
        }
        return "updateSuccess";
    }
}