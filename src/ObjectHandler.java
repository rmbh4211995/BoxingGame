import java.awt.Graphics;
import java.util.LinkedList;

public class ObjectHandler {

	LinkedList<GameObject> objList = new LinkedList<GameObject>();
	
	public void tick(){
		for(int i = 0; i < objList.size(); i++){
			GameObject temp = objList.get(i);
			
			temp.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < objList.size(); i++){
			GameObject temp = objList.get(i);
			
			temp.render(g);
		}
	}
	
	public void addObject(GameObject obj){
		this.objList.add(obj);
	}
	
	public void remObject(GameObject obj){
		this.objList.remove(obj);
	}

	public void remAll() {
		this.objList.clear();
		
	}
}
