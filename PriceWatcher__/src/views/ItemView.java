package views;

import models.Item;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
/** A special panel to display the detail of an item. */

@SuppressWarnings("serial")
public class ItemView extends JPanel {
    private Item item = new Item();


    /** Interface to notify a click on the view page icon. */
    public interface ClickListener {

        /** Callback to be invoked when the view page icon is clicked. */
        void clicked();
    }

    /** Directory for image files: src/image in Eclipse. */
    private final static String IMAGE_DIR = "/image/";

    /** View-page clicking listener. */
    private ClickListener listener;

    /** Create a new instance. */
    public ItemView() {
        setPreferredSize(new Dimension(100, 160));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (isViewPageClicked(e.getX(), e.getY()) && listener != null) {
                    listener.clicked();
                }
            }
        });
    }

    /** Set the view-page click listener. */
    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    /** Overridden here to display the details of the item. */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 20, y = 30;

        Font boldFont = new Font("Helevetica", Font.BOLD, 12);
        Font regFont = new Font("Helvetica", Font.PLAIN, 12);
        g.setFont(boldFont);

        g.drawString("[View on Web Page]", x, y);
        y += 25;
        g.drawString("Name: ", x, y);
        y += 20;
        g.drawString("URL: ", x, y);
        y += 20;

        g.drawString("Price: ", x, y);
        y += 20;

        g.drawString("Added: ", x, y);
        y = 55;
        x = 80;
        g.setFont(regFont);
        g.drawString(item.getName(),x,y);
        y += 20;
        g.drawString(item.printURL(),x,y);
        y += 20;
        g.setColor(Color.BLUE);
        g.drawString(item.printCurrentPrice(),x,y);
        g.setColor(Color.BLACK);
        y += 20;
        g.drawString(item.getDateAdded(),x,y);
        y+= 30;
        
    }

    /** Return true if the given screen coordinate is inside the viewPage icon. */
    private boolean isViewPageClicked(int x, int y) {
        //--
        //-- WRITE YOUR CODE HERE
        //--
        return new Rectangle(20, 20, 30, 20).contains(x,  y);
    }

    /** Return the image stored in the given file. */
    public Image getImage(String file) {
        try {
            URL url = new URL(getClass().getResource(IMAGE_DIR), file);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}