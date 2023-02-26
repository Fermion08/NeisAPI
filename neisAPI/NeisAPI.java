package com.fermion08.lunch.neisAPI;

import android.annotation.SuppressLint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class NeisAPI {

    private URL url;
    private String key;
    private String LCCODE;
    private String SCNAME;
    private String Type;
    private String pIndex;
    private String pSize;

    private HttpURLConnection conn;

    /*
    * String key = "5f9fc2df0be443d1a69a0c421e49e846";
    String LCCODE = "S10";
    String SCNAME = "고성";
    String Type = "json";
    String pIndex = "1";
    String pSize = "10";*/

    /**  */
    public NeisAPI() {

    }
    /** make URL directly */
    public NeisAPI(String Key, String LCCODE, String SCNAME, String Type, String pIndex, String pSize) {
        try {
            URL url = makeURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** make URL with variable in class */
    private URL makeURL() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("https://open.neis.go.kr/hub/schoolInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ATPT_OFCDC_SC_CODE", "UTF-8") + "=" + URLEncoder.encode(this.LCCODE, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("SCHUL_NM", "UTF-8") + "=" + URLEncoder.encode(this.SCNAME, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("Type","UTF-8") + "=" + URLEncoder.encode(this.Type, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pIndex","UTF-8") + "=" + URLEncoder.encode(this.pIndex, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pSize","UTF-8") + "=" + URLEncoder.encode(this.pSize, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("KEY", "UTF-8") + "=" + this.key);

        return new URL(urlBuilder.toString());
    }

    /** make URL with arguments */
    public URL makeURL(String Key, String LCCODE, String SCNAME, String Type, String pIndex, String pSize) throws IOException{
        this.LCCODE = LCCODE;
        this.SCNAME = SCNAME;
        this.Type = Type;
        this.pIndex = pIndex;
        this.pSize = pSize;
        this.url = makeURL();
        return this.url;
    }

    /** call Neis API with URL in class */
    public void callAPI() throws IOException{
        this.conn = (HttpURLConnection) this.url.openConnection();
        this.conn.setRequestMethod("GET");
        this.conn.setRequestProperty("Content-type", "application/json");
    }

    /** get data from Neis API */
    public String getData() throws IOException{
        BufferedReader rd;

        if(this.conn.getResponseCode() >= 200 && this.conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(this.conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(this.conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        this.conn.disconnect();
        System.out.println(sb.toString());
        return sb.toString();
    }
}
