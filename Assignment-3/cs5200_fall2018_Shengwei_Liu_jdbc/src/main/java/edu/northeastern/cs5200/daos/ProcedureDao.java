package edu.northeastern.cs5200.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Phone;

public class ProcedureDao{
		
	    private static ProcedureDao instance=null;
	    private ProcedureDao(){} 
	    
	    public static ProcedureDao getInstance() {
	    	if(instance==null)
	    	{
	    		instance = new ProcedureDao() {};
	    	}
	    	return instance;
	    }
	       
	    private final String connectionString="jdbc:mysql://cs5200-fall2018-shengweiliu.co1xraymzewt.us-east-2.rds.amazonaws.com:3306/cs5200_fall2018_Shengwei_Liu_jdbc";
	    private final String username_db="ShengweiLiu";
	    private final String password_db="kingisme";
	    
	    private Connection connection=null;
	    private Statement  statement=null;
	    private ResultSet  results=null;
	    
	    
	    private final String CALL_PROCEDURE_1="{call getUnansweredQuestions(?)}";
	    private final String CALL_PROCEDURE_2="{call endorsedUserForWeek(?,?)}"; 
	     
	    public void getUnansweredQuestions(String moduleName)
	    // it is used to call procedure, getUnansweredQuestions
	    { 
	    	CallableStatement cstmt=null;
	    	PreparedStatement pstatement=null;
	        int result=0;    
	    	try {
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection(connectionString, username_db, password_db);
				pstatement =connection.prepareStatement(CALL_PROCEDURE_1);
				pstatement.setString(1, moduleName);
				
				results=pstatement.executeQuery();
				
				while(results.next())
				{
					 System.out.println("Text of question:"+results.getString("qtext"));
				     System.out.println("Name of question from widget: "+results.getString("wname"));
				     System.out.println("Numbers of answers for this question: "+results.getString("nuOfa_id"));
				}

		        
	    	} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				 connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    public void endorsedUserForWeek(Date dateStart,Date dateEnd)
	    // it is used to call procedure, endorsedUserForWeek
	    { 
	    	PreparedStatement pstatement=null;
	    	CallableStatement cstmt=null;
	        int result=0;    
	    	try {
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection(connectionString, username_db, password_db);
				
				
				    pstatement = connection.prepareStatement(CALL_PROCEDURE_2);
				    
				    pstatement.setDate(1, dateStart);
				    pstatement.setDate(2, dateEnd);
				    
				    results=pstatement.executeQuery();
	    
			        
				    while(results.next())
				    {
				    	  System.out.println(results.getString("first_name"));
				    }
			        
		        
	    	} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				 connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
