import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'dicom_reader_platform_interface.dart';

/// An implementation of [DicomReaderPlatform] that uses method channels.
class MethodChannelDicomReader extends DicomReaderPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('dicom_reader');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
