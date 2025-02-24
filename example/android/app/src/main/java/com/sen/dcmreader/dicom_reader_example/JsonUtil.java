package com.sen.dcmreader.dicom_reader_example;

import com.google.gson.Gson;

public class JsonUtil {

    public static String convertToJson(ResponseModel responseModel) {
        Gson gson = new Gson();
        return gson.toJson(responseModel);
    }

}