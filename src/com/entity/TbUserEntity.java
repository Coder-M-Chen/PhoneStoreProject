package com.entity;

import com.bean.ImageOperations;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "tb_user", schema = "phonestore", catalog = "")
public class TbUserEntity {
    private String userId;
    private String userType;
    private String certification;
    private String password;
    private String vName;
    private String rName;
    private String idCard;
    private String phone;
    private String email;
    private byte[] image;
    private String address;
    private Timestamp regTime;

    public TbUserEntity() {
        userId="default";
        userType="U";
        certification="default";
        password = "default";
        vName ="default";
        rName = "default";
        idCard = "default";
        phone = "default";
        email = "default";
        image = ImageOperations.image2byte("F:\\IDEA\\PhoneStore\\web\\images\\visit.png");
        address = "default";
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        regTime = timeStamp;
    }

    @Id
    @Column(name = "UserID", nullable = false, length = 11)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "UserType", nullable = false, length = 1)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "Certification", nullable = true, length = 20)
    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    @Basic
    @Column(name = "Password", nullable = false, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "VName", nullable = false, length = 30)
    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    @Basic
    @Column(name = "RName", nullable = false, length = 30)
    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    @Basic
    @Column(name = "IDCard", nullable = false, length = 18)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "Phone", nullable = false, length = 11)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "Email", nullable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Image", nullable = true)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Basic
    @Column(name = "Address", nullable = false, length = 150)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "RegTime", nullable = false)
    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbUserEntity that = (TbUserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userType != null ? !userType.equals(that.userType) : that.userType != null) return false;
        if (certification != null ? !certification.equals(that.certification) : that.certification != null)
            return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (vName != null ? !vName.equals(that.vName) : that.vName != null) return false;
        if (rName != null ? !rName.equals(that.rName) : that.rName != null) return false;
        if (idCard != null ? !idCard.equals(that.idCard) : that.idCard != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (!Arrays.equals(image, that.image)) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (regTime != null ? !regTime.equals(that.regTime) : that.regTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (certification != null ? certification.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (vName != null ? vName.hashCode() : 0);
        result = 31 * result + (rName != null ? rName.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (regTime != null ? regTime.hashCode() : 0);
        return result;
    }
}