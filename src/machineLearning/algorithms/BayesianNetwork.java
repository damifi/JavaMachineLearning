package machineLearning.algorithms;

import java.util.ArrayList;

import machineLearning.data.ImageValue;

public class BayesianNetwork {
	private double runs; // number of test runs
	private double wrongGuesses = 0; // number of wrongly guessed labels
	private ArrayList<ImageValue> wrongGuessedImages = new ArrayList<ImageValue>(); // ArrayList
																					// containing
																					// all
																					// wrongly
																					// guessed
																					// images
	private ArrayList<double[][]> bayesianTables;// ArrayList containing tables
													// of every pixel which
													// contain probabilities for
													// all pixel values for
													// every label

	public BayesianNetwork(int runs) { // constructor
		this.runs = runs;
	}

	public double percentage() { // returns percentage of wrongly found images
		double p;
		p = (wrongGuesses / runs) * 100;
		return p;
	}

	public double getWrongDetected() { // return number of wrongly found images
		return wrongGuesses;
	}

	public ArrayList<ImageValue> getImagesWithLabel(ArrayList<ImageValue> imgList, int label) { // return
																								// an
																								// ArrayList
																								// containing
																								// only
																								// images
																								// with
																								// labels
																								// specified
																								// as
																								// an
																								// integer
		ArrayList<ImageValue> img = new ArrayList<ImageValue>();
		for (int i = 0; i < imgList.size(); i++) {
			if (imgList.get(i).getLabel() == label) {
				img.add(imgList.get(i));
			}
		}

		return img;
	}

	public void createTables(ArrayList<ImageValue> imgList, int labelAnz) { // creates
																			// all
																			// tables
																			// needed
																			// to
																			// run
																			// bayesian
																			// network
																			// algorithm
		int count = 0;
		ArrayList<double[][]> tables = new ArrayList<double[][]>();
		for (int i = 0; i < imgList.get(0).getHeight() * imgList.get(0).getWidth(); i++) {
			double[][] table = new double[10][256];
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 255; k++) {
					table[j][k] = 0.0;
				}
			}
			tables.add(table);

		}
		System.out.println("Size of tables: " + tables.size());
		for (int p = 0; p < 10; p++) {
			ArrayList<ImageValue> temp = new ArrayList<ImageValue>();
			temp = getImagesWithLabel(imgList, p);
			double[][] tempTable = new double[labelAnz][imgList.get(0).getHeight() * imgList.get(0).getWidth()];
			for (int i = 0; i < labelAnz; i++) {
				for (int j = 0; j < temp.size(); j++) {
					temp.get(j).intToDouble();
				}
				double[] pixels = new double[imgList.get(0).getHeight() * imgList.get(0).getWidth()];
				pixels = temp.get(i).getDPixel();
				for (int k = 0; k < imgList.get(0).getHeight() * imgList.get(0).getWidth(); k++) {
					tempTable[i][k] = pixels[k];
				}
			}

			for (int j = 0; j < imgList.get(0).getHeight() * imgList.get(0).getWidth(); j++) {
				double[] table1 = new double[256];
				for (double k = 0.0; k <= 255.0; k++) {
					for (int i = 0; i < labelAnz; i++) {
						if (tempTable[i][j] == k) {
							count++;
						}
					}
					table1[(int) k] = (double) (count + 1) / (double) (labelAnz + 255);
					count = 0;
				}
				for (int l = 0; l <= 255; l++) {

					tables.get(j)[p][l] = table1[l];
				}
			}
		}
		System.out.println("tablesize: " + tables.size());
		System.out.println("count: " + count);
		System.out.println("labelanz: " + labelAnz);
		bayesianTables = tables;
		// return tables;
	}

	public void findLable(ImageValue example, ArrayList<ImageValue> images, int labelAnz) { // finds
																							// a
																							// label
																							// for
																							// a
																							// test
																							// image,
																							// labelAnz
																							// specifies
																							// number
																							// of
																							// images
																							// per
																							// label

		int[] examplePixelValues = example.getPixel();
		double temp;
		double sum = 0.0;
		double optsum = 0.0;
		int foundLabel = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < examplePixelValues.length; j++) {
				temp = bayesianTables.get(j)[i][examplePixelValues[j]];
				sum = sum + Math.log(temp);
			}
			if (i == 0) {
				optsum = sum;
			}
			if (sum > optsum) {
				optsum = sum;
				foundLabel = i;
			}
			sum = 0.0;
		}
		System.out.println("found label: " + foundLabel);
		System.out.println("Label of testdata: " + example.getLabel());
		if (example.getLabel() != foundLabel) {
			wrongGuesses++;
			example.setWrongLabel(foundLabel);
			wrongGuessedImages.add(example);

		}
	}

	public ArrayList<ImageValue> getWrongGuessedImages() { // returns ArrayList
															// of wrongly found
															// images
		return wrongGuessedImages;
	}

}
