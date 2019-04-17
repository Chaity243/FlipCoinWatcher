package com.smart.appworld.flip.activities.dashboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.smart.appworld.flip.R;
import com.smart.appworld.flip.activities.coindetails.CoinDetailsActivity;
import com.smart.appworld.flip.fragment.coinsList.CoinsListFragment;
import com.smart.appworld.flip.fragment.coinwatcher.CoinWatcherFragment;
import com.smart.appworld.flip.fragment.events.EventsListFragment;
import com.smart.appworld.flip.model.Coin;
import com.smart.appworld.flip.model.Event;
import com.smart.appworld.flip.sqliteDB.DatabaseOpenHelper;
import com.smart.appworld.flip.utility.Utils;

import static android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class MainActivity extends AppCompatActivity implements CoinsListFragment.OnCoinsListFragInteractionListener,CoinWatcherFragment.OnCoinWatcherFragInteractionListener,EventsListFragment.OnEventsListFragmentListener {
    private Context mContext;
    DatabaseOpenHelper databaseOpenHelper;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_coins:
                    initFragment(CoinsListFragment.newInstance("",""));
                    return true;
                case R.id.navigation_coin_watcher:
                    initFragment(CoinWatcherFragment.newInstance("",""));

                    return true;
                case R.id.navigation_events:
                    initFragment(EventsListFragment.newInstance("",""));

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        databaseOpenHelper = DatabaseOpenHelper.getInstance(mContext);
        initBottomNavigationView();

        checkInternet();

        if (null == savedInstanceState) {
            initFragment(CoinsListFragment.newInstance("",""));
        }


    }

    private void checkInternet() {
        if(!Utils.isNetworkAvailable())
        {
            //Show Alert Dialog
            showAlertDialog();
        }
    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof CoinsListFragment) {
           /* CoinsListFragment coinsListFragment = (CoinsListFragment) fragment;
            coinsListFragment.setOnHeadlineSelectedListener(this);*/
        }


    }

    private void initBottomNavigationView() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initFragment(Fragment fragment) {


        String backStateName = fragment.getClass().getName();
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Check if fragment is already present or not
        boolean fragmentPresent =  fragmentManager.getFragments().contains(fragmentManager.findFragmentByTag (backStateName));

        if(!fragmentPresent)
        {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.contentFrame, fragment,backStateName);
            transaction.addToBackStack(backStateName);
            transaction.commit();
        }
        else {

            // use already stored fragment
            fragmentManager.popBackStack(backStateName,0);
        }
    }






    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }



    @Override
    public void onCoinsListFragmentInteraction(Coin coin) {
        startActivity(new Intent(MainActivity.this, CoinDetailsActivity.class).putExtra("coinID",coin.getId()));
    }



    @Override
    public void onCoinWatcherFragmentInteraction(Uri uri) {

    }

    @Override
    public void OnEventsListFragmentInteraction(Event event) {

    }

    private void showAlertDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Internet not available,\nplease enable it for better performance");
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


            }

        });


        alertDialog.show();
    }
}
