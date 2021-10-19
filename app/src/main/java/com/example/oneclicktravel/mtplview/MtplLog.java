package com.example.oneclicktravel.mtplview;

import android.util.Log;

public class MtplLog {
	private final static boolean isPrint = true;

	public static void i(String Tag, String msg) {
		if (isPrint) {
			Log.i(Tag, msg);
		}
	}
}
