package com.couponsworld;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.LoggingMXBean;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    LinearLayout loginButtonLinearLayoutActivity;
    EditText emailIdEditTextActivity, passwordEditTextActivity;
    Button loginButtonActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toast.makeText(getBaseContext(), "Please Login To Continue...", Toast.LENGTH_LONG).show();
        emailIdEditTextActivity = (EditText) findViewById(R.id.emailIdEditText);
        passwordEditTextActivity = (EditText) findViewById(R.id.passwordEditText);
        loginButtonActivity = (Button) findViewById(R.id.loginButton);
        loginButtonLinearLayoutActivity = (LinearLayout) findViewById(R.id.loginButtonLinearLayout);
        String path = Environment.getExternalStorageDirectory().getName();
        path = path + "/login.txt";
        File loginFile = new File(path);

        loginButtonActivity.setOnClickListener(new
                                                       View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View view) {
                                                               try {

                                                                   if (Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})").matcher(passwordEditTextActivity.getText()).matches() && Patterns.EMAIL_ADDRESS.matcher(emailIdEditTextActivity.getText()).matches()) {
                                                                       //Toast.makeText(getBaseContext(), path.getAbsolutePath(), Toast.LENGTH_LONG).show();
                                                                       String fileName = "login.json";
                                                                       File path = Environment.getExternalStorageDirectory();
                                                                       // get the path to sdcard
                                                                       //File sdcard = Environment.getExternalStorageDirectory();
// to this path add a new directory path
                                                                       //File dir = new File(sdcard.getAbsolutePath() + "/configuration");
// create this directory if not already created
                                                                       File root = new File(path, "configuration");
                                                                       if (!root.exists()) {
                                                                           Log.i("INFO : ", "Directory created with name : configuration");
                                                                           root.mkdirs();
                                                                       }
// create the file in which we will write the contents
                                                                       File file = new File(root, fileName);
                                                                       Log.i("INFO : ", file.getAbsolutePath());
                                                                       FileWriter fileWriter = new FileWriter(file);
                                                                       JSONObject loginCredentials = new JSONObject();
                                                                       loginCredentials.put("emailId", emailIdEditTextActivity.getText());
                                                                       loginCredentials.put("password", passwordEditTextActivity.getText());
                                                                       fileWriter.append(loginCredentials.toString());
                                                                       fileWriter.flush();
                                                                       fileWriter.close();
                                                                       Log.i("INFO : ", "Login Successfully");
                                                                       Toast.makeText(getBaseContext(), "Login Successfully...", Toast.LENGTH_LONG).show();
                                                                       startActivity(new Intent(getBaseContext(), HomeActivity.class));
                                                                       finish();
                                                                   } else if (!(Pattern.compile("^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{4,12}$").matcher(passwordEditTextActivity.getText()).matches())) {
                                                                       Toast.makeText(getBaseContext(), "Password not valid...", Toast.LENGTH_LONG).show();
                                                                   } else if (!(Patterns.EMAIL_ADDRESS.matcher(emailIdEditTextActivity.getText()).matches())) {
                                                                       Toast.makeText(getBaseContext(), "Email id not valid...", Toast.LENGTH_LONG).show();
                                                                   } else {
                                                                       Toast.makeText(getBaseContext(), "Email id Or Password not valid...", Toast.LENGTH_LONG).show();
                                                                   }

                                                               } catch (IOException ioe) {
                                                                   Toast.makeText(getBaseContext(), "Error Occured", Toast.LENGTH_LONG).show();
                                                               } catch (JSONException ioe) {
                                                                   Toast.makeText(getBaseContext(), "Error Occured", Toast.LENGTH_LONG).show();
                                                               }
                                                           }
                                                       }
        );


    }

    public void checkLogin(View v) {
        try {

            if (Pattern.compile("^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{4,12}$").matcher(passwordEditTextActivity.getText()).matches() && Patterns.EMAIL_ADDRESS.matcher(emailIdEditTextActivity.getText()).matches()) {
                String path = Environment.getExternalStorageDirectory().getName();
                path = path + "/login.txt";
                FileOutputStream fos = openFileOutput(path, MODE_APPEND);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                JSONObject loginCredentials = new JSONObject();
                loginCredentials.put("emailId", emailIdEditTextActivity.getText());
                loginCredentials.put("password", passwordEditTextActivity.getText());
                osw.write(loginCredentials.toString());
                osw.flush();
                Toast.makeText(getBaseContext(), "Login Successfully...", Toast.LENGTH_LONG);
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
                finish();
            } else if (!(Pattern.compile("^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{4,12}$").matcher(passwordEditTextActivity.getText()).matches())) {
                Toast.makeText(getBaseContext(), "Password not valid...", Toast.LENGTH_LONG);
            } else if (!(Patterns.EMAIL_ADDRESS.matcher(emailIdEditTextActivity.getText()).matches())) {
                Toast.makeText(getBaseContext(), "Email id not valid...", Toast.LENGTH_LONG);
            } else {
                Toast.makeText(getBaseContext(), "Email id Or Password not valid...", Toast.LENGTH_LONG);
            }

        } catch (IOException ioe) {
            Toast.makeText(getBaseContext(), "Error Occured", Toast.LENGTH_LONG);
        } catch (JSONException ioe) {
            Toast.makeText(getBaseContext(), "Error Occured", Toast.LENGTH_LONG);
        }
    }

    public void clearEmailId(View v) {
        emailIdEditTextActivity = (EditText) findViewById(R.id.emailIdEditText);
        emailIdEditTextActivity.setText("");
    }

    public void clearPassword(View v) {
        passwordEditTextActivity = (EditText) findViewById(R.id.passwordEditText);
        passwordEditTextActivity.setText("");
    }
}
