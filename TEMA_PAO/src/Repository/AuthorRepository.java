package Repository;

import CLASE.PERSOANE.Author;
import Data.DataBases;

import java.sql.*;

public class AuthorRepository {
    public void insertAuthor(Author author) {
        String preparedSql = "{call insertAuthors(?,?,?,?,?)}";

        Connection databaseConn = DataBases.getDataBases();
        try {
            CallableStatement call = databaseConn.prepareCall(preparedSql);
            call.setString(1, author.getName());
            call.setString(2, author.getNumber());
            call.setInt(3, author.getAge());
            call.setString(4, author.getDateBirth());
            call.setInt(5, author.getNumberofBooks());

            call.registerOutParameter(1, Types.VARCHAR);

            call.execute();
            System.out.println(">--Added author with name: " + call.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Author getAuthorByName(String name){
        String selectSQL = "SELECT * FROM authors WHERE name =?";

        Connection databaseConn = DataBases.getDataBases();
        try{
            PreparedStatement preparedStatement = databaseConn.prepareStatement(selectSQL);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToAuthor(resultSet);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void updateAuthor(String name, String number){
        String updateNameSql = "UPDATE authors SET number=? WHERE name=?";

        Connection databaseConn = DataBases.getDataBases();
        try{
            PreparedStatement preparedStatement = databaseConn.prepareStatement(updateNameSql);

            preparedStatement.setString(1,number);
            preparedStatement.setString(2,name);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private Author mapToAuthor(ResultSet resultSet) throws SQLException{
        if(resultSet.next()){
            return new Author(resultSet.getString(2), resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getInt(6));
        }
        return null;
    }
    public void deleteAuthorByName(String name){
        String deleteSql = "DELETE FROM authors WHERE name=?";

        Connection databaseConn = DataBases.getDataBases();
        try {
            PreparedStatement preparedStatement = databaseConn.prepareStatement(deleteSql);
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}