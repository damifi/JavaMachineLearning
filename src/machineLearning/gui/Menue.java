package machineLearning.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import machineLearning.data.ConverterMNIST;
import machineLearning.data.ImageValue;

public class Menue {

	private JFrame frmZahlenerkennung;
	private JTextField textField;
	private JTextField textField_1;
	private static String imgpath;
	private static String lablepath;
	private static int numbOfLData;
	private static ArrayList<ImageValue> LImages;
	private static ConverterMNIST ld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menue window = new Menue();
					window.frmZahlenerkennung.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menue() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmZahlenerkennung = new JFrame();
		frmZahlenerkennung.setFont(new Font("Dialog", Font.BOLD, 12));
		frmZahlenerkennung.setTitle("Zahlenbildererkennungs Applikation");
		frmZahlenerkennung.setBounds(100, 100, 577, 270);
		frmZahlenerkennung.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmZahlenerkennung.getContentPane().setLayout(null);

		JButton btnSelectPath = new JButton("Select Path");
		btnSelectPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				if (f.getAbsolutePath().endsWith(".idx3-ubyte")) {
					imgpath = f.getAbsolutePath();
					textField.setText(imgpath);
				} else {
					textField.setText("Not supported data type! Reselect please.");
				}
			}
		});
		btnSelectPath.setBounds(423, 34, 113, 23);
		frmZahlenerkennung.getContentPane().add(btnSelectPath);

		textField = new JTextField();
		textField.setBounds(113, 34, 300, 23);
		frmZahlenerkennung.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblPath = new JLabel("Image File Path:");
		lblPath.setBounds(10, 34, 93, 23);
		frmZahlenerkennung.getContentPane().add(lblPath);

		JButton button = new JButton("Select Path");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				if (f.getAbsolutePath().endsWith(".idx1-ubyte")) {
					lablepath = f.getAbsolutePath();
					textField_1.setText(lablepath);
				} else {
					textField_1.setText("Not supported data type! Reselect please.");
				}
			}
		});
		button.setBounds(423, 68, 113, 23);
		frmZahlenerkennung.getContentPane().add(button);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(113, 68, 300, 23);
		frmZahlenerkennung.getContentPane().add(textField_1);

		JLabel lblFilePath = new JLabel("Lable File Path:");
		lblFilePath.setBounds(10, 68, 103, 23);
		frmZahlenerkennung.getContentPane().add(lblFilePath);

		JLabel lblNumberOf = new JLabel("Learning Data to be learned for each Label:");
		lblNumberOf.setBounds(10, 136, 288, 23);
		frmZahlenerkennung.getContentPane().add(lblNumberOf);

		Label label = new Label("Learning Data:");
		label.setFont(new Font("Dialog", Font.BOLD, 13));
		label.setBounds(10, 10, 103, 21);
		frmZahlenerkennung.getContentPane().add(label);

		JButton btnNewButton = new JButton("LEARN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Testing.main(null);
				frmZahlenerkennung.setVisible(false);
				ld = new ConverterMNIST(imgpath, lablepath);
				try {
					ld.openIMG();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					ld.openLabel();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					LImages = ld.readLearnData(numbOfLData);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(191, 171, 200, 50);
		frmZahlenerkennung.getContentPane().add(btnNewButton);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 5500, 5));
		spinner.setBounds(274, 138, 78, 18);
		frmZahlenerkennung.getContentPane().add(spinner);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					spinner.commitEdit();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				numbOfLData = (int) spinner.getValue();
			}
		});

	}

	public static String getImgpath() {
		return imgpath;
	}

	public static String getLablepath() {
		return lablepath;
	}

	public static int getNumbOfLData() {
		return numbOfLData;
	}

	public static ArrayList<ImageValue> getLImages() {
		return LImages;
	}

	public static ConverterMNIST getLd() {
		return ld;
	}

}
