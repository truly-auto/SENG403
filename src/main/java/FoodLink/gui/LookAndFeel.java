package FoodLink.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LookAndFeel {

	
	public LookAndFeel(JFrame f)
	{
		/*
		try {
		    // Set the Look and Feel of the application to the operating
		    // system's look and feel.
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {
		}
		catch (InstantiationException e) {
		}
		catch (IllegalAccessException e) {
		}
		catch (UnsupportedLookAndFeelException e) {
		}
		
		*/
		BufferedImage img = null;
		try 
		{
		    img = ImageIO.read(new File("src/main/resources/images/foodlinkIcon.png")); // put icon image here
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		
		
		
		Image icon = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
		f.setIconImage(img);
		
	
	}
	

}
