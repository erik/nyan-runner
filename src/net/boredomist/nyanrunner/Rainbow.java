package net.boredomist.nyanrunner;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Rainbow {
	static final int WIDTH = 50, HEIGHT = 14;

	ArrayList<Point<Integer>> mPoints;
	private Paint mPaint;
	private boolean mSwitch;
	private int mCtr;

	public Rainbow() {
		mPaint = new Paint();
		mPoints = new ArrayList<Point<Integer>>();
		mSwitch = false;
		mCtr = 0;
	}

	public void addPoint(int x, int y) {
		mPoints.add(new Point<Integer>(x, y));
	}

	public void update() {
		for (int i = mPoints.size() - 1; i >= 0; --i) {
			Point<Integer> p = mPoints.get(i);
			p.X -= WIDTH;

			if (p.X < -WIDTH) {
				mPoints.remove(i);
			}
			mPoints.set(i, p);
		}
		if (++mCtr % 5 == 0) {
			mSwitch = !mSwitch;
		}
	}

	public void draw(Canvas canvas) {
		boolean jag = mSwitch;
		for (Point<Integer> p : mPoints) {
			jag = !jag;
			int x = p.X;
			int y = p.Y;
			if (jag) {
				y += HEIGHT / 2;
			}

			mPaint.setColor(Color.RED);
			canvas.drawRect(x, y, x + WIDTH, y + HEIGHT, mPaint);
			y += HEIGHT;
			mPaint.setColor(Color.rgb(0xff, 0xa5, 0x00));
			canvas.drawRect(x, y, x + WIDTH, y + HEIGHT, mPaint);
			y += HEIGHT;
			mPaint.setColor(Color.YELLOW);
			canvas.drawRect(x, y, x + WIDTH, y + HEIGHT, mPaint);
			y += HEIGHT;
			mPaint.setColor(Color.GREEN);
			canvas.drawRect(x, y, x + WIDTH, y + HEIGHT, mPaint);
			y += HEIGHT;
			mPaint.setColor(Color.BLUE);
			canvas.drawRect(x, y, x + WIDTH, y + HEIGHT, mPaint);
			y += HEIGHT;
			mPaint.setColor(Color.rgb(0x4b, 0x00, 0x82));
			canvas.drawRect(x, y, x + WIDTH, y + HEIGHT, mPaint);
			y += HEIGHT;
			mPaint.setColor(Color.rgb(0xee, 0x82, 0xee));
			canvas.drawRect(x, y, x + WIDTH, y + HEIGHT, mPaint);

		}
	}
}
