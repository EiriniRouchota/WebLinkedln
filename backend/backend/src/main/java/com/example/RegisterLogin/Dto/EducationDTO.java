package com.example.RegisterLogin.Dto;

public class EducationDTO {
    private int id;
    private String degree;
    private String institution;
    private String startDate;
    private String endDate;

    public EducationDTO() {
    }

    public EducationDTO(String degree, String institution, String startDate, String endDate, int id) {
        this.degree = degree;
        this.institution = institution;
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
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

    @Override
    public String toString() {
        return "EducationDTO{" +
                "id=" + id +
                ", degree='" + degree + '\'' +
                ", institution='" + institution + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}

