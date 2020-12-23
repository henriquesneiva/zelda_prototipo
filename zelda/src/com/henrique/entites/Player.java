package com.henrique.entites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.henrique.world.Camera;
import com.henriquesantos.maim.Game;

public class Player extends Entity{
	
	
	public boolean right,up,left,down;
	public int reight_dir =0,left_dir =1;
	public int dir = reight_dir;
	public double speed = 1.4;
	
	private int frames =0,maxFrames =5,index =0, maxIndex =3;
	private boolean moved = false;
	private BufferedImage[] rightplayer;
	private BufferedImage[] leftplayer;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightplayer = new BufferedImage[4];
		leftplayer = new BufferedImage[4];
		for(int i = 0;i< 4;i++) {
		rightplayer[i]= Game.spritesheet.getSprite(32+(i*16),0,16,16);
		}
		for(int i = 0;i< 4;i++) {
			leftplayer[i]= Game.spritesheet.getSprite(32+(i*16),16,16,16);
			}

	}
	
	public void tick() {
		moved = false;
		if(right) {
			moved = true;
			dir = reight_dir;
			x+=speed;
		}else if(left) {
			moved = true;
			dir = left_dir;
			x-=speed;
		}if(up) {
			moved = true;
			y-=speed;
		}else if(down) {
			moved = true;
			y+=speed;
		}
		if(moved) {
			frames ++;
			if(frames == maxFrames) {
				frames= 0;
				index ++;
				if(index> maxIndex) {
					index =0;
				}
			}
			
		}
		
		Camera.x = this.getX() - (Game.WIDTH/2);
		Camera.y = this.getY() - (Game.HEIGHT/2);
		
	}
	
	public void render(Graphics g) {
		if(dir == reight_dir) {
		g.drawImage(rightplayer[index],this.getX()-Camera.x,this.getY()-Camera.y,null);
		}else if(dir == left_dir) {
			g.drawImage(leftplayer[index],this.getX()-Camera.x,this.getY()-Camera.y,null);
		}
		
	}

}
