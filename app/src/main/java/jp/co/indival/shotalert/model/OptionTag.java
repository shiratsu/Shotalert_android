package jp.co.indival.shotalert.model;

import io.realm.RealmObject;

/**
 * Created by shhirats on 3/5/15.
 */
public class OptionTag extends RealmObject {

    private int id;
    private int Code;
    private int Name;

    public int getId() {
        return id;
    }

    public int getCode() {
        return Code;
    }

    public int getName() {
        return Name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(int code) {
        Code = code;
    }

    public void setName(int name) {
        Name = name;
    }
}
