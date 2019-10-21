package musicMaker;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;

import com.google.gson.Gson;

import model.MusicFile;
import model.Track;

public class MusicMakerApp2 extends JFrame {

	protected static final String String = null;
	private JPanel contentPane;
	private JTable table;
	private String username;
	private JPanel gui;
	private Converter converter;
	private JPanel panelMain;
	private DefaultTableModel tableModel;
	private JList list;
	private DefaultListModel listModel;
	private MusicPlayFrame playMusicBt;



	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusicMakerApp2 frame = new MusicMakerApp2();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param password
	 * @param username
	 */

	public MusicMakerApp2() {
		this(null);
	}

	public MusicMakerApp2(String username) {

		panelMain = new JPanel();
		getContentPane().add(panelMain);

		this.username = username;
		setTitle("MusicMaker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 920);
		setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("NEW");
		mnFile.add(mntmNew);
		mntmNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new NewFileFrame(listModel);
			}
		});

		JMenuItem mntmSave = new JMenuItem("SAVE");
		mnFile.add(mntmSave);
		mntmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile();
				JOptionPane.showMessageDialog(null, "Save Success");
			}
		});

		JMenuItem mntmDelete = new JMenuItem("DELETE");
		mnFile.add(mntmDelete);
		mntmDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteFile(list.getSelectedValue().toString());
				listModel.remove(list.getSelectedIndex());
				JOptionPane.showMessageDialog(null, "Delete Success");
			}
		});

		JMenuItem mntmExit = new JMenuItem("EXIT");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
		});
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelTop = new JPanel();
		panelTop.setBackground(SystemColor.controlDkShadow);
		panelTop.setBounds(220, 0, 1374, 46);
		contentPane.add(panelTop);
		panelTop.setLayout(null);

		JButton btnPlay = new JButton("\u25B6");
		btnPlay.setBackground(Color.DARK_GRAY);
		btnPlay.setForeground(new Color(154, 205, 50));
		btnPlay.setBounds(0, 0, 46, 46);
		btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playMusicBt = new MusicPlayFrame(converter);
			}
		});

		panelTop.add(btnPlay);

		JButton btnStop = new JButton("\u25A0");
		btnStop.setBackground(Color.DARK_GRAY);
		btnStop.setForeground(new Color(154, 205, 50));
		btnStop.setBounds(49, 0, 46, 46);
		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playMusicBt.stop();				
			}
		});
		panelTop.add(btnStop);

		JSpinner spinnerBPM = new JSpinner();
		spinnerBPM.setModel(new SpinnerNumberModel(new Integer(120), null, null, new Integer(1)));
		spinnerBPM.setFont(new Font("Arial", Font.BOLD, 24));
		spinnerBPM.setForeground(Color.WHITE);
		spinnerBPM.setBackground(Color.DARK_GRAY);
		spinnerBPM.setBounds(147, 0, 132, 46);
		panelTop.add(spinnerBPM);
		spinnerBPM.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				int tem = Integer.parseInt(spinnerBPM.getValue().toString());
				System.out.println(tem);
				converter.setBPM(tem);
				converter.getMusicFile().setBpm(tem);
			}
		});

		JComboBox cmbTrack = new JComboBox();
		cmbTrack.setFont(new Font("Arial", Font.BOLD, 17));
		cmbTrack.setBounds(367, 0, 105, 46);
		panelTop.add(cmbTrack);

		JButton btnAddTrack = new JButton("AddTrack");
		btnAddTrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				converter.addTrackAndSaveToMusicFile();
				cmbTrack.removeAllItems();
				cmbTrack.addItem("---");
				for (Track t : converter.getTrack()) {
					cmbTrack.addItem(t.getTrackNum());
				}
				cmbTrack.addItem("Drum");

			}
		});
		btnAddTrack.setFont(new Font("Arial", Font.BOLD, 14));
		btnAddTrack.setForeground(Color.WHITE);
		btnAddTrack.setBackground(Color.ORANGE);
		btnAddTrack.setBounds(635, -1, 105, 46);
		panelTop.add(btnAddTrack);

		JButton btnRemovetrack = new JButton("RemoveTrack");
		btnRemovetrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(cmbTrack.getSelectedItem().toString().equals("---"))) {
					remove(panelMain);
					int delNum = Integer.parseInt(cmbTrack.getSelectedItem().toString());
					converter.removeTrackAndSaveToMusicFile(delNum);
					cmbTrack.removeAllItems();
					cmbTrack.addItem("---");
					for (Track t : converter.getTrack()) {
						cmbTrack.addItem(t.getTrackNum());
					}
					cmbTrack.addItem("Drum");
				}

			}
		});
		btnRemovetrack.setForeground(Color.WHITE);
		btnRemovetrack.setFont(new Font("Arial", Font.BOLD, 14));
		btnRemovetrack.setBackground(Color.ORANGE);
		btnRemovetrack.setBounds(752, -1, 141, 46);
		panelTop.add(btnRemovetrack);

		JLabel lblBpm = new JLabel("BPM");
		lblBpm.setFont(new Font("Arial", Font.BOLD, 12));
		lblBpm.setForeground(Color.WHITE);
		lblBpm.setBounds(107, 12, 39, 21);
		panelTop.add(lblBpm);

		JLabel lblTrack = new JLabel("Track");
		lblTrack.setForeground(Color.WHITE);
		lblTrack.setFont(new Font("Arial", Font.BOLD, 12));
		lblTrack.setBounds(329, 12, 39, 21);
		panelTop.add(lblTrack);

		JButton btnLoadTrack = new JButton("LoadTrack");
		btnLoadTrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cmbTrack.getSelectedItem().equals("Drum")) {
					remove(panelMain);
					panelMain = new DrumPanel(converter);
					getContentPane().add(panelMain);
					repaint();
					revalidate();
				} else if ((cmbTrack.getSelectedItem().equals("---"))) {
					remove(panelMain);
					repaint();
				} else {
					remove(panelMain);
					panelMain = new TrackPanel(converter, Integer.parseInt(cmbTrack.getSelectedItem().toString()));
					getContentPane().add(panelMain);
					repaint();
					revalidate();
				}
			}
		});
		btnLoadTrack.setForeground(Color.WHITE);
		btnLoadTrack.setFont(new Font("Arial", Font.BOLD, 14));
		btnLoadTrack.setBackground(Color.ORANGE);
		btnLoadTrack.setBounds(482, -1, 141, 46);
		panelTop.add(btnLoadTrack);

		JSpinner spinnerBar = new JSpinner();
		spinnerBar.setForeground(Color.WHITE);
		spinnerBar.setFont(new Font("Arial", Font.BOLD, 24));
		spinnerBar.setBackground(Color.DARK_GRAY);
		spinnerBar.setBounds(973, 0, 132, 46);
		panelTop.add(spinnerBar);
		spinnerBar.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				converter.getMusicFile().setMadi(Integer.parseInt(spinnerBar.getValue().toString()));
			}
		});

		JLabel lblBar = new JLabel("Bar");
		lblBar.setForeground(Color.WHITE);
		lblBar.setFont(new Font("Arial", Font.BOLD, 12));
		lblBar.setBounds(933, 12, 39, 21);
		panelTop.add(lblBar);

