/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingsystem;

import java.util.Comparator;

/**
 *
 * @author Krish
 */
class MyComparator  implements Comparator<String>
    {
        Bank B;

        MyComparator(Bank B) 
        {
            this.B=B;
        }

        @Override
        public int compare(String o1, String o2) 
        {
            String q1=o1.substring(13,o1.indexOf("TO")-1);
            String q2=o2.substring(13,o2.indexOf("TO")-1);
            User ra1=null,ra2=null;
            for(User u:B.accounts)
            {
               if(q1.equals(u.upiId))
               {
                   ra1=u;
                   break;
               }
            }

            for(User u:B.accounts)
            {
               if(q2.equals(u.upiId))
               {
                   ra2=u;
                   break;
               }
            }
            
            if(ra1.balance < ra2.balance)
            {
                return 1;
            }
            
            else if(ra1.balance > ra2.balance)
            {
                return -1;
            }
            
            return 0;
        }
    }
