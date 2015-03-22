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
    private long userid; 
    private String note;
    private String datecreated;
    
    public IssueNote() {}
    
    public IssueNote(long id, long userid, String note, String datecreated){
        this.id = id;
        this.userid = userid;
        this.note = note;
        this.datecreated = datecreated;
        }
    
    public long getId(){
        return id;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public long getUserid(){
        return userid;
    }
    
    public void setUserid(long userid){
        this.userid = userid;
    }
    
    public String getNote(){
        return note;
    }
    
    public void setNote(String note){
        this.note = note;
    }
    
    public String getDatecreated(){
        return datecreated;
    }
    
    public void setDatecreated(String datecreated){
        this.datecreated = datecreated;
    }
    
}

