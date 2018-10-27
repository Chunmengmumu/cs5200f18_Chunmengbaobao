package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import edu.northeastern.cs5200.Connection_A3;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Role;

public class RoleDao implements RoleImpl{
    private HashMap<Integer,String> rolesDesigned=new HashMap<Integer,String>();
    private RoleDao() {}
    public void setRolesDesigned(Collection<Role> roles)
    {
    	for(Role role:roles)
    		this.rolesDesigned.put(role.getId(), role.getRole());
    }
    public HashMap<Integer,String> getRolesDesigned()
    {
    	return rolesDesigned;
    }
	private static RoleDao instance=null;
    //private RoleDao(){} 
    
    public static RoleDao getInstance() {
    	if(instance==null)
    	{
    		instance = new RoleDao() {};
    	}
    	return instance;
    }

    
    private final String INSERT_WEBSITEROLE="INSERT INTO websiterole (developer_id,website_id,role) VALUE (?,?,?)";
    
    private final String INSERT_PAGEROLE="INSERT INTO pagerole (developer_id,page_id,role) VALUE (?,?,?)";
    
    private final String FIND_PAGEROLE_BYID="SELECT role FROM pagerole WHERE developer_id=? AND page_id=?";
    
    private final String DELETE_WEBSITEROLE="DELETE FROM websiterole WHERE developer_id=? AND website_id=? AND role=?";
   
    private final String DELETE_PAGEROLE="DELETE FROM pagerole WHERE developer_id=? AND page_id=? AND role=?";;
    
    
    public int assignWebsiteRole(int developerId,int websiteId,int roleId) {
    	PreparedStatement pstatement=null;
    	int result=0;
      	try {
  			pstatement=Connection_A3.getConnection().prepareStatement(INSERT_WEBSITEROLE);
      		
  			pstatement.setInt(1, developerId);
  			pstatement.setInt(2, websiteId);
  			pstatement.setString(3, rolesDesigned.get(roleId));
  			
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
    public  int assignPageRole(int developerId,int pageId,int roleId) {
    	PreparedStatement pstatement=null; 
    	int result=0;
      	try {
  			pstatement=Connection_A3.getConnection().prepareStatement(INSERT_PAGEROLE);
      		
  			pstatement.setInt(1, developerId);
  			pstatement.setInt(2, pageId);
  			pstatement.setString(3, rolesDesigned.get(roleId));
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
    
    public Role findPageroleById(int developerId,int pageId)
    //here, assume one developer for one website can only have one role
    //this function is used in question 4 of Implement Updates 
    {
    	 ResultSet  results=null;
    	 Role role=new Role();
    	 PreparedStatement pstatement=null;
    	    try {
    			pstatement=Connection_A3.getConnection().prepareStatement(FIND_PAGEROLE_BYID);
    			pstatement.setInt(1, developerId);
    			pstatement.setInt(2, pageId);
    				
    		    results=pstatement.executeQuery();
    		        
    		    if (results.next()) {
    			    String role_name=results.getString("role");
    			    role.setRole(role_name);
    		    }
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
    	    return role;
    }
    public int deleteWebsiteRole(int developerId,int websiteId, int roleId)
    {   
    	int result=0;
    	PreparedStatement pstatement=null;
    	try {
		    pstatement=Connection_A3.getConnection().prepareStatement(DELETE_WEBSITEROLE);

		    pstatement.setInt(1, developerId);
		    pstatement.setInt(2, websiteId);
		    pstatement.setString(3, rolesDesigned.get(roleId));
		    
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
    public int deletePageRole(int developerId,int pageId,int roleId)
    {   
    	int result=0;
    	PreparedStatement pstatement=null;
    	try {
		    pstatement=Connection_A3.getConnection().prepareStatement(DELETE_PAGEROLE);

		    pstatement.setInt(1, developerId);
		    pstatement.setInt(2, pageId);
		    pstatement.setString(3, rolesDesigned.get(roleId));
		    
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
