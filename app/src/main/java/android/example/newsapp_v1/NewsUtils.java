package android.example.newsapp_v1;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class NewsUtils {

    public NewsUtils() {
    }


    static List<News> fetchBooks(String requestUri) {

        URL url = createUrl(requestUri);
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);

        } catch (IOException e) {
            Log.e("SIGMA", "Problem making the https request:" + e);

        }
        return NewsUtils.ExtractNews(jsonResponse);
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("Error", "Error with creating url", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            } else {
                Log.e("Error", "Error response code:" + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("Error", "Problem retrieving the earthquake json response:", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder sb = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
        }
        return sb.toString();
    }

    private static ArrayList<News> ExtractNews(String jsonResponse) {

        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }
        ArrayList<News> newsArrayLists = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);

            JSONObject response = jsonObject.getJSONObject("response");

            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {

                JSONObject currentNewsObj = results.getJSONObject(i);

                String sectionName = "";
                if (currentNewsObj.has("sectionName")) {

                    sectionName = currentNewsObj.getString("sectionName");
                }

                String webTitle = "";
                if (currentNewsObj.has("webTitle")) {
                    webTitle = currentNewsObj.getString("webTitle");
                }

                String webUrl="";
                if (currentNewsObj.has("webUrl"))
                {
                    webUrl=currentNewsObj.getString("webUrl");
                }

//                String webPublicationDate = "N.A";
//                String finalDate="";

//                if (currentNewsObj.has("webPublicationDate")) {
//
//                    webPublicationDate = currentNewsObj.getString("webPublicationDate");
//                    finalDate = webPublicationDate.substring(0, 10);
//
//                }


                JSONObject fields = currentNewsObj.getJSONObject("fields");

                String thumbnail = "";
                if (fields.has("thumbnail")) {
                    thumbnail = fields.getString("thumbnail");


                }

                News news = new News(sectionName, webTitle, thumbnail,webUrl);

                newsArrayLists.add(news);
            }


        } catch (JSONException e) {
            Log.e("ExtractNews ", "Error parsing json response:", e);
        }


        return newsArrayLists;
    }


}
