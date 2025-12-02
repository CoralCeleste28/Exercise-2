import java.sql.*;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static boolean doWhile = true;
    static int homeScreenSelection = 0;

    public static void main(String[] args){
        homeScreen();
    }

    public static void homeScreen(){

        do{
            System.out.println("""
                Select an option:
                1) Display all products
                2) Display all customers
                3) Display all categories
                0) Exit""");

            homeScreenSelection = Integer.parseInt(scanner.nextLine());

            if (homeScreenSelection == 0){
                closeApp();
            } else if (homeScreenSelection == 1) {
                displayAllProducts();
            } else if (homeScreenSelection == 2) {
                displayAllCustomers();
            } else if (homeScreenSelection == 3) {
                displayAllCategories();
            } else {
                System.out.println("Try again. PLease select a valid entry.");
                homeScreenSelection = 0;
            }

        } while (doWhile);
    }

    public static void closeApp(){
    System.out.println("All done, bye! :)");
    scanner.close();
    }

    public static void displayAllProducts(){

    }

    public static void displayAllCategories(){
        // Making a connection with the database
    try(Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/northwind",
            "root",
            "yearup"))
    {
        // the query workspace is inside the () in the prepared statement
        PreparedStatement preparedStatement = connection.prepareStatement(
                "Select CategoryID FROM Categories");
        // Processing query
        ResultSet results = preparedStatement.executeQuery();

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    }

    public static void displayAllCustomers() {
            // 1. open a connection to the database
            // use the database URL to point to the correct database
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    "root",
                    "yearup")) {


                // define your query
                String query = """
                        select CustomerID, CompanyName
                        FROM  Customers
                        """;

                // Create statement
                // the statement is tied to the open connection
                PreparedStatement preparedstatement = connection.prepareStatement(query);

                // 2. Execute your query
                ResultSet results = preparedstatement.executeQuery(query);

                // process the results
                while (results.next()) {
                    String customerID = results.getString("CustomerID");
                    String customerName = results.getString("Customer`Name");

                    System.out.println("Customer ID: " + customerID);
                    System.out.println("Customer Name: " + customerName);
                    System.out.println("---------------\n");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }





