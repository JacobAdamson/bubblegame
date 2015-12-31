import java.util.ArrayList;

/**
 * creates a level with bounds 
 * When a cell makes contact with the boundary it's trajectory
 * is altered so it moves back towards the center
 * @author Jacob
 *
 */
public class boundedLevel extends Level {

	public boundedLevel(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}
	
	//the same as the super but the bubbles bounce off the boundary
	public void advance(){
		super.advance();
		
		
		for(Cell myCell : list){
			MoveableCell c = (MoveableCell) myCell;
			if(c.x - c.size < 0){
				c.x = c.size;
				c.xDir = -c.xDir;
			}
			if(c.y - c.size < 0){
				c.y = c.size;
				c.yDir = -c.yDir;
			}
			if(c.x + c.size > this.width){
				c.x = this.width - c.size;
				c.xDir = -c.xDir;
			}
			if(c.y + c.size > this.height){
				c.y = this.height - c.size;
				c.yDir = -c.yDir;
			}	
		}
		
		
	}
}
