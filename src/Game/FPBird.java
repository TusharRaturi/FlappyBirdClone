package Game;

import Engine.Game;
import Engine.KeyboardHandler;
import Engine.Physics;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

/**
 * Created by Tushar on 09-01-2017.
 */
public class FPBird extends Game
{
    Floor floor;
    Bird player;
    ArrayList<Pipe> pipes;
    static boolean gameOver;
    private int scr;
    public static final float WIDTH = 600;
    public static final float HEIGHT = 600;

    private double keyTimer, timePassed;

    FPBird()
    {
        super();

        pipes = new ArrayList<Pipe>();

        for(int d = 1; d <= 3; d++)
            pipes.add(new Pipe(WIDTH + (d-1) * Pipe.PIPE_DIST_X,  WIDTH / 10, new Color(22, 165, 17), d == 1, d == 3));

        for(int i = 0; i < pipes.size(); i++)
        {
            gameObjects.add(pipes.get(i));
        }

        gameObjects.add(floor = new Floor(0, 0, WIDTH, HEIGHT / 10, new Color(203, 196, 37, 255)));

        gameObjects.add(player = new Bird(WIDTH / 10, HEIGHT / 2 - 25, WIDTH / 20, HEIGHT / 20, new Color(255, 229, 33)));

        gameOver = false;

        scr = 0;
    }

    private void reset()
    {
        for(int i = 0; i < pipes.size(); i++)
        {
            gameObjects.remove(pipes.get(i));
        }

        gameObjects.remove(floor);

        gameObjects.remove(player);

        pipes = new ArrayList<Pipe>();

        for(int d = 1; d <= 3; d++)
            pipes.add(new Pipe(WIDTH + (d-1) * Pipe.PIPE_DIST_X,  WIDTH / 10, new Color(22, 165, 17), d == 1, d == 3));

        for(int i = 0; i < pipes.size(); i++)
        {
            gameObjects.add(pipes.get(i));
        }

        gameObjects.add(floor = new Floor(0, 0, WIDTH, HEIGHT / 10, new Color(203, 196, 37, 255)));

        gameObjects.add(player = new Bird(WIDTH / 10, HEIGHT / 2 - 25, WIDTH / 20, HEIGHT / 20, new Color(255, 229, 33)));

        gameOver = false;

        scr = 0;
    }

    public void input()
    {
        super.input();

        if(KeyboardHandler.isKeyDown(GLFW_KEY_SPACE))
        {
            if(gameOver)
                reset();
            else
            {
                timePassed = System.nanoTime() - keyTimer;
                if (!player.isJumping() && timePassed >= 10e+8 / 5) {
                    keyTimer = System.nanoTime();
                    timePassed = keyTimer;
                    player.doJump();
                }
            }
        }
    }

    public void update()
    {
        super.update();

        if(player.getTransform().getX() >= Pipe.firstPipe.getTransform().getX() + Pipe.firstPipe.getSx())
        {
            scr++;
            System.out.println("Score = " + scr);
            int index = pipes.indexOf(Pipe.firstPipe) + 1;
            if(index > 2)
                index = 0;
            Pipe.setFirst(pipes.get(index));
        }

        if (Physics.checkRectCollision(player, Pipe.firstPipe) || Physics.checkRectCollision(player, Pipe.firstPipe.getUpper()))
            gameOver = true;
        if(Physics.checkRectCollision(player, floor))
        {
            player.setSpeedY(0.0f);
            player.getTransform().setY(floor.getSy());
            gameOver = true;
        }
    }
}
