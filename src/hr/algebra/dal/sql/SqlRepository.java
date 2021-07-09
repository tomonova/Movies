package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Account;
import hr.algebra.model.User;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Properties;
import javax.sql.DataSource;

public class SqlRepository implements Repository {

    String path = System.getProperty("user.dir") + "\\src\\hr\\algebra\\resources\\";
    String propFilePath = path + "application.properties";
    private static final String SERVER_NAME = "SERVER_NAME";
    private static final String DATABASE_NAME = "DATABASE_NAME";
    private static final String PORT="PORT";
    private static final String USER = "USER";
    private static final String PASSWORD = "PASSWORD";
    private static final String CHECK_USER = "{ CALL checkUser (?,?,?) }";
    private static final String CHECK_IF_USER_EXISTS = "{ CALL checkIfUserExists (?,?) }";
    private static final String INSERT_ACCOUNT_AND_USER = "{ CALL insertAccountAndUser (?,?,?,?,?) }";

    @Override
    public boolean checkUser(User user) throws Exception {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(CHECK_USER)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(3) == 1 ? true : false;
        }
    }

    @Override
    public boolean checkIfUserExists(User user) throws Exception{
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(CHECK_IF_USER_EXISTS)) {
            stmt.setString(1, user.getUsername());
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(2) == 1 ? true : false;
        }
    }

    @Override
    public void CreateAccountAndUser(Account account, User user) throws Exception {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_ACCOUNT_AND_USER)) {
            stmt.setString(1, account.getFirstName());
            stmt.setString(2, account.getLastName());
            stmt.setString(3, account.getEmail());
            stmt.setInt(4, account.getAccountType().getValue());
            stmt.setString(5, user.getPassword());
            stmt.executeUpdate();
        }
    }

}
