package com.easydevelopment.webbyscrappy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new doIT().execute();
            }
        });
    }

    public class doIT extends AsyncTask<Void, Void, Void> {
        String words;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect("https://www.welt.de/").get();
                //words = document.html();

                String question = document.select("#question .post-text").text();
                words = document.getElementsByClass("o-headline o-teaser__headline c-dreifaltigkeit__headline c-teaser-default__headline").text();
                //System.out.println("Question: " + question);
                /*Elements answerers = document.select("#answers .user-details a");
                for (Element answerer : answerers) {
                    //System.out.println("Answerer: " + answerer.text());
                }
            */
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //textView.setText(words);
            textView.setText("Die Welt Kopf: " + words);

        }
    }
}