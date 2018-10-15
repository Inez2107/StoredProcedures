package org.betavzw.be;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static String CONN_STRING = "jdbc:mysql://localhost/testdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Brussels";
    private static String USER = "root";
    private static String PASWOORD = "VDAB";
    private static String SELECT = "select id, voornaam, achternaam";
    private static String INSERT = "select id, voornaam) values (?,?)";
    private static String CALL = "call bieren.bierenMetEenWoord(?)";

    public static void main(String[] args) throws SQLException {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Geef woord in. Typ STOP om te stoppen");
            String woord = scanner.nextLine();

            try (Connection connection = DriverManager.getConnection(CONN_STRING, USER, PASWOORD);
                 CallableStatement statement = connection.prepareCall(CALL)) {
                statement.setString(1, '%' + woord + '%');
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("naam"));
                    }
                }
            }
        }
    }
}
