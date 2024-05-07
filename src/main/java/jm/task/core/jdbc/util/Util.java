package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static SessionFactory sessionFactory;

    private static Configuration getConfiguration() {
        Configuration config = new Configuration();
        Properties props = new Properties();
        props.put("hibernate.connection.username", DB_USERNAME);
        props.put("hibernate.connection.password", DB_PASSWORD);
        props.put("hibernate.connection.driver_class", DB_DRIVER);
        props.put("hibernate.connection.url", DB_URL);
        props.put("hibernate.connection.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        props.put("hibernate.current_session_context_class", "thread");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");
        config.setProperties(props);
        return config;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = getConfiguration();
                config.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(config.getProperties()).build();
                sessionFactory = config.buildSessionFactory(serviceRegistry);
                System.out.println("Hibernate SessionFactory created");

            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return sessionFactory;
    }


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.out.println("Connection Failed! Check output console");
        }
        return connection;
    }


}
