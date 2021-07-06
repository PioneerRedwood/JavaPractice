
public class ChatRoom{
    private JFrame mainFrame;
    private JPanel mainPanel;
    private Container contentPane;
    private JButton sendButton;
    private JTextField textField;

    private String username;
    private String hostname;
    private int port;

    private AdvancedChatClient client;

    public class ChatManagingThread extends Thread{
        public void run(){
            try{
                Thread.sleep(1000);
            } catch(InterruptException e) {
                e.printStackTrace();
            }

            while(true){
                if(client.isConnected()){
                    try{
                        Thread.sleep(200);
                    } catch(InterruptException e) {
                        e.printStackTrace();
                    }

                    if(!client.getReadQueue().isEmpty()){
                        String msg = client.getReadQueue().poll();
                        JLabel recvMsgLabel = new JLabel(msg);
                        mainPanel.add(recvMsgLabel);
                        painPanel.updateUI();
                    }
                }
            }
        }
    }

    public class KeyEventListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_Enter){
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
        client = new AdvancedChatClient(username, hostname, port);
        this.username = username;
        this.hostname = hostname;
        this.port = port;
    }

    public boolean activateClient(){
        return client.execute();
    }

    public void init(){
        // main frame (window)
        mainFrame = new JFrame("EasyTalk");
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = mainFrame.getContentPane();
        mainFrame.setSize(400, 600);

        // mainPanel for displaying chat contents
        mainPanel = new JPanel();
        mainPanel.setBounds(10, 50, 360, 400);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel roomLabel = new JLabel("EasyTalk Chatting Room");
        roomLabel.setBounds(5, 0, 400, 20);

        JLabel nameLabel = new JLabel("[" + username + "]");
        nameLabel.setBounds(280, 10, 150, 20);

        sendButton = new JButton();
        sendButton.setText("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                sendExecute();
            }
        });
        sendButton.setBounds(310, 500, 50, 30);

        textField = new JTextField();
        textField.setBounds(10, 500, 300, 30);
        textField.addKeyListener(new KeyEventListener());

        contentPane.add(roomLabel);
        contentPane.add(nameLabel);
        contentPane.add(mainPanel);
        contentPane.add(textField);
        contentPane.add(sendButton);
    }

    public void setWindowCenter(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation(new Point((int)(screenSize.width * 0.25), (int)(screenSize.height * 0.25)));
    }

    public boolean show(){
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
        client.getWrteQueue().add(textField.getText());
        textField.setText("");
    }

}