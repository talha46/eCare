package com.android.ecare.ecare;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by TALHA on 22.5.15.
 */
public class Success extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);


        String nam = "no name";
        String em = "email";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nam = extras.getString("pat");
            em = extras.getString("age");
        }

        TextView n = (TextView)findViewById(R.id.textView4);
        TextView a = (TextView)findViewById(R.id.textView5);
        n.setText(nam);
        a.setText(em);
    }
}
