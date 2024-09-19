package com.example.RegisterLogin.Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="ads")
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "company_name", length = 255, nullable = false)
    private String companyname;


    @Column(name = "description",  columnDefinition = "TEXT", nullable = false)
    private String description;


    @Column(name = "is_remote", nullable = false)
    private boolean isRemote;


    @Column(name = "is_fulltime", nullable = false)
    private boolean isFulltime;


    @Column(name = "posted_date")
    @Temporal(TemporalType.DATE)
    private Date postedDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRemote(boolean remote) {
        isRemote = remote;
    }

    public void setFulltime(boolean fulltime) {
        isFulltime = fulltime;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRemote() {
        return isRemote;
    }

    public boolean isFulltime() {
        return isFulltime;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public boolean isStatus() {
        return status;
    }
}



