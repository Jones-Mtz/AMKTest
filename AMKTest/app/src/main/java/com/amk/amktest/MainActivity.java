package com.amk.amktest;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.amk.amktest.api.models.Artist;
import com.amk.amktest.api.models.Genre;
import com.amk.amktest.login.LoginActivity;
import com.amk.amktest.music.artists.ArtistsFragment;
import com.amk.amktest.music.collections.CollectionsFragment;
import com.amk.amktest.music.genres.GenresFragment;
import com.amk.amktest.utils.Constants;
import com.amk.amktest.utils.FragmentCommunication;
import com.amk.amktest.utils.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity implements FragmentCommunication {

    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar((Toolbar) findViewById(R.id.toolbar));

        goFragment(GenresFragment.newInstance());

        sharedPreferencesManager = ((AMKTest) getApplication()).getSharedPreferencesManager();
    }

    private void setUpToolbar(Toolbar tbMainToolbar) {
        setSupportActionBar(tbMainToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void goFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
            ft.replace(R.id.fl_main_container, fragment);
            ft.commit();
        }
    }

    public void addFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
            ft.replace(R.id.fl_main_container, fragment);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void fragmentNotification(String fragmentName, Object data) {
        switch (fragmentName) {
            case Constants.GENRESFRAGMENT:
                ((TextView) findViewById(R.id.toolbar_title)).setText("Artistas");
                addFragment(ArtistsFragment.newInstance(((Genre) data).getName()));
                break;

            case Constants.ARTISTSFRAGMENT:
                ((TextView) findViewById(R.id.toolbar_title)).setText("Albums");
                addFragment(CollectionsFragment.newInstance(((Artist) data).getName()));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                sharedPreferencesManager.removeKey(SharedPreferencesManager.SESSION_LOGGED);
                Intent animationActivity = new Intent(MainActivity.this, LoginActivity.class);

                startActivity(animationActivity);
                finish();
                break;
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
