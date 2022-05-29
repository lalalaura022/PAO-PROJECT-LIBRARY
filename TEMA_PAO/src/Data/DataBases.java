package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBases {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "admin";
    private static final String PASSWORD = "root";

    private static Connection databaseConnection;

    private DataBases(){}

    public static Connection getDataBases(){
        try{
            if(databaseConnection == null || databaseConnection.isClosed()){
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            }
        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Error when I try to open the database!");
        }
        return databaseConnection;
    }
    public static void closeDataBases(){
        try{
            if(databaseConnection != null && !databaseConnection.isClosed()){
                databaseConnection.close();
            }
        }catch (SQLException e){
            System.out.println("Error when I try to close the database!");
        }
    }
}

