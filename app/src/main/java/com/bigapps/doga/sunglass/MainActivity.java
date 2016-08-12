package com.bigapps.doga.sunglass;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean mRecording;

    private Button bt_recordingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_recordingButton = (Button) findViewById(R.id.recording_button);
        bt_recordingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecording) {
                    stopRecording();
                } else {
                    startRecording();
                }
            }
        });
    }

    private void startRecording() {
        setRecording(true);

        ResultReceiver receiver = new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                handleStartRecordingResult(resultCode, resultData);
            }
        };

        SunglassService.startToStartRecording(this, receiver);
    }

    private void stopRecording() {
        setRecording(false);

        ResultReceiver receiver = new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                handleStopRecordingResult(resultCode, resultData);
            }
        };

        SunglassService.startToStopRecording(this, receiver);
    }

    private void setRecording(boolean recording) {
        if (recording) {
            mRecording = true;
            bt_recordingButton.setText(R.string.stop_recording);
        } else {
            mRecording = false;
            bt_recordingButton.setText(R.string.start_recording);
        }
    }

    private void handleStartRecordingResult(int resultCode, Bundle resultData) {
        if (resultCode == SunglassService.RECORD_RESULT_OK) {
            Toast.makeText(this, "Start recording...", Toast.LENGTH_SHORT).show();
        } else {
            // start recording failed.
            Toast.makeText(this, "Start recording failed...", Toast.LENGTH_SHORT).show();
            setRecording(false);
        }
    }

    private void handleStopRecordingResult(int resultCode, Bundle resultData) {
        if (resultCode == SunglassService.RECORD_RESULT_OK) {
            String videoPath = resultData.getString(SunglassService.VIDEO_PATH);
            Toast.makeText(this, "Record succeed, file saved in " + videoPath,
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Record failed...", Toast.LENGTH_SHORT).show();
        }
    }
}
