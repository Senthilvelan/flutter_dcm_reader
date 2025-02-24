//package com.sen.dcmreader.dicom_reader_example;
//
//
//import android.Manifest;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.res.AssetManager;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.OpenableColumns;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
////import javax.imageio.ImageIO;
//
//
//public class MainActivity1 extends AppCompatActivity {
//    private EdDicomManager dicomManager;
//
//    private static final int STORAGE_PERMISSION_CODE = 101;
//    private ImageView dicomImageView;
//    private Button selectFileButton;
//    private final ActivityResultLauncher<Intent> filePickerLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                    Uri uri = result.getData().getData();
//                    if (uri != null) {
//                        String filePath = getFileFromUri(uri);
//                        if (filePath != null) {
//                            File file = new File(filePath);
//                            ResponseModel data = null;
//                            try {
//                                data = dicomManager.readDicomFile(read(file));
//                                if (data != null) {
//                                    String op = JsonUtil.convertToJson(data);
//                                    Toast.makeText(this, op + " op file!", Toast.LENGTH_SHORT).show();
//
////                                    result.success(JsonUtil.convertToJson(data));
//                                }
//                            } catch (IOException e) {
//                                e.printStackTrace();
////                                result.error("INVALID_FILE", "Invalid DICOM file", null);
//                            }
//
//
////                            Bitmap dicomBitmap = loadDicomImage(filePath);
////                            dicomImageView.setImageBitmap(dicomBitmap);
//                        } else {
//                            Toast.makeText(this, "Error accessing file!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//            }
//    );
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.screen);
//        dicomManager = new EdDicomManager();
//        dicomImageView = findViewById(R.id.dicomImageView);
//        selectFileButton = findViewById(R.id.selectFileButton);
//
//        // Check and request permissions
//        if (!hasStoragePermission()) {
//            requestStoragePermission();
//        }
//
//        // File picker button
//        selectFileButton.setOnClickListener(v -> openFilePicker());
//    }
//
//    private byte[] read(File file) throws IOException {
//        ByteArrayOutputStream ous = null;
//        byte[] buffer = new byte[4096];
//        ous = new ByteArrayOutputStream();
//        try (InputStream ios = new FileInputStream(file)) {
//            int read = 0;
//            while ((read = ios.read(buffer)) != -1) {
//                ous.write(buffer, 0, read);
//            }
//            ous.close();
//        }
//        return ous.toByteArray();
//    }
//
//
//    private boolean isDICOM(File file) {
//        if (file.length() >= 132) {
//            try (InputStream in = new FileInputStream(file)) {
//                byte[] b = new byte[128 + 4];
//                in.read(b, 0, 132);
//                for (int i = 0; i < 128; i++)
//                    if (b[i] != 0)
//                        return false;
//                if (b[128] != 68 || b[129] != 73 || b[130] != 67
//                        || b[131] != 77)
//                    return false;
//            } catch (IOException e) {
//                return false;
//            }
//            return true;
//        }
//        return false;
//    }
//
//    private boolean hasStoragePermission() {
//        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private void requestStoragePermission() {
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
//    }
//
//    AssetManager assetManager = getAssets();
//
//    private void openFilePicker() {
//        if (!hasStoragePermission()) {
//            requestStoragePermission();
//            return;
//        }
//
//
//        try {
//            File file = readAssetFileAsTempFile(MainActivity1.this, "od.dcm");
//            ResponseModel data = null;
//
//            data = dicomManager.readDicomFile(read(file));
//            if (data != null) {
//                String op = JsonUtil.convertToJson(data);
//                Toast.makeText(this, op + " op file!", Toast.LENGTH_SHORT).show();
//
////                                    result.success(JsonUtil.convertToJson(data));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
////                                result.error("INVALID_FILE", "Invalid DICOM file", null);
//        }
//
//
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("*/*");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        filePickerLauncher.launch(Intent.createChooser(intent, "Select DICOM File"));
//    }
//
//
//    public File readAssetFileAsTempFile(Context context, String fileName) throws IOException {
//
//        AssetManager assetManager = context.getAssets();
//
//        InputStream inputStream = assetManager.open(fileName);
//
//
//        File tempFile = File.createTempFile("temp", ".txt", context.getCacheDir());
//
//        FileOutputStream outputStream = new FileOutputStream(tempFile);
//
//
//        byte[] buffer = new byte[1024];
//
//        int bytesRead;
//
//        while ((bytesRead = inputStream.read(buffer)) != -1) {
//
//            outputStream.write(buffer, 0, bytesRead);
//
//        }
//
//
//        inputStream.close();
//
//        outputStream.close();
//
//
//        return tempFile;
//
//    }
//
//
//    @Nullable
//    private String getFileFromUri(Uri uri) {
//        try {
//            InputStream inputStream = getContentResolver().openInputStream(uri);
//            File tempFile = new File(getCacheDir(), getFileName(uri));
//            FileOutputStream outputStream = new FileOutputStream(tempFile);
//
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//
//            inputStream.close();
//            outputStream.close();
//            return tempFile.getAbsolutePath();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private String getFileName(Uri uri) {
//        String result = null;
//        if (uri.getScheme().equals("content")) {
//            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
//                if (cursor != null && cursor.moveToFirst()) {
//                    result = cursor.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME));
//                }
//            }
//        }
//        return result != null ? result : "temp.dcm";
//    }
//
///*    private Bitmap loadDicomImage(String filePath) {
//        try {
//            File file = new File(filePath);
//            DicomImageReader dicomReader = new DicomImageReader(new DicomImageReaderSpi());
//            dicomReader.setInput(ImageIO.createImageInputStream(file));
//            java.awt.image.BufferedImage image = dicomReader.read(0);
//
//            File tempPng = new File(getCacheDir(), "temp.png");
//            ImageIO.write(image, "png", tempPng);
//
//            return BitmapFactory.decodeFile(tempPng.getAbsolutePath());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }*/
//}
//
