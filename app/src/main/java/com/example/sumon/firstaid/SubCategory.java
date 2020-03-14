package com.example.sumon.firstaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sumon.firstaid.models.IdName;
import com.example.sumon.firstaid.utils.Constant;
import com.example.sumon.firstaid.utils.SubCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class SubCategory extends AppCompatActivity {
    private ListView listViewSC;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub_category);
        listViewSC=(ListView) findViewById(R.id.listViewSC);

        this.listViewSC = (ListView) findViewById(R.id.listViewSC);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        Intent intent = getIntent();
        int categoryId = intent.getIntExtra(Constant.CATEGORY_NAME, 0);

        List<IdName> subCategories = databaseAccess.getSubCategory(categoryId);
        databaseAccess.close();

        SubCategoryAdapter adapter = new SubCategoryAdapter(this, new ArrayList<IdName>(subCategories));
        this.listViewSC.setAdapter(adapter);




    }
    }

