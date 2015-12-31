import java.util.Random;


public class testLevel extends Level {

	public testLevel() {
		super(1000,1000);
		// TODO Auto-generated constructor stub
		this.userBubble = new controlledBubble(0,0,this,10);
		this.addAgent(new bubble(30,10,this,10));
		this.addAgent(this.userBubble);
		//Random r = new Random();
		/*for(int i = 0; i < 1000; i++){
			double x = r.nextDouble()*this.width;
			double y = r.nextDouble()*this.height;
			double xVelocity = r.nextDouble()*.5-.25;
			double yVelocity = r.nextDouble()*.5-.25;
			double size = r.nextDouble()*r.nextDouble()*40+3;
		
			this.addAgent(new bubble(x,y,this,size,xVelocity,yVelocity));
		}*/
		
	}

	public boolean hasWon(){
		
		return false;
		
	}
	
	public boolean hasLost(){
		
		return false;
		
	}
}
