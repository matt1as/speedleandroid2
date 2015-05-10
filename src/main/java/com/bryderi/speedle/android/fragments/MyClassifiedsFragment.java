package com.bryderi.speedle.android.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bryderi.speedle.android.Factory;
import com.bryderi.speedle.android.R;
import com.bryderi.speedle.android.model.Classified;
import com.bryderi.speedle.android.services.AsyncCallback;
import com.bryderi.speedle.android.services.Classifieds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.alexrs.recyclerviewrenderers.adapter.RendererAdapter;
import me.alexrs.recyclerviewrenderers.builder.RendererBuilder;

public class MyClassifiedsFragment extends AuthenticatedFragment {

    @InjectView(R.id.classifiedsList)
    RecyclerView recyclerView;
    private RendererAdapter adapter;
    private List<Classified> classifieds = new ArrayList<Classified>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        View contentView = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.inject(this, contentView);
        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //If we are going to have different items, we have to indicate it
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RendererAdapter(classifieds, new RendererBuilder(new Factory()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (classifieds.size() == 0) {
                new Classifieds().getMyClassifieds(new AsyncCallback() {
                    @Override
                    public void processFinish(Object output) {
                        classifieds.addAll(Arrays.asList((Classified[]) output));
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }

                    ;
                });
            }


        }
    }
}