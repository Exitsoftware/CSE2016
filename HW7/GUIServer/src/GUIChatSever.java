import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JTextArea;


class ChatThread extends Thread{
		private Socket sock;		
		private String id;
		private BufferedReader br;		
		private HashMap hm;	
		private HashSet<String> spam;
		private boolean initFlag = false;		
		
		public ChatThread(Socket sock, HashMap hm, JTextArea MSGView, HashSet spam){		
			this.sock = sock;	
			this.hm = hm;
			this.spam = spam;
			try{	
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));	
				br = new BufferedReader(new InputStreamReader(sock.getInputStream()));	
				id = br.readLine();	
				
				broadcast(id + "님이 접속하였습니다.");	
				
				MSGView.append("접속한 사용자의 아이디는 " + id + "입니다.\n");	
				synchronized(hm){	
					hm.put(this.id, pw);
				}
				synchronized(spam){	
					spam_refresh();
				}
				all_refresh();
				initFlag = true;	
			}catch(Exception ex){		
				MSGView.append(ex.toString());	
			}		
		}		
		public void run(){			
			try{		
				String line = null;	
				while((line = br.readLine()) != null){		
					if(line.equals("/quit"))	
						break;
					if(line.indexOf("/to ") == 0){	
						sendmsg(line);
					}
					else if(line.equals("/list")){
						user_list();
					}
					else if(line.indexOf("/addspam ") == 0){
						add_spam(line);
					}
					else if(line.equals("/listspam")){
						spam_list();
					}
					else{
						Date today = new Date();
						SimpleDateFormat form = new SimpleDateFormat("[a h시 m분 s초] ");
						synchronized(spam){		
							boolean temp = false;
							Iterator words = spam.iterator();
							PrintWriter pw = (PrintWriter)hm.get(id);
							while(words.hasNext()){	
								String word = (String)words.next();
								if(line.contains(word)){
									pw.println("[서버] 입력한 무자열에 금지어 [" + word + "]가 포함되어 전송하지 않았습니다. 조심하십시오.");
									temp = true;
								}
								
							}
							if(!temp){
								broadcast(form.format(today) + id + " : " + line);
							}
							pw.flush();
						}			
					}
				}		
			}catch(Exception ex){			
				System.out.println(ex);
			}finally{			
				synchronized(hm){		
					hm.remove(id);	
				}
				
				broadcast(id + " 님이 접속 종료하였습니다.");
				all_refresh();
				
				try{		
					if(sock != null)	
						sock.close();
				}catch(Exception ex){}		
			}			
		} // run
		
		
		public void user_list(){
			synchronized(hm){		
				int count = 0;
				Set ids = hm.keySet();
				
				Iterator id_iter = ids.iterator();
				
				PrintWriter pw = (PrintWriter)hm.get(id);
				
				pw.println("[서버] 현재 접속자 목록은 다음과 같습니다.");
				while(id_iter.hasNext()){	
					String temp = (String)id_iter.next();
					pw.println(++count + ") " + temp);
					
				}
				pw.println("총 " + count + "명입니다.");
				
				pw.flush();
			}			
		}
		
		public void all_refresh(){
			
			broadcast("/list_all");
			synchronized(hm){
				int count = 0;
				Set ids = hm.keySet();
				
				Iterator id_iter = ids.iterator();
			
				PrintWriter pw = (PrintWriter)hm.get(id);
				while(id_iter.hasNext()){	
					String temp = (String)id_iter.next();
					broadcast(++count + ") " + temp);
				
				}
			}
			broadcast("/list_all");

		}
		
		public void add_spam(String msg){
			int start = msg.indexOf(" ") +1;			
			String word = msg.substring(start);	
			if(word != null){			
				synchronized(spam){
					spam.add(word);
				}
				broadcast("금지어가 추가되었습니다. (" + word + ")");
				spam_refresh();
			}
		}
		
		public void spam_refresh(){
			broadcast("/spam_refresh");
			synchronized(spam){		
				int count = 0;
				
				Iterator words = spam.iterator();
				
				while(words.hasNext()){	
					String word = (String)words.next();
					broadcast(++count + ") " + word);	
				}
			}		
			broadcast("/spam_refresh");
		}
		public void spam_list(){
			synchronized(spam){		
				int count = 0;
				
				Iterator words = spam.iterator();
				
				PrintWriter pw = (PrintWriter)hm.get(id);
				pw.println("[서버] 금지어 목록은 다음과 같습니다.");
				while(words.hasNext()){	
					String word = (String)words.next();
					pw.println(++count + ") " + word);
					
				}
				pw.println("총 " + count + "개입니다.");
				pw.flush();
			}			
		}
		
		public void sendmsg(String msg){				
			int start = msg.indexOf(" ") +1;			
			int end = msg.indexOf(" ", start);			
			if(end != -1){			
				String to = msg.substring(start, end);		
				String msg2 = msg.substring(end+1);		
				Object obj = hm.get(to);		
				if(obj != null){		
					PrintWriter pw = (PrintWriter)obj;	
					pw.println(id + " 님이 다음의 귓속말을 보내셨습니다. : " + msg2);	
					pw.flush();	
				} // if	
			}		
		} // sendmsg			

		public void broadcast(String msg){			
			synchronized(hm){		
				Collection collection = hm.values();	
				Iterator iter = collection.iterator();	
				while(iter.hasNext()){	
					PrintWriter pw = (PrintWriter)iter.next();
					pw.println(msg);
					pw.flush();
				}	
			}		
		} // broadcast		
		
}				


public class GUIChatSever extends JFrame{
	
	static JTextArea MSGView = new JTextArea();
	
	
	
	GUIChatSever(){
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		
		
		MSGView.setLineWrap(true); //한줄이 너무 길면 자동으로 개행할지 설정
		MSGView.setEditable(false); //수정불
		JScrollPane jsp = new JScrollPane(MSGView);

		add(jsp,"Center");
		
		JTextField Input_msg = new JTextField();
		add(Input_msg,"South");
		Input_msg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Input_msg.setText("");
				MSGView.append(e.getActionCommand()+"\n");
				jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
			}
		});
		
		setSize(300,320);
		setVisible(true);
	}
		
	
	public static void main(String[] args) {
		GUIChatSever chatserver = new GUIChatSever();
		
		try{		
			ServerSocket server = new ServerSocket(10001);	
			MSGView.append("접속을 기다립니다. \n");	
			HashMap hm = new HashMap();	
				
			HashSet<String> spam = new HashSet<String>();
			while(true){	
				Socket sock = server.accept();
				ChatThread chatthread = new ChatThread(sock, hm, MSGView, spam);
				chatthread.start();
			} // while	
		}catch(Exception e){	
			System.out.println(e);
		}
		
	}
	
		
}
