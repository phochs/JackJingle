package screen;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by phochs on 05/04/15.
 */
public class PageButton extends Button {
    int page = 0;

    public PageButton(String name, int page) {
        super();
        setText(name);
        this.page = page;

        setMargin(new Insets(10, 10, 10, 10));

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(backgroundDefault);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(new Color(214, 214, 214));
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
