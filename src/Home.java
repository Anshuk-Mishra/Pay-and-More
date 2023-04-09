import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Home {
    public void home(String username) {
        Frame frame = new Frame();
        frame.setSize(250, 250);
        frame.setTitle("Home");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimension.width - frame.getWidth()) / 2;
        int y = (dimension.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        frame.setResizable(false);

        Button admin = new Button("Admin");
        Button serverList = new Button("People on Server");
        Button pay = new Button("Pay");
        Button balance = new Button("Balance");
        Button logout = new Button("Logout");

        admin.setBounds(20, 40, 210, 30);
        serverList.setBounds(20, 80, 210, 30);
        pay.setBounds(20, 120, 210, 30);
        balance.setBounds(20, 160, 210, 30);
        logout.setBounds(20, 200, 210, 30);

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
            }
        });
        serverList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ListOfPeople.listOfPeople(username);
                    WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
                } catch (IOException ex) {
//                    ex.printStackTrace();
                }
            }
        });
        balance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckBalance checkBalance = new CheckBalance();
                try {
                    checkBalance.checkBalance(username);
                    WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pay pay = new Pay();
                pay.pay(username);
                WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
            }
        });
        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Admin();
                WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
            }
        });

        frame.add(admin);
        frame.add(serverList);
        frame.add(pay);
        frame.add(balance);
        frame.add(logout);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }
}
