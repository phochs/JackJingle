package screen;

import jackJingle.JingleConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

/**
 * Created by phochs on 04/04/15.
 */
public class Screen extends JPanel {
    JFrame drawing;
    JingleButton[] jingleButtons = new JingleButton[64];
    public Screen() {
        super(true);

        File settings = new File("config/jingles.txt");
        if(!settings.exists())
            try {
                settings.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        //setLayout(new BorderLayout());

        setLayout(new GridLayout(8, 8, 2, 2));

        drawing = new JFrame("JackJingle");
        drawing.setLayout(new BorderLayout(10, 0));
        drawing.setSize(1000, 800);

        initButtons();
        loadConfig();

        drawing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawing.setLocationRelativeTo(null);
        drawing.add(this, BorderLayout.CENTER);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(9, 1, 5, 2));

        for(int i=0;i<7;i++){
            sidebar.add(new SystemButton(""));
        }

        SystemButton saveButton = new SystemButton("Save config");
        saveButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                saveButtons();
            }

            @Override
            public void mousePressed(MouseEvent e) {

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
        sidebar.add(saveButton);

        SystemButton stopAll = new SystemButton("Stop All");
        stopAll.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i < jingleButtons.length; i++) {
                    if (jingleButtons[i].isPlaying())
                        jingleButtons[i].stopThread();
                    //System.out.println("Button " + i + ": " + jingleButtons[i].isPlaying());
                }
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

        sidebar.add(stopAll);
        drawing.add(sidebar, BorderLayout.EAST);

        drawing.setVisible(true);
    }

    public void initButtons() {
        for (int i = 0; i < 64; i++) {
            jingleButtons[i] = new JingleButton(i);
            add(jingleButtons[i]);
        }
    }

    public void loadConfig() {
        JingleConfig config = new JingleConfig();

        for (int i = 0; i < 64; i++) {
            jingleButtons[i].initButton(config.config[i]);
        }
    }

    public void saveButtons() {
        try {
            PrintWriter file = new PrintWriter("/home/phochs/IdeaProjects/JackJingle/config/jingles.txt");
            for(int i=0;i<jingleButtons.length;i++) {
                if(jingleButtons[i].musicFile != null)
                file.println(i + ":" + jingleButtons[i].musicFile);
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
