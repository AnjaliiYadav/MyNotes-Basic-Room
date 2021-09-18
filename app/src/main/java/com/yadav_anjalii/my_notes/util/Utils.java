package com.yadav_anjalii.my_notes.util;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.yadav_anjalii.my_notes.util.Constants.DATE_TIME_FORMAT;

public class Utils {
    private static final String TAG = "Utils";

    public static String generateHash(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(password.getBytes());
            byte data[] = messageDigest.digest();
            String base64 = Base64.encodeToString(data, Base64.NO_WRAP);
            return base64;
        } catch (NoSuchAlgorithmException e) {
            Log.d(TAG, "generateHash: " + e.getMessage());
        }
        return null;
    }

    public static Date getCurrentDateTime() {
        return Calendar.getInstance().getTime();
    }

    public static String getCurrentDateTime(Date date) {
        try {

            SimpleDateFormat spf = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
            String dateString = spf.format(date);

            Date newDate = spf.parse(dateString);
            spf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
            return spf.format(newDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void displayMessageToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
