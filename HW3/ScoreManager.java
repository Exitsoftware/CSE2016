import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

class Score{
	static int no = 0; // 일련번호를 조금 더 쉽게 부여하기 위해서

	int num;
	String date;
	int home_num;
	int away_num;
	int home_score;
	int away_score;

	Score(String input_date, int home_team_num, int away_team_num, int home_team_score, int away_team_score){
		this.num = ++no;
		this.date = input_date;
		this.home_num =  home_team_num;
		this.away_num = away_team_num;
		this.home_score = home_team_score;
		this.away_score = away_team_score;
	}

	void print_score(String[] team_names){
		System.out.println("["+num+"]"+ " " + date + " " + team_names[home_num] + " " + home_score + " : " + away_score + " " + team_names[away_num]);
	}
}

public class ScoreManager{
	ArrayList<Score> score_list = new ArrayList<Score>();
	Scanner s = new Scanner(System.in);

	String[] team_names = {"삼성", "두산", "롯데", "SK", "한화", "넥센", "LG", "KIA", "NC", "KT"};
	int[] team_play_count = new int[team_names.length];
	int[] team_win_count = new int[team_names.length];
	int[] team_lose_count = new int[team_names.length];
	int[] team_draw_count = new int[team_names.length];
	int[] team_point = new int[team_names.length];
	double[] team_rate = new double[team_names.length];

	void print_team_list(){
		System.out.println();
		System.out.println("팀 목록표");
		System.out.println();

		for (int i = 0; i < team_names.length; i++) {
			System.out.println(i+1+") "+team_names[i]);
		}
		System.out.println();
	}

	void addScore(){
		try{
			System.out.print("경기일자를 입력해주세요. : ");
			String input_date = s.next();

			print_team_list();

			System.out.println();

			System.out.print("홈팀 번호를 입력해주세요. : ");
			int home_team_num = s.nextInt()-1;
			System.out.print("홈팀 점수를 입력해주세요. : ");
			int home_team_score = s.nextInt();
			System.out.print("원정팀 번호를 입력해주세요. : ");
			int away_team_num = s.nextInt()-1;
			System.out.print("원정팀 점수를 입력해주세요. : ");
			int away_team_score = s.nextInt();

			// 경기수 추가
			team_play_count[home_team_num]++;
			team_play_count[away_team_num]++;

			// 홈팀 승리
			if(home_team_score > away_team_score){
				team_win_count[home_team_num]++;
				team_lose_count[away_team_num]++;
				team_point[home_team_num] += 3;
			}
			// 원정팀 승리
			else if (home_team_score < away_team_score) {
				team_win_count[away_team_num]++;
				team_lose_count[home_team_num]++;
				team_point[away_team_num] += 3;
			}
			// 무승부
			else if (home_team_score == away_team_score) {
				team_draw_count[home_team_num]++;
				team_draw_count[away_team_num]++;
				team_point[home_team_num]++;
				team_point[away_team_num]++;
			}

			//승률 계산
			rate(home_team_num, away_team_num);

			score_list.add(new Score(input_date, home_team_num, away_team_num, home_team_score, away_team_score));
			System.out.println("\n정상적으로 추가되었습니다. ");
		}
		catch (Exception e) {
			System.out.println("\n입력이 잘못되었습니다.\n에러코드 : " + e);
		}
	}

