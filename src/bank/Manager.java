package bank;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    protected List<Double> DailyTransaction;
    public Manager(){
        DailyTransaction = new ArrayList<>();
        DailyTransaction.add(0.0);
        DailyTransaction.add(0.0);
        DailyTransaction.add(0.0);
    }

    public void DailyReport(int currency,double amount){
        DailyTransaction.set(currency,DailyTransaction.get(currency)+amount);
    }

    public void renewDailyReport(){
        for(int i=0;i<3;i++){
            DailyTransaction.set(i,0.0);
        }
    }

    public List<Double> getDailyTransaction(){return DailyTransaction;}
}
