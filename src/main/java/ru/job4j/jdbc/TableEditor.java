package ru.job4j.jdbc;

import java.io.InputStream;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        try {
            initConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Properties setProperties(Path path) {
        Properties properties = new Properties();
        try {
            Class.forName("org.postgresql.Driver");
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(path.toString());
            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    private void initConnection() throws SQLException {
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        this.connection = DriverManager.getConnection(url, login, password);
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println(metaData.getUserName());
        System.out.println(metaData.getURL());
    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("create table if not exists %s();", tableName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("drop table %s;", tableName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s add column %s %s;", tableName, columnName, type);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s drop column %s;", tableName, columnName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = setProperties(Path.of("app.properties"));
        TableEditor tableEditor = new TableEditor(properties);
        System.out.println(tableEditor.getScheme("demo_table"));
    }
}