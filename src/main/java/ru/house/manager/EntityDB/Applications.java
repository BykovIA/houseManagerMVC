package ru.house.manager.EntityDB;

public class Applications {
    public Applications() {

    }
    private Integer manageId;
    private Integer userId;
    private String data;
    private String text;
    private String status;
    private String imageName;
    private Integer applicationsId;

    public static final String STATUS_OPEN = "OPEN";
    public static final String STATUS_CLOSE = "CLOSE";

    public Integer getApplicationsId() {
        return applicationsId;
    }

    public void setApplicationsId(Integer applicationsId) {
        this.applicationsId = applicationsId;
    }

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
        this.manageId = manageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
