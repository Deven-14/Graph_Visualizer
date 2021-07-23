package frontEnd;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;

public class Circle {

    public Circle(Graphics2D g2d, int x, int y, Integer nodeId)
    {        
        g2d.setPaint(Color.black);
        g2d.fillOval(x, y, 50, 50);
        g2d.setPaint(Color.white);
        g2d.setFont(new Font(null, Font.BOLD, 20));
        g2d.drawString(nodeId.toString(), x+20, y+30);
    }

    public Circle(Graphics2D g2d, int x, int y, Integer nodeId, boolean isSelected)
    {        
        g2d.setPaint(new Color(0x30d5c8));
        g2d.fillOval(x, y, 50, 50);
        g2d.setPaint(Color.black);
        g2d.setFont(new Font(null, Font.BOLD, 20));
        g2d.drawString(nodeId.toString(), x+20, y+30);
    }
    
}
