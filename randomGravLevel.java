import java.util.Random;


public class randomGravLevel extends Level {
	public randomGravLevel(double width, double height){
		super(width,height);
		int numAgents = 4000;
		Random r = new Random();
		double xDir;
		double yDir;
		double size;
		gravBubble myBubble;
		this.userBubble = new controlledBubble(0,0,this,.0000005);
		userBubble.stopUpdate();
		this.addAgent(userBubble);
		for(int i = 0 ; i<numAgents; i++){
			size = .0000005;
			xDir = r.nextDouble()-.5;
			yDir = r.nextDouble()-.5;
			double x = r.nextDouble()*100;
			double y = r.nextDouble()*100;
			myBubble = new gravBubble(x,y,this,size,xDir,yDir);
			this.addAgent(myBubble);
		}
		
			
			
			
		
		
		
		
		
	}
}
