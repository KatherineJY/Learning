#ifndef _MYDLL_H
#define _MYDLL_H
#define MYDLL extern "C" _declspec(dllexport)
MYDLL void myprint();

class MyClass 
{
public:
	void print();
};
#endif
