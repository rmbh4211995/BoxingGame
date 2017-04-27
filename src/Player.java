import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player extends GameObject {

	ObjectHandler handler;
	private MusicPlayer player = new MusicPlayer();
	protected static boolean player1Attacking = false;
	protected static boolean player2Attacking = false;

	protected static boolean playP1AttackAnim = false;
	protected static boolean playP2AttackAnim = false;

	protected static boolean p1WasMovingRight = false;
	protected static boolean p1WasMovingLeft = false;
	protected static boolean p1MovedFromStart = false;
	protected static boolean p2WasMovingRight = false;
	protected static boolean p2WasMovingLeft = false;
	protected static boolean p2MovedFromStart = false;

	protected static boolean p1Blocking = false;
	protected static boolean p2Blocking = false;

	protected static boolean p1IsMoving = false;
	protected static boolean p2IsMoving = false;

	private int basicDamage = 100;

	private Animation idleP1Anim;
	private Animation idleP1AnimFlip;
	private Animation runP1Anim;
	private Animation runP1AnimFlip;
	private Animation attP1Anim;
	private Animation blkP1Anim;

	private Animation idleP2Anim;
	private Animation idleP2AnimFlip;
	private Animation runP2Anim;
	private Animation runP2AnimFlip;
	private Animation attP2AnimFlip;
	private Animation blkP2Anim;

	private ArrayList<Image> idleP1;
	private ArrayList<Image> idleP1Flip;
	private ArrayList<Image> runP1;
	private ArrayList<Image> runP1Flip;
	private ArrayList<Image> attP1;
	private ArrayList<Image> blkP1;

	private ArrayList<Image> idleP2;
	private ArrayList<Image> idleP2Flip;
	private ArrayList<Image> runP2;
	private ArrayList<Image> runP2Flip;
	private ArrayList<Image> attP2;
	private ArrayList<Image> blkP2;

	//Player 1 Image Icons
	private ImageIcon idleP1F1 = new ImageIcon(this.getClass().getResource("duke_idle1.png"));
	private ImageIcon idleP1F2 = new ImageIcon(this.getClass().getResource("duke_idle2.png"));
	private ImageIcon idleP1F3 = new ImageIcon(this.getClass().getResource("duke_idle3.png"));

	private ImageIcon runP1F1 = new ImageIcon(this.getClass().getResource("duke_run1.png"));
	private ImageIcon runP1F2 = new ImageIcon(this.getClass().getResource("duke_run2.png"));
	private ImageIcon runP1F3 = new ImageIcon(this.getClass().getResource("duke_run3.png"));
	private ImageIcon runP1F4 = new ImageIcon(this.getClass().getResource("duke_run4.png"));
	private ImageIcon runP1F5 = new ImageIcon(this.getClass().getResource("duke_run5.png"));

	private ImageIcon attP1F1 = new ImageIcon(this.getClass().getResource("duke_att1.png"));
	private ImageIcon attP1F2 = new ImageIcon(this.getClass().getResource("duke_att2.png"));
	private ImageIcon attP1F3 = new ImageIcon(this.getClass().getResource("duke_att3.png"));
	private ImageIcon attP1F4 = new ImageIcon(this.getClass().getResource("duke_att4.png"));
	private ImageIcon attP1F5 = new ImageIcon(this.getClass().getResource("duke_att5.png"));
	private ImageIcon attP1F6 = new ImageIcon(this.getClass().getResource("duke_att6.png"));

	private ImageIcon blkP1F1 = new ImageIcon(this.getClass().getResource("duke_block1.png"));

	//Player 2 Image Icons
	private ImageIcon idleP2F1 = new ImageIcon(this.getClass().getResource("demon_idle1.png"));
	private ImageIcon idleP2F2 = new ImageIcon(this.getClass().getResource("demon_idle2.png"));
	private ImageIcon idleP2F3 = new ImageIcon(this.getClass().getResource("demon_idle3.png"));

	private ImageIcon runP2F1 = new ImageIcon(this.getClass().getResource("demon_run1.png"));
	private ImageIcon runP2F2 = new ImageIcon(this.getClass().getResource("demon_run2.png"));
	private ImageIcon runP2F3 = new ImageIcon(this.getClass().getResource("demon_run3.png"));
	private ImageIcon runP2F4 = new ImageIcon(this.getClass().getResource("demon_run4.png"));
	private ImageIcon runP2F5 = new ImageIcon(this.getClass().getResource("demon_run5.png"));

	private ImageIcon attP2F1 = new ImageIcon(this.getClass().getResource("demon_att1.png"));
	private ImageIcon attP2F2 = new ImageIcon(this.getClass().getResource("demon_att2.png"));
	private ImageIcon attP2F3 = new ImageIcon(this.getClass().getResource("demon_att3.png"));
	private ImageIcon attP2F4 = new ImageIcon(this.getClass().getResource("demon_att4.png"));
	private ImageIcon attP2F5 = new ImageIcon(this.getClass().getResource("demon_att5.png"));
	private ImageIcon attP2F6 = new ImageIcon(this.getClass().getResource("demon_att6.png"));

	private ImageIcon blkP2F1 = new ImageIcon(this.getClass().getResource("demon_block1.png"));


	public Player(int x, int y, ID id, ObjectHandler handler) {
		super(x, y, id);
		this.handler = handler;

		Image idleImgP1F1 = idleP1F1.getImage();
		Image idleImgP1F2 = idleP1F2.getImage();
		Image idleImgP1F3 = idleP1F3.getImage();

		Image idleImgP1F1Flip = flipImg(idleImgP1F1);
		Image idleImgP1F2Flip = flipImg(idleImgP1F2);
		Image idleImgP1F3Flip = flipImg(idleImgP1F3);

		Image runImgP1F1 = runP1F1.getImage();
		Image runImgP1F2 = runP1F2.getImage();
		Image runImgP1F3 = runP1F3.getImage();
		Image runImgP1F4 = runP1F4.getImage();
		Image runImgP1F5 = runP1F5.getImage();

		Image runImgP1F1Flip = flipImg(runImgP1F1);
		Image runImgP1F2Flip = flipImg(runImgP1F2);
		Image runImgP1F3Flip = flipImg(runImgP1F3);
		Image runImgP1F4Flip = flipImg(runImgP1F4);
		Image runImgP1F5Flip = flipImg(runImgP1F5);

		Image attImgP1F1 = attP1F1.getImage();
		Image attImgP1F2 = attP1F2.getImage();
		Image attImgP1F3 = attP1F3.getImage();
		Image attImgP1F4 = attP1F4.getImage();
		Image attImgP1F5 = attP1F5.getImage();
		Image attImgP1F6 = attP1F6.getImage();

		Image blkImgP1F1 = blkP1F1.getImage();

		// Player 2
		Image idleImgP2F1 = idleP2F1.getImage();
		Image idleImgP2F2 = idleP2F2.getImage();
		Image idleImgP2F3 = idleP2F3.getImage();

		Image idleImgP2F1Flip = flipImg(idleImgP2F1);
		Image idleImgP2F2Flip = flipImg(idleImgP2F2);
		Image idleImgP2F3Flip = flipImg(idleImgP2F3);

		Image runImgP2F1 = runP2F1.getImage();
		Image runImgP2F2 = runP2F2.getImage();
		Image runImgP2F3 = runP2F3.getImage();
		Image runImgP2F4 = runP2F4.getImage();
		Image runImgP2F5 = runP2F5.getImage();

		Image runImgP2F1Flip = flipImg(runImgP2F1);
		Image runImgP2F2Flip = flipImg(runImgP2F2);
		Image runImgP2F3Flip = flipImg(runImgP2F3);
		Image runImgP2F4Flip = flipImg(runImgP2F4);
		Image runImgP2F5Flip = flipImg(runImgP2F5);

		Image attImgP2F1 = attP2F1.getImage();
		Image attImgP2F2 = attP2F2.getImage();
		Image attImgP2F3 = attP2F3.getImage();
		Image attImgP2F4 = attP2F4.getImage();
		Image attImgP2F5 = attP2F5.getImage();
		Image attImgP2F6 = attP2F6.getImage();

		Image attImgP2F1Flip = flipImg(attImgP2F1);
		Image attImgP2F2Flip = flipImg(attImgP2F2);
		Image attImgP2F3Flip = flipImg(attImgP2F3);
		Image attImgP2F4Flip = flipImg(attImgP2F4);
		Image attImgP2F5Flip = flipImg(attImgP2F5);
		Image attImgP2F6Flip = flipImg(attImgP2F6);
		
		Image blkImgP2F1 = blkP2F1.getImage();
		Image blkImgP2F1Flip = flipImg(blkImgP2F1);

		idleP1 = new ArrayList<Image>();
		idleP1.add(idleImgP1F1);
		idleP1.add(idleImgP1F2);
		idleP1.add(idleImgP1F3);
		idleP1Anim = new Animation(idleP1);
		idleP1Anim.setSpeed(400);

		idleP1Flip = new ArrayList<Image>();
		idleP1Flip.add(idleImgP1F1Flip);
		idleP1Flip.add(idleImgP1F2Flip);
		idleP1Flip.add(idleImgP1F3Flip);
		idleP1AnimFlip = new Animation(idleP1Flip);
		idleP1AnimFlip.setSpeed(400);

		runP1 = new ArrayList<Image>();
		runP1.add(runImgP1F1);
		runP1.add(runImgP1F2);
		runP1.add(runImgP1F3);
		runP1.add(runImgP1F4);
		runP1.add(runImgP1F5);
		runP1Anim = new Animation(runP1);
		runP1Anim.setSpeed(300);

		runP1Flip = new ArrayList<Image>();
		runP1Flip.add(runImgP1F1Flip);
		runP1Flip.add(runImgP1F2Flip);
		runP1Flip.add(runImgP1F3Flip);
		runP1Flip.add(runImgP1F4Flip);
		runP1Flip.add(runImgP1F5Flip);
		runP1AnimFlip = new Animation(runP1Flip);
		runP1AnimFlip.setSpeed(300);

		attP1 = new ArrayList<Image>();
		attP1.add(attImgP1F1);
		attP1.add(attImgP1F2);
		attP1.add(attImgP1F3);
		attP1.add(attImgP1F4);
		attP1.add(attImgP1F5);
		attP1.add(attImgP1F6);
		attP1Anim = new Animation(attP1);
		attP1Anim.setSpeed(350);

		blkP1 = new ArrayList<Image>();
		blkP1.add(blkImgP1F1);
		blkP1Anim = new Animation(blkP1);
		blkP1Anim.setSpeed(300);

		// Player 2 Anims
		idleP2 = new ArrayList<Image>();
		idleP2.add(idleImgP2F1);
		idleP2.add(idleImgP2F2);
		idleP2.add(idleImgP2F3);
		idleP2Anim = new Animation(idleP2);
		idleP2Anim.setSpeed(400);

		idleP2Flip = new ArrayList<Image>();
		idleP2Flip.add(idleImgP2F1Flip);
		idleP2Flip.add(idleImgP2F2Flip);
		idleP2Flip.add(idleImgP2F3Flip);
		idleP2AnimFlip = new Animation(idleP2Flip);
		idleP2AnimFlip.setSpeed(400);

		runP2 = new ArrayList<Image>();
		runP2.add(runImgP2F1);
		runP2.add(runImgP2F2);
		runP2.add(runImgP2F3);
		runP2.add(runImgP2F4);
		runP2.add(runImgP2F5);
		runP2Anim = new Animation(runP2);
		runP2Anim.setSpeed(300);

		runP2Flip = new ArrayList<Image>();
		runP2Flip.add(runImgP2F1Flip);
		runP2Flip.add(runImgP2F2Flip);
		runP2Flip.add(runImgP2F3Flip);
		runP2Flip.add(runImgP2F4Flip);
		runP2Flip.add(runImgP2F5Flip);
		runP2AnimFlip = new Animation(runP2Flip);
		runP2AnimFlip.setSpeed(300);

		attP2 = new ArrayList<Image>();
		attP2.add(attImgP2F1Flip);
		attP2.add(attImgP2F2Flip);
		attP2.add(attImgP2F3Flip);
		attP2.add(attImgP2F4Flip);
		attP2.add(attImgP2F5Flip);
		attP2.add(attImgP2F6Flip);
		attP2AnimFlip = new Animation(attP2);
		attP2AnimFlip.setSpeed(350);
		
		blkP2 = new ArrayList<Image>();
		blkP2.add(blkImgP2F1Flip);
		blkP2Anim = new Animation(blkP2);
		blkP2Anim.setSpeed(300);
	}

	@Override
	public void tick() {
		x += velX;

		if (id == ID.Player && Game.twoPlayerMode) {
			x = Game.bound((int) x, 30, Game.width - getPlayerImg().getWidth(null) * 2 - 15);
		} else if (id == ID.Player2 && Game.twoPlayerMode) {
			x = Game.bound((int) x, getPlayerImg().getWidth(null) + 15,
					Game.width - getPlayerImg().getWidth(null) - 30);
		}

		attackCollision();
		movementCollision();

		if (id == ID.Player && (velX > 0 || velX < 0)) {
			p1IsMoving = true;
		} else if (id == ID.Player && velX == 0) {
			p1IsMoving = false;
		}

		if (id == ID.Player2 && (velX > 0 || velX < 0)) {
			p2IsMoving = true;
		} else if (id == ID.Player2 && velX == 0) {
			p2IsMoving = false;
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;

		//Set up Player Boundaries
		g.setColor(new Color(0, 0, 0, 0));
		if (id == ID.Player) {
			g2.draw(getAttackBounds());
			g2.draw(getMovementBounds());
			checkP1Anims(g); //Check for animation updates to player 1
		} 
		else {
			g2.draw(getAttackBounds());
			g2.draw(getMovementBounds());
			checkP2Anims(g); //Check for animation updates to player 2
		}

		 
	}

	public Image getPlayerImg() {
		ImageIcon player1 = new ImageIcon(this.getClass().getResource("duke_idle1.png"));
		ImageIcon player2 = new ImageIcon(this.getClass().getResource("idle animation 1.png"));
		try {
			if (id == ID.Player) {
				return player1.getImage();
			} else {
				return player2.getImage();
			}
		} catch (Exception e) {
			System.out.println("Player image not found.");
		}
		return null;
	}

	public void attackCollision() {
		for (int i = 0; i < handler.objList.size(); i++) {
			GameObject temp = handler.objList.get(i);

			if (temp.getID() == ID.AI) {
				if (getAttackBounds().intersects(temp.getAttackBounds())) {
					// Unimplemented
				}
			} else {
				if (getAttackBounds().intersects(temp.getAttackBounds()) && id != temp.getID()) {
					if (!player1Attacking && player2Attacking) {
						HUD.updateHealth(ID.Player, basicDamage);
						Player.player2Attacking = false;
						player.playHit();
						// System.out.println("Player 2 stopped attacking.");
					} else if (player1Attacking && !player2Attacking) {
						player.playHit();
						HUD.updateHealth(ID.Player2, basicDamage);
						Player.player1Attacking = false;
						// System.out.println("Player 1 stopped attacking.");
					} else if (player1Attacking && player2Attacking) {
						player.playHit();
						HUD.updateHealth(ID.Player, basicDamage);
						HUD.updateHealth(ID.Player2, basicDamage);
						Player.player1Attacking = false;
						Player.player2Attacking = false;
					}
				}
			}
		}
	}

	public void movementCollision() {
		for (int i = 0; i < handler.objList.size(); i++) {
			GameObject temp = handler.objList.get(i);

			if (temp.getID() == ID.AI) {
				if (getMovementBounds().intersects(temp.getMovementBounds())) {
					// Unimplemented
				}
			} else {
				if (getMovementBounds().intersects(temp.getMovementBounds())) {
					if (getVelX() >= 0 && temp.getVelX() <= 0) {
						x -= velX;
						temp.x -= temp.velX;
					}

				}
			}
		}
	}

	// Set up Collision box for player
	public Rectangle getAttackBounds() {
		// TODO Auto-generated method stub
		if (id == ID.Player) {
			return new Rectangle((int) x + 20, (int) y, 38, 60);
		} else {
			return new Rectangle((int) x + 5, (int) y, 38, 60);
		}
	}

	public Rectangle getMovementBounds() {

		if (id == ID.Player) {
			return new Rectangle((int) x + 25, (int) y, 30, 60);
		} else {
			return new Rectangle((int) x + 10, (int) y, 30, 60);
		}
	}

	private Image flipImg(Image img) {
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		AffineTransform tran = AffineTransform.getTranslateInstance(img.getWidth(null), 0);
		AffineTransform flip = AffineTransform.getScaleInstance(-1d, 1d);
		tran.concatenate(flip);

		Graphics2D g = (Graphics2D) bi.getGraphics();
		g.setTransform(tran);
		g.drawImage(img, 0, 0, null);
		g.dispose();

		return bi;
	}
	
	private void checkP1Anims(Graphics g){
		
		if (!p1MovedFromStart) {
			idleP1Anim.update(System.currentTimeMillis());
			g.drawImage(idleP1Anim.sprite, (int) x, (int) y, null);
		}
		else if (velX == 0 && p1WasMovingRight && !playP1AttackAnim && !p1Blocking) {
			idleP1Anim.update(System.currentTimeMillis());
			g.drawImage(idleP1Anim.sprite, (int) x, (int) y, null);
		}
		else if (velX == 0 && p1WasMovingLeft && !playP1AttackAnim && !p1Blocking) {
			idleP1AnimFlip.update(System.currentTimeMillis());
			g.drawImage(idleP1AnimFlip.sprite, (int) x, (int) y, null);
		}
		else if (velX > 0) {
			runP1Anim.update(System.currentTimeMillis());
			g.drawImage(runP1Anim.sprite, (int) x, (int) y, null);
		} 
		else if (velX < 0) {
			runP1AnimFlip.update(System.currentTimeMillis());
			g.drawImage(runP1AnimFlip.sprite, (int) x, (int) y, null);
		}
		else if (playP1AttackAnim) {
			attP1Anim.update(System.currentTimeMillis());
			g.drawImage(attP1Anim.sprite, (int) x, (int) y, null);
		} 
		else if (p1Blocking) {
			blkP1Anim.update(System.currentTimeMillis());
			g.drawImage(blkP1Anim.sprite, (int) x, (int) y, null);
		}
	}
	
	private void checkP2Anims(Graphics g){
		if (!p2MovedFromStart) {
			idleP2AnimFlip.update(System.currentTimeMillis());
			g.drawImage(idleP2AnimFlip.sprite, (int) x, (int) y, null);
		}
		else if (velX == 0 && p2WasMovingRight && !playP2AttackAnim && !p2Blocking) {
			idleP2Anim.update(System.currentTimeMillis());
			g.drawImage(idleP2Anim.sprite, (int) x, (int) y, null);
		} 
		else if (velX == 0 && p2WasMovingLeft && !playP2AttackAnim && !p2Blocking) {
			idleP2AnimFlip.update(System.currentTimeMillis());
			g.drawImage(idleP2AnimFlip.sprite, (int) x, (int) y, null);
		}
		else if (velX > 0) {
			runP2Anim.update(System.currentTimeMillis());
			g.drawImage(runP2Anim.sprite, (int) x, (int) y, null);
		}
		else if (velX < 0) {
			runP2AnimFlip.update(System.currentTimeMillis());
			g.drawImage(runP2AnimFlip.sprite, (int) x, (int) y, null);
		}
		else if (playP2AttackAnim) {
			attP2AnimFlip.update(System.currentTimeMillis());
			g.drawImage(attP2AnimFlip.sprite, (int) x, (int) y, null);
		}
	 	else if(p2Blocking){
	 		blkP2Anim.update(System.currentTimeMillis());
	 		g.drawImage(blkP2Anim.sprite, (int)x, (int)y, null);
	 	}
	

	}

}
