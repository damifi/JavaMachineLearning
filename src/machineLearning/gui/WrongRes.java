package machineLearning.gui;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import machineLearning.data.ImageDisplayer;
import machineLearning.data.ImageValue;

public class WrongRes extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WrongRes frame = new WrongRes();
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
	public WrongRes() {
		setTitle("Wrong Results");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Label label = new Label("Wrong Detected Image:");
		label.setBounds(25, -1, 140, 22);
		contentPane.add(label);

		Label label_1 = new Label("Right Label:");
		label_1.setBounds(374, -1, 95, 22);
		contentPane.add(label_1);

		Button button = new Button("Show Statistic");
		button.addActionListener(new ActionListener() {
			int i = 1;

			public void actionPerformed(ActionEvent e) {
				if (i != 0) {
					Statistics.main(null);
					i--;
				}
			}
		});
		button.setBounds(540, 25, 100, 29);
		contentPane.add(button);

		Label label_3 = new Label("Wrong Detected Label:");
		label_3.setBounds(207, -1, 140, 22);
		contentPane.add(label_3);

		// JLabel label_2 = new JLabel("");
		// label_2.setBounds(51, 27, 95, 306);
		// contentPane.add(label_2);
		ArrayList<ImageValue> wrongI = new ArrayList<ImageValue>();
		if (Testing.getUsedMethod() == "Bayesian Net") {
			wrongI = Testing.getBN().getWrongGuessedImages();
			Testing.getBN().getWrongGuessedImages();
		} else {
			wrongI = Testing.getKnn().getWrongImages();
		}
		System.out.println("wronz size: " + wrongI.size());
		for (int i = 0; i < wrongI.size(); i++) {
			BufferedImage image = ImageDisplayer.getImageFromArray(wrongI.get(i).getPixel(), wrongI.get(i).getHeight(),
					wrongI.get(i).getWidth());
			JLabel label_2 = new JLabel(new ImageIcon(image));
			label_2.setBounds(51, 27 + (i * 30), 30, 30);
			contentPane.add(label_2);
			JLabel label_4 = new JLabel(String.valueOf(wrongI.get(i).getWrongLabel()));
			label_4.setBounds(230, 30 + (i * 30), 30, 30);
			contentPane.add(label_4);
			JLabel label_5 = new JLabel(String.valueOf(wrongI.get(i).getLabel()));
			label_5.setBounds(400, 30 + (i * 30), 30, 30);
			contentPane.add(label_5);
		}

	}
}
