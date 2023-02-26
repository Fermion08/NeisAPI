package com.fermion08.neisAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class School {
    protected URL url;

    private String KEY;
    private String Type;
    private String pIndex;
    private String pSize;

    private String LCCODE;
    private String SCNAME;

    //HttpURLConnection conn;


    /*
    * String key = "5f9fc2df0be443d1a69a0c421e49e846";
    String LCCODE = "S10";
    String SCNAME = "고성";
    String Type = "json";
    String pIndex = "1";
    String pSize = "10";*/

    /**  */
    public School() {

    }
    /** make URL directly */
    public School(String Key, String LCCODE, String SCNAME, String Type, String pIndex, String pSize) {
        try {
            URL url = makeURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** make URL with variable in class */
    private URL makeURL() throws IOException {
        /*URL*/
        String tmpUrl = "https://open.neis.go.kr/hub/schoolInfo";
        tmpUrl += "?" + URLEncoder.encode("ATPT_OFCDC_SC_CODE", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.LCCODE, StandardCharsets.UTF_8);
        tmpUrl += "&" + URLEncoder.encode("SCHUL_NM", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.SCNAME, StandardCharsets.UTF_8);
        tmpUrl += "&" + URLEncoder.encode("Type", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.Type, StandardCharsets.UTF_8);
        tmpUrl += "&" + URLEncoder.encode("pIndex", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.pIndex, StandardCharsets.UTF_8);
        tmpUrl += "&" + URLEncoder.encode("pSize", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.pSize, StandardCharsets.UTF_8);
        tmpUrl += "&" + URLEncoder.encode("KEY", StandardCharsets.UTF_8) + "=" + this.KEY;

        return new URL(tmpUrl);
    }

    /** make URL with arguments */
    public URL makeURL(String KEY, String LCCODE, String SCNAME, String Type, String pIndex, String pSize) throws IOException{
        this.KEY = KEY;
        this.LCCODE = LCCODE;
        this.SCNAME = SCNAME;
        this.Type = Type;
        this.pIndex = pIndex;
        this.pSize = pSize;
        this.url = makeURL();
        return this.url;
    }

    /** call Neis API */
    public String callAPI() throws IOException{
        HttpURLConnection conn;
        conn = (HttpURLConnection) this.url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json; charset=utf-8");

        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        conn.disconnect();
        System.out.println(sb);
        return sb.toString();
    }
}
