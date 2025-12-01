import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
// 1. open a connection to the database
// use the database URL to point to the correct database
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    "root",
                    "yearup");

// define your query
            String query = """
                    select ProductID, ProductName, UnitPrice, UnitsInStock
                    FROM  Products""";

// Create statement
// the statement is tied to the open connection
            Statement statement = connection.prepareStatement(query);

// 2. Execute your query
            ResultSet results = statement.executeQuery(query);
// process the results
            while (results.next()) {
                String productID = results.getString("ProductID");
                String productName = results.getString("ProductName");
                String unitsInStock = results.getString("ProductName");

                System.out.println(productID);

                System.out.println("ProductID: " + productID);
                System.out.println("Name: " + productName);
                System.out.println("Units in Stock: " + unitsInStock);
                System.out.println("---------------\n");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }


// 3. Close the connection
        connection.close();

    }
}
