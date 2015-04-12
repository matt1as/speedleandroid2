package com.bryderi.speedle.android.renderers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bryderi.speedle.android.viewHolders.ViewHolderClassified;

import me.alexrs.recyclerviewrenderers.renderer.Renderer;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by Mattias on 12/04/15.
 */
public class ClassifiedRenderer extends Renderer {
    public ClassifiedRenderer(int id) {
        super(id);
    }

    @Override
    public RenderViewHolder onCreateViewHolder(ViewGroup viewGroup, int id) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(id, viewGroup, false);
        return new ViewHolderClassified(itemView);
    }
}
