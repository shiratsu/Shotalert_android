package jp.co.indival.shotalert.item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shhirats on 2/27/15.
 */
public class Item implements Parcelable{

    public String title;
    public String content;



    public Item(String title,String content){
        this.title = title;
        this.content = content;

    }

    public Item(Parcel in){
        this.title = in.readString();
        this.content = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }
}
