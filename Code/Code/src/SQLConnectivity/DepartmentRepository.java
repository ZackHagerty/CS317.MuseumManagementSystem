import Models.Department;
import java.sql.*;
import java.util.ArrayList;

//Tested, approved
public class DepartmentRepository {
  
    static String dbURL = "jdbc:mysql://localhost:3306/museum";
    static String user = "root";
    static String password = "seed";

    public static ArrayList<Department> getDepartment() throws SQLException{
        
        ArrayList<Department> departments = new ArrayList<Department>();

        try 
        (
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            CallableStatement statement = conn.prepareCall("{call getDepartments()}");
        )
        {

        boolean hadResults = statement.execute();
         
            while (hadResults) 
            {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) 
                {
                    departments.add(new Department(
                        resultSet.getString("Title"),
                        resultSet.getString("Moderator")
                    ));
                }
                hadResults = statement.getMoreResults();       
            }
        }

        return departments;
    }  
}
