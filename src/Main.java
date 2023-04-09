import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends Frame implements ActionListener{
    Main(){
        //Frame size
        setTitle("Login/Register");
        setSize(250,200);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimension.width-getWidth())/2;
        int y = (dimension.height-getHeight())/2;
        setLocation(x,y);
        setResizable(false);

        Label label = new Label("Welcome To Pay & More");
        label.setBounds(55,50,210,30);
        add(label);

        //Elements
        Button register = new Button("Register");
        register.setBounds(20,100,210,30);
        Button Login = new Button("Login");
        Login.setBounds(20,150,210,30);
        Login.addActionListener(this::actionPerformed);
        register.addActionListener(this::actionPerformed);
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Login login = new Login();
                login.login();
                closeWindow();
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register1 = new Register();
                register1.register();
                closeWindow();
            }
        });

        //Addition of elements
        add(register);
        add(Login);

        //setup
        setLayout(null);
        setVisible(true);

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                dispose();
            }
        });
    }
    public static void main(String[] args) {
        new Main();
    }

    public void closeWindow(){
        WindowEvent closingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
