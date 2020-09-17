/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingsystem;

import java.util.*;
import java.text.*;

/**
 *
 * @author Krish
 */

interface UserInterface
{
    String C="$  HAS BEEN CREDITED TO YOUR ACCOUNT ON ";
    String D="$  HAS BEEN DEBITED FROM YOUR ACCOUNT ON ";
    final double tCharges = 0.04;
    void deposit(Bank b);
    void send(User e,Bank b,Bank s);
    void withdraw(Bank b);
}

public class User implements UserInterface 
{
    double phoneNo;
    String upiId,name,password,bankName,accNo;
    double balance;
    LinkedList<String> transactions = new LinkedList<>();
    LinkedList<String> notifications =new LinkedList<>();
    
    void input(Bank b)
    {
        System.out.println("");
        Scanner sc=new Scanner(System.in);
        System.out.print("ENTER NAME: ");
        name=sc.nextLine();
        bankName=b.Name;
        System.out.print("ENTER UPIID: ");
        upiId=sc.nextLine();
        System.out.print("ENTER PASSWORD: ");
        password=sc.nextLine();
        System.out.print("ENTER CONTACT NUMBER: ");
        phoneNo=sc.nextDouble();
        sc.nextLine();
        System.out.print("ENTER ACCOUNT NUMBER: ");
        accNo=sc.nextLine();
        System.out.print("ENTER INITIAL DEPOSIT: ");
        balance=sc.nextDouble();
        addTransactions("INITIAL DEPOSIT :"+balance+" ON  ");
        b.addTrans(balance+"$ -INITIAL DEPOSIT OF ACCOUNT NUMBER ="+accNo);
        System.out.println("");
    }

    void showAccountHolders()
    {
        String format = "|%1$-15s|%2$-10s|%3$-10s|%4$-15s|%5$-15s|%6$-10s|%7$-10s|\n";
        System.out.format(format,name,upiId,password,phoneNo,accNo,balance,bankName);
    }

    @Override
    public void deposit(Bank b) 
    {
        System.out.println("");
        Scanner sc=new Scanner(System.in);
        double money;
        System.out.println("HOW MUCH MONEY YOU WANT TO DEPOSIT");
        money=sc.nextDouble();
        balance+=money;
        this.addTransactions(money+C);
        b.addTrans(money+"$ ADDED IN ACCOUNT NUMBER ="+this.accNo);
        System.out.println("");
    }

    @Override
    public void send(User e,Bank b,Bank s) 
    {
        System.out.println("");
        Scanner sc=new Scanner(System.in);
        Double mny;
        System.out.println("HOW MUCH MONEY DO YOU WANT TO SEND:");
        mny=sc.nextDouble();
        if(e.bankName.equals(this.bankName))
        {
            if(this.balance >= mny)
            {
                balance-=mny;
                e.balance+=mny;
                e.addTransactions(mny+C);
                this.addTransactions(mny+D);
                s.addTrans(mny+"$ TRANSFERRED BY ACCOUNT NUMBER: "+this.accNo+" TO ACCOUNT NUMBER: "+e.accNo);
            }
            else
            {
                System.out.println("LOW BALANCE");
                send(e,b,s);
            }
        }
        else
        {
            if(((this.balance)-(tCharges)*this.balance) >= mny)
            {
                balance-=(mny+mny*tCharges);
                e.balance+=mny;
                e.addTransactions(mny+C);
                this.addTransactions(mny+mny*tCharges+D);
                s.addTrans(mny+"$ TRANSFERRED BY ACCOUNT NUMBER: "+this.accNo+" TO ACCOUNT NUMBER: "+e.accNo+" OF BANK "+e.bankName);
                b.addTrans(mny+"$ RECEIVED IN ACCOUNT NUMBER: "+e.accNo+" BY ACCOUNT NUMBER: "+this.accNo+" OF BANK "+this.bankName);
            }
            else
            {
                System.out.println("LOW BALANCE");
                send(e,b,s);                
            }
        }
        System.out.println("");
    }

    @Override
    public void withdraw(Bank b) 
    {
        System.out.println("");
        Scanner sc=new Scanner(System.in);
        Double mny;
        System.out.println("HOW MUCH MONEY DO YOU WANT TO WITHDRAW:");
        mny=sc.nextDouble();
        if(mny <= balance)
        {
            balance-=mny;
            this.addTransactions(mny+D);
            b.addTrans(mny+"$ WITHDRAW FROM ACCOUNT NUMBER ="+this.accNo);
        }
        else
        {
            System.out.println("LOW BALANCE!!! TRY AGAIN");
            this.withdraw(b);
        }
        System.out.println("");
    }

    private void addTransactions(String string) 
    {
        System.out.println("");
        String d = getCurrentDate();
        String t = getCurrentTime();
        transactions.addFirst(string+d+" At "+t); 
    }

    private String getCurrentDate() 
    {
        Date date =Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("E, dd MMMM yyyy");
        String strDate = dateFormat.format(date);
        return strDate;    
    }

    private String getCurrentTime() 
    {
        Date date =Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;      
    }
    
    void showTransactions() 
    {
        System.out.println("");
        for(String p:transactions)
        {
            System.out.println(p);
        }
        System.out.println("");
    }
    
    void addNotifications(String p)
    {
        notifications.addFirst(p);
    }

    void showNotifications() 
    {
        System.out.println("");
        if(notifications.isEmpty())
        {
            System.out.println("NO NEW NOTIFICATIONS");
        }
        for(String k:notifications)
        {
            System.out.println(k);
        }
        System.out.println("");
    }
}
