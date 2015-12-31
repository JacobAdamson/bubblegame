import java.awt.Color;
import java.util.ArrayList;


public class Attractor extends bubble {
	
	final double ATTRACTIONFORCE;
	public Attractor(double x, double y, Landscape ls, double size,double strength) {
		super(x, y, ls, size);
		ATTRACTIONFORCE = strength;
		this.color = Color.red;
		// TODO Auto-generated constructor stub
	}

	
	public void updateState(){
		ArrayList<Cell> allBubbles = this.lscape.getNeighbors(this);
		for(Cell b:allBubbles)
		{
			bubble myBubble = (bubble) b;		
			double dist = findDistance(myBubble);
			double xDist = (b.getX() - x);
			double yDist = (b.getY() - y);
			
				double force= ATTRACTIONFORCE/(dist*dist);
				xDir = force * (xDist/(dist)); // the force in the x direction is proportional to that distance to the total distance
				yDir = force * (yDist/(dist)); // the force in the y direction is proportional to that distance to the total distance
			
			myBubble.adjustVelocity(-xDir,-yDir);
		}
		
		super.updateState();
		
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
