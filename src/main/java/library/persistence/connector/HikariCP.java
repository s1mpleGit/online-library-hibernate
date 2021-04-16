//package library.persistence.connector;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//import javax.sql.DataSource;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.time.Duration;
//import java.util.Properties;
//
//public class HikariCP {
//
//    private static DataSource dataSource;
//
//    private static String url;
//    private static String username;
//    private static String password;
//    private static String driver;
//
//    static {
//        try {
//            InputStream resprop = HikariCP.class.getResourceAsStream("/database/data-source.properties");
//
//            Properties prop = new Properties();
//            prop.load(resprop);
//
//            driver = prop.getProperty("driver");
//            url = prop.getProperty("url");
//            username = prop.getProperty("username");
//            password = prop.getProperty("password");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Failed to load database properties");
//        }
//    }
//
//    private static HikariConfig getHikariConfig() {
//
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDriverClassName(driver);
//        hikariConfig.setJdbcUrl(url);
//        hikariConfig.setUsername(username);
//        hikariConfig.setPassword(password);
//        hikariConfig.setMaximumPoolSize(10);
//        hikariConfig.setConnectionTimeout(Duration.ofSeconds(10).toMillis());
//        hikariConfig.setIdleTimeout(Duration.ofMinutes(1).toMillis());
//        return hikariConfig;
//    }
//
//    private static void createDataSource() {
//
//        HikariConfig hikariConfig = getHikariConfig();
//        dataSource = new HikariDataSource(hikariConfig);
//    }
//
//    private static DataSource getDataSource() {
//
//        if (dataSource == null) {
//            createDataSource();
//        }
//        return dataSource;
//    }
//
//    public static Connection getConnection() throws SQLException {
//
//        return getDataSource().getConnection();
//    }
//
//}
