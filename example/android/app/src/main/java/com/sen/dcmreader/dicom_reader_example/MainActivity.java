package com.sen.dcmreader.dicom_reader_example;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sen.dcmreader.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

//import javax.imageio.ImageIO;


public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("imebra_lib");  // Ensure the correct library name
    }

    private EdDicomManager dicomManager;

    private static final int STORAGE_PERMISSION_CODE = 101;
    private ImageView dicomImageView;
    private TextView textViewDicomPatient;
    private Button selectFileButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);
        dicomManager = new EdDicomManager();
        dicomImageView = findViewById(R.id.dicomImageView);
        textViewDicomPatient = findViewById(R.id.textViewDicomPatient);
        selectFileButton = findViewById(R.id.selectFileButton);


        selectFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    File file = readAssetFileAsTempFile(MainActivity.this, "od.dcm");

                    ResponseModel data = null;

//                    data = dicomManager.readDicomFile(read(file));

                    byte[] ba = readAssetFileToByteArray(MainActivity.this, "od.dcm");
                    data = dicomManager.readDicomFile(ba);
                    if (data != null) {
//                        String op = JsonUtil.convertToJson(data);
                        byte[] imageData = data.getDecodedBytes();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                        dicomImageView.setImageBitmap(bitmap);
                        textViewDicomPatient.setText("Name : " + data.getPatientName()
                                + "\n Age : " + data.getPatientAge()
                                + "\n gender : " + data.getpatientGender()
                                + "\n dob : " + data.getPatientBirthDate()
                                + "\n " + data.getPatientHeight());

                        Toast.makeText(MainActivity.this, " op file!", Toast.LENGTH_SHORT).show();

//                                    result.success(JsonUtil.convertToJson(data));
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }


    public void displayByteArrayImage(byte[] imageData, ImageView imageView) {
        if (imageData != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            imageView.setImageBitmap(bitmap);
        }
    }


    public byte[] readAssetFileToByteArray(Context context, String fileName) throws IOException {

        AssetManager assetManager = context.getAssets();

        InputStream inputStream = assetManager.open(fileName);


        byte[] buffer = new byte[inputStream.available()];

        int bytesRead = inputStream.read(buffer, 0, buffer.length);


        inputStream.close();


        return Arrays.copyOf(buffer, bytesRead);

    }


    private byte[] read(File file) throws IOException {
        ByteArrayOutputStream ous = null;
        byte[] buffer = new byte[4096];
        ous = new ByteArrayOutputStream();
        try (InputStream ios = new FileInputStream(file)) {
            int read = 0;
            while ((read = ios.read(buffer)) != -1) {
                ous.write(buffer, 0, read);
            }
            ous.close();
        }
        return ous.toByteArray();
    }

    public File readAssetFileAsTempFile(Context context, String fileName) throws IOException {

        AssetManager assetManager = context.getAssets();

        InputStream inputStream = assetManager.open(fileName);


        File tempFile = File.createTempFile("temp", ".dcm", context.getCacheDir());

        FileOutputStream outputStream = new FileOutputStream(tempFile);


        byte[] buffer = new byte[1024];

        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {

            outputStream.write(buffer, 0, bytesRead);

        }


        inputStream.close();

        outputStream.close();


        return tempFile;

    }





/*    private Bitmap loadDicomImage(String filePath) {
        try {
            File file = new File(filePath);
            DicomImageReader dicomReader = new DicomImageReader(new DicomImageReaderSpi());
            dicomReader.setInput(ImageIO.createImageInputStream(file));
            java.awt.image.BufferedImage image = dicomReader.read(0);

            File tempPng = new File(getCacheDir(), "temp.png");
            ImageIO.write(image, "png", tempPng);

            return BitmapFactory.decodeFile(tempPng.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/
}

