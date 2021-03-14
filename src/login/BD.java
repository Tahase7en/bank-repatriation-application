package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;


public class BD
{
	
	static Connection conn=null;
    static String sql;
	public static Connection dbConnector() { 
		try {
			 // db parameters  
	        String url = "jdbc:sqlite:E:/sqlite/login.db";  
	        // create a connection to the database  
	        conn = DriverManager.getConnection(url);     
	        System.out.println("Connection to SQLite has been established.");  
	    	return conn;  
		} catch (SQLException e) {
	        System.out.println(e.getMessage());  
		    return null;
		}
}
	
	public static String executionUpdate(String sql) {
        String result = "";
        try {
        	Statement st = conn.createStatement();
            st.executeUpdate(sql);

            result = sql;
            System.out.println(result);  
            System.out.println("///");  


        } catch (SQLException ex) {
            result = ex.toString();
            System.out.println(result);  

        }
        return result;

    }
	public static String queryUpdate(String nomTable, String[] nomColonne, ArrayList<JTextField> list, String s) {

        int i;
        sql = "UPDATE " + nomTable + " SET ";

        for (i = 0; i <= nomColonne.length - 1; i++) {
            sql += nomColonne[i] + "='" + list.get(i).getText() + "'";
            if (i < nomColonne.length - 1) {
                sql += ",";
            }
        }
        sql += " WHERE " + s;

        return BD.executionUpdate(sql);
        

    }
	public static String queryInsert(String nomTable, String[] nomColonne,String col, ArrayList<JTextField> list, String s) {

        
        int i;
        sql = "INSERT INTO " + nomTable + "(";
        for (i = 0; i <= nomColonne.length - 1; i++) {
            sql += nomColonne[i];
            if (i < nomColonne.length - 1) {
                sql += ",";
            }
        }
        sql +=","+col+ ") VALUES(";
        for (i = 0; i <= list.size() - 1; i++) {
            sql += "'" + list.get(i).getText() + "'";
            if (i < list.size() - 1) {
                sql += ",";
            }
        }
        sql +=","+s+ ")";
        return executionUpdate(sql);

    }
public static String queryInsert2(String nomTable, String nomColonne, String s) {
	
               sql = "INSERT INTO " + nomTable + "("+nomColonne+") Values("+s+");";  
               
        return executionUpdate(sql);

    }
	public static ResultSet executionQuery(String sql) {
        ResultSet resultSet = null;
        try {
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {System.err.println(ex);//
        }
        
        return resultSet;
    }
	public static void execute(String sql) {
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {System.err.println(ex);//
        }
        
    }
    public static ResultSet fcSelect(String[] nomColonne, String nomTable, String s) {

     //   ResultSet rs = BD.fcSelect(col, "banque", "iduser='"+id+"'");   
        sql = "SELECT ";
    	for(int i=0; i< nomColonne.length ; i++) {
	         sql+= nomColonne[i];
	         if (i < nomColonne.length - 1) {
	            sql += ",";
	         }
    	}
        sql+=" FROM " + nomTable + " WHERE " + s +"";
        return BD.executionQuery(sql);

    }
    
    public static String ChangeName(int IdUser,String Name)
	{
		String query="UPDATE login SET `nom` = '"+Name+"' WHERE (`id` ="+IdUser+" );";
		System.out.println(query);
        return BD.executionUpdate(query);
	}

	public static String ChangePass(int IdUser,String Pass)
	{
		String query="UPDATE login SET pwd = '"+Pass+"' WHERE id = "+IdUser+";";
		System.out.println(query);
        return executionUpdate(query);
	}
	public static String ChangeType(int IdUser,String Type)
	{
		String query="UPDATE login SET `Type` = '"+Type+"' WHERE (`id` ="+IdUser+" );";
        return executionUpdate(query);

	}
}

