import java.awt.Color;


public class blackHole extends bubble {

	public blackHole(double x, double y, Landscape ls, double size)
	{
		super(x,y,ls);
		this.size = size;
		this.color = Color.BLACK;
	}
	
	public blackHole(double x, double y, Landscape ls, double size, double xspeed, double yspeed)
	{
		super(x,y,ls,size,xspeed, yspeed);
	}
	//this bubble will impact bubbles whether they are larger or small
	//this may not be necessary
	public boolean shouldImpact(bubble myBubble){
		return true;
	}
	//does nothing because it's area will not be adjusted
	public void adjustArea(double area){
		;	
	}
	//the other bubble's size is reduced, this ones is maintained
	public void adjustSizes(bubble myBubble,double reduction){
		System.out.println("adjusting deviously");
		myBubble.size -= reduction;
	}
	
}