	void modifyScore(){
		try{
			System.out.print("몇 번째 경기를 수정하시겠습니까? ");
			int select = s.nextInt()-1;

			int home_team_score = score_list.get(select).home_score;
			int away_team_score = score_list.get(select).away_score;

			int home_team_num = score_list.get(select).home_num;
			int away_team_num = score_list.get(select).away_num;

			// 홈팀 승리
			if(home_team_score > away_team_score){
				team_win_count[home_team_num]--;
				team_lose_count[away_team_num]--;
				team_point[home_team_num] -= 3;
			}
			// 원정팀 승리
			else if (home_team_score < away_team_score) {
				team_win_count[away_team_num]--;
				team_lose_count[home_team_num]--;
				team_point[away_team_num] -= 3;
			}
			// 무승부
			else if (home_team_score == away_team_score) {
				team_draw_count[home_team_num]--;
				team_draw_count[away_team_num]--;
				team_point[home_team_num]--;
				team_point[away_team_num]--;
			}

			System.out.print("홈팀의 점수를 입력해 주세요. : ");
			score_list.get(select).home_score = s.nextInt();
			System.out.print("원정팀의 점수를 입력해 주세요. : ");
			score_list.get(select).away_score = s.nextInt();

			home_team_score = score_list.get(select).home_score;
			away_team_score = score_list.get(select).away_score;

			home_team_num = score_list.get(select).home_num;
			away_team_num = score_list.get(select).away_num;


			// 홈팀 승리
			if(home_team_score > away_team_score){
				team_win_count[home_team_num]++;
				team_lose_count[away_team_num]++;
				team_point[home_team_num] += 3;
			}
			// 원정팀 승리
			else if (home_team_score < away_team_score) {
				team_win_count[away_team_num]++;
				team_lose_count[home_team_num]++;
				team_point[away_team_num] += 3;
			}
			// 무승부
			else if (home_team_score == away_team_score) {
				team_draw_count[home_team_num]++;
				team_draw_count[away_team_num]++;
				team_point[home_team_num]++;
				team_point[away_team_num]++;
			}

			//승률 계산
			rate(home_team_num, away_team_num);

			System.out.println("\n정상적으로 수정되었습니다. ");
		}
		catch (Exception e) {
			System.out.println("\n입력이 잘못되었습니다.\n에러코드 : " + e);		
		}

	}

	void listScores(){
		System.out.println();
		for (int i = 0; i < score_list.size(); i++) {
			score_list.get(i).print_score(team_names);
		}
		System.out.println();
	}

	void findScores(int search){

		for (int i = 0; i < score_list.size(); i++) {
			if (score_list.get(i).home_num == search || score_list.get(i).away_num == search) {
				score_list.get(i).print_score(team_names);
			}
		}

		System.out.println("\n"+team_win_count[search] + "승 " + team_draw_count[search] + "무 " + team_lose_count[search] + "패, 승점 " + team_point[search] + "점 승률 : " + team_rate[search]*100 + "%");
	}

	void findScores(String search_name){

		int team_num = 0;

		for (int i = 0; i < team_names.length; i++) {
			if (team_names[i].equals(search_name)) {
				team_num = i;		
			}
		}

		for (int i = 0; i < score_list.size(); i++) {
			if (score_list.get(i).home_num == team_num || score_list.get(i).away_num == team_num) {
				score_list.get(i).print_score(team_names);
			}
		}
		System.out.println("\n"+team_win_count[team_num] + "승 " + team_draw_count[team_num] + "무 " + team_lose_count[team_num] + "패, 승점 " + team_point[team_num] + "점 승률 : " + team_rate[team_num]*100 + "%");
	}

	void viewResult(){
		int max = 0;
		int max_index = 0;
		System.out.println();
		for (int i = 0; i < team_names.length; i++) {
			System.out.println(team_names[i]+" "+team_win_count[i] + "승 " + team_draw_count[i] + "무 " + team_lose_count[i] + "패, 승점 " + team_point[i] + "점 승률 : " + team_rate[i]*100 + "%");
			if(max < team_point[i]){
				max = team_point[i];
				max_index = i;
			}
		}
		System.out.println("\n현재 가장 우수한 성적의 팀은 " + team_names[max_index] + "팀입니다.");
	}

