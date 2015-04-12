package com.bryderi.speedle.android.viewHolders;

import android.view.View;
import android.widget.TextView;

import com.bryderi.speedle.android.R;
import com.bryderi.speedle.android.model.Classified;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder;

/**
 * Created by Mattias on 12/04/15.
 */
public class ViewHolderClassified extends RenderViewHolder<Classified> implements View.OnClickListener {


        @InjectView(R.id.title)
        TextView title;

        public ViewHolderClassified(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onBindView(Classified renderable) {
            title.setText(renderable.getName());
        }

        @Override
        public void onClick(View v) {
           // Intent intent = new Intent(getContext(), OtherClass.class);
           // getContext().startActivity(intent);
        }

    }

