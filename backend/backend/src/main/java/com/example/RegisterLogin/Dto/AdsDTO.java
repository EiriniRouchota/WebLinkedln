package com.example.RegisterLogin.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class AdsDTO {

    private int id;
    private String companyname;
    private String description;
    @JsonProperty("isRemote")
    private boolean isRemote;
    @JsonProperty("isFulltime")
    private boolean isFulltime;
    private Date postedDate;
    @JsonProperty("status")
    private boolean status;



    public AdsDTO() {
    }


    public AdsDTO(String companyname, String description,boolean isRemote, boolean isFulltime,boolean status, Date postedDate) {
        this.companyname = companyname;
        this.description = description;
        this.isRemote = isRemote;
        this.isFulltime = isFulltime;
        this.postedDate = postedDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRemote() {
        return isRemote;
    }

    public void setRemote(boolean remote) {
        isRemote = remote;
    }

    public boolean isFulltime() {
        return isFulltime;
    }

    public void setFulltime(boolean fulltime) {
        isFulltime = fulltime;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ads{" +
                "id=" + id +
                ", companyname='" + companyname + '\'' +
                ", description='" + description + '\'' +
                ", isRemote=" + isRemote +
                ", isFulltime=" + isFulltime +
                ", postedDate=" + postedDate +
                ", status=" + status +
                '}';
    }
}
