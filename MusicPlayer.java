import javax.sound.sampled.*;
import java.util.*;
import java.io.*;
public class MusicPlayer {
    // to store current position 
    Long currentFrame; 
    Clip clip; 
      
    // current status of clip 
    String status; 
      
    AudioInputStream audioInputStream; 
    static String filePath; 
  
    // constructor to initialize streams and clip 
    public MusicPlayer(String filepath) 
        throws UnsupportedAudioFileException, 
        IOException, LineUnavailableException  
    { 
        // create AudioInputStream object 
        audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile()); 
          
        // create clip reference 
        clip = AudioSystem.getClip(); 
          
        // open audioInputStream to the clip 
        clip.open(audioInputStream); 
          
        clip.loop(Clip.LOOP_CONTINUOUSLY); 
    } 
      
    // Method to play the audio 
    public void play(int l)  
    { 
        //start the clip 
        clip.start(); 
        clip.loop(l);
          
        status = "play"; 
    } 

    public void play() {
        //start the clip 
        clip.start(); 
          
        status = "play"; 
    }
      
    // Method to pause the audio 
    public void pause()  
    { 
        if (status.equals("paused"))  
        { 
            System.out.println("audio is already paused"); 
            return; 
        } 
        this.currentFrame =  
        this.clip.getMicrosecondPosition(); 
        clip.stop(); 
        status = "paused"; 
    } 
      
    // Method to stop the audio 
    public void stop() throws UnsupportedAudioFileException, 
    IOException, LineUnavailableException  
    { 
        currentFrame = 0L; 
        clip.stop(); 
        clip.close(); 
    } 
}