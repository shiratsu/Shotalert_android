package jp.co.indival.shotalert.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ListFragment;
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

import com.costum.android.widget.PullAndLoadListView;

import java.util.Arrays;
import java.util.LinkedList;

import jp.co.indival.shotalert.R;

import jp.co.indival.shotalert.fragment.dummy.DummyContent;

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
