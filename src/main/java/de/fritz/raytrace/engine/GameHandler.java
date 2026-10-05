package de.fritz.raytrace.engine;

import de.fritz.raytrace.Statics;

public class GameHandler implements Runnable {

    Thread gameThread;

    Canvas canvas;

    public GameHandler(Canvas canvas) {
        this.canvas = canvas;
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        double drawInterval = 1000000000.0 / Statics.FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        long lastTime = System.nanoTime();

        while (gameThread.isAlive()) {

            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
            lastTime = currentTime;

            // Avoid a large movement jump after a pause or debugger break.
            deltaTime = Math.min(deltaTime, 0.1);
            Time.setDeltaTime(deltaTime);

            //handleMovement
            canvas.keyHandler.handleKeys();

            //handleMouseMovement
            canvas.update();

            //getFrameImage
            canvas.updateFrame();

            // DRAW
            canvas.repaint();



            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = Math.max(remainingTime / 1000000, 0);

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
