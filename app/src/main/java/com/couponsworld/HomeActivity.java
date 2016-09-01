package com.couponsworld;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class HomeActivity extends AppCompatActivity {

    Button logoutButtonActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toast.makeText(getApplicationContext(), "Welcome to Coupons World .... Enjoy Saving..:-) ", Toast.LENGTH_LONG).show();
        logoutButtonActivity = (Button) findViewById(R.id.logoutButton);
        logoutButtonActivity.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        File path = Environment.getExternalStorageDirectory();
                                                        File root = new File(path, "configuration");

                                                        File loginFilePath = new File(root, "login.json");

                                                        File loginFile = new File(loginFilePath.getAbsolutePath().toString());
                                                        try {
                                                            if (loginFile.exists() && root.exists()) {

                                                                // Log.i("ÏNFO :", loginCredentails.toString());
                                                                FileInputStream fis = new FileInputStream(new File(loginFile.getAbsolutePath().toString()));
                                                                InputStreamReader insr = new InputStreamReader(fis);
                                                                String fileText = "";
                                                                for (int i = 0; (i = insr.read()) != -1; i++) {
                                                                    fileText += (char) i;
                                                                }

                                                                JSONObject loginCredentials = new JSONObject(fileText);
                                                                Log.i("INFO :", loginCredentials.toString());
                                                                loginCredentials.put("password", "");
                                                                FileOutputStream fos = new FileOutputStream(new File(loginFile.getAbsolutePath().toString()));
                                                                OutputStreamWriter osw = new OutputStreamWriter(fos);
                                                                osw.write(loginCredentials.toString());
                                                                osw.flush();
                                                                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                                                                finish();
                                                            } else {
                                                                Toast.makeText(getApplicationContext(), "Logout Error Occured...Login Again... ", Toast.LENGTH_LONG).show();
                                                                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                                                                finish();
                                                            }
                                                        } catch (JSONException jsonException) {
                                                            Log.i("INFO : ", "Json Exception..." + jsonException.getMessage());
                                                            Toast.makeText(getApplicationContext(), "Json Exception..." + jsonException.getMessage(), Toast.LENGTH_LONG).show();
                                                        } catch (FileNotFoundException e) {
                                                            Log.i("INFO : ", "File Not Found Exception..." + e.getMessage());
                                                            Toast.makeText(getApplicationContext(), "File Not Found Exception..." + e.getMessage(), Toast.LENGTH_LONG).show();
                                                        } catch (IOException e) {
                                                            Log.i("INFO : ", "IO Exception..." + e.getMessage());
                                                            Toast.makeText(getApplicationContext(), "IO Exception..." + e.getMessage(), Toast.LENGTH_LONG).show();
                                                        } catch (Exception e) {
                                                            Log.i("INFO : ", "Exception..." + e.getMessage());
                                                            Toast.makeText(getApplicationContext(), "Exception..." + e.getMessage(), Toast.LENGTH_LONG).show();
                                                        }
                                                    }


                                                }


        );
    }
}
