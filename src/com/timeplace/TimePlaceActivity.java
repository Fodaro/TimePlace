package com.timeplace;

import java.io.File;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TabHost;

public class TimePlaceActivity extends TabActivity {

	public static LocationDatabase pointsOfInterest = new LocationDatabase();

	public static KeywordDatabase keywords = new KeywordDatabase();
	public static ArrayList<Task> tasks = new ArrayList<Task>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("ToDoMe::TimePlaceActivity", "Begining ToDoMe, start of onCreate.");
		try {
			setContentView(R.layout.main);

			Resources res = getResources(); // Resource object to get Drawables
			TabHost tabHost = getTabHost(); // The activity TabHost
			TabHost.TabSpec spec; // Reusable TabSpec for each tab
			Intent intent; // Reusable Intent for each tab

			// Create an Intent to launch an Activity for the tab (to be reused)
			// intent = new Intent().setClass(this, TaskViewActivity.class);

			// Initialise a TabSpec for each tab and add it to the TabHost
			// spec = tabHost.newTabSpec("todo").setIndicator("To-Do",
			// res.getDrawable(R.drawable.ic_tab_todo)).setContent(intent);
			// tabHost.addTab(spec);

			// Do the same for the other tabs
			intent = new Intent().setClass(this, MapViewActivity.class);
			spec = tabHost.newTabSpec("map").setIndicator("Map", res.getDrawable(R.drawable.ic_tab_map)).setContent(intent);
			tabHost.addTab(spec);

			intent = new Intent().setClass(this, TestTabActivity.class);
			spec = tabHost.newTabSpec("map").setIndicator("Test", res.getDrawable(R.drawable.ic_tab_map)).setContent(intent);
			tabHost.addTab(spec);

			tabHost.setCurrentTab(0);

			// Add a "New task" task if one does not already exist
			boolean found = false;
			for (int i = 0; i < tasks.size(); i++) {
				if (tasks.get(i).getName() == "New task") {
					found = true;
				}
			}
			if (!found) {
				tasks.add(new Task("New task", "", "", 0));
			}

			// start the service
			Log.d("ToDoMe-TimePlaceActivity", "Starting service");
			bindService(new Intent(this, ToDoMeService.class), null, 0);

			ContentResolver cr = getContentResolver();
			String[] args = { "array", "of", "string" };

			Object taskTemp = (Object) cr.query(Uri.parse("content://com.timeplace.taskprovider"), args, "", args, "");
			tasks = (ArrayList<Task>) taskTemp;
			Log.i("TimePlaceActivity", "" + (tasks == null));

		} catch (Exception ex) {
			message("TimePlaceActivity.onCreate: " + ex.getClass().toString(), ex.getMessage());
		}
	}

	private void message(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.show();
	}
}