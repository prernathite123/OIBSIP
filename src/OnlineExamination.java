import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.lang.Exception;
import java.util.Timer;
import java.util.TimerTask;

class OnlineExamination extends JFrame implements ActionListener
{
    JButton Btn;
    JPanel newPanel;
    JLabel userl,passl;
    JTextField textField1,textField2;
    OnlineExamination()
    {
        userl = new JLabel("Please enter your name");
        textField1 = new JTextField(5);
        passl = new JLabel("Please enter your Password");
        textField2 = new JPasswordField(5);
        Btn = new JButton("Login");
        newPanel = new JPanel(new GridLayout(3, 3));
        newPanel.add(userl);
        newPanel.add(textField1);
        newPanel.add(passl);
        newPanel.add(textField2);
        newPanel.add(Btn);
        add(newPanel, BorderLayout.CENTER);
        Btn.addActionListener(this);
        setTitle("Login Page");
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void actionPerformed(ActionEvent ae)
    {

        String userValue =textField1.getText();
        String passValue =textField2.getText();
        if(!passValue.equals(""))
        {
            new OnlineExaminationBegin(userValue);
        }
        else {
            textField2.setText("Please enter your password:");
            actionPerformed(ae);
        }
    }

}
class OnlineExaminationBegin extends JFrame implements ActionListener
{
    JLabel j1;
    JLabel j2;
    JRadioButton jrbtn[]=new JRadioButton[6];
    JButton b1,b2,log;
    ButtonGroup bg;
    int count=0,current=0,x=1,y=1,now=0;
    int m[]=new int[10];
    Timer timer =new Timer();
    OnlineExaminationBegin(String s)
    {
        super(s);
        j1=new JLabel();
        j2=new JLabel();
        add(j1);
        add(j2);
        bg=new ButtonGroup();

        for(int i=0;i<5;i++)
        {
            jrbtn[i]=new JRadioButton();
            add(jrbtn[i]);
            bg.add(jrbtn[i]);
        }
        b1=new JButton("Next question");
        b2=new JButton("Bookmark question");
        add(b1);
        add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);

        set();
        j1.setBounds(30,30,200,10);
        j2.setBounds(30,20,20,10);
        jrbtn[0].setBounds(50,80,100,20);
        jrbtn[1].setBounds(50,110,100,20);
        jrbtn[2].setBounds(50,140,100,20);
        jrbtn[3].setBounds(50,110,100,20);
        b1.setBounds(95,240,140,30);
        b2.setBounds(270,240,100,30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250,100);
        setVisible(true);
        setSize(600,350);
        timer.scheduleAtFixedRate(new TimerTask() {
            //@Override
            int i=200;
            public void run()
            {
                j2.setText("Time Left"+i);
                i--;
                if (i<0){
                    timer.cancel();
                    j2.setText("time out");
                }
            }
        },0,1000);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==b1)
        {
            if (check())
                count =count +1;
            current++;
            set();
            if (current==4)
            {
                b1.setEnabled(false);
                b2.setText("Result");
            }

        }
        if (e.getActionCommand().equals("Save for later"))
        {
            JButton bk=new JButton("Review"+x);
            bk.setBounds(480,20+30,100,30);
            add(bk);
            bk.addActionListener(this);
            m[x]=current;
            x++;
            current++;
            if (current==4)
                b2.setText("Result");
            b2.setActionCommand("Result");
            setVisible(false);
            setVisible(true);
        }
        for(int i=0, y=1;i<x;i++,y++)
        {
            if(e.getActionCommand().equals("Review"+y))
            {
                if(check())
                    count=count+1;
                now=current;
                current=m[y];
                set();
                ((JButton)e.getSource()).setEnabled(false);
                current=now;
            }


        }
        if (e.getActionCommand().equals("Result"))
        {
            if (check())
                count =count+1;
            current++;
            JOptionPane.showMessageDialog(this,"Score"+count);
            System.exit(0);
        }

    }
    void set()
    {
        jrbtn[4].setSelected(true);
        if(current==0)
        {
            j1.setText("Which is a reserved word in the Java programming language?");
            jrbtn[0].setText("method");
            jrbtn[1].setText("native");
            jrbtn[2].setText("subclasses");
            jrbtn[3].setText("reference");
        }
        if(current==1)
        {
            j1.setText("Which is the valid declarations within an interface definition?");
            jrbtn[0].setText("public double methoda();");
            jrbtn[1].setText("public final double methoda();");
            jrbtn[2].setText("static void methoda(double d1);");
            jrbtn[3].setText( "protected void methoda(double d1);");
        }
        if(current==2)
        {
            j1.setText("Which is a valid declarations of a String?\n" +
                    "String s1 = null;\n" +
                    "String s2 = 'null';\n" +
                    "String s3 = (String) 'abc';\n" +
                    "String s4 = (String) '\\ufeed';\n");
            jrbtn[0].setText("object");
            jrbtn[1].setText("b");
            jrbtn[2].setText("c");
            jrbtn[3].setText("d");
        }
        if(current==3)
        {
            j1.setText("You want subclasses in any package to have access to members of a superclass. Which is the most restrictive access that accomplishes this objective?\n" +
                    "public\n" +
                    "private\n" +
                    "protected\n" +
                    "transient\n");
            jrbtn[0].setText("object");
            jrbtn[1].setText("b");
            jrbtn[2].setText("c");
            jrbtn[3].setText("d");
        }
        if(current==4)
        {
            j1.setText("Which is a valid keyword in java?\n" +
                    "interface\n" +
                    "string\n" +
                    "Float\n" +
                    "unsigned");
            jrbtn[0].setText("object");
            jrbtn[1].setText("b");
            jrbtn[2].setText("c");
            jrbtn[3].setText("d");
        }
        j1.setBounds(60,60,600,30);
        for(int i=0,j=0;i<=90;i+=30,j++)
            jrbtn[j].setBounds(60,90+i,400,30);


    }
    boolean check()
    {
        if(current==0)
            return(jrbtn[2].isSelected());
        if(current==1)
            return(jrbtn[2].isSelected());
        if(current==2)
            return(jrbtn[4].isSelected());
        if(current==3)
            return(jrbtn[3].isSelected());
        if(current==4)
            return(jrbtn[4].isSelected());

        return false;
    }
    public static void main(String args[])
    {
        try
        {
            OnlineExamination form=new OnlineExamination();
            form.setSize(600,450);
            form.setVisible(true);
        }
        catch (Exception e)
        {
            JOptionPane.showConfirmDialog(null,e.getMessage());
        }
    }
}

