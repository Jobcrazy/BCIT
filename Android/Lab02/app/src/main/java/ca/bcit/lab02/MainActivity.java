package ca.bcit.lab02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onChangeColor(View view) {
        ConstraintLayout layOut = findViewById(R.id.main_activity);

        Random random = new Random();
        int randomColor = Color.argb(random.nextInt(256),
                random.nextInt(256), random.nextInt(256), 255);

        layOut.setBackgroundColor(randomColor);
    }

    public void onAPIVersion(View view) {
        String manufacturer = android.os.Build.MANUFACTURER;
        String model = android.os.Build.MODEL;
        int version = android.os.Build.VERSION.SDK_INT;
        String versionRelease = android.os.Build.VERSION.RELEASE;

        String messageText = " manufacturer " + manufacturer
                + " \n model " + model
                + " \n version " + version
                + " \n versionRelease " + versionRelease;

        Toast.makeText(this, messageText, Toast.LENGTH_LONG).show();
    }

    public void onSerialNumber(View view) {
        String deviceId = Settings.System.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, deviceId);
        intent.setType("text/plain");

        startActivity(intent);
    }

    public void onSpeak(View view) {
        Intent intent = new Intent(this, SpeakActivity.class);
        startActivity(intent);
    }
}