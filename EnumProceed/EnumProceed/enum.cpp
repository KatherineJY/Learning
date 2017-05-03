#include <stdio.h>
#include <windows.h>
#include <tlhelp32.h>
#include <tchar.h>

int main(void)
{
	HANDLE hProcessSnap;
	PROCESSENTRY32 pe32;

	hProcessSnap = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, 0);
	if (hProcessSnap == INVALID_HANDLE_VALUE) {
		printf("createToolhelp32Snapshot failed..");
		return(false);
	}

	pe32.dwSize = sizeof(PROCESSENTRY32);
	if (!Process32First(hProcessSnap, &pe32))
	{
		printf("get process failed..");
		CloseHandle(hProcessSnap);
		return(false);
	}

	do {
		printf("\n\n");
		_tprintf(TEXT("\nPROCESS NAME:  %s"), pe32.szExeFile);
		_tprintf(TEXT("\n  Process ID = 0x%08X"), pe32.th32ProcessID);

	} while (Process32Next(hProcessSnap, &pe32));
	CloseHandle(hProcessSnap);
	system("pause");
	return 0;
}
