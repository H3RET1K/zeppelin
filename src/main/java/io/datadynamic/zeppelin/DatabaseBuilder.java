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
            connectionHandle.execute("CREATE TABLE IF NOT EXISTS users (id identity, username varchar(140), password varchar(max), role varchar(140))");
            connectionHandle.execute("insert into users(username, password, role) values ('admin', ?, 'admin')", BCrypt.hashpw("password", BCrypt.gensalt()));
            connectionHandle.close();
        } catch (Exception ex) {
            System.out.println("Cannot create in memory database.");
            System.exit(1);
        }
        /*
        Class.forName("org.h2.Driver");
        Connection setupConn = DriverManager.getConnection("jdbc:h2:mem:");
        
        setupConn.createStatement()
            .executeUpdate("CREATE TABLE IF NOT EXISTS users (id identity, username varchar(140), password varchar(max), role varchar(140))");        
        
        ResultSet adminExists = setupConn.createStatement().executeQuery("SELECT top 1 username FROM users where username = 'admin'");
        boolean hasAdmin = false;
        while(adminExists.next()) {
            hasAdmin = true;
        }
        adminExists.close();
        if(!hasAdmin) {
            String pw_hash = BCrypt.hashpw("password", BCrypt.gensalt());
            System.out.print("admin username does not exist so creating...");
            PreparedStatement pstmt =
            setupConn.prepareStatement("insert into users(username, password, role) values ('admin', ?, 'admin')");
            pstmt.setString(1, pw_hash);
            pstmt.execute();
            System.out.print("done!\n");
        }        
        
        setupConn.close();
        */
    }
}
