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
    private String SCCODE;
    private String SCNAME;
    private String SCKNAME;
    private String LCTN;
    private String FOND;

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
    public School(String KEY, String Type, String pIndex, String pSize, String LCCODE, String SCCODE, String SCNAME, String SCKNAME, String LCTN, String FOND) {
        try {
            URL url = makeURL(KEY,  Type,  pIndex,  pSize,  LCCODE,  SCCODE,  SCNAME,  SCKNAME,  LCTN,  FOND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setDefaultParam(String KEY, String Type, String pIndex, String pSize) {
        this.KEY = KEY;
        this.Type = Type;
        this.pIndex = pIndex;
        this.pSize = pSize;
    }

    public String[] getDefaultParam() {
        String[] str = new String[4];
        str[0] = this.KEY;
        str[1] = this.Type;
        str[2] = this.pIndex;
        str[3] = this.pSize;

        return str;
    }

    public void setOptionalParam(String LCCODE, String SCCODE, String SCNAME, String SCKNAME, String LCTN, String FOND) {
        this.LCCODE = LCCODE;
        this.SCCODE = SCCODE;
        this.SCNAME = SCNAME;
        this.SCKNAME = SCKNAME;
        this.LCTN = LCTN;
        this.FOND = FOND;
    }

    public String[] getOptionalParam() {
        String[] str = new String[6];
        str[0] = this.LCCODE;
        str[1] = this.SCCODE;
        str[2] = this.SCNAME;
        str[3] = this.SCKNAME;
        str[4] = this.LCTN;
        str[5] = this.FOND;

        return str;
    }


    /** make URL with variable in class */
    public URL makeURL() throws IOException {
        /*URL*/
        String tmpUrl = "https://open.neis.go.kr/hub/schoolInfo";
        tmpUrl += "?" + URLEncoder.encode("KEY", StandardCharsets.UTF_8) + "=" + this.KEY;
        tmpUrl += "&" + URLEncoder.encode("Type", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.Type, StandardCharsets.UTF_8);
        tmpUrl += "&" + URLEncoder.encode("pIndex", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.pIndex, StandardCharsets.UTF_8);
        tmpUrl += "&" + URLEncoder.encode("pSize", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.pSize, StandardCharsets.UTF_8);

        tmpUrl += "&" + URLEncoder.encode("ATPT_OFCDC_SC_CODE", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.LCCODE, StandardCharsets.UTF_8);
        tmpUrl += "&" + URLEncoder.encode("SCHUL_CODE", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.SCCODE, StandardCharsets.UTF_8);
        tmpUrl += "&" + URLEncoder.encode("SCHUL_NM", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.SCNAME, StandardCharsets.UTF_8);
        tmpUrl += "&" + URLEncoder.encode("SCHUL_KND_SC_NM", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.SCKNAME, StandardCharsets.UTF_8);
        tmpUrl += "&" + URLEncoder.encode("LCTN_SC_NM", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.LCTN, StandardCharsets.UTF_8);
        tmpUrl += "&" + URLEncoder.encode("FOND_SC_NM", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(this.FOND, StandardCharsets.UTF_8);

        return new URL(tmpUrl);
    }

    /** make URL with arguments */
    public URL makeURL(String KEY, String Type, String pIndex, String pSize, String LCCODE, String SCCODE, String SCNAME, String SCKNAME, String LCTN, String FOND) throws IOException{
        this.KEY = KEY;
        this.Type = Type;
        this.pIndex = pIndex;
        this.pSize = pSize;

        this.LCCODE = LCCODE;
        this.SCCODE = SCCODE;
        this.SCNAME = SCNAME;
        this.SCKNAME = SCKNAME;
        this.LCTN = LCTN;
        this.FOND = FOND;

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
        //System.out.println(sb);
        return sb.toString();
    }
}
