/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.datadynamic.zeppelin;

import javax.sql.DataSource;
import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import java.util.*;
import java.io.*;

/**
 *
 * @author vgoff
 */
public final class DB {
    final private static DataSource ds;
    final private static DBI dbi;
    static {
        Properties prop = new Properties();
        
        try {               
            prop.load(new FileInputStream("db.properties"));   
        } catch (Exception ex) {
            System.out.println("Cannot read file");
            System.exit(1); 
        }
        
        String connection = prop.getProperty("DatabaseConnectionString");     
        String user = prop.getProperty("DatabaseUsername");                 
        String pass = prop.getProperty("DatabasePassword");        
        
        ds = JdbcConnectionPool.create(
            connection,
            user,
            pass
        );        
        dbi = new DBI(ds);
    }
                          
    private DB(){}
    
    static public Handle getDataSource(){
        return dbi.open();
    }   
}
  

