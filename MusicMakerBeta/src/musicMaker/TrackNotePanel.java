package musicMaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

public class TrackNotePanel extends JPanel {

	private ArrayList<TrackNoteLabel> trlb;
	private JComboBox cmbBakja;
	JComboBox cmbInstName;
	private Converter converter;
	private int si;
	private ArrayList<JLabel> piano;
	private ArrayList<JLabel> lbNums;
	JLabel lbFocusInfo;
	String noteName[] = { "B7", "A#7", "A7", "G#7", "G7", "F#7", "F7", "E7", "D#7", "D7", "C#7", "C7", "B6", "A#6",
			"A6", "G#6", "G6", "F#6", "F6", "E6", "D#6", "D6", "C#6", "C6", "B5", "A#5", "A5", "G#5", "G5", "F#5", "F5",
			"E5", "D#5", "D5", "C#5", "C5", "B4", "A#4", "A4", "G#4", "G4", "F#4", "F4", "E4", "D#4", "D4", "C#4", "C4",
			"B3", "A#3", "A3", "G#3", "G3", "F#3", "F3", "E3", "D#3", "D3", "C#3", "C3", "B2", "A#2", "A2", "G#2", "G2",
			"F#2", "F2", "E2", "D#2", "D2", "C#2", "C2", "B1", "A#1", "A1", "G#1", "G1", "F#1", "F1", "E1", "D#1", "D1",
			"C#1", "C1" };

	public TrackNotePanel(JComboBox cmbBakja, JComboBox cmbInstName, int si, Converter converter,
			ArrayList<JLabel> piano, ArrayList<JLabel> lbNums, JLabel lbFocusInfo) {
		this.lbFocusInfo = lbFocusInfo;
		this.lbNums = lbNums;
		this.piano = piano;
		setBackground(Color.DARK_GRAY);
		this.si = si;
		this.converter = converter;
		this.cmbInstName = cmbInstName;
		this.cmbBakja = cmbBakja;
		trlb = new ArrayList<TrackNoteLabel>();
		setBounds(0, 0, 2000, 1700);
		setPreferredSize(new Dimension(2000, 1700));
		setLayout(null);

		makeNote(converter.getMusicFile().getMadi());
		stringToNote();

	}

	public void stringToNote() {
		String tem1[] = noteName;
		String test = converter.getTrack().get(si).getNote();
		String test2 = converter.getTrack().get(si).getInst();
		System.out.println(test);
		if (test.substring(0, 1).equals(" ")) {
			test = test.substring(1, test.length());
		}

		cmbInstName.setSelectedItem(test2);

		String tem[] = test.split(" ");
		String temPlus[] = new String[tem.length];
		for (int i = 0; i < tem.length; i++) {
			if (tem[i].contains("+")) {
				temPlus[i] = tem[i];
				tem[i] = "";
			} else {
				temPlus[i] = "";
			}
		}
		test1(tem);

		String temPlus2[] = new String[tem.length];
		for (int i = 0; i < temPlus2.length; i++) {
			temPlus2[i] = "";
		}

		for (int i = 0; i < temPlus.length; i++) {
			if (!(temPlus[i].equals(""))) {
				String pt[] = temPlus[i].split("\\+");
				for (int j = 0; j < pt.length; j++) {
					temPlus2[i] = pt[j];
					test1(temPlus2);
				}
			}
		}

	}

