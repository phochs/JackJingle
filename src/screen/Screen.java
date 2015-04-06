package screen;

import jackJingle.JingleConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by phochs on 04/04/15.
 */
public class Screen extends JPanel {
    JFrame drawing;
    JingleButton[] jingleButtons = new JingleButton[64];
    int currentPage = 0;

    public Screen() {
        super(true);

        // Create settings if it doesn't exist yet
        checkJingleFile(currentPage);

        //setLayout(new BorderLayout());

        setLayout(new GridLayout(8, 8, 2, 2));

        drawing = new JFrame("JackJingle page A");
        drawing.setLayout(new BorderLayout(10, 0));
        drawing.setSize(1000, 800);

        initButtons();
        loadConfig();

        drawing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawing.setLocationRelativeTo(null);
        drawing.add(this, BorderLayout.CENTER);

        drawSidebar();
        drawTop();

        drawing.setVisible(true);
    }

    public void initButtons() {
        for (int i = 0; i < 64; i++) {
            jingleButtons[i] = new JingleButton(i);
            add(jingleButtons[i]);
        }
    }

    public void loadConfig() {
        JingleConfig config = new JingleConfig(currentPage);

        for (int i = 0; i < 64; i++) {
            jingleButtons[i].initButton(config.config[i]);
        }
    }

    public void saveButtons() {
        try {
            checkJingleFile(currentPage);
            PrintWriter file = new PrintWriter("config/jingles." + currentPage + ".txt");
            for(int i=0;i<jingleButtons.length;i++) {
                if(jingleButtons[i].musicFile != null)
                file.println(currentPage + ":" + i + ":" + jingleButtons[i].musicFile);
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void checkJingleFile(int page) {
        File settings = new File("config/jingles." + page + ".txt");
        if(!settings.exists()) {
            try {
                settings.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void drawSidebar() {
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
    }

    public void drawTop() {
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 8, 2, 2));

        final String[] pages = {"A", "B", "C", "D", "E", "F", "G", "H"};

        for(int i=0;i<8;i++){
            final PageButton button = new PageButton(pages[i], i);
            button.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    switchPage(button.page);
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
            top.add(button);
        }

        drawing.add(top, BorderLayout.NORTH);
    }

    public void switchPage(int newPage) {
        System.out.println("Switching page to " + newPage);
        currentPage = newPage;

        checkJingleFile(currentPage);

        final String[] pages = {"A", "B", "C", "D", "E", "F", "G", "H"};
        drawing.setTitle("JackJingle page " + pages[newPage]);

        loadConfig();
    }
}
