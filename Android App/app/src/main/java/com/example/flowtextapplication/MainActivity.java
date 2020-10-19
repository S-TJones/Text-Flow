package com.example.flowtextapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText InputSentence = (EditText) findViewById(R.id.InputSentenceEditText);
        final EditText InputWidth = (EditText) findViewById(R.id.InputWidthEditText);

        Button FindSolution = (Button) findViewById(R.id.FindButton);

        final TextView ResultText = (TextView) findViewById(R.id.ResultTextView);
        final TextView ResultRiver = (TextView) findViewById(R.id.RiverTextView);

        FindSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Convert the user inputs into correct data types.
                String Sentence = InputSentence.toString();
                String stringWidth = String.valueOf(InputWidth);
                int Width;

                // Checks to see if a width was entered by the user.
                if (stringWidth.equals("")){
                    Width = 0;
                } else {
                    Width = Integer.parseInt(stringWidth);
                }

                //Calls the findFlow method.
                // It calculates the
                String[] lines = findFlow(Sentence, Width);

                // Adds visibility and text to the TextViews
                ResultText.setVisibility(View.VISIBLE);
                ResultText.setText(lines[0]);
                ResultRiver.setVisibility(View.VISIBLE);
                ResultRiver.setText(lines[1]);
            }
        });
    }

    private String[] findFlow(String sentence, int width){

        String[] output = new String[2];
        int total = sentence.split(" ").length;
        ArrayList<String> wrapped;
        int defaultWidth = 15;

        // If the width is zero, the default width is used.
        if (width == 0){
            width = defaultWidth;
        }

        wrapped = TextFlow.autoWrap(sentence, width);

        String rivers = TextFlow.locateRivers(wrapped);
        rivers = "Final text && marked maximum flow with red *.\n" + rivers;
        output[1] = rivers;

        String goals = "Total words: " + total + ", Best width: " + width + ", Max flow: " + TextFlow.getLongest();
        output[0] = goals;

        return output;
    }
}