package com.github.msx80.omicron;

import android.app.Activity;
import android.content.Intent;


public interface AndroidPlugin extends HardwarePlugin{
	   
	boolean resolveIntent(Intent intent);

}
