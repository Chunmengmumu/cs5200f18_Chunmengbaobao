package edu.northeastern.cs5200.daos;

public interface PriviledgeImpl {
	//Implement
	int assignWebsitePriviledge(int developerId,int websiteId,String priviledge);
    //inserts into table websitepriviledge a record that assigns a developer whose id is developerId, the priviledge with
    //priviledge name, to the website with websiteId
    
    int assignPagePriviledge(int developerId,int pageId,String priviledge);
    int deleteWebsitePriviledge(int developerId,int websiteId, String priviledge);
    int deletePagePriviledge(int developerId,int pageId,String priviledge);

}
