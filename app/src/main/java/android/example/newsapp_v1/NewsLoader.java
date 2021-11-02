package android.example.newsapp_v1;

import android.content.AsyncTaskLoader;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.List;


public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;

    }


    @Nullable
    @Override
    public List<News> loadInBackground() {

        if (mUrl == null) {
            return null;
        }
        List<News> newsList = NewsUtils.fetchBooks(mUrl);

        return newsList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
