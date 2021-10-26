package android.example.newsapp_v1;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsUtils {

    public NewsUtils() {
    }

    public static final String JSON_RESPONSE = "http://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";

    public static ArrayList<News> ExtractNews() {

        ArrayList<News> newsArrayLists = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(JSON_RESPONSE);

            JSONObject response = jsonObject.getJSONObject("response");

            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject currentNewsObj = results.getJSONObject(i);

                String sectionName = currentNewsObj.getString("sectionName");

                String webTitle = currentNewsObj.getString("webTitle");

                String webPublicationDate = currentNewsObj.getString("webPublicationDate");


                News news = new News(sectionName, webTitle, webPublicationDate);

                newsArrayLists.add(news);
            }


        } catch (JSONException e) {
            Log.e("NewsUtils", "Error parsing json response:", e);
        }


        return newsArrayLists;
    }


}
