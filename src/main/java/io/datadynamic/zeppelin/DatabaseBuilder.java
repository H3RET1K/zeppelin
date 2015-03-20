/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.datadynamic.zeppelin;

import java.sql.SQLException;
import org.skife.jdbi.v2.Handle;

/**
 *
 * @author vgoff
 */
public class DatabaseBuilder {
    // DB Setup needs to go here
    public static void createDatabase() throws SQLException, ClassNotFoundException {        
        /*
        
        Initial DB Setup
        
        */
        try {
            Handle connectionHandle = DB.getDataSource();
            
            //PROJECT TABLE
            connectionHandle.execute("CREATE TABLE IF NOT EXISTS projects "
                    + "(id identity, "
                    + "name varchar(140), "
                    + "description varchar(max), "
                    + "projectstatusid bigint, "
                    + "PRIMARY KEY (id))");
            
            //USER TABLE
            connectionHandle.execute("CREATE TABLE IF NOT EXISTS users "
                    + "(id identity, "
                    + "username varchar(140), "
                    + "password varchar(max), "
                    + "role varchar(140), "
                    + "PRIMARY KEY (id))");
            
            //STATUS TABLE
            connectionHandle.execute("CREATE TABLE IF NOT EXISTS status "
                    + "(id identity, "
                    + "name varchar(140), "
                    + "description varchar(max), "
                    + "PRIMARY KEY (id))");
            
             //PROJECT STATUS TABLE
            connectionHandle.execute("CREATE TABLE IF NOT EXISTS projectstatus "
                    + "(id identity, "      
                    + "name varchar(140), "
                    + "description varchar(max), "
                    + "PRIMARY KEY (id))");
            
            //ISSUE NOTES TABLE
            connectionHandle.execute("CREATE TABLE IF NOT EXISTS issuenotes "
                    + "(id identity, "
                    + "userid bigint, "
                    + "note varchar(max), "
                    + "datedcreated datetime, "
                    + "PRIMARY KEY (id))");                
            
            //ISSUES TABLE
            connectionHandle.execute("CREATE TABLE IF NOT EXISTS issues "
                    + "(id identity, "
                    + "labels varchar(140), "
                    + "statusid bigint, "
                    + "description varchar(max), "
                    + "version varchar(140), "
                    + "projectid bigint, "
                    //+ "attachments blob, "
                    + "assigneduser varchar(140), "
                    + "noteid bigint, "
                    + "createddate datetime, "
                    + "closeddate datetime, "
                    + "PRIMARY KEY (id))");       
            
            //ADD FOREIGN KEYS
            connectionHandle.execute("ALTER TABLE projects  "
                    + "ADD FOREIGN KEY (projectstatusid) "
                    + "REFERENCES public.projectstatus(id)");
            
            connectionHandle.execute("ALTER TABLE issuenotes  "
                    + "ADD FOREIGN KEY (userid) "
                    + "REFERENCES public.users(id)");
            
            connectionHandle.execute("ALTER TABLE issues  "
                    + "ADD FOREIGN KEY (projectid) "
                    + "REFERENCES public.projects(id)");
            
            connectionHandle.execute("ALTER TABLE issues  "
                    + "ADD FOREIGN KEY (statusid) "
                    + "REFERENCES public.status(id)");
            
            connectionHandle.execute("ALTER TABLE issues  "                            
                    + "ADD FOREIGN KEY (noteid) "
                    + "REFERENCES public.issuenotes(id)");
            
            connectionHandle.execute("ALTER TABLE issues  "                              
                    + "ADD FOREIGN KEY (assigneduser) "
                    + "REFERENCES public.users(id)");
 
            // default data
            //  ADMIN USER
            connectionHandle.execute("insert into users(username, password, role) values ('admin', ?, 'admin')", BCrypt.hashpw("password", BCrypt.gensalt()));
            
            //  PROJECT STATUS
            connectionHandle.execute("insert into projectstatus(description) values ('open')");
            connectionHandle.execute("insert into projectstatus(description) values ('closed')");
            //  PROJECT
            connectionHandle.execute("insert into projects(name, description, projectstatusid) values ('test project', 'this is a test project', 1)");
            connectionHandle.execute("insert into projects(name, description, projectstatusid) values ('B project', 'this is a B project', 1)");
            
            connectionHandle.close();
        } catch (Exception ex) {
            System.out.println("Cannot create database stucture.");
            System.exit(1);
        }     
    }
}
