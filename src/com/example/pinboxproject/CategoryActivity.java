package com.example.pinboxproject;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.pinboxproject.adapters.CategoryGridAdapter;

public class CategoryActivity extends NavigationActivity {
	
	private GridView gridCategory;
	private CategoryGridAdapter adapter;

	private String[] categories = {"Bank", "Education", "Entertainment", "Fire Brigade",
			"Hospital", "Hotel(Residential)", "Shopping Mall", "Police Station",
			"Restaurent", "Transportation", "Others"};
	
	private Integer[] categoryIcons = {R.drawable.ic_bank, R.drawable.ic_education, R.drawable.ic_entertainment,
			R.drawable.ic_fire, R.drawable.ic_hospital, R.drawable.ic_hotel, R.drawable.ic_shopping,
			R.drawable.ic_police, R.drawable.ic_restaurant, R.drawable.ic_transport, R.drawable.ic_others};
	
	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_category);
		
		init();
		
	}

	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}
	
	private void init(){
		
		gridCategory = (GridView)findViewById(R.id.category_grid);
		adapter = new CategoryGridAdapter(this, categories, categoryIcons);
		gridCategory.setAdapter(adapter);
		
		gridCategory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CategoryActivity.this, SearchActivity.class);
				String selectedCategory = categories[position];
				intent.putExtra("category", selectedCategory);
				startActivity(intent);
			}
		});
	}

}
