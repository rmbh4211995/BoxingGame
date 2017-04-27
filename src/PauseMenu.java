import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

public class PauseMenu extends MouseAdapter {

	private ObjectHandler handler;

	private boolean resumeButHover = false;
	private boolean menuButHover = false;
	private boolean quitButHover = false;
	private boolean yesButHover = false;
	private boolean noButHover = false;
	private boolean showYesOrNoMes = false;

	private Color jmuGold = new Color(194, 161, 77);

	public PauseMenu(ObjectHandler handler) {
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e){
		int mouseX = e.getX();
		int mouseY = e.getY();

		if(Game.state == State.Pause){
			if(!showYesOrNoMes){
				if(Menu.isOver(mouseX, mouseY, Game.width / 2 - 60, Game.height / 2 - 90, 120, 40)){ //Resume Button
					Game.state = State.Game;
				}
				else if(Menu.isOver(mouseX, mouseY, Game.width / 2 - 60, Game.height / 2 - 30, 120, 40)){ //Menu Button
					Game.state = State.Menu;
					handler.remAll();
				}
				else if(Menu.isOver(mouseX, mouseY, Game.width / 2 - 60, Game.height / 2 + 30, 120, 40)){ //Quit Button
					showYesOrNoMes = true;
				}
			}
			if(Menu.isOver(mouseX, mouseY, Game.width / 2 - 170, Game.height / 2 - 20, 125, 45)){ //Yes Button
				System.exit(0);
				showYesOrNoMes = false;
			}
			else if(Menu.isOver(mouseX, mouseY, Game.width / 2 + 20, Game.height / 2 - 20, 125, 45)){ //No Button
				Game.state = State.Pause;
				showYesOrNoMes = false;
			}
		}
		
		if (Game.state == State.Win) {
		    if (Menu.isOver(mouseX, mouseY, Game.width / 2 - 100, Game.height / 2 - 40, 190, 40)) { // Restart Button
		        HUD.setP1Score(0);
		        HUD.setP2Score(0);
		        HUD.setGameOver(false);
		        Game.state = State.Game;
		    }
		    
		    else if (Menu.isOver(mouseX, mouseY, Game.width / 2 - 100, Game.height / 2 + 10, 190, 40)) { // Main Menu Button
	            Game.state = State.Menu;
	            handler.remAll();
	        }
		    else if (Menu.isOver(mouseX, mouseY, Game.width / 2 - 100, Game.height / 2 + 60, 190, 40)) { // Quit Button
	            System.exit(0);
	        }
		}
	}

	public void mouseMoved(MouseEvent e){
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if(Game.state == State.Pause){
			if(!showYesOrNoMes){
				if(Menu.isOver(mouseX, mouseY, Game.width / 2 - 60, Game.height / 2 - 90, 120, 40)){ //Resume Hover
					resumeButHover = true;
					menuButHover = false;
					quitButHover = false;
				}
				else if(Menu.isOver(mouseX, mouseY, Game.width / 2 - 60, Game.height / 2 - 30, 120, 40)){ //Menu Hover
					menuButHover = true;
					resumeButHover = false;
					quitButHover = false;
				}
				else if(Menu.isOver(mouseX, mouseY,Game.width / 2 - 60, Game.height / 2 + 30, 120, 40)){ //Quit Hover
					quitButHover = true;
					resumeButHover = false;
					menuButHover = false;
				}
				else{
					resumeButHover = false;
					quitButHover = false;
					menuButHover = false;
				}
			}
			else if(Menu.isOver(mouseX, mouseY, Game.width / 2 - 170, Game.height / 2 - 20, 125, 45)){ //Yes Button
				yesButHover = true;
				noButHover = false;
			}
			else if(Menu.isOver(mouseX, mouseY, Game.width / 2 + 20, Game.height / 2 - 20, 125, 45)){ //No Button
				noButHover = true;
				yesButHover = false;
			}
			else{
				yesButHover = false;
				noButHover = false;
			}
		}

	}
	
