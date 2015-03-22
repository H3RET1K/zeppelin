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
    private long statusid;
    private String description;
    private String version;
    private long projectid;
    private String assigneduser;
    private long noteid;
    private String createddate;
    private String closeddate;
    
    public Issue () {}
    
    public Issue(long id, String labels, long statusid, String description, String version, long projectid, String assigneduser, long noteid, String createddate, String closeddate){
        this.id = id;
        this.labels = labels;
        this.statusid = statusid;
        this.description = description;
        this.version = version;
        this.projectid = projectid;
        this.assigneduser = assigneduser;
        this.noteid = noteid;
        this.createddate = createddate;
        this.closeddate = closeddate;
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
    
    public long getStatusId() {
        return statusid;
    }
    
    public void setStatusId(long statusid) {
        this.statusid = statusid;
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
    
    public long getProjectId() {
        return projectid;
    }
    
    public void setProjectId(long projectid) {
        this.projectid = projectid;
    }
    
    public String getAssignedUser() {
        return assigneduser;
    }
    
    public void setAssignedUser(String assigneduser) {
        this.assigneduser = assigneduser;
    }
    
    public long getNoteId() {
        return noteid;
    }
    
    public void setNoteId(long noteid) {
        this.noteid = noteid;
    }
    
    public String getCreatedDate() {
        return createddate;
    }
    
    public void setCreatedDate(String createddate) {
        this.createddate = createddate;
    }
    
    public String getClosedDate() {
        return closeddate;
    }
    
    public void setClosedDate(String closeddate) {
        this.closeddate = closeddate;
    }
       
}


