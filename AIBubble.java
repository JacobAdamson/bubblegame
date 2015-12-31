import java.util.ArrayList;


public class AIBubble extends bubble {

	public AIBubble(double x, double y, Landscape ls){
		super(x,y,ls);
	}
	
	public void updateState(){
		super.updateState();
		//TODO change this so the neighbor list is sent to the update state function
		//TODO change the arraylist to a bubble list
		ArrayList<Cell> allBubbles = this.lscape.getNeighbors(this);
		bubble closestGettable = findClosestGettable(allBubbles);
		changeDirectionTowards(closestGettable);
		
	}
	
	public void changeDirectionTowards(	bubble closestGettable){
		
		
		
	}
	
	public bubble findClosestGettable(ArrayList<Cell> list){
		bubble returned = null;
		double closestDist = Double.MAX_VALUE;
		double curDist;
		for(Cell c:list){
			//TODO make is gettable a method in the bubble class?
			bubble b = (bubble)c;
			if(isGettable(b)){
				curDist = findDistance(b);
				if(curDist < closestDist){
					closestDist = curDist;
					returned = b;	
				}
			}
		}
		return returned;
	}
	
	public boolean isGettable(bubble b){
		if(!(b instanceof Attractor)&&!(b instanceof blackHole)){
			if(b.size < this.size)
				return true;
			
		}
		return false;
	}
	

}
