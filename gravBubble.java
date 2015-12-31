import java.util.ArrayList;


public class gravBubble extends bubble {

	public gravBubble(double x, double y, Landscape ls, double size,
			double xspeed, double yspeed) {
		super(x, y, ls, size, xspeed, yspeed);
		// TODO Auto-generated constructor stub
	}
	public void updateState(){
		double xDist;
		double yDist;
		double totalDist;
		double force;
		double gravStrength;
		ArrayList<Cell> neighbors = this.lscape.getNeighbors(this);
		
		for(Cell c : neighbors){
			xDist = (c.getX() - x);
			yDist = (c.getY() - y);
			gravStrength = 5;
			totalDist = (Math.sqrt(xDist*xDist + yDist*yDist));
			
			if (totalDist>2){
				force = gravStrength/(totalDist*totalDist);
				xDir += force * (xDist/(totalDist)); // the force in the x direction is proportional to that distance to the total distance
				yDir += force * (yDist/(totalDist)); // the force in the y direction is proportional to that distance to the total distance
			}
		}
		super.updateState();
	}

}
