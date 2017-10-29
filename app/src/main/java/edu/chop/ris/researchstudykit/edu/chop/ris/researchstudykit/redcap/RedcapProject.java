package edu.chop.ris.researchstudykit.edu.chop.ris.researchstudykit.redcap;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang.builder.ToStringBuilder;

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

/**
 * Created by danie on 10/16/2017.
 */

public class RedcapProject {
    public List<RedcapInstrumentInfo> instrumentInfoList;
    private String token;
    private int instrumentCount;
    public List<RedcapInstrument> instrumentList;
    private String redcapApiUrl = "https://redcap.chop.edu/api/";


    public RedcapProject(String token) {//List<RedcapInstrumentInfo> instrumentInfoList
//        this.instrumentInfoList = instrumentInfoList;
            this.token = token;  //"token=5086D0FBBA709C9DA72FEF1A8FC054F1
        }
    public void setup(){
        new MyAsyncTask(this).execute();
        //task.execute();

    }

    public void doStuff(){
        Gson gson = new Gson();

        String requested_content;
        String redcapInstrumentJson;

        requested_content = "&content=instrument";
        redcapInstrumentJson = fetchData(requested_content);

        Type instrumentInfoListType = new TypeToken<ArrayList<RedcapInstrumentInfo>>(){}.getType();
        instrumentInfoList = gson.fromJson(redcapInstrumentJson, instrumentInfoListType);

        instrumentCount = instrumentInfoList.size();

        for(int i =0;i<instrumentCount;i++ ){
            requested_content = "&content=metadata&forms=iapp_parent_survey_2";
            redcapInstrumentJson = fetchData(requested_content);

            Type questionListType = new TypeToken<ArrayList<RedcapQuestion>>(){}.getType();

            List<RedcapQuestion> questionList = gson.fromJson(redcapInstrumentJson, questionListType);
            RedcapQuestion q4 = questionList.get(3);


        }

//
//
//        Type instrumentInfoListType = new TypeToken<ArrayList<RedcapInstrumentInfo>>(){}.getType();
//        requested_content = "&content=instrument";
//        String instrumentInfoJson = fetchData(requested_content);
//
//        instrumentInfoList = gson.fromJson(instrumentInfoJson, instrumentInfoListType);

//        int x = 1;

        //output += System.getProperty("line.separator") + responseOutput.toString();
        //Integer outLength = responseOutput.length();
//        Log.d("CHOP",output);
//            Log.d("CHOP",responseOutput);
//        Log.d("CHOP",outLength.toString());


    }

    private String fetchData(String requested_content){
            try {
                URL url = new URL(redcapApiUrl);
                //String requested_content = "&content=metadata&forms=iapp_parent_survey_2";  //instrument

                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

                String urlParameters = "token="+ token + "&format=json&returnFormat=json"+ requested_content; //instrument";
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
//                DataOutputStream dStream = new DataOutputStream( connection.getOutputStream());

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

                while ((line = br.readLine()) != null) {
                    responseOutput.append(line);
                }
                br.close();
//                String redcapInstrumentJson = responseOutput.toString();
//
//                Type questionListType = new TypeToken<ArrayList<RedcapQuestion>>(){}.getType();
//
//                Gson gson = new Gson();
//                List<RedcapQuestion> questionList = gson.fromJson(redcapInstrumentJson, questionListType);
//                RedcapQuestion q4 = questionList.get(3);
//
//                output += System.getProperty("line.separator") + responseOutput.toString();
//                Integer outLength = responseOutput.length();
//                Log.d("CHOP",output);
////            Log.d("CHOP",responseOutput);
//                Log.d("CHOP",outLength.toString());
                return responseOutput.toString();


            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
        return "";
    }


    class RedcapInstrumentInfo {

        @SerializedName("instrument_name")
        @Expose
        public String instrumentName;
        @SerializedName("instrument_label")
        @Expose
        public String instrumentLabel;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("instrumentName", instrumentName).append("instrumentLabel", instrumentLabel).toString();
        }
    }

    class MyAsyncTask extends AsyncTask {
        private RedcapProject callee;

        public MyAsyncTask(RedcapProject rs) {
            super();

            callee = rs;
        }

        @Override
        protected Object doInBackground(Object... params) {
            //post
            callee.doStuff();
//            callee.validate();
//            callee.post();
//        post();
            return new Object();
        }
    }

}
