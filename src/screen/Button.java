package screen;

import javax.swing.*;
import java.awt.*;

/**
 * Created by phochs on 05/04/15.
 */
public abstract class Button extends JTextPane {
    public Color backgroundDefault = new Color(129, 223, 112);
    public Color backgroundPressed = new Color(220, 110, 110);

    public Button() {
        super();

        setEnabled(false);
        setDisabledTextColor(new Color(0, 0, 0));

        setBackground(new Color(214, 214, 214));
    }
}
