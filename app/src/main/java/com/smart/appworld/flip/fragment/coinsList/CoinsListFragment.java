package com.smart.appworld.flip.fragment.coinsList;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smart.appworld.flip.R;
import com.smart.appworld.flip.adapter.CoinsListAdapter;
import com.smart.appworld.flip.model.Coin;
import com.smart.appworld.flip.sqliteDB.DatabaseOpenHelper;
import com.smart.appworld.flip.utility.Utils;

import java.util.List;


public class CoinsListFragment extends Fragment implements CoinsListContract.MainView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnCoinsListFragInteractionListener mListener;

    private  Context mContext;


    private CoinsListContract.presenter presenter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DatabaseOpenHelper databaseOpenHelper;
    private CoinsListAdapter adapter;



    public CoinsListFragment() {


        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoinsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoinsListFragment newInstance(String param1, String param2) {
        CoinsListFragment fragment = new CoinsListFragment();
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

        View view = inflater.inflate(R.layout.fragment_coin, container, false);

        // Set the recycler
        intPresenter();
        initRecyclerView(view);
        initSwipeToRefresh(view);
        showProgress(true);

        return view;
    }

    private void initSwipeToRefresh(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.coins_fragment_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefreshButtonClick();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void initRecyclerView(View view) {

        recyclerView = view.findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }

    private void intPresenter() {

        presenter = new CoinsListPresenterImpl(this, new CoinsListIntractorImpl());
        presenter.requestDataFromServer();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Coin coin) {
        if (mListener != null) {
            mListener.onCoinsListFragmentInteraction(coin);
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        databaseOpenHelper = DatabaseOpenHelper.getInstance(mContext);
        mContext = context;
        if (context instanceof OnCoinsListFragInteractionListener) {
            mListener = (OnCoinsListFragInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCoinsListFragInteractionListener");
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
    public interface OnCoinsListFragInteractionListener {

        void onCoinsListFragmentInteraction(Coin coin);

    }

    /**
     * RecyclerItem click event listener
     * */
    private CoinsListAdapter.CoinsListItemClickListener recyclerItemClickListener = new CoinsListAdapter.CoinsListItemClickListener() {
        @Override
        public void onItemClick(Coin coin) {
            mListener.onCoinsListFragmentInteraction(coin);
            adapter.notifyDataSetChanged();


        }

        @Override
        public void onAddButtonClick(Coin coin) {
            databaseOpenHelper.insertCoin(coin);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onRemoveButtonClick(Coin coin) {
            databaseOpenHelper.deleteCoin(coin);
            adapter.notifyDataSetChanged();

        }
    };



    @Override
    public void showProgress(final boolean refreshing) {


        if (swipeRefreshLayout == null) {
            return;
        }
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(refreshing);
            }
        });



    }

    @Override
    public void hideProgress() {
        showProgress(false);

    }

    @Override
    public void setDataToRecyclerView(List<Coin> coinArrayList) {

        adapter = new CoinsListAdapter(coinArrayList, recyclerItemClickListener,databaseOpenHelper);
        recyclerView.setAdapter(adapter);




    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(mContext,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }



}
