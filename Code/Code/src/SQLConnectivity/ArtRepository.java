import java.sql.*;
import java.util.ArrayList;

import Models.Art;

public class ArtRepository {
       
    static String dbURL = "jdbc:mysql://localhost:3306/museum";
    static String user = "root";
    static String password = "seed";
 

    public static void createArt(int artistID, String filePath, String title, String date, String department) throws SQLException{

        try 
        (
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            CallableStatement statement = conn.prepareCall("{call createArt(?, ?, ?, ?, ?)}");
        )
        {
            statement.setInt(1, artistID);
            statement.setString(2, filePath);
            statement.setString(3, title);
            statement.setString(4, date);
            statement.setString(5, department); 

            statement.execute();
            statement.close();

            System.out.println("createArt() has been executed");
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public static ArrayList<Art> getArt() throws SQLException{
        
        ArrayList<Art> art = new ArrayList<Art>();

        try 
        (
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            CallableStatement statement = conn.prepareCall("{call getArt()}");
        )
        {

            boolean hadResults = statement.execute();

            while(hadResults)
            {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    art.add(new Art(
                       resultSet.getInt("ArtID"),
                       resultSet.getInt("ArtistID"),
                       resultSet.getString("FilePath"),                           
                       resultSet.getString("Title"),
                       resultSet.getString("Date"),
                       resultSet.getString("Department")
                    ));
                }
                hadResults = statement.getMoreResults();
            }
        }

        return art;
    }

    public static void updateArt(int artID, int artistID, String filePath, String title, String date, String department) throws SQLException{

        try 
        (
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            CallableStatement statement = conn.prepareCall("{call updateArt(?, ?, ?, ?, ?, ?)}");
        )
        {
            statement.setInt(1, artID);
            statement.setInt(2, artistID);
            statement.setString(3, filePath);
            statement.setString(4, title);
            statement.setString(5, date); 
            statement.setString(6, department); 

            statement.execute();
            statement.close();

            System.out.println("updateArt() has been executed");

        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void deleteArt(int artID)
    {
    
        try 
        (
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            CallableStatement statement = conn.prepareCall("{call deleteArt(?)}");
        )
        {
            statement.setInt(1, artID);

            statement.execute();
            statement.close();

            System.out.println("deleteArt() has been executed");

        } catch(SQLException ex){
            ex.printStackTrace();
        }   
    }
}
