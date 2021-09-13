package machineLearning.gui;

import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Statistics extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistics frame = new Statistics();
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
	public Statistics() {
		setTitle("Statistics");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 100, 452, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(153, 23, 278, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(Testing.getUsedMethod());

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(369, 51, 62, 20);
		textField_1.setText(String.valueOf((Menue.getNumbOfLData())));
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(163, 76, 268, 20);
		textField_2.setText(Menue.getLablepath());
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(163, 144, 268, 20);
		textField_3.setText(String.valueOf(Testing.getWrongDetected()));
		contentPane.add(textField_3);

		Label label = new Label("Used Method:");
		label.setBounds(10, 23, 78, 22);
		contentPane.add(label);

		Label label_1 = new Label("Training Objects: ");
		label_1.setBounds(10, 51, 102, 22);
		contentPane.add(label_1);

		Label label_2 = new Label("Data:");
		label_2.setBounds(10, 75, 84, 22);
		contentPane.add(label_2);

		Label label_3 = new Label("Wrong Detected:");
		label_3.setBounds(10, 142, 102, 22);
		contentPane.add(label_3);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(177, 51, 62, 20);
		textField_4.setText(String.valueOf((Menue.getNumbOfLData() * 10)));
		contentPane.add(textField_4);

		Label label_4 = new Label("Total:");
		label_4.setBounds(129, 49, 42, 22);
		contentPane.add(label_4);

		Label label_5 = new Label("per Class:");
		label_5.setBounds(299, 49, 64, 22);
		contentPane.add(label_5);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(163, 168, 263, 20);
		if (Testing.getUsedMethod() == "Bayesian Net") {
			textField_5.setText(String.valueOf(Testing.getBN().percentage()));
		} else {
			textField_5.setText(String.valueOf(Testing.getKnn().percentage()));
		}
		contentPane.add(textField_5);

		Label label_6 = new Label("%:");
		label_6.setBounds(73, 170, 15, 18);
		contentPane.add(label_6);

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(163, 107, 268, 20);
		textField_6.setText(Menue.getImgpath());
		contentPane.add(textField_6);

		JLabel lblLabels = new JLabel("Labels:");
		lblLabels.setBounds(106, 79, 46, 14);
		contentPane.add(lblLabels);

		JLabel lblImages = new JLabel("Images:");
		lblImages.setBounds(106, 110, 47, 14);
		contentPane.add(lblImages);
	}
}
