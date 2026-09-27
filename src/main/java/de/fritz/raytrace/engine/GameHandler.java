package de.fritz.raytrace.engine;

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

    int FPS = 60;

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread.isAlive()) {

            // UPDATE
            canvas.update();

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
