package com.bean;

import com.entity.TbCartEntity;

import java.util.List;

public class GoodsList {
    private List<TbCartEntity> list;  //存放订单中的商品列表(购物车详情数据)
    private double orderPrice;  //订单所有商品的总价值

    public List<TbCartEntity> getList() {
        return list;
    }

    public void setList(List<TbCartEntity> list) {
        this.list = list;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