//		JPanel panelMain = new TrackPanel(converter);
//		contentPane.add(panelMain);
//		setVisible(true);

		JPanel panelFile = new JPanel();
		panelFile.setBackground(Color.GRAY);
		panelFile.setBounds(0, 0, 208, 850);
		contentPane.add(panelFile);
		panelFile.setLayout(null);

		String[] fileNames = getFilenames();

		listModel = new DefaultListModel();
		for (int i = 0; i < fileNames.length; i++) {
			listModel.addElement(fileNames[i]);
		}
		
		list = new JList(listModel);
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Arial", Font.BOLD, 12));
		list.setBackground(Color.DARK_GRAY);
		list.setBounds(6, 6, 196, 840);
		panelFile.add(list);
		list.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					System.out.println();
					Gson gson = new Gson();
					String data = readFile(list.getSelectedValue().toString());
					MusicFile m = gson.fromJson(data, MusicFile.class);
					converter = new Converter(m);

					spinnerBPM.setValue(converter.getMusicFile().getBpm());
					cmbTrack.removeAllItems();
					cmbTrack.addItem("---");
					for (Track t : converter.getTrack()) {
						cmbTrack.addItem(t.getTrackNum());
					}
					cmbTrack.addItem("Drum");
					spinnerBar.setValue(converter.getMusicFile().getMadi());
					remove(panelMain);
					repaint();
					revalidate();
					JOptionPane.showMessageDialog(null, "Load Success");
				}
			}
		});

		setVisible(true);
	}

	private String readFile(String filename) {
		String test = "";
		try {
			FileReader file_reader = new FileReader(new File("musicFile/" + filename));
			int cur = 0;
			while ((cur = file_reader.read()) != -1) {
				test = test + (char) cur;
			}
			file_reader.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return test;
	}
	
	private void newFile(String fileName) {
		Gson gson = new Gson();
		String result = gson.toJson(new MusicFile(fileName, 1, 8, 120, "V0 I[piano] Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri Ri", "................................@................................@................................@................................@................................"));

		try {
			OutputStream output = new FileOutputStream(new File("musicFile/" + fileName));
			byte[] by = result.getBytes();
			output.write(by);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	private void saveFile() {
		Gson gson = new Gson();
		String result = gson.toJson(converter.getMusicFile());

		try {
			OutputStream output = new FileOutputStream(new File("musicFile/" + converter.getMusicFile().getFilename()));
			byte[] by = result.getBytes();
			output.write(by);

		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	private void deleteFile(String fileName) {
		File file = new File("musicFile/" + fileName);
		file.delete();
	}

	private String[] getFilenames() {
		String path = "musicFile/";
		File dirFile = new File(path);
		File[] fileList = dirFile.listFiles();
		String result = "";
		for (File tempFile : fileList) {
			if (tempFile.isFile()) {
				String tempPath = tempFile.getParent();
				String tempFileName = tempFile.getName();
				if (tempFileName.substring(tempFileName.length() - 6, tempFileName.length()).equals(".music")) {
					result = result + "@" + tempFileName;
				}
			}
		}
		return result.split("@");
	}
}