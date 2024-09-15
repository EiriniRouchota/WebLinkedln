package com.example.RegisterLogin.response;

import java.util.Date;

public class ExperienceResponse {
    private String description;
    private String companyname;
    private String jobtitle;
    private Date startDate;
    private Date endDate;
    private boolean isPublic;


    // Constructor with fields
    public ExperienceResponse(boolean isPublic, Date endDate, Date startDate, String description, String companyname, String jobtitle) {
        this.isPublic = isPublic;
        this.endDate = endDate;
        this.startDate = startDate;
        this.description = description;
        this.companyname = companyname;
        this.jobtitle = jobtitle;

    }

    // Empty constructor
    public ExperienceResponse() {
    }





    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }
}
