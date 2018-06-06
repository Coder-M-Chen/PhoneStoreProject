package com.action;

import com.bean.GoodsList;
import com.bean.JsonOption;
import com.bean.StatisticsBean;
import com.entity.TbCartEntity;
import com.entity.TbGoodsEntity;
import com.entity.TbUserEntity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.CartService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CartAction extends ActionSupport implements ModelDriven<TbCartEntity> {
    private TbCartEntity cartEntity = new TbCartEntity();

    @Override
    public TbCartEntity getModel() {
        return cartEntity;
    }

    private CartService cartService;

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public String add(){
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");
        TbGoodsEntity currGoodEntity = (TbGoodsEntity)ActionContext.getContext().getSession().get("goodsEntity");

        String currGoodId = currGoodEntity.getGoodsId();
        String currUserId = currUserEntity.getUserId();

        TbCartEntity existCartEntity = cartService.findByUserIdAndGoodId(currUserId, currGoodId);

        //另需判断商品套餐类型是否一致，未实现
        if(existCartEntity!=null){
            int goodsCount = existCartEntity.getGoodCount() + 1;
            existCartEntity.setGoodCount(goodsCount);
            cartService.update(existCartEntity);
        }else {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            cartEntity.setCartId(dateFormat.format(date)+currUserId);

            cartEntity.setGoodId(currGoodId);
            cartEntity.setUserId(currUserId);
            cartEntity.setGoodPrice(currGoodEntity.getGoodsPrice());
            cartEntity.setGoodCheck("T");
            cartEntity.setGoodName(currGoodEntity.getGoodsName());
//            cartEntity.setGoodSet();
            cartEntity.setGoodCount(1);
            cartService.add(cartEntity);
        }
        return "addSuccess";
    }

    private Integer currPage = 1;
    public void setCurrPage(Integer currPage){
        this.currPage = currPage;
    }
    public String findByUserId(){
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");

        if(currUserEntity!=null) {
            String userId = currUserEntity.getUserId();

            GoodsList goodsList = new GoodsList();
            List<TbCartEntity> list = cartService.findByUserId(userId);
            goodsList.setList(list);
            double cartPrice = 0;
            for (TbCartEntity currCart : list) {
                if (currCart.getGoodCheck().equals("T")) {
                    cartPrice = cartPrice + currCart.getGoodCount() * currCart.getGoodPrice();
                }
            }
            goodsList.setOrderPrice(cartPrice);
            if(ActionContext.getContext().getSession().containsKey("goodsList")){
                ActionContext.getContext().getSession().remove("goodsList");
            }
            ActionContext.getContext().getSession().put("goodsList",goodsList);
            return "findByUserIdSuccess";
        }
        return ERROR;
    }

    private String currCartId;
    public void setCurrCartId(String currCartId){
        this.currCartId = currCartId;
    }
    public String delete(){
        TbCartEntity existCartEntity = cartService.findById(currCartId);
        cartService.delete(existCartEntity);
        return "deleteSuccess";
    }

    /**
     * 直接在商品详情页点击购买
     * @return
     */
    public String addGoodAndPay(){
        TbGoodsEntity currGoodEntity = (TbGoodsEntity)ActionContext.getContext().getSession().get("goodsEntity");
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        cartEntity.setCartId(dateFormat.format(date)+currUserEntity.getUserId());
        cartEntity.setGoodSet("暗夜灰");
        cartEntity.setGoodName(currGoodEntity.getGoodsName());
        cartEntity.setGoodPrice(currGoodEntity.getGoodsPrice());
        cartEntity.setGoodCheck("T");
        cartEntity.setUserId(currUserEntity.getUserId());
        cartEntity.setGoodCount(1);
        cartEntity.setGoodId(currGoodEntity.getGoodsId());
        cartService.add(cartEntity);
        ActionContext.getContext().getSession().put("cartEntity", cartEntity);
        return "addGoodAndPaySuccess";
    }

    public String addGoodCount(){
        TbCartEntity currCartEntity = cartService.findById(currCartId);
        int tempCount = currCartEntity.getGoodCount();
        currCartEntity.setGoodCount(tempCount+1);
        cartService.update(currCartEntity);
        return "updateGoodCount";
    }

    public String subGoodCount(){
        TbCartEntity currCartEntity = cartService.findById(currCartId);
        int tempCount = currCartEntity.getGoodCount();
        currCartEntity.setGoodCount(tempCount-1);
        cartService.update(currCartEntity);
        return "updateGoodCount";
    }

    public String checkGood(){
        TbCartEntity currCartEntity = cartService.findById(currCartId);
        if(currCartEntity.getGoodCheck()=="F"){
            currCartEntity.setGoodCheck("T");
        }else {
            currCartEntity.setGoodCheck("F");
        }
        cartService.update(currCartEntity);
        return "checkGoodSuccess";
    }

    public String checkAllByUserId(){
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");
        List<TbCartEntity> list = cartService.findByUserId(currUserEntity.getUserId());
        for (TbCartEntity currCart :
                list) {
            currCart.setGoodCheck("T");
            cartService.update(currCart);
        }
        return "checkGoodSuccess";
    }

    public String clearCheckedByUserId(){
        TbUserEntity currUserEntity = (TbUserEntity)ActionContext.getContext().getSession().get("userEntity");
        List<TbCartEntity> list = cartService.findByUserId(currUserEntity.getUserId());
        for (TbCartEntity currCart :
                list) {
            currCart.setGoodCheck("X");
            cartService.update(currCart);
        }
        return "clearCheckedByUserIdSuccess";
    }

    public String toManagerView(){
        List<StatisticsBean> topList = cartService.findTop();
        String goodsNameArray = JsonOption.string2JsonArray(topList);
        if(ActionContext.getContext().getSession().containsKey("goodsNameArray")){
            ActionContext.getContext().getSession().remove("goodsNameArray");
        }
        ActionContext.getContext().getSession().put("goodsNameArray", goodsNameArray);
        String goodsCountArray = JsonOption.count2JsonArray(topList);
        if(ActionContext.getContext().getSession().containsKey("goodsCountArray")){
            ActionContext.getContext().getSession().remove("goodsCountArray");
        }
        ActionContext.getContext().getSession().put("goodsCountArray", goodsCountArray);

        String goodsPriceArray = JsonOption.price2JsonArray(topList);
        if(ActionContext.getContext().getSession().containsKey("goodsPriceArray")){
            ActionContext.getContext().getSession().remove("goodsPriceArray");
        }
        ActionContext.getContext().getSession().put("goodsPriceArray", goodsPriceArray);

        return "toManagerViewSuccess";
    }

    public String topList(){
        return this.toManagerView();
    }

    public String bottomList(){
        List<StatisticsBean> topList = cartService.findBottom();
        String goodsNameArray = JsonOption.string2JsonArray(topList);
        if(ActionContext.getContext().getSession().containsKey("goodsNameArray")){
            ActionContext.getContext().getSession().remove("goodsNameArray");
        }
        ActionContext.getContext().getSession().put("goodsNameArray", goodsNameArray);
        String goodsCountArray = JsonOption.count2JsonArray(topList);
        if(ActionContext.getContext().getSession().containsKey("goodsCountArray")){
            ActionContext.getContext().getSession().remove("goodsCountArray");
        }
        ActionContext.getContext().getSession().put("goodsCountArray", goodsCountArray);

        String goodsPriceArray = JsonOption.price2JsonArray(topList);
        if(ActionContext.getContext().getSession().containsKey("goodsPriceArray")){
            ActionContext.getContext().getSession().remove("goodsPriceArray");
        }
        ActionContext.getContext().getSession().put("goodsPriceArray", goodsPriceArray);
        return "bottomListSuccess";
    }

    private Integer orderKey = 1;
    public void setOrderKey(){
        this.orderKey = orderKey;
    }
    private Date startDate;
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    private Date endDate;
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
    public String statisticsByDate(){
        List<StatisticsBean> topList = cartService.findByDate(startDate, endDate, orderKey);
        String goodsNameArray = JsonOption.string2JsonArray(topList);
        if(ActionContext.getContext().getSession().containsKey("goodsNameArray")){
            ActionContext.getContext().getSession().remove("goodsNameArray");
        }
        ActionContext.getContext().getSession().put("goodsNameArray", goodsNameArray);
        String goodsCountArray = JsonOption.count2JsonArray(topList);
        if(ActionContext.getContext().getSession().containsKey("goodsCountArray")){
            ActionContext.getContext().getSession().remove("goodsCountArray");
        }
        ActionContext.getContext().getSession().put("goodsCountArray", goodsCountArray);

        String goodsPriceArray = JsonOption.price2JsonArray(topList);
        if(ActionContext.getContext().getSession().containsKey("goodsPriceArray")){
            ActionContext.getContext().getSession().remove("goodsPriceArray");
        }
        ActionContext.getContext().getSession().put("goodsPriceArray", goodsPriceArray);

        return "statisticsByDateSuccess";
    }
}
