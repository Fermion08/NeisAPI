package com.fermion08.neisAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class NeisAPI {

    private String KEY;
    private String Type;
    private String pIndex;
    private String pSize;

    /**  */
    public NeisAPI() {

    }
    /** make URL directly */
    /*
    *public NeisAPI(String Key, String LCCODE, String SCNAME, String Type, String pIndex, String pSize) {
        try {
            URL url = makeURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public class School {}
    public class Lunch {}
    public class Academy {}
    public class ESchedule {}
    public class MSchedule {}
    public class HSchedule {}

    private URL url;

    private String LCCODE;
    private String SCNAME;


    //private HttpURLConnection conn;

    /*
    * String key = "5f9fc2df0be443d1a69a0c421e49e846";
    String LCCODE = "S10";
    String SCNAME = "고성";
    String Type = "json";
    String pIndex = "1";
    String pSize = "10";*/
}
