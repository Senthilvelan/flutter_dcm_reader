#include "include/dicom_reader/dicom_reader_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "dicom_reader_plugin.h"

void DicomReaderPluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  dicom_reader::DicomReaderPlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
