package com.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "tb_news", schema = "phonestore", catalog = "")
public class TbNewsEntity {
    private String newsId;
    private String newsHeader;
    private String newsBody;
    private byte[] newsImage;
    private Timestamp newsUpdateTime;
    private Timestamp newsEndTime;
    private String newsOwner;

    public TbNewsEntity() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        newsId = dateFormat.format(date);
        newsHeader = "default";
        newsBody = "default";
        newsImage = "default".getBytes();
        newsUpdateTime = new Timestamp(date.getTime());
        newsEndTime = new Timestamp(date.getTime());
        newsOwner = "default";
    }

    @Id
    @Column(name = "NewsID", nullable = false, length = 14)
    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    @Basic
    @Column(name = "NewsHeader", nullable = false, length = 50)
    public String getNewsHeader() {
        return newsHeader;
    }

    public void setNewsHeader(String newsHeader) {
        this.newsHeader = newsHeader;
    }

    @Basic
    @Column(name = "NewsBody", nullable = false, length = 500)
    public String getNewsBody() {
        return newsBody;
    }

    public void setNewsBody(String newsBody) {
        this.newsBody = newsBody;
    }

    @Basic
    @Column(name = "NewsImage", nullable = true)
    public byte[] getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(byte[] newsImage) {
        this.newsImage = newsImage;
    }

    @Basic
    @Column(name = "NewsUpdateTime", nullable = false)
    public Timestamp getNewsUpdateTime() {
        return newsUpdateTime;
    }

    public void setNewsUpdateTime(Timestamp newsUpdateTime) {
        this.newsUpdateTime = newsUpdateTime;
    }

    @Basic
    @Column(name = "NewsEndTime", nullable = false)
    public Timestamp getNewsEndTime() {
        return newsEndTime;
    }

    public void setNewsEndTime(Timestamp newsEndTime) {
        this.newsEndTime = newsEndTime;
    }

    @Basic
    @Column(name = "NewsOwner", nullable = false, length = 11)
    public String getNewsOwner() {
        return newsOwner;
    }

    public void setNewsOwner(String newsOwner) {
        this.newsOwner = newsOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbNewsEntity that = (TbNewsEntity) o;

        if (newsId != null ? !newsId.equals(that.newsId) : that.newsId != null) return false;
        if (newsHeader != null ? !newsHeader.equals(that.newsHeader) : that.newsHeader != null) return false;
        if (newsBody != null ? !newsBody.equals(that.newsBody) : that.newsBody != null) return false;
        if (!Arrays.equals(newsImage, that.newsImage)) return false;
        if (newsUpdateTime != null ? !newsUpdateTime.equals(that.newsUpdateTime) : that.newsUpdateTime != null)
            return false;
        if (newsEndTime != null ? !newsEndTime.equals(that.newsEndTime) : that.newsEndTime != null) return false;
        if (newsOwner != null ? !newsOwner.equals(that.newsOwner) : that.newsOwner != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = newsId != null ? newsId.hashCode() : 0;
        result = 31 * result + (newsHeader != null ? newsHeader.hashCode() : 0);
        result = 31 * result + (newsBody != null ? newsBody.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(newsImage);
        result = 31 * result + (newsUpdateTime != null ? newsUpdateTime.hashCode() : 0);
        result = 31 * result + (newsEndTime != null ? newsEndTime.hashCode() : 0);
        result = 31 * result + (newsOwner != null ? newsOwner.hashCode() : 0);
        return result;
    }
}
