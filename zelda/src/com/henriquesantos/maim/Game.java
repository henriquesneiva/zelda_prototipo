package com.henriquesantos.maim;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.henrique.entites.Entity;
import com.henrique.entites.Player;
import com.henrique.graficos.Spritesheet;
import com.henrique.wolrd.Wolrd;




public class Game extends Canvas implements Runnable,KeyListener {
	
	

	private static final long serialVersionUID = 1L;
	public static JFrame jframe;
	private  Thread thread;
	private boolean isRunning = true;
	private final int HIDTH= 160;
	private final int HEIGHT = 120;
	private final int SCALE =4;
	
	private BufferedImage image;
	
	public List<Entity> entities;
	public static  Spritesheet spritesheet;
	public static Wolrd wolrd;
	
	private Player  player;
	
	public Game() {
		addKeyListener(this);
		this.setPreferredSize(new Dimension(HIDTH*SCALE,HEIGHT*SCALE));
		initframe();
		//inicializando objetos;
		wolrd = new Wolrd("/map.png");
		image = new BufferedImage(HIDTH,HEIGHT,BufferedImage.TYPE_INT_BGR);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheet.png");		
	    player = new Player(0,0,16,16,spritesheet.getSprite(32, 0, 16,16));
		entities.add(player);
	}
	
	public void initframe() {
		jframe = new JFrame("zelda");
		jframe.add(this);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
		thread.join();
		}catch(InterruptedException e) {
		e.printStackTrace();}
	}
	
	public static void main(String args[]) {
		
		Game game = new Game();
		game.start();
	}
	
	public void tick() {
		for(int i =0;i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g= image.getGraphics();
		g.setColor(new Color(0,139,19));
		g.fillRect(0, 0, HIDTH, HEIGHT);
		for(int i =0;i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
			g = bs.getDrawGraphics();
			g.drawImage(image,0,0,HIDTH*SCALE, HEIGHT*SCALE,null);
			bs.show();
		}
	}
	
	public void run() {
		
		long LastTime =System.nanoTime();
		double amountOFTicks = 60.0;
		double ns = 1000000000 / amountOFTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - LastTime) /ns;
			LastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta --;
			}
			if(System.currentTimeMillis() -timer >= 1000) {
				
				System.out.println("FPS:"+ frames);
				frames=0;
				timer += 1000;
			}
			}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_KP_RIGHT||e.getKeyCode()== KeyEvent.VK_D) {
			player.right =true;
		}else if(e.getKeyCode()== KeyEvent.VK_KP_LEFT ||e.getKeyCode()== KeyEvent.VK_A) {
			player.left =true;
		}
		if(e.getKeyCode()== KeyEvent.VK_KP_UP ||e.getKeyCode()== KeyEvent.VK_W) {
			player.up =true;
		}else if(e.getKeyCode()== KeyEvent.VK_KP_DOWN ||e.getKeyCode()== KeyEvent.VK_S) {
			player.down =true;
		}
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_KP_RIGHT||e.getKeyCode()== KeyEvent.VK_D) {
			player.right =false;
		}else if(e.getKeyCode()== KeyEvent.VK_KP_LEFT ||e.getKeyCode()== KeyEvent.VK_A) {
			player.left =false;
		}
		if(e.getKeyCode()== KeyEvent.VK_KP_UP ||e.getKeyCode()== KeyEvent.VK_W) {
			player.up =false;
		}else if(e.getKeyCode()== KeyEvent.VK_KP_DOWN ||e.getKeyCode()== KeyEvent.VK_S) {
			player.down =false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

}
