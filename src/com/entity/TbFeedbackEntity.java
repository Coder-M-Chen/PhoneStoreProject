package com.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "tb_feedback", schema = "phonestore", catalog = "")
public class TbFeedbackEntity {
    private String feedbackId;
    private String feedbackOwner;
    private String feedbackBody;
    private String feedbackType;
    private String feedbackMore;
    private Timestamp feedbackTime;
    private String feedbackState;
    private String feedbackExecutor;

    public TbFeedbackEntity() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        feedbackId = dateFormat.format(date);
        feedbackOwner = "default";
        feedbackBody ="default";
        feedbackType = "default";
        feedbackExecutor = "default";
        feedbackMore = "default";
        Timestamp timestamp = new Timestamp(date.getTime());
        feedbackTime = timestamp;
        feedbackState = "default";
    }

    @Id
    @Column(name = "FeedbackID", nullable = false, length = 14)
    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Basic
    @Column(name = "FeedbackOwner", nullable = false, length = 11)
    public String getFeedbackOwner() {
        return feedbackOwner;
    }

    public void setFeedbackOwner(String feedbackOwner) {
        this.feedbackOwner = feedbackOwner;
    }

    @Basic
    @Column(name = "FeedbackBody", nullable = false, length = 500)
    public String getFeedbackBody() {
        return feedbackBody;
    }

    public void setFeedbackBody(String feedbackBody) {
        this.feedbackBody = feedbackBody;
    }

    @Basic
    @Column(name = "FeedbackType", nullable = false, length = 20)
    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    @Basic
    @Column(name = "FeedbackMore", nullable = true, length = 500)
    public String getFeedbackMore() {
        return feedbackMore;
    }

    public void setFeedbackMore(String feedbackMore) {
        this.feedbackMore = feedbackMore;
    }

    @Basic
    @Column(name = "FeedbackTime", nullable = false)
    public Timestamp getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Timestamp feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    @Basic
    @Column(name = "FeedbackState", nullable = false, length = 10)
    public String getFeedbackState() {
        return feedbackState;
    }

    public void setFeedbackState(String feedbackState) {
        this.feedbackState = feedbackState;
    }

    @Basic
    @Column(name = "FeedbackExecutor", nullable = true, length = 11)
    public String getFeedbackExecutor() {
        return feedbackExecutor;
    }

    public void setFeedbackExecutor(String feedbackExecutor) {
        this.feedbackExecutor = feedbackExecutor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbFeedbackEntity that = (TbFeedbackEntity) o;

        if (feedbackId != null ? !feedbackId.equals(that.feedbackId) : that.feedbackId != null) return false;
        if (feedbackOwner != null ? !feedbackOwner.equals(that.feedbackOwner) : that.feedbackOwner != null)
            return false;
        if (feedbackBody != null ? !feedbackBody.equals(that.feedbackBody) : that.feedbackBody != null) return false;
        if (feedbackType != null ? !feedbackType.equals(that.feedbackType) : that.feedbackType != null) return false;
        if (feedbackMore != null ? !feedbackMore.equals(that.feedbackMore) : that.feedbackMore != null) return false;
        if (feedbackTime != null ? !feedbackTime.equals(that.feedbackTime) : that.feedbackTime != null) return false;
        if (feedbackState != null ? !feedbackState.equals(that.feedbackState) : that.feedbackState != null)
            return false;
        if (feedbackExecutor != null ? !feedbackExecutor.equals(that.feedbackExecutor) : that.feedbackExecutor != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = feedbackId != null ? feedbackId.hashCode() : 0;
        result = 31 * result + (feedbackOwner != null ? feedbackOwner.hashCode() : 0);
        result = 31 * result + (feedbackBody != null ? feedbackBody.hashCode() : 0);
        result = 31 * result + (feedbackType != null ? feedbackType.hashCode() : 0);
        result = 31 * result + (feedbackMore != null ? feedbackMore.hashCode() : 0);
        result = 31 * result + (feedbackTime != null ? feedbackTime.hashCode() : 0);
        result = 31 * result + (feedbackState != null ? feedbackState.hashCode() : 0);
        result = 31 * result + (feedbackExecutor != null ? feedbackExecutor.hashCode() : 0);
        return result;
    }
}
