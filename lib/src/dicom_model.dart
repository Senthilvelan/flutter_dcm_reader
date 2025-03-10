class DicomModel {
  final List<int> decodedBytes;
  final String patientName;
  final String patientGender;
  final String patientID;
  final String patientAge;
  final String patientBirthDate;
  final String patientWeight;
  final String patientHeight;

  DicomModel({
    required this.decodedBytes,
    required this.patientName,
    required this.patientGender,
    required this.patientID,
    required this.patientAge,
    required this.patientBirthDate,
    required this.patientWeight,
    required this.patientHeight,
  });

  factory DicomModel.fromJson(Map<String, dynamic> json) {
    return DicomModel(
      decodedBytes: json['decodedBytes'].cast<int>(),
      patientName: json['patientName'],
      patientGender: json['patientGender'],
      patientID: json['patientID'],
      patientAge: json['patientAge'],
      patientBirthDate: json['patientBirthDate'],
      patientWeight: json['patientWeight'],
      patientHeight: json['patientHeight'],
    );
  }

  @override
  String toString() {
    return 'DicomModel{patientName: $patientName, patientGender: $patientGender, patientID: $patientID, patientAge: $patientAge, patientBirthDate: $patientBirthDate, patientWeight: $patientWeight, patientHeight: $patientHeight}';
  }
}