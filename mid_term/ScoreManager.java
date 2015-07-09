import java.util.*;

// 2014037901 컴퓨터공학과 나윤환
// 프로그램설계 방법론 중간고사
// 2015년 4월 25일 토요일


public class ScoreManager{
	ArrayList<Score> score_list = new ArrayList<Score>();
	Scanner s = new Scanner(System.in);

	String[] team_names =  new String[4];
	int[] team_play_count = new int[4];
	int[] team_win_count = new int[4];
	int[] team_lose_count = new int[4];
	int[] team_draw_count = new int[4];
	int[] team_point = new int[4];


	void addScore(){
		try{
			System.out.print("경기일자를 입력해주세요. : ");
			String input_date = s.next();

			// 팀이름 출력
			System.out.println();
			System.out.println("팀 목록표");
			System.out.println();
			for (int i = 0; i < team_names.length; i++) {
				System.out.println(i+1+") "+team_names[i]);
			}
			System.out.println();

			System.out.print("홈팀 번호를 입력해주세요. : ");
			int home_team_num = s.nextInt()-1;
			System.out.print("홈팀 점수를 입력해주세요. : ");
			int home_team_score = s.nextInt();
			System.out.print("원정팀 번호를 입력해주세요. : ");
			int arr_team_num = s.nextInt()-1;
			System.out.print("원정팀 점수를 입력해주세요. : ");
			int arr_team_score = s.nextInt();

			// 경기수 추가
			team_play_count[home_team_num]++;
			team_play_count[arr_team_num]++;

			// 홈팀 승리
			if(home_team_score > arr_team_score){
				team_win_count[home_team_num]++;
				team_lose_count[arr_team_num]++;
				team_point[home_team_num] += 3;
			}
			// 원정팀 승리
			else if (home_team_score < arr_team_score) {
				team_win_count[arr_team_num]++;
				team_lose_count[home_team_num]++;
				team_point[arr_team_num] += 3;
			}
			// 무승부
			else if (home_team_score == arr_team_score) {
				team_draw_count[home_team_num]++;
				team_draw_count[arr_team_num]++;
				team_point[home_team_num]++;
				team_point[arr_team_num]++;
			}

			score_list.add(new Score(input_date, home_team_num, arr_team_num, home_team_score, arr_team_score));
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
			int arr_team_score = score_list.get(select).arr_score;

			int home_team_num = score_list.get(select).home_num;
			int arr_team_num = score_list.get(select).arr_num;

			// 홈팀 승리
			if(home_team_score > arr_team_score){
				team_win_count[home_team_num]--;
				team_lose_count[arr_team_num]--;
				team_point[home_team_num] -= 3;
			}
			// 원정팀 승리
			else if (home_team_score < arr_team_score) {
				team_win_count[arr_team_num]--;
				team_lose_count[home_team_num]--;
				team_point[arr_team_num] -= 3;
			}
			// 무승부
			else if (home_team_score == arr_team_score) {
				team_draw_count[home_team_num]--;
				team_draw_count[arr_team_num]--;
				team_point[home_team_num]--;
				team_point[arr_team_num]--;
			}

			System.out.print("홈팀의 점수를 입력해 주세요. : ");
			score_list.get(select).home_score = s.nextInt();
			System.out.print("원정팀의 점수를 입력해 주세요. : ");
			score_list.get(select).arr_score = s.nextInt();

			home_team_score = score_list.get(select).home_score;
			arr_team_score = score_list.get(select).arr_score;

			home_team_num = score_list.get(select).home_num;
			arr_team_num = score_list.get(select).arr_num;


			// 홈팀 승리
			if(home_team_score > arr_team_score){
				team_win_count[home_team_num]++;
				team_lose_count[arr_team_num]++;
				team_point[home_team_num] += 3;
			}
			// 원정팀 승리
			else if (home_team_score < arr_team_score) {
				team_win_count[arr_team_num]++;
				team_lose_count[home_team_num]++;
				team_point[arr_team_num] += 3;
			}
			// 무승부
			else if (home_team_score == arr_team_score) {
				team_draw_count[home_team_num]++;
				team_draw_count[arr_team_num]++;
				team_point[home_team_num]++;
				team_point[arr_team_num]++;
			}


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
			if (score_list.get(i).home_num == search || score_list.get(i).arr_num == search) {
				score_list.get(i).print_score(team_names);
			}
		}

		System.out.println("\n"+team_win_count[search] + "승 " + team_draw_count[search] + "무 " + team_lose_count[search] + "패, 승점 " + team_point[search] + "점");
	}

