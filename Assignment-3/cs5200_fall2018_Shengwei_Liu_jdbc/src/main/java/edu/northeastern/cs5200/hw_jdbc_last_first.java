package edu.northeastern.cs5200;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import edu.northeastern.cs5200.daos.DeveloperDao;
import edu.northeastern.cs5200.daos.PageDao;
import edu.northeastern.cs5200.daos.PriviledgeDao;
import edu.northeastern.cs5200.daos.ProcedureDao;
import edu.northeastern.cs5200.daos.RoleDao;
import edu.northeastern.cs5200.daos.UserDao;
import edu.northeastern.cs5200.daos.WebsiteDao;
import edu.northeastern.cs5200.daos.WidgetDao;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.models.Priviledge;
import edu.northeastern.cs5200.models.Role;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class hw_jdbc_last_first {
	public  Collection<Role> setDefaultRoles()
	//set the default roles by Role class
	{
		Collection<Role> roles= new ArrayList<Role>();
		Role role1=new Role(1, "owner");
    	Role role2=new Role(2, "admin");
    	Role role3=new Role(3, "writer");
    	Role role4=new Role(4, "editor");
    	Role role5=new Role(5, "reviewer");
    	roles.add(role1);
    	roles.add(role2);
    	roles.add(role3);
    	roles.add(role4);
    	roles.add(role5);
    	return roles;
	}
	public int findIdofRoleinRoles(String rolename,Collection<Role> roles)
	{
		int roleIdinRoles=0;
		for(Role role:roles)
		{
			if(role.getRole().equals(rolename))
			{
				roleIdinRoles=role.getId();
			    break;
			}
		}
		return roleIdinRoles;
	}
	public int findIdofDeveloper(String name)
	//name is the username of developer, and no repeating names
	{
		int result=0;
		DeveloperDao developerdao=DeveloperDao.getInstance();
		Collection<Developer> developers=developerdao.findAllDevelopers();
	
		for(Developer developer:developers){
			String username=developer.getUsername();
			if(username.equals(name))
			{
				result=developer.getId();
				break;
			}
		}
		developers.clear();
		return result;
	}
	public int findIdofWebsite(String name)
	//name is the name of website, and no repeating names
	{
		int result=0;
		WebsiteDao websitedao=WebsiteDao.getInstance();
		Collection<Website> websites=websitedao.findAllWebsites();
		for(Website website:websites){
			if(website.getName().contains(name))
			{
				result=website.getId();
				break;
			}
		}
		websites.clear();
		return result;
	}
	public int findIdofPage(String title)
	//No repeating names
	{
		int result=0;
		PageDao pagedao=PageDao.getInstance();
		Collection<Page> pages=pagedao.findAllPages();
		for(Page page:pages){
			//usually, we use equal(), but here use contains() for the update question 4
			if(page.getTitle().contains(title))
			{
				result=page.getId();
				break;
			}
		}
		pages.clear();
		return result;
	}
	public int findIdofWidget(String name)
	//name is name of widget
	{
		int result=0;
		WidgetDao widgetdao=WidgetDao.getInstance();
		Collection<Widget> widgets=widgetdao.findAllWidgets();
		for(Widget widget:widgets){
			if(widget.getName().equals(name))
			{
				result=widget.getId();
				break;
			}
		}
		widgets.clear();
		return result;
	}

    public void createDeveloperUser() 
	{
		DeveloperDao developerdao=DeveloperDao.getInstance();
    	UserDao      userdao=UserDao.getInstance();
        
    	//Here, I also insert the phone and address table for the following use,
    	// and, use the same phone and address for each person
    	Collection<Phone> phones=new ArrayList<Phone>();
    	Phone phone1=new Phone("6173720940",true);
    	phones.add(phone1);
    	List<Address> addresses=new ArrayList<Address>();
    	Address address1=new Address("115 Forsyth", "Shillman", "Boston", "MA", "02115", true);
    	addresses.add(address1);
    	
    	Developer developer1=new Developer(12, "4321rewq","Alice", "Wonder","alice","alice","alice@wonder.com", null,phones,addresses);
    	Developer developer2=new Developer(23,"5432trew","Bob","Marley","bob","bob","bob@marley.com", null,phones,addresses);
    	Developer developer3=new Developer(34,"6543ytre","Charles","Garcia","charlie","charlie","chuch@garcia.com", null,phones,addresses);
    	developerdao.createDeveloper(developer1);
    	developerdao.createDeveloper(developer2);
    	developerdao.createDeveloper(developer3);
    	
    	User user1=new User(45,"7654fda","Dan","Martin","dan","dan","dan@martin.com",null,phones,addresses);
    	User user2=new User(56,"5678dfgh","Ed","Karaz","ed","ed","ed@kar.com", null,phones,addresses);
	    userdao.createUser(user1);
	    userdao.createUser(user2);
	}
	public void createWebsite() 
	{
		WebsiteDao websitedao=WebsiteDao.getInstance();
    	//Date date=java.time.LocalDate.now();
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
    	Website website1=new Website(123,"Facebook","an online social media and social networking service",date,date,1234234);
    	Website website2=new Website(234,"Twitter","an online news and social networking service",date,date,4321543);
    	Website website3=new Website(345,"Wikipedia","a free online encyclopedia",date,date,3456654);
    	Website website4=new Website(456,"CNN","an American basic cable and satellite television news channel",date,date,6543345);
    	Website website5=new Website(567,"CNET","an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics",date,date,5433455);
    	Website website6=new Website(678,"Gizmodo","a design, technology, science and science fiction website that also writes articles on politics",date,date,4322345);
    	
    	//here, I just use id of owner as the developer_id
    	//first, find different id's of different people
    	int aliceId=findIdofDeveloper("alice");
    	int bobId=findIdofDeveloper("bob");
    	int charlieId=findIdofDeveloper("charlie");
    	
    	
    	websitedao.createWebsiteForDeveloper(aliceId, website1);
    	websitedao.createWebsiteForDeveloper(bobId, website2);
    	websitedao.createWebsiteForDeveloper(charlieId, website3);
    	websitedao.createWebsiteForDeveloper(aliceId, website4);
    	websitedao.createWebsiteForDeveloper(bobId, website5);
    	websitedao.createWebsiteForDeveloper(charlieId, website6);
        
    	//Here, I also insert data to websiterole and websitepriviledge table

    	Collection<Role> roles=setDefaultRoles();
    	RoleDao roledao=RoleDao.getInstance();
    	roledao.setRolesDesigned(roles);
    	int ownerId=findIdofRoleinRoles("owner",roles);
    	int editorId=findIdofRoleinRoles("editor",roles);
    	int adminId=findIdofRoleinRoles("admin",roles);
    	
    	
    	//add role for web id=123
    	roledao.assignWebsiteRole(aliceId, 123, ownerId);
    	roledao.assignWebsiteRole(bobId, 123, editorId);
    	roledao.assignWebsiteRole(charlieId, 123, adminId);
    	//add role for web id=234
    	roledao.assignWebsiteRole(bobId, 234, ownerId);
    	roledao.assignWebsiteRole(charlieId, 234, editorId);
    	roledao.assignWebsiteRole(aliceId, 234, adminId);
    	//add role for web id=345
    	roledao.assignWebsiteRole(charlieId, 345, ownerId);
    	roledao.assignWebsiteRole(aliceId, 345, editorId);
    	roledao.assignWebsiteRole(bobId, 345, adminId);
    	//add role for web id=456
    	roledao.assignWebsiteRole(aliceId, 456, ownerId);
    	roledao.assignWebsiteRole(bobId, 456, editorId);
    	roledao.assignWebsiteRole(charlieId, 456, adminId);
    	//add role for web id=567
    	roledao.assignWebsiteRole(bobId, 567, ownerId);
    	roledao.assignWebsiteRole(charlieId, 567, editorId);
    	roledao.assignWebsiteRole(aliceId, 567, adminId);
    	//add role for web id=678
    	roledao.assignWebsiteRole(charlieId, 678, ownerId);
    	roledao.assignWebsiteRole(aliceId, 678, editorId);
    	roledao.assignWebsiteRole(bobId, 678, adminId);
    	
	}
	public void createPage() 
	{
		PageDao pagedao=PageDao.getInstance();
		Date date_start=Date.valueOf("2018-09-05");
		Date date_due=Date.valueOf("2018-10-26");
    
    	Page page1=new Page(123,"Home","Landing page",date_start,date_due,123434);
        Page page2=new Page(234,"About","Website description",date_start,date_due,234545);
    	Page page3=new Page(345,"Contact","Addresses,phones,and contact info",date_start,date_due,345656);
    	Page page4=new Page(456,"Perferences","Where users can configure their preferences",date_start,date_due,456776);
    	Page page5=new Page(567,"Profile","Users can configure their personal information",date_start,date_due,567878);
    	 	
        pagedao.createPageForWebsite(findIdofWebsite("CNET"), page1);
        pagedao.createPageForWebsite(findIdofWebsite("Gizmodo"), page2);
        pagedao.createPageForWebsite(findIdofWebsite("Wikipedia"), page3);
        pagedao.createPageForWebsite(findIdofWebsite("CNN"), page4);
        pagedao.createPageForWebsite(findIdofWebsite("CNET"), page5);
        
    	//Here, I also insert data to pagerole
    	Collection<Role> roles=setDefaultRoles();
    	RoleDao roledao=RoleDao.getInstance();
    	roledao.setRolesDesigned(roles);
    	int editorId=findIdofRoleinRoles("editor",roles);
    	int reviewerId=findIdofRoleinRoles("reviewer",roles);
    	int writerId=findIdofRoleinRoles("writer",roles);
    	
    	
    	int aliceId=findIdofDeveloper("alice");
    	int bobId=findIdofDeveloper("bob");
    	int charlieId=findIdofDeveloper("charlie");
    	
    	//add role for page id=123
    	roledao.assignPageRole(aliceId,123,editorId);
    	roledao.assignPageRole(bobId,123,reviewerId);
    	roledao.assignPageRole(charlieId,123,writerId);
    	//add role for page id=234    	
    	roledao.assignPageRole(bobId,234,editorId);
    	roledao.assignPageRole(charlieId,234,reviewerId);
    	roledao.assignPageRole(aliceId,234,writerId);
    	//add role for page id=345
    	roledao.assignPageRole(charlieId,345,editorId);
    	roledao.assignPageRole(aliceId,345,reviewerId);
    	roledao.assignPageRole(bobId,345,writerId);
    	//add role for page id=456
    	roledao.assignPageRole(aliceId,456,editorId);
    	roledao.assignPageRole(bobId,456,reviewerId);
    	roledao.assignPageRole(charlieId,456,writerId);
    	//add role for page id=567
    	roledao.assignPageRole(bobId,567,editorId);
    	roledao.assignPageRole(charlieId,567,reviewerId);
    	roledao.assignPageRole(aliceId,567,writerId);   	 	
    	
	}
	public void createWidget() 
	{
		WidgetDao widgetdao=WidgetDao.getInstance();
		
    	HeadingWidget widget1=new HeadingWidget("head123",null, null, "Welcome",0);
        HtmlWidget    widget2=new HtmlWidget("post234", null, null, "<p>Lorem</p>",0);
        HeadingWidget widget3=new HeadingWidget("head345",null, null, "Hi",1);
        HtmlWidget    widget4=new HtmlWidget("intro456", null, null, "<h1>Hi</h1>",2);
        
        ImageWidget   widget5=new ImageWidget("image345", 50, 100, null, null, null,3,"/img/567.png");
        YouTubeWidget widget6=new YouTubeWidget("video456", 400, 300, null,null, null,
    			0,"https://youtu.be/h67VX51QXiQ", false, false);// the last two is shareble, expandable
        
        widgetdao.createWidgetForPage(findIdofPage("Home"), widget1);
        widgetdao.createWidgetForPage(findIdofPage("About"), widget2);
        widgetdao.createWidgetForPage(findIdofPage("Contact"), widget3);
        widgetdao.createWidgetForPage(findIdofPage("Contact"), widget4);
        widgetdao.createWidgetForPage(findIdofPage("Contact"), widget5);
        widgetdao.createWidgetForPage(findIdofPage("Perferences"), widget6);
	}
   
    public void updateDeveloper(String name,Boolean primary,String phone_number)
    //update phone_number based on name and primary
    {
   	    DeveloperDao developerdao=DeveloperDao.getInstance();
        Developer developer=developerdao.findDeveloperByUsername(name);
        Developer developer_full=developerdao.findDeveloperById(developer.getId());
        Collection<Phone> phones=developer_full.getPhones();
        Collection<Phone> phonesNew=new ArrayList<Phone>();
    
        for(Phone phone:phones)
        {  
        	if(phone.getPrimary()==primary)
        	   phone.setPhone(phone_number);
        	phonesNew.add(phone);
        }
        developer_full.setPhones(phonesNew);
        developerdao.updateDeveloper(developer_full.getId(), developer_full);
    }
   	public void updateWidget(String name,int orderNew)
   	{
   		int widgetId=findIdofWidget(name);
   		WidgetDao widgetdao=WidgetDao.getInstance();
   		Widget    widget=widgetdao.findWidgetById(widgetId);
   		int orderOld=widget.getOrder();
   		int orderDifference=orderNew-orderOld;
   		Collection<Widget> widgets=widgetdao.findAllWidgets();
   		int orderTotal=0;
   		//OrderTotal is used to compute no. of widgets with no-zero order
   		for(Widget widgetTemp:widgets)
   		{
   			if(widgetTemp.getOrder()!=0)
   			    orderTotal++;
   		}
   		//the new order=the old order+orderDifference%orderTotal
   		for(Widget widgetTemp:widgets)
   		{
   			if(widgetTemp.getOrder()!=0)
   			{
   				int orderNewTemp=(widgetTemp.getOrder()+orderDifference)%orderTotal;
  			    if(orderNewTemp==0)
   				    widgetTemp.setOrder(orderTotal);
  			    else
  			    	widgetTemp.setOrder(orderNewTemp);
   				widgetdao.updateWidget(widgetTemp.getId(), widgetTemp);
   			}

   		}
   		
   	}
    public void updatePage(String appendString,String name) 
   	//Append appendString in the titles of pages related to website(name)
   	{
   		int webId=findIdofWebsite(name);
   		PageDao pagedao=PageDao.getInstance();
   		Collection<Page> pages=pagedao.findPagesForWebsite(webId);
   		for(Page page:pages)
   		{
   			page.setTitle(appendString+page.getTitle());
   			pagedao.updatePage(page.getId(), page);
   		}
   	}
    public void updateRoles(String webName,String pageTitle,String name1,String name2)
    //swap roles of name1 and name2 based on webName and pageTitle
    //there is no updateRole function, so I use delete and assign
    {
    	RoleDao roledao=RoleDao.getInstance();
    	Collection<Role> roles=setDefaultRoles();
    	roledao.setRolesDesigned(roles);
    	
    	
    	int webId=findIdofWebsite(webName);
    	int pageId=findIdofPage(pageTitle);
    	int developerId1=findIdofDeveloper(name1);
    	int developerId2=findIdofDeveloper(name2);
    	
    	//System.out.println("developerId1: "+developerId1);
    	
    	//Here, role1 and role2 only have role info without id info
    	Role role1=roledao.findPageroleById(developerId1, pageId);
    	Role role2=roledao.findPageroleById(developerId2, pageId);
    	
    			
    	int Idrole1=findIdofRoleinRoles(role1.getRole(),roles);
    	int Idrole2=findIdofRoleinRoles(role2.getRole(),roles);
    	
    	//update pagerole table by deleting and assigning
    	roledao.deletePageRole(developerId1, pageId, Idrole1);
    	roledao.deletePageRole(developerId2, pageId, Idrole2);
    	
    	roledao.assignPageRole(developerId1, pageId, Idrole2);
    	roledao.assignPageRole(developerId2, pageId, Idrole1);
    }
    
    public void deleteAdress(String name,Boolean primary)
    {
    	 //To delete data from address, I create a new function in DeveloperDao:deleteAddressofDeveloper
    	 DeveloperDao developerdao=DeveloperDao.getInstance();
    	 int developerId=findIdofDeveloper(name);
    	 developerdao.deleteAddressofDeveloper(developerId, primary);
    	 
    }
    public void deleteWidget(String pageName)
    //delete the last widget in the page with pageName
    {
    	int pageId=findIdofPage(pageName);
    	WidgetDao widgetdao=WidgetDao.getInstance();
    	Collection<Widget> widgets=widgetdao.findWidgetsForPage(pageId);
    	
    	ArrayList<Integer> arrli = new ArrayList<Integer>();
        for(Widget widget:widgets)
        {
        	arrli.add(widget.getOrder());
        }
        int orderMax=Collections.max(arrli);
        //System.out.println(orderMax);
        for(Widget widget:widgets)
        {
        	if(widget.getOrder()==orderMax)
        		widgetdao.deleteWidget(widget.getId());
        }
        
    	
    }
    public void deletePage(String webName)
    {
    	int webId=findIdofWebsite(webName);
    	PageDao pagedao=PageDao.getInstance();
    	Collection<Page> pages=pagedao.findPagesForWebsite(webId);
    	Collection<Date> dates=new ArrayList<Date>();
    	for(Page page:pages)
    	{
    		dates.add(page.getUpdated());
    	}
    	for(Page page:pages)
    	{
    		if(page.getUpdated().equals(Collections.max(dates)))
    			//System.out.println("max date:"+page.getUpdated());
    		    //System.out.println("max date with id:"+page.getId());
    		    pagedao.deletePage(page.getId());
    	}
    }
    public void deleteWebsite(String name) 
    //delete the data in website,websiterole,websitepriviledge tables related to website with name
    {
    	int webId=findIdofWebsite(name);
    	WebsiteDao websitedao=WebsiteDao.getInstance();
    	websitedao.findWebsiteById(webId);
    	
    	websitedao.deleteWebsite(webId);
    	PageDao    pagedao=PageDao.getInstance();
    	Collection<Page> pages=pagedao.findPagesForWebsite(webId);
    	{
    		for(Page page:pages)
    			pagedao.deletePage(page.getId());
    	}
    	//Because of foreign key-cascade relationship and trigger, 
    	//the relevant data in websiterole,pagerole, website priviledge and pagepriviledge will also be deleted
    }
    
    public static void main(String arg[]){
    	
    	//Insert the data into 
    	hw_jdbc_last_first createInstance=new hw_jdbc_last_first();
    	///*
    	createInstance.createDeveloperUser();
    	createInstance.createWebsite();
    	createInstance.createPage();
    	createInstance.createWidget();
    	//*/
    	
    	//Implement Updates
    	hw_jdbc_last_first updateInstance=new hw_jdbc_last_first();
    	///*
    	updateInstance.updateDeveloper("charlie",true,"333-444-5555");
    	updateInstance.updateWidget("head345", 3);
    	updateInstance.updatePage("CNET-", "CNET");
    	updateInstance.updateRoles("CNET", "Home", "charlie", "bob");
    	//*/
    	
    	//Implement Deletes
    	///*
    	hw_jdbc_last_first deleteInstance=new hw_jdbc_last_first();
    	deleteInstance.deleteAdress("alice",true);
    	deleteInstance.deleteWidget("Contact");
    	deleteInstance.deletePage("Wikipedia");
    	deleteInstance.deleteWebsite("CNET");
    	//*/
    	
    	
    	//Call Procedures by creating ProcedureDao
        String moduleName="Exam";
   	    ProcedureDao proceduredao=ProcedureDao.getInstance();
   	    proceduredao.getUnansweredQuestions(moduleName);
   	    
   	    //
   	    Date dateStart=Date.valueOf("2018-10-01");
   	    Date dateEnd=Date.valueOf("2018-10-07");
   	    proceduredao.endorsedUserForWeek(dateStart, dateEnd);
    	
    }

}