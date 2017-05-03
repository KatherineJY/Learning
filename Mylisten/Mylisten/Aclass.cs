using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Mylisten
{
    class Aclass
    {
        public delegate void MyEventHandler(object sender,EventArgs e);
        public event MyEventHandler AEvent;

        public Aclass()
        {
            AEvent += new MyEventHandler(new Bclass().doit);
            AEvent += new MyEventHandler(new Cclass().doit);
        }
        
        public void workout()
        {
            Console.WriteLine("A had happened");
            if ( AEvent != null)
            {
                AEvent(this, new EventArgs());
            }
        }
     
    }
}