	public void test1(String tem[]) {
		String tem1[] = noteName;
		int x, y = 0;
		for (int i = 0; i < tem.length; i++) {
			if (tem[i].contains("w")) {
				x = i * 15;
				String temY = tem[i].substring(0, tem[i].length() - 1);
				for (int j = 0; j < tem1.length; j++) {
					if (tem1[j].equals(temY)) {
						y = j * 20;
					}
				}
				for (TrackNoteLabel t : trlb) {
					if (t.getX() == x && t.getY() == y) {
						t.changeToBeginImg(8);
					} else if (t.getX() == x + 15 && t.getY() == y) {
						t.changeToMidImg();
					} else if (t.getX() == x + 30 && t.getY() == y) {
						t.changeToMidImg();
					} else if (t.getX() == x + 45 && t.getY() == y) {
						t.changeToMidImg();
					} else if (t.getX() == x + 60 && t.getY() == y) {
						t.changeToMidImg();
					} else if (t.getX() == x + 75 && t.getY() == y) {
						t.changeToMidImg();
					} else if (t.getX() == x + 90 && t.getY() == y) {
						t.changeToMidImg();
					} else if (t.getX() == x + 105 && t.getY() == y) {
						t.changeToEndImg();
					}
				}
			}
			if (tem[i].contains("h")) {
				x = i * 15;
				String temY = tem[i].substring(0, tem[i].length() - 1);
				for (int j = 0; j < tem1.length; j++) {
					if (tem1[j].equals(temY)) {
						y = j * 20;
					}
				}
				for (TrackNoteLabel t : trlb) {
					if (t.getX() == x && t.getY() == y) {
						t.changeToBeginImg(4);
					} else if (t.getX() == x + 15 && t.getY() == y) {
						t.changeToMidImg();
					} else if (t.getX() == x + 30 && t.getY() == y) {
						t.changeToMidImg();
					} else if (t.getX() == x + 45 && t.getY() == y) {
						t.changeToEndImg();
					}
				}
			}
			if (tem[i].contains("q")) {
				x = i * 15;
				String temY = tem[i].substring(0, tem[i].length() - 1);
				for (int j = 0; j < tem1.length; j++) {
					if (tem1[j].equals(temY)) {
						y = j * 20;
					}
				}
				for (TrackNoteLabel t : trlb) {
					if (t.getX() == x && t.getY() == y) {
						t.changeToBeginImg(2);
					} else if (t.getX() == x + 15 && t.getY() == y) {
						t.changeToEndImg();
					}
				}
			}
			if (tem[i].contains("i") && !(tem[i].contains("R"))) {
				x = i * 15;
				String temY = tem[i].substring(0, tem[i].length() - 1);
				for (int j = 0; j < tem1.length; j++) {
					if (tem1[j].equals(temY)) {
						y = j * 20;
					}
				}
				for (TrackNoteLabel t : trlb) {
					if (t.getX() == x && t.getY() == y) {
						t.changeToOneBakjaImg();
					}
				}
			}
		}
	}

	public String noteToString() {
		String scaleArr[] = noteName;
		String bakja[] = { "i", "q", "", "h", "", "", "", "w" };
		String tem[] = new String[converter.getMusicFile().getMadi()*8];
		String result = "";
		for (int i = 0; i < tem.length; i++) {
			tem[i] = "";
			for (int j = 0; j < scaleArr.length; j++) {
				for (TrackNoteLabel t : trlb) {
					if (!(t.getNoteLen() == 0)) {
						if ((t.getY() == j * 20) && (t.getX() == i * 15)) {
							if (tem[i].equals("")) {
								tem[i] = tem[i] + scaleArr[j] + bakja[t.getNoteLen() - 1];
							} else {
								tem[i] = tem[i] + "+" + scaleArr[j] + bakja[t.getNoteLen() - 1];
							}
						}
					}
				}
			}
		}
		// 이까지 노트 읽어오기

		for (int i = 0; i < tem.length; i++) {
			if (tem[i].equals("")) {
				tem[i] = "Ri";
			}
		}

		for (int i = 0; i < tem.length; i++) {
			if (tem[i].contains("w")) {
				for (int j = i; j < i + 7; j++) {
					tem[j + 1] = ".";
				}
			} else if (tem[i].contains("h")) {
				for (int j = i; j < i + 3; j++) {
					tem[j + 1] = ".";
				}
			} else if (tem[i].contains("q")) {
				tem[i + 1] = ".";
			}
		}
		// 이까지 빈배열 처리

		for (int i = 0; i < tem.length; i++) {
			result = result + " " + tem[i];
		}

		if (result.subSequence(0, 1).equals(" ")) {
			result = result.substring(1, result.length());
		}

		return result;
	}

