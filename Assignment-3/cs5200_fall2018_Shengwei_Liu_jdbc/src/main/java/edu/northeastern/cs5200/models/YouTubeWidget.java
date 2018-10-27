package edu.northeastern.cs5200.models;

public class YouTubeWidget extends Widget{

	private String url;
    private Boolean shareble;
    private Boolean expandable;
    
    public YouTubeWidget() {
		super();
		// TODO Auto-generated constructor stub
	}
	public YouTubeWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text,
			int order) {
		super(id, name, width, height, cssClass, cssStyle, text, order);
		// TODO Auto-generated constructor stub
	}
	//This constructor is used in WidgetDao
	public YouTubeWidget( String name, int width, int height, String cssClass, String cssStyle, String text,
			int order,String url, Boolean shareble, Boolean expandable) {
		super(name, width, height, cssClass, cssStyle,text, order);
		this.url = url;
		this.shareble = shareble;
		this.expandable = expandable;
	}
    //setters and getters
    public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getShareble() {
		return shareble;
	}
	public void setShareble(Boolean shareble) {
		this.shareble = shareble;
	}
	public Boolean getExpandable() {
		return expandable;
	}
	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}
    
}
