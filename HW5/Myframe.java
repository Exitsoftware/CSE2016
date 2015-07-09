import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

class MyActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if(b.getText().equals("Button")){
			System.out.println("pressed!");
		}
	}
}

public class Myframe extends JFrame {
	
	Myframe(){
		File f;
		MyActionListener listener = new MyActionListener();
		setTitle("Title");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		final JTextField tf1 = new JTextField();
		JButton btn = new JButton("Print");


		JMenuBar mb = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem menuItemOpen = new JMenuItem("Open");
		menuFile.add(menuItemOpen);
		mb.add(menuFile);
		setJMenuBar(mb);
		JFileChooser fc = new JFileChooser();
		int answer = fc.showOpenDialog(null);
		if(answer == JFileChooser.APPROVE_OPTION){
			f = fc.getSelectedFile();
		}


		
		//이거야 쉬운거
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(tf1.getText());
				System.out.println(e);
			}
		});
	
		add(tf1, BorderLayout.NORTH);
		add(btn, BorderLayout.SOUTH);
		
//		
//		JButton btn1 = new JButton("Center");
//		JButton btn2 = new JButton("East");
//		JButton btn3 = new JButton("West");
//		JButton btn4 = new JButton("North");
//		JButton btn5 = new JButton("South");
//		
//		btn1.addActionListener(listener);
//		btn2.addActionListener(listener);
//		btn3.addActionListener(listener);
//		btn4.addActionListener(listener);
//		btn5.addActionListener(listener);
//		
//		add(btn1, BorderLayout.CENTER);	
//		add(btn2, BorderLayout.EAST);
//		add(btn3, BorderLayout.WEST);
//		add(btn4, BorderLayout.NORTH);
//		add(btn5, BorderLayout.SOUTH);
		setSize(300,200);
		setVisible(true);
	}
	public static void main(String[] args) {
		Myframe s = new Myframe();
	}
}
