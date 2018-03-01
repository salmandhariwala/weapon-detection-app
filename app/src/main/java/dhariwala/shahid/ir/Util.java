package dhariwala.shahid.ir;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Label;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by salmandhariwala on 02/03/18.
 */

public class Util {

    public static void setImage(String path, ImageView imageView) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        final Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        imageView.setImageBitmap(bitmap);
    }

    public static String setTextViewLabels(List<Label> result, TextView resultTextView) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            sb.append(i + ") " + result.get(i).toString());
            sb.append("\n");
        }
        if (resultTextView != null) {
            resultTextView.setText(sb.toString());
        }
        return sb.toString();
    }

    public static String setTextViewFaces(List<FaceDetail> faceDetails, TextView resultTextView) {

        StringBuilder sb = new StringBuilder();

        Log.w("face",new Gson().toJson(faceDetails));

        for (int i = 0; i < faceDetails.size(); i++) {
            FaceDetail face = faceDetails.get(i);
            sb.append(new Gson().toJson(face.getEmotions()));
            sb.append("******* \n");

        }

        if (resultTextView != null) {
            resultTextView.setText(sb.toString());
        }

        return sb.toString();

    }

}
