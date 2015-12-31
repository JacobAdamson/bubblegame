
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class mainGame {

	public enum state {
		PLAYING,LOST,MENU,PAUSE,QUIT,WON,RESTART
	}
	
	
	Level lscape;
	LandscapeDisplay display;
	state myState;

	
	public mainGame(){
		int xDim = 1000;
		int yDim = 1000;
		
		Level myLevel = new dynamicLevel(xDim,yDim);
		//it would be nice if the display didn't require an landscape object upon instantiation
		//levels should take place within a landscape that has its own dimensions
		this.lscape = myLevel;
		display = new LandscapeDisplay(myLevel,1);
		
		display.addKeyListener(myLevel.userBubble);
		display.addKeyListener(new mainGameListener());
	}
	
	public mainGame(Level l){
		Level myLevel = l;
		//it would be nice if the display didn't require an landscape object upon instantiation
		//levels should take place within a landscape that has its own dimensions
		this.lscape = myLevel;
		display = new LandscapeDisplay(myLevel,1);
		
		display.addKeyListener(myLevel.userBubble);
		display.addKeyListener(new mainGameListener());
		
		
	}
	/**
	* TODO add different levels
	* TODO improve graphics 
	* TODO scale size of drawing with the size of center bubble
	* TODO add menus
	* TODO set win conditions
	* TODO add multiple types of gameplay
	* TODO release a complied game
	* TODO add density
	* TODO change graphics to add multiple graphic modes
	**/
	
	public void run()
	{
		this.myState = state.PLAYING;
		//main state machine
		while (this.myState != state.QUIT){
			if(this.myState == state.PLAYING){
				lscape.advance();
				display.primaryUpdate();
				
				if(lscape.hasWon())
					this.myState = state.WON;
				if(lscape.hasLost())
					this.myState = state.LOST;
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(this.myState == state.PAUSE){
				System.out.println("Paused");
				//TODO add a pause screen
			}
			else if(this.myState == state.WON){
				System.out.println("You WON!");
				lscape.userBubble.stopAccelerationChange();
				lscape.advance();
				display.primaryUpdate();
				display.removeKeyListener(lscape.userBubble);
			}
			else if (this.myState == state.LOST){
				System.out.println("You LOST!");
				lscape.userBubble.stopAccelerationChange();
				lscape.advance();
				display.primaryUpdate();
				display.removeKeyListener(lscape.userBubble);
			}
			else if(this.myState == state.RESTART){
				lscape = new dynamicLevel(1000,1000);
				display.changeLandscape(lscape);
				this.myState = state.PLAYING;
			}
			else if(this.myState == state.MENU){
				//if(display. == true)
					//display
				
			}
			
		}

	}
	
	private class mainGameListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar() == 'p'){
				//toggles playstate
				if(myState == state.PLAYING)
					myState = state.PAUSE;
				else if(myState == state.PAUSE)
					myState = state.PLAYING;
			}
			else if(e.getKeyChar() == 'q'){
				display.dispose();
			}
			else if(e.getKeyChar() == 'r'){
				myState = state.RESTART;
			}
			
			if(e.getKeyChar() == ' '){
				display.centerLandscape();
			}
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	public static void main(String args[]){
		
		mainGame game = new mainGame(new spiralGravityLevel(1000,1000));
		game.run();
		
	}
	
	
}
