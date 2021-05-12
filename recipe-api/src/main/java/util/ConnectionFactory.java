package util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    // Eager singleton
//    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    // Lazy singleton
    private static ConnectionFactory connectionFactory;
    private Properties props = new Properties();

    /* Unsure about the purpose of this
    static {
        try {
            Class.forName("org.postgresql.drivers.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
     */



    private ConnectionFactory() {
        try {
            props.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }

        return connectionFactory;
    }

    public Connection getConnection() {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(
                    props.getProperty("host-url"),
                    props.getProperty("username"),
                    props.getProperty("password"));

        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return conn;

    }

}
