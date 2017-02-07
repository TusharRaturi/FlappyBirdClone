package Engine;

import Engine.GameObject.GameObject;

import java.util.ArrayList;

/**
 * Created by Tushar on 05-01-2017.
 */
public abstract class Game
{
    protected ArrayList<GameObject> gameObjects;

    protected long window;

    protected Game()
    {
        gameObjects = new ArrayList<GameObject>();
    }

    protected void input()
    {

    }

    protected void update()
    {
        for (GameObject object:gameObjects)
        {
            object.update();
        }
    }

    protected void render()
    {
        for (GameObject object:gameObjects)
        {
            object.render();
        }
    }

    public long getWindow()
    {
        return window;
    }

    public void setWindow(long window)
    {
        this.window = window;
    }
}
