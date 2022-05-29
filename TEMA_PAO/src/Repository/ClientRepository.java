package Repository;

import CLASE.PERSOANE.Author;
import CLASE.PERSOANE.Client;
import Data.DataBases;

import java.sql.*;

public class ClientRepository {

    public void insertClient(Client client) {
        String preparedSql = "{call insertClients(?,?,?)}";

        Connection databaseConn = DataBases.getDataBases();
        try {
            CallableStatement call = databaseConn.prepareCall(preparedSql);
            call.setString(1, client.getName());
            call.setString(2, client.getNumber());
            call.setInt(3, client.getAge());

            call.registerOutParameter(1, Types.VARCHAR);

            call.execute();
            System.out.println(">--Added client with name: " + call.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Client getClientByName(String name){
        String selectSQL = "SELECT * FROM clients WHERE name =?";

        Connection databaseConn = DataBases.getDataBases();
        try{
            PreparedStatement preparedStatement = databaseConn.prepareStatement(selectSQL);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToClient(resultSet);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void updateClient(String name, String number){
        String updateNameSql = "UPDATE clients SET number=? WHERE name=?";

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
    private Client mapToClient(ResultSet resultSet) throws SQLException{
        if(resultSet.next()){
            return new Client(resultSet.getString(2), resultSet.getString(3),resultSet.getInt(4));
        }
        return null;
    }
    public void deleteClientByName(String name){
        String deleteSql = "DELETE FROM clients WHERE name=?";

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
