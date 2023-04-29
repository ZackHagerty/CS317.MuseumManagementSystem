import java.sql.*;
import java.util.ArrayList;

import Models.Artist;

public class ArtistRepository {
        
    static String dbURL = "jdbc:mysql://localhost:3306/museum";
    static String user = "root";
    static String password = "seed";
 

    public static void createArtist(String name, String dob, String dod, String info) throws SQLException{

        try 
        (
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            CallableStatement statement = conn.prepareCall("{call createArtist(?, ?, ?, ?)}");
        )
        {
            statement.setString(1, name);
            statement.setString(2, dob);
            statement.setString(3, dod);
            statement.setString(4, info); 

            statement.execute();
            statement.close();

            System.out.println("createArtist() has been executed");

        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public static ArrayList<Artist> getArtists() throws SQLException{
        
        ArrayList<Artist> artists = new ArrayList<Artist>();

        try 
        (
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            CallableStatement statement = conn.prepareCall("{call getArtists()}");
        )
        {

            boolean hadResults = statement.execute();

            while(hadResults)
            {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    artists.add(new Artist(
                       resultSet.getInt("ArtistID"),
                       resultSet.getString("Name"),                           
                       resultSet.getString("DOB"),
                       resultSet.getString("DOD"),
                       resultSet.getString("Info")
                    ));
                }
                hadResults = statement.getMoreResults();
            }
        }

        return artists;
    }

    public static void updateArtist(int artistID, String name, String dob, String dod, String info) throws SQLException{

        try 
        (
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            CallableStatement statement = conn.prepareCall("{call updateArtist(?, ?, ?, ?, ?)}");
        )
        {
            statement.setInt(1, artistID);
            statement.setString(2, name);
            statement.setString(3, dob);
            statement.setString(4, dod); 
            statement.setString(5, info); 

            statement.execute();
            statement.close();

            System.out.println("updateArtist() has been executed");

        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void deleteArtist(int artistID)
    {
    
        try 
        (
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            CallableStatement statement = conn.prepareCall("{call deleteArtist(?)}");
        )
        {
            statement.setInt(1, artistID);

            statement.execute();
            statement.close();

            System.out.println("deleteArtist() has been executed");

        } catch(SQLException ex){
            ex.printStackTrace();
        }   
    }   
}
