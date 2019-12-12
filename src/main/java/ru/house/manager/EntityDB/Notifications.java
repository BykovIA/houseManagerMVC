package ru.house.manager.EntityDB;

public class Notifications {

    private int notificationId;
    private int houseId;
    private String date;
    private String text;
    private String status;
    private int managerId;
    private int userVisibility;

    public int getUserVisibility() {
        return userVisibility;
    }

    public void setUserVisibility(int userVisibility) {
        this.userVisibility = userVisibility;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
