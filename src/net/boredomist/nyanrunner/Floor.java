package net.boredomist.nyanrunner;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Floor {
	static final int TILE_WIDTH = 100, TILE_HEIGHT = 50;

	private double mScrollSpeed;
	private ArrayList<Tile> mTiles;
	private int mTilePos;
	private GameWorld mWorld;
	private Paint mPaint;
	private int mDistance;

	private int mTicks;

	private boolean mCreateFloor;

	public Floor(GameWorld world) {
		mWorld = world;

		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);

		mTicks = 0;

		mDistance = 0;

		mCreateFloor = true;
		
		mScrollSpeed = 15;
		mTilePos = -TILE_WIDTH;
		mTiles = new ArrayList<Tile>();
	}

	public boolean collides(int x, int y, int w, int h) {
		if (y + h < 400) {
			return false;
		}
		Rect c = new Rect(x, y, x + w, y + h);

		Rect r = new Rect(0, 0, 0, 0);
		for (Tile i : mTiles) {
			r.set(i.X, 400, i.X + i.mWidth, 400 + 10);
			if (r.intersect(c)) {
				return true;
			}
		}

		return false;
	}

	public void heyIDontMeanToInterruptButTheCatDiedSoYouShouldResetSomeVariablesAndSuchKThnx() {
		mDistance = 0;
		mScrollSpeed = 15;
	}

	public void update() {
		mScrollSpeed += 0.01;
		mDistance += mScrollSpeed;
		mTilePos -= (int) mScrollSpeed;
		mTicks++;

		for (int i = mTiles.size() - 1; i >= 0; --i) {
			Tile t = mTiles.get(i);
			t.X -= (int) mScrollSpeed;
			if (t.X < -t.mWidth) {
				mTiles.remove(i);
			}
		}


		while (mTilePos < mWorld.getWidth()) {
			if (!mCreateFloor) {
				mTilePos += 400;
			} else {
				int times = ((int) (Math.random() * 2) + 1);
				for (int i = 0; i < times; ++i) {
					Tile t = new Tile(mTilePos, mWorld);
					mTiles.add(t);
					mTilePos += t.mWidth;
				}
			}
			mCreateFloor = !mCreateFloor;

		}

	}

	public int getDistance() {
		return mDistance / 100;
	}

	public void draw(Canvas canvas) {
		for (Tile t : mTiles) {
			t.draw(canvas);
		}
	}
}
