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

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

import java.io.ByteArrayOutputStream;

public class Utils {
    public static int grabColorFromBitmap(Bitmap bitmap) {
        int color = 0;
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, new ByteArrayOutputStream());
            color = bitmap.getPixel(bitmap.getWidth() / 2, 1);
        }catch (Exception a) {
            a.printStackTrace();
        }
        return color;
    }
    public static Bitmap transferViewIntoBitmap(View view) {
        Bitmap result = null;

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        result = view.getDrawingCache();
        return result;
    }
    public static Rect getRectFromView(View view) {
        Rect rect = new Rect();

        view.getWindowVisibleDisplayFrame(rect);
        return rect;
    }
}
