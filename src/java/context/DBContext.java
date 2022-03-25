package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    private static DBContext instance;
    public Connection con;
    
    private DBContext() throws ClassNotFoundException, SQLException {      
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");     
        this.con = DriverManager.getConnection("jdbc:sqlserver://bookingField.mssql.somee.com:1433;databaseName=bookingField;user=vangnguyengz_SQLLogin_1;password=eilprz7vbw");
        //this.con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookingFootballField;user=sa;password=123");
    }
    public static DBContext getInstance() throws ClassNotFoundException, SQLException {
        DBContext result = instance;
        if(result != null){
            return result;
        }   
        synchronized(DBContext.class){
            if (instance == null) {
                instance = new DBContext();
            }
        }
        return instance;
    }

    /*Insert your other code right after this comment*/
 /*Change/update information of your database connection, DO NOT change name of instance variables in this class*/

    public static void main(String[] args) {
        try {
            DBContext db = DBContext.getInstance();
            System.out.println(db.con);
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
