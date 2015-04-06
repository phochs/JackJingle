package jackJingle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by phochs on 04/04/15.
 */
public class JingleConfig {
    public String[] config = new String[64];
    private int page;

    public JingleConfig(int page) {
        this.page = page;
        Scanner reader = null;
        try {
            reader = new Scanner(new File("config/jingles." + page + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line;
        String[] explodedLine;
        while(reader.hasNextLine()) {
            line = reader.nextLine();

            if(line.equals("") || line.startsWith("#"))
                continue;

            explodedLine = line.split(":", 3);
            if(Integer.parseInt(explodedLine[0]) == page)
            config[Integer.parseInt(explodedLine[1])] = explodedLine[2];
        }
        System.out.println(Arrays.toString(config));
    }
}
