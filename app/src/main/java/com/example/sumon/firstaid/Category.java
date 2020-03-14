package com.example.sumon.firstaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sumon.firstaid.models.IdName;
import com.example.sumon.firstaid.utils.Constant;
import com.example.sumon.firstaid.utils.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class Category extends AppCompatActivity {
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        this.listView = (ListView) findViewById(R.id.listView);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        final List<IdName> categories = databaseAccess.getCategory();
        databaseAccess.close();

        CategoryAdapter adapter = new CategoryAdapter(this, new ArrayList<IdName>(categories));
        // ArrayAdapter<IdName> adapter = new ArrayAdapter<IdName>(this, android.R.layout.simple_list_item_1, categories);
        this.listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),SubCategory.class);
                IdName category = categories.get(i);
                intent.putExtra(Constant.CATEGORY_NAME, category.getId());
                startActivity(intent);
            }
        });
    }

}
