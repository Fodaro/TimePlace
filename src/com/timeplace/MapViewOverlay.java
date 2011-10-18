package com.timeplace;

import java.util.ArrayList;

import org.osmdroid.views.overlay.ItemizedOverlayControlView;
import org.osmdroid.views.overlay.Overlay;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MapViewOverlay extends ItemizedOverlayControlView {

	private ArrayList<Overlay> overlays = new ArrayList<Overlay>();
	private Context context;

	public MapViewOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	
	public MapViewOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		this.context = context;
	}
	
	public void addOverlay(Overlay overlay) {
	    overlays.add(overlay);
	    populate();
	}
	
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = overlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(context);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}
	
	@Override
	protected OverlayItem createItem(int i) {
	  return overlays.get(i);
	}

	@Override
	public int size() {
		return overlays.size();
	}

}
