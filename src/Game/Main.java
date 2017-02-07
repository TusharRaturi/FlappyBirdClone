package Game;

import Engine.Engine;

/**
 * Created by Tushar on 09-01-2017.
 */
public class Main
{
    public static void main(String args[])
    {
        FPBird fpBird = new FPBird();
        Engine engine = new Engine(FPBird.WIDTH, FPBird.HEIGHT, "Flappy Bird Clone", fpBird);

        fpBird.setWindow(engine.getWindow());

        engine.run();
    }
}
