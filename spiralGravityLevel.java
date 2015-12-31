import java.util.ArrayList;
import java.util.Random;


public class spiralGravityLevel extends Level {
	public spiralGravityLevel(double width, double height) {
		super(width, height);
		
		
		int numAgents = 400;
		bubble myBubble;
		double dist;
		double angle;
		double x;
		double y;
		double xDir;
		double yDir;
		double force;
		double size;
		double attraction  = 5;
		
		double gravitationalForce;
		Random r = new Random();
		
		this.userBubble = new controlledBubble(100,100,this,1,-.5,-.5);
		userBubble.stopUpdate();
		this.addAgent(userBubble);
		this.addAgent(new Attractor(0,0,this,1,attraction));
		for(int i = 0; i < numAgents; i++){
			dist =  r.nextDouble()*200; //more cells are located near the center
			angle = r.nextDouble()*2*Math.PI; //the angle is random
			x = dist*Math.cos(angle); //get the x position relative to the center
			y = dist*Math.sin(angle); //get the y position relative to the center
			size = r.nextDouble()+.01;
			angle += Math.PI/2;
			//TODO adjust so that the force increases as cells are farther out
			gravitationalForce = attraction/(dist*dist);
			double velocity = Math.sqrt(gravitationalForce * dist);
			xDir = velocity*Math.cos(angle); //the force is tangent to the angle from the center
			yDir = velocity*Math.sin(angle);
			myBubble = new bubble((int)x,(int)y,this,size,xDir,yDir);
			this.addAgent(myBubble);
		}
		
	}
	
	public boolean hasWon(){
		return super.hasWon();
	}
}
