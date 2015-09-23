package com.star.bitsandpizzas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class PizzaDetailActivity extends Activity {

    private ShareActionProvider mShareActionProvider;

    public static final String EXTRA_PIZZANO = "pizzaNo";

    private TextView mTextView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        int pizzaNo = getIntent().getIntExtra(EXTRA_PIZZANO, 0);

        mTextView = (TextView) findViewById(R.id.pizza_text);
        mTextView.setText(Pizza.pizzas[pizzaNo].getName());

        mImageView = (ImageView) findViewById(R.id.pizza_image);
        mImageView.setImageDrawable(getResources().getDrawable(
                Pizza.pizzas[pizzaNo].getImageResourceId()));
        mImageView.setContentDescription(Pizza.pizzas[pizzaNo].getName());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) menuItem.getActionProvider();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mTextView.getText());
        mShareActionProvider.setShareIntent(intent);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_order:
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
