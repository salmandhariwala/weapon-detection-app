package dhariwala.shahid.ir;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Label;

import java.util.List;

public class UploadActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ImageView imgPreview;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        imgPreview = (ImageView) findViewById(R.id.imgPreview);
        resultTextView = (TextView) findViewById(R.id.chargesText);

        // init aws
        AwsUtil.init(getApplicationContext());

        // Receiving the data from previous activity
        Intent i = getIntent();

        // image or video path that is captured in previous activity
        String filePath = i.getStringExtra("filePath");

        if (filePath != null) {
            // Displaying the image or video on the screen
            imgPreview.setVisibility(View.VISIBLE);
            Util.setImage(filePath, imgPreview);
            new detectLabels().execute(filePath);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Sorry, file path is missing!", Toast.LENGTH_LONG).show();
        }


    }


    public class detectLabels extends AsyncTask<String, Void, List<Label>> {

        @Override
        protected List<Label> doInBackground(String... strings) {
            return AwsUtil.getLabels(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Label> result) {
            Util.setTextViewLabels(result, resultTextView);
        }
    }

    public class detectFaces extends AsyncTask<String, Void, List<FaceDetail>> {

        @Override
        protected List<FaceDetail> doInBackground(String... strings) {
            return AwsUtil.getFaces(strings[0]);
        }

        @Override
        protected void onPostExecute(List<FaceDetail> result) {
            Util.setTextViewFaces(result, resultTextView);
        }

    }


}
