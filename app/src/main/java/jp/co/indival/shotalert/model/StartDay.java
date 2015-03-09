package jp.co.indival.shotalert.model;

import io.realm.RealmObject;

/**
 * Created by shhirats on 3/5/15.
 */
public class StartDay extends RealmObject {

    private int id;
    private String Code;
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
