package com.fc.mydemo.activity.camera;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.fc.mydemo.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

@EActivity(R.layout.activity_camera_test)
public class CameraTestActivity extends Activity {

    @ViewById(R.id.btnTakePicture)
    protected Button takePictureBtn;
    @ViewById(R.id.btnAutoFocus)
    protected Button autoFocusBtn;

    private Camera camera = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void init() {
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().setFixedSize(320, 240); // 设置分辨率
        surfaceView.getHolder().addCallback(new SurfaceCallback());
    }

    @Click({R.id.btnTakePicture, R.id.btnAutoFocus})
    protected void click(View view) {
        if (view.getId() == R.id.btnTakePicture) {
            if (camera != null)
                camera.takePicture(null, null, new TakePictureCallback()); // 拍照
        } else if (view.getId() == R.id.btnAutoFocus) {
            if (camera != null)
                camera.autoFocus(null); // 对焦
        }
    }

    private final class SurfaceCallback implements SurfaceHolder.Callback {
        private boolean preview; // 是否正在预览

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera = Camera.open();
                Camera.Parameters parameters = camera.getParameters();
                parameters.setPreviewFrameRate(5); //每秒5帧
                parameters.setPictureFormat(PixelFormat.JPEG);//设置照片的输出格式
                parameters.set("jpeg-quality", 85);//照片质量
                camera.setParameters(parameters);
                camera.setPreviewDisplay(holder);
                updateCameraParameters();
                camera.startPreview();
                preview = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (camera != null) {
                if (preview) {
                    camera.stopPreview();
                    preview = false;
                }
                camera.release();
                camera = null; // 记得释放
            }
        }
    }

    private final class TakePictureCallback implements Camera.PictureCallback {
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Matrix matrix = new Matrix();
            //设置缩放
            matrix.postScale(0.5f, 0.5f);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            File file = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
            try {
                FileOutputStream outStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                outStream.close();
                camera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateCameraParameters() {
        if (camera != null) {
            Camera.Parameters p = camera.getParameters();

            long time = new Date().getTime();
            p.setGpsTimestamp(time);

            Camera.Size previewSize = findBestPreviewSize(p);
            p.setPreviewSize(previewSize.width, previewSize.height);
            p.setPictureSize(previewSize.width, previewSize.height);

            if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                camera.setDisplayOrientation(90);
                p.setRotation(90);
            }
            camera.setParameters(p);
        }
    }

    /**
     * 找到最合适的显示分辨率 （防止预览图像变形）
     *
     * @param parameters
     * @return
     */
    private Camera.Size findBestPreviewSize(Camera.Parameters parameters) {

        // 系统支持的所有预览分辨率
        String previewSizeValueString = null;
        previewSizeValueString = parameters.get("preview-size-values");

        if (previewSizeValueString == null) {
            previewSizeValueString = parameters.get("preview-size-value");
        }

        if (previewSizeValueString == null) { // 有些手机例如m9获取不到支持的预览大小 就直接返回屏幕大小
            DisplayMetrics metrics = getResources().getDisplayMetrics();

            return camera.new Size(metrics.widthPixels, metrics.heightPixels);
        }
        float bestX = 0;
        float bestY = 0;

        float tmpRadio = 0;
        float viewRadio = 0;

//            if (surfaceView.getWidth() != 0 && surfaceView.getHeight() != 0) {
//                viewRadio = Math.min((float) surfaceView.getWidth(),
//                        (float) surfaceView.getHeight())
//                        / Math.max((float) surfaceView.getWidth(),
//                        (float) surfaceView.getHeight());
//            }

        String[] COMMA_PATTERN = previewSizeValueString.split(",");
        for (String prewsizeString : COMMA_PATTERN) {
            prewsizeString = prewsizeString.trim();
            int dimPosition = prewsizeString.indexOf('x');
            if (dimPosition == -1) {
                continue;
            }
            float newX = 0;
            float newY = 0;
            try {
                newX = Float.parseFloat(prewsizeString
                        .substring(0, dimPosition));
                newY = Float.parseFloat(prewsizeString
                        .substring(dimPosition + 1));
            } catch (NumberFormatException e) {
                continue;
            }
            float radio = Math.min(newX, newY) / Math.max(newX, newY);
            if (tmpRadio == 0) {
                tmpRadio = radio;
                bestX = newX;
                bestY = newY;
            } else if (tmpRadio != 0
                    && (Math.abs(radio - viewRadio)) < (Math.abs(tmpRadio
                    - viewRadio))) {
                tmpRadio = radio;
                bestX = newX;
                bestY = newY;
            }
        }
        if (bestX > 0 && bestY > 0) {
            return camera.new Size((int) bestX, (int) bestY);
        }
        return null;
    }


    @Override
    protected void onDestroy() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
        super.onDestroy();
    }
}
