package android.example.newsapp_v1;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {


    private static final String JSON_RESPONSE = "https://content.guardianapis.com/search?show-fields=thumbnail&api-key=7e7ebf19-ad71-4b2a-b8c4-f434b435a3db";

    private NewsAdapter newsAdapter;

    private static final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newsView = findViewById(R.id.list);

        newsAdapter = new NewsAdapter(this, new ArrayList<News>());

        newsView.setAdapter(newsAdapter);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, this);


    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, JSON_RESPONSE);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
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