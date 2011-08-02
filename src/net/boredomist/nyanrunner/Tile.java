package net.boredomist.nyanrunner;

import net.boredomist.R;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Tile {
	public int X, mWidth;
	public static Drawable mDrawable;

	public Tile(int x, GameWorld world) {
		X = x;
		if (mDrawable == null) {
			mDrawable = world.getController().getContext().getResources()
					.getDrawable(R.drawable.plat1);
		}
		mWidth = 800;
	}

	public void draw(Canvas canvas) {
		mDrawable.setBounds(X, 400, X + 800, 500);
		mDrawable.draw(canvas);
	}
}
