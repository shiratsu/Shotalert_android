package jp.co.indival.shotalert.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import jp.co.indival.shotalert.model.MainJob;
import jp.co.indival.shotalert.model.MinimumDay;
import jp.co.indival.shotalert.model.StartDay;

/**
 * Created by shhirats on 3/6/15.
 */
public class MainJobAdapter extends RealmBaseAdapter<MainJob> implements ListAdapter {

    RealmResults<MainJob> rmResults = null;

    public MainJobAdapter(Context context, RealmResults<MainJob> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);

    }

    private static class ViewHolder {
        TextView content;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link android.view.LayoutInflater#inflate(int, android.view.ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.content = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MainJob item = realmResults.get(position);
        viewHolder.content.setText(item.getName());
        return convertView;

    }

    public RealmResults<MainJob> getRealmResults() {
        return realmResults;
    }
}
