package com.example.RegisterLogin.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class AdsDTO {

    private int id;
    private String companyname;
    private String description;
    
    @JsonProperty("isRemote")
    private boolean remote;  // Rename field from isRemote to remote

    @JsonProperty("isFulltime")
    private boolean fulltime;
    private Date postedDate;
    @JsonProperty("status")
    private boolean status;



    public AdsDTO() {
    }


    public AdsDTO(String companyname, String description,boolean remote, boolean fulltime,boolean status, Date postedDate) {
        this.companyname = companyname;
        this.description = description;
        this.remote = remote;
        this.fulltime = fulltime;
        this.postedDate = postedDate;
        this.status = status;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public boolean isFulltime() {
        return fulltime;
    }

    public void setFulltime(boolean fulltime) {
        this.fulltime = fulltime;
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
        return "AdsDTO{" +
                "id=" + id +
                ", companyname='" + companyname + '\'' +
                ", description='" + description + '\'' +
                ", remote=" + remote +
                ", fulltime=" + fulltime +
                ", postedDate=" + postedDate +
                ", status=" + status +
                '}';
    }
}
