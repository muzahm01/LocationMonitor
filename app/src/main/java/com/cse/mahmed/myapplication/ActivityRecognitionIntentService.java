package com.cse.mahmed.myapplication;

/**
 * Created by mahmed on 16/03/2015.
 */

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;


/**
 * Service that receives ActivityRecognition updates. It receives updates
 * in the background, even if the main Activity is not visible.
 */
public class ActivityRecognitionIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ActivityRecognitionIntentService(String name) {

        super(name);
    }
    //..

    /**
     * Called when a new activity detection update is available.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        //...
        // If the intent contains an update
        if (ActivityRecognitionResult.hasResult(intent)) {
            // Get the update
            ActivityRecognitionResult result =
                    ActivityRecognitionResult.extractResult(intent);

            DetectedActivity mostProbableActivity
                    = result.getMostProbableActivity();

            // Get the confidence % (probability)
            int confidence = mostProbableActivity.getConfidence();

            Log.d("Monitor", "test");
            // Get the type
            int activityType = mostProbableActivity.getType();
            switch (activityType) {
                case DetectedActivity.IN_VEHICLE:
                    Log.d("Monitor", "in vehicle" + confidence);
                case DetectedActivity.ON_BICYCLE:
                    Log.d("Monitor", "on Bike" + confidence);
                case DetectedActivity.ON_FOOT:
                    Log.d("Monitor", "on foot" + confidence);
                case DetectedActivity.TILTING:
                    Log.d("Monitor", "tilting" + confidence);
                case DetectedActivity.STILL:
                    Log.d("Monitor", "Still" + confidence);
                default:
                    Log.d("Monitor", "Unknown" + confidence);
            }
           /* types:
            * DetectedActivity.IN_VEHICLE
            * DetectedActivity.ON_BICYCLE
            * DetectedActivity.ON_FOOT
            * DetectedActivity.STILL
            * DetectedActivity.UNKNOWN
            * DetectedActivity.TILTING
            */
            // process
        }
    }
}
