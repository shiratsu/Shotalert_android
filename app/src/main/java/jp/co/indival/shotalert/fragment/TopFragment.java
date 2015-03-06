package jp.co.indival.shotalert.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.indival.shotalert.R;
import jp.co.indival.shotalert.adapter.ItemListAdapter;
import jp.co.indival.shotalert.adapter.TopAdapter;
import jp.co.indival.shotalert.common.AppInfo;
import jp.co.indival.shotalert.common.Util;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopFragment extends ListFragment implements AbsListView.OnItemClickListener  {


    private TopAdapter adapter;
    private List<HashMap<String,String>> items;
    private AbsListView condView;

    final 

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
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onResume(){
        super.onResume();

        condView = (AbsListView) getActivity().findViewById(android.R.id.list);

        //初期データをセットする
        _setData();

        //set adapter
        adapter = new TopAdapter(getActivity(),R.layout.top_item,items);
        condView.setAdapter(adapter);
    }

    /**
     * トップページの初期データをセット
     */
    private void _setData() {
        items = new ArrayList<HashMap<String,String>>();

        AppInfo.codeArea        = Util.getIntPreferences(getActivity(),"codeArea");
        AppInfo.codePref        = Util.getIntPreferences(getActivity(),"codePref");
        AppInfo.codeStartday    = Util.getIntPreferences(getActivity(),"codeStartday");
        AppInfo.codeMinimumday    = Util.getIntPreferences(getActivity(),"codeMinimumday");
        AppInfo.codeMainjob     = Util.getIntPreferences(getActivity(),"codeMainjob");
        AppInfo.codeTag         = Util.getIntPreferences(getActivity(),"codeTag");

        AppInfo.namePref        = Util.getStrPreferences(getActivity(),"namePref");
        AppInfo.nameStartday    = Util.getStrPreferences(getActivity(),"nameStartday");
        AppInfo.nameMinimumday    = Util.getStrPreferences(getActivity(),"nameMinimumday");
        AppInfo.nameMainjob     = Util.getStrPreferences(getActivity(),"nameMainjob");
        AppInfo.nameTag         = Util.getStrPreferences(getActivity(),"nameTag");

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
        if(AppInfo.nameTag != null){
            tmpMap.put("Name", AppInfo.nameTag);
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
        return inflater.inflate(R.layout.fragment_top, container, false);
    }


    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                break;
        }
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
