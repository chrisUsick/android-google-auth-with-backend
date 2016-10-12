package com.google.samples.quickstart.signin;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by chris on 29-Sep-16.
 */
public class BackendSigninTask extends AsyncTask<Void, Void, User> {
    private static final String TAG = "BackendSigninTask";
    private final AppCompatActivity context;
    private final String token;
    private ObjectMapper om;

    public BackendSigninTask(AppCompatActivity c, String token) {
        this.context = c;
        om = new ObjectMapper();
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.token = token;
    }

    @Override
    protected User doInBackground(Void... voids) {
        try {
            final String url = "http://10.0.2.2:3000/auth/mobile";
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            SignInRequest request = new SignInRequest(token);
            User user = template.postForObject(url, request, User.class);
            return user;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        TextView status = (TextView)context.findViewById(R.id.status);
        if (user != null) {
            status.setText(context.getString(R.string.signed_in_backend) + user.getEmail());
        } else {
            status.setText(context.getString(R.string.signed_in_backend_failure));
        }
    }
}
