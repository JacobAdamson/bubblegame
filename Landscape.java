import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
public class Landscape {
	
	LinkedList<Cell> llCell;
	
	double width;
	double height;
	
	/**
	 * @param rows number of rows
	 * @param cols number of columns
	 */
	public Landscape(double width, double height){
		llCell = new LinkedList<Cell>();
		this.width = width;
		this.height = height;
	}
	
	
	
	
	/**
	 * fills the grid with dead cells
	 */
	public void reset(){
		this.llCell.clear();	
	}
	
	/**
	 * removes the object from the simulation
	 */
	public void remove(Object obj){
		this.llCell.remove(obj);
	}
	
	/**
	 * 
	 * @return integer representing the number of rows
	 */
	public double getWidth(){
		return this.width;
	}
	
	/**
	 * 
	 * @return integer representing the number of columns
	 */
	public double getHeight(){
		return this.height;
	}
	
	public int getRows(){
		return (int)this.height;
	}
	
	public int getCols(){
		return (int)this.width;
	}
	/**
	 * add the cell to the linked list of cells
	 * @param a Cell
	 */
	public void addAgent(Cell a){
		this.llCell.add(a);
	}
	
	public ArrayList<Cell> getAgents(){
		return this.llCell.toArrayList();
	}
	
	public LinkedList<Cell> getLL(){
		return this.llCell;
	}
	
	
	/**
	 * Converts the grid into a string which looks like an board
	 * @return returns a single String which represents the grid. A dead cell is represented by a single space
	 * a live cell is represented by a 0
	 */
	public String toString(){
		String[][] str = new String[this.getCols()][this.getRows()];//creates and initializes a 2D array of Strings
		Cell cell;
		String returned = "";
				
		for(Iterator<Cell> iterator = this.llCell.iterator(); iterator.hasNext(); ){
			cell = iterator.next();
			//System.out.println("Cell Location"+cell.getX()+ "  "+cell.getY());
			if(!( cell.getX() < 0 || cell.getY()<0 || cell.getY() > this.getRows() || cell.getX() > this.getCols()))
				str[cell.getRow()][cell.getCol()] = cell.toString();
		}
		
		returned += "-------------------------------\n";
		for( int x = 0; x < this.getRows(); x++){
			for( int y = 0; y < this.getCols();y++){
				if (str[x][y] == null)
					returned += "□";
				else
					returned += str[x][y];
			}
			returned += "\n";
		}
		returned += "-------------------------------\n";

		return returned;			
	}
	
	/**
	 * @param x0  a double representing x position
	 * @param y0 a double representing y position
	 * @param radius
	 * @return a list of cells within the radius
	 */
	public ArrayList<Cell> getNeighbors( Cell qCell){
		ArrayList<Cell> list = new ArrayList<Cell>();
		for( Cell c: llCell){
			if (qCell.isNeighbor(c))
				list.add(c);
			
		}
		return list;
		
		
	}
	
	/**
	 * Advances the 2D grid by one step.
	 * Each cell is updated according to it's nieghbors. The new cells are put into a new grid. The new grid then replaces the old grid
	 */
	public void advance(){
		ArrayList<Cell> cList = llCell.toShuffledList();
		Cell cell;
		for( int i = 0; i < cList.size(); i++){
			cell = cList.get(i);
			cell.updateState();
		}
		
	}
	
	/**
	 * The Main method
	 * @param argv
	 * @throws InterruptedException
	 */
	public static void main(String argv[]) throws InterruptedException{
		Landscape lscape = new Landscape(20,20);
		Random r = new Random();
		for(int i = 0; i < 20; i++){
			//Cell c = new ClumpingCell(r.nextDouble()*20,r.nextDouble()*20);
			//lscape.addAgent(c);
		}
		for(int i = 0; i<1000; i++){
			Thread.sleep(20);
			System.out.println(lscape);
			lscape.advance();
		}
		
		
		
	}
	
}
