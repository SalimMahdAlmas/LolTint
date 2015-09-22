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

/**
 * @author Sahid Almas
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
public class LolTint {
    private static boolean SUPPORT_LOL_TINT = false;

    /**
     * <p>LolTint is library which help you to tint your android app according to the background color
     * of your action bar without hanging with xml file</p>
     * @param systemui <p>Do You want to tint Statusbar</p>
     * @param nav <p>Do you want to tint Navigation Bar</p>
     * @param dark <p>Do yoy want daker tint at starusbar</p>
     * @param color <p>Color you want to tint the system</p>
     * @param activity <p>The class where will tint</p>
     */
    private LolTint(boolean systemui,boolean nav , boolean dark,int color,Activity activity) {
        performTint(systemui,nav,dark,color,activity);
    }

    /**
     * <p>LolTint is library which help you to tint your android app according to the background color
     * of your action bar without hanging with xml file</p>
     * @param systemui <p>Do You want to tint Statusbar</p>
     * @param nav <p>Do you want to tint Navigation Bar</p>
     * @param dark <p>Do yoy want daker tint at starusbar</p>
     * @param activity <p>The class where will tint</p>
     */

    private LolTint(boolean systemui,boolean nav , boolean dark,Activity activity) {
        performTint(systemui,nav,dark,0,activity);
    }


    /**
     * <p>LolTint is library which help you to tint your android app according to the background color
     * of your action bar without hanging with xml file</p>
     * @param systemui <p>Do You want to tint Statusbar</p>
     * @param nav <p>Do you want to tint Navigation Bar</p>
     * @param activity <p>The class where will tint</p>
     */
    private LolTint(boolean systemui,boolean nav,Activity activity) {
        performTint(systemui,nav,false,0,activity);
    }


    /**
     * <p>LolTint is library which help you to tint your android app according to the background color
     * of your action bar without hanging with xml file</p>
     * @param systemui <p>Do You want to tint Statusbar</p>
     * @param activity <p>The class where will tint</p>
     */
    private LolTint(boolean systemui,Activity activity) {
        performTint(systemui,false,false,0,activity);
    }

    /**
     * <p>LolTint is library which help you to tint your android app according to the background color
     * of your action bar without hanging with xml file</p>
     * @param systemui <p>Do You want to tint Statusbar</p>
     * @param nav <p>Do you want to tint Navigation Bar</p>
     * @param dark <p>Do yoy want daker tint at starusbar</p>
     * @param color <p>Color you want to tint the system</p>
     * @param activity <p>The class where will tint</p>
     */

    public static LolTint on(boolean systemui,boolean nav , boolean dark,int color,Activity activity ) {
       return new LolTint(systemui,nav,dark,color,activity);
    }

    /**
     * <p>LolTint is library which help you to tint your android app according to the background color
     * of your action bar without hanging with xml file</p>
     * @param systemui <p>Do You want to tint Statusbar</p>
     * @param nav <p>Do you want to tint Navigation Bar</p>
     * @param dark <p>Do yoy want daker tint at starusbar</p>
     * @param activity <p>The class where will tint</p>
     */
    public static LolTint on(boolean systemui,boolean nav , boolean dark,Activity activity ) {
        return new LolTint(systemui,nav,dark,activity);
    }


    /**
     * <p>LolTint is library which help you to tint your android app according to the background color
     * of your action bar without hanging with xml file</p>
     * @param systemui <p>Do You want to tint Statusbar</p>
     * @param nav <p>Do you want to tint Navigation Bar</p>
     * @param activity <p>The class where will tint</p>
     */

    public static LolTint on(boolean systemui,boolean nav ,Activity activity ) {
        return new LolTint(systemui,nav,activity);
    }

    /**
     * <p>LolTint is library which help you to tint your android app according to the background color
     * of your action bar without hanging with xml file</p>
     * @param systemui <p>Do You want to tint Statusbar</p>
     * @param activity <p>The class where will tint</p>
     */

    public static LolTint on(boolean systemui ,Activity activity ) {
        return new LolTint(systemui,activity);
    }

    /**
     * <p>Power of LolTint</p>
     * @param systemui <p>Do You want to tint Statusbar</p>
     * @param nav <p>Do you want to tint Navigation Bar</p>
     * @param dark <p>Do yoy want daker tint at starusbar</p>
     * @param color <p>Color you want to tint the system</p>
     * @param activity <p>The class where will tint</p>
     */
    private void performTint(boolean systemui, boolean nav, boolean dark, int color, Activity activity) {

        if (Build.VERSION.RELEASE.contains("5.")) {
            SUPPORT_LOL_TINT = true;
        }
        else {
            new LowApiException().printMessage("Sorry your device does not support loltint " +
                    "\n loltint is only supprot on android 5.0+");
        }

        if (SUPPORT_LOL_TINT) {
            if (activity.getActionBar() != null) {
                activity.getActionBar().setBackgroundDrawable(new ColorDrawable(color));
            }
            Bitmap bitmap = Utils.transferViewIntoBitmap(activity.getWindow().getDecorView());
            Rect rect = Utils.getRectFromView(activity.getWindow().getDecorView());
            Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, rect.top, bitmap.getWidth(), rect.top);
            int raw_color = Utils.grabColorFromBitmap(bitmap1);

            if (systemui && !dark) {
                activity.getWindow().setStatusBarColor(raw_color);
            } if  (dark) {
                activity.getWindow().setStatusBarColor(Color.toDarker(raw_color));
            }
            if (nav) {
                activity.getWindow().setNavigationBarColor(raw_color);
            }

        }
    }
}
