package com.smart.appworld.flip.fragment.events;

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
import com.smart.appworld.flip.adapter.EventsListAdapter;
import com.smart.appworld.flip.model.Event;

import java.util.List;

public class EventsListFragment extends Fragment implements EventsContract.MainView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EventsListFragment.OnEventsListFragmentListener mListener;

    private  Context mContext;


    private EventsContract.presenter presenter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;



    public EventsListFragment() {


        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsListFragment newInstance(String param1, String param2) {
        EventsListFragment fragment = new EventsListFragment();
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

    private void initRecyclerView(View view) {

        recyclerView = view.findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();



    }

    private void intPresenter() {

        presenter = new EventsListPresenterImpl(this, new EventsListIntractorImpl());
        presenter.requestDataFromServer();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Event event) {
        if (mListener != null) {
            mListener.OnEventsListFragmentInteraction(event);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof EventsListFragment.OnEventsListFragmentListener) {
            mListener = (EventsListFragment.OnEventsListFragmentListener) context;
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

    @Override
    public void setDataToRecyclerView(List<Event> eventList) {
        if(eventList!=null&&eventList.size()!=0)

        {
            EventsListAdapter adapter = new EventsListAdapter(eventList, recyclerItemClickListener);
            recyclerView.setAdapter(adapter);
        }

        else {

            Toast.makeText(mContext,
                    "Error: No events loaded...",
                    Toast.LENGTH_SHORT).show();

        }


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
    public interface OnEventsListFragmentListener {
        // TODO: Update argument type and name
        void OnEventsListFragmentInteraction(Event event);
    }

    /**
     * RecyclerItem click event listener
     * */
    private EventsListAdapter.EventsListItemClickListener recyclerItemClickListener = new EventsListAdapter.EventsListItemClickListener() {
        @Override
        public void onItemClick(Event event) {
            mListener.OnEventsListFragmentInteraction(event);

            Toast.makeText(mContext,
                    "List title:  " + event.getTitle(),
                    Toast.LENGTH_SHORT).show();

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
