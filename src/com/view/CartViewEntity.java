package com.view;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cart_view", schema = "phonestore", catalog = "")
public class CartViewEntity {
    private String goodsId;
    private String goodsName;
    private String userId;
    private String goodsType;
    private String typeName;
    private int goodCount;
    private int goodPrice;
    private String goodSet;

    @Basic
    @Column(name = "GoodsID", nullable = false, length = 14)
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "GoodsName", nullable = false, length = 30)
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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
    @Column(name = "GoodsType", nullable = false, length = 20)
    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    @Basic
    @Column(name = "TypeName", nullable = false, length = 50)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
    @Column(name = "GoodPrice", nullable = false)
    public int getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(int goodPrice) {
        this.goodPrice = goodPrice;
    }

    @Basic
    @Column(name = "GoodSet", nullable = true, length = 200)
    public String getGoodSet() {
        return goodSet;
    }

    public void setGoodSet(String goodSet) {
        this.goodSet = goodSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartViewEntity that = (CartViewEntity) o;

        if (goodCount != that.goodCount) return false;
        if (goodPrice != that.goodPrice) return false;
        if (goodsId != null ? !goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (goodsName != null ? !goodsName.equals(that.goodsName) : that.goodsName != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (goodsType != null ? !goodsType.equals(that.goodsType) : that.goodsType != null) return false;
        if (typeName != null ? !typeName.equals(that.typeName) : that.typeName != null) return false;
        if (goodSet != null ? !goodSet.equals(that.goodSet) : that.goodSet != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goodsId != null ? goodsId.hashCode() : 0;
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (goodsType != null ? goodsType.hashCode() : 0);
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        result = 31 * result + goodCount;
        result = 31 * result + goodPrice;
        result = 31 * result + (goodSet != null ? goodSet.hashCode() : 0);
        return result;
    }
}
