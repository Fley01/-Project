package com.example.tesstproject2;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class fileFromBitmap extends AsyncTask<Void, Integer, String> {

    File file;

    Context context;
    Bitmap bitmap;
    String path_external = Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg";

    public fileFromBitmap(Bitmap bitmap, Context context) {
        this.bitmap = bitmap;
        this.context= context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // before executing doInBackground
        // update your UI
        // exp; make progressbar visible
    }

    @Override
    protected String doInBackground(Void... params) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        file = new File(Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg");
        try {
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            fo.flush();
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // back to main thread after finishing doInBackground
        // update your UI or take action after
        // exp; make progressbar gone

        sendFile(file);

    }
}