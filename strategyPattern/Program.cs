using System;
using HelloWorld.Ducks;
namespace HelloWorld
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            var p = new Program();

            var duckA = new TypeADuck();
            var duckB = new TypeBDuck();

            duckA.Flutter = p.flutterImpl;
            duckB.Flutter = p.flutterImpl;
            duckA.Flutter.Invoke();
            duckB.Flutter.Invoke();
        }


        void flutterImpl()
        {
            Console.WriteLine("Fair as God's love, same flutter ability for ducks.");
        }
    }
}
