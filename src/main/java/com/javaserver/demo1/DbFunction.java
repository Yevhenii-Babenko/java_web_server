package com.javaserver.demo1;

import java.sql.*;
import java.sql.ResultSet;

public class DbFunction {
    public Connection connect_to_db(String dbName, String user, String password) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, user, password);
            if (conn != null) {
                System.out.println("connection establish");
            } else {
                System.out.println("connection failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public void createTable(Connection conn, String table_name) {
        Statement statement;
        try {
            String query = "create table " + table_name + "(empid SERIAL,name varchar(200),address varchar(200),primary key(empid));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insert_row(Connection conn, String table_name, String name, String address) {
        Statement statement;
        PreparedStatement preparedStatement;

        try {
            String query = String.format("INSERT INTO %s(name,address) VALUES('%s','%s');",table_name,name,address);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row insert");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void read_data(Connection conn, String table_name) {
        Statement statement;
        ResultSet rs =null;
        try {
            String query=String.format("SELECT * FROM %s",table_name);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while(rs.next()) {
                System.out.println(rs.getString("empid")+ " ");
                System.out.println(rs.getString("name")+ " ");
                System.out.println(rs.getString("address")+ " ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update_name(Connection conn, String table_name,String old_name, String new_name) {
        Statement statement;
        try {
            String query = String.format("UPDATE %s SET name='%s' WHERE name='%s'",table_name,new_name,old_name);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.print("Updated name");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void search_by_name(Connection conn, String table_name, String name) {
        Statement statement;
        ResultSet rs =null;
        try {
            String query = String.format("SELECT * FROM %s WHERE name= '%s'", table_name, name);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("empid")+ " ");
                System.out.print(rs.getString("name")+ " ");
                System.out.println(rs.getString("address")+ " ");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void search_by_id(Connection conn, String table_name, int id) {
        Statement statement;
        ResultSet rs =null;
        try {
            String query = String.format("SELECT * FROM %s WHERE empid= %s", table_name, id);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("empid")+ " ");
                System.out.print(rs.getString("name")+ " ");
                System.out.println(rs.getString("address")+ " ");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void delete_row_by_name(Connection conn, String table_name, String name) {
        Statement statement;
        try {
            String query = String.format("DELETE FROM %s WHERE name='%s'", table_name, name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.print("Delete row");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void delete_row_by_id(Connection conn, String table_name, int id) {
        Statement statement;
        try {
            String query = String.format("DELETE FROM %s WHERE empid=%s", table_name, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.print("Delete row");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void delete_table(Connection conn, String table_name) {
        Statement statement;
        try {
            String query = String.format("DROP TABLE %s", table_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.print("Delete table");
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
