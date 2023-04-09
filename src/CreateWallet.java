import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CreateWallet {
    CreateWallet(){
        Frame frame = new Frame("Create Wallet Page");
        frame.setVisible(true);
        frame.setSize(250,290);
        frame.setLayout(null);
        frame.setResizable(false);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimension.width-frame.getWidth())/2;
        int y = (dimension.height-frame.getHeight())/2;
        frame.setLocation(x,y);
        frame.setResizable(false);

        Label label = new Label("Create Wallet");
        Label label1 = new Label("Username");
        JTextField user = new JTextField();
        Button button = new Button("Proceed");
        Button button2 = new Button("Home");

        frame.add(label);
        frame.add(label1);
        frame.add(user);
        frame.add(button);
        frame.add(button2);

        label.setBounds(20,50,160,30);
        label1.setBounds(20,90,160,30);
        user.setBounds(20,130,160,30);
        button.setBounds(20,180,160,30);
        button2.setBounds(20,220,160,30);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register = new Register();
                try {
                    if (register.readFile(user.getText(), "")) {
                        String name = user.getText();
                        String path = name + "balance.txt";
                        File file = new File(path);
                        try {
                            if (file.createNewFile()) {
                                new Paid("The Wallet is created");
                            } else {
                                new Paid("The Wallet already exists");
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }else{
                        new Paid("No User Found By this username ");
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
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
