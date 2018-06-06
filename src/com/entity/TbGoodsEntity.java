package com.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "tb_goods", schema = "phonestore", catalog = "")
public class TbGoodsEntity {
    private String goodsId;
    private String goodsName;
    private String goodsType;
    private String goodsInfo;
    private int goodsPrice;
    private int goodsDiscount;
    private int goodsAmount;
    private byte[] goodsImage;
    private String goodsState;
    private String upMaster;
    private String goodsSet;

    public TbGoodsEntity() {
        Date date = new Date();
        DateFormat dateFormat = new  SimpleDateFormat("yyyyMMddHHmmss");
        goodsId = dateFormat.format(date);
        goodsName = "default";
        goodsType = "default";
        goodsInfo = "default";
        goodsPrice = 0;
        goodsDiscount = 0;
        goodsAmount = 0;
        goodsImage = "default".getBytes();
        goodsState = "default";
        upMaster = "default";
        goodsSet = "default";
    }

    @Id
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
    @Column(name = "GoodsType", nullable = false, length = 20)
    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    @Basic
    @Column(name = "GoodsInfo", nullable = false, length = 500)
    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Basic
    @Column(name = "GoodsPrice", nullable = false)
    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    @Basic
    @Column(name = "GoodsDiscount", nullable = false)
    public int getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(int goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    @Basic
    @Column(name = "GoodsAmount", nullable = false)
    public int getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(int goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    @Basic
    @Column(name = "GoodsImage", nullable = false)
    public byte[] getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(byte[] goodsImage) {
        this.goodsImage = goodsImage;
    }

    @Basic
    @Column(name = "GoodsState", nullable = false, length = 20)
    public String getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(String goodsState) {
        this.goodsState = goodsState;
    }

    @Basic
    @Column(name = "UpMaster", nullable = false, length = 11)
    public String getUpMaster() {
        return upMaster;
    }

    public void setUpMaster(String upMaster) {
        this.upMaster = upMaster;
    }

    @Basic
    @Column(name = "GoodsSet", nullable = true, length = 200)
    public String getGoodsSet() {
        return goodsSet;
    }

    public void setGoodsSet(String goodsSet) {
        this.goodsSet = goodsSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbGoodsEntity that = (TbGoodsEntity) o;

        if (goodsPrice != that.goodsPrice) return false;
        if (goodsDiscount != that.goodsDiscount) return false;
        if (goodsAmount != that.goodsAmount) return false;
        if (goodsId != null ? !goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (goodsName != null ? !goodsName.equals(that.goodsName) : that.goodsName != null) return false;
        if (goodsType != null ? !goodsType.equals(that.goodsType) : that.goodsType != null) return false;
        if (goodsInfo != null ? !goodsInfo.equals(that.goodsInfo) : that.goodsInfo != null) return false;
        if (!Arrays.equals(goodsImage, that.goodsImage)) return false;
        if (goodsState != null ? !goodsState.equals(that.goodsState) : that.goodsState != null) return false;
        if (upMaster != null ? !upMaster.equals(that.upMaster) : that.upMaster != null) return false;
        if (goodsSet != null ? !goodsSet.equals(that.goodsSet) : that.goodsSet != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goodsId != null ? goodsId.hashCode() : 0;
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + (goodsType != null ? goodsType.hashCode() : 0);
        result = 31 * result + (goodsInfo != null ? goodsInfo.hashCode() : 0);
        result = 31 * result + goodsPrice;
        result = 31 * result + goodsDiscount;
        result = 31 * result + goodsAmount;
        result = 31 * result + Arrays.hashCode(goodsImage);
        result = 31 * result + (goodsState != null ? goodsState.hashCode() : 0);
        result = 31 * result + (upMaster != null ? upMaster.hashCode() : 0);
        result = 31 * result + (goodsSet != null ? goodsSet.hashCode() : 0);
        return result;
    }
}