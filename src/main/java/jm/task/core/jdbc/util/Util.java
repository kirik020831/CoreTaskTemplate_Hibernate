package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.sql.DriverManager;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String rootName = "root";
    private static final String password = "Aa02083125";
    private static final String connectionURL = "jdbc:mysql://localhost:3306/usertest";
    private static final String connectionDriver = "com.mysql.cj.jdbc.Driver";
    private static SessionFactory sessionFactory = null;
    private static Util instance = null;

    private Util() {
        try {
            if (null == sessionFactory || sessionFactory.isClosed()) {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, connectionDriver);
                settings.put(Environment.URL, connectionURL);
                settings.put(Environment.USER, rootName);
                settings.put(Environment.PASS, password);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Util getInstance() {
        if (null == instance) {
            instance = new Util();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(connectionDriver);
            connection = DriverManager.getConnection(connectionURL, rootName, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}

