import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class AddAmount {
    AddAmount(){
        Frame frame = new Frame("Add Amount");
        frame.setVisible(true);
        frame.setSize(220,340);
        frame.setLayout(null);
        frame.setResizable(false);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimension.width-frame.getWidth())/2;
        int y = (dimension.height-frame.getHeight())/2;
        frame.setLocation(x,y);
        frame.setResizable(false);

        Label label = new Label("Enter Amount");
        Label label1 = new Label("Username");
        JTextField user = new JTextField();
        Button button = new Button("Proceed");
        JTextField amu = new JTextField();
        Button button2 = new Button("Home");

        frame.add(label);
        frame.add(label1);
        frame.add(user);
        frame.add(button);
        frame.add(button2);
        frame.add(amu);

        label1.setBounds(20,50,160,30);
        user.setBounds(20,90,160,30);
        label.setBounds(20,140,160,30);
        amu.setBounds(20,180,160,30);
        button.setBounds(20,230,160,30);
        button2.setBounds(20,270,160,30);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean set = true;
                try{
                    Integer.parseInt(amu.getText());
                }
                catch (Exception exp){
                    set = false;
                }
                if(set) {
                    System.out.println("done");
                    String name = user.getText();
                    String path = name + "balance.txt";
                    File file = new File(path);
                    try {
                        if (file.exists()) {
                            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
                            String store = bufferedReader.readLine();
                            int value;
                            if(store == null)
                                value = 0;
                            else
                                value = Integer.parseInt(store);
                            int secValue = Integer.parseInt(amu.getText());
                            int addition = value + secValue;
                            FileWriter fileWriter = new FileWriter(path);
                            String writeString = addition+"";
                            fileWriter.write(writeString);
                            fileWriter.close();
                            new Paid(secValue + " Amount added in wallet");
                        } else {
                            new Paid("The Wallet is not in existence");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    new Paid("Please Enter Integer Value");
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminPage();
                WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }
}
