import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AccountCreated {
    public void accountCreated(){
        Frame frame = new Frame("Confirmation");
        frame.setSize(250,190);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimension.width-frame.getWidth())/2;
        int y = (dimension.height-frame.getHeight())/2;
        frame.setLocation(x,y);
        frame.setResizable(false);

        Label label1 = new Label("Go and Try Logging in");
        Button home = new Button("Go To Home");
        home.setBounds(20,130,210,30);
        label1.setBounds(50,70,150,30);

        frame.add(label1);
        frame.add(home);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                frame.dispose();
            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
            }
        });
    }
}
