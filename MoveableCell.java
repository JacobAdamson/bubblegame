/**
 * The moveable cell, ahh to be alive and in motion
 */
import java.awt.Graphics;
import java.util.ArrayList;


public abstract class MoveableCell extends Cell {
	protected double xDir;
	protected double yDir;
	protected double maxSpeed;
	
	/**
	 * constructs a cell with no inital direction
	 * @param x
	 * @param y
	 * @param ls
	 */
	public MoveableCell(double x, double y, Landscape ls){
		super(x,y,ls);
		this.xDir = 0;//thats unnecessary
		this.yDir = 0;
	}
	
	/**
	 * constructs a Moveable cell with an additional direction
	 * @param x
	 * @param y
	 * @param ls
	 * @param xDir
	 * @param yDir
	 */
	public MoveableCell(double x, double y, Landscape ls, double xDir, double yDir){
		super(x,y,ls);
		this.xDir = xDir;
		this.yDir = yDir;
	}

	/**
	 * moves the cell according to it's direction
	 */
	public void updateState() {
		this.x += this.xDir;
		this.y += this.yDir;
	}
	
}
