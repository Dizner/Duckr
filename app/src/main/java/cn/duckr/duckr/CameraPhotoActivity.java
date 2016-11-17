package cn.duckr.duckr;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraPhotoActivity extends AppCompatActivity {
    private static final int TAKE_PICTURE = 0;
    private static final int CHOOSE_PICTURE = 1;
    private static final int SCALE = 2;

    private ImageView yulan_image = null;
    private static String filename;
    private static Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_photo);

        initView();


    }

    private void initView() {
        yulan_image = (ImageView) findViewById(R.id.yulan_image);

    }

    public void onclick(View v){
        switch (v.getId()){

            case R.id.me_setting_back:
                finish();
                break;
            case R.id.CameraphotoOK:

                break;
            case R.id.forphoto:

                Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                openAlbumIntent.setType("image/*");
                startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);

                break;
            case R.id.forcamera:

                SimpleDateFormat formatephoto = new SimpleDateFormat("yyMMddHHmmss");
                String format = formatephoto.format(new Date());
                filename = format+".jpg";

                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

//------------------------------------------------------------------------------------------------------------
                //图片地址
                Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), filename));
                // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(openCameraIntent, TAKE_PICTURE);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case CHOOSE_PICTURE:
                    ContentResolver resolver = getContentResolver();
                    // 照片的原始资源地址
                    Uri originalUri = data.getData();
                    try {
                        // 使用ContentProvider通过URI获取原始图片
                        Bitmap photo = MediaStore.Images.Media.getBitmap(resolver,
                                originalUri);
                        if (photo != null) {
                            // 为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
                            Bitmap smallBitmap = ImageTools.zoomBitmap(photo,
                                    photo.getWidth() / SCALE, photo.getHeight()
                                            / SCALE);
                            // 释放原始图片占用的内存，防止out of memory异常发生
                            photo.recycle();

                            yulan_image.setImageBitmap(smallBitmap);

                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case TAKE_PICTURE:

                    String onfilename = "/"+filename;

                    System.out.println(onfilename);
                    // 将保存在本地的图片取出并缩小后显示在界面上
                    bitmap = BitmapFactory.decodeFile(Environment
                            .getExternalStorageDirectory() + onfilename);
                    Bitmap newBitmap = ImageTools.zoomBitmap(bitmap,
                            bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);//将图片缩放SCALE倍
                    // 由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
                    bitmap.recycle();

                    // 将处理过的图片显示在界面上，并保存到本地
                    yulan_image.setImageBitmap(newBitmap);
                    ImageTools.savePhotoToSDCard(newBitmap, Environment.getExternalStorageDirectory().getAbsolutePath(),
                            String.valueOf(System.currentTimeMillis()));

                    saveBitmap();


                    break;

                default:
                    break;
            }
        }
    }

    public void saveBitmap() {
        File f = new File("/sdcard/namecard/", "/"+filename);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
