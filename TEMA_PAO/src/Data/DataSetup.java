package Data;

import Data.DataBases;
import Repository.RepositoryHelper;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class DataSetup {

    public void createTables(){
        File file = new File("src/Files/QuerysCreateTable.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String createTableSql;

            while((createTableSql = br.readLine()) != null){
                Connection databaseConnection = DataBases.getDataBases();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();
                try{
                    repositoryHelper.executeSql(databaseConnection, createTableSql);
                }catch (SQLException e){
                    System.out.println("Error when I try to create the tables!");
                }

            }
        }
        catch(FileNotFoundException e){
            System.out.println("Error when I try to create the tables! File not found!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void addRows(){
        File file = new File("src/Files/QuerysAddRows.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String insertRow;

            while((insertRow = br.readLine()) != null){
                Connection databaseConn = DataBases.getDataBases();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

                try{
                    repositoryHelper.executeUpdateSql(databaseConn, insertRow);
                }catch(SQLException e){
                    e.printStackTrace();
                    System.out.println("Error when I try to insert rows");
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void deleteAllRows(){
        File file = new File("src/Files/QuerysDeleteAllRows.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String deleteRow;

            while((deleteRow = br.readLine()) != null){
                Connection databaseConn = DataBases.getDataBases();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();
                repositoryHelper.executeUpdateSql(databaseConn, deleteRow);
            }
            System.out.println("The information was deleted successfully!");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(SQLException e){
            System.out.println("Error when I try to delete all rows!");
        }
    }
    public void dropAllTables(){
        File file = new File("src/Files/QuerysDropAllTables.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String dropTable;

            while((dropTable = br.readLine()) != null){
                Connection databaseConn = DataBases.getDataBases();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

                repositoryHelper.executeSql(databaseConn,dropTable);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error when I try to create the tables!");
        }
    }
    public void displayTable(){
        String selectSQL = "";
        System.out.println("What is the name of the table you want to show? ");
        Scanner var = new Scanner(System.in);
        String name = var.nextLine();

        if(name.equalsIgnoreCase("authors")){
            selectSQL = "SELECT * FROM authors";
        }else if(name.equalsIgnoreCase("clients")) {
            selectSQL = "SELECT * FROM clients";
        }else if(name.equalsIgnoreCase("sellers")) {
            selectSQL = "SELECT * FROM sellers";
        }

        Connection databaseConn = DataBases.getDataBases();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try{
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConn, selectSQL);

            while(resultSet.next()) {
                ResultSetMetaData results = resultSet.getMetaData();
                int j = results.getColumnCount();

                for (int i = 2; i <= j; i++) {
                    System.out.print(resultSet.getString(i) + " ");
                }
                System.out.println();
            }
            }catch (SQLException e) {
                e.printStackTrace();
             }
        }
}
