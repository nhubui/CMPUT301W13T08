package com.foodbook.foodbook;

import com.foodbook.onlinemanager.OnlineSearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;


/**
 * <p>
 * This class contains the implementation of title bar menu button.
 * </p>
 * 
 * <p>
 * This button can be used to navigate through different activities within the app. Since this class extends Activity, all activities that need to have the button will extend this one, so that they will all have button and behave as activities.
 * </p>
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert Janes (rjanes)
 * 
 */
public class TitleBarOverride extends Activity {

	RecipeBook myRecipeBook;
	Fridge myFridge;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater menuI = getMenuInflater();
		menuI.inflate(R.menu.menu, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_myRecipe:
			Intent myRecipeBook = new Intent();
			myRecipeBook.setClass(getApplicationContext(), RecipeBookActivity.class);
			startActivity(myRecipeBook);
			break;
		case R.id.menu_makeRecipe:
			Intent makeRecipe = new Intent();
			makeRecipe.setClass(getApplicationContext(), MakeRecipeActivity.class);
			startActivity(makeRecipe);
			break;
		case R.id.menu_postRecipe:
			Intent postRecipe = new Intent();
			postRecipe.setClass(getApplicationContext(), RecipeBookActivity.class);
			postRecipe.putExtra("currentTab", 2);
			startActivity(postRecipe);
			break;
		case R.id.menu_searchOnline:
			Intent search = new Intent();
			search.setClass(getApplicationContext(), OnlineSearch.class);
			startActivity(search);
			break;
		case R.id.menu_changeName:
			askForNewName();
		default:
			break;
		}
		return true;
	}

	/**
	 * Prompt User for author name. Automatically executed on first run. The ability to change the name is availbe from the actions menu
	 * 
	 * 
	 */

	protected void askForNewName() {
		AlertDialog.Builder alertdg = new AlertDialog.Builder(this);
		alertdg.setTitle("New username");
		final EditText nameField = new EditText(this);
		nameField.setSingleLine(true);
		LinearLayout layout = new LinearLayout(this);
		layout.addView(nameField);
		alertdg.setView(layout);
		alertdg.setPositiveButton("Save", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String newName = nameField.getText().toString();
				RecipeBook.getInstance().setAuthor(newName);
				RecipeBook.getInstance().updateAuthorInAllRecipes();
			}
		});
		alertdg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// do nothing for "cancel"
			}
		});
		alertdg.show();
	}

	@Override
	public void onPause() {
		super.onPause();
		Fridge.getInstance().saveToFile(getApplicationContext());
		RecipeBook.getInstance().saveToFile(getApplicationContext());
	}

}