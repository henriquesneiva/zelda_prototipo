package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tubo extends Entity{
	
	
	public Tubo(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
	}

	public void tick(){
		x--;
	}
	
	public void render(Graphics g){
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
