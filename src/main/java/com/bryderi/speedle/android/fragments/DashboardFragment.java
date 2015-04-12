package com.bryderi.speedle.android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bryderi.speedle.android.R;
import com.bryderi.speedle.android.Factory;
import com.bryderi.speedle.android.services.AsyncCallback;
import com.bryderi.speedle.android.services.Classifieds;
import com.bryderi.speedle.android.model.Classified;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.alexrs.recyclerviewrenderers.adapter.RendererAdapter;
import me.alexrs.recyclerviewrenderers.builder.RendererBuilder;

public class DashboardFragment extends Fragment {

    private RendererAdapter adapter;
    private List<Classified> classifieds = new ArrayList<Classified>();


    @InjectView(R.id.classifiedsList) RecyclerView recyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  contentView = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.inject(this, contentView);
        return contentView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //If we are going to have different items, we have to indicate it
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RendererAdapter(classifieds, new RendererBuilder(new Factory() ));
        recyclerView.setAdapter(adapter);

    }

        @Override
    public void onStart() {
        super.onStart();
        new Classifieds().getClassifieds(new AsyncCallback() {
            @Override
            public void processFinish(Object output) {
                classifieds.addAll(Arrays.asList((Classified[]) output) );
                recyclerView.getAdapter().notifyDataSetChanged();
            };
        });
    }
}