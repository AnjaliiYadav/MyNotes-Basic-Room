package com.yadav_anjalii.my_notes.util;

import android.app.Activity;
import android.content.Intent;

import com.yadav_anjalii.my_notes.model.Note;

import static com.yadav_anjalii.my_notes.util.Constants.INTENT_TASK;

public class ScreenRedirect implements Constants {

    public static  void navigate(Activity activity, Class<?> cls, Note data ){
        Intent intent = new Intent(activity, cls);
        intent.putExtra(INTENT_TASK, data );
        activity.startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
    }

    public static  void navigate(Activity activity, Class<?> cls){
        Intent intent = new Intent(activity, cls);
        activity.startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
    }
}
