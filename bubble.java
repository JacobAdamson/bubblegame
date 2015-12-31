import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class bubble extends MoveableCell {
	
	Color color;
	//overloaded constructor: default size is 10
	public bubble(double x, double y, Landscape ls)
	{
		super(x,y,ls);
		this.size = 10;	
	}
		
	public bubble(double x, double y, Landscape ls, double size)
	{
		super(x,y,ls);
		this.size = size;
		
	}
	
	public bubble(double x, double y, Landscape ls, double size, double xspeed, double yspeed)
	{
		super(x,y,ls,xspeed, yspeed);
		this.size = size;
		
	}
	
	//all cells are neighbors because the calculations used to see if they are neighbors are the same for use in the update funciton
	public boolean isNeighbor(Cell cell) {
		// TODO Auto-generated method stub
		return cell != this;//this should 
	}

	//reduce the size of the small bubble proportional to the distance they are apart
	//inceases the size of the large bubble proportional to the size lost of the smaller
	public void updateState() {
		super.updateState();
		//TODO: move all first and then reduce size?
		//TODO: reduce time complexity
		ArrayList<Cell> allBubbles = this.lscape.getNeighbors(this);
		for(Cell b:allBubbles)
		{
			bubble myBubble = (bubble) b;		
			//this operation is only done once: by the bubble that is smaller
			//myBubble should increase in size
			//this should decrease in size
			if(shouldImpact(myBubble)){
				// 1. find absolute distance
				
				double dist = findDistance(myBubble);
					
				// 2. subtract the sum of the size from this distance
				double reduction = (myBubble.size + this.size) - dist;
				
	
	
				//check to make sure they have actually collided
				if(reduction > 0){
					//adjust Sizes accordingly
					adjustSizes(myBubble,reduction);
					
					// 5. if the radius would be below 0, remove this
					if (this.size <= 0)
						this.removeSelf();
					}
			}
		}
	}
	
	public boolean shouldImpact(bubble myBubble){
		return (myBubble.size >= this.size);
	}
	
	public double findDistance(bubble myBubble){
		double squaredDist = (this.x - myBubble.x)*(this.x - myBubble.x) + (this.y - myBubble.y)*(this.y - myBubble.y); 
		double dist = Math.sqrt(squaredDist);
		return dist;
	}
	
	public void adjustSizes(bubble myBubble, double reduction){
		// 3. reduce the radius of the smaller bubble by this number
		// 4. increase the area of the large bubble to the area equivenlant to that lost by the smaller bubble
		double areaLost;
		double newSizeOfThis;
		
		newSizeOfThis = this.size - reduction;
		//size is changed after the area lost is calculated
		//calculate the new radius of t cell
		areaLost = Math.PI*this.size*this.size - Math.PI*newSizeOfThis*newSizeOfThis; 
		myBubble.adjustArea(areaLost);
		this.setSize(newSizeOfThis);
	}
	//changes the area of the bubble by the specified area
	//size adjusts accordingly
	public void adjustArea(double area){
		double newArea = this.size*this.size*Math.PI + area;
		this.setSize(Math.sqrt(newArea/Math.PI));	
	}
	
	public void adjustSize(double sizeChange){
		this.size = this.size + sizeChange;
	}
	
	public void setSize(double newSize){
		this.size = newSize;	
	}
	
	public void adjustVelocity(double dx, double dy){
		this.xDir += dx;
		this.yDir += dy;
	}
	
	
	
	
	public void adjustSizesAndVelocity(bubble myBubble, double reduction){
		// 3. reduce the radius of the smaller bubble by this number
		// 4. increase the area of the large bubble to the area equivenlant to that lost by the smaller bubble
		double areaLost;
		double newSizeOfThis;
		double newSizeOfMyBubble;
		double newAreaOfMyBubble;
	
		newSizeOfThis = this.size - reduction;
		
		
		
		//size is changed after the area lost is calculated
		//calculate the new radius of t cell
		areaLost = Math.PI*this.size*this.size - Math.PI*newSizeOfThis*newSizeOfThis; 
		newAreaOfMyBubble = areaLost + Math.PI*myBubble.size*myBubble.size;
		newSizeOfMyBubble = Math.sqrt(newAreaOfMyBubble/Math.PI);
		
		//velocity adjustment of the larger bubble
		double xAdjustofMyBubble;
		double yAdjustofMyBubble;
		
		//area lost multiplied by x velocity divided by the area of both bubbles
		xAdjustofMyBubble = ((areaLost*this.xDir)+(myBubble.size*myBubble.size*Math.PI*myBubble.xDir))/(newAreaOfMyBubble);
		yAdjustofMyBubble = ((areaLost*this.yDir)+(myBubble.size*myBubble.size*Math.PI*myBubble.yDir))/(newAreaOfMyBubble);
		myBubble.xDir = xAdjustofMyBubble;
		myBubble.yDir = yAdjustofMyBubble;
		
		
		//change Sizes
		this.size = newSizeOfThis;
		myBubble.size = newSizeOfMyBubble;
	}
	
	
	//draws the oval centered at current x and y
	//x and y as parameters are offsets, they are where the center of the screen should be 
	//scales
	public void draw(Graphics g, int xOffset, int yOffset, Cell centerCell){
		int endX;
		int endY;
		int endDiameter;
		//scale is a sqrt in order to increase the actual and percieved size of the bubble at the same time
		//that is zoom out, but also make the bubble size on screen bigger
		double scale = Math.sqrt(30/centerCell.size);
		//scales by transforming to center, scaling values, transforming to the center of the screen, and then transforming by the size of the bubble
		endX = (int)(( (this.getX() - centerCell.getX())*scale) + xOffset - this.size*scale);
		endY = (int)(((this.getY() - centerCell.getY())*scale) + yOffset - this.size*scale);
		endDiameter = (int)(this.size*2*scale);
		//g.drawOval(endX,endY,endDiameter,endDiameter);
		g.setColor(getColor());
		g.fillOval(endX,endY,endDiameter,endDiameter);
	}
	

	//draws the oval centered at current x and y
	//x and y as parameters are offsets, they are where the center of the screen should be 
	//scales
	public void draw(Graphics g, int xOffset, int yOffset, double centerX, double centerY, double scale){
		int endX;
		int endY;
		int endDiameter;
		//scale is a sqrt in order to increase the actual and percieved size of the bubble at the same time
		//that is zoom out, but also make the bubble size on screen bigger
		
		//scales by transforming to center, scaling values, transforming to the center of the screen, and then transforming by the size of the bubble
		endX = (int)(( (this.getX() - centerX)*scale) + xOffset - this.size*scale);
		endY = (int)(( (this.getY() - centerY)*scale) + yOffset - this.size*scale);
		
		
		endDiameter = (int)(this.size*2*scale);
		
		
		g.setColor(getColor());
		
		//g.drawOval(endX,endY,endDiameter,endDiameter);
		g.fillOval(endX,endY,endDiameter,endDiameter);
	}
	
	
	public Color getColor(){
		if (this.color != null)
				return color;
		else return Color.BLACK;
	}

	@Override
	public void draw(Graphics g, int x, int y, double scale) {
		int endX;
		int endY;
		int endDiameter;
		
		endX = (int)((x + this.x - this.size)*scale);
		endY = (int)((y + this.y-this.size)*scale);
		endDiameter = (int)((this.size * 2)*scale);
		g.drawOval(endX,endY,endDiameter,endDiameter);
	}

}
