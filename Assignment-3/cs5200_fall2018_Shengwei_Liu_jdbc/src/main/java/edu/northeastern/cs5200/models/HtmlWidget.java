package edu.northeastern.cs5200.models;

public class HtmlWidget extends Widget{

    private String html;
    
    public HtmlWidget() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HtmlWidget(String name, String cssClass, String cssStyle, String text,
			int order,String html) {
		super(name,cssClass, cssStyle, text, order);
		this.html=html;
	}
	//this constructor is used in WidgetDao
	public HtmlWidget(String name, String cssClass, String cssStyle, String text,
			int order) {
		super(name,cssClass, cssStyle, text, order);
	}
	public HtmlWidget(String name, int width,int height,String cssClass, String cssStyle, String text,
			int order) {
		super(name,width,height,cssClass, cssStyle, text, order);
	}
	public HtmlWidget(String html) {
		super();
		this.html = html;
	}
	//setters and getters

	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}

}
