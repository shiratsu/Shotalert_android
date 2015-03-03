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
import jp.co.indival.shotalert.item.WorkItem;

/**
 * Created by shhirats on 3/3/15.
 */
public class ShotListAdapter extends ArrayAdapter<WorkItem> {

    private List<WorkItem> items;
    private static final int resource = R.layout.select_item;
    private LayoutInflater inflater;

    public ShotListAdapter(Context context, int textViewResourceId,
                           List<WorkItem> object) {
        super(context, textViewResourceId, object);
        this.items = object;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            //
            v = inflater.inflate(R.layout.select_item, null);
        }
        //
        //表示系処理
        WorkItem searchData = (WorkItem) items.get(position);
        TextView contentText = (TextView) v.findViewById(R.id.content);


        contentText.setText("test");

        return v;

    }
}