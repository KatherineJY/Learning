using System;

namespace HelloWorld.Ducks
{
    public class TypeBDuck : IFlutter
    {
        private Action _flutter;

        public Action Flutter { get => _flutter; set => _flutter = value; }
    }
}