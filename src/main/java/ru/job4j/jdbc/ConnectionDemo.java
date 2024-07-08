package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config configDB = new Config("data/db/app.properties");
        configDB.load();
        Class.forName(configDB.value("driver"));
        try (Connection connection = DriverManager.getConnection(
                configDB.value("url"),
                configDB.value("login"),
                configDB.value("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}