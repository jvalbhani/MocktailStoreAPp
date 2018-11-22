package com.mocktails.mocktailstore.ApiCalls;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class fetchResultApi {

    private String result = "error";
    private boolean isFinished = false;
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public void callApi(String method, String URL)
    {
        try {
            new MocktailCall().execute(method, URL).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void callApi(String method, String URL, String resp)
    {
        try {
            new MocktailCall().execute(method, URL, resp).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isFinished() {
        return isFinished;
    }

    private class MocktailCall extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... uris) {

            String JsonDATA = uris[0];
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            URL url = null;

            try {
                url = new URL(uris[1]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod(uris[0]);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
                if(uris.length > 2)
                {
                    urlConnection.setDoOutput(true);
                    Log.d("---------result", "in api call");
                    Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                    writer.write(uris[2]);
                    writer.close();
                }
                Log.d("---------result", "Response Code " + urlConnection.getResponseCode());
                if(urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK)
                    return null;


                InputStream inputStream = urlConnection.getInputStream();//input stream
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String inputLine;

                while ((inputLine = reader.readLine()) != null)
                    buffer.append(inputLine + "\n");

                if (buffer.length() == 0) {                    // Stream was empty. No point in parsing.
                    return null;
                }
                setResult(buffer.toString());
                setFinished(true);
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                    }
                }
            }
            return null;
        }
    }
}


