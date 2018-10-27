package edu.northeastern.cs5200.daos;

//import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.Connection_A3;

public class UserDao implements UserImpl{
  private static UserDao instance=null;
  private UserDao(){} 
  
  public static UserDao getInstance() {
  	if(instance==null)
  	{
  		instance = new UserDao() {};
  	}
  	return instance;
  }
  

  private ResultSet  results=null;
  
  private final String INSERT_PERSON="INSERT INTO person (id,last_name,first_name,username,password,email,dob) VALUE (?,?,?,?,?,?,?)";
  private final String INSERT_USER="INSERT INTO user (person_id,user_key) VALUE (?,?)";
  private final String INSERT_ADDRESS="INSERT INTO address (street1,street2,city,state,zip,`primary`,person_id) VALUE (?,?,?,?,?,?,?)";
  private final String INSERT_PHONE="INSERT INTO phones (phone,`primary`,person_id) VALUE (?,?,?)";
  
  private final String FIND_ALL_USERS="SELECT * FROM person JOIN user ON person.id=user.person_id";
  
  private final String FIND_USERS_BYID="SELECT * FROM person JOIN user ON person.id=user.person_id AND person.id=?";
  
  private final String FIND_USERS_BYUSERNAME="SELECT * FROM person JOIN user ON person.id=user.person_id AND person.username=?";
  
  private final String FIND_USERS_BYCREDENTIALS="SELECT * FROM person JOIN user ON person.id=user.person_id AND person.username=? AND person.password=?";
  
