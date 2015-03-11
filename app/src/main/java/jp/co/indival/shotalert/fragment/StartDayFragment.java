package jp.co.indival.shotalert.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import jp.co.indival.shotalert.R;

import jp.co.indival.shotalert.adapter.AreaAdapter;
import jp.co.indival.shotalert.adapter.StartDayAdapter;
import jp.co.indival.shotalert.common.AppInfo;

import jp.co.indival.shotalert.model.StartDay;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class StartDayFragment extends ListFragment {

    private Realm realm;

    private StartDayAdapter adapter;
    private RealmResults<StartDay> items;
    private AbsListView condView;

    private OnFragmentInteractionListener mListener;

    private static final String TAG = "StartDayFragment";

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static StartDayFragment newInstance() {
        StartDayFragment fragment = new StartDayFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StartDayFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.deleteRealmFile(getActivity());

        realm = Realm.getInstance(getActivity(),"shotalert");

    }

    /**
     * トップページの初期データをセット
     */
    private void _setData() {
        items = realm.allObjects(StartDay.class);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area, container, false);

        //初期データをセットする
        _setData();

        // Set the adapter
        condView = (AbsListView) view.findViewById(android.R.id.list);
        //set adapter
        adapter = new StartDayAdapter(getActivity(),items,true);
        condView.setAdapter(adapter);

        // Set OnItemClickListener so we can be notified on item clicks
        condView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnStartDayInteractionListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            StartDay obj = items.get(position);
            mListener.onFragmentInteraction(obj, "StartDay", AppInfo.MODE_START_DAY);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(RealmObject obj,String key,int mode);
    }

}
