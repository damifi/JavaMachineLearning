package machineLearning.algorithms;

import java.util.ArrayList;
import java.util.List;

import machineLearning.algorithms.KdTree.Entry;
import machineLearning.algorithms.KdTree.Manhattan;
import machineLearning.data.ImageValue;

public class KNN {
	private KdTree<Integer> tree; // KdTree containing all training data images
									// as nodes
	private double runs; // number of test runs
	private double wrongGuesses = 0; // number of wrongly found images
	private ArrayList<ImageValue> wrongGuessedImages; // ArrayList containing
														// all wrongly found
														// images

	public KNN(int n) { // constructor
		this.runs = n;
		this.wrongGuessedImages = new ArrayList<ImageValue>();
	}

	public ArrayList<ImageValue> getWrongImages() { // returns ArrayList of
													// wrongly found images
		return wrongGuessedImages;
	}

	public double percentage() { // returns percentage of wrongly found images
		double p;
		p = (wrongGuesses / runs) * 100;
		return p;
	}

	public double getWrongDetected() { // returns number of wrongly found images
		return wrongGuesses;
	}

	public void buildMtree(ArrayList<ImageValue> list) { // builds a KdTree
															// containing all
															// training images
															// as nodes
		tree = new Manhattan<Integer>((list.get(0).getHeight() * list.get(0).getWidth()), Integer.MAX_VALUE);

		for (int i = 0; i < list.size(); i++) {
			list.get(i).intToDouble();
			tree.addPoint(list.get(i).getDPixel(), list.get(i).getLabel());
		}
		System.out.println("Size of tree: " + tree.size());
	}

	public void findKNN(ImageValue testdata, int count) { // takes a test image
															// and an integer(K)
															// and finds a label
															// by computing the
															// K nearest
															// neighbours
		System.out.println();
		testdata.intToDouble();
		List<Entry<Integer>> nearestEntries = tree.nearestNeighbor(testdata.getDPixel(), count, false);
		int[] label = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < nearestEntries.size(); i++) {
			label[nearestEntries.get(i).value]++;
			for (int j = 0; j < label.length; j++) {
				System.out.print(label[j] + " ");
			}
			System.out.println();
		}

		int labelToUse = 0;
		int temp = 0;
		for (int i = 0; i < label.length; i++) {
			if (label[i] > temp) {
				temp = label[i];
				labelToUse = i;
			}
		}
		System.out.println("Guessed Label = " + labelToUse);
		if (testdata.getLabel() != labelToUse) {
			wrongGuesses++;
			testdata.setWrongLabel(labelToUse);
			wrongGuessedImages.add(testdata);
		}
	}

}
