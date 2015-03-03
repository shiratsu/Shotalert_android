package jp.co.indival.shotalert.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.indival.shotalert.R;
import jp.co.indival.shotalert.item.Item;

/**
 * Created by shhirats on 2/27/15.
 */

public class ItemListAdapter extends ArrayAdapter<HashMap<String,String>> {

    private List<HashMap<String,String>> items;
    private static final int resource = R.layout.select_item;
    private LayoutInflater inflater;

    public ItemListAdapter(Context context, int textViewResourceId,
                           List<HashMap<String,String>> object) {
        super(context, textViewResourceId, object);
        this.items = object;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public ItemListAdapter(Context context) {
        super(context,0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            //
            v = inflater.inflate(R.layout.select_item, null);
        }
        //
        //表示系処理
        HashMap<String,String> searchData = (HashMap<String, String>)items.get(position);
        TextView contentText = (TextView)v.findViewById(R.id.content);


        contentText.setText(searchData.get("Name"));

        return v;

    }



}
