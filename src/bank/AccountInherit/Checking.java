package bank.AccountInherit;

import bank.Account;
import bank.Manager;

public class Checking extends Account {
    public Checking(){super();}

    public boolean Withdraw(double money, int currency, Manager m){
        if(totalDeposit.get(currency)+1 >= money){
            totalDeposit.set(currency,totalDeposit.get(currency)-money-1);
            m.DailyReport(currency,1.0);
            return true;
        }
        return false;
    }

    public boolean Transfer(double money,int currency,Account account,Manager m){
        if(money+1 <= totalDeposit.get(currency)){
            account.makeDeposit(money,currency);
            this.totalDeposit.set(currency,this.totalDeposit.get(currency)-money-1);
            m.DailyReport(currency,1.0);
            return true;
        }
        return false;
    }

}
