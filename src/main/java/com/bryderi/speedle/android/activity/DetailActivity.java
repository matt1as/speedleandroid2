package com.bryderi.speedle.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bryderi.speedle.android.GlobalData;
import com.bryderi.speedle.android.R;
import com.bryderi.speedle.android.model.Classified;
import com.bryderi.speedle.android.model.User;
import com.bryderi.speedle.android.services.AsyncCallback;
import com.bryderi.speedle.android.services.Classifieds;
import com.bryderi.speedle.android.services.Users;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mattias on 13/04/15.
 */
public class DetailActivity extends Activity {

    Classified classified;
    // @InjectView(R.id.id) TextView id;
    @InjectView(R.id.fragment_show_classifieds_description)
    TextView description;
    @InjectView(R.id.fragment_show_classifieds_title)
    TextView title;
    @InjectView(R.id.fragment_show_classifieds_tags)
    TextView tags;
    @InjectView(R.id.fragment_show_classifieds_imageview)
    ImageView image;
    @InjectView(R.id.fragment_classified_details_editbutton)
    ImageView editButton;
    @InjectView(R.id.fragment_classified_details_favbutton)
    ImageView favImage;
    @InjectView(R.id.fragment_classified_details_backbutton)
    ImageView backButton;
    private List<Classified> favourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classified_details);

        ButterKnife.inject(this);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        // if( b != null ) {
        //     id.setText(b.getString("id"));
        // }

        // Navigate to back
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        // Navigate to edit
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditDetailsActivity.class);
                intent.putExtra("id", classified.get_id());
                startActivityForResult(intent, 0);
            }
        });

        // change favourite to edit
        favImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toggleFavourite();
            }
        });
        getClassified(b.getString("id"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            getClassified(classified.get_id());
        }
    }

    private void getClassified(String id) {
        new Classifieds().getClassified(id, new AsyncCallback() {
            @Override
            public void processFinish(Object output) {
                classified = (Classified) output;
                title.setText(classified.getName());
                description.setText(classified.getDescription());
                if (classified.getTags() != null) {
                    String txt = "";
                    for (String tag : classified.getTags()) {
                        txt = txt + " " + tag;
                    }
                    tags.setText(txt);
                    tags.setVisibility(View.VISIBLE);
                }
                if (classified.getImages().length > 0)
                    Picasso.with(getApplicationContext()).load(classified.getImages()[0]).resize(600, 600).into(image);

                if (GlobalData.getInstance().getAuthenticator() != null && classified.getOwner().equals(GlobalData.getInstance().getAuthenticator().getUser().get_id())) {
                    editButton.setVisibility(View.VISIBLE);
                }
                updateFavImage();
            }

            ;
        });
    }

    private void updateFavImage() {
        if (GlobalData.getInstance().getAuthenticator() != null && GlobalData.getInstance().getAuthenticator().getSpeedleToken() != null) {
            new Users().getMe(GlobalData.getInstance().getAuthenticator().getSpeedleToken(), new AsyncCallback() {
                @Override
                public void processFinish(Object output) {
                    favourites = Arrays.asList(((User) output).getFavourites());

                    if (favourites.contains(classified)) {
                        favImage.setImageResource(R.drawable.ic_favorite_white_24dp);
                    }
                }
            });
        } else {
            favImage.setVisibility(View.GONE);
        }
    }

    private void toggleFavourite() {
        new Classifieds().getMyFavourites(new AsyncCallback() {
            @Override
            public void processFinish(Object output) {
                if (favourites.contains(classified)) {
                    favourites.remove(classified);
                } else {
                    favourites.add(classified);
                }

            }


        });
    }
}
