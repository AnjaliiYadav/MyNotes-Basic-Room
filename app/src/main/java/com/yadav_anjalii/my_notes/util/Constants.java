package com.yadav_anjalii.my_notes.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface Constants {
    String INTENT_DELETE = "intent_delete";
    String INTENT_TASK ="intent_task";
    String TITLE = "title";
    String DESC = "desc";
    String ENCRYPT = "encrypt";
    String PASSWORD = "password";
    DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

    int ACTIVITY_REQUEST_CODE = 201;


}
