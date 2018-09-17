package commanderKeen.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {

    private BufferedImage texture;
    private int width;
    private int height;
    private String text;
    private Color selectColor;

    public boolean selected = false;

    private int x;
    private int y;

    public Button(BufferedImage texture, String text, Color selectColor){
        this.texture = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.text = text;
        this.selectColor = selectColor;
    }

    public void update(){

    }

    public void render(Graphics2D g2d){
        if(selected){
            g2d.setColor(selectColor);
            g2d.drawRect(x, y, width, height);
        }
        g2d.drawImage(texture, x, y, null);
        g2d.drawString(text, x, y);
    }
}
