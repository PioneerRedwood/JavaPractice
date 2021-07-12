package EasyTalk;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.Point;

public class ChatDialog{
    private JFrame mainFrame;
    private JTextField nameField;
    private JTextField hostnameField;
    private JTextField portField;
    private Container contentPane;
    private JLabel statusLabel;

    private String username;
    private String hostname;
    private String port;

    private ChatRoom room; 

    public ChatDialog(){
        this.username = "Redwood";
        this.hostname = "localhost";
        this.port = "9000";
    }

    public ChatDialog(String username, String hostname, String port){
        this.username = username;
        this.hostname = hostname;
        this.port = port;
    }

    public void init(){
        mainFrame = new JFrame("EasyTalk");
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = mainFrame.getContentPane();

        setWindowCenter();

        // 1st row -- name
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 10, 150, 50);

        nameField = new JTextField(this.username);
        nameField.setBounds(210, 10, 150, 50);

        // 2nd row -- hostname
        JLabel hostLabel = new JLabel("hostname");
        hostLabel.setBounds(10, 110, 150, 50);

        hostnameField = new JTextField(this.hostname);
        hostnameField.setBounds(210, 110, 150, 50);

        // 3rd row -- port number
        JLabel portLabel = new JLabel("Port");
        portLabel.setBounds(10, 210, 150, 50);

        portField = new JTextField(this.port);
        portField.setBounds(210, 210, 150, 50);

        statusLabel = new JLabel("<html>To connect, fill the above context.<br>Basically it filled some variables</html>");
        statusLabel.setBounds(10, 250, 400, 150);

        JButton button = new JButton();
        button.setText("Connect");
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                execute();
            }
        });
        button.setSize(100, 30);
        button.setBounds(10, 400, 350, 50);

        contentPane.add(nameLabel);
        contentPane.add(nameField);

        contentPane.add(hostLabel);
        contentPane.add(hostnameField);

        contentPane.add(portLabel);
        contentPane.add(portField);

        contentPane.add(statusLabel);

        contentPane.add(button);
        


        mainFrame.setSize(400, 600);
    }

    public void show(){
        mainFrame.setVisible(true);
    }

    public void setWindowCenter(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation(new Point((int)(screenSize.width * 0.25), (int)(screenSize.height * 0.25)));
    }

    public void execute(){
        if(nameField.getText().isEmpty()){
            return;
        }

        if(room == null){
            room = new ChatRoom();
        } else {
            return ;
        }

        room.initClient(nameField.getText(), hostnameField.getText(), Integer.parseInt(portField.getText()));
        room.init();
        if(room.show()){
            this.mainFrame.setVisible(false);
        } else {
            statusLabel.repaint();
            statusLabel.setText("Can not connect to server!");
        }
    }

    public static void main(String[] args){
        ChatDialog dialog;
        if(args.length > 0){
            dialog = new ChatDialog(args[0], args[1], args[2]);
        } else {
            dialog = new ChatDialog();
        }
        dialog.init();
        dialog.show();
    }
}