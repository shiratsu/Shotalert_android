package jp.co.indival.shotalert;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import jp.co.indival.shotalert.fragment.AreaFragment;
import jp.co.indival.shotalert.fragment.TopFragment;


public class MainActivity extends ActionBarActivity implements TopFragment.OnMenuInteractionListener{


    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new TopFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuItemClick(int position) {
        Log.d(TAG, "onListItemClick position => " + position);
        switch (position){
            case 0:

                // フラグメントのインスタンスを生成する。
                Fragment newFragment = new AreaFragment();

                // ActivityにFragmentを登録する。
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                // Layout位置先の指定
                ft.replace(R.id.container, newFragment);
                // Fragmentの変化時のアニメーションを指定
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();

                break;
        }
    }
}
