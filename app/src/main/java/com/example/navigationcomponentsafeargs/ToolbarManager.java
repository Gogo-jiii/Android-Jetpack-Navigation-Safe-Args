package com.example.navigationcomponentsafeargs;

import android.content.Context;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;

public class ToolbarManager {

    private static ToolbarManager instance;
    private AppBarConfiguration appBarConfiguration;

    private ToolbarManager() {
    }

    public static ToolbarManager getInstance() {
        if (instance == null) {
            instance = new ToolbarManager();
        }
        return instance;
    }

    public void setupToolbar(Context context, NavController navController, AppBarConfiguration appBarConfiguration, Toolbar toolbar,
                             boolean isSetupBackButton) {

        //appbarConfiguration
        this.appBarConfiguration = appBarConfiguration;
        if (appBarConfiguration == null) {
            appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        }
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

        //title color
        toolbar.setTitleTextColor(context.getResources().getColor(R.color.white));

        //back arrow button
        if (isSetupBackButton) {
            setupBackButton(context, toolbar);
        } else {
            toolbar.setNavigationIcon(null);
        }
    }

    private void setupBackButton(Context context, Toolbar toolbar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setNavigationIcon(context.getResources().getDrawable(R.drawable.ic_baseline_arrow_back,
                    null));
        }
    }

    public void setupNavigationDrawer(AppCompatActivity activity, NavController navController,
                                      Toolbar toolbar2, DrawerLayout drawerLayout, NavigationView navView) {
        //set toolbar
        activity.setSupportActionBar(toolbar2);

        //set drawwerlayout with navigation component
        NavigationUI.setupActionBarWithNavController(activity, navController, drawerLayout);

        //set navView with the controller
        NavigationUI.setupWithNavController(navView, navController);
    }

    //Top level destination doesnt have the toolbar backarrow by default.
    //All other destinations will have the back arrow.
    //But in case of Bottomnavigation view, we may want multiple top-lvel destinations.
    //Following functions creates multiple top-level destinations and pass them into the-
    //-AppConfiguration
    public AppBarConfiguration setTopLevelDestinationsBottomNavigation() {
        HashSet<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.fragmentOne);

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(topLevelDestinations)
                        .build();
        return appBarConfiguration;
    }
}
