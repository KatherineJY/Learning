// ProcessCommunicate.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include <stdio.h>
#include <windows.h>
#include <tchar.h>
#include <errno.h> 
#include <string.h>

void readFromPipe() {
	char buf[256] = "";
	DWORD rlen = 0;
	HANDLE hPipe = CreateNamedPipe(
		TEXT("\\\\.\\Pipe\\mypipe"),                        //管道名  
		PIPE_ACCESS_DUPLEX,                                 //管道类型   
		PIPE_TYPE_MESSAGE | PIPE_READMODE_MESSAGE | PIPE_WAIT,  //管道参数  
		PIPE_UNLIMITED_INSTANCES,                           //管道能创建的最大实例数量  
		0,                                                  //输出缓冲区长度 0表示默认  
		0,                                                  //输入缓冲区长度 0表示默认  
		NMPWAIT_WAIT_FOREVER,                               //超时时间  
		NULL);                                                  //指定一个SECURITY_ATTRIBUTES结构,或者传递零值. 
	
	if (INVALID_HANDLE_VALUE == hPipe) {
		printf("Create Pipe Error\n");
		return;
	}
	
	printf("Waing for connection...\n");
	if (ConnectNamedPipe(hPipe, NULL) == NULL) {
		printf("Connection faile\n");
		CloseHandle(hPipe);
		return;
	}
	printf("Connection Success!\n");
	
	if (ReadFile(hPipe, buf, 256, &rlen, NULL) == FALSE) {
		printf("Read Data From Pipe Failed!\n");
		CloseHandle(hPipe);
		return;
	}

	printf("From Client: data = %s, size = %d\n", buf, rlen);
	char wbuf[256] = "has received data form client";
	DWORD wlen = 0;
	WriteFile(hPipe, wbuf, sizeof(wbuf), &wlen, 0); 
	printf("To Client: data = %s, size = %d\n", wbuf, wlen);
	Sleep(1000);
	CloseHandle(hPipe);
}

void readFromFile() {
	int BUF_SIZE = 256;
	TCHAR szName[] = TEXT("MyFileMappingObject1");


		HANDLE hMapFile;
		LPCTSTR pBuf;

		hMapFile = OpenFileMapping(
			FILE_MAP_ALL_ACCESS,   // read/write access
			FALSE,                 // do not inherit the name
			szName);               // name of mapping object

		if (hMapFile == NULL)
		{
			_tprintf(TEXT("Could not open file mapping object (%d).\n"),
				GetLastError());
			return;
		}

		pBuf = (LPTSTR)MapViewOfFile(hMapFile, // handle to map object
			FILE_MAP_ALL_ACCESS,  // read/write permission
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
		_tprintf(TEXT("From Client: data = %s, size = %d\n"), pBuf, sizeof(pBuf));
		//MessageBox(NULL, pBuf, TEXT("Process2"), MB_OK);
		UnmapViewOfFile(pBuf);

		CloseHandle(hMapFile);
}

int main()
{
	printf("========================================================\n");
	printf("Test transimit data by pipe:\n");
	readFromPipe();
	printf("========================================================\n");
	printf("Test transimit data by flie:\n");
	readFromFile();

	system("pause");
    return 0;
}

