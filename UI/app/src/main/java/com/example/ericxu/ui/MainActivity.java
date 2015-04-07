package com.example.ericxu.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ericxu.utils.CommonDefs;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends Activity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static String LOG_TAG = "eric_log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = (Button) findViewById(R.id.button3);
        if (btn == null) {
            return;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              openGuideActivity();

//              testCamera1();
//                test_TakePhotoWithCustomPath();
//                try {
//                    test_cropLargePicture();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                test_settingPage();

            }
        });
    }

    private void test_settingPage() {
        startActivity(new Intent(this, SettingActivity.class));
    }

    private void test_cropLargePicture() throws IOException {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Intent intent = new Intent("com.android.camera.action.CROP");
        File sourceImageFile = new File("/sdcard/eric244103726.jpg");
        intent.setData(Uri.fromFile(sourceImageFile));
        intent.setType("image/*");
        intent.putExtra("crop", "true");

        //this means that the cropped bounds would be 2 to 1 (X to y), no matter how you adjust the x or y
        //if no below 2 arguments, you can adjust the bounds(x or y) freely
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", "false");
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("noFaceDetection", true);


        File tempCroppedFile = File.createTempFile("eric", ".jpg", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
        Log.d(LOG_TAG, "tempCroppedFile:" + tempCroppedFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempCroppedFile));
        startActivity(intent);
    }

    private File createTempImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File tempFile = File.createTempFile(imageFileName, ".jpg", dir);

        Log.d(LOG_TAG, "tempImageFile Path:" + tempFile.getAbsolutePath());

        return tempFile;
    }

    private void test_TakePhotoWithCustomPath() {
        //when start activity, tell the activity the final  image saving path in intent

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) == null) {
            Log.e(LOG_TAG, "no activity's responsible for taking a photo");

            return;
        }

        File tempImageFile;
        try {
            tempImageFile = createTempImageFile();

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (tempImageFile == null) {
            Log.e(LOG_TAG, "createTempImageFile failed");
            return;
        }

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempImageFile));
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

    }

    private void testCamera1() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void openGuideActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(CommonDefs.LOG_TAG, "in post");

                startActivity(new Intent(MainActivity.this, GuideActivity.class));
//                startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));

            }


        }, 2000);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extra = data.getExtras();
//            if (extra == null) {
//                return;
//            }
//
//            Bitmap bitmap = (Bitmap) extra.get("data");
//            if (bitmap == null) {
//                return;
//            }
//
//            ImageView imageView = (ImageView) findViewById(R.id.imageView);
//            if (imageView == null) {
//                return;
//            }
//
//            imageView.setImageBitmap(bitmap);
//
//
//        }
    }
}
