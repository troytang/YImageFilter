package com.findd.imagefilter.samples;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.findd.imagefilter.filter.BacksheetFilter;
import com.findd.imagefilter.drawable.FilterDrawable;
import com.findd.imagefilter.filter.BlackWhiteFilter;
import com.findd.imagefilter.filter.BlurFilter;
import com.findd.imagefilter.filter.DarkFilter;
import com.findd.imagefilter.filter.MosaicFilter;
import com.findd.imagefilter.filter.OldFilter;
import com.findd.imagefilter.filter.PunchFilter;
import com.findd.imagefilter.filter.ReliefFilter;
import com.findd.imagefilter.YImageFilter;

public class MainActivity extends AppCompatActivity {

    ImageView ivBlur;
    ImageView ivOld;
    ImageView ivBacksheet;
    ImageView ivReliefs;
    ImageView ivBW;
    ImageView ivChange;
    ImageView ivMosaic;
    ImageView ivDark;
    ImageView ivPunch;
    FilterDrawable od;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ivBlur = (ImageView) findViewById(R.id.ivBlur);
        ivBlur.setImageBitmap(YImageFilter.filter(new BlurFilter(), BitmapFactory.decodeResource(getResources(), R.mipmap.blur)));

        ivOld = (ImageView) findViewById(R.id.ivOld);
        ivOld.setImageBitmap(YImageFilter.filter(new OldFilter(), BitmapFactory.decodeResource(getResources(), R.mipmap.blur)));

        ivBacksheet = (ImageView) findViewById(R.id.ivBacksheet);
        ivBacksheet.setImageBitmap(YImageFilter.filter(new BacksheetFilter(), BitmapFactory.decodeResource(getResources(), R.mipmap.blur)));

        ivReliefs = (ImageView) findViewById(R.id.ivReliefs);
        ivReliefs.setImageBitmap(YImageFilter.filter(new ReliefFilter(), BitmapFactory.decodeResource(getResources(), R.mipmap.blur)));

        ivBW = (ImageView) findViewById(R.id.ivBW);
        ivBW.setImageBitmap(YImageFilter.filter(new BlackWhiteFilter(), BitmapFactory.decodeResource(getResources(), R.mipmap.blur)));

        ivMosaic = (ImageView) findViewById(R.id.ivMosaic);
        ivMosaic.setImageBitmap(YImageFilter.filter(new MosaicFilter().mosaic(40), BitmapFactory.decodeResource(getResources(), R.mipmap.blur)));

        ivDark = (ImageView) findViewById(R.id.ivDark);
        ivDark.setImageBitmap(YImageFilter.filter(new DarkFilter(), BitmapFactory.decodeResource(getResources(), R.mipmap.blur)));

        ivPunch = (ImageView) findViewById(R.id.ivPunch);
        ivPunch.setImageBitmap(YImageFilter.filter(new PunchFilter(), BitmapFactory.decodeResource(getResources(), R.mipmap.blur)));

        ivChange = (ImageView) findViewById(R.id.ivChange);
        od = new FilterDrawable(ivChange, new BlackWhiteFilter(), BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.blur), false);
        ivChange.setImageDrawable(od);
        ivChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                od.start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}