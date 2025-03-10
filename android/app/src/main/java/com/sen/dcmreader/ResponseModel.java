package com.sen.dcmreader;

public class ResponseModel {
    public ResponseModel(
            byte[] decodedBytes,
            String patientName,
            String patientGender,
            String patientID,
            String patientAge,
            String patientBirthDate,
            String patientWeight,
            String patientHeight
    ) {
        this.decodedBytes = decodedBytes;
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientID = patientID;
        this.patientAge = patientAge;
        this.patientBirthDate = patientBirthDate;
        this.patientWeight = patientWeight;
        this.patientHeight = patientHeight;
    }

    private final byte[] decodedBytes;
    private final String patientName;
    private final String patientGender;
    private final String patientID;
    private final String patientAge;
    private final String patientBirthDate;
    private final String patientWeight;
    private final String patientHeight;

    public String getPatientName() {
        return patientName;
    }

    public byte[] getDecodedBytes() {
        return decodedBytes;
    }

    public String getpatientGender() {
        return patientGender;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public String getPatientBirthDate() {
        return patientBirthDate;
    }

    public String getPatientWeight() {
        return patientWeight;
    }

    public String getPatientHeight() {
        return patientHeight;
    }
}
