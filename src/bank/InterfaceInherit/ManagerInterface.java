package bank.InterfaceInherit;

import bank.Customer;
import bank.Interface;
import bank.Manager;
import bank.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerInterface extends Interface {
    public ManagerInterface(){super();}

    public void Onboard(Customer c, Manager m){
        JFrame frame = this.addWindow("Manager",500,500);

        JPanel jp = new JPanel();
        jp.setLayout(null);
        JButton check_btn = this.addButton(150,50,200,100,"Check Customer");
        check_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Check_status(c);
            }
        });

        JButton daily_btn = this.addButton(150,200,200,100,"Daily Report");
        daily_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Daily(m);
            }
        });

        jp.add(check_btn);
        jp.add(daily_btn);
        frame.add(jp);
    }

    public void Daily(Manager m){
        JFrame frame = addWindow("Daily Report",500,500);
        JPanel jp = new JPanel();
        String str = "<html>Checking Account<br>"+
                m.getDailyTransaction().get(0)+" USD<br>"+
                m.getDailyTransaction().get(1)+" EUR<br>"+
                m.getDailyTransaction().get(2)+" CNY</html>";
        JLabel label1 = new JLabel(str);

        jp.add(label1);
        frame.add(jp);
    }

    public void Check_status(Customer c){
        JFrame frame = this.addWindow("Check Customer Status",500,500);

        JPanel jp = new JPanel();

        JTextField username = this.addTextField(jp,"Customer Username",30,20,30,60);

        JButton check_btn = this.addButton(150,160,200,50,"Check Status");
        check_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(c.getAccount().containsKey(username.getText())){
                    User u = c.getAccount().get(username.getText());
                    String str = u.AccountBalance();
                    ShowStatus(str,username.getText());
                }else{
                    JOptionPane.showMessageDialog(null, "Account does not exist!","Message", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        JButton tran_btn = this.addButton(150,220,200,50,"Check Transaction History");
        tran_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(c.getAccount().containsKey(username.getText())) {
                    PrintTransaction(c.getAccount().get(username.getText()));
                }else{
                    JOptionPane.showMessageDialog(null, "Account does not exist!","Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jp.add(tran_btn);
        jp.add(check_btn);
        frame.add(jp);
    }

    public void ShowStatus(String str,String username){
        JFrame frame = this.addWindow(username,500,300);

        JPanel jp = new JPanel();

        JLabel label1 = new JLabel(str);

        jp.add(label1);
        frame.add(jp);
    }

}
