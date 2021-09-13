package machineLearning.data;

import java.util.ArrayList;

import machineLearning.algorithms.BayesianNetwork;

public class test {
	public static void main(String[] args) throws Exception {
		ConverterMNIST db = new ConverterMNIST("H:/Eclipse/ImageData/train-images.idx3-ubyte",
				"H:/Eclipse/ImageData/train-labels.idx1-ubyte");
		int test[] = db.openIMG();
		System.out.println("Number of Images: " + test[0]);
		System.out.println("Number of Rows: " + test[1]);
		System.out.println("Number of Columns: " + test[2]);

		int test1 = db.openLabel();
		System.out.println("Number of Labels: " + test1);

		ArrayList<ImageValue> images = db.readLearnData(1000);
		System.out.println("Fertig");
		System.out.println("Elements of Learndata: " + images.size());

		ImageValue testdata = db.readTestData();
		db.closeStreams();

		// KNN knn = new KNN(5);
		// knn.buildMtree(images);
		// knn.findKNN(testdata, 10);

		System.out.println("Label von testdata: " + testdata.getLabel());
		System.out.println("WrongLabel von testdata: " + testdata.getWrongLabel());

		ImageDisplayer imagetest = new ImageDisplayer();
		// imagetest.displayImage(images);

		BayesianNetwork bayes = new BayesianNetwork(5);
		bayes.findLable(testdata, images, 1000);

	}

}
