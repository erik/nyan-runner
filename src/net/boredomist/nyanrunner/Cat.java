package net.boredomist.nyanrunner;

import net.boredomist.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Cat {
	static final int MAX_JUMP = 15;
	static final int MAX_FRAMES = 5;
	static final int WIDTH = 200, HEIGHT = 120;

	private Drawable mImages[];
	private int mCurrentFrame = 0;
	private int mJumps;
	private int mVelocity;
	private Input mInput;

	private Point<Integer> mPosition;

	private GameWorld mWorld;

	private int mCtr;

	private Rainbow mRainbow;

	public Cat(GameWorld world) {
		mWorld = world;

		mCtr = 0;

		mInput = mWorld.getInput();
		mVelocity = 50;
		mPosition = new Point<Integer>(300, 000);

		mImages = new Drawable[MAX_FRAMES];

		Context ctx = mWorld.getController().getContext();
		Resources r = ctx.getResources();

		mImages[0] = r.getDrawable(R.drawable.nyan1);
		mImages[1] = r.getDrawable(R.drawable.nyan2);
		mImages[2] = r.getDrawable(R.drawable.nyan3);
		mImages[3] = r.getDrawable(R.drawable.nyan4);
		mImages[4] = r.getDrawable(R.drawable.nyan5);

		mRainbow = new Rainbow();

		mJumps = 0;
		mCurrentFrame = 0;
	}

	public void update() {
		if (++mCtr % 3 == 0) {
			mCurrentFrame = (++mCurrentFrame % MAX_FRAMES);
		}
		if (mInput.hasTouch() && mJumps++ < MAX_JUMP) {
			mVelocity = Math.max(0, mVelocity - 5);
			mPosition.Y -= mVelocity;
		} else {
			mVelocity = Math.min(20, mVelocity + 5);
			mPosition.Y = Math.min(mWorld.getHeight(), mPosition.Y + mVelocity);

			if (mPosition.Y >= mWorld.getHeight()) {
				mPosition.Y = -HEIGHT;
				mWorld.getFloor()
						.heyIDontMeanToInterruptButTheCatDiedSoYouShouldResetSomeVariablesAndSuchKThnx();
				mWorld.mSadTicks = 100;
			}
			if (isTouchingFloor()) {
				mJumps = 0;
				mVelocity = 50;
				mPosition.Y = 300;
			}
		}
		mRainbow.addPoint(mPosition.X + WIDTH / 2, mPosition.Y);
		mRainbow.update();
	}

	public boolean isTouchingFloor() {
		return mWorld.getFloor().collides(mPosition.X + 50,
				mPosition.Y + HEIGHT / 2, WIDTH - 50, HEIGHT / 2);
	}

	public void draw(Canvas canvas) {
		mRainbow.draw(canvas);

		mImages[mCurrentFrame].setBounds(mPosition.X, mPosition.Y, mPosition.X
				+ WIDTH, mPosition.Y + HEIGHT);
		mImages[mCurrentFrame].draw(canvas);
	}
}
