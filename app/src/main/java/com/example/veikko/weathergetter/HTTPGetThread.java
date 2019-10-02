package com.example.veikko.weathergetter;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

class HTTPGetThread extends Thread {

    String urlString;
    String content;


    public interface OnRequestDoneInterface {
        void onRequestDone(String data);
    }


    public HTTPGetThread(String u, OnRequestDoneInterface myInterface) {
        callBackInterface = myInterface;
        urlString = u;
    }

    OnRequestDoneInterface callBackInterface = null;

    public void run() {
        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            content = scanner.hasNext() ? scanner.next() : "";

            callBackInterface.onRequestDone(content);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


}
