package com.example.RegisterLogin.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;


public class ExperienceDTO {

    private int id;
    private String jobtitle;
    private String companyname;
    private String description;
    private Date startDate;
    private Date endDate;
    @JsonProperty("isPublic")
    private boolean isPublic;

    public ExperienceDTO() {
    }

    public ExperienceDTO(String jobtitle, String companyname, String description, Date startDate, Date endDate, boolean isPublic) {
        this.jobtitle = jobtitle;
        this.companyname = companyname;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPublic = isPublic;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("isPublic")
    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public String toString() {
        return "ExperienceDTO{" +
                "id=" + id +
                ", jobtitle='" + jobtitle + '\'' +
                ", companyname='" + companyname + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isPublic=" + isPublic +
                '}';
    }
}
