import java.util.ArrayList;
import java.util.Random;
/**
 * 
 * @author Jacob Adamson
 * 
 * The level generator is an object that returns a list of cells
 * it creates a list of Cells in the specified way
 *
 */
public class levelGenerator {
	Landscape lscape;
	public levelGenerator(Landscape lscape)
	{
		this.lscape = lscape;
	}
	
	//TODO create a level class containing a landscape and a center cell
	//should this extend landscape?
	//this level class should also have its own dimensions that aren't necessarily equal to that of "landscape"
	public ArrayList<bubble> generate(String levelName){
	if (levelName.equals("staticLevel")){
		return staticLevel();
	}
	else if (levelName.equals("dynamicLevel")){
		return dynamicLevel();
	}
	else return null;
		
	}

	public ArrayList<bubble> staticLevel(){
		ArrayList<bubble> cellList = new ArrayList<bubble>();
		Random r = new Random();
		for(int i = 0; i < 100; i++){
			double x = r.nextDouble()*lscape.width*3;
			double y = r.nextDouble()*lscape.height*3;
			double size = r.nextDouble()*30+3;
			cellList.add(new bubble(x,y,lscape,size,0,0));
		}
		return cellList;
	}
	
	public ArrayList<bubble> dynamicLevel(){
		ArrayList<bubble> cellList = new ArrayList<bubble>();
		Random r = new Random();
		for(int i = 0; i < 200; i++){
			double x = r.nextDouble()*lscape.width*3;
			double y = r.nextDouble()*lscape.height*3;
			double xVelocity = r.nextDouble()*.3-.15;
			double yVelocity = r.nextDouble()*.3-.15;
			double size = r.nextDouble()*70+3;
		
			cellList.add(new bubble(x,y,lscape,size,xVelocity,yVelocity));
	}
	return cellList;
	}
	public ArrayList<bubble> dynamicGravityLevel(){
		ArrayList<bubble> cellList = new ArrayList<bubble>();
		Random r = new Random();
		for(int i = 0; i < 200; i++){
			double x = r.nextDouble()*lscape.width*3;
			double y = r.nextDouble()*lscape.height*3;
			double xVelocity = r.nextDouble()*.3-.15;
			double yVelocity = r.nextDouble()*.3-.15;
			double size = r.nextDouble()*70+3;
		
			cellList.add(new gravBubble(x,y,lscape,size,0,0));
	}
	return cellList;
	}
	
	
	public ArrayList<bubble> spiralGravityLevel(){
			ArrayList<bubble> cellList = new ArrayList<bubble>();
			int numAgents = 1000;
			bubble myBubble;
			double dist;
			double angle;
			double x;
			double y;
			double xDir;
			double yDir;
			double force;
			double size;
			Random r = new Random();
			
			for(int i = 0; i < numAgents*3; i++){
				dist =  Math.pow(r.nextDouble(),1.5)*150; //more cells are located near the center
				angle = r.nextDouble()*2*Math.PI; //the angle is random
				x = dist*Math.cos(angle); //get the x position relative to the center
				y = dist*Math.sin(angle); //get the y position relative to the center
				size = .0001;
				angle += Math.PI/2;
				//TODO adjust so that the force increases as cells are farther out
				if (dist>5) // cells close to the center, where gravity is weak, don't have an initial velocity
					force = .2;
				else 
					force = 0;
				xDir = force*Math.cos(angle); //the force is tangent to the angle from the center
				yDir = force*Math.sin(angle);
				myBubble = new gravBubble((int)x,(int)y,lscape,size,xDir,yDir);
				cellList.add(myBubble);
			}
			return cellList;	
	}
}

