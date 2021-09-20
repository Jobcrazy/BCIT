package ca.bcit.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class SpeakActivity extends AppCompatActivity {
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
    }

    public void onBack(View view) {
        finish();
    }

    public void onSpeak(View view) {
        EditText editText = findViewById(R.id.textToSpeak);
        String textToSpeak = editText.getText().toString();
        TextView remind = findViewById(R.id.remind);
        if (0 == textToSpeak.length()) {
            remind.setVisibility(View.VISIBLE);
        } else {
            remind.setVisibility(View.INVISIBLE);
            textToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}