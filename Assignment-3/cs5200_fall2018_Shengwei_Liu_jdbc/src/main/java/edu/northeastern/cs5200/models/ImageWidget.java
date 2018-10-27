package edu.northeastern.cs5200.models;

public class ImageWidget extends Widget{
	
	private String src;

	public ImageWidget() {
		super();
		// TODO Auto-generated constructor stub
	}
	//this constructor is used in WidgetDao
	public ImageWidget(String name, int width, int height, String cssClass, String cssStyle, String text,
			int order,String src) {
		super(name, width, height, cssClass, cssStyle, text, order);
		this.src=src;
		// TODO Auto-generated constructor stub
	}
	public ImageWidget(String src) {
		super();
		this.src = src;
	}
	
    //setters and getters
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}

	

}
