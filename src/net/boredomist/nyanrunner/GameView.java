package net.boredomist.nyanrunner;

import net.boredomist.R;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView implements SurfaceHolder.Callback,
		View.OnTouchListener {

	private GameThread mThread;
	private MediaPlayer mPlayer;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);

		mThread = new GameThread(getHolder(), getContext());
		
		mPlayer = MediaPlayer.create(getContext(), R.raw.nyan);
		mPlayer.setLooping(true);
		
		this.setOnTouchListener(this);

		getHolder().addCallback(this);
		this.setFocusable(true);
	}

	public boolean onTouch(View view, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			mThread.getInput().setHasTouch(false);
			return false;
		} else {
			mThread.getInput().setTouch(event);
		}
		return true;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		mThread.create(w, h);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		mThread.setRunning(true);
		mThread.start();
		mPlayer.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		mPlayer.stop();
		mThread.setRunning(false);
		boolean retry = true;
		while (retry) {
			try {
				mThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}
}
