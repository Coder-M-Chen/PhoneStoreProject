package com.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "tb_cart", schema = "phonestore", catalog = "")
public class TbCartEntity {
    private String cartId;
    private String userId;
    private String goodId;
    private String goodSet;
    private int goodCount;
    private String goodName;
    private int goodPrice;
    private String goodCheck;

    public TbCartEntity() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        cartId = dateFormat.format(date);
        userId = "default";
        goodId = "default";
        goodSet = "default";
        goodCount = 0;
        goodName = "default";
        goodPrice = 0;
        goodCheck = "T";
    }

    @Id
    @Column(name = "CartId", nullable = false, length = 25)
    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    @Basic
    @Column(name = "UserId", nullable = false, length = 11)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "GoodId", nullable = false, length = 14)
    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    @Basic
    @Column(name = "GoodSet", nullable = true, length = 200)
    public String getGoodSet() {
        return goodSet;
    }

    public void setGoodSet(String goodSet) {
        this.goodSet = goodSet;
    }

    @Basic
    @Column(name = "GoodCount", nullable = false)
    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    @Basic
    @Column(name = "GoodName", nullable = false, length = 30)
    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    @Basic
    @Column(name = "GoodPrice", nullable = false)
    public int getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(int goodPrice) {
        this.goodPrice = goodPrice;
    }

    @Basic
    @Column(name = "GoodCheck", nullable = false, length = 1)
    public String getGoodCheck() {
        return goodCheck;
    }

    public void setGoodCheck(String goodCheck) {
        this.goodCheck = goodCheck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbCartEntity that = (TbCartEntity) o;

        if (goodCount != that.goodCount) return false;
        if (goodPrice != that.goodPrice) return false;
        if (cartId != null ? !cartId.equals(that.cartId) : that.cartId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (goodId != null ? !goodId.equals(that.goodId) : that.goodId != null) return false;
        if (goodSet != null ? !goodSet.equals(that.goodSet) : that.goodSet != null) return false;
        if (goodName != null ? !goodName.equals(that.goodName) : that.goodName != null) return false;
        if (goodCheck != null ? !goodCheck.equals(that.goodCheck) : that.goodCheck != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartId != null ? cartId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (goodId != null ? goodId.hashCode() : 0);
        result = 31 * result + (goodSet != null ? goodSet.hashCode() : 0);
        result = 31 * result + goodCount;
        result = 31 * result + (goodName != null ? goodName.hashCode() : 0);
        result = 31 * result + goodPrice;
        result = 31 * result + (goodCheck != null ? goodCheck.hashCode() : 0);
        return result;
    }
}
