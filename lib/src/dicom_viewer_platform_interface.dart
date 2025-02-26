import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'dicom_viewer_method_channel.dart';

abstract class DicomViewerPlatform extends PlatformInterface {
  DicomViewerPlatform() : super(token: _token);

  static final Object _token = Object();

  static DicomViewerPlatform _instance = MethodChannelDicomViewer();

  static DicomViewerPlatform get instance => _instance;

  static set instance(DicomViewerPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String> getViewDicom(String filePath) {
    throw UnimplementedError('getViewDicom() has not been implemented.');
  }
}
