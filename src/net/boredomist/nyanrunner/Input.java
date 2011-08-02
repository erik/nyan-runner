package net.boredomist.nyanrunner;

import android.view.MotionEvent;

public class Input {

	private boolean mHasTouch;
	private Point<Integer> mTouchPos;

	public Input() {
		mHasTouch = false;
		mTouchPos = new Point<Integer>(0, 0);
	}

	public boolean hasTouch() {
		return mHasTouch;
	}

	public Point<Integer> getTouch() {
		return mTouchPos;
	}

	public void setTouch(MotionEvent event) {
		mHasTouch = true;
		mTouchPos.X = (int) event.getX();
		mTouchPos.Y = (int) event.getY();
	}

	public void setHasTouch(boolean b) {
		mHasTouch = b;
	}

}