  private final String UPDATE_PERSON="UPDATE person set id=?,last_name=?,first_name=?,username=?,password=?, email=?,dob=? WHERE id=?";
  private final String UPDATE_USER="UPDATE user set person_id=?,user_key=? WHERE person_id=?";
  private final String UPDATE_PHONE="UPDATE phones set phone=? WHERE person_id =? AND `primary`= ? ";
  private final String UPDATE_ADDRESS="UPDATE address set street1=?,street2=?,city=?,state=?,zip=? WHERE person_id=? AND `primary`=?";

  
  private final String DELETE_PERSON="DELETE FROM person WHERE id=?";
  private final String DELETE_USER="DELETE FROM user WHERE person_id=?";
  private final String DELETE_PHONE="DELETE FROM phones WHERE person_id =?";
  private final String DELETE_ADDRESS="DELETE FROM address WHERE person_id=?";
  
  
  public void createUser(User user) {
  	  PreparedStatement pstatement=null;
        int result=0;    
    	try {
			//Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection(connectionString, username_db, password_db);
			//pstatement =connection.prepareStatement(INSERT_PERSON);
    		//edu.northeastern.cs5200.Connection connection1=new Connection();
    		
			pstatement=Connection_A3.getConnection().prepareStatement(INSERT_PERSON);
    		
			pstatement.setInt(1, user.getId());
			pstatement.setString(2, user.getLast_name());
			pstatement.setString(3, user.getFirst_name());
			pstatement.setString(4, user.getUsername());
			pstatement.setString(5, user.getPassword());
			pstatement.setString(6, user.getEmail());
			pstatement.setDate(7, user.getDob());
	        result=pstatement.executeUpdate();
	        
	        pstatement=null;
	        //pstatement =connection.prepareStatement(INSERT_DEVELOPER);
	        pstatement =Connection_A3.getConnection().prepareStatement(INSERT_USER);
	        
			pstatement.setInt(1, user.getId());
			pstatement.setString(2, user.getUser_key());
	        result=pstatement.executeUpdate();
	        
	        Collection<Address> addresses=user.getAddresses();
	        for(Address address:addresses)
	        {
	        	pstatement=null;
	 	        //pstatement =connection.prepareStatement(INSERT_ADDRESS);
	 	        pstatement =Connection_A3.getConnection().prepareStatement(INSERT_ADDRESS);
	 			pstatement.setString(1, address.getStreet1());
	 			pstatement.setString(2, address.getStreet2());
	 			pstatement.setString(3, address.getCity());	
	 			pstatement.setString(4, address.getState());	
	 			pstatement.setString(5, address.getZip());
	 			pstatement.setBoolean(6, address.getPrimary());	
	 			pstatement.setInt(7, user.getId());
	 			result=pstatement.executeUpdate();
	 			
	        }
	        Collection<Phone> phones=user.getPhones();
	        for(Phone phone:phones)
	        {
	        	pstatement=null;
	 	        //pstatement =connection.prepareStatement(INSERT_PHONE);
	        	pstatement =Connection_A3.getConnection().prepareStatement(INSERT_PHONE);
	 			pstatement.setString(1, phone.getPhone());
	 			pstatement.setBoolean(2, phone.getPrimary());
	 			pstatement.setInt(3, user.getId());
	 			
	 			result=pstatement.executeUpdate();
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
	}
  public 	Collection<User> findAllUsers()
  {
  	Statement  statement=null;
  	Collection<User> users=new ArrayList<User>();
  	try {
			statement =Connection_A3.getConnection().createStatement();
			
		    results =statement.executeQuery(FIND_ALL_USERS);
		    while(results.next()) {
		    	int id=results.getInt("person_id");
		    	String last_name=results.getString("last_name");
		    	String first_name=results.getString("last_name");
		    	String username=results.getString("username");
		    	String password=results.getString("password");
		    	String email=results.getString("email");
		    	Date   dob=results.getDate("dob");
		    	String user_key=results.getString("user_key");
		    	User user=new User(id, user_key,first_name, last_name,username,
		    	password,email, dob);
		    	
		    	users.add(user);
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
  	return users;
  }

  public User findUserById(int userId)
  {
  	User user=new User();
  	PreparedStatement pstatement=null;
  	try {
			//Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection(connectionString, username_db, password_db);
			//pstatement =connection.prepareStatement(FIND_DEVELOPERS_BYID);
			pstatement=Connection_A3.getConnection().prepareStatement(FIND_USERS_BYID);
			
			pstatement.setInt(1, userId);
			
	        results=pstatement.executeQuery();
	        if (results.next()) {
	        	int id=results.getInt("id");
		    	String last_name=results.getString("last_name");
		    	String first_name=results.getString("last_name");
		    	String username=results.getString("username");
		    	String password=results.getString("password");
		    	String email=results.getString("email");
		    	Date   dob=results.getDate("dob");
		    	String user_key=results.getString("user_key");
		    	
		    	
		    	user.setId(id);
		    	user.setLast_name(last_name);
		    	user.setFirst_name(first_name);
		    	user.setUsername(username);
		        user.setPassword(password);
		        user.setEmail(email);
		        user.setDob(dob);
		        user.setUser_key(user_key);

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
  	return user;
  }

  public User findUserByUsername(String username)
  {
  	User user=new User();
  	PreparedStatement pstatement=null;
  	try {
			//Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection(connectionString, username_db, password_db);
			//pstatement =connection.prepareStatement(FIND_DEVELOPERS_BYUSERNAME);
			pstatement=Connection_A3.getConnection().prepareStatement(FIND_USERS_BYUSERNAME);
			pstatement.setString(1, username);
			
	        results=pstatement.executeQuery();
	        if (results.next()) {
	        	int id=results.getInt("id");
		    	String last_name=results.getString("last_name");
		    	String first_name=results.getString("last_name");
		    	String usernameTemp=results.getString("username");
		    	String password=results.getString("password");
		    	String email=results.getString("email");
		    	Date   dob=results.getDate("dob");
		    	String user_key=results.getString("user_key");
		    	
		    	user.setId(id);
		    	user.setLast_name(last_name);
		    	user.setFirst_name(first_name);
		    	user.setUsername(usernameTemp);
		        user.setPassword(password);
		        user.setEmail(email);
		        user.setDob(dob);
		        user.setUser_key(user_key);
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
  	return user;
  }

  public User findUserByCredentials(String username, String password)
  {
  	User user=new User();
  	PreparedStatement pstatement=null;
  	try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection(connectionString, username_db, password_db);
			//pstatement =connection.prepareStatement(FIND_DEVELOPERS_BYCREDENTIALS);
			pstatement=Connection_A3.getConnection().prepareStatement(FIND_USERS_BYCREDENTIALS);
			pstatement.setString(1, username);
			pstatement.setString(2, password);
			
	        results=pstatement.executeQuery();
	        if (results.next()) {
	        	int id=results.getInt("id");
		    	String last_name=results.getString("last_name");
		    	String first_name=results.getString("last_name");
		    	String usernameTemp=results.getString("username");
		    	String passwordTemp=results.getString("password");
		    	String email=results.getString("email");
		    	Date   dob=results.getDate("dob");
		    	String user_key=results.getString("user_key");
		    	
		    	user.setId(id);
		    	user.setLast_name(last_name);
		    	user.setFirst_name(first_name);
		    	user.setUsername(usernameTemp);
		        user.setPassword(passwordTemp);
		        user.setEmail(email);
		        user.setDob(dob);
		        user.setUser_key(user_key);
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
  	return user;
  }

  
  public int updateUser(int userId, User user)
  {   
  	int result=0;
  	PreparedStatement pstatement=null;
  	//Collection<Developer> developers=new Collection<Developer>();
  	try {
			//Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection(connectionString, username_db, password_db);
			
			//pstatement =connection.prepareStatement(UPDATE_PERSON);
		    pstatement=Connection_A3.getConnection().prepareStatement(UPDATE_PERSON);
		    pstatement.setInt(1, user.getId());
		    pstatement.setString(2, user.getLast_name());
		    pstatement.setString(3, user.getFirst_name());
		    pstatement.setString(4, user.getUsername());
		    pstatement.setString(5, user.getPassword());
		    pstatement.setString(6, user.getEmail());
		    pstatement.setDate(7, user.getDob());
		    pstatement.setInt(8, userId);
		    result=pstatement.executeUpdate();
		    
		    pstatement=null;
			//pstatement =connection.prepareStatement(UPDATE_DEVELOPER);
			pstatement =Connection_A3.getConnection().prepareStatement(UPDATE_USER);
		    pstatement.setInt(1, userId);
		    pstatement.setString(2, user.getUser_key());
		    pstatement.setInt(3, userId);
		    result=pstatement.executeUpdate();

	        Collection<Phone> phones=user.getPhones();
	        for(Phone phone:phones)
	        {
	        	pstatement=null;
	 	       //pstatement =connection.prepareStatement(UPDATE_PHONE);
	 	        pstatement =Connection_A3.getConnection().prepareStatement(UPDATE_PHONE);
	 			pstatement.setString(1, phone.getPhone());
	 			pstatement.setInt(2, user.getId());
	 			pstatement.setBoolean(3, phone.getPrimary());
	 			result=pstatement.executeUpdate();
	        }
	        
		    Collection<Address> addresses=user.getAddresses();
	        for(Address address:addresses)
	        {
	        	pstatement=null;
	 	        //pstatement =connection.prepareStatement(UPDATE_ADDRESS);
	 	        pstatement =Connection_A3.getConnection().prepareStatement(UPDATE_ADDRESS);
	 			pstatement.setString(1, address.getStreet1());
	 			pstatement.setString(2, address.getStreet2());
	 			pstatement.setString(3, address.getCity());	
	 			pstatement.setString(4, address.getState());	
	 			pstatement.setString(5, address.getZip());
	 			pstatement.setInt(6, user.getId());
	 			pstatement.setBoolean(7,address.getPrimary());
	 			result=pstatement.executeUpdate();
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
  	return result;
  }
  
  public int deleteUser(int userId)
  {   
  	int result=0;
  	PreparedStatement pstatement=null;
  	//Collection<Developer> developers=new Collection<Developer>();
  	try {
			//Class.forName("com.mysql.jdbc.Driver");
			//connection=DriverManager.getConnection(connectionString, username_db, password_db);
			
			//pstatement =connection.prepareStatement(DELETE_PERSON);
  			pstatement =Connection_A3.getConnection().prepareStatement(DELETE_PERSON);
		    pstatement.setInt(1, userId);
		    result=pstatement.executeUpdate();
		    
		    pstatement=null;
			//pstatement =connection.prepareStatement(DELETE_DEVELOPER);
		    pstatement =Connection_A3.getConnection().prepareStatement(DELETE_USER);
		    pstatement.setInt(1, userId);
		    result=pstatement.executeUpdate();

		    pstatement=null;
			//pstatement =connection.prepareStatement(DELETE_PHONE);
			pstatement =Connection_A3.getConnection().prepareStatement(DELETE_PHONE);
;		    pstatement.setInt(1, userId);
		    result=pstatement.executeUpdate();
		    
		    pstatement=null;
			//pstatement =connection.prepareStatement(DELETE_ADDRESS);
			pstatement =Connection_A3.getConnection().prepareStatement(DELETE_ADDRESS);
		    pstatement.setInt(1, userId);
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
