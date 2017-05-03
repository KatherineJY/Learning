#include "stdafx.h"
#include <stdio.h>
#include <conio.h>
#include <windows.h>
#include <tchar.h>

void writeInPipe() {
	DWORD wlen = 0;
	Sleep(1000);

	BOOL bRet = WaitNamedPipe(TEXT("\\\\.\\Pipe\\mypipe"), NMPWAIT_WAIT_FOREVER);
	if (!bRet) {
		printf("connect the namePipe failed!\n");
		return;
	}

	HANDLE hPipe = CreateFile(            
		TEXT("\\\\.\\Pipe\\mypipe"),    
		GENERIC_READ | GENERIC_WRITE, 
		0,                              
		NULL,                          
		OPEN_EXISTING,                  
		FILE_ATTRIBUTE_NORMAL,         
		NULL);                          

	if (INVALID_HANDLE_VALUE == hPipe)
	{
		printf("open the exit pipe failed!\n");
		return;
	}

	char buf[256] = "try to write something in pipe";
	if (WriteFile(hPipe, buf, sizeof(buf), &wlen, 0) == FALSE) {
		printf("write to pipe failed!\n");
		CloseHandle(hPipe);
		return;
	}
	printf("To Server: data = %s, size = %d\n", buf, wlen);
	char rbuf[256] = "";
	DWORD rlen = 0;
	ReadFile(hPipe, rbuf, sizeof(rbuf), &rlen, 0);
	printf("From Server: data = %s, size = %d\n", rbuf, rlen);
	CloseHandle(hPipe);
}

void writeInFile() {
	int BUF_SIZE=256;
	TCHAR szName[] = TEXT("MyFileMappingObject1");
	TCHAR szMsg[] = TEXT("Message from first process.");


		HANDLE hMapFile;
		LPCTSTR pBuf;

		hMapFile = CreateFileMapping(
			INVALID_HANDLE_VALUE,    // use paging file
			NULL,                    // default security
			PAGE_READWRITE,          // read/write access
			0,                       // maximum object size (high-order DWORD)
			BUF_SIZE,                // maximum object size (low-order DWORD)
			szName);                 // name of mapping object

		if (hMapFile == NULL)
		{
			_tprintf(TEXT("Could not create file mapping object (%d).\n"),
				GetLastError());
			return ;
		}
		pBuf = (LPTSTR)MapViewOfFile(hMapFile,   // handle to map object
			FILE_MAP_ALL_ACCESS, // read/write permission
			0,
			0,
			BUF_SIZE);

		if (pBuf == NULL)
		{
			_tprintf(TEXT("Could not map view of file (%d).\n"),
				GetLastError());

			CloseHandle(hMapFile);

			return;
		}

		_tprintf(TEXT("To Server: data = %s"),szMsg);
		CopyMemory((PVOID)pBuf, szMsg, (_tcslen(szMsg) * sizeof(TCHAR)));
		_getch();
		
		UnmapViewOfFile(pBuf);

		CloseHandle(hMapFile);
}

int main()
{
	printf("========================================================\n");
	printf("Test transimit data by pipe:\n");
	writeInPipe();
	printf("========================================================\n");
	printf("Test transimit data by flie:\n");	
	writeInFile();
	system("pause");

	return 0;
}

