package com.buseduc.javacourse.petshop.db;


import com.buseduc.javacourse.petshop.Currency;
import com.buseduc.javacourse.petshop.Petshop;
import com.buseduc.javacourse.petshop.animalproperties.Allergy;
import com.buseduc.javacourse.petshop.users.Customer;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcAgent {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/petshop";
    static final String SETTINGS = "?useUnicode=true&" +
            "useJDBCCompliantTimezoneShift=true&" +
            "useLegacyDatetimeCode=false&" +
            "serverTimezone=UTC";
    private static final String USER = "petshop";
    private static final String PWD = "secret";
    private static JdbcAgent instance;
    private Connection connection;
    public void connect() {
        try {
            System.out.println("Creating database connection...");
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL + SETTINGS, USER, PWD);
            System.out.println("Got connection " + connection.toString());
        } catch (SQLException | ClassNotFoundException sqle ) {
            System.out.println("DB Connection error");
            sqle.printStackTrace();
        }
    }
    public static JdbcAgent getInstance() {
        if (instance == null) {
            instance = new JdbcAgent();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void saveCustomer(Customer customer) {
        try {
            Statement statement = connection.createStatement();
            String allergyRes = customer.getAllergy() != null ? "'" + customer.getAllergy().name() + "'" : "null";
            String sql = String.format("INSERT INTO petshop.customer(name, budget_amount, age, allergy ) " +
                    "VALUES ('%s', %f , %d, %s);", customer.getName(), customer.getAvailableMoney(),
                    customer.getAge(), allergyRes);
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            System.out.println("Couldn't save customer");
            se.printStackTrace();

        }
    }

    public Map<String, Customer> getCustomers() {
        Map<String, Customer> ret = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM customer";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("Retrieving data about customers");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Double amount = resultSet.getDouble("budget_amount");
                Integer age = resultSet.getInt("age");
                String allergyStr = resultSet.getString("allergy");
                Allergy allergy = null;
                try {
                    allergy = Allergy.valueOf(allergyStr);
                } catch (IllegalArgumentException | NullPointerException e) { /*SILENT*/ }
                ret.put(name, new Customer(name, amount, age, allergy));

            }

        } catch (SQLException se) {
            System.out.println("Couldn't get statement");
            se.printStackTrace();
        }
        return ret;
    }



}
