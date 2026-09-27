import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener{

    private JTextField display;
    private String current= "0";
    private String previous =null;
    private String operator = null;


    public Main()  {

        setTitle("Calculator");
        setSize(320,420);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8,8));



        display = new JTextField("0");
        display.setEnabled(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("SansSerif", Font.BOLD, 32));
        display.setPreferredSize(new Dimension(0, 70));
        add(display,BorderLayout.NORTH);
        JPanel pad=new JPanel(new GridLayout(5,4,6,6));
        String[] buttons = {
                "C", "Del", "%", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=", "",
        };

        for(String label:buttons){
            if (label.isEmpty()){
                pad.add(new JLabel());
                continue;
            }
            JButton b=new JButton(label);
            b.setFont(new Font("SansSerif",Font.PLAIN,18));
            b.setBackground(new Color(0, 0, 0));
            b.setForeground(Color.white);
            b.addActionListener(this);
            pad.add(b);

        }
        add(pad,BorderLayout.CENTER);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();

        if (cmd.matches("[0-9]")){
            current = current.equals("0")?cmd : current+cmd;
        }else if(cmd.equals( ".")){
            if(current==null){
                current += ".";

            }
        }else if(cmd.equals("C")){
            current = "0";
            previous = null;
            operator=null;
        }else if(cmd.equals("DEL")){
            current= current.length()>1?current.substring(0,current.length()-1): "0";

        } else if (cmd.equals("%")) {
            current= String.valueOf(Double.parseDouble(current)/100);

        } else if ("+,-,*,/".contains(cmd)) {
            if(operator!=null){
                Answer();
            }
            previous=current;
            operator=cmd;
            current="0";
        } else if (cmd.equals("=")) {
            Answer();
        }
        display.setText(current);
    }

    private  void Answer(){
        if (previous ==null || operator==null)
            return;

        double a= Double.parseDouble(previous);
        double b= Double.parseDouble(current);
        double result =0;

        switch (operator){

            case "+":
                result = a+b;
                break;
            case "-":
                result = a-b;
                break;
            case "/":
                result = a/b;
                break;
            case "*":
                result = a*b;
                break;
        }

        current= String.valueOf(result);
        previous=null;
        operator=null;

    }
    public static void main(String[]args){
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }

}