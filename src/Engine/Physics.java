package Engine;

import Engine.GameObject.GORect;

import java.awt.*;

/**
 * Created by Tushar on 06-01-2017.
 */
public class Physics
{
    public static boolean checkRectCollision(Rectangle a, Rectangle b)
    {
        return a.intersects(b);
    }

    public static boolean checkRectCollision(GORect g1, GORect g2)
    {
        Rectangle a = new Rectangle((int)g1.getTransform().getX(), (int)g1.getTransform().getY(), (int)g1.getSx(), (int)g1.getSy());
        Rectangle b = new Rectangle((int)g2.getTransform().getX(), (int)g2.getTransform().getY(), (int)g2.getSx(), (int)g2.getSy());
        return a.intersects(b);
    }
}
