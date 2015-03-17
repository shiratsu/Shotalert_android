package jp.co.indival.shotalert;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.exceptions.RealmMigrationNeededException;


public class SplashActivity extends ActionBarActivity {


    Handler mHandler = new Handler();

    public static final String TAG = SplashActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String shotalert_path = copyBundledRealmFile(this.getResources().openRawResource(R.raw.shotalert), "shotalert");


        // If you try to open a file that doesn't match your model an exception is thrown:
        try {
            // should throw as migration is required
            Realm.getInstance(this, "shotalert");
        } catch (RealmMigrationNeededException ex) {
            Log.i(TAG, "Excellent! This is expected.");
        }


        // 2秒したらMainActivityを呼び出してSplashActivityを終了する
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // MainActivityを呼び出す
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
                // SplashActivityを終了する
                SplashActivity.this.finish();
            }
        }, 2 * 1000); // 2000ミリ秒後（2秒後）に実行


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String copyBundledRealmFile(InputStream inputStream, String outFileName) {
        try {
            File file = new File(this.getFilesDir(), outFileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, bytesRead);
            }
            outputStream.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
