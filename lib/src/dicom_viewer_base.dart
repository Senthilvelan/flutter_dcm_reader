import 'dicom_viewer_platform_interface.dart';

class DicomViewer {
  Future<String> getViewDicom(String filePath) {
    return DicomViewerPlatform.instance.getViewDicom(filePath);
  }
}
