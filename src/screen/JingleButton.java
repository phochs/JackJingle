package screen;

import javazoom.jlgui.basicplayer.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Map;

/**
 * Created by phochs on 04/04/15.
 */
public class JingleButton extends Button {
    String musicFile;
    private BasicPlayer player;
    private int buttonNumber;

    public JingleButton(int buttonNumber) {
        super();

        this.buttonNumber = buttonNumber;

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1 && e.isControlDown()) {
                    JFileChooser newJingle = new JFileChooser();
                    FileFilter filter = new FileNameExtensionFilter("Music files", "mp3", "mp2", "ogg", "wav", "flac");
                    newJingle.setAcceptAllFileFilterUsed(false);
                    newJingle.setFileFilter(filter);
                    int fileResult = newJingle.showOpenDialog(null);
                    if(fileResult == JFileChooser.APPROVE_OPTION) {
                        String filename = newJingle.getSelectedFile().getAbsolutePath();
                        initButton(filename);
                    }
                }
                else if(e.getButton() == MouseEvent.BUTTON1)
                    replaySound();
                else if(e.getButton() == MouseEvent.BUTTON3)
                    stopThread();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void initButton(String filename) {
        if(filename != null) {
            musicFile = filename;
            clearButton();
            setBackground(backgroundDefault);

            String name = musicFile.substring(musicFile.lastIndexOf("/")+1);
            name = name.substring(0, name.lastIndexOf("."));
            setText(name);

            loadFile();
        } else {
            musicFile = filename;
            clearButton();
        }
    }

    public void loadFile() {
        /*JavaSoundAudioDevice audio = new JavaSoundAudioDevice();
        try {
            clip = new Player(new FileInputStream(musicFile), audio);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        File audioFile = new File(musicFile);
        player = new BasicPlayer();
        try {
            player.open(audioFile);
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }

    }

    public void playSound() {
        if(player != null) {
            if(player.getStatus() == 0) {
                stopThread();
            } else {
                playThread();
            }
        }
    }

    public void replaySound() {
        if(player != null) {
            if(isPlaying()) {
                stopThread();
            }
            playSound();
        }
    }

    public void playThread() {
        System.out.println("Playing: " + getText());
        try {
            player.play();
            setBackground(backgroundPressed);
            System.out.println("status: " + player.getStatus());

            BasicPlayerListener eventListner = new BasicPlayerListener() {
                @Override
                public void opened(Object o, Map map) {

                }

                @Override
                public void progress(int i, long l, byte[] bytes, Map map) {

                }

                @Override
                public void stateUpdated(BasicPlayerEvent basicPlayerEvent) {
                    System.out.println(basicPlayerEvent.toString());
                    System.out.println("State updated: " + basicPlayerEvent.getCode());

                    if(basicPlayerEvent.getCode() == 3 && !isPlaying())
                        setBackground(backgroundDefault);
                }

                @Override
                public void setController(BasicController basicController) {

                }
            };
            player.addBasicPlayerListener(eventListner);
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }

    public void stopThread() {
        System.out.println("Stopping: " + getText());
        /*Thread stopping = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    player.stop();
                } catch (BasicPlayerException e) {
                    e.printStackTrace();
                }
            }
        });*/
        //player.pause();
        player.closeStream();
        player = null;
        loadFile();

        //stopping.start();
        setBackground(backgroundDefault);
    }

    public boolean isPlaying() {
        if(player != null) {
            if (player.getStatus() == 0)
                return true;
        }
        return false;
    }

    public void clearButton() {
        setBackground(new Color(214, 214, 214));
        setText("");
        player = null;
    }
}
