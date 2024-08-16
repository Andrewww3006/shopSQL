import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Properties;
import static java.nio.file.Files.newInputStream;
public class Main {

    public static void main(String[] args)  {
        // write your code here
        //Class.forName("org.postgresql.Driver");
Item shoes = new Item(1, "Shoes", 100);
Item pants = new Item(2, "Pants", 200);
Item shirt = new Item(3, "Shirt", 50);


       /* try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "hustlajo1989")) {
        }
catch (SQLException exception){
    System.out.println("Connection failed...");
}*/

        try (Connection conn = getConnection()) {



            Statement statement = conn.createStatement();
            //statement.executeUpdate("CREATE TABLE items2 (Id INT, Name2 VARCHAR (20), Price INT)");
            //statement.executeUpdate("INSERT Items (Id, Name, Price) VALUES (01, 'Shoes', 120)");
            //statement.executeUpdate("DROP TABLE items2");'
            //statement.executeUpdate("INSERT INTO items(Id, Price) VALUES ('"+shoes.getId()+"', '"+shoes.getPrice()+"')");
            //statement.executeUpdate("INSERT INTO items(Name) VALUES ('"+shoes.getName()+"')");
            //statement.executeUpdate("DELETE FROM items WHERE price=50");
            //statement.executeUpdate("DELETE FROM items WHERE id=2");
            //statement.executeUpdate("INSERT into items (id, name, price) values ('"+shoes.getId()+"', '"+shoes.getName()+"', '"+shoes.getPrice()+"')");
            //statement.executeUpdate("INSERT into items (id, name, price) values ('"+pants.getId()+"', '"+pants.getName()+"', '"+pants.getPrice()+"')");
            //statement.executeUpdate("UPDATE items SET price = price - 20");
            /*String sql  = "INSERT INTO Items (id,name,price) Values (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
               preparedStatement.setInt(1, shirt.getId());
                preparedStatement.setString(2, shirt.getName());
                preparedStatement.setInt(3, shirt.getPrice());
                preparedStatement.executeUpdate();*/


            ResultSet resultSet = statement.executeQuery("SELECT * FROM items");
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                System.out.println("id = " + id + "; name = " + name + "; price = " + price + ".");

            }

            int maxPrice = 60;
            PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT * FROM items WHERE price > ?");
            preparedStatement2.setInt(1, maxPrice);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            System.out.println("The items with price higher 60:");
            while (resultSet2.next()){
                int id = resultSet2.getInt(1);
                String name = resultSet2.getString("name");
                int price = resultSet2.getInt("price");
                System.out.println("id = " + id + "; name = " + name + "; price = " + price + ".");

            }


            System.out.println("Connection is ok!");
        }
        catch (Exception ex){
            System.out.println("Connection failed");
        }
    }


    public static Connection getConnection() throws SQLException, IOException{
        Properties props = new Properties();
        try (InputStream in = newInputStream(Paths.get("database.properties"))){
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        return DriverManager.getConnection(url, username, password);

    }
}