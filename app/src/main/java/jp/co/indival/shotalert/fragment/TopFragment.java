package jp.co.indival.shotalert.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.indival.shotalert.R;
import jp.co.indival.shotalert.adapter.TopAdapter;
import jp.co.indival.shotalert.common.AppInfo;
import jp.co.indival.shotalert.common.Util;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopFragment.OnMenuInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopFragment extends ListFragment {


    private TopAdapter adapter;
    private List<HashMap<String,String>> items;
    private AbsListView condView;

    private static final String TAG = "TopFragment";

    private OnMenuInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopFragment newInstance() {
        TopFragment fragment = new TopFragment();
        return fragment;
    }

    public TopFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onResume(){
        super.onResume();

    }

    /**
     * トップページの初期データをセット
     */
    private void _setData() {
        items = new ArrayList<HashMap<String,String>>();

        AppInfo.codeArea        = Util.getStrPreferences(getActivity(),"codeArea");
        AppInfo.codePref        = Util.getStrPreferences(getActivity(),"codePref");
        AppInfo.codeStartday    = Util.getStrPreferences(getActivity(),"codeStartDay");
        AppInfo.codeMinimumday    = Util.getStrPreferences(getActivity(),"codeMinimumDay");
        AppInfo.codeMainjob     = Util.getStrPreferences(getActivity(),"codeMainJob");
        AppInfo.codeOptionTag         = Util.getStrPreferences(getActivity(),"codeOptionTag");

        AppInfo.namePref        = Util.getStrPreferences(getActivity(),"namePref");
        AppInfo.nameStartday    = Util.getStrPreferences(getActivity(),"nameStartDay");
        AppInfo.nameMinimumday    = Util.getStrPreferences(getActivity(),"nameMinimumDay");
        AppInfo.nameMainjob     = Util.getStrPreferences(getActivity(),"nameMainJob");
        AppInfo.nameOptionTag         = Util.getStrPreferences(getActivity(),"nameOptionTag");

        HashMap<String,String> tmpMap = new HashMap<String,String>();
        if(AppInfo.namePref  != null){
            tmpMap.put("Name",AppInfo.namePref);
        }else{
            tmpMap.put("Name", "選択されていません");
        }

        items.add(tmpMap);

        tmpMap = new HashMap<String,String>();
        if(AppInfo.nameStartday != null){
            tmpMap.put("Name", AppInfo.nameStartday);
        }else{
            tmpMap.put("Name", "選択されていません");
        }

        items.add(tmpMap);

        tmpMap = new HashMap<String,String>();
        if(AppInfo.nameMinimumday != null){
            tmpMap.put("Name", AppInfo.nameMinimumday);
        }else{
            tmpMap.put("Name", "選択されていません");
        }

        items.add(tmpMap);

        tmpMap = new HashMap<String,String>();
        if(AppInfo.nameMainjob != null){
            tmpMap.put("Name", AppInfo.nameMainjob);
        }else{
            tmpMap.put("Name", "選択されていません");
        }
        items.add(tmpMap);

        tmpMap = new HashMap<String,String>();
        if(AppInfo.nameOptionTag != null){
            tmpMap.put("Name", AppInfo.nameOptionTag);
        }else{
            tmpMap.put("Name", "選択されていません");
        }

        items.add(tmpMap);

    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_top, container, false);

        // Set the adapter
        condView = (AbsListView) view.findViewById(android.R.id.list);

        //初期データをセットする
        _setData();

        //set adapter
        adapter = new TopAdapter(getActivity(),R.layout.top_item,items);
        condView.setAdapter(adapter);



        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMenuInteractionListener) activity;
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
        Log.d(TAG, "onListItemClick position => " + position + " : id => " + id);
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onMenuItemClick(position);
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
    public interface OnMenuInteractionListener {
        // TODO: Update argument type and name
        public void onMenuItemClick(int position);
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
