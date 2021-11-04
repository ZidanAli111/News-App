package android.example.newsapp_v1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {


    private static final String JSON_RESPONSE = "https://content.guardianapis.com/search?page-size=200&show-fields=thumbnail&api-key=7e7ebf19-ad71-4b2a-b8c4-f434b435a3db";

    private NewsAdapter newsAdapter;

    private static final int LOADER_ID = 1;

    private TextView emptyTextView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newsView = findViewById(R.id.list);

        emptyTextView=findViewById(R.id.empty_view);

        newsView.setEmptyView(emptyTextView);

        newsAdapter = new NewsAdapter(this, new ArrayList<News>());

        newsView.setAdapter(newsAdapter);


        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        if (networkInfo!=null&&networkInfo.isConnected())
        {

            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);

        }else {

            View loadingIndicator=findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            emptyTextView.setText("No internet Connection");
        }


        newsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news=newsAdapter.getItem(position);
                Uri uri=Uri.parse(news.getmUrl());


                CustomTabsIntent.Builder builder= new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent=builder.build();
                customTabsIntent.launchUrl(MainActivity.this,uri);
            }
        });
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, JSON_RESPONSE);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {

        View loadingIndicator=findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        emptyTextView.setText("No News Data found");

        newsAdapter.clear();
        if (data != null && !data.isEmpty()) {
            newsAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

        newsAdapter.clear();
    }
}