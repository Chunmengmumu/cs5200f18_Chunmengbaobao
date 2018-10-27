package edu.northeastern.cs5200.daos;

public interface RoleImpl {
	//Implement
		int assignWebsiteRole(int developerId,int websiteId,int roleId);
	    //inserts into table websiterole a record that assigns a developer whose id is developerId, the role with roleId, to
	    //the website with websiteId
	    
	    int assignPageRole(int developerId,int pageId,int roleId);
	    int deleteWebsiteRole(int developerId,int websiteId, int roleId);
	    int deletePageRole(int developerId,int pageId,int roleId);
	}
