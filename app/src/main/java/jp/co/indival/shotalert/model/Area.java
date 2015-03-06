package jp.co.indival.shotalert.model;

import io.realm.RealmObject;

/**
 * Created by shhirats on 3/5/15.
 */
public class Area extends RealmObject {


    private int id;
    private int AreaCode;
    private int PrefCode;

    private int AreaName;
    private int PrefName;


    public int getAreaCode() {
        return AreaCode;
    }

    public int getPrefCode() {
        return PrefCode;
    }

    public int getAreaName() {
        return AreaName;
    }

    public int getPrefName() {
        return PrefName;
    }

    public int getId() {

        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setAreaCode(int areaCode) {
        AreaCode = areaCode;
    }

    public void setPrefCode(int prefCode) {
        PrefCode = prefCode;
    }

    public void setAreaName(int areaName) {
        AreaName = areaName;
    }

    public void setPrefName(int prefName) {
        PrefName = prefName;
    }
}
