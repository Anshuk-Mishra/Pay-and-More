import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ListOfPeople implements AdjustmentListener{
    public static void listOfPeople(String user) throws IOException {
        Frame frame = new Frame();
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setLayout(null);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int j = (dimension.width - frame.getWidth()) / 2;
        int n = (dimension.height - frame.getHeight()) / 2;
        frame.setLocation(j, n);

        FileInputStream fileInputStream = new FileInputStream("LoginData.txt");

        int i = 0;

        try {
            Boolean value = true;
            while (value) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Person person = (Person) objectInputStream.readObject();
                int y = 0;
                if (person != null) {
                    System.out.println(person.id);
                    i++;
                } else
                    value = false;
            }
        } catch (IOException e) {
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        }
        fileInputStream.close();
        FileInputStream fileInputStream1 = new FileInputStream("LoginData.txt");
        Person[] personArray = new Person[i];
        int k = 0;
        try {
            Boolean value = true;
            while (value) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream1);
                Person person = (Person) objectInputStream.readObject();
                int y = 0;
                if (person != null) {
                    personArray[k] = person;
                    k++;
                } else
                    value = false;
            }
        } catch (IOException e) {
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        }
        int m = 2;
        Button button = new Button("Go To Home");
        button.setBounds(20, 55, 100, 20);
        frame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
                Home home = new Home();
                home.home(user);
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        DefaultListModel<String> l1 = new DefaultListModel<>();
        for (int l = 0; l < personArray.length; l++) {
            m++;
            //Label label = new Label(personArray[l].id);
            l1.addElement(personArray[l].id);
            //int y = 30 * m;
            //label.setBounds(20, y, 100, 20);
            //frame.add(label);
        }
        JList<String> list = new JList<>(l1);
        list.setBounds(20,60,280,200);
        frame.add(list);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {

    }
}
