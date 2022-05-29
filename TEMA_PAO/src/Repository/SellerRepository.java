package Repository;

import CLASE.PERSOANE.Client;
import CLASE.PERSOANE.Seller;
import Data.DataBases;

import java.sql.*;

public class SellerRepository {
    public void insertSeller(Seller seller) {
        String preparedSql = "{call insertSeller(?,?,?,?)}";

        Connection databaseConn = DataBases.getDataBases();
        try {
            CallableStatement call = databaseConn.prepareCall(preparedSql);
            call.setString(1, seller.getName());
            call.setString(2, seller.getNumber());
            call.setInt(3, seller.getAge());
            call.setFloat(4,seller.getSalary());

            call.registerOutParameter(1, Types.VARCHAR);

            call.execute();
            System.out.println(">--Added seller with name: " + call.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Seller getSellerByName(String name){
        String selectSQL = "SELECT * FROM sellers WHERE name =?";

        Connection databaseConn = DataBases.getDataBases();
        try{
            PreparedStatement preparedStatement = databaseConn.prepareStatement(selectSQL);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToSeller(resultSet);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void updateSeller(String name, String number){
        String updateNameSql = "UPDATE sellers SET number=? WHERE name=?";

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
    private Seller mapToSeller(ResultSet resultSet) throws SQLException{
        if(resultSet.next()){
            return new Seller(resultSet.getString(2), resultSet.getString(3),resultSet.getInt(4),resultSet.getFloat(5));
        }
        return null;
    }
    public void deleteSellerByName(String name){
        String deleteSql = "DELETE FROM sellers WHERE name=?";

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
