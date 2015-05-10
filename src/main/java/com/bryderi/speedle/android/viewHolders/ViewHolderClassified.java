package com.bryderi.speedle.android.viewHolders;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bryderi.speedle.android.R;
import com.bryderi.speedle.android.activity.DetailActivity;
import com.bryderi.speedle.android.model.Classified;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by Mattias on 12/04/15.
 */
public class ViewHolderClassified extends RenderViewHolder<Classified> implements View.OnClickListener {


    Classified classified;


    @InjectView(R.id.classifieds_price)
    TextView price;

    @InjectView(R.id.classifieds_image)
    ImageView image;

    public ViewHolderClassified(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onBindView(Classified renderable) {
        classified = renderable;
        price.setText(renderable.getPrice() + " " + renderable.getCurrency());
        if (renderable.getThumbnails().length > 0)
            Picasso.with(getContext()).load(renderable.getThumbnails()[0]).fit().into(image);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("id", classified.get_id());
        getContext().startActivity(intent);
    }
}

