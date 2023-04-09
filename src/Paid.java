import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Paid extends Frame {
    Paid(String username,String message){
        setResizable(false);
        setVisible(true);
        setSize(300,200);
        setLayout(null);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimension.width - getWidth()) / 2;
        int y = (dimension.height - getHeight()) / 2;
        setLocation(x, y);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        Label label = new Label(message);
        Button ok = new Button("OK");

        add(label);
        add(ok);

        label.setBounds(60,70,200,30);
        ok.setBounds(65,120,150,30);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                home.home(username);
                closeWindow();
            }
        });

    }
    Paid(String message){
        setResizable(false);
        setVisible(true);
        setSize(300,200);
        setLayout(null);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimension.width - getWidth()) / 2;
        int y = (dimension.height - getHeight()) / 2;
        setLocation(x, y);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        Label label = new Label(message);
        Button ok = new Button("OK");

        add(label);
        add(ok);

        label.setBounds(60,70,200,30);
        ok.setBounds(65,120,150,30);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });

    }
    public void closeWindow(){
        WindowEvent closingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
    }
}
