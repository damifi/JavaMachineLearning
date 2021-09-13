package machineLearning.data;

public class ImageValue {
	private int width; // width of an image
	private int height;// height of an image
	private int label;// label of an image
	private int[] image; // array containing all pixels of an image
	private double[] dImage; // double array containing all pixels of an image
	private int wrongLabel; // wrongly found label

	public void setWrongLabel(int i) { // sets wrongly found label
		this.wrongLabel = i;
	}

	public int getWrongLabel() { // returns wrongly found label
		return wrongLabel;
	}

	public ImageValue(int rows, int columns) { // constructor
		this.height = columns;
		this.width = rows;
		this.wrongLabel = -1;
		image = new int[height * width];
	}

	public void setImage(int[] i) { // sets an array containing all pixels
		this.image = i;
	}

	public void setLabel(int label) { // sets the label of an image
		this.label = label;
	}

	public int[] getPixel() { // returns pixels of an image as an array
		return image;
	}

	public int getLabel() { // returns a label of an image
		return label;
	}

	public int getHeight() { // returns the height of an image
		return this.height;
	}

	public int getWidth() { // returns the width of an image
		return this.width;
	}

	public void intToDouble() { // converts int array containing all pixels into
								// a double array
		dImage = new double[image.length];
		for (int i = 0; i < image.length; i++) {
			dImage[i] = image[i];
		}
	}

	public double[] getDPixel() { // returns a double array containing all
									// pixels of an image
		return dImage;
	}

}