	public void winMessage(Graphics g) {
	    g.setColor(Color.darkGray);
        g.fillRect(Game.width / 2 - 100, Game.height / 2 - 90, 210, 40);
        g.setColor(jmuGold);
        g.drawRect(Game.width / 2 - 100, Game.height / 2 - 90, 210, 40);
        g.setColor(jmuGold);
        
        if (Game.p1Win) {
            g.drawString("Player1 Wins", Game.width / 2 - 80, Game.height / 2 - 60);    
        }
        
        if (Game.p2Win) {
            g.drawString("Player2 Wins", Game.width / 2 - 80, Game.height / 2 - 60);
        }

        g.setColor(Color.darkGray);
        g.fillRect(Game.width / 2 - 100, Game.height / 2 - 40, 190, 40);
        g.setColor(jmuGold);
        g.drawRect(Game.width / 2 - 100, Game.height / 2 - 40, 190, 40);
        g.setColor(jmuGold);
        g.drawString("Restart", Game.width / 2 - 80, Game.height / 2 - 10);
        
        g.setColor(Color.darkGray);
        g.fillRect(Game.width / 2 - 100, Game.height / 2 + 10, 190, 40);
        g.setColor(jmuGold);
        g.drawRect(Game.width / 2 - 100, Game.height / 2 + 10, 190, 40);
        g.setColor(jmuGold);
        g.drawString("Main Menu", Game.width / 2 - 80, Game.height / 2 + 40);
        
        g.setColor(Color.darkGray);
        g.fillRect(Game.width / 2 - 100, Game.height / 2 + 60, 190, 40);
        g.setColor(jmuGold);
        g.drawRect(Game.width / 2 - 100, Game.height / 2 + 60, 190, 40);
        g.setColor(jmuGold);
        g.drawString("Quit Game", Game.width / 2 - 80, Game.height / 2 + 90);
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 128));
		g.fillRect(0, 0, Game.width, Game.height);
		
		Font font = null;
		try {
			InputStream is = getClass().getResourceAsStream("Fipps.TTF");
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(!showYesOrNoMes){
			// Resume Game Button
			font = font.deriveFont(Font.PLAIN, 17.0f);
			g.setFont(font);
			g.setColor(Color.darkGray);
			g.fillRect(Game.width / 2 - 60, Game.height / 2 - 90, 120, 40);
			if (resumeButHover) {
				g.setColor(Color.gray);
				g.fillRect(Game.width / 2 - 60, Game.height / 2 - 90, 120, 40);
			}
			g.setColor(jmuGold);
			g.drawRect(Game.width / 2 - 60, Game.height / 2 - 90, 120, 40);
			g.drawString("Resume", Game.width / 2 - 50, Game.height / 2 - 60);

			// Main Menu Button
			g.setColor(Color.darkGray);
			g.fillRect(Game.width / 2 - 60, Game.height / 2 - 30, 120, 40);
			if (menuButHover) {
				g.setColor(Color.gray);
				g.fillRect(Game.width / 2 - 60, Game.height / 2 - 30, 120, 40);
			}
			g.setColor(jmuGold);
			g.drawRect(Game.width / 2 - 60, Game.height / 2 - 30, 120, 40);
			g.drawString("Menu", Game.width / 2 - 35, Game.height / 2);

			// Quit Game Button
			g.setColor(Color.darkGray);
			g.fillRect(Game.width / 2 - 60, Game.height / 2 + 30, 120, 40);
			if (quitButHover) {
				g.setColor(Color.gray);
				g.fillRect(Game.width / 2 - 60, Game.height / 2 + 30, 120, 40);
			}
			g.setColor(jmuGold);
			g.drawRect(Game.width / 2 - 60, Game.height / 2 + 30, 120, 40);
			g.drawString("Quit", Game.width / 2 - 30, Game.height / 2 + 60);
		}
		else{
			//Yes or No Message
			g.setColor(jmuGold);
			font = font.deriveFont(Font.PLAIN, 24.0f);
			g.setFont(font);
			//g.setFont(new Font("Fipps", Font.PLAIN, 24));
			g.drawString("Are you sure?", Game.width / 2 - 140, Game.height / 2 - 50);
			
			//Yes Button
			g.setColor(Color.darkGray);
			font = font.deriveFont(Font.PLAIN, 24.0f);
			g.setFont(font);
			//g.setFont(new Font("Fipps", Font.PLAIN, 24));
			g.fillRect(Game.width / 2 - 170, Game.height / 2 - 20, 125, 45);
			if (yesButHover) {
				g.setColor(Color.gray);
				g.fillRect(Game.width / 2 - 170, Game.height / 2 - 20, 125, 45);
			}
			g.setColor(jmuGold);
			g.drawRect(Game.width / 2 - 170, Game.height / 2 - 20, 125, 45);
			g.drawString("Yes", Game.width / 2 - 150, Game.height / 2 + 18);
			
			//No Button
			g.setColor(Color.darkGray);
			font = font.deriveFont(Font.PLAIN, 24.0f);
			g.setFont(font);
			//g.setFont(new Font("Fipps", Font.PLAIN, 24));
			g.fillRect(Game.width / 2 + 20, Game.height / 2 - 20, 125, 45);
			if (noButHover) {
				g.setColor(Color.gray);
				g.fillRect(Game.width / 2 + 20, Game.height / 2 - 20, 125, 45);
			}
			g.setColor(jmuGold);
			g.drawRect(Game.width / 2 + 20, Game.height / 2 - 20, 125, 45);
			g.drawString("No", Game.width / 2 + 58, Game.height / 2 + 18);
			
		}

	}
}
