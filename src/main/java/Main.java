import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import static java.nio.file.Files.newInputStream;
public class Main {

    public static void main(String[] args)  {
        // write your code here
        //Class.forName("org.postgresql.Driver");
Item shoes = new Item(01, "Shoes", 100);


       /* try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "hustlajo1989")) {
        }
catch (SQLException exception){
    System.out.println("Connection failed...");
}*/
        try (Connection conn = getConnection()){
            System.out.println("Connection is ok!");

        }
        catch (Exception ex){
            System.out.println("Connection failed");
            System.out.println(ex);
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