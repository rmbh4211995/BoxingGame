import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x, y;
	protected ID id;
	protected float velX;
	
	public GameObject(float x, float y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getAttackBounds();
	public abstract Rectangle getMovementBounds();
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setID(ID id){
		this.id = id;
	}
	
	public ID getID(){
		return id;
	}
	
	public void setVelX(float velX){
		this.velX = velX;
	}
	
	public float getVelX(){
		return velX;
	}
}
