package musicMaker;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DrumNote extends JLabel{
	private int currentValue = 0;
	private int x,y;
	ArrayList<JLabel> lbNums;
	
	private ImageIcon notSelected = new ImageIcon("img/drumNoteNotSelected.png");
	private ImageIcon selected = new ImageIcon("img/drumNoteSelected.png");
	
	public DrumNote(int x,int y,ArrayList<JLabel> lbNums) {
		
		this.x = x;
		this.y = y;
		this.lbNums = lbNums;
		
		setIcon(notSelected);
		setBackground(Color.GRAY);
		setBounds(x, y, 60, 60);
		setSize(60,60);
		addMouseListener(new EventListener());	
		
	}
	
	

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
		if(currentValue==1) {
			setIcon(selected);
		}else {
			setIcon(notSelected);
		}
	}

	public int getCurrentValue() {
		return currentValue;
	}
	
	
	//event
	class EventListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(currentValue == 0) {				
				setIcon(selected);
				currentValue = 1;
			}else {
				setIcon(notSelected);
				currentValue = 0;
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			for (JLabel b : lbNums) {
				if(b.getX()==x) {
					b.setBackground(Color.YELLOW);
					b.setForeground(Color.RED);
				}
			}			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			for (JLabel b : lbNums) {
				if(b.getX()==x) {
					b.setBackground(Color.DARK_GRAY);
					b.setForeground(Color.WHITE);
				}
			}			
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
	}	

}
