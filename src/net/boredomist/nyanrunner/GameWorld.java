package net.boredomist.nyanrunner;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class GameWorld {
	private Cat mCat;
	private Floor mFloor;
	private Controller mController;
	private Input mInput;
	private int mWidth, mHeight;
	private Paint mPaint;
	private Typeface mFont;
	private ArrayList<Star> mStars;
	public int mSadTicks;

	public GameWorld(Controller controller) {
		mController = controller;
		mInput = controller.getThread().getInput();

		mWidth = mController.getThread().getWidth();
		mHeight = mController.getThread().getHeight();

		mFont = Typeface.createFromAsset(mController.getContext().getAssets(),
				"fonts/slkscreen.ttf");

		mPaint = new Paint();
		mPaint.setTextSize(32);
		mPaint.setTypeface(mFont);

		mStars = new ArrayList<Star>();

		mSadTicks = 0;

		mFloor = new Floor(this);
		mCat = new Cat(this);
	}

	public Floor getFloor() {
		return mFloor;
	}

	public int getWidth() {
		return mWidth;
	}

	public int getHeight() {
		return mHeight;
	}

	public Controller getController() {
		return mController;
	}

	public Input getInput() {
		return mInput;
	}

	public void update() {
		mFloor.update();
		mCat.update();

		mSadTicks--;

		if ((int) (Math.random() * 10) == 0) {
			mStars.add(new Star(this, (int) (Math.random() * mWidth),
					(int) (Math.random() * mHeight)));
		}

		for (int i = mStars.size() - 1; i >= 0; --i) {
			Star s = mStars.get(i);
			s.update();
			if (s.getX() < -35) {
				mStars.remove(i);
			}
		}

	}

	public void draw(Canvas canvas) {
		mPaint.setColor(Color.rgb(0x0F, 0x4D, 0x8F));
		canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

		for (Star s : mStars) {
			s.draw(canvas);
		}

		mFloor.draw(canvas);
		mCat.draw(canvas);

		mPaint.setTextSize(32);
		mPaint.setTextAlign(Paint.Align.LEFT);
		mPaint.setColor(Color.WHITE);

		canvas.drawText("" + mFloor.getDistance() + " nyans", 0, 32, mPaint);
		
		if(mSadTicks > 0) { 
			mPaint.setTextSize(124);
			mPaint.setTextAlign(Paint.Align.CENTER);
			canvas.drawText(":(", mWidth / 2, mHeight / 2, mPaint);
		}

	}
}
