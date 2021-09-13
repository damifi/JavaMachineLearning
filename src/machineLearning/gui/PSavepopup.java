package machineLearning.gui;

import java.awt.Checkbox;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PSavepopup extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PSavepopup frame = new PSavepopup();
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
	public PSavepopup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 100, 403, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final Checkbox checkbox = new Checkbox("check if you want to save the data persistant");
		checkbox.setState(true);
		checkbox.setBounds(51, 10, 270, 50);
		contentPane.add(checkbox);

		final JButton btnSelectPath = new JButton("Select Path");

		btnSelectPath.setBounds(285, 66, 100, 23);
		contentPane.add(btnSelectPath);

		textField = new JTextField();
		textField.setBounds(75, 66, 205, 23);
		contentPane.add(textField);
		textField.setColumns(10);

		checkbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (checkbox.getState() == false) {
					btnSelectPath.setEnabled(false);
					textField.setEnabled(false);
					;
				} else {
					btnSelectPath.setEnabled(true);
					;
					textField.setEnabled(true);
					;
				}
			}
		});

		btnNext = new JButton("Next >\r\n");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WrongRes.main(null);
				PSavepopup.this.setVisible(false);
				;
			}
		});
		btnNext.setBounds(248, 138, 91, 23);
		contentPane.add(btnNext);
	}
}
