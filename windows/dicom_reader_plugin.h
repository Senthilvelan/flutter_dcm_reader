#ifndef FLUTTER_PLUGIN_DICOM_READER_PLUGIN_H_
#define FLUTTER_PLUGIN_DICOM_READER_PLUGIN_H_

#include <flutter/method_channel.h>
#include <flutter/plugin_registrar_windows.h>

#include <memory>

namespace dicom_reader {

class DicomReaderPlugin : public flutter::Plugin {
 public:
  static void RegisterWithRegistrar(flutter::PluginRegistrarWindows *registrar);

  DicomReaderPlugin();

  virtual ~DicomReaderPlugin();

  // Disallow copy and assign.
  DicomReaderPlugin(const DicomReaderPlugin&) = delete;
  DicomReaderPlugin& operator=(const DicomReaderPlugin&) = delete;

  // Called when a method is called on this plugin's channel from Dart.
  void HandleMethodCall(
      const flutter::MethodCall<flutter::EncodableValue> &method_call,
      std::unique_ptr<flutter::MethodResult<flutter::EncodableValue>> result);
};

}  // namespace dicom_reader

#endif  // FLUTTER_PLUGIN_DICOM_READER_PLUGIN_H_
