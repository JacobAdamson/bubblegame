import java.util.ArrayList;
import java.util.Random;


public class dynamicLevel extends boundedLevel {
	
	public dynamicLevel(double width, double height) {
		super(width, height);
		
		this.userBubble = new controlledBubble(25,25,this,20);
		this.addAgent(userBubble);
		
		Random r = new Random();
		for(int i = 0; i < 10; i++){
			double x = r.nextDouble()*this.width;
			double y = r.nextDouble()*this.height;
			double xVelocity = r.nextDouble()*.1-.05;
			double yVelocity = r.nextDouble()*.1-.05;
			double size = r.nextDouble()*r.nextDouble()*40+3;
		
			this.addAgent(new bubble(x,y,this,size,xVelocity,yVelocity));
		}
	}
	
	public boolean hasWon(){
		return super.hasWon();
	}

}
