package player;

import java.util.Collections;
import java.util.Vector;

public class PlayerMain {
    public static void main(String[] args) {
        Vector<Playable> playables = new Vector<>();
        playables.add(new MusicPlayer());
        playables.add(new VideoPlayer());
        playables.add(() -> {System.out.println("Playing something else");});
        Collections.shuffle(playables);

        for(Playable playable : playables) {
            MediaController.playMedia(playable);
        }
    }
}
