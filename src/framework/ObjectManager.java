package framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import entities.GameObject;

public class ObjectManager {
	private final LinkedList<GameObject> objects=new LinkedList<>();
	
	public void render(Graphics2D g){
		GameObject s=null;
		for(int i=0;i<objects.size();i++){
			s=objects.get(i);
			s.render(g);
		}
		
	}
	public void tick(){
		GameObject s=null;
		for(int i=0;i<objects.size();i++){
			s=objects.get(i);
			s.tick(this);
		}
	}
	
	public void add(GameObject ob){
		objects.add(ob);
	}
	public void remove(GameObject ob){
		objects.remove(ob);
	}
	public LinkedList<GameObject> getObjectList(){
		return (LinkedList<GameObject>)objects.clone();
	}
	public void clearAll(){
		objects.clear();
	}
}
