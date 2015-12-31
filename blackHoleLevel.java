
public class blackHoleLevel extends Level {

	public blackHoleLevel() {
		super(1000,1000);
		// TODO Auto-generated constructor stub
		this.userBubble = new controlledBubble(0,0,this,10);
		this.addAgent(new blackHole(0,200,this,5));
		this.addAgent(this.userBubble);
	}

	public boolean hasWon(){
		
		return false;
		
	}
}
