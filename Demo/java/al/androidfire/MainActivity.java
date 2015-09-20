package al.androidfire;

import android.app.Activity;
import android.os.Bundle;

import al.androidfire.loltint.Color;
import al.androidfire.loltint.LolTint;


public class MainActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        LolTint.on(true,false,true,Color.COLOR_DEEP_ORANGE,this);

    }
}

