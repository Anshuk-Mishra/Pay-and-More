import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class CheckBalance {
    public void checkBalance(String username) throws IOException {
        String path = username + "balance.txt";
        File file = new File(path);
        if(file.exists()){
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            Frame frame = new Frame("Balance Page");
            frame.setVisible(true);
            frame.setSize(250,200);
            frame.setResizable(false);
            frame.setLayout(null);
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (dimension.width-frame.getWidth())/2;
            int y = (dimension.height-frame.getHeight())/2;
            frame.setLocation(x,y);

            Label label = new Label("Balance Of Your Wallet");
            String balance = bufferedReader.readLine();
            Label label1 = new Label(balance);
            Button button = new Button("OK");

            label.setBounds(60,50,150,20);
            label1.setBounds(100,80,150,20);
            button.setBounds(50,120,150,30);

            frame.add(label);
            frame.add(label1);
            frame.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
                    Home home = new Home();
                    home.home(username);
                }
            });
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    frame.dispose();
                }
            });
        }else{
            Frame frame = new Frame("Error Access");
            frame.setVisible(true);
            frame.setSize(250,200);
            frame.setResizable(false);
            frame.setLayout(null);
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (dimension.width-frame.getWidth())/2;
            int y = (dimension.height-frame.getHeight())/2;
            frame.setLocation(x,y);

            Label label = new Label("Please Create Your Wallet");
            Label label1 = new Label("By Requesting Admin");
            Button button = new Button("OK");

            label.setBounds(50,50,150,20);
            label1.setBounds(60,80,150,20);
            button.setBounds(50,120,150,30);

            frame.add(label);
            frame.add(label1);
            frame.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
                    Home home = new Home();
                    home.home(username);
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
}
