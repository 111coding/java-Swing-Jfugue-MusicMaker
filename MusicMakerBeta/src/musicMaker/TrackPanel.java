package musicMaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class TrackPanel extends JPanel {

	private Converter converter;
	int selectedIndex;
	ArrayList<JLabel> piano = new ArrayList<JLabel>();
	ArrayList<JLabel> lbNums = new ArrayList<JLabel>();

	public TrackPanel(Converter converter, int selectedIndex) {
		this.selectedIndex = selectedIndex;
		this.converter = converter;
		setBackground(Color.DARK_GRAY);
		setBounds(220, 56, 1374, 820);
		setLayout(null);

		JComboBox cmbInstName = new JComboBox();
		cmbInstName.setFont(new Font("Arial", Font.BOLD, 14));
		cmbInstName.setBackground(Color.WHITE);
		cmbInstName.setModel(new DefaultComboBoxModel(new String[] { "Piano", "Bright_Acoustic", "Electric_Grand",
				"Honkey_Tonk", "Electric_Piano", "Electric_Piano_2", "Harpsichord", "Clavinet", "Celesta",
				"Glockenspiel", "Music_Box", "Vibraphone", "Marimba", "Xylophone", "Tubular_Bells", "Dulcimer",
				"Drawbar_Organ", "Percussive_Organ", "Rock_Organ", "Church_Organ", "Reed_Organ", "Accordian",
				"Harmonica", "Tango_Accordian", "Guitar", "Steel_String_Guitar", "Electric_Jazz_Guitar",
				"Electric_Clean_Guitar", "Electric_Muted_Guitar", "Overdriven_Guitar", "Distortion_Guitar",
				"Guitar_Harmonics", "Acoustic_Bass", "Electric_Bass_Finger", "Electric_Bass_Pick", "Fretless_Bass",
				"Slap_Bass_1", "Slap_Bass_2", "Synth_Bass_1", "Synth_Bass_2", "Violin", "Viola", "Cello", "Contrabass",
				"Tremolo_Strings", "Pizzicato_Strings", "Orchestral_Strings", "Timpani", "String_Ensemble_1",
				"String_Ensemble_2", "Synth_Strings_1", "Synth_Strings_2", "Choir_Aahs", "Voice_Oohs", "Synth_Voice",
				"Orchestra_Hit", "Trumpet", "Trombone", "Tuba", "Muted_Trumpet", "French_Horn", "Brass_Section",
				"Synth_Brass_1", "Synth_Brass_2", "Soprano_Sax", "Alto_Sax", "Tenor_Sax", "Baritone_Sax", "Oboe",
				"English_Horn", "Bassoon", "Clarinet", "Piccolo", "Flute", "Recorder", "Pan_Flute", "Blown_Bottle",
				"Skakuhachi", "Whistle", "Ocarina", "Square", "Sawtooth", "Calliope", "Chiff", "Charang", "Voice",
				"Fifths", "Bass_Lead", "New_Age", "Warm", "Poly_Synth", "Choir", "Bowed", "Metallic", "Halo", "Sweep",
				"Rain", "Soundtrack", "Crystal", "Atmosphere", "Brightness", "Goblins", "Echoes", "Sci_Fi", "Sitar",
				"Banjo", "Shamisen", "Koto", "Kalimba", "Bagpipe", "Fiddle", "Shanai", "Tinkle_Bell", "Agogo",
				"Steel_Drums", "Woodblock", "Taiko_Drum", "Melodic_Tom", "Synth_Drum", "Reverse_Cymbal",
				"Guitar_Fret_Noise", "Breath_Noise", "Seashore", "Bird_Tweet", "Telephone_Ring", "Helicopter",
				"Applause", "Gunshot" }));
		cmbInstName.setBounds(0, 0, 155, 33);
		add(cmbInstName);

		JComboBox cmbBakja = new JComboBox();
		cmbBakja.setFont(new Font("Arial", Font.BOLD, 14));
		cmbBakja.setModel(new DefaultComboBoxModel(new String[] { "1/8", "1/4", "1/2", "1" }));
		cmbBakja.setBounds(159, 0, 161, 33);
		add(cmbBakja);

		// ----------------------

		JPanel mainFrame = new JPanel();
		mainFrame.setBounds(0, 0, 1350, 980);
		mainFrame.setPreferredSize(new Dimension(1350, 980));
		mainFrame.setLayout(null);

		JScrollPane trackMainPanel = new JScrollPane(mainFrame);
		trackMainPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		trackMainPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		trackMainPanel.setBounds(0, 43, 1374, 757);

		JPanel pianoPanel = new JPanel();
		pianoPanel.setBounds(0, 0, 87, 980);
		mainFrame.add(pianoPanel);

		JLabel label = new TrackPianoLabel(cmbInstName, piano);
		pianoPanel.add(label);

		// -----------------------------------------
		JLabel lbFocusInfo = new JLabel();
		lbFocusInfo.setForeground(Color.WHITE);
		lbFocusInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lbFocusInfo.setFont(new Font("Arial", Font.BOLD, 22));
		lbFocusInfo.setBounds(403, 0, 175, 33);
		add(lbFocusInfo);

		JPanel notePanel = new TrackNotePanel(cmbBakja, cmbInstName, selectedIndex, converter,piano, lbNums,lbFocusInfo);

		JScrollPane noteScrollPanel = new JScrollPane(notePanel);
		noteScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		noteScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		noteScrollPanel.setBounds(87, 0, 1268, 980);
		mainFrame.add(noteScrollPanel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		noteScrollPanel.setColumnHeaderView(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		for (int i = 0; i < converter.getMusicFile().getMadi() * 8; i++) {
			JLabel lbNum = new JLabel((i+1)+"");
			lbNum.setHorizontalAlignment(SwingConstants.CENTER);
			lbNum.setPreferredSize(new Dimension(15,20));
			lbNum.setForeground(Color.WHITE);
			lbNum.setBackground(Color.DARK_GRAY);
			lbNum.setOpaque(true);
			lbNum.setFont(new Font("Arial", Font.BOLD, 10));
			panel.add(lbNum);
			lbNums.add(lbNum);
		}

		add(trackMainPanel);

		JLabel lblSave = new JLabel("Apply");
		lblSave.setHorizontalAlignment(SwingConstants.CENTER);
		lblSave.setBackground(new Color(0, 128, 128));
		lblSave.setForeground(Color.WHITE);
		lblSave.setFont(new Font("Arial", Font.BOLD, 18));
		lblSave.setBounds(1146, 0, 228, 42);
		lblSave.setOpaque(true);
		add(lblSave);
		
		
		lblSave.addMouseListener(new MouseListener() {

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
				TrackNotePanel p = (TrackNotePanel) notePanel;
				String instSave = cmbInstName.getSelectedItem().toString();
				String resultNote = p.noteToString();
				JOptionPane.showMessageDialog(null, "Apply Success");
				converter.getTrack().get(selectedIndex).setNote(resultNote);
				converter.getTrack().get(selectedIndex).setInst(instSave);
				converter.trackSaveToMusicFile();
			}
		});
	}
}
