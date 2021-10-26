package android.example.newsapp_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newsView=findViewById(R.id.list);

        ArrayList<News>newsArrayList=NewsUtils.ExtractNews();

        NewsAdapter newsAdapter=new NewsAdapter(this,newsArrayList);

        newsView.setAdapter(newsAdapter);


    }
}