package com.henrique.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.henrique.entites.Bullet;
import com.henrique.entites.Enemy;
import com.henrique.entites.Entity;
import com.henrique.entites.Lifepack;
import com.henrique.entites.Weapon;
import com.henriquesantos.maim.Game;

public class World {
	
	private Tile[] tiles;
	public static  int WIDTH,HEIGHT;
	
public World(String path) {
		
		try {
		BufferedImage  map = ImageIO.read(new File("C:/Users/henrique/eclipse-workspace/zelda/res/map.png"));
		int[] pixeis = new int[map.getWidth()*map.getHeight()];
		WIDTH = map.getWidth();
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         	HEIGHT = map.getHeight();
		tiles = new Tile[map.getWidth()*map.getHeight()];
		map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixeis, 0, map.getWidth());
		
		for(int xx = 0; xx < map.getWidth(); xx  ++) {
			for(int yy = 0; yy < map.getHeight(); yy++) {
				int pixelAtual = pixeis[xx +(yy* map.getWidth())];
				tiles[xx +(yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
				if(pixelAtual == 0xFF000000) {
					//chao
					tiles[xx +(yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
				}else if(pixelAtual == 0xFFFFFDFC) {
					//parede
					tiles[xx +(yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_WALL);
				}else if(pixelAtual == 0xFF003EC6) {
					//player
					Game.player.setX(xx*16);
					Game.player.setY(yy*16);
					
				}else if(pixelAtual == 0xFFFF0000) {
					//enimy
					Game.entities.add(new Enemy(xx*16, yy*16, 16, 16, Entity.ENEMY_EN));
					
				}else if(pixelAtual == 0xFF3BC6B6) {
					//weapon
					Game.entities.add(new Weapon(xx*16, yy*16, 16, 16, Entity.WEAPON_EN));
					
				}else if(pixelAtual == 0xFF03C624) {
					//lifepack
					Game.entities.add(new Lifepack(xx*16, yy*16, 16, 16, Entity.LIFEPACK_EN));
				}else if(pixelAtual == 0xFFC6C300) {
					//bullet
					Game.entities.add(new Bullet(xx*16, yy*16, 16, 16, Entity.BULLET_EN));
				
				}

			}
			
		}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

public static boolean isFree() {
	
}


public void render(Graphics g) {
	
	int xstart = Camera.x >> 4;
	int ystart = Camera.y >> 4;
	
	int xfinal = xstart + (Game.WIDTH >> 4);
	int yfinal = ystart + (Game.HEIGHT >> 4);
	
	for(int xx = xstart; xx <= xfinal;xx++) {
		for(int yy = ystart; yy <= yfinal;yy++) {
			if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT )
				continue;
			Tile tile = tiles[xx +(yy*WIDTH)];
			tile.render(g);
		}
		
	}
	
}

}
