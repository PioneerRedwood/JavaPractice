import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Point;

public class ChatRoom{
    private JFrame mainFrame;
    private JPanel mainPanel;
    private Container contentPane;
    private JButton sendButton;
    private JTextField textField;
    private JScrollPane scrollPane; 

    private String username;
    private AdvancedChatClient client;

    public class ChatManagingThread extends Thread{
        public void run(){
            try{
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            JScrollBar bar = scrollPane.getVerticalScrollBar();
            while(true){
                if(client.isConnected()){
                    try{
                        Thread.sleep(200);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(!client.getReadQueue().isEmpty()){
                        String msg = client.getReadQueue().poll();

                        // analyze msg here
                        // why is the split() does not work??
                        // it has been making me confusing.
                        if(msg.contains("%^d")){
                            System.out.println(msg);
                            System.out.println("now participants: ");
                            for(String str : msg.split("%^d")){
                                System.out.print(str + " ");
                            }
                        } else{
                            JLabel recvMsgLabel = new JLabel(msg);
                            mainPanel.add(recvMsgLabel);
                            mainPanel.updateUI();
                            scrollPane.validate();
                            bar.setValue(bar.getMaximum());
                        }
                    }
                }
            }
        }
    }

    public class KeyEventListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                sendExecute();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }
    }

    public void initClient(String username, String hostname, int port){
        System.out.println("Initializing network client..");
        client = new AdvancedChatClient(username, hostname, port);
        this.username = username;
    }

    public boolean activateClient(){
        return client.execute();
    }

    public void init(){
        System.out.println("Initializing main frame window..");

        // main frame (window)
        mainFrame = new JFrame("EasyTalk");
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = mainFrame.getContentPane();
        mainFrame.setSize(400, 600);

        // mainPanel for displaying chat contents
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // make it scrollable by scrollPane in swing lib
        scrollPane = new JScrollPane(mainPanel, 
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 50, 360, 400);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

        JLabel roomLabel = new JLabel("EasyTalk Chatting Room");
        roomLabel.setBounds(5, 0, 400, 20);

        JButton roomInfoButton = new JButton();
        roomInfoButton.setText("Get room info");
        roomInfoButton.setBounds(280, 0, 200, 10);
        roomInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getUsersInServer();
            }
        });

        JLabel nameLabel = new JLabel("[" + username + "]");
        nameLabel.setBounds(280, 10, 150, 20);

        sendButton = new JButton();
        sendButton.setText("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendExecute();
            }
        });
        sendButton.setBounds(310, 500, 50, 30);

        textField = new JTextField();
        textField.setBounds(10, 500, 300, 30);
        textField.addKeyListener(new KeyEventListener());

        contentPane.add(roomLabel);
        contentPane.add(nameLabel);
        contentPane.add(scrollPane);
        contentPane.add(textField);
        contentPane.add(sendButton);
    }

    public void setWindowCenter(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation(new Point((int)(screenSize.width * 0.25), (int)(screenSize.height * 0.25)));
    }

    public boolean show(){
        System.out.println("Display main frame.. ");
        setWindowCenter();
        boolean status = activateClient();
        if(status) {
            mainFrame.setVisible(true);
            new ChatManagingThread().start();
            return status;
        } else {
            return status;
        }
    }

    public void sendExecute(){
        String msg = textField.getText().toString();
        // client.getWriteQueue().add(textField.getText());
        client.addMsgWriteQueue(msg);
        // System.out.println(textField.getText());
        textField.setText("");
    }

    public void getUsersInServer(){
        client.addMsgWriteQueue("getUser");

    }
}