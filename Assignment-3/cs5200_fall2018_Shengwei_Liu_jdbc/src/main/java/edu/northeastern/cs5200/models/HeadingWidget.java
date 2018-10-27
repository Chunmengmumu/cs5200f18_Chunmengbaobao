package edu.northeastern.cs5200.models;

public class HeadingWidget extends Widget{

    private int size;
    
    public HeadingWidget() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HeadingWidget(String name, String cssClass, String cssStyle, String text,
			int order,int size) {
		super(name, cssClass, cssStyle, text, order);
		this.size=size;
	}
	//this constructor is used in WidgetDao
	public HeadingWidget(String name, String cssClass, String cssStyle, String text,
			int order) {
		super(name, cssClass, cssStyle, text, order);
	}
	public HeadingWidget(String name, int width,int height,String cssClass, String cssStyle, String text,
			int order) {
		super(name, width,height,cssClass, cssStyle, text, order);
	}
	
    //setters and getters
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
    

}
