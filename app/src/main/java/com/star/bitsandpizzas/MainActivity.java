package com.star.bitsandpizzas;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity {

    private ShareActionProvider mShareActionProvider;

    private ListView mDrawerListView;

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private int mCurrentPosition;

    public static final String POSITION = "position";

    public static final String FRAGMENT_TAG = "visible_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerListView = (ListView) findViewById(R.id.drawer);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.titles)
        );

        mDrawerListView.setAdapter(arrayAdapter);

        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(POSITION);
            setActionBarTitle(mCurrentPosition);
        } else {
            selectItem(0);
        }

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        final FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);

                if (fragment instanceof TopFragment) {
                    mCurrentPosition = 0;
                } else if (fragment instanceof PizzaMaterialFragment) {
                    mCurrentPosition = 1;
                } else if (fragment instanceof PastaFragment) {
                    mCurrentPosition = 2;
                } else if (fragment instanceof StoresFragment) {
                    mCurrentPosition = 3;
                }

                setActionBarTitle(mCurrentPosition);

                mDrawerListView.setItemChecked(mCurrentPosition, true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) menuItem.getActionProvider();

        setIntent("This is example text");

        return true;
    }

    private void setIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        mShareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

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

    private void selectItem(int position) {

        mCurrentPosition = position;

        Fragment fragment;

        switch (position) {
            case 1:
                fragment = new PizzaMaterialFragment();
                break;

            case 2:
                fragment = new PastaFragment();
                break;

            case 3:
                fragment = new StoresFragment();
                break;

            default:
                fragment = new TopFragment();
        }

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.content_frame, fragment, FRAGMENT_TAG);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        fragmentTransaction.commit();

        setActionBarTitle(position);

        mDrawerLayout.closeDrawer(mDrawerListView);
    }

    private void setActionBarTitle(int position) {

        String[] titles = getResources().getStringArray(R.array.titles);

        String title;

        if (position == 0) {
            title = getResources().getString(R.string.app_name);
        } else {
            title = titles[position];
        }

        getActionBar().setTitle(title);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        boolean isDrawerOpen = mDrawerLayout.isDrawerOpen(mDrawerListView);
        menu.findItem(R.id.action_share).setVisible(!isDrawerOpen);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, mCurrentPosition);
    }
}
