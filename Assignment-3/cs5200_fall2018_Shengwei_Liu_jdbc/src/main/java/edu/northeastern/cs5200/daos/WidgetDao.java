package edu.northeastern.cs5200.daos;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Connection_A3;
import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class WidgetDao implements WidgetImpl{
	private static WidgetDao instance=null;
    private WidgetDao(){} 
    
    public static WidgetDao getInstance() {
    	if(instance==null)
    	{
    		instance = new WidgetDao() {};
    	}
    	return instance;
    }
    
    private Statement  statement=null;
    private ResultSet  results=null;
    
    private final String INSERT_WIDGET="INSERT INTO widget (id,name,width,height,cssClass,cssStyle,text,`order`,page_id) "
    		             +"VALUE (?,?,?,?,?,?,?,?,?)";
    private final String INSERT_WIDGET_HEADING="INSERT INTO widget (name,width,height,cssClass,cssStyle,text,`order`,heading_size, dtype,page_id) "
                  +"VALUE (?,?,?,?,?,?,?,?,?,?)";
    private final String INSERT_WIDGET_HTML="INSERT INTO widget (name,width,height,cssClass,cssStyle,text,`order`,html,dtype,page_id) "
    		      +"VALUE (?,?,?,?,?,?,?,?,?,?)";
    private final String INSERT_WIDGET_IMAGE="INSERT INTO widget (name,width,height,cssClass,cssStyle,text,`order`,src,dtype,page_id) "
                  +"VALUE (?,?,?,?,?,?,?,?,?,?)";
    private final String INSERT_WIDGET_YOUTUBE="INSERT INTO widget (name,width,height,cssClass,cssStyle,text,`order`,youtube_url,`youtube_shareble`,`youtube_expandable`,dtype,page_id) "
                  +"VALUE (?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private final String FIND_ALL_WIDGETS="SELECT * FROM widget";
    
    private final String FIND_WIDGET_BYID="SELECT * FROM widget WHERE id=?";
    
    private final String FIND_WIDGETS_FORPAGE="SELECT * FROM widget WHERE page_id=?";
    
    private final String UPDATE_WIDGET="UPDATE widget set name=?,width=?,height=?,cssClass=?,cssStyle=?,text=?,`order`=? WHERE id=?";
    private final String UPDATE_WIDGET_HEADING="UPDATE widget set name=?,width=?,height=?,cssClass=?,cssStyle=?,text=?,`order`=?,heading_size=?,dtype=? WHERE id=?";
    private final String UPDATE_WIDGET_HTML="UPDATE widget set name=?,width=?,height=?,cssClass=?,cssStyle=?,text=?,`order`=?,html=?,dtype=? WHERE id=?";
    private final String UPDATE_WIDGET_IMAGE="UPDATE widget set name=?,width=?,height=?,cssClass=?,cssStyle=?,text=?,`order`=?,src=?,dtype=? WHERE id=? ";
    private final String UPDATE_WIDGET_YOUTUBE="UPDATE widget set name=?,width=?,height=?,cssClass=?,cssStyle=?,text=?,`order`=?,"
     +"youtube_url=?,`youtube_shareble`=?,`youtube_expandable`=?,dtype=? WHERE id=? ";
    
    
    private final String DELETE_WIDGET="DELETE FROM widget WHERE id=?";
     				
    
    public void createWidgetForPage(int pageId,Widget widget) {
    	PreparedStatement pstatement=null;   
      	try {
      		int result=0; 
  	        if(widget instanceof HeadingWidget)
  	        {
  	      	    pstatement=Connection_A3.getConnection().prepareStatement(INSERT_WIDGET_HEADING);
  	  			pstatement.setString(1,widget.getName());
  	  			if(widget.getWidth()==0)
  	  				pstatement.setNull(2, java.sql.Types.INTEGER);
  	  			else
  	  		        pstatement.setObject(2, widget.getWidth(),JDBCType.INTEGER);
  	  			if(widget.getHeight()==0)
  	  				pstatement.setNull(3, java.sql.Types.INTEGER);
  	  			else
  	  		        pstatement.setObject(3, widget.getHeight(),JDBCType.INTEGER);
  	  			
  	  			pstatement.setString(4,widget.getCssClass());
  	  			pstatement.setString(5, widget.getCssStyle());
  	  			pstatement.setString(6, widget.getText());
  	  			pstatement.setInt(7, widget.getOrder());
  	  		    pstatement.setInt(8, ((HeadingWidget) widget).getSize());
  	  		
  	  		   // In sql table, heading_size has been set as default value 2 if no value
  	  		   if(((HeadingWidget) widget).getSize()==0)
	  				pstatement.setInt(8, 2);
	  			else
	  		        pstatement.setObject(8, ((HeadingWidget) widget).getSize(),JDBCType.INTEGER);
  	  			
  	  			pstatement.setString(9, "heading");
  	  			pstatement.setInt(10, pageId);
  	  		    result=pstatement.executeUpdate();
  	        	
  	        }
  	        else if(widget instanceof HtmlWidget)
  	        {
  	            pstatement=Connection_A3.getConnection().prepareStatement(INSERT_WIDGET_HTML);
   	  			pstatement.setString(1,widget.getName());
   	  		    if(widget.getWidth()==0)
	  				pstatement.setNull(2, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(2, widget.getWidth(),JDBCType.INTEGER);
	  			if(widget.getHeight()==0)
	  				pstatement.setNull(3, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(3, widget.getHeight(),JDBCType.INTEGER);
	  			
   	  			pstatement.setString(4,widget.getCssClass());
   	  			pstatement.setString(5, widget.getCssStyle());
   	  			pstatement.setString(6, widget.getText());
   	  			pstatement.setInt(7, widget.getOrder());
   	  			pstatement.setString(8, ((HtmlWidget) widget).getHtml());
   	  			pstatement.setString(9, "html");
   	  			pstatement.setInt(10, pageId);
   	  		    result=pstatement.executeUpdate();
  	        }
  	        else if(widget instanceof ImageWidget)
  	        { 
  	        	//(name,width,height,cssClass,cssStyle,text,`order`,src,dtype,page_id) 
  	        	pstatement=Connection_A3.getConnection().prepareStatement(INSERT_WIDGET_IMAGE);
   	  			pstatement.setString(1,widget.getName());
   	  		    if(widget.getWidth()==0)
	  				pstatement.setNull(2, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(2, widget.getWidth(),JDBCType.INTEGER);
	  			if(widget.getHeight()==0)
	  				pstatement.setNull(3, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(3, widget.getHeight(),JDBCType.INTEGER);
	  			
   	  		    pstatement.setString(4, widget.getCssClass());
   	  		    pstatement.setString(5, widget.getCssStyle());
	  			pstatement.setString(6, widget.getText());
   	  			pstatement.setInt(7, widget.getOrder());
   	  			pstatement.setString(8, ((ImageWidget) widget).getSrc());
   	  			pstatement.setString(9, "image");
   	  			pstatement.setInt(10, pageId);
   	  		    result=pstatement.executeUpdate();
  	        }
  	        else if(widget instanceof YouTubeWidget)
  	        {
  	        	pstatement=Connection_A3.getConnection().prepareStatement(INSERT_WIDGET_YOUTUBE);
   	  			pstatement.setString(1,widget.getName());
   	  		    if(widget.getWidth()==0)
	  				pstatement.setNull(2, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(2, widget.getWidth(),JDBCType.INTEGER);
	  			if(widget.getHeight()==0)
	  				pstatement.setNull(3, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(3, widget.getHeight(),JDBCType.INTEGER);
   	  		    pstatement.setString(4, widget.getCssClass());
   	  		    pstatement.setString(5, widget.getCssStyle());
	  			pstatement.setString(6, widget.getText());
   	  			pstatement.setInt(7, widget.getOrder());
   	  			pstatement.setString(8, ((YouTubeWidget) widget).getUrl());
   	  			pstatement.setBoolean(9, ((YouTubeWidget) widget).getShareble());
   	  			pstatement.setBoolean(10, ((YouTubeWidget) widget).getExpandable());
   	  			pstatement.setString(11, "youtube");
   	  			pstatement.setInt(12, pageId);
   	  		    result=pstatement.executeUpdate();
  	        }
  	        else
  	        {
  	        	pstatement=Connection_A3.getConnection().prepareStatement(INSERT_WIDGET);
  	        	pstatement.setInt(1,widget.getId());
  	        	pstatement.setString(2,widget.getName());
  	        	if(widget.getWidth()==0)
	  				pstatement.setNull(3, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(3, widget.getWidth(),JDBCType.INTEGER);
	  			if(widget.getHeight()==0)
	  				pstatement.setNull(4, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(4, widget.getHeight(),JDBCType.INTEGER);
   	  		    pstatement.setString(5, widget.getCssClass());
   	  		    pstatement.setString(6, widget.getCssStyle());
	  			pstatement.setString(7, widget.getText());
   	  			pstatement.setInt(8, widget.getOrder());
   	  			pstatement.setInt(9, pageId);
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
    
    public Collection<Widget> findAllWidgets()
    {
    	Statement  statement=null;
    	Collection<Widget> widgets=new ArrayList<Widget>();
    	try {
			statement =Connection_A3.getConnection().createStatement();
			
		    results =statement.executeQuery(FIND_ALL_WIDGETS);
		    while(results.next()) {
		    	int id=results.getInt("id");
		    	String name=results.getString("name");
		    	int    width=results.getInt("width");
		    	int    height=results.getInt("height");
		    	String cssClass=results.getString("cssClass");
		    	String cssStyle=results.getString("cssStyle");
		    	String text=results.getString("text");
		    	int    order=results.getInt("order");
		    	
		    	Widget widget=new Widget(id, name, width, height, cssClass, cssStyle, text,order);
		    	widgets.add(widget);
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
    	return widgets;
    }

    public Widget findWidgetById(int widgetId)
    {
        Widget widget=new Widget();
    	PreparedStatement pstatement=null;
    	try {
			pstatement=Connection_A3.getConnection().prepareStatement(FIND_WIDGET_BYID);
    		pstatement.setInt(1, widgetId);
			
	        results=pstatement.executeQuery();
	        
	        if (results.next()) {
	        	int id=results.getInt("id");
		    	String name=results.getString("name");
		    	int    width=results.getInt("width");
		    	int    height=results.getInt("height");
		    	String cssClass=results.getString("cssClass");
		    	String cssStyle=results.getString("cssStyle");
		    	String text=results.getString("text");
		    	int    order=results.getInt("order");
		    	
		    	widget.setId(id);
		    	widget.setName(name);
		    	widget.setWidth(width);
		    	widget.setHeight(height);
		    	widget.setCssClass(cssClass);
		    	widget.setCssStyle(cssStyle);
		    	widget.setText(text);
		    	widget.setOrder(order);
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
    	return widget;
    }
    
    public Collection<Widget> findWidgetsForPage(int pageId)
    {
    	PreparedStatement pstatement=null;
    	Collection<Widget> widgets=new ArrayList<Widget>();
    	try {
			pstatement=Connection_A3.getConnection().prepareStatement(FIND_WIDGETS_FORPAGE);
			pstatement.setInt(1, pageId);
			
	        results=pstatement.executeQuery();
	        while(results.next()) {
	        	int id=results.getInt("id");
		    	String name=results.getString("name");
		    	int    width=results.getInt("width");
		    	int    height=results.getInt("height");
		    	String cssClass=results.getString("cssClass");
		    	String cssStyle=results.getString("cssStyle");
		    	String text=results.getString("text");
		    	int    order=results.getInt("order");
		    	
		    	Widget widget=new Widget(id, name, width, height, cssClass, cssStyle, text,order);
		    	widgets.add(widget);
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
    	return widgets;
    }

    public int updateWidget(int widgetId,Widget widget)
    {   
    	int result=0; 
    	PreparedStatement pstatement=null;
    	try {
  	        if(widget instanceof HeadingWidget)
  	        {
  	        	//UPDATE_WIDGET_HEADING="UPDATE widget set name=?,cssClass=?,cssStyle=?,text=?,`order`=?,dtype=?,page_id=? WHERE id=?";
  	      	    pstatement=Connection_A3.getConnection().prepareStatement(UPDATE_WIDGET_HEADING);
  	  			pstatement.setString(1,widget.getName());
  	  		   if(widget.getWidth()==0)
	  				pstatement.setNull(2, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(2, widget.getWidth(),JDBCType.INTEGER);
	  			if(widget.getHeight()==0)
	  				pstatement.setNull(3, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(3, widget.getHeight(),JDBCType.INTEGER);
  	  			pstatement.setString(4,widget.getCssClass());
  	  			pstatement.setString(5, widget.getCssStyle());
  	  			pstatement.setString(6, widget.getText());
  	  			pstatement.setInt(7, widget.getOrder());
  	  		    pstatement.setObject(8, ((HeadingWidget) widget).getSize(),JDBCType.INTEGER);
  	  			pstatement.setString(9, "heading");
  	  			pstatement.setInt(10, widgetId);
  	  		    result=pstatement.executeUpdate();
  	        	
  	        }
  	        else if(widget instanceof HtmlWidget)
  	        {
  	      	    pstatement=Connection_A3.getConnection().prepareStatement(UPDATE_WIDGET_HTML);
  	  			pstatement.setString(1,widget.getName());
  	  		  if(widget.getWidth()==0)
	  				pstatement.setNull(2, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(2, widget.getWidth(),JDBCType.INTEGER);
	  			if(widget.getHeight()==0)
	  				pstatement.setNull(3, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(3, widget.getHeight(),JDBCType.INTEGER);
  	  			pstatement.setString(4,widget.getCssClass());
  	  			pstatement.setString(5, widget.getCssStyle());
  	  			pstatement.setString(6, widget.getText());
  	  			pstatement.setInt(7, widget.getOrder());
  	  		    pstatement.setString(8,((HtmlWidget) widget).getHtml());
  	  			pstatement.setString(9, "html");
  	  			pstatement.setInt(10, widgetId);
  	  		    result=pstatement.executeUpdate();
  	        }
  	        else if(widget instanceof ImageWidget)
  	        { 
  	        	//(name,width,height,cssClass,cssStyle,text,`order`,src,dtype,page_id) 
  	        	pstatement=Connection_A3.getConnection().prepareStatement(UPDATE_WIDGET_IMAGE);
   	  			pstatement.setString(1,widget.getName());
   	  			if(widget.getWidth()==0)
	  				pstatement.setNull(2, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(2, widget.getWidth(),JDBCType.INTEGER);
	  			if(widget.getHeight()==0)
	  				pstatement.setNull(3, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(3, widget.getHeight(),JDBCType.INTEGER);
   	  		    pstatement.setString(4, widget.getCssClass());
   	  		    pstatement.setString(5, widget.getCssStyle());
	  			pstatement.setString(6, widget.getText());
   	  			pstatement.setInt(7, widget.getOrder());
   	  			pstatement.setString(8, ((ImageWidget) widget).getSrc());
   	  			pstatement.setString(9, "image");
   	  			pstatement.setInt(10, widgetId);
   	  		    result=pstatement.executeUpdate();
  	        }
  	        else if(widget instanceof YouTubeWidget)
  	        {
  	        	pstatement=Connection_A3.getConnection().prepareStatement(UPDATE_WIDGET_YOUTUBE);
   	  			pstatement.setString(1,widget.getName());
   	  		    if(widget.getWidth()==0)
	  				pstatement.setNull(2, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(2, widget.getWidth(),JDBCType.INTEGER);
	  			if(widget.getHeight()==0)
	  				pstatement.setNull(3, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(3, widget.getHeight(),JDBCType.INTEGER);
   	  		    pstatement.setString(4, widget.getCssClass());
   	  		    pstatement.setString(5, widget.getCssStyle());
	  			pstatement.setString(6, widget.getText());
   	  			pstatement.setInt(7, widget.getOrder());
   	  			pstatement.setString(8, ((YouTubeWidget) widget).getUrl());
   	  		    pstatement.setObject(9, ((YouTubeWidget) widget).getShareble(),JDBCType.BOOLEAN);
   	            pstatement.setObject(10, ((YouTubeWidget) widget).getExpandable(),JDBCType.BOOLEAN);
   	  			pstatement.setString(11, "youtube");
   	  			pstatement.setInt(12, widgetId);
   	  		    result=pstatement.executeUpdate();
  	        }
  	        else
  	        {
  	        	pstatement=Connection_A3.getConnection().prepareStatement(UPDATE_WIDGET);
  	        	pstatement.setString(1,widget.getName());
  	        	if(widget.getWidth()==0)
	  				pstatement.setNull(2, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(2, widget.getWidth(),JDBCType.INTEGER);
	  			if(widget.getHeight()==0)
	  				pstatement.setNull(3, java.sql.Types.INTEGER);
	  			else
	  		        pstatement.setObject(3, widget.getHeight(),JDBCType.INTEGER);
   	  		    pstatement.setString(4, widget.getCssClass());
   	  		    pstatement.setString(5, widget.getCssStyle());
	  			pstatement.setString(6, widget.getText());
   	  			pstatement.setInt(7, widget.getOrder());
   	  			pstatement.setInt(8, widgetId);
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
    
    public int deleteWidget(int widgetId)
    {   
    	int result=0;
    	PreparedStatement pstatement=null;
    	try {
    		pstatement =Connection_A3.getConnection().prepareStatement(DELETE_WIDGET);
		    pstatement.setInt(1,widgetId);
		    result=pstatement.executeUpdate();
		    pstatement=null;   
		    
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
