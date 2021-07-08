/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.User;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Properties;
import javax.sql.DataSource;

/**
 *
 * @author TomoNova
 */
public class SqlRepository implements Repository {

    String path = System.getProperty("user.dir") + "\\src\\hr\\algebra\\resources\\";
    String propFilePath = path + "application.properties";
    private static final String SERVER_NAME = "SERVER_NAME";
    private static final String DATABASE_NAME = "DATABASE_NAME";
    private static final String PORT="PORT";
    private static final String USER = "USER";
    private static final String PASSWORD = "PASSWORD";
    private static final String CHECK_USER = "{ CALL checkUser (?,?,?) }";

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

}
