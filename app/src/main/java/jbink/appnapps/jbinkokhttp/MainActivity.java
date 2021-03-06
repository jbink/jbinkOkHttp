package jbink.appnapps.jbinkokhttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import jbink.appnapps.okhttplibrary.ApiCall;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();
    OkHttpClient.Builder client_builder = ApiCall.configureClient(new OkHttpClient().newBuilder());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new sampleAsyncTask().execute("URL");
    }

    private void sample(final String _url) {
        new AsyncTask<String, Void, Void>() {
            String response = null;

            @Override
            protected Void doInBackground(String... params) {

                try {
                    response = ApiCall.POST(
                            client,
                            _url,
                            new FormBody.Builder()
                                    .add("id", "id")
                                    .add("pw", "************")
                                    .build());

                    Logger.json(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            //GET
            try {
                response = ApiCall.GET(
                        client,
                        params[0]// URL
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
                return null;
            }
        }.execute("URL");
    }

    private class sampleAsyncTask extends AsyncTask<String, Void, String>{
        String response = null;

        @Override
        protected String doInBackground(String... params) {

            //POST
            try {
                response = ApiCall.SSL_POST(
                        client_builder,
                        params[0],// URL
                        new FormBody.Builder()
                                .add("id", "id")
                                .add("pw", "************")
                                .build()
                );
            } catch (IOException e) {
                e.printStackTrace();
            }


            Log.d("where", "res : " +response);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Logger.json(result);
        }
    }
}
