package com.example.RegisterLogin.response;

import java.util.Date;

public class EducationResponse {
    private String degree;
    private Date startDate;
    private Date endDate;
    private boolean isPublic;
    private String institutionName;
    private int institutionId; // Add this if you need to return the institution's ID as well

    // Constructor with fields
    public EducationResponse(boolean isPublic, Date endDate, Date startDate, String degree, String institutionName) {
        this.isPublic = isPublic;
        this.endDate = endDate;
        this.startDate = startDate;
        this.degree = degree;
        this.institutionName = institutionName;

    }

    // Empty constructor
    public EducationResponse() {
    }



    // Getters and Setters
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public int getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
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
}
