package jp.co.indival.shotalert.model;

import io.realm.RealmObject;

/**
 * Created by shhirats on 3/5/15.
 */
public class Area extends RealmObject {


    private int id;
    private String AreaCode;
    private String PrefCode;

    private String AreaName;
    private String PrefName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getPrefCode() {
        return PrefCode;
    }

    public void setPrefCode(String prefCode) {
        PrefCode = prefCode;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getPrefName() {
        return PrefName;
    }

    public void setPrefName(String prefName) {
        PrefName = prefName;
    }
}
