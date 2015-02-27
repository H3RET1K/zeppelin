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

/**
 *
 * @author vgoff
 */
public final class DB {
    final private static DataSource ds;
    final private static DBI dbi;
    static {
        ds = JdbcConnectionPool.create(
            "jdbc:h2:mem:test",
            "username",
            "password"
        );        
        dbi = new DBI(ds);
    }
         
    private DB(){}
    
    static public Handle getDataSource(){
        return dbi.open();
    }
    
}