	void findScores(String search_name){

		int team_num = 0;

		for (int i = 0; i < team_names.length; i++) {
			if (team_names[i].equals(search_name)) {
				team_num = i;		
			}
		}

		for (int i = 0; i < score_list.size(); i++) {
			if (score_list.get(i).home_num == team_num || score_list.get(i).arr_num == team_num) {
				score_list.get(i).print_score(team_names);
			}
		}
		System.out.println("\n"+team_win_count[team_num] + "승 " + team_draw_count[team_num] + "무 " + team_lose_count[team_num] + "패, 승점 " + team_point[team_num] + "점");
	}

	void viewResult(){
		int max = 0;
		int max_index = 0;
		System.out.println();
		for (int i = 0; i < team_names.length; i++) {
			System.out.println(team_names[i]+" "+team_win_count[i] + "승 " + team_draw_count[i] + "무 " + team_lose_count[i] + "패, 승점 " + team_point[i] + "점");
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
			if ((score_list.get(i).home_num == first_team || score_list.get(i).arr_num == first_team) &&
				(score_list.get(i).home_num == second_team || score_list.get(i).arr_num == second_team)) {
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
			int arr_team_num = score_list.get(select).arr_num;
			int home_team_score = score_list.get(select).home_score;
			int arr_team_score = score_list.get(select).arr_score;

			boolean is_home = first_team == home_team_num;

			// 홈팀일 때, 홈팀 승리
			if(home_team_score > arr_team_score && is_home){
				win++;
			}
			// 홈팀일 때, 원정팀 승리
			else if (home_team_score < arr_team_score && is_home) {
				lose++;
			}
			// 원정일 때, 원정팀 승리
			else if (home_team_score < arr_team_score && !is_home) {
				win++;
			}
			// 원정일 때, 홈팀 승리
			else if (home_team_score > arr_team_score && !is_home) {
				lose++;
			}
			// 무승부
			else if (home_team_score == arr_team_score) {
				draw++;
			}
		}
		System.out.println("\n" + team_names[first_team]+"의 "+team_names[second_team]+" 상대전적 - " + win + "승 " + draw + "무 "+ lose + "패");
	}

	// 랜덤으로 테스트 값을 10개 추가하기 위해서 
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

			int home_team_num = (int)(Math.random()*4);
			int home_team_score = (int)(Math.random()*10);
			int arr_team_num = (int)(Math.random()*4);
			while(home_team_num == arr_team_num){
				arr_team_num = (int)(Math.random()*4);
			}
			int arr_team_score = (int)(Math.random()*10);

			// 경기수 추가
			team_play_count[home_team_num]++;
			team_play_count[arr_team_num]++;

			// 홈팀 승리
			if(home_team_score > arr_team_score){
				team_win_count[home_team_num]++;
				team_lose_count[arr_team_num]++;
				team_point[home_team_num] += 3;
			}
			// 원정팀 승리
			else if (home_team_score < arr_team_score) {
				team_win_count[arr_team_num]++;
				team_lose_count[home_team_num]++;
				team_point[arr_team_num] += 3;
			}
			// 무승부
			else if (home_team_score == arr_team_score) {
				team_draw_count[home_team_num]++;
				team_draw_count[arr_team_num]++;
				team_point[home_team_num]++;
				team_point[arr_team_num]++;
			}

			score_list.add(new Score(input_date, home_team_num, arr_team_num, home_team_score, arr_team_score));	
		}
		
		System.out.println("\n10개의 테스트 데이터가 정상적으로 입력되었습니다.\n ");


	}
	void start(){

		int select;

		for (int i = 0; i < team_names.length; i++) {
			System.out.print(i+1+"번째 팀 이름을 입력해주세요. : ");
			team_names[i] = s.next();
		}

		while(true){
			System.out.println("=======================================================================");
			System.out.print("1)등록 2)수정 3)전체출력 4)검색 5)통계 6)상대전적 7)종료 8)테스트 추가: ");
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
				try{
					if(select == 1){
						System.out.print("팀 번호를 입력해주세요. : ");
						select = s.nextInt()-1;

						findScores(select);
					}
					else if (select == 2) {
						System.out.print("팀 이름을 입력해주세요. : ");
						String search_name = s.next();

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

					System.out.println();
					System.out.println("팀 목록표");
					System.out.println();
					for (int i = 0; i < team_names.length; i++) {
						System.out.println(i+1+") "+team_names[i]);
					}
					System.out.println();

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