package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;

import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Phone;


public class DeveloperDao implements DeveloperImpl{
	
	    private static DeveloperDao instance=null;
	    private DeveloperDao(){} 
	    
	    public static DeveloperDao getInstance() {
	    	if(instance==null)
	    	{
	    		instance = new DeveloperDao() {};
	    	}
	    	return instance;
	    }
	    
	     
	    private final String connectionString="jdbc:mysql://cs5200-fall2018-shengweiliu.co1xraymzewt.us-east-2.rds.amazonaws.com:3306/cs5200_fall2018_Shengwei_Liu_jdbc";
	    private final String username_db="ShengweiLiu";
	    private final String password_db="kingisme";
	    
	    edu.northeastern.cs5200.Connection_A3 connect;
	    
	    private Connection connection=null;
	    private Statement  statement=null;
	    private ResultSet  results=null;
	    
	    private final String INSERT_PERSON="INSERT INTO person (id,last_name,first_name,username,password,email,dob) VALUE (?,?,?,?,?,?,?)";
	    private final String INSERT_DEVELOPER="INSERT INTO developer (person_id,developer_key) VALUE (?,?)";
	    private final String INSERT_ADDRESS="INSERT INTO address (street1,street2,city,state,zip,`primary`,person_id) VALUE (?,?,?,?,?,?,?)";
	    private final String INSERT_PHONE="INSERT INTO phones (phone,`primary`,person_id) VALUE (?,?,?)";
	    
	    private final String FIND_ALL_DEVELOPERS="SELECT * FROM person JOIN developer ON person.id=developer.person_id";
	    
	    private final String FIND_DEVELOPER_BYID="SELECT * FROM person JOIN developer ON person.id=developer.person_id AND person.id=?";
	    private final String FIND_DEVELOPER_BYID_PHONES="SELECT * FROM phones WHERE person_id=?";
	    private final String FIND_DEVELOPER_BYID_ADDRESSES="SELECT * FROM address WHERE person_id=?";
	    
	    
	    private final String FIND_DEVELOPERS_BYUSERNAME="SELECT * FROM person JOIN developer ON person.id=developer.person_id AND person.username=?";
	    
	    private final String FIND_DEVELOPERS_BYCREDENTIALS="SELECT * FROM person JOIN developer ON person.id=developer.person_id AND person.username=? AND person.password=?";
	    
	    private final String UPDATE_PERSON="UPDATE person set id=?,last_name=?,first_name=?,username=?,password=?, email=?,dob=? WHERE id=?";
	    private final String UPDATE_DEVELOPER="UPDATE developer set person_id=?,developer_key=? WHERE person_id=?";
	    private final String UPDATE_PHONE="UPDATE phones set phone=? WHERE person_id =? AND `primary`= ? ";
	    private final String UPDATE_ADDRESS="UPDATE address set street1=?,street2=?,city=?,state=?,zip=? WHERE person_id=? AND `primary`=?";
	    
	    
	    private final String DELETE_PERSON="DELETE FROM person WHERE id=?";
	    private final String DELETE_DEVELOPER="DELETE FROM developer WHERE person_id=?";
	    private final String DELETE_PHONE="DELETE FROM phones WHERE person_id =?";
	    private final String DELETE_ADDRESS="DELETE FROM address WHERE person_id=?";
	    private final String DELETE_ADDRESSOFDEVELOPER="DELETE FROM address WHERE person_id=? AND `primary`=?";
	    
