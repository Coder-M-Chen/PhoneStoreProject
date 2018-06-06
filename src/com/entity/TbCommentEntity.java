package com.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "tb_comment", schema = "phonestore", catalog = "")
public class TbCommentEntity {
    private String commentId;
    private String orderId;
    private String userId;
    private String goodsId;
    private String commentInfo;
    private int commentStar;
    private String commentMore;
    private String more;

    public TbCommentEntity() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        commentId = dateFormat.format(date);
        orderId = "default";
        userId = "default";
        goodsId = "default";
        commentInfo = "default";
        commentStar = 5;
        commentMore = "default";
        more = "default";
    }

    @Id
    @Column(name = "CommentID", nullable = false, length = 20)
    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    @Basic
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
    @Column(name = "GoodsID", nullable = false, length = 14)
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "CommentInfo", nullable = false, length = 200)
    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    @Basic
    @Column(name = "CommentStar", nullable = false)
    public int getCommentStar() {
        return commentStar;
    }

    public void setCommentStar(int commentStar) {
        this.commentStar = commentStar;
    }

    @Basic
    @Column(name = "CommentMore", nullable = true, length = 200)
    public String getCommentMore() {
        return commentMore;
    }

    public void setCommentMore(String commentMore) {
        this.commentMore = commentMore;
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

        TbCommentEntity that = (TbCommentEntity) o;

        if (commentStar != that.commentStar) return false;
        if (commentId != null ? !commentId.equals(that.commentId) : that.commentId != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (goodsId != null ? !goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (commentInfo != null ? !commentInfo.equals(that.commentInfo) : that.commentInfo != null) return false;
        if (commentMore != null ? !commentMore.equals(that.commentMore) : that.commentMore != null) return false;
        if (more != null ? !more.equals(that.more) : that.more != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId != null ? commentId.hashCode() : 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (goodsId != null ? goodsId.hashCode() : 0);
        result = 31 * result + (commentInfo != null ? commentInfo.hashCode() : 0);
        result = 31 * result + commentStar;
        result = 31 * result + (commentMore != null ? commentMore.hashCode() : 0);
        result = 31 * result + (more != null ? more.hashCode() : 0);
        return result;
    }
}
