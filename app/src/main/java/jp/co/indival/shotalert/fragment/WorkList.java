package jp.co.indival.shotalert.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.costum.android.widget.PullAndLoadListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import jp.co.indival.shotalert.R;

import jp.co.indival.shotalert.common.AppController;
import jp.co.indival.shotalert.item.WorkItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnWorkInteractionListener}
 * interface.
 */
public class WorkList extends ListFragment{


    // list with the data to show in the listview
    private LinkedList<String> mListItems;

    private List<WorkItem> dataList = new ArrayList<WorkItem>();

    public static final String TAG = WorkList.class.getName();

    // The data to be displayed in the ListView
    private String[] mNames = { "Fabian", "Carlos", "Alex", "Andrea", "Karla",
            "Freddy", "Lazaro", "Hector", "Carolina", "Edwin", "Jhon",
            "Edelmira", "Andres" };

    // The data to be displayed in the ListView
    private String[] mAnimals = { "Perro", "Gato", "Oveja", "Elefante", "Pez",
            "Nicuro", "Bocachico", "Chucha", "Curie", "Raton", "Aguila",
            "Leon", "Jirafa" };


    private OnWorkInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private PullAndLoadListView mListView;

    private int totalCount = 0;
    private int nowCount = 0;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static WorkList newInstance() {
        WorkList fragment = new WorkList();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WorkList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_worklist, container, false);


        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mNames));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, mListItems);

        // Set the adapter
        mListView = (PullAndLoadListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(adapter);

        // Set OnItemClickListener so we can be notified on item clicks

        return view;
    }


    private void _loadData(String url){

        String tag_json_arry = "json_array_req";

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();



        JsonObjectRequest req = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();
                        try {
                            _parseJson(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG,e.toString());
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_arry);

    }

    /**
     * _getDataFromCafhe
     * volleyは通信すると自動でキャッシュに入れる（入れるとことを拒否もできるが）
     * なので、キャッシュからデータを取得
     * @access private
     *
     *
     */
    private void _getDataByVolley(String url){

        //まずはキャッシュを確認。なければ、volleyで通信
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(url);
        if(entry != null){
            try {
                String data = new String(entry.data, "UTF-8");
                JSONObject workJson = new JSONObject(data);
                _parseJson(workJson);

                // handle data, like converting it to xml, json, bitmap etc.,
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.e(TAG,e.toString());

            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG,e.toString());
            }

        }else{
            // Cached response doesn't exists. Make network call here
            _loadData(url);
        }
    }


    /**
     * _parseJson
     * Jsonのパース
     *
     * @param workJson
     */
    private void _parseJson(JSONObject workJson) throws JSONException {
        JSONObject resultSet = workJson.getJSONObject("ResultSet");

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnWorkInteractionListener) activity;
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
    public void onListItemClick(ListView l, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            String workid = null;
            mListener.onWorkInteraction(workid);
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
            ((TextView) emptyView).setText("empty");
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
    public interface OnWorkInteractionListener {
        // TODO: Update argument type and name
        public void onWorkInteraction(String workid);
    }



    private class LoadMoreDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            if (isCancelled()) {
                return null;
            }

            // Simulates a background task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            for (int i = 0; i < mNames.length; i++)
                mListItems.add(mNames[i]);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mListItems.add("Added after load more");

            // We need notify the adapter that the data have been changed
            ((BaseAdapter) getListAdapter()).notifyDataSetChanged();

            // Call onLoadMoreComplete when the LoadMore task, has finished
            ((PullAndLoadListView) getListView()).onLoadMoreComplete();

            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            // Notify the loading more operation has finished
            ((PullAndLoadListView) getListView()).onLoadMoreComplete();
        }
    }

    private class PullToRefreshDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            if (isCancelled()) {
                return null;
            }

            // Simulates a background task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            for (int i = 0; i < mAnimals.length; i++)
                mListItems.addFirst(mAnimals[i]);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mListItems.addFirst("Added after pull to refresh");

            // We need notify the adapter that the data have been changed
            ((BaseAdapter) getListAdapter()).notifyDataSetChanged();

            // Call onLoadMoreComplete when the LoadMore task, has finished
            ((PullAndLoadListView) getListView()).onRefreshComplete();

            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            // Notify the loading more operation has finished
            ((PullAndLoadListView) getListView()).onLoadMoreComplete();
        }
    }
}
