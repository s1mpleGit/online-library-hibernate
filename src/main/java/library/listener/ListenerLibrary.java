package library.listener;

import org.h2.tools.RunScript;
import org.h2.tools.Server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;

//import static library.persistence.connector.HikariCP.getConnection;

@WebListener
public class ListenerLibrary implements ServletContextListener {

    public ListenerLibrary() {
    }

    public void contextInitialized(ServletContextEvent sce) {

        try {
            Server.createTcpServer().start();
        } catch (Exception e) {
            System.out.println("Failed to create TCP server");
            e.printStackTrace();
        }

//        try (Connection conn = getConnection()) {
//
//            URL ddl = ListenerLibrary.class.getResource("/database/script/DDL-initialization.sql");
//            URL dml = ListenerLibrary.class.getResource("/database/script/DML-initialization.sql");
//
//            RunScript.execute(conn, Files.newBufferedReader(Paths.get(ddl.toURI()), StandardCharsets.UTF_8));
//            RunScript.execute(conn, Files.newBufferedReader(Paths.get(dml.toURI()), StandardCharsets.UTF_8));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Failed to initialize DB");
//        }
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

}
