import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'dicom_reader_method_channel.dart';

abstract class DicomReaderPlatform extends PlatformInterface {
  /// Constructs a DicomReaderPlatform.
  DicomReaderPlatform() : super(token: _token);

  static final Object _token = Object();

  static DicomReaderPlatform _instance = MethodChannelDicomReader();

  /// The default instance of [DicomReaderPlatform] to use.
  ///
  /// Defaults to [MethodChannelDicomReader].
  static DicomReaderPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [DicomReaderPlatform] when
  /// they register themselves.
  static set instance(DicomReaderPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
