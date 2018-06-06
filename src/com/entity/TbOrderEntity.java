package com.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "tb_order", schema = "phonestore", catalog = "")
public class TbOrderEntity {
    private String orderId;
    private String userId;
    private String tradeId;
    private Timestamp tradeTime;
    private String orderInfo;
    private String orderState;
    private Integer orderPrice;
    private String sendType;
    private Integer sendPrice;
    private String sendAdmin;
    private String sendAddress;
    private String sendState;
    private String more;

    public TbOrderEntity() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSSS00");
        orderId = dateFormat.format(date);
        userId = "default";
        tradeId = orderId;
        tradeTime = new Timestamp(date.getTime());
        orderInfo = "default";
        orderState = "default";
        orderPrice = 0;
        sendType = "default";
        sendPrice = 0;
        sendAdmin = "default";
        sendAddress = "default";
        sendState = "default";
        more = "default";
    }

    @Id
    @Column(name = "OrderID", nullable = false, length = 20)
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "UserID", nullable = false, length = 11)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "TradeID", nullable = true, length = 30)
    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    @Basic
    @Column(name = "TradeTime", nullable = true)
    public Timestamp getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Timestamp tradeTime) {
        this.tradeTime = tradeTime;
    }

    @Basic
    @Column(name = "OrderInfo", nullable = false, length = 200)
    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Basic
    @Column(name = "OrderState", nullable = false, length = 20)
    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    @Basic
    @Column(name = "OrderPrice", nullable = true)
    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Basic
    @Column(name = "SendType", nullable = true, length = 20)
    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    @Basic
    @Column(name = "SendPrice", nullable = true)
    public Integer getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(Integer sendPrice) {
        this.sendPrice = sendPrice;
    }

    @Basic
    @Column(name = "SendAdmin", nullable = true, length = 11)
    public String getSendAdmin() {
        return sendAdmin;
    }

    public void setSendAdmin(String sendAdmin) {
        this.sendAdmin = sendAdmin;
    }

    @Basic
    @Column(name = "SendAddress", nullable = false, length = 120)
    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    @Basic
    @Column(name = "SendState", nullable = true, length = 100)
    public String getSendState() {
        return sendState;
    }

    public void setSendState(String sendState) {
        this.sendState = sendState;
    }

    @Basic
    @Column(name = "More", nullable = true, length = 100)
    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbOrderEntity that = (TbOrderEntity) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (tradeId != null ? !tradeId.equals(that.tradeId) : that.tradeId != null) return false;
        if (tradeTime != null ? !tradeTime.equals(that.tradeTime) : that.tradeTime != null) return false;
        if (orderInfo != null ? !orderInfo.equals(that.orderInfo) : that.orderInfo != null) return false;
        if (orderState != null ? !orderState.equals(that.orderState) : that.orderState != null) return false;
        if (orderPrice != null ? !orderPrice.equals(that.orderPrice) : that.orderPrice != null) return false;
        if (sendType != null ? !sendType.equals(that.sendType) : that.sendType != null) return false;
        if (sendPrice != null ? !sendPrice.equals(that.sendPrice) : that.sendPrice != null) return false;
        if (sendAdmin != null ? !sendAdmin.equals(that.sendAdmin) : that.sendAdmin != null) return false;
        if (sendAddress != null ? !sendAddress.equals(that.sendAddress) : that.sendAddress != null) return false;
        if (sendState != null ? !sendState.equals(that.sendState) : that.sendState != null) return false;
        if (more != null ? !more.equals(that.more) : that.more != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (tradeId != null ? tradeId.hashCode() : 0);
        result = 31 * result + (tradeTime != null ? tradeTime.hashCode() : 0);
        result = 31 * result + (orderInfo != null ? orderInfo.hashCode() : 0);
        result = 31 * result + (orderState != null ? orderState.hashCode() : 0);
        result = 31 * result + (orderPrice != null ? orderPrice.hashCode() : 0);
        result = 31 * result + (sendType != null ? sendType.hashCode() : 0);
        result = 31 * result + (sendPrice != null ? sendPrice.hashCode() : 0);
        result = 31 * result + (sendAdmin != null ? sendAdmin.hashCode() : 0);
        result = 31 * result + (sendAddress != null ? sendAddress.hashCode() : 0);
        result = 31 * result + (sendState != null ? sendState.hashCode() : 0);
        result = 31 * result + (more != null ? more.hashCode() : 0);
        return result;
    }
}
