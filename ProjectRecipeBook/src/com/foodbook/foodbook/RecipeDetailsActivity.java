package com.foodbook.foodbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeDetailsActivity extends TitleBarOverride {
	
	private Intent in;
	
	private String recipeid;
	
	private String name;
	private String author;
	private String descriptions;
	private String instructions;
	private String ingredients;
	private String category;
	
	private TextView nameField;
	private TextView authorField;
	private TextView descriptionsField;
	private TextView instructionsField;
	private TextView ingredientsField;
	private TextView categoryField;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_details);
		
		TextView categoryLabel = (TextView) findViewById(R.id.recipeDetails_categoryLabel);
		categoryLabel.setText("Category");
		TextView ingredientsLabel = (TextView) findViewById(R.id.recipeDetails_ingredientsLabel);
		ingredientsLabel.setText("Ingredients");

		in = getIntent();
		
		recipeid = in.getStringExtra("recipeid");
		
		name = in.getStringExtra("name");
		author = in.getStringExtra("author");
		descriptions = in.getStringExtra("descriptions");
		instructions = in.getStringExtra("instructions");
		ingredients = in.getStringExtra("ingredients").replace(", ", "\n").replace(",", "\n");
		category = in.getStringExtra("category").replace(", ", "\n").replace(",", "\n");
		
		nameField = (TextView) findViewById(R.id.recipeDetails_foodName);
		authorField = (TextView) findViewById(R.id.recipeDetails_author);
		descriptionsField = (TextView) findViewById(R.id.recipeDetails_desc);
		instructionsField = (TextView) findViewById(R.id.recipeDetails_instructions);
		ingredientsField = (TextView) findViewById(R.id.recipeDetails_ingredients);
		categoryField = (TextView) findViewById(R.id.recipeDetails_category);
		
		updateTextViews();
	}
	
	@Override
	public void onNewIntent(Intent newIntent) {
		super.onNewIntent(newIntent);
		setIntent(newIntent);
	}
	
	public void updateTextViews() {
		nameField.setText(name);
		authorField.setText(author);
		descriptionsField.setText(descriptions);
		instructionsField.setText(instructions);
		ingredientsField.setText(ingredients);
		categoryField.setText(category);
	}
	
	@Override
	public void onResume() {
		super.onResume();

		
	}
}
