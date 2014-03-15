package FoodLink.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LookAndFeel {

	
	public LookAndFeel(JFrame f)
	{
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
		Image icon = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
		f.setIconImage(icon);
	
	}
	

}
