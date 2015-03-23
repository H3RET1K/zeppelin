/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.datadynamic.zeppelin.DAO;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author H3RET1K
 */

@XmlRootElement 
public class Issue {
    private long id;
    private String labels;
    private String status;
    private String description;
    private String version;
    private String project;
    private String assignedUser;    
    private String createdDate;
    private String closedDate;
    
    public Issue () {}
    
    public Issue(long id, String labels, String status, String description, String version, String project, String assignedUser, String createdDate, String closedDate) {
        this.id = id;
        this.labels = labels;
        this.status = status;
        this.description = description;
        this.version = version;
        this.project = project;
        this.assignedUser = assignedUser;        
        this.createdDate = createdDate;
        this.closedDate = closedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }
       
}