	void viewMatchScore(int first_team, int second_team){
		ArrayList<Integer> searched_score = new ArrayList<Integer>();

		for (int i = 0 ; i < score_list.size(); i++) {
			if ((score_list.get(i).home_num == first_team || score_list.get(i).away_num == first_team) &&
				(score_list.get(i).home_num == second_team || score_list.get(i).away_num == second_team)) {
				score_list.get(i).print_score(team_names);
				searched_score.add(i);
			}
		}

		int win = 0;
		int draw = 0;
		int lose = 0;

		for (int i = 0; i < searched_score.size(); i++) {
			int select = searched_score.get(i);

			int home_team_num = score_list.get(select).home_num;
			int away_team_num = score_list.get(select).away_num;
			int home_team_score = score_list.get(select).home_score;
			int away_team_score = score_list.get(select).away_score;

			boolean is_home = first_team == home_team_num;

			// 홈팀일 때, 홈팀 승리
			if(home_team_score > away_team_score && is_home){
				win++;
			}
			// 홈팀일 때, 원정팀 승리
			else if (home_team_score < away_team_score && is_home) {
				lose++;
			}
			// 원정일 때, 원정팀 승리
			else if (home_team_score < away_team_score && !is_home) {
				win++;
			}
			// 원정일 때, 홈팀 승리
			else if (home_team_score > away_team_score && !is_home) {
				lose++;
			}
			// 무승부
			else if (home_team_score == away_team_score) {
				draw++;
			}
		}
		System.out.println("\n" + team_names[first_team]+"의 "+team_names[second_team]+" 상대전적 - " + win + "승 " + draw + "무 "+ lose + "패");
	}

	int[] print_match(int first_team, int second_team){

		ArrayList<Integer> searched_score = new ArrayList<Integer>();

		for (int i = 0 ; i < score_list.size(); i++) {
			if ((score_list.get(i).home_num == first_team || score_list.get(i).away_num == first_team) &&
				(score_list.get(i).home_num == second_team || score_list.get(i).away_num == second_team)) {
				// score_list.get(i).print_score(team_names);
				searched_score.add(i);
			}
		}

		int win = 0;
		int draw = 0;
		int lose = 0;

		for (int i = 0; i < searched_score.size(); i++) {
			int select = searched_score.get(i);

			int home_team_num = score_list.get(select).home_num;
			int away_team_num = score_list.get(select).away_num;
			int home_team_score = score_list.get(select).home_score;
			int away_team_score = score_list.get(select).away_score;

			boolean is_home = first_team == home_team_num;

			// 홈팀일 때, 홈팀 승리
			if(home_team_score > away_team_score && is_home){
				win++;
			}
			// 홈팀일 때, 원정팀 승리
			else if (home_team_score < away_team_score && is_home) {
				lose++;
			}
			// 원정일 때, 원정팀 승리
			else if (home_team_score < away_team_score && !is_home) {
				win++;
			}
			// 원정일 때, 홈팀 승리
			else if (home_team_score > away_team_score && !is_home) {
				lose++;
			}
			// 무승부
			else if (home_team_score == away_team_score) {
				draw++;
			}
		}
		System.out.println("\t" + win + "-" + draw + "-"+ lose);
		int[] return_arr = {win, draw, lose};
		return return_arr;
	}

	void addtest(){

		for (int i = 0; i < 10; i++) {

			String month = String.valueOf((int)(Math.random()*4)+1);
			if(Integer.parseInt(month) < 10){
				month = "0".concat(month);
			}
			String day = String.valueOf((int)(Math.random()*28)+1);
			if(Integer.parseInt(day) < 10){
				day = "0".concat(day);
			}

			String input_date = (((("2015".concat("/")).concat(month)).concat("/")).concat(day));

			int home_team_num = (int)(Math.random()*10);
			int home_team_score = (int)(Math.random()*15);
			int away_team_num = (int)(Math.random()*10);
			while(home_team_num == away_team_num){
				away_team_num = (int)(Math.random()*10);
			}
			int away_team_score = (int)(Math.random()*15);

			// 경기수 추가
			team_play_count[home_team_num]++;
			team_play_count[away_team_num]++;

			// 홈팀 승리
			if(home_team_score > away_team_score){
				team_win_count[home_team_num]++;
				team_lose_count[away_team_num]++;
				team_point[home_team_num] += 3;
			}
			// 원정팀 승리
			else if (home_team_score < away_team_score) {
				team_win_count[away_team_num]++;
				team_lose_count[home_team_num]++;
				team_point[away_team_num] += 3;
			}
			// 무승부
			else if (home_team_score == away_team_score) {
				team_draw_count[home_team_num]++;
				team_draw_count[away_team_num]++;
				team_point[home_team_num]++;
				team_point[away_team_num]++;
			}

			//승률 계산
			rate(home_team_num, away_team_num);

			score_list.add(new Score(input_date, home_team_num, away_team_num, home_team_score, away_team_score));	
		}
		
		System.out.println("\n10개의 테스트 데이터가 정상적으로 입력되었습니다.\n ");
	}

