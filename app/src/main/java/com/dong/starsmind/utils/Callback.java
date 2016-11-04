package com.dong.starsmind.utils;

public interface Callback {
	void onBefore();

	boolean onRun();

	void onAfter(boolean b);
}
