package jp.co.indival.shotalert.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import jp.co.indival.shotalert.R;

import jp.co.indival.shotalert.adapter.AreaAdapter;
import jp.co.indival.shotalert.adapter.ItemListAdapter;
import jp.co.indival.shotalert.adapter.TopAdapter;
import jp.co.indival.shotalert.fragment.dummy.DummyContent;
import jp.co.indival.shotalert.model.Area;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class AreaFragment extends ListFragment implements AbsListView.OnItemClickListener {

    private Realm realm;

    private AreaAdapter adapter;
    private RealmResults<Area> items;
    private AbsListView condView;

    // TODO: Rename and change types of parameters
    public static AreaFragment newInstance() {
        AreaFragment fragment = new AreaFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AreaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.deleteRealmFile(getActivity());

        realm = Realm.getInstance(getActivity(),"shotalert");

    }

    @Override
    public void onResume(){
        super.onResume();

        condView = (AbsListView) getActivity().findViewById(android.R.id.list);

        //初期データをセットする
        _setData();

        //set adapter
        adapter = new AreaAdapter(getActivity(),items,true);
        condView.setAdapter(adapter);
    }

    /**
     * トップページの初期データをセット
     */
    private void _setData() {
        items = realm.allObjects(Area.class);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area, container, false);

        // Set the adapter
        condView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) condView).setAdapter(adapter);

        // Set OnItemClickListener so we can be notified on item clicks
        condView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = condView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }



}
