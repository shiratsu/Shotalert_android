package jp.co.indival.shotalert.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import jp.co.indival.shotalert.R;

/**
 * Created by shhirats on 3/3/15.
 */
public class TopAdapter extends ArrayAdapter<HashMap<String,String>> {

    private List<HashMap<String,String>> items;
    private static final int resource = R.layout.top_item;
    private LayoutInflater inflater;
    private Context aContext = null;

    final static int LIST_ITEM_PREF         = 0;
    final static int LIST_ITEM_START_DAY    = 1;
    final static int LIST_ITEM_MINIMUM_DAY  = 2;
    final static int LIST_ITEM_MAIN_JOB     = 3;
    final static int LIST_ITEM_TAG          = 4;

    public TopAdapter(Context context, int textViewResourceId,
                           List<HashMap<String,String>> object) {
        super(context, textViewResourceId, object);
        this.items = object;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.aContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            //
            v = inflater.inflate(R.layout.top_item, null);
        }
        //
        //表示系処理
        HashMap<String,String> searchData = (HashMap<String, String>)items.get(position);
        TextView contentText = (TextView)v.findViewById(R.id.content);
        TextView titleText = (TextView)v.findViewById(R.id.title);


        switch (position){
            case LIST_ITEM_PREF:
                titleText.setText(aContext.getString(R.string.pref_text));
                break;
            case LIST_ITEM_START_DAY:
                titleText.setText(aContext.getString(R.string.startday_text));
                break;
            case LIST_ITEM_MINIMUM_DAY:
                titleText.setText(aContext.getString(R.string.minimumday_text));
                break;
            case LIST_ITEM_MAIN_JOB:
                titleText.setText(aContext.getString(R.string.mainjob_text));
                break;
            case LIST_ITEM_TAG:
                titleText.setText(aContext.getString(R.string.tag_text));
                break;


        }

        contentText.setText(searchData.get("Name"));

        return v;

    }

}
