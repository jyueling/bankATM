package bank;

import bank.AccountInherit.Saving;

import java.util.HashMap;

public class Customer {
    private HashMap<String,String> customer;
    private HashMap<String,User> account;

    public Customer(){
        customer = new HashMap<>();
        account = new HashMap<>();

        //init exist users
        User A = new User();
        this.addExistUser("A",A);

        User B = new User();
        addExistUser("B",B);
    }

    private void addExistUser(String str,User u){
        u.CreateAcc(str,"123");
        this.account.put(str,u);
        for(int i=0;i<3;i++){
            u.getSaving().totalDeposit.set(i,20000.0);
            u.getChecking().totalDeposit.set(i,100.0);
        }


        customer.put(u.getUsername(),u.getPassword());
    }

    public void addToList(User u){
        account.put(u.getUsername(),u);
        customer.put(u.getUsername(),u.getPassword());
    }

    public boolean checkLogin(String user, String pwd){
        if(customer.containsKey(user)){
            if(pwd.equals(customer.get(user))){
                return true;
            }
        }
        return false;
    }

    public void Daily(){
        for(User u:account.values()){
            for(int i=0;i<3;i++){
                double deposit = u.getSaving().dailyInterest(u.getSaving().gettotalDeposit().get(i));
                u.getSaving().gettotalDeposit().set(i,deposit);
            }
            u.dailyLoan();
        }


    }

    public HashMap<String,String> getCustomer(){return customer;}
    public HashMap<String,User> getAccount(){return account;}
}
