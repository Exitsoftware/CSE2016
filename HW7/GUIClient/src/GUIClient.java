import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


class LoginFrame extends JFrame{
	JLabel ID = new JLabel();
	JTextField input_id = new JTextField();
	JLabel IP = new JLabel();
	JTextField input_ip = new JTextField();
	JButton btn = new JButton();
	
	LoginFrame(){
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout());
		
		
		ID.setText("ID");
		add(ID);
		
		
		add(input_id);
		
		
		IP.setText("IP");
		add(IP);
		
		add(input_ip);
		
		
		btn.setText("접속");
		add(btn);
		
		setSize(400,100);
		setVisible(true);
	}
	void frame_hide(){
		setVisible(false);	
	}
	
}

class InputThread extends Thread{					
	private Socket sock = null;				
	private BufferedReader br = null;				
	private JTextArea view = null;
	private JTextArea user_view = null;
	private JTextArea spam_view = null;
	private JScrollPane jsp = null;
	
	public boolean user_temp = false;
	public boolean spam_temp = false;
	
	public InputThread(Socket sock, BufferedReader br, JTextArea view, JScrollPane jsp, JTextArea user_view, JTextArea spam_view){				
		this.sock = sock;			
		this.br = br;
		this.view = view;
		this.jsp = jsp;
		this.user_view = user_view;
		this.spam_view = spam_view;
	}				
	
	
	public void run(){				
		try{			
			
			String line = null;		
			while((line = br.readLine()) != null){	
				
				if (!user_temp && !spam_temp && !line.equals("/list_all") && !line.equals("/spam_refresh")){
					view.append(line + "\n");
					jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
					
				}
				
				if(user_temp && !line.equals("/list_all")){
					user_view.append(line + "\n");
				}
				

				if(spam_temp && !line.equals("/spam_refresh")){
					spam_view.append(line + "\n");
				}
				
				
				
				if(line.equals("/list_all")){
					user_temp = !user_temp;
					if(user_temp){
						user_view.setText("");
					}
				}
				
				if(line.equals("/spam_refresh")){
					spam_temp = !spam_temp;
					if(spam_temp){
						spam_view.setText("");
					}
				}
			}
			
			
		}catch(Exception ex){			
		}finally{			
			try{		
				if(br != null)	
					br.close();
			}catch(Exception ex){}		
			try{		
				if(sock != null)	
					sock.close();
			}catch(Exception ex){}		
		}			
	} // InputThread				
}
	
	
public class GUIClient extends JFrame{

	
	static JTextArea MSGView = new JTextArea();
	static JTextArea user_view = new JTextArea();
	static JTextArea spam_view = new JTextArea();
	static JPanel pan = new JPanel();
	
	JTextField Input_msg = new JTextField();
	

	Socket sock = null;	
	BufferedReader br = null;	
	PrintWriter pw = null;
	boolean endflag = false;
	
	String id;
	String ip;
	
	GUIClient(String id, String ip){
		this.id = id;
		this.ip = ip;
		
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		MSGView.setLineWrap(true); //한줄이 너무 길면 자동으로 개행할지 설정
		MSGView.setEditable(false); //수정불가
		
		user_view.setLineWrap(true); //한줄이 너무 길면 자동으로 개행할지 설정
		user_view.setEditable(false); //수정불가
		
		spam_view.setLineWrap(true); //한줄이 너무 길면 자동으로 개행할지 설정
		spam_view.setEditable(false); //수정불가
		
		JScrollPane jsp = new JScrollPane(MSGView);
		JScrollPane user_jsp = new JScrollPane(user_view);
		JScrollPane spam_jsp = new JScrollPane(spam_view);

		add(jsp,"Center");
		add(Input_msg,"South");
		pan.setLayout(new GridLayout(2,1));
		pan.add(user_jsp);
		pan.add(spam_jsp);
		add(pan,"East");
//		add(spam_jsp,"East");
		
		try{
			sock = new Socket(ip, 10001);
			pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));		
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			pw.println(id);		
			pw.flush();	
			
			InputThread it = new InputThread(sock, br, MSGView,jsp, user_view, spam_view);
			it.start();
			
			
			Input_msg.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String line = e.getActionCommand();
					Input_msg.setText("");
					
					pw.println(line);	
					pw.flush();	
					
					if(line.equals("/quit")){	
						endflag = true;
						MSGView.append("클라이언트의 접속을 종료합니다.");
						System.exit(1);
					}
					
					
				}
			});
			
		}
		catch(Exception ex){
			if(!endflag)		
				MSGView.append(ex.toString());	
		};
		
			
		setSize(1000,320);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		LoginFrame login = new LoginFrame();
		
		login.btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					String user_id = login.input_id.getText();
					String server_ip = login.input_ip.getText();
					
					login.frame_hide();
					GUIClient client = new GUIClient(user_id, server_ip);
			}	
		});
		
		
		
	}
	
	
}