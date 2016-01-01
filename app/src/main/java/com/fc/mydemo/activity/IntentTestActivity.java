package com.fc.mydemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fc.mydemo.R;
import com.fc.mydemo.utils.ToastUtil;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 主要表现一些系统自带的Intent。
 */
@EActivity(R.layout.activity_intent_test)
public class IntentTestActivity extends BaseActivity {

    @ViewById(R.id.phone_number)
    protected EditText phoneNumber;

    @ViewById
    protected Button call;
    @ViewById
    protected Button file_choose;
    @ViewById
    protected TextView file_name;
    @ViewById
    protected Button open_camera;
    @ViewById
    protected Button open_album;

    @Click({R.id.call, R.id.file_choose,R.id.open_camera,R.id.open_album})
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.call:
                //拨打电话需要相应的权限
                Intent intent = new Intent(Intent.ACTION_DIAL);
                //打电话的两种方式
                //Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + phoneNumber.getText().toString());
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.file_choose:
                //文件选择
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                try {
                    startActivityForResult(Intent.createChooser(intent, "请选择一个文件"), 1000);
                } catch (android.content.ActivityNotFoundException ex) {
                    ToastUtil.showToastShort(this, "请安装文件管理器");
                }
                break;
            case R.id.open_camera:
                //调用系统相机，注意需要添加相机权限
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1001);
                break;
            case R.id.open_album:
                //调用android的图库
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1002);
                break;
        }
    }

    /**
     * 根据返回选择的文件，来进行上传操作
     **/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    // 选择文件
                    Uri uri = data.getData();
                    String url;
                    url = getPath(this, uri);
                    Log.i("ht", "url" + url);
                    String fileName = url.substring(url.lastIndexOf("/") + 1);
                    file_name.setText("选择的文件名是：" + fileName);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getPath(Context context, Uri uri) {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }
}
