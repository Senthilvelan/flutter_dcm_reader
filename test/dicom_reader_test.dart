import 'package:flutter_test/flutter_test.dart';
import 'package:dicom_reader/dicom_reader.dart';
import 'package:dicom_reader/dicom_reader_platform_interface.dart';
import 'package:dicom_reader/dicom_reader_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockDicomReaderPlatform
    with MockPlatformInterfaceMixin
    implements DicomReaderPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final DicomReaderPlatform initialPlatform = DicomReaderPlatform.instance;

  test('$MethodChannelDicomReader is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelDicomReader>());
  });

  test('getPlatformVersion', () async {
    DicomReader dicomReaderPlugin = DicomReader();
    MockDicomReaderPlatform fakePlatform = MockDicomReaderPlatform();
    DicomReaderPlatform.instance = fakePlatform;

    expect(await dicomReaderPlugin.getPlatformVersion(), '42');
  });
}
