import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer{
	static private boolean volume = true;
	File file1,file2,file3,file4,file5,file6,file7,file8,file9,file10;
	AudioInputStream audioStream1, audioStream2, audioStream3,audioStream4,
	audioStream5 ,audioStream6,audioStream7,audioStream8,audioStream9,audioStream10;
	Clip clip1,clip2,clip3,clip4,clip5, clip6,clip7,clip8,clip9,clip10;
	public AudioPlayer() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		file1 = new File(".\\src\\Sound\\menu.wav");
		file2 = new File(".\\src\\Sound\\movement.wav");
		file3 = new File(".\\src\\Sound\\defeat.wav");
		file4 = new File(".\\src\\Sound\\apple.wav");
		file5 = new File(".\\src\\Sound\\start_game.wav");
		file6 = new File(".\\src\\Sound\\difficulty_select.wav");
		file7 = new File(".\\src\\Sound\\volume_on.wav");
		file8 = new File(".\\src\\Sound\\error.wav");
		file9 = new File(".\\src\\Sound\\high_score.wav");
		file10 = new File(".\\src\\Sound\\volume_off.wav");
		audioStream1 = AudioSystem.getAudioInputStream(file1);
		audioStream2 = AudioSystem.getAudioInputStream(file2);
		audioStream3 = AudioSystem.getAudioInputStream(file3);
		audioStream4 = AudioSystem.getAudioInputStream(file4);
		audioStream5 = AudioSystem.getAudioInputStream(file5);
		audioStream6 = AudioSystem.getAudioInputStream(file6);
		audioStream7 = AudioSystem.getAudioInputStream(file7);
		audioStream8 = AudioSystem.getAudioInputStream(file8);
		audioStream9 = AudioSystem.getAudioInputStream(file9);
		audioStream10 = AudioSystem.getAudioInputStream(file10);
		clip1 = AudioSystem.getClip();
		clip2 = AudioSystem.getClip();
		clip3 = AudioSystem.getClip();
		clip4 = AudioSystem.getClip();
		clip5 = AudioSystem.getClip();
		clip6 = AudioSystem.getClip();
		clip7 = AudioSystem.getClip();
		clip8 = AudioSystem.getClip();
		clip9 = AudioSystem.getClip();
		clip10 = AudioSystem.getClip();
		clip1.open(audioStream1);
		clip2.open(audioStream2);
		clip3.open(audioStream3);
		clip4.open(audioStream4);
		clip5.open(audioStream5);
		clip6.open(audioStream6);
		clip7.open(audioStream7);
		clip8.open(audioStream8);
		clip9.open(audioStream9);
		clip10.open(audioStream10);
	}
	public void turnVolumeOnOff() {
		volume = !volume;
		if (volume) { volumeTurnedOn(); }
		else { volumeTurnedOff(); }
	}
	public boolean getVolumeOnOrOff() {
		return volume;
	}
	public void playEffect(int i) {
		if (!volume)
			return;
		switch(i) {
		case 1: playMenuSound();
		break;
		case 2: playMoveSound();
		break;
		case 3: playLoseSound();
		break;
		case 4: playAppleSound();
		break;
		case 5: playGameStart();
		break;
		case 6: playDifficultySelectSound();
		break;
		case 7: volumeTurnedOn();
		break;
		case 8: error();
		break;
		case 9: newHighScore();
		break;
		case 10: volumeTurnedOff();
		}
	}
	private void playMenuSound() {
		clip1.setMicrosecondPosition(0);
		clip1.start();
	}
	private void playMoveSound() {
		clip2.setMicrosecondPosition(0);
		clip2.start();

	}
	private void playLoseSound() {
		clip3.setMicrosecondPosition(0);
		clip3.start();
	}	
	private void playAppleSound() {
		clip4.setMicrosecondPosition(0);
		clip4.start();
	}
	private void playGameStart() {
		clip5.setMicrosecondPosition(0);
		clip5.start();
	}
	private void playDifficultySelectSound() {
		clip6.setMicrosecondPosition(0);
		clip6.start();
	}
	private void volumeTurnedOn() {
		clip7.setMicrosecondPosition(0);
		clip7.start();
	}
	private void error() {
		clip8.setMicrosecondPosition(0);
		clip8.start();
	}
	private void newHighScore() {
		clip9.setMicrosecondPosition(0);
		clip9.start();
	}
	private void volumeTurnedOff() {
		clip10.setMicrosecondPosition(0);
		clip10.start();
	}
}

 