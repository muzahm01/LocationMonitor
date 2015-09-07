package com.cse.mahmed.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

/**
 * Created by mahmed on 24/03/2015.
 */
public class ActivityRecognitionService extends IntentService {

    private static final String TAG = "ActivityRecognition";

    public ActivityRecognitionService() {
        super("ActivityRecognitionService");
    }

    /**
     * When supplied with the integer representation of the activity returns the activity as friendly string
     *
     * @param type the DetectedActivity.getType()
     * @return a friendly string of the
     */
    private static String getFriendlyName(int detected_activity_type) {
        switch (detected_activity_type) {
            case DetectedActivity.IN_VEHICLE:
                return "in vehicle";
            case DetectedActivity.ON_BICYCLE:
                return "on bike";
            case DetectedActivity.ON_FOOT:
                return "on foot";
            case DetectedActivity.TILTING:
                return "tilting";
            case DetectedActivity.STILL:
                return "still";
            default:
                return "unknown";
        }
    }

    /**
     * Google Play Services calls this once it has analysed the sensor data
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        if (ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);

            Log.d(TAG, "ActivityRecognitionResult: " + getFriendlyName(result.getMostProbableActivity().getType()));
            Log.d(TAG, result.toString());


// YOUR FUNKY CODE GOES HERE!
        }
    }
}