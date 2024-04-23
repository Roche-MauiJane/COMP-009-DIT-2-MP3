package COMP009;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class MP3 {
    private Clip clip;

    public static void main(String[] args) {
        MP3 player = new MP3();
        int choice = player.getUserChoice();

        switch (choice) {
            case 1:
                player.playMp3("drivers license.mp3");
                break;
            case 2:
                player.playMp3("Hurts So Good.mp3");
                break;
            case 3:
                player.playMp3("You'll Be Safe Here.mp3");
                break;
            default:
                System.out.println("Invalid choice. Please select a number between 1, 2, and 3.");
                return;
        }

        if (player.clip != null) {
            player.controlMusic();
        }
    }

    public int getUserChoice() {
        System.out.println
        ("Enter the number of the song you want to play: ");
        System.out.println
        ("1. drivers license\n2. Hurts So Good \n3. You'll Be Safe Here");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a your choice: ");
        return scanner.nextInt();
    }

    public void playMp3(String filename) {
        String filePath = "Playlist/" + filename;
        URL url = MP3.class.getResource(filePath);
        if (url == null) {
            System.out.println("File not found: " + filePath);
            return;
        }
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("Error playing MP3: " + e.getMessage());
        }
    }

    public void controlMusic() {
        System.out.println("Press 1 to pause, 2 to play, and any other key to exit.");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (clip.isRunning()) {
                        clip.stop();
                    }
                    break;
                case 2:
                    if (!clip.isRunning()) {
                        clip.start();
                    }
                    break;
                default:
                    if (clip.isRunning()) {
                        clip.stop();
                    }
                    clip.close();
                    return;
            }
        }
    }
}