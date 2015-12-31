import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;

/**
 * Displays a Landscape graphically using Swing.  The Landscape
 * can be displayed at any scale factor.
 * @author Jacob Adamson
 */
public class LandscapeDisplay extends JFrame
{
	
	//TODO Create Methods for transfering between all the different modes
	//	those modes are: win, lose, pause, main menu
		protected Landscape scape;
		private LandscapePanel canvas;
		private mainMenu menu;
		private int scale;
	
		/**
		 * Initializes a display window for a Landscape.
		 * @param scape	the Landscape to display
		 * @param scale	controls the relative size of the display
		 */
		public LandscapeDisplay(Landscape scape, int scale)
		{
				// setup the window
				super("Landscape Display");
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				this.scape = scape;
				this.scale = scale;
				
				
				this.menu = new mainMenu(1000,1000);
				this.add(this.menu);
				//this.displayLandscape();
				this.pack();
				this.setVisible(true);
		}
		
		public void displayLandscape(){
			// create a panel in which to display the Landscape
	
			this.remove(this.menu);
			
			this.canvas = new LandscapePanel(
											(int) this.scape.getWidth() * this.scale,
											(int) this.scape.getHeight() * this.scale);
	
			// add the panel to the window, layout, and display
			this.add(this.canvas, BorderLayout.CENTER);
			this.pack();
		}
		
		/**
		 * Saves an image of the display contents to a file.  The supplied
		 * filename should have an extension supported by javax.imageio, e.g.
		 * "png" or "jpg".
		 *
		 * @param filename	the name of the file to save
		 */
		public void saveImage(String filename)
		{
				// get the file extension from the filename
				String ext = filename.substring(
																				filename.lastIndexOf('.') + 1, filename.length());
		
				// create an image buffer to save this component
				Component tosave = this.getRootPane();
				BufferedImage image = new BufferedImage(
																		tosave.getWidth(), 
																		tosave.getHeight(), 
																		BufferedImage.TYPE_INT_RGB);
		
				// paint the component to the image buffer
				Graphics g = image.createGraphics();
				tosave.paint(g);
				g.dispose();
		
				// save the image
				try
						{
								ImageIO.write(image, ext, new File(filename));
						}
				catch (IOException ioe)
						{
								System.out.println(ioe.getMessage());
						}
		}
	
		/**
		 * This inner class provides the panel on which Landscape elements
		 * are drawn.
		 */
		
		/**
		 * changes the landscape of this display
		 * @param l
		 */
		public void changeLandscape(Landscape l){
			this.scape = l;
			//required to make the bubble controlable
			this.addKeyListener(((Level)l).userBubble);
		}
		
		
		
		public void centerLandscape(){
		canvas.cellZoom = true;
		canvas.cellCentered = true;
		}
		
		private class LandscapePanel extends JPanel
		{
			
				double scale;
				double centerX;
				double centerY;
				
				int xOffset = (int)(getWidth()/2);
				int yOffset = (int)(getHeight()/2);
				
				boolean cellCentered = true;
				boolean cellZoom = true;
				
				
				/**
				 * Creates the panel.
				 * @param width		the width of the panel in pixels
				 * @param height		the height of the panel in pixels
				 */
				public LandscapePanel(int width, int height)
				{
						super();
						
						this.setPreferredSize(new Dimension(width, height));
						this.setBackground(Color.white);
						LandscapePanelMouseListener l = new LandscapePanelMouseListener();
						
						this.addMouseListener(l);
						this.addMouseMotionListener(l);
						this.addMouseWheelListener(l);
						
						setCentertoCell();
						setScaletoCell();
				}
		
				/**
				 * Method overridden from JComponent that is responsible for
				 * drawing components on the screen.  The supplied Graphics
				 * object is used to draw.
				 * TODO seperate this class somehow as cell centered and non centered classes
				 * @param g		the Graphics object used for drawing
				 * 
				 */
				public void paintComponent(Graphics g)
				{
		
					
					Graphics offgc;
				    Image offscreen = null;
				    
					// create the offscreen buffer and associated Graphics
				    // the purpose is to keep the previous image on the screen until the new image is ready
				 
				    
				    offscreen = createImage(getWidth(), getHeight()); 
				    offgc = offscreen.getGraphics();
				    
				    // clear the exposed area
				    
				    offgc.setColor(getBackground());
				    offgc.fillRect(0, 0, getWidth(), getHeight());
				    offgc.setColor(getForeground());
				    
				    offgc.clearRect(0,0, getWidth(),getHeight());
				    
					// draw all the agents
				    
					if(cellCentered)
						this.setCentertoCell();
					if(cellZoom)
						this.setScaletoCell();
					
					drawPointCentered(offgc);
					
					
					g.drawImage(offscreen, 0, 0, this);
					
					//got rid of this, not sure of the consequences of that
					//super.paintComponent(g);
 				
				}
				