	    public void createDeveloper(Developer developer)
	    { 
	        PreparedStatement pstatement=null;
	        int result=0;    
	    	try {
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection(connectionString, username_db, password_db);
				pstatement =connection.prepareStatement(INSERT_PERSON);
				
				pstatement.setInt(1, developer.getId());
				pstatement.setString(2, developer.getLast_name());
				pstatement.setString(3, developer.getFirst_name());
				pstatement.setString(4, developer.getUsername());
				pstatement.setString(5, developer.getPassword());
				pstatement.setString(6, developer.getEmail());
				pstatement.setDate(7, developer.getDob());
		        result=pstatement.executeUpdate();
		        
		        pstatement=null;
		        pstatement =connection.prepareStatement(INSERT_DEVELOPER);
				pstatement.setInt(1, developer.getId());
				pstatement.setString(2, developer.getDeveloper_key());
		        result=pstatement.executeUpdate();
		        
		        Collection<Phone> phones=developer.getPhones();
		        //developer.getPhones().size()
		        //for(int i=0;i<2;i++)
		        if(!phones.isEmpty())
		        {
			        for(Phone phone:phones)
			        {
			        	pstatement=null;
			 	        pstatement =connection.prepareStatement(INSERT_PHONE);
			 			pstatement.setString(1, phone.getPhone());
			 			pstatement.setBoolean(2, phone.getPrimary());
			 			pstatement.setInt(3, developer.getId());
			 			result=pstatement.executeUpdate();
			        }
		        }

		        Collection<Address> addresses=developer.getAddresses();
		        if(!addresses.isEmpty())
		        {
		        	for(Address address:addresses)
			        {
			        	pstatement=null;
			 	        pstatement =connection.prepareStatement(INSERT_ADDRESS);
			 			pstatement.setString(1, address.getStreet1());
			 			pstatement.setString(2, address.getStreet2());
			 			pstatement.setString(3, address.getCity());	
			 			pstatement.setString(4, address.getState());	
			 			pstatement.setString(5, address.getZip());
			 			pstatement.setBoolean(6, address.getPrimary());	
			 			pstatement.setInt(7, developer.getId());
			 			result=pstatement.executeUpdate();
			 			
			        }
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
	    public Collection<Developer> findAllDevelopers()
	    {
	    	results=null;
	    	statement=null;
	    	//Collection<Developer> developers=new Collection<Developer>();
	    	Collection<Developer> developers=new ArrayList<Developer>();
	    	try {
				Class.forName("com.mysql.jdbc.Driver");
				
				connection=DriverManager.getConnection(connectionString, username_db, password_db);
				statement =connection.createStatement();
				//statement =connect.getConnection().createStatement();
			    results =statement.executeQuery(FIND_ALL_DEVELOPERS);
			    while(results.next()) {
			    	int id=results.getInt("person_id");
			    	String last_name=results.getString("last_name");
			    	String first_name=results.getString("last_name");
			    	String username=results.getString("username");
			    	String password=results.getString("password");
			    	String email=results.getString("email");
			    	Date   dob=results.getDate("dob");
			    	String developer_key=results.getString("developer_key");
			    	Developer developer=new Developer(id, developer_key,first_name, last_name,username,
			    	password,email, dob);
			    	developers.add(developer);
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
	    	return developers;
	    }

	    public Developer findDeveloperById(int developerId)
	    // Here, the returned developer includes address and phone
	    {
	    	Developer developer=new Developer();
	    	PreparedStatement pstatement=null;
	    	results=null;
	    	try {
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection(connectionString, username_db, password_db);
				pstatement =connection.prepareStatement(FIND_DEVELOPER_BYID);
				pstatement.setInt(1, developerId);
				
		        results=pstatement.executeQuery();
		        if (results.next()) {
		        	int id=results.getInt("id");
			    	String last_name=results.getString("last_name");
			    	String first_name=results.getString("last_name");
			    	String username=results.getString("username");
			    	String password=results.getString("password");
			    	String email=results.getString("email");
			    	Date   dob=results.getDate("dob");
			    	String developer_key=results.getString("developer_key");
			    	
			    	developer.setId(id);
			    	developer.setLast_name(last_name);
			    	developer.setFirst_name(first_name);
			    	developer.setUsername(username);
			        developer.setPassword(password);
			        developer.setEmail(email);
			        developer.setDob(dob);
			        developer.setDeveloper_key(developer_key);
		        }
		        results=null;
				pstatement = null;
				pstatement =connection.prepareStatement(FIND_DEVELOPER_BYID_PHONES);
				pstatement.setInt(1, developerId);
		        results=pstatement.executeQuery();
		        
		        Collection<Phone> phones=new ArrayList<Phone>();
		        while (results.next()) {
			    	String phone_number=results.getString("phone");
			    	Boolean primary=results.getBoolean("primary");
			    	Phone phone=new Phone(phone_number,primary);
			    	phones.add(phone);
		        }
		        developer.setPhones(phones);
		        
		        results=null;
		    	pstatement = null;
				pstatement =connection.prepareStatement(FIND_DEVELOPER_BYID_ADDRESSES);
				pstatement.setInt(1, developerId);
		        results=pstatement.executeQuery();
		        
		        Collection<Address> addresses=new ArrayList<Address>();
		        while (results.next()) {
			    	String street1=results.getString("street1");
			    	String street2=results.getString("street2");
			    	String city=results.getString("city");
			    	String state=results.getString("state");
			    	String zip=results.getString("zip");
			    	Boolean primary=results.getBoolean("primary");
			    	Address address=new Address(street1,street2,city,state,zip,primary);
			    	addresses.add(address);
		        }
		    	developer.setAddresses(addresses);
		        
		    	
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
	    	return developer;
	    }

	    public Developer findDeveloperByUsername(String username)
	    {
	    	Developer developer=new Developer();
	    	PreparedStatement pstatement=null;
	    	results=null;
	    	try {
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection(connectionString, username_db, password_db);
				pstatement =connection.prepareStatement(FIND_DEVELOPERS_BYUSERNAME);
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
			    	String developer_key=results.getString("developer_key");
			    	
			    	developer.setId(id);
			    	developer.setLast_name(last_name);
			    	developer.setFirst_name(first_name);
			    	developer.setUsername(usernameTemp);
			        developer.setPassword(password);
			        developer.setEmail(email);
			        developer.setDob(dob);
			        developer.setDeveloper_key(developer_key);
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
	    	return developer;
	    }

	    public Developer findDeveloperByCredentials(String username,String password)
	    {
	    	Developer developer=new Developer();
	    	PreparedStatement pstatement=null;
	    	results=null;
	    	try {
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection(connectionString, username_db, password_db);
				pstatement =connection.prepareStatement(FIND_DEVELOPERS_BYCREDENTIALS);
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
			    	String developer_key=results.getString("developer_key");
			    	
			    	developer.setId(id);
			    	developer.setLast_name(last_name);
			    	developer.setFirst_name(first_name);
			    	developer.setUsername(usernameTemp);
			        developer.setPassword(passwordTemp);
			        developer.setEmail(email);
			        developer.setDob(dob);
			        developer.setDeveloper_key(developer_key);
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
	    	return developer;
	    }

	    
	    public int updateDeveloper(int developerId,Developer developer)
	    {   
	    	int result=0;
	    	PreparedStatement pstatement=null;
	    	//Collection<Developer> developers=new Collection<Developer>();
	    	try {
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection(connectionString, username_db, password_db);
				
				pstatement =connection.prepareStatement(UPDATE_PERSON);
			    pstatement.setInt(1, developer.getId());
			    pstatement.setString(2, developer.getLast_name());
			    pstatement.setString(3, developer.getFirst_name());
			    pstatement.setString(4, developer.getUsername());
			    pstatement.setString(5, developer.getPassword());
			    pstatement.setString(6, developer.getEmail());
			    pstatement.setDate(7, developer.getDob());
			    pstatement.setInt(8, developerId);
			    result=pstatement.executeUpdate();
			    
			    pstatement=null;
				pstatement =connection.prepareStatement(UPDATE_DEVELOPER);
			    pstatement.setInt(1, developerId);
			    pstatement.setString(2, developer.getDeveloper_key());
			    pstatement.setInt(3, developerId);
			    result=pstatement.executeUpdate();
			    
	             
		        Collection<Phone> phones=developer.getPhones();
		        if(phones.isEmpty())
		        {
				    pstatement=null;
					pstatement =connection.prepareStatement(DELETE_PHONE);
				    pstatement.setInt(1, developerId);
				    result=pstatement.executeUpdate();
		        }
		        else 
		        {
		        	//Here， we need to assume the number of phone keep being unchanged
			        for(Phone phone:phones)
			        {
			        	pstatement=null;
			 	        pstatement =connection.prepareStatement(UPDATE_PHONE);
			 			pstatement.setString(1, phone.getPhone());
			 			pstatement.setInt(2, developer.getId());
			 			pstatement.setBoolean(3, phone.getPrimary());
			 			result=pstatement.executeUpdate();
			        }
		        }
	 			
			    Collection<Address> addresses=developer.getAddresses();
			    if(addresses.isEmpty())
			    {
			    	 pstatement=null;
					 pstatement =connection.prepareStatement(DELETE_ADDRESS);
					 pstatement.setInt(1, developerId);
					 result=pstatement.executeUpdate();	    
			    }
			    else 
			    {
			    	for(Address address:addresses)
			        {
			        	pstatement=null;
			 	        pstatement =connection.prepareStatement(UPDATE_ADDRESS);
			 			pstatement.setString(1, address.getStreet1());
			 			pstatement.setString(2, address.getStreet2());
			 			pstatement.setString(3, address.getCity());	
			 			pstatement.setString(4, address.getState());	
			 			pstatement.setString(5, address.getZip());
			 			pstatement.setInt(6, developer.getId());
			 			pstatement.setBoolean(7,address.getPrimary());
			 			result=pstatement.executeUpdate();
			        }
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
	    	return result;
	    }
	    
	    public int deleteDeveloper(int developerId)
	    {   
	    	int result=0;
	    	PreparedStatement pstatement=null;
	    	//Collection<Developer> developers=new Collection<Developer>();
	    	try {
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection(connectionString, username_db, password_db);
				
				pstatement =connection.prepareStatement(DELETE_PERSON);
			    pstatement.setInt(1, developerId);
			    result=pstatement.executeUpdate();
			    
			    pstatement=null;
				pstatement =connection.prepareStatement(DELETE_DEVELOPER);
			    pstatement.setInt(1, developerId);
			    result=pstatement.executeUpdate();

			    pstatement=null;
				pstatement =connection.prepareStatement(DELETE_PHONE);
			    pstatement.setInt(1, developerId);
			    result=pstatement.executeUpdate();
			    
			    pstatement=null;
				pstatement =connection.prepareStatement(DELETE_ADDRESS);
			    pstatement.setInt(1, developerId);
			    result=pstatement.executeUpdate();	    
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
	    	return result;
	    }
	    public int deleteAddressofDeveloper(int developerId,Boolean primary)
	    {   
	    	int result=0;
	    	PreparedStatement pstatement=null;
	    	//Collection<Developer> developers=new Collection<Developer>();
	    	try {
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection(connectionString, username_db, password_db);
				
				pstatement =connection.prepareStatement(DELETE_ADDRESSOFDEVELOPER);
			    pstatement.setInt(1, developerId);
			    pstatement.setBoolean(2, primary);
			    
			    result=pstatement.executeUpdate();
			   
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
	    	return result;
	    }
	   
}
