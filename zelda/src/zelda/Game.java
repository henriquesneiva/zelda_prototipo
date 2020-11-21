package zelda;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	
	public static JFrame jframe;
	private  Thread thread;
	private boolean isRunning = true;
	private final int HIDTH= 160;
	private final int HEIGHT = 120;
	private final int SCALE =4;
	
	public Game() {
		this.setPreferredSize(new Dimension(HIDTH*SCALE,HEIGHT*SCALE));
		initframe();
		
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
		
	}
	
	public static void main(String args[]) {
		
		Game game = new Game();
		game.start();
	}
	
	public void tick() {
		
	}
	
	public void render() {
		
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

}
