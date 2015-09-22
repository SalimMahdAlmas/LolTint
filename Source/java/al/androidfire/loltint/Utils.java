
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
    /**
     * This method grab the highest used color from bitmap
     * @param bitmap Bitmap you want to get color
     * @return Return the color of bitmap
     */
    public static int grabColorFromBitmap(Bitmap bitmap) {
        int color = 0;
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, new ByteArrayOutputStream());
            color = bitmap.getPixel(bitmap.getWidth() / 2, 1);
        }catch (Exception a) {
            a.printStackTrace();
        }
        retur
