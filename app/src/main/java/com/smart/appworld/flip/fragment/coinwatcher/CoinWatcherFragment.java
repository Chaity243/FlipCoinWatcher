package com.smart.appworld.flip.fragment.coinwatcher;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.smart.appworld.flip.R;
import com.smart.appworld.flip.adapter.CoinWatcherAdapter;
import com.smart.appworld.flip.model.Coin;
import com.smart.appworld.flip.sqliteDB.DatabaseOpenHelper;
import com.smart.appworld.flip.utility.Utils;

import java.util.Timer;
import java.util.TimerTask;


public class CoinWatcherFragment extends Fragment implements CoinWatcherContract.MainView {
    // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnCoinWatcherFragInteractionListener mListener;

    private Context mContext;


    private CoinWatcherContract.presenter presenter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private DatabaseOpenHelper databaseOpenHelper;
    private CoinWatcherAdapter adapter;

    private  Timer timer; // This will create a new Thread;
    private  final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    private  final long PERIOD_MS = 5*1000; // time in milliseconds between successive task executions.
    final Handler handler = new Handler();


    // runnable will call  refresh the data
    final Runnable Update = new Runnable() {
        public void run() {
            presenter.requestDataFromServer();

        }
    };




    public CoinWatcherFragment() {


        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoinWatcherFragment.
     */
// TODO: Rename and change types and number of parameters
    public static CoinWatcherFragment newInstance(String param1, String param2) {
        CoinWatcherFragment fragment = new CoinWatcherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_coin_watcher, container, false);

        // Set the recycler
        initRecyclerView(view);
        intPresenter();
        return view;
    }

    private void initRecyclerView(View view) {

        recyclerView = view.findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        /*  adapter = new CoinWatcherAdapter(databaseOpenHelper.getAllCoins(),recyclerItemClickListener);*/
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }

    private void intPresenter() {

        presenter = new CoinWatcherPresenter(this, new CoinWatcherIntractorImpl(),databaseOpenHelper.getAllCoinsIds());
        presenter.requestDataFromServer();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCoinWatcherFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        databaseOpenHelper = DatabaseOpenHelper.getInstance(mContext);
        mContext = context;
        if (context instanceof OnCoinWatcherFragInteractionListener) {
            mListener = (OnCoinWatcherFragInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCoinWatcherFragInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCoinWatcherFragInteractionListener {
        // TODO: Update argument type and name
        void onCoinWatcherFragmentInteraction(Uri uri);
    }

    /**
     * RecyclerItem click event listener
     * */
    private CoinWatcherAdapter.CoinsListItemClickListener recyclerItemClickListener = new CoinWatcherAdapter.CoinsListItemClickListener() {
        @Override
        public void onItemClick(Coin coin) {



        }
    };



    @Override
    public void showProgress(boolean refreshing) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToRecyclerView(JsonElement coinWatcherJSONObject) {
        if(databaseOpenHelper.getCoinsCount()>0)
        {
            adapter = new CoinWatcherAdapter(databaseOpenHelper.getAllCoins(),coinWatcherJSONObject.getAsJsonObject(),recyclerItemClickListener,databaseOpenHelper);
            recyclerView.setAdapter(adapter);
            timeRefresher(60*1000,60*1000);


        }
        else {
            Toast.makeText(mContext,
                    "Please add some coins in watcher list...",
                    Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(mContext,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
            timeRefresher(60*1000,60*1000);


    }

    @Override
    public void onPause() {
        super.onPause();
        if(timer!=null)
        {
            timer.cancel();

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void timeRefresher(final long DELAY_MS,final long PERIOD_MS){
        timer= new Timer();
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }

        }, DELAY_MS, PERIOD_MS);

    }


}