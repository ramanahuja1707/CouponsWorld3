package com.couponsworld;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.couponsworld.utilities.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

public class SplashScreenActivity extends AppCompatActivity {

    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        pb = (ProgressBar) findViewById(R.id.progressBar1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {

                    pb.setProgress(i);

                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                    }
                }
                try {

                    File path = Environment.getExternalStorageDirectory();
                    File root = new File(path, "configuration");

                    File loginFile = new File(root, "login.json");
                    if (loginFile.exists() && root.exists()) {
                        Log.i("INFO : ", "Login File Existing");
                        Log.i("INFO :", loginFile.getAbsolutePath());


                        // Log.i("ÏNFO :", loginCredentails.toString());
                        FileInputStream fis = new FileInputStream(new File(loginFile.getAbsolutePath().toString()));
                        InputStreamReader insr = new InputStreamReader(fis);
                        String fileText = "";
                        for (int i = 0; (i = insr.read()) != -1; i++) {
                            fileText += (char) i;
                        }

                        JSONObject loginCredentials = new JSONObject(fileText);
                        Log.i("INFO :", loginCredentials.toString());
                        if (!(loginCredentials.get("emailId").toString().equals("")) && !(loginCredentials.get("password").toString().equals(""))) {
                            Log.i("INFO : ", "Login Credentials Existing in file");
                            Log.i("INFO : ", "Diverting control to Home Page");
                            startActivity(new Intent(getBaseContext(), HomeActivity.class));
                            finish();
                        } else if (!(loginCredentials.get("emailId").toString().equals("")) && loginCredentials.get("password").equals("")) {
                            Log.i("INFO : ", "Login Credentials (Password) Not Existing in file");
                            Log.i("INFO : ", "Diverting control to Login Page");
                            startActivity(new Intent(getBaseContext(), LoginActivity.class));
                            finish();
                        } else {
                            Log.i("INFO : ", "The data in file is corrupt");
                            Log.i("INFO : ", "Diverting control to Error Page");
                            startActivity(new Intent(getBaseContext(), ErrorActivity.class));
                            finish();
                        }

                    } else {
                        Log.i("INFO : ", "Login File Not Existing");
                        Log.i("INFO : ", "Diverting control to Login Page");
                        startActivity(new Intent(getBaseContext(), LoginActivity.class));
                        finish();
                    }
                } catch (JSONException ioe) {
                    Log.i("INFO :", ioe.getMessage());
                    //Toast.makeText(getApplicationContext(), "Error Occured" + ioe.getMessage(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getBaseContext(), ErrorActivity.class));
                    finish();

                } catch (Exception e) {
                    Log.i("INFO :", e.getMessage());
                    startActivity(new Intent(getBaseContext(), ErrorActivity.class));
                    finish();
                    // Toast.makeText(getApplicationContext(), "Error Occured" + e.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        }).start();
    }

    public JSONObject readFile(String path) {
        try {
            FileInputStream fis = new FileInputStream(new File(path.toString()));
            InputStreamReader insr = new InputStreamReader(fis);

            String readString = " ";

            int i = 0;

            while ((i = insr.read()) != -1) {
                readString = readString + (char) i;
            }

            Toast.makeText(this.getBaseContext(), "Data Read : " + readString,
                    Toast.LENGTH_SHORT).show();
            JSONObject loginCredentials = new JSONObject(readString);
            return loginCredentials;
        } catch (FileNotFoundException e) {
            Toast.makeText(this.getBaseContext(),
                    "File Not Found Exception : " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            Toast.makeText(this.getBaseContext(),
                    "IO Exception : " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
            return null;
        } catch (JSONException jsonException) {
            Toast.makeText(this.getBaseContext(),
                    "Json Exception : " + jsonException.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            return null;
        }

    }


}
