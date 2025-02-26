import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'dicom_viewer_platform_interface.dart';

class MethodChannelDicomViewer extends DicomViewerPlatform {
  @visibleForTesting
  final methodChannel = const MethodChannel('ed_dicom_viewer');

  @override
  Future<String> getViewDicom(String filePath) async {
    try {
      return await methodChannel
          .invokeMethod('getViewDicom', {'filePath': filePath});
    } on PlatformException catch (e) {
      throw "Failed to view DICOM: '${e.message}'.";
    }
  }
}
