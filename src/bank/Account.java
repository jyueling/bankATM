package bank;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected List<Double> totalDeposit;

    public Account(){
        totalDeposit = new ArrayList<>();
        totalDeposit.add(0.0);
        totalDeposit.add(0.0);
        totalDeposit.add(0.0);

    }

    public List<Double> gettotalDeposit(){return totalDeposit;}

    public void makeDeposit(double money,int currency){
        totalDeposit.set(currency,totalDeposit.get(currency)+money);
    }

}
