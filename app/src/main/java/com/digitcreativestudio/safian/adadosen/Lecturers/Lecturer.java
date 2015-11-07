package com.digitcreativestudio.safian.adadosen.Lecturers;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by faqih_000 on 11/7/2015.
 */
public class Lecturer {
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_NIP = "nip";
    public static final String KEY_STATUS = "status";
    public static final String KEY_LAST_MODIFY = "last_modify";
    public static final String KEY_MODIFIED_BY = "modified_by";

    private int id;
    private String name;
    private String nip;
    private boolean status;
    private Date lastModify;
    private String modifiedBy;

    public Lecturer(JSONObject jObj){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

            id = jObj.getInt(KEY_ID);
            nip = jObj.getString(KEY_NIP);
            name = jObj.getString(KEY_NAME);
            status = !(jObj.getString(KEY_STATUS)).equals("0");
            lastModify = sdf.parse(jObj.getString(KEY_LAST_MODIFY));
            modifiedBy = jObj.getString(KEY_MODIFIED_BY);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int getId(){
        return id;
    }
    public String getNip(){
        return nip;
    }
    public String getName(){
        return name;
    }
    public boolean getStatus(){
        return status;
    }
    public String getModifiedBy(){
        return modifiedBy;
    }
    public String getLastModify(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        return sdf.format(lastModify);
    }
}
