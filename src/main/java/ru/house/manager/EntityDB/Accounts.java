package ru.house.manager.EntityDB;

import org.springframework.http.converter.json.GsonBuilderUtils;

public class Accounts {

    private Integer id;
    private String eMail;
    private String hashPassword;
    private String salt;
    private Integer residentFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getResidentFlag() {
        return residentFlag;
    }

    public void setResidentFlag(Integer residentFlag) {
        this.residentFlag = residentFlag;
    }

    public Accounts() {
    }

}