	public void makeNote(int madi) {
		String tem[] = noteName;
		for (int i = 0; i < 84; i++) {

			for (int j = 0; j < (madi) * 8; j++) {
				JLabel lb = new TrackNoteLabel(j * 15, i * 20);
				add(lb);
				trlb.add((TrackNoteLabel) lb);

				lb.addMouseListener(new MouseListener() {

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
						for (JLabel p : piano) {
							if (p.getText().equals(noteName[lb.getY() / 20])) {
								if (p.getText().contains("#")) {
									p.setForeground(Color.WHITE);
								} else {
									p.setForeground(Color.BLACK);
								}
								p.setFont(new Font("Arial", Font.PLAIN, 10));
							}
						}

						for (JLabel n : lbNums) {
							int num = Integer.parseInt(n.getText()) - 1;
							num = num * 15;
							if (num == lb.getX()) {
								n.setBackground(Color.DARK_GRAY);
								n.setForeground(Color.WHITE);
							}
						}
						lbFocusInfo.setText("");
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						for (JLabel p : piano) {
							if (p.getText().equals(noteName[lb.getY() / 20])) {
								p.setForeground(Color.RED);
								p.setFont(new Font("Arial", Font.BOLD, 11));
							}
						}

						for (JLabel n : lbNums) {
							int num = Integer.parseInt(n.getText()) - 1;
							num = num * 15;
							if (num == lb.getX()) {
								n.setBackground(Color.YELLOW);
								n.setForeground(Color.RED);
							}
						}
						String t1 = noteName[lb.getY() / 20];
						String t2 = lb.getX() / 15 + 1 + "";
						lbFocusInfo.setText(t1 + " / " + t2);
					}

					@Override
					public void mouseClicked(MouseEvent e) {

						if (e.getButton() == 1) {
							TrackNoteLabel t = (TrackNoteLabel) lb;

							if (cmbBakja.getSelectedItem().equals("1/8")) {
								t.changeToOneBakjaImg();
							} else if (cmbBakja.getSelectedItem().equals("1/4")) {
								t.changeToBeginImg(2);
								for (TrackNoteLabel tt : trlb) {
									if (tt.getX() == t.getX() + 15 && tt.getY() == t.getY()) {
										tt.changeToEndImg();
									}
								}
							} else if (cmbBakja.getSelectedItem().equals("1/2")) {
								t.changeToBeginImg(4);
								for (TrackNoteLabel tt : trlb) {
									if (tt.getX() == t.getX() + 15 && tt.getY() == t.getY()
											|| tt.getX() == t.getX() + 30 && tt.getY() == t.getY()) {
										tt.changeToMidImg();
									} else if (tt.getX() == t.getX() + 45 && tt.getY() == t.getY()) {
										tt.changeToEndImg();
									}
								}
							} else {
								t.changeToBeginImg(8);
								for (TrackNoteLabel tt : trlb) {
									if (tt.getX() == t.getX() + 15 && tt.getY() == t.getY()
											|| tt.getX() == t.getX() + 30 && tt.getY() == t.getY()
											|| tt.getX() == t.getX() + 45 && tt.getY() == t.getY()
											|| tt.getX() == t.getX() + 60 && tt.getY() == t.getY()
											|| tt.getX() == t.getX() + 75 && tt.getY() == t.getY()
											|| tt.getX() == t.getX() + 90 && tt.getY() == t.getY()) {
										tt.changeToMidImg();
									} else if (tt.getX() == t.getX() + 105 && tt.getY() == t.getY()) {
										tt.changeToEndImg();
									}
								}
							}
						} else if (e.getButton() == 3) {
							TrackNoteLabel t = (TrackNoteLabel) lb;
							if (t.getNoteIndex() == 4) {
								t.changeToDefaulttImg();
							} else if (t.getNoteIndex() == 1) {
								t.changeToDefaulttImg();
								for (TrackNoteLabel tt : trlb) {
									if (t.getX() + 15 == tt.getX() && tt.getY() == t.getY()
											|| t.getX() + 30 == tt.getX() && tt.getY() == t.getY()
											|| t.getX() + 45 == tt.getX() && tt.getY() == t.getY()
											|| t.getX() + 60 == tt.getX() && tt.getY() == t.getY()
											|| t.getX() + 75 == tt.getX() && tt.getY() == t.getY()
											|| t.getX() + 90 == tt.getX() && tt.getY() == t.getY()
											|| t.getX() + 105 == tt.getX() && tt.getY() == t.getY()
											|| t.getX() + 120 == tt.getX() && tt.getY() == t.getY()) {
										if (tt.getNoteIndex() == 2 || tt.getNoteIndex() == 3) {
											tt.changeToDefaulttImg();
										}
									}
								}
							}
						}

					}
				});
			}
		}

	}
}
