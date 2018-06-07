package com.action;

import com.bean.Addressee;
import com.bean.GoodsList;
import com.bean.JsonOption;
import com.bean.PageBean;
import com.entity.TbCartEntity;
import com.entity.TbOrderEntity;
import com.entity.TbUserEntity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.OrderService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderAction extends ActionSupport implements ModelDriven<TbOrderEntity>{
    private TbOrderEntity orderEntity = new TbOrderEntity();

    @Override
    public TbOrderEntity getModel() {
        return orderEntity;
    }

    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    private String payType;
    public void setPayType(String payType){
        this.payType = payType;
    }
    private String sendOwner;
    public void setSendOwner(String sendOwner){
        this.sendOwner = sendOwner;
    }
    private String sendPhone;
    public void setSendPhone(String sendPhone){
        this.sendPhone = sendPhone;
    }
    private String address;
    public void setAddress(String address){
        this.address = address;
    }
    public String add(){
        GoodsList goodsList = (GoodsList)ActionContext.getContext().getSession().get("goodsList");
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");

        List<TbCartEntity> list = goodsList.getList();
        String oldJsonString = orderEntity.getOrderInfo();

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSSS00");
        orderEntity.setOrderId(dateFormat.format(date));
        orderEntity.setTradeId(orderEntity.getOrderId());

        String newJsonString;
        for (TbCartEntity cartEntity : list) {
            newJsonString = JsonOption.carts2Order(cartEntity, oldJsonString);
            oldJsonString = newJsonString;
        }

        orderEntity.setOrderInfo(oldJsonString);
        orderEntity.setOrderPrice((int) goodsList.getOrderPrice());
        if(payType=="other"){
            orderEntity.setTradeId("货到付款");
        }
        orderEntity.setOrderState("待发货");
        orderEntity.setUserId(currUserEntity.getUserId());
        orderEntity.setSendAddress(JsonOption.makeUpSeedAddress(sendOwner, sendPhone, address));

        orderService.add(orderEntity);
        currPage = 1;
        return "addSuccess";
    }

    public String update(){
        orderService.update(orderEntity);
        return "updateSuccess";
    }

    private int currPage = 1;
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
    public String findAll(){
        PageBean<TbOrderEntity> pageBean = orderService.findByPage(currPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAllSuccess";
    }

    public String findByUserId(){
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");
        PageBean<TbOrderEntity> pageBean = orderService.findByUserId(currUserEntity.getUserId(), currPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findByUserIdSuccess";
    }

    public String addFromGoodsDetail(){
        TbCartEntity currCartEntity = (TbCartEntity)ActionContext.getContext().getSession().get("cartEntity");
        GoodsList goodsList = new GoodsList();
        List<TbCartEntity> list = new ArrayList<TbCartEntity>();
        list.add(currCartEntity);
        goodsList.setList(list);
        goodsList.setOrderPrice((double) currCartEntity.getGoodPrice() );
        if(ActionContext.getContext().getSession().containsKey("goodsList")){
            ActionContext.getContext().getSession().remove("goodsList");
        }
        ActionContext.getContext().getSession().put("goodsList", goodsList);

        currPage = 1;
        return "addFromGoodsDetailSuccess";
    }

    public String toCheckOrder(){
        GoodsList list = (GoodsList)ActionContext.getContext().getSession().get("goodsList");
        List<TbCartEntity> cartEntityList = new ArrayList<TbCartEntity>();

        for (TbCartEntity currCart :
                list.getList()) {
            if (currCart.getGoodCheck().equals("T")){
                cartEntityList.add(currCart);
            }
        }
        list.setList(cartEntityList);
        if(ActionContext.getContext().getSession().containsKey("goodsList")) {
            ActionContext.getContext().getSession().remove("goodsList");
        }
        ActionContext.getContext().getSession().put("goodsList", list);
        return "toCheckOrderSuccess";
    }

    private String currOrderId;
    public void setCurrOrderId(String currOrderId){
        this.currOrderId = currOrderId;
    }
    public String toUpdatePage(){
        TbOrderEntity existOrderEntity = orderService.findById(currOrderId);

        //解析出订单中的商品列表
        /**列表包含以下6中元素
         * goodId 商品编号
         * goodName 商品名称
         * goodSet 商品规格
         * goodPrice 商品价格
         * goodCheck 选中状态
         * goodCount 商品数量
         */
        List<TbCartEntity> list = JsonOption.order2Cart(existOrderEntity.getOrderInfo());
        GoodsList goodsList = new GoodsList();
        goodsList.setList(list);
        goodsList.setOrderPrice(existOrderEntity.getOrderPrice());
        if(ActionContext.getContext().getSession().containsKey("goodsList")){
            ActionContext.getContext().getSession().remove("goodsList");
        }
        ActionContext.getContext().getSession().put("goodsList", goodsList);

        //解析出订单收件人信息
        Addressee addressee = new Addressee();
        addressee.setAddress(JsonOption.getStringFromSendAddress(existOrderEntity.getSendAddress(),"address"));
        addressee.setPhone(JsonOption.getStringFromSendAddress(existOrderEntity.getSendAddress(),"phone"));
        addressee.setOwner(JsonOption.getStringFromSendAddress(existOrderEntity.getSendAddress(),"owner"));
        if(ActionContext.getContext().getSession().containsKey("addressee")){
            ActionContext.getContext().getSession().remove("addressee");
        }
        ActionContext.getContext().getSession().put("addressee", addressee);

        if(ActionContext.getContext().getSession().containsKey("orderEntity")){
            ActionContext.getContext().getSession().remove("orderEntity");
        }
        ActionContext.getContext().getSession().put("orderEntity", existOrderEntity);
        return "toUpdatePageSuccess";
    }

    //发货
    public String sendOrder(){
        TbOrderEntity currOrderEntity = (TbOrderEntity)ActionContext.getContext().getSession().get("orderEntity");
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");
        if(currOrderEntity.getOrderState().equals("待发货")){
            currOrderEntity.setOrderState("待收货");
            currOrderEntity.setSendAdmin(currUserEntity.getUserId());
            currOrderEntity.setSendState("待收货");
            orderService.update(currOrderEntity);
        }
        return "sendOrderSuccess";
    }

    //收货
    public String receiveOrder(){
        TbOrderEntity currOrderEntity = (TbOrderEntity)ActionContext.getContext().getSession().get("orderEntity");
        if(currOrderEntity.getOrderState().equals("待收货")){
            currOrderEntity.setSendState("待评价");
            currOrderEntity.setOrderState("待评价");
            orderService.update(currOrderEntity);
        }
        return "receiveOrderSuccess";
    }

    //待支付订单
    public String findByUserAndNoPay(){
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");
        if(currUserEntity.getUserType().equals("A")){
            PageBean<TbOrderEntity> pageBean = orderService.findNoPay(currPage);
            ActionContext.getContext().getValueStack().push(pageBean);
        }else {
            PageBean<TbOrderEntity> pageBean = orderService.findByUserAndNoPay(currUserEntity.getUserId(), currPage);
            ActionContext.getContext().getValueStack().push(pageBean);
        }
        return "findByUserAndNoPaySuccess";
    }

    public String findByUserAndNoSend(){
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");
        if(currUserEntity.getUserType().equals("A")){
            PageBean<TbOrderEntity> pageBean = orderService.findNoSend(currPage);
            ActionContext.getContext().getValueStack().push(pageBean);
        }else {
            PageBean<TbOrderEntity> pageBean = orderService.findByUserAndNoSend(currUserEntity.getUserId(), currPage);
            ActionContext.getContext().getValueStack().push(pageBean);
        }
        return "findByUserAndNoSendSuccess";
    }

    public String findAllByTypeAndPage(){
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");
        if(currUserEntity.getUserType().equals("A")){
            return findAll();
        }else{
            return findByUserId();
        }
    }

    public String updateCommentStatus(){
        TbOrderEntity currOrderEntity = (TbOrderEntity)ActionContext.getContext().getSession().get("orderEntity");
        if(currOrderEntity!=null){
            currOrderEntity.setOrderState("已评论");
            orderService.update(currOrderEntity);
            ActionContext.getContext().getSession().remove("orderEntity");
            ActionContext.getContext().getSession().put("orderEntity", currOrderEntity);
        }
        return "updateCommentStatusSuccess";

    }
}