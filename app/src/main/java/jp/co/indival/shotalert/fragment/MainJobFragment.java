package jp.co.indival.shotalert.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import jp.co.indival.shotalert.R;

import jp.co.indival.shotalert.adapter.MainJobAdapter;
import jp.co.indival.shotalert.common.AppInfo;
import jp.co.indival.shotalert.model.MainJob;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class MainJobFragment extends ListFragment {

    private Realm realm;

    private MainJobAdapter adapter;
    private RealmResults<MainJob> items;
    private AbsListView condView;

    private OnFragmentInteractionListener mListener;

    private static final String TAG = "MinimumDayFragment";



    // TODO: Rename and change types of parameters
    public static MainJobFragment newInstance() {
        MainJobFragment fragment = new MainJobFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MainJobFragment() {
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
        items = realm.allObjects(MainJob.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainjob, container, false);

        //初期データをセットする
        _setData();

        // Set the adapter
        condView = (AbsListView) view.findViewById(android.R.id.list);
        //set adapter
        adapter = new MainJobAdapter(getActivity(),items,true);
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
                    + " must implement OnFragmentInteractionListener");
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
            MainJob obj = items.get(position);
            mListener.onFragmentInteraction(obj,"MainJob", AppInfo.MODE_MAIN_JOB);
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
