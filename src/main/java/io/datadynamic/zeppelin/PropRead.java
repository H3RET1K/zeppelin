/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.datadynamic.zeppelin;

import java.util.*;
import java.io.*;


/**
 *
 * @author H3RET1K
 */
public class PropRead {
    
    public static void main (String[] args) {
        
        Properties prop = new Properties();
        
        try {
            
        //Is this method inefficient because you can do it all in one step below?    
        /*FileInputStream in = new FileInputStream("/zeppelin/db.properties");
        prop.load(in);
        in.close();*/
            
        //Does it automatically know to read from the root of the project folder?   
        prop.load(new FileInputStream("db.properties"));   
            
        //Testing the line read
        System.out.println("DB connection = " + prop.getProperty("DatabaseConnectionString"));
        System.out.println("DB Driver = " + prop.getProperty("DatabaseDriver"));         
        System.out.println("DB user = " + prop.getProperty("DatabaseUsername"));                 
        System.out.println("DB Pass = " + prop.getProperty("DatabasePassword"));                       
        
        } catch (Exception ex) {
            System.out.println("Cannot read file");
            System.exit(1);
        }
       
    }
} 
