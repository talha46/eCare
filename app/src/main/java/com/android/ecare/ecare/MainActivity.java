package com.android.ecare.ecare;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends Activity{

    DatabaseHandler db;

    TextView gender;
    static int TAKE_PICTURE = 1;
    Uri imageUri;
    signup sign;
    Login log;
    mainFrag main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        main = new mainFrag();
        fragmentTransaction.add(android.R.id.content, main);
        fragmentTransaction.commit();


        gender = (TextView)findViewById(R.id.textView);
        sign = new signup();
        log = new Login();

    }



    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(fragmentManager.getBackStackEntryCount() > 0) {
            //fragmentManager.popBackStackImmediate();
            fragmentTransaction.replace(android.R.id.content, main);
        }
        else {
            super.onBackPressed();
        }
    }
    public void sign(View v)
    {
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(android.R.id.content, sign);
        fragmentTransaction.commit();
    }


    public void save(View v){
        EditText name = (EditText)findViewById(R.id.editText);
        EditText email = (EditText)findViewById(R.id.editText2);
        EditText pas = (EditText)findViewById(R.id.pas);
        EditText age = (EditText)findViewById(R.id.editText3);
        EditText phone = (EditText)findViewById(R.id.editText4);

        db.addPatient(new Patient(name.getText().toString(), email.getText().toString(), pas.getText().toString(),
                Integer.parseInt(age.getText().toString()), phone.getText().toString(),gender.getText().toString()));

        //Patient p = db.getPatient(3);

        Intent i = new Intent(this, Login.class);
        //i.putExtra("pat", p.getName());
        //i.putExtra("age", p.getSex());
        startActivity(i);
    }


    public void login(View v)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(android.R.id.content, log);

        fragmentTransaction.commit();
    }


    public void camera(View v)
    {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);
    }
    ////start here and make sign up fragment

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case  1:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getContentResolver().notifyChange(selectedImage, null);
                    ImageView imageView = (ImageView) findViewById(R.id.imageView);
                    ContentResolver cr = getContentResolver();
                    Bitmap bitmap;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);

                        imageView.setImageBitmap(bitmap);
                        Toast.makeText(this, selectedImage.toString(),
                                Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
