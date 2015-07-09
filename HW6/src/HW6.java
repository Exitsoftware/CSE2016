import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

class Score{
	static int no = 0; // 일련번호를 조금 더 쉽게 부여하기 위해서

	int num;
	String date;
	String home_name;
	String away_name;
	int home_score;
	int away_score;

	Score(String input_date, String home_team_name, String away_team_name, int home_team_score, int away_team_score){
		this.num = ++no;
		this.date = input_date;
		this.home_name =  home_team_name;
		this.away_name = away_team_name;
		this.home_score = home_team_score;
		this.away_score = away_team_score;
	}

	void print_score(String[] team_names){
		System.out.println(date + " " + home_name + " " + home_score + " : " + away_score + " " + away_name);
	}
}

public class HW6 extends JFrame{
	File f;
	
	ArrayList<Score> score_list = new ArrayList<Score>();
	HW6(){
		
		
		setTitle("Title");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout());
		JMenuBar mb = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem menuItemsave = new JMenuItem("Save");
		JMenuItem menuItemexit = new JMenuItem("Exit");
		JMenuItem menuItemOpen = new JMenuItem("Open");
		
		
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemsave);
		menuFile.add(menuItemexit);
		
		menuItemOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int answer = fc.showOpenDialog(null);
				if(answer == JFileChooser.APPROVE_OPTION){
					f = fc.getSelectedFile();
				}
				try{
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "euc-kr"));
					String line = null;
					while((line = br.readLine()) != null){
						String[] words = line.split(" ");
						// for (int i = 0; i < words.length; i++) {
						// 	System.out.println(words[i]);
						// }
						String input_date = words[0];
						String input_home_name = words[1];
						int input_home_score = Integer.parseInt(words[2]);
						int input_away_score = Integer.parseInt(words[4]);
						String input_away_name = words[5];
						
						System.out.println(input_date + " " + input_home_name + " " + input_home_score + " : " + input_away_score + " " + input_away_name);
					}
					br.close();
				}
				catch(Exception ex){

				}
			}
		});
		
		
		
		mb.add(menuFile);
		setJMenuBar(mb);

		JLabel lb_date = new JLabel();
		lb_date.setText("Date");
		lb_date.setHorizontalAlignment(JLabel.CENTER);
		add(lb_date);
		
		JTextField date = new JTextField();
		add(date);
		
		JLabel lb = new JLabel();
		lb.setText("Home");
		lb.setHorizontalAlignment(JLabel.CENTER);
		
		add(lb);
		
		JTextField home_name = new JTextField();
		add(home_name);
		
		JLabel lb_home_score = new JLabel();
		lb_home_score.setText("Score");
		lb_home_score.setHorizontalAlignment(JLabel.CENTER);
		add(lb_home_score);
		
		JTextField home_score = new JTextField();
		add(home_score);
		
		JLabel vs = new JLabel();
		vs.setText("vs");
		vs.setHorizontalAlignment(JLabel.CENTER);
		add(vs);
		
		
		
		JLabel lb_away_score = new JLabel();
		lb_away_score.setText("Score");
		lb_away_score.setHorizontalAlignment(JLabel.CENTER);
		add(lb_away_score);
		
		JTextField away_score = new JTextField();
		add(away_score);
		
		JLabel lb_home_name = new JLabel();
		lb_home_name.setText("Away");
		lb_home_name.setHorizontalAlignment(JLabel.CENTER);
		add(lb_home_name);
		
		JTextField away_name = new JTextField();
		add(away_name);
		
		JButton submit = new JButton();
		submit.setText("OK");
		add(submit);
		
		
	
		menuItemsave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				
				try{
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true), "euc-kr"));
					
					for(int i = 0; i < score_list.size(); i++){
						
						Score temp_score = score_list.get(i);
						
						String input_date = temp_score.date;
						String input_home_name = temp_score.home_name;
						String input_away_name = temp_score.away_name;
						int input_home_score = temp_score.home_score;
						int input_away_score = temp_score.away_score;
						
						bw.write(input_date + " " + input_home_name + " " + input_home_score + " : " + input_away_score + " " + input_away_name+ "\n");
						
					}
					bw.close();
				}
				catch(Exception ex){
					
				}
				
				
			}
		});
		
		menuItemexit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String input_date = date.getText();
				String input_home_name = home_name.getText();
				String input_away_name = away_name.getText();
				int input_home_score = Integer.parseInt(home_score.getText());
				int input_away_score = Integer.parseInt(away_score.getText());
				
				score_list.add(new Score(input_date, input_home_name, input_away_name, input_home_score, input_away_score));
				
				date.setText("");
				home_name.setText("");
				away_name.setText("");
				home_score.setText("");
				away_score.setText("");
			}
		});
		
		setSize(600,100);
		setVisible(true);
		
		
		
	}
	public static void main(String[] args) {
		HW6 frame = new HW6();
	}
}
