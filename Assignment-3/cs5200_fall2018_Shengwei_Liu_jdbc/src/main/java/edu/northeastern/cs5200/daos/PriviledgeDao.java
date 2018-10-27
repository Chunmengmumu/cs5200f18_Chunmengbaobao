package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.Connection_A3;

public class PriviledgeDao implements PriviledgeImpl{
	private static PriviledgeDao instance=null;
    private PriviledgeDao(){} 
    
    public static PriviledgeDao getInstance() {
    	if(instance==null)
    	{
    		instance = new PriviledgeDao() {};
    	}
    	return instance;
    }
    
    private final String INSERT_WEBSITEPRIVILEDGE="INSERT INTO websitepriviledge (developer_id,website_id,priviledge) VALUE (?,?,?)";    
    private final String INSERT_PAGEPRIVILEDGE="INSERT INTO pagepriviledge (developer_id,page_id,priviledge) VALUE (?,?,?)";  
    private final String DELETE_WEBSITEPRIVILEDGE="DELETE FROM websitepriviledge WHERE developer_id=? AND website_id=? AND priviledge=?"; 
    private final String DELETE_PAGEPRIVILEDGE="DELETE FROM pagepriviledge WHERE developer_id=? AND page_id=? AND priviledge=?";;
    
    
    public int assignWebsitePriviledge(int developerId,int websiteId,String priviledge) {
    	PreparedStatement pstatement=null;
    	int result=0;
      	try {
  			pstatement=Connection_A3.getConnection().prepareStatement(INSERT_WEBSITEPRIVILEDGE);
      		
  			pstatement.setInt(1, developerId);
  			pstatement.setInt(2, websiteId);
  			pstatement.setString(3, priviledge);
  			
  			result=pstatement.executeUpdate();
  	        
      	} catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
      	try {
			Connection_A3.closeConnection(Connection_A3.getConnection());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	return result;
	}
    public int assignPagePriviledge(int developerId,int pageId,String priviledge) {
    	PreparedStatement pstatement=null;
    	int result=0;
      	try {
  			pstatement=Connection_A3.getConnection().prepareStatement(INSERT_PAGEPRIVILEDGE);
      		
  			pstatement.setInt(1, developerId);
  			pstatement.setInt(2, pageId);
  			pstatement.setString(3, priviledge);
  			
  			result=pstatement.executeUpdate();
  	        
      	} catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
      	try {
			Connection_A3.closeConnection(Connection_A3.getConnection());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	return result;
	}

    public int deleteWebsitePriviledge(int developerId,int websiteId, String priviledge)
    {   
    	int result=0;
    	PreparedStatement pstatement=null;
    	try {
		    pstatement=Connection_A3.getConnection().prepareStatement(DELETE_WEBSITEPRIVILEDGE);

		    pstatement.setInt(1, developerId);
		    pstatement.setInt(2, websiteId);
  	  	    pstatement.setString(3, priviledge);
  	  	    
		    result=pstatement.executeUpdate();
		    
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			Connection_A3.closeConnection(Connection_A3.getConnection());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
    public int deletePagePriviledge(int developerId,int pageId,String priviledge)
    {   
    	int result=0;
    	PreparedStatement pstatement=null;
    	try {
		    pstatement=Connection_A3.getConnection().prepareStatement(DELETE_PAGEPRIVILEDGE);

		    pstatement.setInt(1, developerId);
		    pstatement.setInt(2, pageId);
  	  	    pstatement.setString(3, priviledge);
  	  	    
		    result=pstatement.executeUpdate();
		    
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			Connection_A3.closeConnection(Connection_A3.getConnection());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
}


