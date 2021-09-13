package machineLearning.data;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageDisplayer {

	public static BufferedImage getImageFromArray(int[] pixels, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = (WritableRaster) image.getRaster();
		raster.setPixels(0, 0, width, height, pixels);
		return image;
	}

	// public static void displayImage(ImageValue img) throws
	// InterruptedException {
	//
	// BufferedImage image = getImageFromArray(img.getPixel(), img.getHeight(),
	// img.getWidth());
	// // ImageIcon icon = new ImageIcon();
	// // icon.setImage(image);
	// // JOptionPane.showMessageDialog(null, icon);
	// // System.out.println("Image: " + icon.getImage());
	// JFrame frame = new JFrame("Pictures");
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.getContentPane().setLayout(new FlowLayout());
	// frame.getContentPane().add(new JLabel(new ImageIcon(image)));
	// frame.getContentPane().add(new JLabel(new ImageIcon(image)));
	// frame.getContentPane().add(new JLabel(new ImageIcon(image)));
	// frame.pack();
	// frame.setVisible(true);
	//
	// }

	public static void displayImage(ArrayList<ImageValue> img) throws InterruptedException {
		JFrame frame = new JFrame("Pictures");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout());
		for (int i = 0; i < img.size(); i++) {
			BufferedImage image = getImageFromArray(img.get(i).getPixel(), img.get(i).getHeight(),
					img.get(i).getWidth());
			frame.getContentPane().add(new JLabel(new ImageIcon(image)));
		}

		frame.pack();
		frame.setVisible(true);

	}
}
