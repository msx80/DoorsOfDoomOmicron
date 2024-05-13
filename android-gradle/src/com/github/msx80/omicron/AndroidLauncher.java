package com.github.msx80.omicron;

import android.os.Bundle;
import android.net.Uri;
import android.content.Intent;
import android.widget.Toast;
import android.app.Activity;
import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Consumer;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.github.msx80.omicron.GdxOmicron;
import com.github.msx80.omicron.AndroidPlugin;
import com.github.msx80.omicron.HardwareInterface;
import com.github.msx80.omicron.api.Game;
import com.github.msx80.omicron.api.adv.*;
import com.github.msx80.omicron.fantasyconsole.cartridges.*;
import java.util.Properties;

import android.content.pm.*;

public class AndroidLauncher extends AndroidApplication implements HardwareInterface {
	
	private static final int SAVE_FILE = 3345993;
	
	private byte[] bytesToSave = null;
	private Consumer<String> fileResult = null;
	
	//AndroidPlugin plugin = new NfcAndroidPlugin();
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExceptionHandler.addHandler(this);
		Cartridge c = null;
		try {
		//    ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
		    //Bundle bundle = ai.metaData;
		    //String gameClass = bundle.getString("gameClass");
			
			java.io.InputStream is = this.getClass().getClassLoader().getResourceAsStream("omicron_android.properties");
			if(is == null) throw new RuntimeException("Unable to find omicron_android.properties where expected.");
			java.util.Properties p = new Properties();
			p.load(is);
			String gameClass = p.getProperty("omicron.pkg")+"."+p.getProperty("omicron.main");
			
			int n = gameClass.lastIndexOf('.');
			String pkg = gameClass.substring(0,  n);
			String main = gameClass.substring(n+1);
		
			 c = new ClasspathCartridge("Gamex", pkg, main);
			 
			 //plugin.onCreate(this, savedInstanceState);
			 //if(true) throw new RuntimeException("err");
		    //g = (Game)Class.forName(gameClass).newInstance();
		} catch (Exception e) {
		    throw new RuntimeException(e);
		}
		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new GdxOmicron(c, this), config);
	}
	
	
   @Override
	protected void onResume() {
		super.onResume();
		//plugin.onResume(this);
	}
  
	@Override
   protected void onPause() {
		super.onPause();
		//plugin.onPause(this);
	}

	
	@Override
	public void onNewIntent(Intent intent) {
		
		//plugin.onNewIntent(this, intent);
	}

	
	@Override
	public void openUrl(String url) throws Exception {
      Uri uri = Uri.parse( url );
      Intent intent = new Intent( Intent.ACTION_VIEW, uri );
      this.startActivity( intent );
   }
   
   	@Override
	public void saveFile(String mimeType, String filename, byte[] content, Consumer<String> result) {
		/*this.bytesToSave = content;
		this.fileResult = result;
		Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType(mimeType); //not needed, but maybe usefull
		intent.putExtra(Intent.EXTRA_TITLE, filename); //not needed, but maybe usefull
		startActivityForResult(intent, SAVE_FILE);
		*/
   }
   
   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  if(requestCode == SAVE_FILE ) {
		  // clean stuff
		  byte[] bytes = bytesToSave;
		  bytesToSave = null;
		  Consumer<String> onResult = fileResult;
		  fileResult = null;
		  
		if (resultCode == Activity.RESULT_OK) {
		Uri uri = data.getData();
		Toast.makeText(this, "Info: "+uri, Toast.LENGTH_SHORT).show();
		//just as an example, I am writing a String to the Uri I received from the user:
		String error = null;
		try {
		  OutputStream output = getContext().getContentResolver().openOutputStream(uri);

		  output.write(bytes);
		  output.flush();
		  output.close();
		}
		catch(Exception e) {
		  Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
		  error = e.getMessage();
		}
		onResult.accept(error);
		
	  }
	  else
	  {
		  Toast.makeText(this, "Not saved", Toast.LENGTH_SHORT).show();
		  onResult.accept("dismissed");
	  }
	  }
}

	public Object hardware(String module, String command, Object param)
	{
		return null;
	}
   
   
   @Override public String[] startupArgs()
   {
	   return new String[0];
   }
}
