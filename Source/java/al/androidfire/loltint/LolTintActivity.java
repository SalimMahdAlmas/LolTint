/*
 * Copyright (C) 2015 AndroidFire
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package al.androidfire.loltint;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

/**
 * @author Sahid Almas
 * @see android.app.Activity
 *
 * A library to tint statusbar according to darker version of actionbar color
 *
 * Note:
 *
 * There is no offical api to get action bar background color
 * we do some stuff that hooked out most of color from action bar
 * if action bar color is not colordrawable it may not work perfectly
 *
 */
public abstract class LolTintActivity extends Activity {
    private static boolean SUPPORT_LOL_TINT = false;
    private static boolean TURN = false;
    private static boolean SYSTEMUI  = false;
    private static boolean NAV_BAR = false;
    private static boolean DARKER= false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.RELEASE.contains("5.")) {
            SUPPORT_LOL_TINT = true;
        }
        else {
             new LowApiException().printMessage("Sorry your device does not support loltint");
        }

    }

    /**
     * This is the power of loltint
     * @param systemUi if you want to tint statusbar
     * @param navBar if you want to tint nav bar
     * @param darker if you want darker tint
     * @param color the color you want to tint
     */
    public void setLolTint(boolean systemUi,boolean navBar,boolean darker,int color) {
        if (systemUi || navBar) {
            TURN = true;
            if (systemUi) {
                SYSTEMUI = true;
            }
            if (navBar) {
                NAV_BAR = true;
            }
            if (darker) {
                DARKER= true;
            }
        }
        getActionBar().setBackgroundDrawable(new ColorDrawable(color));
    }
    /**
     * This is the power of loltint
     * @param systemUi if you want to tint statusbar
     * @param navBar if you want to tint nav bar
     * @param color the color you want to tint
     */
    public void setLolTint(boolean systemUi,boolean navBar,int color) {
        if (systemUi || navBar) {
            TURN = true;
            if (systemUi) {
                SYSTEMUI = true;
            }
            if (navBar) {
                NAV_BAR = true;
            }
        }
        getActionBar().setBackgroundDrawable(new ColorDrawable(color));
    }
    /**
     * This is the power of loltint
     * @param systemUi if you want to tint statusbar
     * @param color the color you want to tint
     */

    public void setLolTint(boolean systemUi,int color) {
        if (systemUi) {
            TURN = true;
            if (systemUi) {
                SYSTEMUI = true;
            }

        }
        getActionBar().setBackgroundDrawable(new ColorDrawable(color));
    }
    /**
     * This is the power of loltint
     * @param systemUi if you want to tint statusbar
     */

    public void setLolTint(boolean systemUi) {
        if (systemUi) {
            TURN = true;
            if (systemUi) {
                SYSTEMUI = true;
            }

        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (SUPPORT_LOL_TINT) {
            while (TURN) {

                Bitmap bitmap = Utils.transferViewIntoBitmap(getWindow().getDecorView());
                Rect rect = Utils.getRectFromView(getWindow().getDecorView());
                Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, rect.top, bitmap.getWidth(), rect.top);
                int color = Utils.grabColorFromBitmap(bitmap1);
                if (SYSTEMUI) {
                    if (DARKER) {
                        getWindow().setStatusBarColor(Color.toDarker(color));
                    }

                }
                if (NAV_BAR) {
                    getWindow().setNavigationBarColor(color);
                }

                TURN = false;
            }
        }

    }
}

