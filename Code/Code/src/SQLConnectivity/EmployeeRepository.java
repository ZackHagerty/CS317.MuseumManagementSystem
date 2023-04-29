import java.sql.*;
import java.util.ArrayList;

import Models.Employee;

//Tested, approved
public class EmployeeRepository {
    
    static String dbURL = "jdbc:mysql://localhost:3306/museum";
    static String user = "root";
    static String password = "seed";


    public static void editEmployee(String newSSN, String oldName, String newName ) throws SQLException{

        try 
        (
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            CallableStatement statement = conn.prepareCall("{call updateEmployees(?, ?, ?)}");
        )
        { 
            statement.setString(1, newSSN);
            statement.setString(2, oldName);
            statement.setString(3, newName);            
 
            statement.execute();
            statement.close();
 
            System.out.println(oldName + " has been replaced in the MMS by "+ newName);
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Employee> getEmployees() throws SQLException{
        
        ArrayList<Employee> employees = new ArrayList<Employee>();

        try 
        (
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            CallableStatement statement = conn.prepareCall("{call getEmployees()}");
        )
        {

            boolean hadResults = statement.execute();
         
            while (hadResults) 
            {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) 
                {
                    employees.add(new Employee(
                        resultSet.getString("SSN"),
                        resultSet.getString("Name")
                    ));
                }
                hadResults = statement.getMoreResults();       
            }
        }

        return employees;
    }    
}
