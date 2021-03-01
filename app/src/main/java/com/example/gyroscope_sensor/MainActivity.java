package com.example.gyroscope_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor gyroscope_sensor;
    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        gyroscope_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (gyroscope_sensor == null)
        {
            Toast.makeText(this, "your device have not guroscope sensor", Toast.LENGTH_SHORT).show();
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                getSupportActionBar().setTitle(String.valueOf(event.values[2]));

                if (event.values[2] > 0.5f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.CYAN);
                }
                else if (event.values[2] < -0.5f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, gyroscope_sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }
}