using System;

namespace HelloWorld.Ducks
{
    public class TypeADuck : IFlutter
    {
        private Action _flutter;

        public Action Flutter { get => _flutter; set => _flutter = value; }
    }


}