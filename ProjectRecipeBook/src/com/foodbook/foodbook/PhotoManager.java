package com.foodbook.foodbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.Gallery;

/**
 *
 * Manages pictures of recipes.
 *
 */

public class PhotoManager extends Activity {

	protected ArrayList<String> pictures = new ArrayList<String>();

	protected GalleryBaseAdapter myGalleryBaseAdapter;
	protected Gallery myPhotoGallery;

	protected String picFolderPath;

	protected final int PICTURE_SIZE = 200;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_manager);
		if (getIntent().getExtras() != null) {
			Log.v("mylog", "intent contains something");
			pictures = getIntent().getStringArrayListExtra("pictures");
		}

		setupGallery(getApplicationContext());

		try {
			setFolder();
			emptyFolder();
			readPictures(getApplicationContext());
			readBMP();
			updateScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Button addButton = (Button) findViewById(R.id.photoManager_addButton);
		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					addPhoto();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 *
	 * Finds the gallery component from xml and bounds to a variable
	 * 
	 * @param applicationContext
	 */
	
	protected void setupGallery(final Context applicationContext) {
		myPhotoGallery = (Gallery) findViewById(R.id.photomanager_gallery);
		myPhotoGallery.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
				deletePic(position, applicationContext);
				return true;
			}
		});
	}
	
	/**
	 * 
	 * Removes picture from the picture list
	 * 
	 * @param position
	 * @param applicationContext
	 */

	protected void deletePic(int position, Context applicationContext) {

		pictures.remove(position);

		try {
			emptyFolder();
			readPictures(applicationContext);
			readBMP();
			updateScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Reads bitmap files in the SD card
	 * 
	 */
	
	protected void readBMP() {
		boolean deleteFiles = false;
		iterateThroughFiles(deleteFiles);
	}

	/**
	 * 
	 * Deletes temporary files in the SD card
	 * 
	 */
	
	protected void emptyFolder() {
		boolean deleteFiles = true;
		iterateThroughFiles(deleteFiles);
	}
	
	/**
	 * 
	 * Updates Gallery by setting adapter
	 * 
	 */

	protected void updateScreen() {
		myPhotoGallery.setAdapter(myGalleryBaseAdapter);
	}

	/**
	 * 
	 * Sets the folder path
	 * 
	 */
	
	protected void setFolder() {

		String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recipePic";
		File folderF = new File(folder);

		if (!folderF.exists()) {
			folderF.mkdir();
		}

		picFolderPath = folder;

	}
	
	/**
	 * 
	 * Iterate through every file in the folder, performing appropriate actions.
	 * 
	 * @param delete
	 */

	protected void iterateThroughFiles(boolean delete) {

		myGalleryBaseAdapter = new GalleryBaseAdapter(getApplicationContext());

		File folderF = new File(picFolderPath);
		File[] files = folderF.listFiles();

		for (File file : files) {
			if (delete) {
				file.delete();
			} else {
				myGalleryBaseAdapter.add(file.getPath());
			}
		}

	}
	
	/**
	 * 
	 * Converts bitmap strings into actual BMP files and save them as files so that they can be displayed
	 * 
	 * @param con
	 * @throws IOException
	 */

	protected void readPictures(Context con) throws IOException {

		for (String pic : pictures) {
			Bitmap bmp = ImageConverter.getBitmapFromString(pic);
			String imageFilePath = picFolderPath + "/" + String.valueOf(System.currentTimeMillis()) + "jpg";
			File imageFile = new File(imageFilePath);
			saveBMP(imageFile, bmp);
		}
	}
	
	/**
	 * 
	 * Save BMP file
	 * 
	 * @param intentPicture
	 * @param ourBMP
	 * @throws IOException
	 */

	protected void saveBMP(File intentPicture, Bitmap ourBMP) throws IOException {
		OutputStream out = new FileOutputStream(intentPicture);
		ourBMP.compress(Bitmap.CompressFormat.JPEG, 75, out);
		out.close();
	}
	
	/**
	 * 
	 * Generates a random bitmap image using BogoPicGen class
	 * 
	 * @throws IOException
	 */

	public void addPhoto() throws IOException {

		Bitmap newBMP = BogoPicGen.generateBitmap(PICTURE_SIZE, PICTURE_SIZE);
		String newBMPString = ImageConverter.getJsonString(newBMP);
		pictures.add(newBMPString);

		String imageFilePath = picFolderPath + "/" + String.valueOf(System.currentTimeMillis()) + "jpg";
		File imageFile = new File(imageFilePath);
		saveBMP(imageFile, newBMP);

		myGalleryBaseAdapter.add(imageFile.getPath());
		updateScreen();
	}

	@Override
	public void onPause() {
		super.onPause();
		if (pictures.size() != 0) {
			PictureContainer container = PictureContainer.getInstance();
			container.setPics(pictures);
		}
	}
}
