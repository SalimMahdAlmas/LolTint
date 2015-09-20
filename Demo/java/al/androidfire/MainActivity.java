package al.androidfire;

import android.os.Bundle;

import al.androidfire.loltint.LolTintActivity;


public class MainActivity extends LolTintActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLolTint(true,true,true,Color.COLOR_DEEP_ORANGE);
    }


}
