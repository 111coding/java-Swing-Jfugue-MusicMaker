package musicMaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class TrackPianoLabel extends JLabel {

	private JComboBox cmbInstName;
	private String whiteNote[] = { "C", "D", "E", "F", "G", "A", "B" };
	private String blackNote[] = { "C#", "D#", "F#", "G#", "A#" };
	private ArrayList<JLabel> piano;

	public TrackPianoLabel(JComboBox cmbInstName,ArrayList<JLabel> piano) {
		this.piano = piano;
		this.cmbInstName = cmbInstName;
		setBounds(0, 0, 87, 980);
		setIcon(new ImageIcon("img/piano1.png"));
		createWhiteNoteLabel();
		createBlackNoteLabel();
	}

	private void createWhiteNoteLabel() {
		int h = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < whiteNote.length; j++) {
				JLabel lb = new JLabel(whiteNote[6 - j] + (7 - i));
				lb.setFont(new Font("Arial", Font.PLAIN, 10));
				lb.setBounds(50, h, 35, 20);
				lb.setHorizontalAlignment(SwingConstants.RIGHT);
				add(lb);
				h = h + 20;
				piano.add(lb);
				lb.addMouseListener(new EventListner(lb));
			}
		}
	}

	private void createBlackNoteLabel() {
		int h = 10;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < blackNote.length; j++) {
				JLabel lb = new JLabel(blackNote[4 - j] + (7 - i));
				lb.setFont(new Font("Arial", Font.PLAIN, 9));
				lb.setForeground(Color.WHITE);
				lb.setBounds(23, h, 20, 20);
				lb.setHorizontalAlignment(SwingConstants.RIGHT);
				add(lb);
				if ((j == 2) || (j == 4)) {
					h = h + 40;
				} else {
					h = h + 20;
				}
				piano.add(lb);
				lb.addMouseListener(new EventListner(lb));
			}
		}
	}

	class PlayThread implements Runnable {
		private String clickedNote = "";

		public PlayThread(String clickedNote) {
			this.clickedNote = clickedNote;
		}

		@Override
		public void run() {
			Pattern pt = new Pattern(clickedNote + "q").setInstrument(cmbInstName.getSelectedItem().toString());
			Player p = new Player();
			p.play(pt);
		}
	}

	class EventListner implements MouseListener {
		private JLabel lb;
		public EventListner(JLabel lb) {
			this.lb = lb;
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			String clickedNote = lb.getText();
			Thread t = new Thread(new PlayThread(clickedNote));
			t.start();
		}
	}
}
