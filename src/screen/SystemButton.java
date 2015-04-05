package screen;

import java.awt.*;

/**
 * Created by phochs on 05/04/15.
 */
public class SystemButton extends Button {
    public SystemButton(String name) {
        super();
        setText(name);
        setMargin(new Insets(10, 10, 10, 10));
    }
}
