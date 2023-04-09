import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class Register {
        static Person person = new Person();
    public void register(){
        Frame frame = new Frame("Register");
        frame.setSize(250,290);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimension.width-frame.getWidth())/2;
        int y = (dimension.height-frame.getHeight())/2;
        frame.setLocation(x,y);
        frame.setResizable(false);

        Label label1 = new Label("Username");
        Label label2 = new Label("Password");
        Button button = new Button("Register");
        Button home = new Button("Home");
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        label1.setBounds(20,40,100,40);
        label2.setBounds(20,110,100,40);
        username.setBounds(20,80,100,20);
        password.setBounds(20,150,100,20);
        button.setBounds(20,190,210,30);
        home.setBounds(20,230,210,30);

        frame.add(button);
        frame.add(home);
        frame.add(label1);
        frame.add(label2);
        frame.add(username);
        frame.add(password);
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
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!username.getText().equals("") && !password.getText().equals("")){
                    //file
                    System.out.println(username.getText() + password.getText());
                    try {
                        if(readFile(username.getText(),password.getText())){
                            new Paid("This Username is already taken");
                        }else{
                            write(username.getText(),password.getText());
                            AccountCreated accountCreated = new AccountCreated();
                            accountCreated.accountCreated();
                            WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
                        }
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
    public Boolean readFile(String idKey,String key) throws FileNotFoundException {
        Boolean set = false;
        File f = new File("LoginData.txt");
        FileInputStream fis = new FileInputStream("LoginData.txt");
        if (f.exists() && !f.isDirectory()){
            ArrayList<Object> objectsList = new ArrayList<>();
            boolean cont = true;
            while (cont) {
                try{
                    ObjectInputStream input = new ObjectInputStream(fis);
                    System.out.println("Loop is running");
                    Object obj = input.readObject();
                    if (obj != null) {
                        objectsList.add(obj);
                    } else {
                        cont = false;
                    }
                } catch (Exception e) {
//                     System.out.println(e.printStackTrace());
                    break;
                }
                for(int i = 0 ; i < objectsList.size();i++){
                    Person person = new Person();
                    person = (Person) objectsList.get(i);
                    if (person.id.equals(idKey)){
                        set = true;
                        System.out.println(person.id);
                    }
                }
            }

        }else{
            set = false;
        }
        return set;
    }
    public void write(String idkey,String key){
        try {
            person.id = idkey;
            person.password = key;
            FileOutputStream file = new FileOutputStream("LoginData.txt",true);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(person);
            output.close();

        } catch (FileNotFoundException r) {
            r.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
