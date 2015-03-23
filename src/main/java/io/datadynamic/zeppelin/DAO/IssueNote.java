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
public class IssueNote {
    private long id;
    private long userID; 
    private String note;
    private String dateCreated;
    private String userName;
    
    public IssueNote() {}
    
    public IssueNote(long id, long userid, String note, String datecreated){
        this.id = id;
        this.userID = userid;
        this.note = note;
        this.dateCreated = datecreated;
    }           

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
}