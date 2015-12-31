import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class controlledBubble extends bubble implements KeyListener {

	private boolean acceleratingLeft;
	private boolean acceleratingRight;
	private boolean acceleratingUp;
	private boolean acceleratingDown;
	private final double rateOfAcceleration = .01;
	
	private boolean shouldUpdate = true;
	
	public controlledBubble(double x, double y, Landscape ls) {
		super(x, y, ls);
	}
	
	public controlledBubble(double x, double y, Landscape ls, double size)
	{
		super(x,y,ls,size);
		
	}
	
	public controlledBubble(double x, double y, Landscape ls, double size, double xspeed, double yspeed)
	{
		super(x,y,ls,size, xspeed, yspeed);
	}
	
	public void stopUpdate(){
		shouldUpdate = false;
		
	}
	//public void setAcceleration(double ){}
	public void stopAccelerationChange(){
		acceleratingLeft = false;
		acceleratingRight = false;
		acceleratingUp = false;
		acceleratingDown = false;
	}
	
	public void updateState()
	{
		if(acceleratingLeft)
			this.xDir -= rateOfAcceleration;
		if(acceleratingRight)
			this.xDir += rateOfAcceleration;
		if(acceleratingUp)
			this.yDir -= rateOfAcceleration;
		if(acceleratingDown)
			this.yDir += rateOfAcceleration;
		
		if(shouldUpdate)
			super.updateState();
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	System.out.println("Key Printed");	
	}

	@Override
	//sets the boolean variables for the direction acceleration indicated by keypress to true
	public void keyPressed(KeyEvent e) {
		System.out.println("keyPressed");
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			acceleratingDown = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			acceleratingUp = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			acceleratingLeft = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			acceleratingRight = true;
		}
	}

	@Override
	//sets the boolean variables for the direction acceleration indicated by keypress to false
	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			acceleratingDown = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			acceleratingUp = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			acceleratingLeft = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			acceleratingRight = false;
		}
	}

}
