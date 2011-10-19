package com.timeplace;

import android.app.Activity;

public class Controller extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.local_service_controller);
	}

	private OnClickListener mStartListener = new OnClickListener() {
		public void onClick(View v) {
			// Make sure the service is started. It will continue running
			// until someone calls stopService(). The Intent we use to find
			// the service explicitly specifies our service component, because
			// we want it running in our own process and don't want other
			// applications to replace it.
			startService(new Intent(Controller.this, LocalService.class));
		}
	};

	private OnClickListener mStopListener = new OnClickListener() {
		public void onClick(View v) {
			// Cancel a previous call to startService(). Note that the
			// service will not actually stop at this point if there are
			// still bound clients.
			stopService(new Intent(Controller.this, LocalService.class));
		}
	};
}
