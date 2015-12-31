import java.util.ArrayList;


public class Level extends Landscape{

	public Level(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}
	
	Landscape ls;
	controlledBubble userBubble;
	
	//returns true if this bubble is the largest
	public boolean hasWon(){
		for(Cell b:this.mainList){
			if(b.size > this.userBubble.size)
				return false;
		}
		return true;
	}
	
	public boolean hasLost(){
		for(Cell b:this.mainList){
			if(b.size < this.userBubble.size)
				return false;
		}
		return true;
	}
	
	public void advance(){
		super.advance();
		for(Cell c:mainList){
			if(c.size<=0)
				mainList.remove(c);
			
		}
	}
	public void setCenter(Cell c) 
	{
		this.userBubble = (controlledBubble)c;
	}
	
	public Cell getCenterCell()
	{
		return this.userBubble;
	}
	
	
	
	
}
