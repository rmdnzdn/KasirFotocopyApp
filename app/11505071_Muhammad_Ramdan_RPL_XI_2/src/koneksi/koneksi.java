/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author zidun
 */
public class koneksi {
    private static Connection cn;
    
    public static Connection getKoneksi(){
         if (cn == null) {
            try {
                DriverManager.registerDriver(new org.sqlite.JDBC());
                cn = DriverManager.getConnection("jdbc:sqlite:db_fotocopy.db");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        if(cn == null){
//            try {
//                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//                cn = DriverManager.getConnection("jdbc:mysql://localhost/db_fotocopy","root","");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        return cn;
    }
}
