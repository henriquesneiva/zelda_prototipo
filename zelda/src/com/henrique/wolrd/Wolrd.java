package com.henrique.wolrd;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wolrd {
	

	
	public Wolrd(String path) {
		
		try {
		BufferedImage  map =  ImageIO.read(getClass().getResource(path));
		int[] pixeis = new int[map.getWidth()*map.getHeight()];
		map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixeis, 0, map.getWidth());
		for(int i = 0; i< pixeis.length; i++) {
			if(pixeis[i]== 0xFF000000) {
				System.out.println("estou no vermelho");
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	

}