	void rate(int home_team_num, int away_team_num){
		team_rate[home_team_num] = Math.round(( ((double)team_win_count[home_team_num] / (double)team_play_count[home_team_num])) * 10000d) / 10000d;
		team_rate[away_team_num] = Math.round(( ((double)team_win_count[away_team_num] / (double)team_play_count[away_team_num])) * 10000d) / 10000d;
	}

	void file_input(){
		try{

			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("scoredata.txt"), "euc-kr" ));

			String line = null;

			while((line = br.readLine()) != null){
				// System.out.println(line);
				String[] words = line.split(" ");
				// for (int i = 0; i < words.length; i++) {
				// 	System.out.println(words[i]);
				// }
				String input_date = words[0];
				String input_home_name = words[1];
				int input_home_score = Integer.parseInt(words[2]);
				int input_away_score = Integer.parseInt(words[4]);
				String input_away_name = words[5];
				int home_team_num = 0;
				int away_team_num = 0;

				for (int i = 0; i < team_names.length; i++) {
					if(team_names[i].equals(input_home_name)){
						home_team_num = i;
					}
				}

				for (int i = 0; i < team_names.length; i++) {
					if(team_names[i].equals(input_away_name)){
						away_team_num = i;
					}
				}

				// 경기수 추가
				team_play_count[home_team_num]++;
				team_play_count[away_team_num]++;

				// 홈팀 승리
				if(input_home_score > input_away_score){
					team_win_count[home_team_num]++;
					team_lose_count[away_team_num]++;
					team_point[home_team_num] += 3;
				}
				// 원정팀 승리
				else if (input_home_score < input_away_score) {
					team_win_count[away_team_num]++;
					team_lose_count[home_team_num]++;
					team_point[away_team_num] += 3;
				}
				// 무승부
				else if (input_home_score == input_away_score) {
					team_draw_count[home_team_num]++;
					team_draw_count[away_team_num]++;
					team_point[home_team_num]++;
					team_point[away_team_num]++;
				}

				//승률 계산
				rate(home_team_num, away_team_num);

				score_list.add(new Score(input_date, home_team_num, away_team_num, input_home_score, input_away_score));	
			}
			System.out.println("\n파일을 정상적으로 읽어왔습니다.\n");
			br.close();
		}
		catch (Exception e) {
			
		}

	}

	void file_output(){
		int[] rank = new int[team_names.length];
		int[][] if_home = new int[team_names.length][3];
		int[][] if_away = new int[team_names.length][3];


		try{
			// 파일을 이어쓰고 싶을때는 true를 붙여준다.
			// BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt", true));
			BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));

			for (int i = 0; i < team_names.length; i++) {
				for (int j = 0; j < team_names.length; j++) {ㅅ
					if (team_rate[i] < team_rate[j]) {
						rank[i]++;
					}
				}
			}

			for (int i = 0; i < team_names.length; i++) {
				for (int j = 0; j < score_list.size(); j++) {

					int home_team_score = score_list.get(j).home_score;
					int away_team_score = score_list.get(j).away_score;

					if (i == score_list.get(j).home_num) {

						// 홈팀일때

						if (home_team_score > away_team_score) {
							if_home[i][0]++;
						}
						else if (home_team_score == away_team_score) {
							if_home[i][1]++;	
						}
						else if (home_team_score < away_team_score) {
							if_home[i][2]++;
						}

					}
					else if (i == score_list.get(j).away_num) {
						// 원정팀일때

						if (home_team_score > away_team_score) {
							if_away[i][0]++;
						}
						else if (home_team_score == away_team_score) {
							if_away[i][1]++;	
						}
						else if (home_team_score < away_team_score) {
							if_away[i][2]++;
						}

					}
				}

			}


			for (int i = 0; i < team_names.length; i++) {
				for (int j = 0; j < team_names.length; j++) {
					if (i == rank[j]) {
						System.out.print("[" + (rank[j]+1) + "]" + "\t" + team_names[j] + "\t" + team_win_count[j] + "\t" + team_draw_count[j] + "\t" + team_lose_count[j] + "\t" + team_rate[j] + "\t");
						System.out.print(if_home[j][0] + "-" + if_home[j][1] + "-" + if_home[j][2] + "\t");
						System.out.print(if_away[j][0] + "-" + if_away[j][1] + "-" + if_away[j][2] + "\n");

						bw.write("[" + (rank[j]+1) + "]" + "\t" + team_names[j] + "\t" + team_win_count[j] + "\t" + team_draw_count[j] + "\t" + team_lose_count[j] + "\t" + team_rate[j] + "\t");
						bw.write(if_home[j][0] + "-" + if_home[j][1] + "-" + if_home[j][2] + "\t");
						bw.write(if_away[j][0] + "-" + if_away[j][1] + "-" + if_away[j][2] + "\n");
					}	
				}
			}

			for (int i = 0; i < team_names.length; i++) {
				System.out.println("\n"+team_names[i] + "의 경기"+"\n");
				bw.write("\n"+team_names[i] + "의 경기"+"\n");
				for (int j = 0 ; j < team_names.length; j++) {
					if(i != j){
						System.out.print("\t"+team_names[j]);
						bw.write("\t"+team_names[j]);
						int[] print_arr = print_match(i,j);
						bw.write("\t" + print_arr[0] + "-" + print_arr[1] + "-"+ print_arr[2] + "\n");

					}
				}
			}

			bw.close();

			
		}
		catch (Exception e) {
			
		}

	}

	void start(){
		while(true){
			int select;

			System.out.println("=======================================================================");
			System.out.print("1)등록 2)수정 3)전체출력 4)검색 5)통계 6)상대전적 7)종료 8)테스트 추가 9)input 0)ouput: ");
			select = s.nextInt();
			System.out.println("=======================================================================");

			if (select == 1) {
				addScore();
			}
			else if (select == 2) {
				modifyScore();
			}
			else if (select == 3) {
				listScores();	
			}
			else if (select == 4) {
				System.out.print("1)번호 검색 2)이름 검색 : ");
				select = s.nextInt();
				System.out.println("");
				print_team_list();
				try{
					if(select == 1){
						System.out.print("팀 번호를 입력해주세요. : ");
						select = s.nextInt()-1;
						System.out.println("");

						findScores(select);
					}
					else if (select == 2) {
						System.out.print("팀 이름을 입력해주세요. : ");
						String search_name = s.next();
						System.out.println("");

						findScores(search_name);
					}
				}
				catch (Exception e) {
					System.out.println("\n입력이 잘못되었습니다.\n에러코드 : " + e);
				}
			}
			else if (select == 5) {
				viewResult();
			}
			else if (select == 6) {
				try{
					int first_team;
					int second_team;

					print_team_list();

					System.out.print("검색할 팀 번호를 입력하세요. : ");
					first_team = s.nextInt() - 1;
					System.out.print("상대 팀 번호를 입력하세요. : ");
					second_team = s.nextInt() - 1;
					System.out.println("");
					viewMatchScore(first_team, second_team);
				}
				catch (Exception e) {
					System.out.println("\n입력이 잘못되었습니다.\n에러코드 : " + e);
				}
			}
			else if (select == 7) {
				break;
			}
			else if (select == 8) {
				addtest();
			}
			else if (select == 9) {
				file_input();
			}
			else if (select == 0) {
				file_output();
			}
			else{
				System.out.println("입력이 잘못되었습니다.");
			}
		}		
	}

	public static void main(String[] args) {
		ScoreManager manager = new ScoreManager();
		manager.start();	
	}
}