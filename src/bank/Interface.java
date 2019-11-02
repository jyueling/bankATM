package bank;

import bank.AccountInherit.Checking;
import bank.InterfaceInherit.CustomerInterface;
import bank.InterfaceInherit.ManagerInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Interface extends JFrame {
    private Customer c;
    public Interface(){
        c = new Customer();
    }

    public JButton addButton(int x, int y, int width,int height,String txt){
        JButton btn=new JButton(txt);
        btn.setBounds(x,y,width,height);

        return btn;
    }

    public JFrame addWindow(String txt,int width,int height){
        JFrame frame = new JFrame();
        frame.setTitle(txt);
        frame.setSize( width, height );
        frame.setLocation( 750, 100 );

        frame.setVisible( true );
        return frame;
    }

    public JTextField addTextField(JPanel jp,String str,int labelx,int labely,int txtx,int txty){
        JLabel label1 = new JLabel(str);
        jp.setLayout(null);
        label1.setBounds(labelx, labely, 350, 20);

        jp.add(label1);
        JTextField text = new JTextField("0");
        text.setBounds(txtx, txty, 200, 20);
        jp.add(text);

        return text;
    }

    public JRadioButton addRadioButton(JPanel jp,String str,int x,int y, int width,int height){
        JRadioButton jr=new JRadioButton(str);
        jr.setBounds(x,y,width,height);
        return jr;
    }

    public void PrintTransaction(User user){
        JFrame frame = this.addWindow("Transaction History",500,500);

        JPanel jp = new JPanel();

        String str = user.wholeTran();

        JLabel label1 = new JLabel(str);

        jp.add(label1);
        frame.add(jp);
    }

    public void Onboarding(){
        //Interface gui = new Interface();
        Customer c = new Customer();
        Manager m = new Manager();
        CustomerInterface cus_gui = new CustomerInterface();
        ManagerInterface man_gui = new ManagerInterface();
        JFrame frame = new JFrame();
        frame.setTitle( "Bank ATM" );
        frame.setSize( 500, 500 );
        frame.setLocation( 750, 100 );


        JPanel jp=new JPanel();
        jp.setLayout(null);
        JButton cus_btn = this.addButton(150,50,200,80,"Customer");
        cus_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cus_gui.Login(c,m);

            }
        });

        JButton man_btn = this.addButton(150,150,200,80,"Manager");
        man_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                man_gui.Onboard(c,m);
            }
        });

        JButton day_btn = this.addButton(150,250,200,80,"Next Day");
        day_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.Daily();
                m.renewDailyReport();
                JOptionPane.showMessageDialog(jp, "Daily Status is Updated to a new day",
                        "Next Day",JOptionPane.WARNING_MESSAGE);
            }
        });

        jp.add(day_btn);
        jp.add(cus_btn);
        jp.add(man_btn);

        frame.add(jp);

        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );

    }
}
