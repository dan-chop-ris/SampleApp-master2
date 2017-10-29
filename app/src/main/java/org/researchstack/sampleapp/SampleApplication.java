package org.researchstack.sampleapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.multidex.MultiDex;

import org.apache.commons.io.IOUtils;
import org.researchstack.skin.PermissionRequestManager;
import org.researchstack.skin.ResearchStack;
import org.researchstack.skin.ResourceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import edu.chop.ris.researchstudykit.edu.chop.ris.researchstudykit.redcap.RedcapProject;

public class SampleApplication extends Application
{

    public static final String PERMISSION_NOTIFICATIONS = "SampleApp.permission.NOTIFICATIONS";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate()
    {
        super.onCreate();

        //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
        // Init RS Singleton
        //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

        //comment out -dja
        ResearchStack.init(this, new SampleResearchStack());

// get json file to work with
        String jsonDocName = "survey/asthma_daily_prompt";
        String jsonFilePath =  ResourceManager.getInstance().generatePath(ResourceManager.Resource.TYPE_JSON, jsonDocName);

        String text= "";
        try {
            InputStream inputStream = getApplicationContext().getAssets().open(jsonFilePath);
            StringWriter writer = new StringWriter();
            String encoding = StandardCharsets.UTF_8.name();
            IOUtils.copy(inputStream, writer, encoding);

             text = writer.toString();//IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());

        }catch(IOException e){
            System.out.println(e.toString());
        }

//         PostTask pt =  new PostTask(this);
//        pt.execute();
        //pt.updateUI();
        //pt.u
        //pt.u

//         new PostTask(this).execute();
        //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
        // Init permission objects
        //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

        RedcapProject project = new RedcapProject("5086D0FBBA709C9DA72FEF1A8FC054F1");
        project.setup();
//        project.doStuff();
//        RedcapSurvey survey = new RedcapSurvey();

//         If Build is M or >, add needed permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            PermissionRequestManager.PermissionRequest location = new PermissionRequestManager.PermissionRequest(Manifest.permission.ACCESS_FINE_LOCATION,
                    R.drawable.rss_ic_location_24dp,
                    R.string.rss_permission_location_title,
                    R.string.rss_permission_location_desc);
            location.setIsBlockingPermission(true);
            location.setIsSystemPermission(true);

            PermissionRequestManager.getInstance().addPermission(location);
        }

        // We have some unique permissions that tie into Settings. You will need
        // to handle the UI for this permission along w/ storing the result.
        PermissionRequestManager.PermissionRequest notifications =
                new PermissionRequestManager.PermissionRequest(
                        PERMISSION_NOTIFICATIONS,
                        R.drawable.rss_ic_notification_24dp,
                        R.string.rss_permission_notification_title,
                        R.string.rss_permission_notification_desc
                );

        PermissionRequestManager.getInstance().addPermission(notifications);
    }

    @Override
    protected void attachBaseContext(Context base)
    {
        // This is needed for android versions < 5.0 or you can extend MultiDexApplication
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}



    // The definition of our task class
    class PostTask extends AsyncTask<String, Integer, String> {
        public static final String PERMISSION_NOTIFICATIONS = "SampleApp.permission.NOTIFICATIONS";

        private Context context;

        public PostTask(Context context){
            super();

            this.context= context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //displayProgressBar("Downloading...");
        }

        @Override
        protected String doInBackground(String... params) {
//            String url=params[0];

            RedcapProject project = new RedcapProject("5086D0FBBA709C9DA72FEF1A8FC054F1");
            project.doStuff();


            // Dummy code
//            for (int i = 0; i <= 100; i += 5) {
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                publishProgress(i);
//            }
//            ResearchStack.init(context, new SampleResearchStack());

            return "All Done!";
        }
        public void updateUI(){
            ResearchStack.init(context, new SampleResearchStack());
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //updateProgressBar(values[0]);
        }

        @Override
            protected void onPostExecute(String result) {
            super.onPostExecute(result);
        ResearchStack.init(context, new SampleResearchStack());
            // If Build is M or >, add needed permissions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                PermissionRequestManager.PermissionRequest location = new PermissionRequestManager.PermissionRequest(Manifest.permission.ACCESS_FINE_LOCATION,
                        R.drawable.rss_ic_location_24dp,
                        R.string.rss_permission_location_title,
                        R.string.rss_permission_location_desc);
                location.setIsBlockingPermission(true);
                location.setIsSystemPermission(true);

                PermissionRequestManager.getInstance().addPermission(location);
            }

            // We have some unique permissions that tie into Settings. You will need
            // to handle the UI for this permission along w/ storing the result.
            PermissionRequestManager.PermissionRequest notifications =
                    new PermissionRequestManager.PermissionRequest(
                            PERMISSION_NOTIFICATIONS,
                            R.drawable.rss_ic_notification_24dp,
                            R.string.rss_permission_notification_title,
                            R.string.rss_permission_notification_desc
                    );

            PermissionRequestManager.getInstance().addPermission(notifications);


            //dismissProgressBar();
        }
    }
