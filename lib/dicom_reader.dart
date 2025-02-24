
import 'dicom_reader_platform_interface.dart';

class DicomReader {
  Future<String?> getPlatformVersion() {
    return DicomReaderPlatform.instance.getPlatformVersion();
  }
}
