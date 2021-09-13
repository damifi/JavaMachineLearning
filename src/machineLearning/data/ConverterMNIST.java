package machineLearning.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ConverterMNIST {
	private String filepathIMG; // path to file of images
	private String filepathLabel; // path to file of labels
	private DataInputStream inLabel; // Datastream of labels
	private DataInputStream inIMG; // DataStream of images
	private int imgRows; // width
	private int imgColumns; // height
	private int imgCount; // Number of Images

	public ConverterMNIST(String filepathIMG, String filepathLabel) { // constructor,
																		// takes
																		// file
																		// paths
																		// to
																		// image
																		// file
																		// and
																		// label
																		// file
		super();
		this.filepathIMG = filepathIMG;
		this.filepathLabel = filepathLabel;
	}

	public String getFPIMG() { // returns file path to images
		return filepathIMG;
	}

	public String getFPLabel() { // returns filepath to labels
		return filepathLabel;
	}

	public int[] openIMG() throws Exception { // opens a DataInputStream to read
												// images, also returns header
												// of images

		inIMG = new DataInputStream(new FileInputStream(new File(filepathIMG)));
		int magicNumber = inIMG.readInt();
		if (magicNumber != 2051) {
			throw new Exception(filepathIMG + " is not a MNIST image file.");
		}
		imgCount = inIMG.readInt();
		imgRows = inIMG.readInt();
		imgColumns = inIMG.readInt();
		int resolution[] = { imgCount, imgRows, imgColumns };
		return resolution;
	}

	public int openLabel() throws Exception { // opens a DataInputStream to read
												// labels, also returns header
												// of images
		inLabel = new DataInputStream(new FileInputStream(new File(filepathLabel)));
		int magicNumber = inLabel.readInt();
		if (magicNumber != 2049) {
			throw new Exception(filepathLabel + " is not a MNIST label file.");
		}
		int itemCount = inLabel.readInt();
		return itemCount;
	}

	public ArrayList<ImageValue> readLearnData(int howManyLabel) throws IOException { // reads
																						// howManyLabel
																						// training
																						// images
																						// from
																						// stream
																						// and
																						// returns
																						// them
																						// in
																						// an
																						// ArrayList
		ArrayList<ImageValue> imgList = new ArrayList<ImageValue>();
		int[] labelcounter = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int label = 0;
		System.out.println("imgcount: " + imgCount);
		for (int j = 0; j < imgCount; j++) {
			if (labelcounter[0] == howManyLabel && labelcounter[1] == howManyLabel && labelcounter[2] == howManyLabel
					&& labelcounter[3] == howManyLabel && labelcounter[4] == howManyLabel
					&& labelcounter[5] == howManyLabel && labelcounter[6] == howManyLabel
					&& labelcounter[7] == howManyLabel && labelcounter[8] == howManyLabel
					&& labelcounter[9] == howManyLabel) {
				return imgList;
			}
			label = inLabel.read();
			int[] pixels = new int[imgRows * imgColumns];
			ImageValue image = new ImageValue(imgRows, imgColumns);
			if (labelcounter[label] < howManyLabel) {
				labelcounter[label]++;
				for (int i = 0; i < (imgRows * imgColumns); i++) {
					pixels[i] = inIMG.read();
				}

				image.setLabel(label);
				image.setImage(pixels);
				imgList.add(image);

			} else {
				inIMG.skipBytes(imgRows * imgColumns);
			}
		}
		System.out.println("label test : " + label);
		return imgList;
	}

	public ImageValue readTestData() throws IOException { // reads a test image
															// from stream
		ImageValue testData = new ImageValue(imgRows, imgColumns);
		int[] pixels = new int[imgRows * imgColumns];
		int label = inLabel.read();
		for (int i = 0; i < (imgRows * imgColumns); i++) {
			pixels[i] = inIMG.read();
		}
		testData.setImage(pixels);
		testData.setLabel(label);
		return testData;
	}

	public void writeLearnData(String filepathIMG, String filepathLabel, ArrayList<ImageValue> images) // opens
																										// a
																										// DataOutputStream
																										// and
																										// writes
																										// images
																										// contained
																										// in
																										// the
																										// ArrayList
																										// images
			throws IOException {
		DataOutputStream ImageFile = new DataOutputStream(new FileOutputStream(new File(filepathIMG)));
		DataOutputStream LabelFile = new DataOutputStream(new FileOutputStream(new File(filepathLabel)));

		ImageFile.writeInt(2051);
		LabelFile.writeInt(2049);

		LabelFile.writeInt(images.size());
		ImageFile.writeInt(images.size());

		ImageFile.writeInt(images.get(0).getHeight());
		ImageFile.writeInt(images.get(0).getWidth());

		for (int i = 0; i < images.size(); i++) {
			int[] array = images.get(i).getPixel();
			for (int j = 0; j < array.length; j++) {
				ImageFile.writeByte(array[j]);
			}
			LabelFile.writeByte(images.get(i).getLabel());
		}
		ImageFile.close();
		LabelFile.close();
	}

	public void closeStreams() throws IOException { // closes input streams
		inIMG.close();
		inLabel.close();
	}
}