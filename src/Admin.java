import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Admin {
    Admin(){
        Frame frame = new Frame("Admin Login");
        frame.setSize(250,290);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimension.width-frame.getWidth())/2;
        int y = (dimension.height-frame.getHeight())/2;
        frame.setLocation(x,y);
        frame.setResizable(false);

        Label label1 = new Label("Admin Username");
        Label label2 = new Label("Admin Password");
        Button button = new Button("Admin Login");
        Button home = new Button("Home");
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        label1.setBounds(20,40,100,40);
        label2.setBounds(20,110,100,40);
        username.setBounds(20,80,150,20);
        password.setBounds(20,150,150,20);
        button.setBounds(20,190,210,30);
        home.setBounds(20,230,210,30);

        frame.add(home);
        frame.add(label1);
        frame.add(label2);
        frame.add(username);
        frame.add(password);
        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                frame.dispose();
            }
        });
        home.addActionListener(e -> {
            new Main();
            WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
        });
        button.addActionListener(e -> {
            if (username.getText().equals("MyServerAdmin@server.com") && password.getText().equals("Complex@password123")){
                new AdminPage();
                WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
            }else{
                new Paid("Please Enter Valid id or Password");
            }
        });
    }
}
