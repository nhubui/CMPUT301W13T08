package com.foodbook.foodbook.test;

import com.foodbook.foodbook.FridgeActivity;
import com.foodbook.foodbook.RecipeBook;

import android.test.ActivityInstrumentationTestCase2;

/**
 * <p>
 * This class test the creation of auther 
 * </p>
 * 
 * @author Jaeseo Park (jaeseo1), Jasmine Woo (jwoo), Nhu Bui (nbui), Robert
 *         Janes (rjanes)
 * 
 */
public class CacheValidation extends ActivityInstrumentationTestCase2
{
  private RecipeBook testRecipeBook;

	/**
	 * method to inherit from fridgeactivity
	 */
	public CacheValidation(String name)
	{

		super("com.foodbook.foodbook.test", FridgeActivity.class);
	}

	/**
	 * method create a new setup/activity
	 */
	protected void setUp() throws Exception
	{

		super.setUp();
		//testRecipeBook = new RecipeBook();
	}
	
	/**
	 * method to test the creation of a new recipe and adding to the recipebook
	 */
	public void testAuthor() {
		try{


			
			} catch (Exception e) {
            fail("Exception occurred");
        }
    }

}
