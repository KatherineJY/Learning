#include<stdio.h>
#include<string.h>
#include<Windows.h>
#include<SetupAPI.h>
#include<iostream>
using namespace std;
#pragma comment(lib, "SetupAPI.lib")

DWORD properties[30] = { SPDRP_ADDRESS,SPDRP_BUSNUMBER,SPDRP_BUSTYPEGUID,SPDRP_CAPABILITIES,SPDRP_CHARACTERISTICS,
SPDRP_CLASS,SPDRP_CLASSGUID,SPDRP_COMPATIBLEIDS,SPDRP_CONFIGFLAGS,SPDRP_DEVICEDESC,SPDRP_DEVTYPE,
SPDRP_DRIVER,SPDRP_ENUMERATOR_NAME,SPDRP_EXCLUSIVE,SPDRP_FRIENDLYNAME,SPDRP_HARDWAREID,
SPDRP_LEGACYBUSTYPE,SPDRP_LOCATION_INFORMATION,SPDRP_LOWERFILTERS,SPDRP_MFG,SPDRP_PHYSICAL_DEVICE_OBJECT_NAME,
SPDRP_SECURITY,SPDRP_UI_NUMBER,SPDRP_UI_NUMBER_DESC_FORMAT,SPDRP_UPPERFILTERS };

char propertiesName[30][35] = { "SPDRP_ADDRESS","SPDRP_BUSNUMBER","SPDRP_BUSTYPEGUID","SPDRP_CAPABILITIES",
								"SPDRP_CHARACTERISTICS","SPDRP_CLASS","SPDRP_CLASSGUID","SPDRP_COMPATIBLEIDS",
								"SPDRP_CONFIGFLAGS","SPDRP_DEVICEDESC","SPDRP_DEVTYPE","SPDRP_DRIVER","SPDRP_ENUMERATOR_NAME",
								"SPDRP_EXCLUSIVE","SPDRP_FRIENDLYNAME","SPDRP_HARDWAREID","SPDRP_LEGACYBUSTYPE",
								"SPDRP_LOCATION_INFORMATION","SPDRP_LOWERFILTERS","SPDRP_MFG","SPDRP_PHYSICAL_DEVICE_OBJECT_NAME",
								"SPDRP_SECURITY","SPDRP_UI_NUMBER","SPDRP_UI_NUMBER_DESC_FORMAT","SPDRP_UPPERFILTERS" };

int main()
{
	FILE *fp;
	freopen_s( &fp,"device_information.txt","w",stderr);
	HDEVINFO hDevInfo;
	SP_DEVINFO_DATA DeviceInfoData;
	DWORD i;

	hDevInfo = SetupDiGetClassDevs(NULL, 0, 0, DIGCF_PRESENT | DIGCF_ALLCLASSES);
	if (hDevInfo == INVALID_HANDLE_VALUE)
	{
		fprintf( fp, "fail to get information of devices..");
		fprintf( fp, "This will go to the file 'device_information.txt'\n");
		fclose(fp);
		return 0;
	}

	DeviceInfoData.cbSize = sizeof(SP_DEVINFO_DATA);
	for (i = 0;; i++)
	{
		if (!SetupDiEnumDeviceInfo(hDevInfo, i, &DeviceInfoData))
			break;
		char propertyBuf[MAX_PATH] = { 0 };
		DWORD bufSize;
		int j;
		fprintf(fp, "Device\tNo.%d\n", i);
		for (j = 0; j<25; j++) {
			DWORD current_property = properties[j];
			if (!SetupDiGetDeviceRegistryProperty(hDevInfo, &DeviceInfoData, current_property, NULL, (PBYTE)propertyBuf, MAX_PATH, &bufSize
			))
				continue;
			fprintf(fp, "%s:\t%s\n", propertiesName[j], propertyBuf);
		}
		fprintf(fp, "\n\n");
	}
	SetupDiDestroyDeviceInfoList(hDevInfo);
	fclose(fp);
	return 0;
}

