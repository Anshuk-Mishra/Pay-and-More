import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

public class Pay {
    public void pay(String user) {
        Frame frame = new Frame();
        frame.setVisible(true);
        frame.setSize(300, 400);
        frame.setResizable(false);
        frame.setLayout(null);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimension.width - frame.getWidth()) / 2;
        int y = (dimension.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        Label eName = new Label("Username of Receiver ");
        eName.setBounds(40, 70, 200, 20);
        JTextField username = new JTextField();
        username.setBounds(40, 100, 150, 20);
        Label payAmount = new Label("Amount");
        payAmount.setBounds(40, 140, 150, 20);
        JTextField payMount = new JTextField();
        payMount.setBounds(40, 170, 150, 20);
        Button pay = new Button("Pay Securely");
        pay.setBounds(40, 220, 80, 20);
        Button home = new Button("Go to Home");
        home.setBounds(40,270,80,20);

        frame.add(pay);
        frame.add(eName);
        frame.add(username);
        frame.add(payAmount);
        frame.add(payMount);
        frame.add(home);

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home1 = new Home();
                home1.home(user);
                WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
            }
        });
        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!test(payMount.getText())) {
                    payMount.setText("Please Enter Integer Value");
                } else {
                    try {
                        if (readFile(username.getText())) {
                            String temp = username.getText();
                            if (!user.equals(temp)) {
                                Person person = new Person();
                                takeFile(user, person);
                                String path = username.getText() + "balance.txt";
                                String path2 = user + "balance.txt";
                                File file = new File(path);
                                File file1 = new File(path2);
                                System.out.println(username.getText() + "balance.txt");
                                if (file.exists() && file1.exists()) {
                                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(user+"balance.txt"));
                                    int bal = Integer.parseInt(bufferedReader2.readLine());
                                    if (bal >= Integer.parseInt(payMount.getText())) {
                                        //sender
                                        BufferedReader bufferedReader = new BufferedReader(new FileReader(path2));
                                        String store1 = bufferedReader.readLine();
                                        int storeUser1 = Integer.parseInt(store1);
                                        storeUser1 = storeUser1 - Integer.parseInt(payMount.getText());
                                        String storeUser3 = "" + storeUser1;
                                        System.out.println(store1);
                                        FileWriter fileWriter1 = new FileWriter(path2);
                                        fileWriter1.write(storeUser3);
                                        fileWriter1.close();

                                        //getter
                                        BufferedReader bufferedReader1 = new BufferedReader(new FileReader(path));
                                        String store = bufferedReader1.readLine();
                                        if (store == null)
                                            store = "0";
                                        int store2 = Integer.parseInt(store);
                                        store2 = store2 + Integer.parseInt(payMount.getText());
                                        String store3 = "" + store2;
                                        System.out.println(store);
                                        FileWriter fileWriter = new FileWriter(path);
                                        fileWriter.write(store3);
                                        fileWriter.close();
                                        WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                                        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
                                        new Paid(user,"Transaction Done Successfully");
                                    } else {
                                        System.out.println("Insufficient Balance");
                                        new Paid(user,"Insufficient Balance");
                                        WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                                        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
                                    }
                                } else {
                                    System.out.println("file Doesn't Exists");
                                    new Paid(user,"Wallet Doesn't Exists");
                                    WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                                    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
                                }
                            } else {

                                WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);

                                Frame frame1 = new Frame("Existence");

                                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                                int x = (dimension.width - frame1.getWidth()) / 2;
                                int y = (dimension.height - frame1.getHeight()) / 2;
                                frame1.setLocation(x, y);

                                frame1.setLayout(null);
                                frame1.setSize(300, 200);
                                frame1.setVisible(true);
                                frame1.setResizable(false);
                                frame1.addWindowListener(new WindowAdapter() {
                                    @Override
                                    public void windowClosing(WindowEvent e) {
                                        frame1.dispose();
                                    }
                                });
                                Label label = new Label("This User is not having pay functionality ");
                                Button button = new Button("OK");

                                label.setBounds(40, 70, 220, 30);
                                button.setBounds(70, 120, 150, 30);

                                frame1.add(label);
                                frame1.add(button);

                                button.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        WindowEvent closingEvent = new WindowEvent(frame1, WindowEvent.WINDOW_CLOSING);
                                        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
                                        new Main();
                                    }
                                });
                            }
                        }
                    } catch (FileNotFoundException ex) {
//                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }

    private boolean test(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Boolean readFile(String idKey) throws FileNotFoundException {
        Boolean set = false;
        File f = new File("LoginData.txt");
        FileInputStream fis = new FileInputStream("LoginData.txt");
        if (f.exists() && !f.isDirectory()) {
            ArrayList<Object> objectsList = new ArrayList<>();
            boolean cont = true;
            while (cont) {
                try {
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
                for (int i = 0; i < objectsList.size(); i++) {
                    Person person = new Person();
                    person = (Person) objectsList.get(i);
                    if (person.id.equals(idKey))
                        set = true;
                }
            }

        } else {
            set = false;
        }
        return set;
    }

    public Boolean takeFile(String idKey, Person tempPerson) throws FileNotFoundException {
        Boolean set = false;
        File f = new File("LoginData.txt");
        FileInputStream fis = new FileInputStream("LoginData.txt");
        if (f.exists() && !f.isDirectory()) {
            ArrayList<Object> objectsList = new ArrayList<>();
            boolean cont = true;
            while (cont) {
                try {
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
                for (int i = 0; i < objectsList.size(); i++) {
                    Person person = new Person();
                    person = (Person) objectsList.get(i);
                    if (person.id.equals(idKey)) {
                        tempPerson = person;
                        set = true;

                    }
                }
            }

        } else {
            set = false;
        }
        return set;
    }
}
