package org.researchstack.sampleapp;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import edu.chop.ris.researchstudykit.edu.chop.ris.researchstudykit.redcap.RedcapQuestion;

/**
 * Created by danie on 10/10/2017.
 */

public class RedcapSurvey {
    //private BufferedReader reader;
    private final String USER_AGENT = "Mozilla/5.0";


    public RedcapSurvey(String token) {

    }

    public RedcapSurvey() {

        try {
            //post();
            new MyAsyncTask(this).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void post(){
        try {
            URL url = new URL("https://redcap.chop.edu/api/");
            String requested_content = "metadata&forms=iapp_parent_survey_2";  //instrument

//            URL url = new URL("https://www.google.com/");

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            String urlParameters = "token=5086D0FBBA709C9DA72FEF1A8FC054F1&format=json&returnFormat=json&content="+ requested_content; //instrument";
            byte[] postData       = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
            }
            int    postDataLength = postData.length;

            connection.setRequestMethod("POST");
            connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
            connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;05");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty( "charset", "utf-8");
            connection.setRequestProperty( "Content-Length", Integer.toString( postDataLength  ));

            connection.setDoOutput(true);


            OutputStream os = connection.getOutputStream();
            DataOutputStream dStream = new DataOutputStream(os);
//            DataOutputStream dStream = new DataOutputStream( connection.getOutputStream());


            dStream.writeBytes(urlParameters);
            dStream.flush();
            dStream.close();

            int responseCode = connection.getResponseCode();
            String output = "Request URL " + url;
            output += System.getProperty("line.separator") + "Request Parameters " + urlParameters;
            output += System.getProperty("line.separator") + "Response Code " + responseCode;

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";

            StringBuilder responseOutput = new StringBuilder();

//            String responseOutput = "";

            while ((line = br.readLine()) != null) {
//                responseOutput += line;
                responseOutput.append(line);
            }
            br.close();
            String redcapInstrumentJson = responseOutput.toString();

            Type questionListType = new TypeToken<ArrayList<RedcapQuestion>>(){}.getType();

            Gson gson = new Gson();
            List<RedcapQuestion> questionList = gson.fromJson(redcapInstrumentJson, questionListType);
            RedcapQuestion q4 = questionList.get(3);

            output += System.getProperty("line.separator") + responseOutput.toString();
            Integer outLength = responseOutput.length();
            Log.d("CHOP",output);
//            Log.d("CHOP",responseOutput);
            Log.d("CHOP",outLength.toString());

        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }


    public void validate(){
        Log.d("CHOP","calling parent");
    }
}
class MyAsyncTask extends AsyncTask {
    private RedcapSurvey callee;

    public MyAsyncTask(RedcapSurvey rs){
        super();

        callee = rs;
    }

    @Override
    protected Object doInBackground(Object... params){
        //post
        callee.validate();
        callee.post();
//        post();
        return new Object();
    }
}