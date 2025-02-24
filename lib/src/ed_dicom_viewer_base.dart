import 'ed_dicom_viewer_platform_interface.dart';

class DicomViewer {
  Future<String> getViewDicom(String filePath) {
    return EdDicomViewerPlatform.instance.getViewDicom(filePath);
  }
}
