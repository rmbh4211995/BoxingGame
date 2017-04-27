import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Menu extends MouseAdapter {

	private Color jmuGold = new Color(194, 161, 77);

	private ObjectHandler handler;
	private boolean gameButHover = false;
	private boolean helpButHover = false;
	private boolean credButHover = false;
	private boolean backButHover = false;

	public Menu(ObjectHandler handler) {
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (Game.state == State.Menu) {
			if (isOver(mouseX, mouseY, Game.width / 2 + 75,
					Game.height / 2 - 70 * 2, 150, 80)) {
				Game.state = State.Game;
				handler.addObject(new Player(Game.width / 6,
						Game.height / 6 + 110, ID.Player, handler));
				handler.addObject(new Player(Game.width / 2 + 100,
						Game.height / 6 + 110, ID.Player2, handler));
				Game.twoPlayerMode = true;
			} else if (isOver(mouseX, mouseY, Game.width / 2 + 75,
					Game.height / 2 - 52, 150, 80)) {
				Game.state = State.Help;
			} else if (isOver(mouseX, mouseY, Game.width / 2 + 75,
					Game.height / 2 + 36, 150, 80)) {
				Game.state = State.Credits;
			}
		} else if (Game.state == State.Help || Game.state == State.Credits) {
			if (isOver(mouseX, mouseY, Game.width / 2 - 225,
					Game.height / 2 - 70 * 2, 75, 40)) {
				Game.state = State.Menu;
			}
		}

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (isOver(mouseX, mouseY, Game.width / 2 + 75,
				Game.height / 2 - 70 * 2, 150, 80)) {
			gameButHover = true;
			helpButHover = false;
			credButHover = false;
		} else if (isOver(mouseX, mouseY, Game.width / 2 + 75,
				Game.height / 2 - 52, 150, 80)) {
			helpButHover = true;
			gameButHover = false;
			credButHover = false;
		} else if (isOver(mouseX, mouseY, Game.width / 2 + 75,
				Game.height / 2 + 36, 150, 80)) {
			credButHover = true;
			gameButHover = false;
			helpButHover = false;
		} else if (isOver(mouseX, mouseY, Game.width / 2 - 225,
				Game.height / 2 - 70 * 2, 75, 40)) {
			backButHover = true;
		} else {
			gameButHover = false;
			helpButHover = false;
			credButHover = false;
			backButHover = false;
		}
	}

	public static boolean isOver(int mouseX, int mouseY, int x, int y,
			int width, int height) {
		if (mouseX > x && mouseX < x + width) {
			if (mouseY > y && mouseY < y + height) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public void render(Graphics g){
		
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
		
		if(Game.state == State.Menu){
			
			font = font.deriveFont(Font.PLAIN, 70.0f);
			g.setFont(font);
			g.setColor(jmuGold);
			g.drawString("JMvU", 12, Game.height / 2 + 34);
			
			font = font.deriveFont(Font.PLAIN, 24.0f);
			g.setFont(font);
			//Credits Button
			g.setColor(Color.darkGray);
			g.fillRect(Game.width / 2 + 75, Game.height / 2 + 36, 150, 80);
			if(credButHover){
				g.setColor(Color.gray);
				g.fillRect(Game.width / 2 + 75, Game.height / 2 + 36, 150, 80);
			}
			g.setColor(jmuGold);
			g.drawRect(Game.width / 2 + 75, Game.height / 2 + 36, 150, 80); 
			g.drawString("Credits", Game.width / 2 + 78, Game.height / 2 + 86);
			
			//Help Button
			g.setColor(Color.darkGray);
			g.fillRect(Game.width / 2 + 75, Game.height / 2 - 52, 150, 80);
			if(helpButHover){
				g.setColor(Color.gray);
				g.fillRect(Game.width / 2 + 75, Game.height / 2 - 52, 150, 80);
			}
			g.setColor(jmuGold);
			g.drawRect(Game.width / 2 + 75, Game.height / 2 - 52, 150, 80); 
			g.drawString("Help", Game.width / 2 + 108, Game.height / 2);
			
			//Game Button
			g.setColor(Color.darkGray);
			g.fillRect(Game.width / 2 + 75, Game.height / 2 - 70 * 2, 150, 80);
			if(gameButHover){
				g.setColor(Color.gray);
				g.fillRect(Game.width / 2 + 75, Game.height / 2 - 70 * 2, 150, 80);
			}
			g.setColor(jmuGold);
			g.drawRect(Game.width / 2 + 75, Game.height / 2 - 70 * 2, 150, 80); 
			g.drawString("Play", Game.width / 2 + 108, Game.height / 2 - 42 * 2);
		}
		else if(Game.state == State.Help){
			//Controls Message Header
			font = font.deriveFont(Font.PLAIN, 25.0f);
			g.setFont(font);
			g.setColor(jmuGold);
			g.drawString("Controls", Game.width / 2 - 100, Game.height / 2 - 100);
			
			//Controls Message Lines
			g.setFont(new Font("Fipps", Font.PLAIN, 10));
			g.drawString("Player 1: A to move left, D to move right, and E to attack.", 10, Game.height / 2 - 80);
			g.drawString("Player 2: LT Arrow to move left, RT Arrow to move right,", 10, Game.height / 2 - 60);
			g.drawString("and NumPad 1 to attack.", 10, Game.height / 2 - 40);
			
			//Objective Message Header
			font = font.deriveFont(Font.PLAIN, 25.0f);
			g.setFont(font);
			//g.setFont(new Font("Fipps", Font.PLAIN, 25));
			g.setColor(jmuGold);
			g.drawString("Objective", Game.width / 2 - 100, Game.height / 2);
			
			//Objective Message Lines
			//font = font.deriveFont(Font.PLAIN, 10.0f);
			//g.setFont(font);
			g.setFont(new Font("Fipps", Font.PLAIN, 10));
			g.drawString("Win the game by defeating the opposing player or", 10, Game.height / 2 + 20);
			g.drawString("enemy in a best 3 out of 5 rounds.", 10, Game.height / 2 + 40);
			g.drawString("Win rounds by reducing your opponents health to 0.", 10, Game.height / 2 + 60);
			
			//Back Button
			g.setColor(Color.darkGray);
			g.fillRect(Game.width / 2 - 225, Game.height / 2 - 70 * 2, 75, 40);
			if(backButHover){
				g.setColor(Color.gray);
				g.fillRect(Game.width / 2 - 225, Game.height / 2 - 70 * 2, 75, 40);
			}
			font = font.deriveFont(Font.PLAIN, 15.0f);
			g.setFont(font);
			//g.setFont(new Font("Fipps", Font.PLAIN, 15));
			g.setColor(jmuGold);
			g.drawRect(Game.width / 2 - 225, Game.height / 2 - 70 * 2, 75, 40); 
			g.drawString("Back", Game.width / 2 - 215, Game.height / 2 - 55 * 2);
		}
		else if (Game.state == State.Credits){
			//Credits Message Header
			font = font.deriveFont(Font.PLAIN, 25.0f);
			g.setFont(font);
			//g.setFont(new Font("Fipps", Font.PLAIN, 25));
			g.setColor(jmuGold);
			g.drawString("Credits", Game.width / 2 - 80, Game.height / 2 - 20);
			
			//Credits Message Lines
			g.setFont(new Font("Fipps", Font.PLAIN, 10));
			g.drawString("Authors: John Ochs, Ryan Hirst, Kaelan Holic", 60, Game.height / 2);
			g.drawString("PProject Help Courtesy of: Prof. Berstein", 60, Game.height / 2 + 20);
			
			//Back Button
			g.setColor(Color.darkGray);
			font = font.deriveFont(Font.PLAIN, 15.0f);
			g.setFont(font);
			//g.setFont(new Font("Fipps", Font.PLAIN, 15));
			g.fillRect(Game.width / 2 - 225, Game.height / 2 - 70 * 2, 75, 40);
			if(backButHover){
				g.setColor(Color.gray);
				g.fillRect(Game.width / 2 - 225, Game.height / 2 - 70 * 2, 75, 40);
			}
			g.setColor(jmuGold);
			g.drawRect(Game.width / 2 - 225, Game.height / 2 - 70 * 2, 75, 40); 
			g.drawString("Back", Game.width / 2 - 215, Game.height / 2 - 55 * 2);
		}
	}

	public void tick() {

	}

}
