package com.bryderi.speedle.android.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bryderi.speedle.android.R;
import com.bryderi.speedle.android.model.Classified;
import com.bryderi.speedle.android.services.AsyncCallback;
import com.bryderi.speedle.android.services.Classifieds;

import org.springframework.web.client.HttpClientErrorException;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class EditDetailsActivity extends Activity {

    final String TAG = "EditDetailsActivity";

    Classified classified;

    // @InjectView(R.id.id)
    // TextView id;
    @InjectView(R.id.fragment_edit_classifieds_name)
    EditText title;
    @InjectView(R.id.fragment_edit_classifieds_description)
    EditText description;
    @InjectView(R.id.fragment_edit_classifieds_fullname)
    EditText fullName;
    @InjectView(R.id.fragment_edit_classifieds_email)
    EditText email;
    @InjectView(R.id.fragment_edit_classifieds_phone)
    EditText phone;
    @InjectView(R.id.fragment_edit_classifieds_price)
    EditText price;
    @InjectView(R.id.currency_symbol)
    Spinner currency;
    @InjectView(R.id.fragment_classified_details_backbutton)
    ImageView backButton;


    @InjectView(R.id.fragment_classified_edit_savebutton)
    ImageView saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classified_edit);

        ButterKnife.inject(this);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {
            // id.setText(b.getString("id"));
        }


        new Classifieds().getClassified(b.getString("id"), new AsyncCallback() {
            @Override
            public void processFinish(Object output) {
                classified = (Classified) output;
                title.setText(classified.getName());
                email.setText(classified.getEmail());
                phone.setText(classified.getPhoneNumber());
                description.setText(classified.getDescription());
                price.setText(classified.getPrice());
                fullName.setText(classified.getOwnerName());

            }
        });

        // Save classified
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                classified.setName(title.getText().toString());
                classified.setDescription(description.getText().toString());
                classified.setPrice(price.getText().toString());
                classified.setOwnerName(fullName.getText().toString());
                classified.setEmail(email.getText().toString());
                classified.setPhoneNumber(phone.getText().toString());

                try {
                    new Classifieds().saveClassified(classified, new AsyncCallback() {
                        public void processFinish(Object output) {
                            setResult(0);
                            finish();
                        }
                    });
                } catch (HttpClientErrorException e) {
                    Log.e(TAG, "Http error: " + e.getStatusText(), e);
                    setResult(e.getStatusCode().value());
                }
            }

        });

        // Navigate to back
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

}
