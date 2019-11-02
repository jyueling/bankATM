package bank;

import bank.AccountInherit.Checking;
import bank.AccountInherit.Saving;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class User {
    private Saving saving;
    private Checking checking;
    private Queue<String> transaction;
    private List<Double> loan;
    public int Currency;


    private String username;
    private String password;

    public User(){
        saving = new Saving();
        checking = new Checking();
        Currency = 0;
        transaction = new LinkedList<>();
        loan = new ArrayList<>();
        loan.add(0.0);
        loan.add(0.0);
        loan.add(0.0);
    }

    public void CreateAcc(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String currencytoStr(int currency){
        if(currency == 0){
            return "USD";
        }else if(currency == 1){
            return "EUR";
        }else{
            return "CNY";
        }
    }

    public String AccountBalance(){
        return "<html>Checking Account<br>"+
                this.getChecking().gettotalDeposit().get(0)+" USD<br>"+
                this.getChecking().gettotalDeposit().get(1)+" EUR<br>"+
                this.getChecking().gettotalDeposit().get(2)+" CNY<br>"+
                "Saving Account<br>"+
                this.getSaving().gettotalDeposit().get(0)+" USD<br>"+
                this.getSaving().gettotalDeposit().get(1)+" EUR<br>"+
                this.getSaving().gettotalDeposit().get(2)+" CNY<br>"+
                "Loan<br>"+
                this.loan.get(0)+" USD<br>"+
                this.loan.get(1)+" EUR<br>"+
                this.loan.get(2)+" CNY</html>";

    }

    public String singleTran(String amount, String type,String account,int currency){
        String cur = currencytoStr(currency);
        return account+" "+type+" "+amount+" "+cur;
    }

    public void inputTran(String str){
        if(transaction.size()>=10){
            transaction.remove();
            transaction.add(str);
        }else{
            transaction.add(str);
        }
    }

    public String wholeTran(){
        String str = "<html>";
        for(String s:transaction){
            str = str+s+"<br>";
        }
        str = str +"</html>";
        return str;
    }

    public String PrintLoan(){
        return "<html>Checking Account<br>"+
                this.loan.get(0)+" USD<br>"+
                this.loan.get(1)+" EUR<br>"+
                this.loan.get(2)+" CNY</html>";
    }

    public boolean addLoan(double amount,int currency){
        if(amount >= 0){
            this.loan.set(currency,loan.get(currency)+amount);
            return true;
        }
        return false;
    }

    public boolean Repay(double amount,int currency){
        if(amount >= 0 && amount < loan.get(currency)){
            this.loan.set(currency,loan.get(currency)-amount);
            return true;
        }else if(amount >= loan.get(currency)){
            this.loan.set(currency,0.0);
            return true;
        }
        return false;
    }

    public void dailyLoan(){
        for(int i=0;i<3;i++){
            loan.set(i,loan.get(i)*1.00001);
        }
    }

    public String getUsername(){return username;}
    public String getPassword(){return password;}

    public Saving getSaving(){return saving;}
    public Checking getChecking(){return checking;}

    public List<Double> getLoan(){return loan;}



}
