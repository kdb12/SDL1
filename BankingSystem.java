/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingsystem;

import java.util.*;

/**
 *
 * @author Krish
 */
public class BankingSystem
{

    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args)  
    {
        System.out.println("***********WELCOME TO BANKING SYSTEM MANAGEMENT***********");
        Vector<Bank> India=new Vector<>(10);
        HashMap<Integer,String> IP=new HashMap<>(10);
        HashMap<Integer,String> IN=new HashMap<>(10);
        
        Scanner sc=new Scanner(System.in);
        int choice;
        boolean end=false;
        
        while(!end)
        {
            System.out.println("");
            System.out.println("1.ADD BANK");
            System.out.println("2.ADMIN LOGIN");
            System.out.println("3.USER LOGIN");
            System.out.println("4.EXIT");
            System.out.print("ENTER YOUR CHOICE: ");
            
            
            choice=sc.nextInt();
            sc.nextLine();
            System.out.println("");
            
            switch(choice)
            {
                case 1:
                {
                    String N,A,P;
                    int i;
                    System.out.print("ENTER NAME: ");
                    N=sc.nextLine();
                    System.out.print("ENTER ADDRESS: ");
                    A=sc.nextLine();
                    System.out.print("ENTER PASSWORD: ");
                    P=sc.nextLine();
                    System.out.print("ENTER ID: ");
                    i=sc.nextInt();
                    if(IN.containsKey(i))
                    {
                        System.out.println("DUPLICATE BANK ID");
                    }
                    else if(IN.containsValue(N))
                    {
                        System.out.println("DUPLICATE BANK NAME");
                    }
                    else
                    {
                        Bank B=new Bank(N,A,P,i);
                        India.add(B);
                        B.setComparator();
                        IP.put(i,P);
                        IN.put(i,N);
                    }
                    System.out.println("");
                }
                break;
                
                case 2:
                {
                    System.out.println("");
                    int bid;
                    Bank Bk = null;
                    String bpass;
                    System.out.print("ENTER BANK ID: ");
                    bid=sc.nextInt();
                    sc.nextLine();
                    System.out.print("ENTER PASSWORD: ");
                    
                    bpass=sc.nextLine();
                    
                    if(IP.containsKey(bid)&&IP.containsValue(bpass))
                    {
                        if(!(IP.get(bid).equals(bpass)))
                        {
                            System.out.println("INCOREECT LOGIN CREDENTIALS");
                            break;
                        }
                        System.out.println("");
                        System.out.println("LOGIN SUCEESFULL");
                        boolean termi=false;
                        int choice2;
                        boolean kr=true;
                        for(int i=0;i<India.size()&&kr;i++)
                        {
                            Bank B=India.get(i);
                            if(B.id==bid)
                            {
                                kr=false;
                                Bk=B;
                            }
                        }
                        while(!termi)
                        {
                            System.out.println("");
                            System.out.println("1.CREATE USERS");
                            System.out.println("2.SHOW ACCOUNT HOLDERS");
                            System.out.println("3.TRANSACTIONS");
                            System.out.println("4.DELETE ACCOUNT");
                            System.out.println("5.REQUESTS");
                            System.out.println("6.SEARCH USER");
                            System.out.println("7.LOG OUT");
                            System.out.print("ENTER YOUR CHOICE: ");
                            choice2=sc.nextInt();
                            sc.nextLine();
                            
                            switch(choice2)
                            {
                                
                                case 1:
                                {
                                    User U=new User();
                                    U.input(Bk);
                                    Bk.accounts.add(U);
                                    Bk.upiHolders.put(U.upiId, U.password);
                                }
                                break;
                                
                                case 2:
                                {
                                    System.out.println("");
                                    System.out.println("ACCOUNT HOLDERS IN THE BANK  "+IN.get(bid)+" ARE AS FOLLOWS:\n");                                  
                                    String format = "|%1$-15s|%2$-10s|%3$-10s|%4$-15s|%5$-15s|%6$-10s|%7$-10s|\n";
                                    System.out.format(format,"NAME","UPI_ID","PASSWORD","MOBILE_NUMBER","ACCOUNT_NUMBER","BALANCE","BANK_NAME");
                                    for(int i=0;i<Bk.accounts.size();i++)
                                    {
                                       User K=Bk.accounts.get(i);
                                       K.showAccountHolders();
                                    }
                                    System.out.println("");
                                    
                                }
                                break;
                                
                                case 3:
                                {
                                    System.out.println("");
                                    Bk.showTrans();
                                }
                                break;
                                
                                case 4:
                                {
                                    System.out.println("");
                                    String k;
                                    System.out.println("Enter User UPIID:");
                                    k=sc.nextLine();
                                    for(int i=0;i<Bk.accounts.size();i++)
                                    {
                                       User K=Bk.accounts.get(i);
                                       if(k.equals(K.upiId))
                                       {
                                           Bk.accounts.remove(i);
                                           K=null;
                                       }
                                    }                                   
                                }
                                break;
                                case 5:
                                {
                                    System.out.println("");
                                    Bk.showRequest();
                                    break;
                                }
                                
                                case 6:
                                {
                                    System.out.println("");
                                    Bk.searchUser();
                                    break;
                                }
                                
                                case 7:
                                {
                                    System.out.println("");
                                    termi = true;
                                }
                                break;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("INCOREECT LOGIN CREDENTIALS");
                    }
                }
                break;
                
                case 3:
                {
                    System.out.println("");
                    System.out.println("");
                    String bName;
                    System.out.println("Enter Bank Name:");
                    bName=sc.nextLine();
                    if(!(IN.containsValue(bName)))
                    {
                        System.out.println("BANK NAME IS WRONG");
                    }
                    else
                    {
                        Bank Bk = null;
                        boolean kr=true;
                        for(int i=0;i<India.size()&&kr;i++)
                        {
                            Bank B=India.get(i);
                            if(B.Name.equals(bName))
                            {
                                kr=false;
                                Bk=B;
                            }
                        }                       
                        String Id,Pass;
                        User U=null;
                        System.out.println("ENTER UPI ID:");
                        Id=sc.nextLine();
                        System.out.println("ENTER PASSWORD");
                        Pass=sc.nextLine();
                        if(Bk.upiHolders.containsKey(Id)&& Bk.upiHolders.containsValue(Pass))
                        {
                            if(Bk.upiHolders.get(Id).equals(Pass))
                            {
                                System.out.println("");
                                System.out.println("LOGIN SUCCESSFULL");
                                boolean lend=false;
                                for(int i=0;i<Bk.accounts.size()&&!lend;i++)
                                {
                                    if(Bk.accounts.get(i).upiId.equals(Id))
                                    {
                                        lend=true;
                                        U=Bk.accounts.get(i);
                                    }
                                }
                                boolean wend = false;
                                while(!wend)
                                {
                                    System.out.println("");
                                    int choice3;
                                    System.out.println("1.SEND MONEY");
                                    System.out.println("2.ADD MONEY");
                                    System.out.println("3.UPDATE INFORMATION");
                                    System.out.println("4.SHOW TRANSACTIONS");
                                    System.out.println("5.ACCOUNT DETAILS");
                                    System.out.println("6.WITHDRAW MONEY");
                                    System.out.println("7.NOTIFICATIONS");
                                    System.out.println("8.LOG OUT");
                                    System.out.print("ENTER YOUR CHOICE: ");
                                    choice3=sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("");
                                    switch(choice3)
                                    {
                                        case 1:
                                        {
                                            System.out.println("");
                                            String BN;
                                            User P=null;
                                            Bank O=null;
                                            boolean fend=false;
                                            System.out.print("ENTER RECEIVERS BANK NAME: ");
                                            BN=sc.nextLine();
                                            if(IN.containsValue(BN))
                                            {
                                                for(int i=0;i<India.size()&&!fend;i++)
                                                {
                                                    if(India.get(i).Name.equals(BN))
                                                    {
                                                        O=India.get(i);
                                                        fend = true;
                                                    }
                                                }
                                                System.out.print("ENTER RECEIVERS ACCOUNT NUMBER: ");
                                                String tAccno;
                                                tAccno=sc.nextLine();
                                                for(int i=0;i<O.accounts.size()&&fend;i++)
                                                {
                                                    if(O.accounts.get(i).accNo.equals(tAccno))
                                                    {
                                                        P=O.accounts.get(i);
                                                        fend=false;
                                                    }
                                                }
                                                if(fend)
                                                {
                                                    System.out.println("WRONG INFORMATION");
                                                }
                                                else
                                                {                                                    
                                                    U.send(P,O,Bk);
                                                }
                                                System.out.println("");
                                            }
                                            else
                                            {
                                                System.out.println("BANK NAME IS NOT AVAILABLE");
                                            }
                                            System.out.println("");
                                        }
                                        break;

                                        case 2:
                                        {
                                            U.deposit(Bk);
                                            System.out.println("");
                                        }
                                        break;
                                        
                                        case 3:
                                        {
                                            int ch=0;
                                            while(ch!=4)
                                            {
                                                System.out.println("");
                                                System.out.println("1.TO CHANGE CONTACT NO:");
                                                System.out.println("2.TO CHANGE NAME");
                                                System.out.println("3.TO CHANGE BOTH");
                                                System.out.println("4.BACK");
                                                System.out.print("ENTER YOUR CHOICE: ");
                                                ch=sc.nextInt();
                                                sc.nextLine();
                                                switch(ch)
                                                {
                                                    
                                                    case 1:
                                                    {
                                                        Double New;
                                                        System.out.print("ENTER UPDATED MOBILE NUMBER: ");
                                                        New=sc.nextDouble();
                                                        System.out.println("A REQUEST HAS BEEN SENT ON ADMIN SIDE");
                                                        Bk.addRequest("FROM UPI ID= "+U.upiId+" TO UPDATE MOBILE NUMBER TO "+New);
                                                        ch=4;
                                                    }
                                                    break;
                                                    
                                                    case 2:
                                                    {
                                                        String New;
                                                        System.out.print("ENTER Name: ");
                                                        New=sc.nextLine();
                                                        System.out.println("A REQUEST HAS BEEN SENT ON ADMIN SIDE");
                                                        Bk.addRequest("FROM UPI ID= "+U.upiId+" TO UPDATE NAME TO "+New);
                                                        ch=4;
                                                    }
                                                    break;
                                                    
                                                    case 3:
                                                    {
                                                        Double New;
                                                        System.out.print("ENTER UPDATED MOBILE NUMBER: ");
                                                        New=sc.nextDouble();                                                     
                                                        sc.nextLine();
                                                        String New2;
                                                        System.out.print("ENTER Name: ");
                                                        New2=sc.nextLine();                                                      
                                                        System.out.println("A REQUEST HAS BEEN SENT ON ADMIN SIDE");
                                                        Bk.addRequest("FROM UPI ID= "+U.upiId+" TO UPDATE NAME AND MOBILE NUMBER TO "+New2+" AND "+New);
                                                        ch=4;
                                                    }
                                                    break;
                                                    
                                                    case 4:
                                                    {
                                                        ch=4;
                                                    }
                                                    break;
                                                }
                                                System.out.println("");
                                            }
                                            
                                        }
                                        break;
                                        
                                        case 4:
                                        {
                                            System.out.println("");
                                            U.showTransactions();
                                            System.out.println("");
                                        }
                                        break;
                                        
                                        case 5:
                                        {
                                            System.out.println("");
                                            U.showAccountHolders(); 
                                            System.out.println("");
                                        }
                                        break;
                                        
                                        case 6:
                                        {
                                            System.out.println("");
                                            U.withdraw(Bk);                                        
                                        }
                                        break;
                                        
                                        case 7:
                                        {
                                            System.out.println("");
                                            U.showNotifications();
                                            break;
                                        }
                                        
                                        case 8:
                                        {
                                            System.out.println("");
                                            wend=true;
                                            break;
                                        }
                                    }
                                }
                                
                            }
                            else
                            {
                                
                                System.out.println("INCOREECT LOGIN CREDENTIALS");
                            }
                        }
                        else
                        {
                            System.out.println("INCOREECT LOGIN CREDENTIALS");
                        }

                    }
                }
                break;
                
                case 4:
                {
                    end=true;
                    break;
                    
                }
            }
        }
        System.out.println("THANK YOU FOR USING BANKING SYSTEM");
    }

    
    
}
