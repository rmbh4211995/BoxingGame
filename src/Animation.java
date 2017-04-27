import java.awt.Image;
import java.util.ArrayList;

public class Animation{
	
	private ArrayList<Image> imgs;
	
	private float speed;
	
	public Image sprite;
	
	private long previousTime = 0;
	
	public Animation(ArrayList<Image> imgs){
		this.imgs = imgs;
	}
	
	public synchronized void update(long time){
		if(Game.isRunning){
			if(time - previousTime >= speed){
				Game.frames++;
				try{
					sprite = imgs.get(Game.frames);
				}catch(IndexOutOfBoundsException e){
					Game.frames = 0;
					sprite = imgs.get(Game.frames);
				}
				
				previousTime = time;
			}
		}
	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public long getPrevTime(){
		return previousTime;
	}
}
