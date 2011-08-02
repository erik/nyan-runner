package net.boredomist.nyanrunner;

import net.boredomist.R;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Star {
	public static final int MAX_FRAMES = 5;
	public static Drawable frame1 = null, frame2, frame3, frame4, frame5;

	private Point<Integer> mPosition;
	private int mFrame, mTicks;
	private Resources mRes;

	public Star(GameWorld world, int x, int y) {
		mPosition = new Point<Integer>(x, y);
		mFrame = 0;
		mTicks = 0;
		mRes = world.getController().getContext().getResources();

		if (frame1 == null) {
			frame1 = mRes.getDrawable(R.drawable.part1);
			frame2 = mRes.getDrawable(R.drawable.part2);
			frame3 = mRes.getDrawable(R.drawable.part3);
			frame4 = mRes.getDrawable(R.drawable.part4);
			frame5 = mRes.getDrawable(R.drawable.part5);
		}

	}

	public int getX() {
		return mPosition.X;
	}

	public void update() {
		if (++mTicks % 15 == 0) {
			++mFrame;
		}

		mPosition.X -= 10;
	}

	public void draw(Canvas canvas) {
		Drawable d = null;

		if (mFrame == 0)
			d = frame1;
		else if (mFrame == 1)
			d = frame2;
		else if (mFrame == 2)
			d = frame3;
		else if (mFrame == 3)
			d = frame4;
		else if (mFrame == 4)
			d = frame5;
		else
			return;

		d.setBounds(mPosition.X, mPosition.Y, mPosition.X + 35,
				mPosition.Y + 35);
		d.draw(canvas);
	}

}
