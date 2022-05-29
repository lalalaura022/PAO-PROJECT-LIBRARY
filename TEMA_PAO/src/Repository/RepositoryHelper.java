package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryHelper {
    private static RepositoryHelper single_instance;

    private RepositoryHelper(){}

    public static synchronized RepositoryHelper getRepositoryHelper(){
        if(single_instance == null)
            single_instance = new RepositoryHelper();
        return single_instance;
    }
    public void executeSql(Connection conn, String sql) throws SQLException{
        Statement state = conn.createStatement();

        state.execute(sql);
    }
    public void executeUpdateSql(Connection conn, String sql) throws SQLException{
        Statement state = conn.createStatement();

        int i = state.executeUpdate(sql);
    }
    public ResultSet executeQuerySql(Connection conn, String sql) throws SQLException{
        Statement state = conn.createStatement();
        return state.executeQuery(sql);
    }
}
