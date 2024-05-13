package com.github.msx80.omicron;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;

public interface AndroidPlugin  
{
	
	void onCreate (android.app.Activity ctx, Bundle savedInstanceState);
   
	void onResume(android.app.Activity ctx);
	
	void onPause(android.app.Activity ctx);
	
	void onNewIntent(android.app.Activity ctx, Intent intent);
}
