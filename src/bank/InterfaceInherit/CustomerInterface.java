package bank.InterfaceInherit;

import bank.*;
import bank.AccountInherit.Checking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerInterface extends Interface {
    public CustomerInterface(){super();}

    public int CurrencyChoose(JPanel jp, User u){
        JRadioButton jr1=addRadioButton(jp,"USD",80,30,100,20);
        JRadioButton jr2=addRadioButton(jp,"EUR",180,30,100,20);
        JRadioButton jr3=addRadioButton(jp,"CNY",280,30,100,20);

        jp.add(jr1);
        jp.add(jr2);
        jp.add(jr3);

        ButtonGroup group = new ButtonGroup();
        group.add(jr1);
        group.add(jr2);
        group.add(jr3);

        JButton jButton = addButton(360,25,140,30,"Choose Currency");

        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String qual = " ";
                if (jr1.isSelected()) {
                    u.Currency = 0;
                    System.out.println("USD");
                }
                else if (jr2.isSelected()) {
                    u.Currency = 1;
                    System.out.println("EUR");
                }
                else if(jr3.isSelected()){
                    u.Currency = 2;
                    System.out.println("CNY");
                }
            }
        });
        jp.add(jButton);
        return u.Currency;
    }

    public void Login(Customer c, Manager m){
        //new window
        JFrame cus_frame = this.addWindow("Customer",500,300);
        //user login
        JPanel cus_panel = new JPanel();
        cus_panel.setLayout(new FlowLayout());
        cus_panel.add(new JLabel("Customer Login"));
        cus_frame.add(cus_panel,"North");

        //label
        JPanel fieldPanel = new JPanel();
        JLabel label_user = new JLabel("Username");
        JLabel label_pwd = new JLabel("Password");
        fieldPanel.setLayout(null);
        label_user.setBounds(50, 20, 80, 20);
        label_pwd.setBounds(50, 60, 80, 20);
        fieldPanel.add(label_user);
        fieldPanel.add(label_pwd);

        //text field and password field
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        username.setBounds(120, 20, 200, 20);
        password.setBounds(120, 60, 200, 20);
        fieldPanel.add(username);
        fieldPanel.add(password);
        cus_frame.add(fieldPanel,"Center");

        //create account
        JPanel login_panel = new JPanel();
        JButton acc_btn = this.addButton(50,300,100,20,"Create Account");
        acc_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccCreate(c,m);
            }
        });
        login_panel.add(acc_btn);

        //log in
        JButton login_btn = this.addButton(50,200,100,20,"Log in");
        login_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pwd = new String(password.getPassword());

                //Customer c = new Customer();
                boolean b_login = c.checkLogin(user,pwd);
                functionChoose(b_login,user,c,m);
            }
        });
        cus_frame.add(fieldPanel,"Center");

        login_panel.setLayout(new FlowLayout());
        login_panel.add(login_btn);
        cus_frame.add(login_panel,"South");
    }

    public void AccCreate(Customer c,Manager m){
        JFrame frame = this.addWindow("Create Account",500,500);
        JPanel jp = new JPanel();

        JLabel label = new JLabel("Create an account will charge 10 USD");
        label.setBounds(30 ,5,300,20);

        JTextField username = this.addTextField(jp,"Username",30,30,30,60);
        JTextField pwd = this.addTextField(jp,"Password:",30,90,30,120);

        JButton create_btn = this.addButton(150,160,200,50,"Create Account");
        create_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = new User();
                u.CreateAcc(username.getText(),pwd.getText());
                c.addToList(u);
                m.DailyReport(0,10.0);
                JOptionPane.showMessageDialog(jp, "Account Create Success! Username is "+username.getText(),
                        "Account Create Success",JOptionPane.WARNING_MESSAGE);
            }
        });

        jp.add(label);
        jp.add(create_btn);
        frame.add(jp);
    }

    public void functionChoose(boolean b,String user,Customer c,Manager m){
        if(!b){
            JOptionPane.showMessageDialog(null, "Username and Password not match","Message", JOptionPane.ERROR_MESSAGE);
        }else{
            //System.out.println(b);
            User u = c.getAccount().get(user);

            JFrame frame = this.addWindow("Actions",500,700);
            JPanel jp=new JPanel();
            jp.setLayout(null);

            JLabel label = new JLabel("Please choose the currency First");
            label.setBounds(80 ,10,300,20);
            u.Currency = CurrencyChoose(jp,u);

            JButton Deposit_btn = this.addButton(150,50,200,100,"Deposit");
            Deposit_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //!!!!choose checking or saving
                    Deposit(u,u.Currency);
                }
            });
            JButton Withdraw_btn = this.addButton(150,150,200,100,"Withdraw");
            Withdraw_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    WithDraw(u.getChecking(),u.Currency,u,m);
                }
            });

            JButton Transfer_btn = this.addButton(150,250,200,100,"Transfer");
            Transfer_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Transfer(u,u.Currency,c,m);
                }
            });

            JButton Balance_btn = this.addButton(150,350,200,100,"Balance");
            Balance_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CheckBalance(u);
                }
            });

            JButton tran_btn = this.addButton(150,450,200,100,"Transaction History");
            tran_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PrintTransaction(u);
                }
            });

            JButton loan_btn = this.addButton(150,550,200,100,"Loan");
            loan_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Loan(u);
                }
            });

            jp.add(label);
            jp.add(Balance_btn);
            jp.add(Deposit_btn);
            jp.add(Withdraw_btn);
            jp.add(Transfer_btn);
            jp.add(tran_btn);
            jp.add(loan_btn);

            frame.add(jp);

        }
    }

    public void Deposit(User u,int currency){
        JFrame frame = this.addWindow("Deposit",500,500);

        JPanel jp = new JPanel();
        JTextField amount_check = this.addTextField(jp,"Amount for Deposit(Checking)",30,20,30,60);
        JTextField amount_saving = this.addTextField(jp,"Amount for Deposit(Saving)",30,100,30,130);

        JButton dep_btn = this.addButton(150,160,200,50,"Make Deposit");
        dep_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                u.getChecking().makeDeposit(Double.parseDouble(amount_check.getText()),currency);
                u.getSaving().makeDeposit(Double.parseDouble(amount_saving.getText()),currency);

                String cur_str = u.currencytoStr(u.Currency);

                u.inputTran(u.singleTran(amount_check.getText(),"Deposit","Checking",currency));
                u.inputTran(u.singleTran(amount_saving.getText(),"Deposit","Saving",currency));

                JOptionPane.showMessageDialog(jp, "Success! Now the checking account has total "+u.getChecking().gettotalDeposit().get(currency)+" "+
                                cur_str+ "\n"+ "the saving account has total "+ u.getSaving().gettotalDeposit().get(currency)+" "+cur_str,
                        "Deposit Success",JOptionPane.WARNING_MESSAGE);
                //System.out.println(acc.gettotalDeposit());
            }
        });

        jp.add(dep_btn);
        frame.add(jp);
    }

    public void WithDraw(Checking check, int currency, User u, Manager m){
        JFrame frame = this.addWindow("WithDraw",500,500);

        JPanel jp = new JPanel();

        JTextField amount = this.addTextField(jp,"Amount for Withdraw(Checking,fee = 1)",30,20,30,60);

        JButton dep_btn = this.addButton(150,160,200,50,"Withdraw");
        dep_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = check.Withdraw(Double.parseDouble(amount.getText()),currency,m);
                String cur_str = u.currencytoStr(currency);
                if(b){
                    u.inputTran(u.singleTran(amount.getText(),"WithDraw","Checking",currency));
                    JOptionPane.showMessageDialog(jp, "Success! Now the checking account has total "+check.gettotalDeposit().get(currency)+
                                    " "+cur_str,
                            "Withdraw Success",JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Account does not have enough money!","Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jp.add(dep_btn);
        frame.add(jp);
    }

    public void Transfer(User u,int currency,Customer c,Manager m){
        JFrame frame = this.addWindow("Transfer",500,500);

        JPanel jp = new JPanel();

        JTextField amount = this.addTextField(jp,"Amount for Transfer(Checking, fee = 1)",30,20,30,50);
        String cur_str = u.currencytoStr(currency);

        JTextField acc_num = this.addTextField(jp,"Account Number",30,70,30,100);
        JButton btn1 = this.addButton(150,120,200,30,"Transfer Money");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account account = c.getAccount().get(acc_num.getText()).getChecking();
                boolean b1 = u.getChecking().Transfer(Double.parseDouble(amount.getText()),currency,account,m);
                if(b1){
                    u.inputTran(u.singleTran(amount.getText(),"Transfer","Checking",currency));
                    JOptionPane.showMessageDialog(jp, "Success! Now the checking account has total "+u.getChecking().gettotalDeposit().get(currency)
                                    +" "+cur_str,
                            "Transfer Success",JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Account does not have enough money!","Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JTextField saving_tran = this.addTextField(jp,"Transfer Money from Checking to Saving(fee = 1)",30,160,30,190);
        JButton btn2 = this.addButton(150,210,200,30,"Transfer Money");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b1 = u.getChecking().Transfer(Double.parseDouble(saving_tran.getText()),currency,u.getSaving(),m);
                if(b1){
                    u.inputTran(u.singleTran(saving_tran.getText(),"Transfer","Checking",currency));
                    JOptionPane.showMessageDialog(jp, "Success! Now the checking account has total "+u.getChecking().gettotalDeposit().get(currency)+" "+cur_str+"\n"
                                    +"the saving account has total "+u.getSaving().gettotalDeposit().get(currency) +" "+cur_str,
                            "Transfer Success",JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Account does not have enough money!","Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        JTextField checking_tran = this.addTextField(jp,"Transfer Money from Saving to Checking(fee = 3)",30,250,30,280);
        JButton btn3 = this.addButton(150,300,200,30,"Transfer Money");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b1 = u.getSaving().transferChecking(Double.parseDouble(checking_tran.getText()),currency,u.getChecking(),m);
                if(b1){
                    u.inputTran(u.singleTran(checking_tran.getText(),"Transfer","Saving",currency));
                    JOptionPane.showMessageDialog(jp, "Success! Now the saving account has total "+u.getSaving().gettotalDeposit().get(currency) +" "+cur_str+"\n"
                                    +"the checking account has total "+u.getChecking().gettotalDeposit().get(currency) +" "+cur_str,
                            "Transfer Success",JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Account does not have enough money!","Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jp.add(btn1);
        jp.add(btn2);
        jp.add(btn3);
        frame.add(jp);
    }

    public void CheckBalance(User user){
        JFrame frame = this.addWindow("Balance",500,500);

        JPanel jp = new JPanel();

        String str = user.AccountBalance();

        JLabel label1 = new JLabel(str);

        jp.add(label1);
        frame.add(jp);
    }

    public void Loan(User u){
        JFrame frame = this.addWindow("Loan",500,500);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        JButton get_btn = this.addButton(150,50,200,80,"Loan");
        get_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getLoan(u,u.Currency);
            }
        });

        JButton repay_btn = this.addButton(150,150,200,80,"Repay");
        repay_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Repay(u,u.Currency);
            }
        });

        jp.add(get_btn);
        jp.add(repay_btn);
        frame.add(jp);

    }

    public void getLoan(User u,int currency){
        JFrame frame = this.addWindow("Loan",500,500);
        JPanel jp = new JPanel();
        JTextField loan_amount = this.addTextField(jp,"Enter Amount",30,20,30,60);
        JButton loan_btn = this.addButton(150,160,200,50,"Confirm");
        loan_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = u.addLoan(Double.parseDouble(loan_amount.getText()),currency);
                if(b){
                    JOptionPane.showMessageDialog(jp, "You successfully received "+loan_amount.getText()+
                                    " on your account.\n Daily interest rate would be 0.001%",
                            "Success",JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter valid number","Message", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        jp.add(loan_btn);
        frame.add(jp);
    }

    public void Repay(User u,int currency){
        JFrame frame = this.addWindow("Loan",500,500);
        JPanel jp = new JPanel();
        JTextField loan_amount = this.addTextField(jp,"Enter Amount",30,20,30,60);
        JButton loan_btn = this.addButton(150,160,200,50,"Confirm");
        loan_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = u.Repay(Double.parseDouble(loan_amount.getText()),currency);
                if(b){
                    JOptionPane.showMessageDialog(jp, "You successfully pay the loan "+loan_amount.getText()+
                                    " on your account.",
                            "Success",JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter valid number","Message", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        jp.add(loan_btn);
        frame.add(jp);
    }
}
