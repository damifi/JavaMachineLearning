package machineLearning.gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import machineLearning.data.ConverterMNIST;
import machineLearning.data.ImageValue;

public class DisplayImage extends JFrame {

	private static JFrame contentFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConverterMNIST images = new ConverterMNIST("H:/Eclipse/ImageData/train-images.idx3-ubyte",
							"H:/Eclipse/ImageData/train-labels.idx1-ubyte");
					int[] trasha = images.openIMG();
					int trash = images.openLabel();
					DisplayImage(images.readLearnData(10));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static BufferedImage getImageFromArray(int[] pixels, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = (WritableRaster) image.getRaster();
		raster.setPixels(0, 0, width, height, pixels);
		return image;
	}

	/**
	 * Create the frame.
	 */
	public static void DisplayImage(ArrayList<ImageValue> images) {
		contentFrame = new JFrame();
		// JPanel panel = new JPanel();
		contentFrame.getContentPane().setLayout(new FlowLayout());
		for (int i = 0; i < images.size(); i++) {
			BufferedImage image = getImageFromArray(images.get(i).getPixel(), images.get(i).getWidth(),
					images.get(i).getHeight());
			contentFrame.getContentPane().add(new JLabel(new ImageIcon(image)));
		}
		contentFrame.pack();
		contentFrame.setVisible(true);
	}

}