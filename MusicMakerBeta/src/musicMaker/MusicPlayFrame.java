package musicMaker;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.JFrame;

import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.player.Player;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.rhythm.Rhythm;

public class MusicPlayFrame extends JFrame{
	
	Converter converter;
	RealtimePlayer rp1,rp2;
	Player p;
	
	class PlayThread extends Thread {

		private void playMusic() {
			Pattern p1 = new Pattern(converter.trackToString());
			Rhythm r = new Rhythm();
			String drum = converter.drumTrackToString();
			String dt[] = drum.split("@");

			for (int i = 0; i < dt.length; i++) {
				r.addLayer(dt[i]);
			}

			Pattern p2 = new Pattern(r.getPattern().setTempo(converter.getMusicFile().getBpm()));

			p1.setTempo(converter.getMusicFile().getBpm());

			p = new Player();
			p.play(p1, p2);

		}

		public void run() {
			try {
				playMusic();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	

	public MusicPlayFrame(Converter converter) {
		this.converter = converter;
		setBounds(0,0,10,10);
		//playMusic();
		PlayThread test = new PlayThread();
		test.start();
		
	}
	
	public void stop() {
		//rp1.close();
		//rp2.close();
		p.play("");
		dispose();
	}
	

	
	private void playMusic() {
		try {
			
			Pattern p1 = new Pattern(converter.trackToString());
			Rhythm r = new Rhythm();
			String drum = converter.drumTrackToString();
			String dt[] = drum.split("@");

			for (int i = 0; i < dt.length; i++) {
				r.addLayer(dt[i]);
			}
			Pattern p2 = new Pattern(r.getPattern().setTempo(converter.getMusicFile().getBpm()));
			p1.setTempo(converter.getMusicFile().getBpm());
			
						
			rp1 = new RealtimePlayer();
			rp1.play(p1);
			rp2 = new RealtimePlayer();
			rp2.play(p2);			
			
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	

}