				//sets the coordinates to the cell center
				public void setCentertoCell(){
					Cell centerCell= ((Level)scape).getCenterCell();
					centerX = centerCell.getX();
					centerY = centerCell.getY();
				}
				
				//sets the scale to the controllable cell
				public void setScaletoCell(){
					Cell centerCell= ((Level)scape).getCenterCell();
					scale = Math.sqrt(30/centerCell.size);
				}
				
				//draws the cells centered on the controllable cell
				public void drawCellCentered(Graphics offgc){
					List<Cell> agents = scape.getAgents();	
					Cell centerCell= ((Level)scape).getCenterCell();
			
					
					//get offsets equal to the position of the first bubble
					int xOffset = (int)(getWidth()/2);
					int yOffset = (int)(getHeight()/2);
					
					
					
					for (Cell agent: agents)
							{
	
								//offset is equal to where the first bubble is
									((bubble)(agent)).draw(offgc,xOffset, yOffset,centerCell);
									
							}

				}
				
				//draws all the cell centered at the current specified place
				public void drawPointCentered(Graphics offgc){
					List<Cell> agents = scape.getAgents();	
					Cell centerCell= ((Level)scape).getCenterCell();
			
					
					//get offsets equal to the position of the first bubble
					xOffset = (int)(getWidth()/2);
					yOffset = (int)(getHeight()/2);
					
					
					
					for (Cell agent: agents)
							{
	
								//offset is equal to where the first bubble is
									((bubble)(agent)).draw(offgc,xOffset, yOffset,centerX,centerY,scale);
									
							}

					
					
					
				}
		
				public void pan(double dx, double dy){
					System.out.println("PANNED");
					centerX += dx/scale;
					centerY += dy/scale;
				}
				
				//takes the number of the "scroll" or whatever is outputed 
				//and converts it to a usable scale differential
				public void zoom(double number){
					//TODO figure out max zoom
					scale += (number)*scale*scale;
				}
				
		}
		
		private class LandscapePanelMouseListener extends MouseAdapter{

			private double prevX;
			private double prevY;
			
			double xDif;
			double yDif;
			
			public void mouseMoved(MouseEvent e){
				System.out.println("MOUSE MOVED");
				prevX = e.getX();
				prevY = e.getY();

			}
			
			public void mouseDragged(MouseEvent e){
				
				System.out.println("MOUSE Dragged");
			
				xDif = prevX - e.getX();
				yDif = prevY - e.getY();
				canvas.pan(xDif, yDif);
				
				prevX = e.getX();
				prevY = e.getY();
				
				canvas.cellCentered = false;

			}
			public void mouseWheelMoved(MouseWheelEvent e){
				System.out.println("Scrolled");
				System.out.println(e.getPreciseWheelRotation());
				canvas.zoom(e.getPreciseWheelRotation());
				canvas.cellZoom = false;
			}
			
			
		}
		
		private class mainMenu extends JPanel
		{
			
			private JButton button;
			/**
			 * Creates the panel.
			 * @param width		the width of the panel in pixels
			 * @param height		the height of the panel in pixels
			 */
			public mainMenu(int width, int height)
			{
					super();
					this.setPreferredSize(new Dimension(width, height));
					this.setBackground(Color.white);
					this.button = new JButton("PLAY");
					this.button.setActionCommand("play");
					this.add(button);
			}
			
		}
		
		public void addMenuListener(ActionListener e){
			if(this.menu != null){
				this.menu.button.addActionListener(e);
			}
		}
		
		public void addThisKeyListener(KeyListener e){
			this.addKeyListener(e);
		}
		public void primaryUpdate() {
				Graphics g = canvas.getGraphics();
				canvas.paintComponent( g );
		}
		
		
	
		public static void main(String[] args) throws InterruptedException
		{
				Landscape scape = new Landscape(400, 400);


				LandscapeDisplay display = new LandscapeDisplay(scape, 2);

				for(int i=0;i<1000;i++) {
						scape.advance();
						display.primaryUpdate();
						Thread.sleep( 250 );
				}
		}
}
