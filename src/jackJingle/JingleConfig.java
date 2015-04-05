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
    public JingleConfig() {
        Scanner reader = null;
        try {
            reader = new Scanner(new File("/home/phochs/IdeaProjects/JackJingle/config/jingles.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line;
        String[] explodedLine;
        while(reader.hasNextLine()) {
            line = reader.nextLine();

            if(line.equals("") || line.startsWith("#"))
                continue;

            explodedLine = line.split(":", 2);
            config[Integer.parseInt(explodedLine[0])] = explodedLine[1];
        }
        System.out.println(Arrays.toString(config));
    }
}
