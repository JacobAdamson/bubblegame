import java.util.ArrayList;
import java.awt.Graphics;
public abstract class Cell {
	
	//protected so the extended classes can alter the value without the use of methods
	protected double x;
	protected double y;
	protected Landscape lscape;
	protected double radius; //the radius is how big objects that are its neighbors it senses
	protected double size;//the size is how big the thing is
	
	/**
	 * constructor for a cell object
	 * @param x the row this cell is in
	 * @param y the column this cell is in
	 */
	public Cell(double x, double y,Landscape ls){
		this.x = x;
		this.y = y;
		this.lscape = ls;
	}
	
	public abstract boolean isNeighbor( Cell cell );
	public abstract void updateState();
	public abstract void draw(Graphics g, int x, int y, double scale);
	/**
	 * returns the distance from the other cell
	 * @param c another cell
	 * @return double 
	 */
	public double distanceFrom(Cell c){
		double xDist = (c.getX() - x);
		double yDist = (c.getY() - y);
		return (Math.sqrt(xDist*xDist + yDist*yDist));
	}
	
	public void removeSelf(){
		lscape.remove(this);
	}
	/**
	 * returns the x position
	 * @return a double
	 */
	public double getX(){
		return this.x;
	}
	
	/**
	 * returns the y position
	 * @return a double
	 */
	public double getY(){
		return this.y;
	}
	/**
	 * @return the column this cell is in, the integer nearest to x;
	 */
	public int getCol(){
		return (int)this.x;
	}
	/**
	 * 
	 * @return the row this cell is in, the integer nearest to y
	 */
	public int getRow(){
		return (int)this.y;
	}
	
	/**
	 * 
	 * @return a boolean, true if the cell is alive, false if it is dead
	 */
	
	/*
	 *returns the character ■
	 */
	public String toString(){
		return "■";	
	}
	
	public void moveTo(double x, double y){
		this.x = x;
		this.y = y;
	}
	public void shift(double x, double y){
		this.x += x;
		this.y += y;
		
	}
}