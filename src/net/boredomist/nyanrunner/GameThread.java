package net.boredomist.nyanrunner;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Process;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
	private boolean mRun;
	private Context mContext;
	private SurfaceHolder mSurfaceHolder;
	private boolean mCreated;
	private int mWidth;
	private int mHeight;
	private Controller mController;
	private Input mInput;

	public GameThread(SurfaceHolder surfaceHolder, Context context) {
		mContext = context;
		mSurfaceHolder = surfaceHolder;

		mInput = new Input();

		mController = null;
		mCreated = false;
	}

	public Input getInput() {
		return mInput;
	}

	public void create(int w, int h) {
		mWidth = w;
		mHeight = h;

		mController = new Controller(this);
		mCreated = true;
	}
	
	public int getWidth() {
		return mWidth;
	}
	
	public int getHeight() {
		return mHeight;
	}

	@Override
	public void run() {
		Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_DISPLAY);
		Canvas c;
		double nextTick = System.currentTimeMillis();
		while (mRun) {
			try {
				// clamp to 35fps
				Thread.sleep(1000 / 35);
			} catch (InterruptedException e) {
				Log.d("SHM", "Thread interrupted: " + e);
			}

			if (!mCreated) {
				continue;
			}

			final int MAX_FRAMESKIP = 10;
			int loops = 0;
			while ((double) System.currentTimeMillis() > nextTick
					&& loops++ < MAX_FRAMESKIP) {
				mController.update();
				nextTick += 1000 / 30.0;
			}

			c = null;
			try {
				c = mSurfaceHolder.lockCanvas(null);
				synchronized (mSurfaceHolder) {
					mController.draw(c);
				}
			} finally {
				if (c != null) {
					mSurfaceHolder.unlockCanvasAndPost(c);
				}
			}
		}
	}

	public void setRunning(boolean b) {
		mRun = b;
	}

	public Context getContext() {
		return mContext;
	}
}
