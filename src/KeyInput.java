import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	private ObjectHandler handler;
	protected static boolean p1CanAttack = true;
	protected static boolean p2CanAttack = true;
	
	private boolean p1CanBlock = true;
	private boolean p2CanBlock = true;
	
	private boolean[] keysPressed = new boolean[4];
	
	public KeyInput(ObjectHandler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(Game.state == State.Game){
			for(int i = 0; i < handler.objList.size(); i++){
				GameObject temp = handler.objList.get(i);
				
				if(temp.getID() == ID.Player){
					if(key == KeyEvent.VK_D){
						temp.setVelX(2);
						if(HUD.p1Stamina == 0){
							temp.setVelX(1);
						}
						keysPressed[0] = true;
						Player.p1WasMovingRight = true;
						Player.p1WasMovingLeft = false;
						Player.p1MovedFromStart = true;
					}
					else if(key == KeyEvent.VK_A){
						temp.setVelX(-2);
						if(HUD.p1Stamina == 0){
							temp.setVelX(-1);
						}
						keysPressed[1] = true;
						Player.p1WasMovingLeft = true;
						Player.p1WasMovingRight = false;
						Player.p1MovedFromStart = true;
					}
					else if(key == KeyEvent.VK_G && p1CanAttack && HUD.p1Stamina >= 10){
						Player.player1Attacking = true;
						Player.playP1AttackAnim = true;
						HUD.p1Stamina -= 10;
						p1CanAttack = false;
					}
					else if(key == KeyEvent.VK_H && p1CanBlock){
						Player.p1Blocking = true;
						if(HUD.p1Stamina == 0){
							Player.p1Blocking = false;
							p1CanBlock = false;
						}
					}
				}
				else if(temp.getID() == ID.Player2){
					if(key == KeyEvent.VK_RIGHT){
						temp.setVelX(2);
						if(HUD.p2Stamina == 0){
							temp.setVelX(1);
						}
						keysPressed[2] = true;
						Player.p2WasMovingRight = true;
						Player.p2WasMovingLeft = false;
						Player.p2MovedFromStart = true;
					}
					else if(key == KeyEvent.VK_LEFT){
						temp.setVelX(-2);
						if(HUD.p2Stamina == 0){
							temp.setVelX(-1);
						}
						keysPressed[3] = true;
						Player.p2WasMovingRight = false;
						Player.p2WasMovingLeft = true;
						Player.p2MovedFromStart = true;
					}
					else if(key == KeyEvent.VK_NUMPAD1 && p2CanAttack && HUD.p2Stamina >= 10){
						Player.player2Attacking = true;
						Player.playP2AttackAnim = true;
						HUD.p2Stamina -= 10;
						p2CanAttack = false;
					}
					else if(key == KeyEvent.VK_NUMPAD2 && p2CanBlock){
						Player.p2Blocking = true;
						if(HUD.p2Stamina == 0){
							Player.p2Blocking = false;
							p2CanBlock = false;
						}
					}
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE){
			if(Game.state == State.Game){
				Game.state = State.Pause;
			}
			else if(Game.state == State.Pause){
				Game.state = State.Game;
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.objList.size(); i++){
			GameObject temp = handler.objList.get(i);
			
			if(temp.getID() == ID.Player){
				if(key == KeyEvent.VK_D){
					keysPressed[0] = false;
				}
				else if(key == KeyEvent.VK_A){
					keysPressed[1] = false;
				}
				else if(key == KeyEvent.VK_G){
					p1CanAttack = true;
					Player.player1Attacking = false;
					Player.playP1AttackAnim = false;
				}
				else if(key == KeyEvent.VK_H){
					Player.p1Blocking = false;
					p1CanBlock = true;
				}
			}
			else if(temp.getID() == ID.Player2){
				if(key == KeyEvent.VK_RIGHT){
					keysPressed[2] = false;
				}
				else if(key == KeyEvent.VK_LEFT){
					keysPressed[3] = false;
				}
				else if(key == KeyEvent.VK_NUMPAD1){
					p2CanAttack = true;
					Player.player2Attacking = false;
					Player.playP2AttackAnim = false;
				}
				else if(key == KeyEvent.VK_NUMPAD2){
					Player.p2Blocking = false;
					p2CanBlock = true;
				}
			}
			
			if(!keysPressed[0] && !keysPressed[1]){
				temp.setVelX(0);
			}
			else if(!keysPressed[2] && !keysPressed[3]){
				temp.setVelX(0);
			}
		}
	}
}
