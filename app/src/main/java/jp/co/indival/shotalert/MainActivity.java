package jp.co.indival.shotalert;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import io.realm.RealmObject;
import jp.co.indival.shotalert.common.AppInfo;
import jp.co.indival.shotalert.common.Util;
import jp.co.indival.shotalert.fragment.AreaFragment;
import jp.co.indival.shotalert.fragment.MainJobFragment;
import jp.co.indival.shotalert.fragment.MinimumDayFragment;
import jp.co.indival.shotalert.fragment.StartDayFragment;
import jp.co.indival.shotalert.fragment.TopFragment;
import jp.co.indival.shotalert.model.Area;
import jp.co.indival.shotalert.model.StartDay;


public class MainActivity extends ActionBarActivity
        implements TopFragment.OnMenuInteractionListener
                   ,AreaFragment.OnAreaInteractionListner
                    ,StartDayFragment.OnFragmentInteractionListener
                    ,MinimumDayFragment.OnFragmentInteractionListener
                    ,MainJobFragment.OnFragmentInteractionListener{


    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, TopFragment.newInstance())
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
        Fragment newFragment = null;
        FragmentTransaction ft = null;
        switch (position){
            case 0:

                // フラグメントのインスタンスを生成する。
                newFragment = AreaFragment.newInstance();
                break;
            case 1:

                // フラグメントのインスタンスを生成する。
                newFragment = StartDayFragment.newInstance();
                break;
            case 2:

                // フラグメントのインスタンスを生成する。
                newFragment = MinimumDayFragment.newInstance();
                break;
            case 3:

                // フラグメントのインスタンスを生成する。
                newFragment = MainJobFragment.newInstance();
                break;

        }
        // ActivityにFragmentを登録する。
        ft = getSupportFragmentManager().beginTransaction();
        // Layout位置先の指定
        ft.replace(R.id.container, newFragment);
        // Fragmentの変化時のアニメーションを指定
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onAreaItemClick(Area area) {

        String PrefName = area.getPrefName();
        String PrefCode = area.getPrefCode();
        String AreaName = area.getAreaName();
        String AreaCode = area.getPrefCode();

        Util.saveStrPreferences("PrefName",PrefName,this);
        Util.saveStrPreferences("PrefCode",PrefCode,this);
        Util.saveStrPreferences("AreaName",AreaName,this);
        Util.saveStrPreferences("AreaCode",AreaCode,this);

        goToTopFragment();

    }

    /**
     * goToTopFragment
     * topへ戻るFragmentの遷移
     */
    private void goToTopFragment(){

        // フラグメントのインスタンスを生成する。
        Fragment newFragment = TopFragment.newInstance();

        // ActivityにFragmentを登録する。
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Layout位置先の指定
        ft.replace(R.id.container, newFragment);
        // Fragmentの変化時のアニメーションを指定
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();

    }



    @Override
    public void onFragmentInteraction(RealmObject obj, String key,int mode) {

        String name = null;
        String code = null;

        switch (mode){
            case AppInfo.MODE_START_DAY:
                name = ((StartDay) obj).getName();
                code = ((StartDay) obj).getCode();
                break;
            case AppInfo.MODE_MINIMUM_DAY:
                name = ((StartDay) obj).getName();
                code = ((StartDay) obj).getCode();
                break;
            case AppInfo.MODE_MAIN_JOB:
                name = ((StartDay) obj).getName();
                code = ((StartDay) obj).getCode();
                break;

        }



        Util.saveStrPreferences("name"+key,name,this);
        Util.saveStrPreferences("code"+key,code,this);

        goToTopFragment();

    }
}
