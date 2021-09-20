package ca.bcit.reversetext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void reverseText(View view) {
        TextView result = findViewById(R.id.result);
        EditText userInput = findViewById(R.id.userInput);

        String strUserInput = userInput.getText().toString();
        if (0 == strUserInput.length()) {
            result.setText(this.getResources().getText(R.string.error_input));
            result.setTextColor(this.getResources().getColorStateList(R.color.red));
            return;
        }

        String reversedText = "";
        for (int index = strUserInput.length() - 1; index >= 0; --index) {
            reversedText += strUserInput.charAt(index);
        }

        //result.setTextColor();
        result.setText(reversedText);
    }
}