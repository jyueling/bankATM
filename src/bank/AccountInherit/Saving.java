package bank.AccountInherit;

import bank.Account;
import bank.Manager;

public class Saving extends Account {
    public Saving(){super();}

    public boolean transferChecking(double money, int currency, Checking check, Manager m){
        if(money+3 <= totalDeposit.get(currency)){
            check.makeDeposit(money,currency);
            this.totalDeposit.set(currency,this.totalDeposit.get(currency)-money-3);
            m.DailyReport(currency,3.0);
            return true;
        }
        return false;
    }

    public double dailyInterest(double deposit){
        if(deposit > 10000){
            deposit = deposit*1.0001;
        }
        return deposit;
    }
}
