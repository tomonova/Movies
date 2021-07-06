package hr.algebra.dal.sql;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;

public class DataSourceSingleton {

    private DataSourceSingleton() {
    }

    private static DataSource instance;

    public static DataSource getInstance(String serverName, String dataBaseName, String user, String password) {
        if (instance == null) {
            instance = createInstance(serverName,dataBaseName,user,password);
        }
        return instance;
    }

    private static DataSource createInstance(String serverName, String dataBaseName, String user, String password) {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName(serverName);
        dataSource.setDatabaseName(dataBaseName);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}
