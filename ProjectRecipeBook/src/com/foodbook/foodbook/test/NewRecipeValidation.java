package com.foodbook.foodbook.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.foodbook.foodbook.FridgeActivity;
import com.foodbook.foodbook.Recipe;
import com.foodbook.foodbook.RecipeBook;

/**
 * <p>
 * This class is testing the creation of recipe in EditRecipeActivity test add
 * title, description, ingredient, instruction,category able to save test
 * exclude title-not able to save- test exclude description -not able to save-
 * test exclude ingredient -not able to save- test exclude category -able to
 * save- test add a photo
 * </p>
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert
 *         Janes (rjanes)
 * 
 */

public class NewRecipeValidation extends ActivityInstrumentationTestCase2 {
	private String getrecipe;
	private ArrayList<String> idAll;

	private RecipeBook testRecipeBook;

	String rName = "name";
	String rDesc = "desc";
	String rInst = "inst";
	ArrayList<String> rIng = new ArrayList<String>();
	ArrayList<String> rCate = new ArrayList<String>();
	String rUserID = "123";
	String rAuth = "auth";

	private Recipe r;

	/**
	 * method to inherit from fridgeactivity
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public NewRecipeValidation() {
		super("com.foodbook.foodbook.test", FridgeActivity.class);
	}

	/**
	 * method create a new setup/activity
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		testRecipeBook = new RecipeBook();
	}

	/**
	 * method to test the creation of a new recipe and adding to the recipebook
	 */
	public void testNewRecipe() {
		try{
			int oldSize = testRecipeBook.getRecipeBook().size();
			testRecipeBook.addRecipe(rName, rDesc, rInst, rIng, rCate, null);
			
			int newSize = testRecipeBook.getRecipeBook().size();
			assertTrue(oldSize + 1 == newSize);
			
			assertTrue(testRecipeBook.getMine().get(newSize-1).getRecipename().equals(rName));
			
			} catch (Exception e) {
            fail("Exception occurred");
        }
    }

	/**
	 * testing the ability to remove a recipe from a recipebook
	 */

	public void testDeleteRecipe() {
		try{
			
			// adding a new recipe
			int oldSize = testRecipeBook.getRecipeBook().size();
			testRecipeBook.addRecipe(rName, rDesc, rInst, rIng, rCate, null);
			int newSize = testRecipeBook.getRecipeBook().size();
			assertTrue(oldSize + 1 == newSize);
			assertTrue(testRecipeBook.getMine().get(newSize-1).getRecipename().equals(rName));
			
			// deleting the recipe
			String recipeID = testRecipeBook.getMine().get(newSize-1).getRecipeid();
			testRecipeBook.deleteById(recipeID);
			
			// check if it's really deleted
			assertFalse(testRecipeBook.containsRecipeOfID(recipeID));
			
			} catch (Exception e) {
            fail("Exception occurred");
        }
	}
	
}