package com.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.utils.ServerUtil;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 10-3-13
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 */
public class LoginTask extends AsyncTask{


    private ProgressDialog mDialog;
    private Context ctx;
    @Override
    protected void onPreExecute() {
        mDialog = new ProgressDialog(ctx);
        mDialog.setMessage("Validating...");
        mDialog.setCancelable(false);
        mDialog.show();
    }

    @Override
    protected void onPostExecute(Object o) {
        mDialog.dismiss();
    }

    public LoginTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected Object doInBackground(Object... parameters) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost requestLogin = new HttpPost(ServerUtil.getServerAddres(false)+"/j_spring_security_check?");
        JSONObject jUser = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("j_username",(String)parameters[0]));
        params.add(new BasicNameValuePair("j_password",(String)parameters[1]));
        try {
            requestLogin.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse response = client.execute(requestLogin);
            String user = response.getFirstHeader("user").getValue();
            jUser = new JSONObject(user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return jUser;
    }
}
