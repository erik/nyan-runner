package net.boredomist.nyanrunner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;

public class Controller {
	private GameThread mThread;
	private Input mInput;
	private GameWorld mWorld;

	
	public Controller(GameThread thread) {
		mThread = thread;
		mInput = mThread.getInput();

		mWorld = new GameWorld(this);
	}

	public GameThread getThread() {
		return mThread;
	}

	public void update() {
		mWorld.update();
	}

	public void draw(Canvas canvas) {
		mWorld.draw(canvas);
	}

	public Context getContext() {
		return mThread.getContext();
	}
}
