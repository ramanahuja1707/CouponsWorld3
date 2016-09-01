package com.couponsworld.utilities;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by cpt2rah on 11-08-2016.
 */
public class Utilities {

    public static JSONObject readFile(String path, Context context) {
        try {
            FileInputStream fis = new FileInputStream(new File(path.toString()));
            InputStreamReader insr = new InputStreamReader(fis);

            String readString = " ";

            int i = 0;

            while ((i = insr.read()) != -1) {
                readString = readString + (char) i;
            }

            Toast.makeText(context, "Data Read : " + readString,
                    Toast.LENGTH_SHORT).show();
            JSONObject loginCredentials = new JSONObject(readString);
            return loginCredentials;
        } catch (FileNotFoundException e) {
            Toast.makeText(context,
                    "File Not Found Exception : " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            Toast.makeText(context,
                    "IO Exception : " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
            return null;
        } catch (JSONException jsonException) {
            Toast.makeText(context,
                    "Json Exception : " + jsonException.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            return null;
        }

    }


}
