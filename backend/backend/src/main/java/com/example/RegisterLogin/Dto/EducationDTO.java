package com.example.RegisterLogin.Dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class EducationDTO {
    private int id;
    private String degree;
    private int institutionId;
    private Date startDate;
    private Date endDate;
    @JsonProperty("isPublic")
    private boolean isPublic;

    public EducationDTO() {
    }

    public EducationDTO(String degree, int institutionId, Date startDate, Date endDate, int id, boolean isPublic) {
        this.degree = degree;
        this.institutionId = institutionId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
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

    public int getInstitutionId() {
        return institutionId;
    }

    public void setInstitution(int institutionId) {
        this.institutionId = institutionId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
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
        return "EducationDTO{" +
                "id=" + id +
                ", degree='" + degree + '\'' +
                ", institution='" + institutionId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isPublic=" + isPublic +
                '}';
    }
}

