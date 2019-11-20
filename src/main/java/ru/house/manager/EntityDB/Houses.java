package ru.house.manager.EntityDB;

public class Houses {

    public Houses() {
    }
    private Integer houseId;
    private Integer manageCompanyId;
    private String adress;
    private String city;
    private Integer residentsNumber;
    private Integer accessToken;
    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getManageCompanyId() {
        return manageCompanyId;
    }

    public void setManageCompanyId(Integer manageCompanyId) {
        this.manageCompanyId = manageCompanyId;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getResidentsNumber() {
        return residentsNumber;
    }

    public void setResidentsNumber(Integer residentsNumber) {
        this.residentsNumber = residentsNumber;
    }

    public Integer getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(int accessToken) {
        this.accessToken = accessToken;
    }
}
