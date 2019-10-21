package musicMaker;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.google.gson.Gson;

import model.MusicFile;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.awt.event.ActionEvent;

public class NewFileFrame extends JFrame {
	private JTextField textField;

	public NewFileFrame(DefaultListModel listModel) {
		setSize(442, 113);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("FileName : ");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(12, 10, 92, 21);
		getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.BOLD, 12));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(96, 10, 324, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Make File");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newFile(textField.getText() + ".music");
				listModel.addElement(textField.getText() + ".music");
				JOptionPane.showMessageDialog(null, "Apply Success");
				dispose();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBounds(272, 41, 148, 23);
		getContentPane().add(btnNewButton);
		setVisible(true);

	}

	private void newFile(String fileName) {
		Gson gson = new Gson();
		String result = gson.toJson(new MusicFile(fileName, 1, 8, 120,
				"V0 I[piano] Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri",
				"................................@................................@................................@................................@................................"));

		try {
			OutputStream output = new FileOutputStream(new File("musicFile/" + fileName));
			byte[] by = result.getBytes();
			output.write(by);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
