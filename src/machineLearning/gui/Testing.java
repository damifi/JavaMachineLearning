package machineLearning.gui;

import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import machineLearning.algorithms.BayesianNetwork;
import machineLearning.algorithms.KNN;
import machineLearning.data.ImageValue;

public class Testing extends JFrame {

	private JPanel contentPane;
	private static int numbOfTData;
	private ArrayList<ImageValue> TImages;
	private static int k;
	private static KNN knn;
	private static BayesianNetwork bn;
	private static String usedMethod;
	private static int wrongDetected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Testing frame = new Testing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Testing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Label label_1 = new Label("Testing Data:");
		label_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1.setBounds(10, 10, 103, 21);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Number of Testing Data to be learned:");
		label_2.setBounds(10, 37, 225, 23);
		contentPane.add(label_2);

		Choice choice = new Choice();
		choice.setBounds(232, 63, 144, 20);
		choice.add("K-Nearest Neighbour");
		choice.add("Bayesian Net");
		contentPane.add(choice);

		Label label_3 = new Label("Select Learning Method:");
		label_3.setBounds(87, 63, 139, 22);
		contentPane.add(label_3);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 60000 - Menue.getNumbOfLData(), 1));
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					spinner.commitEdit();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				Testing.this.numbOfTData = (int) spinner.getValue();
			}
		});

		spinner.setBounds(232, 39, 76, 18);
		contentPane.add(spinner);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 50000, 1));
		spinner_1.setBounds(232, 89, 46, 20);
		contentPane.add(spinner_1);
		spinner_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					spinner_1.commitEdit();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				Testing.this.k = (int) spinner_1.getValue();
				System.out.println(k);
			}
		});

		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (choice.getSelectedItem() == "Bayesian Net") {
					spinner_1.setEnabled(false);
				} else {
					spinner_1.setEnabled(true);
				}
			}
		});

		JButton btnNewButton = new JButton("Test");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PSavepopup.main(null);
				Testing.this.setVisible(false);
				TImages = new ArrayList<ImageValue>();
				for (int i = 0; i < numbOfTData; i++) {
					ImageValue test = null;
					try {
						test = Menue.getLd().readTestData();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					TImages.add(test);
				}
				try {
					Menue.getLd().closeStreams();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (choice.getSelectedItem() == "Bayesian Net") {
					usedMethod = choice.getSelectedItem();
					bn = new BayesianNetwork(numbOfTData);
					bn.createTables(Menue.getLImages(), Menue.getNumbOfLData());
					for (int i = 0; i < numbOfTData; i++) {
						bn.findLable(TImages.get(i), Menue.getLImages(), Menue.getNumbOfLData());
					}
					wrongDetected = (int) bn.getWrongDetected();
					System.out.println("Bayesian ist durch");
				} else {
					usedMethod = choice.getSelectedItem();
					knn = new KNN(numbOfTData);
					knn.buildMtree(Menue.getLImages());
					for (int i = 0; i < numbOfTData; i++) {
						knn.findKNN(TImages.get(i), k);
					}
					wrongDetected = (int) knn.getWrongDetected();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(152, 122, 139, 56);
		contentPane.add(btnNewButton);

		JLabel lblK = new JLabel("K:");
		lblK.setBounds(180, 91, 46, 14);
		contentPane.add(lblK);
	}

	public static int getNumbOfTData() {
		return numbOfTData;
	}

	public static KNN getKnn() {
		return knn;
	}

	public static BayesianNetwork getBN() {
		return bn;
	}

	public static String getUsedMethod() {
		return usedMethod;
	}

	public static int getWrongDetected() {
		return wrongDetected;
	}
}
