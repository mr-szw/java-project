package com.dawei.boot.boothelloword.pojo;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author by Dawei on  2018/7/19
 */
public class DemoPojo implements Serializable {

    private Long uId;
    private String userName;
    private Date birthday;
    private String phoneNum;
    private String passWord;
    private String token;
    private String saltNUm;
    private Set<String> roleSet;
    private Set<String> authSet;

    public DemoPojo() {
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSaltNUm() {
        return saltNUm;
    }

    public void setSaltNUm(String saltNUm) {
        this.saltNUm = saltNUm;
    }

    public Set<String> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<String> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<String> getAuthSet() {
        return authSet;
    }

    public void setAuthSet(Set<String> authSet) {
        this.authSet = authSet;
    }


    @Override
    public String toString() {
        return "DemoPojo{" +
                "uId=" + uId +
                ", userName='" + userName + '\'' +
                ", birthday=" + birthday +
                ", phoneNum='" + phoneNum + '\'' +
                ", passWord='" + passWord + '\'' +
                ", token='" + token + '\'' +
                ", saltNUm='" + saltNUm + '\'' +
                ", roleSet=" + roleSet +
                ", authSet=" + authSet +
                '}';
    }
}
