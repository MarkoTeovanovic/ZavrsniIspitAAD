package com.ftninformatika.zavrsniispitaad;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.ftninformatika.zavrsniispitaad.Adapters.DrawerListAdapter;
import com.ftninformatika.zavrsniispitaad.Fragments.DetailFrag;
import com.ftninformatika.zavrsniispitaad.Fragments.SearchFrag;
import com.ftninformatika.zavrsniispitaad.Fragments.SettingsFragment;
import com.ftninformatika.zavrsniispitaad.Model.NavigationItem;
import com.ftninformatika.zavrsniispitaad.Network.DatabaseHelpORM;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.util.ArrayList;

import static android.os.Build.ID;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private CharSequence drawerTitle;
    private CharSequence title;
    private DatabaseHelpORM databaseHelper;

    private final ArrayList<NavigationItem> navigationItems = new ArrayList<>();

    private boolean searchShown = false;
    private boolean detailsShown = false;
    private boolean settingsShown = false;
    private boolean favoritesListShown = false;
    private boolean favoritesShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDrawer();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }
        new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();
            }
        };
    }

    private void setupDrawer() {
        setupDrawerNavigationItems();
        title = drawerTitle = getTitle();
        setupDrawerItems();
        setupToolbar();
    }

    private void setupDrawerItems() {
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.leftDrawer);

        DrawerListAdapter adapter = new DrawerListAdapter(navigationItems, this);
        drawerList.setAdapter(adapter);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        SearchFragment();
                        break;
                    case 1:
                        showSettingsFragment();
                        break;
                    case 2:
                       // About();
                        break;
                    case 3:

                        break;
                }
                drawerLayout.closeDrawer(drawerList);
            }
        });
    }
        private void SearchFragment () {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SearchFrag searchFragment = new SearchFrag();
            transaction.replace(R.id.root, searchFragment);
            transaction.commit();

            searchShown = true;
            detailsShown = false;
            settingsShown = false;
            favoritesListShown = false;
            favoritesShown = false;
        }
    private void showDetailsFragment(String ImdbID) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DetailFrag detailsFragment = new DetailFrag();
        detailsFragment.setID(ID);
        transaction.replace(R.id.root, detailsFragment);
        transaction.commit();

        searchShown = false;
        detailsShown = true;
        settingsShown = false;
        favoritesListShown = false;
        favoritesShown = false;
    }


    private void setupDrawerNavigationItems() {
        navigationItems.add(new NavigationItem(getString(R.string.drawer_home_title), getString(R.string.drawer_home_subtitle), R.drawable.icon));
        navigationItems.add(new NavigationItem(getString(R.string.drawer_settings_title), getString(R.string.drawer_settings_subtitle), R.drawable.icon));
        navigationItems.add(new NavigationItem(getString(R.string.drawer_about_title), getString(R.string.drawer_about_subtitle), R.drawable.icon));
        navigationItems.add(new NavigationItem(getString(R.string.drawer_favorites_title), getString(R.string.drawer_favorites_subtitle), R.drawable.icon));
    }
    private void showSettingsFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SettingsFragment settingsFragment = new SettingsFragment();
        transaction.replace(R.id.root, settingsFragment);
        transaction.commit();

        searchShown = false;
        detailsShown = false;
        settingsShown = true;
        favoritesListShown = false;
    }
    public DatabaseHelpORM getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelpORM.class);
        }
        return databaseHelper;
    }
}