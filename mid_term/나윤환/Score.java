// 2014037901 컴퓨터공학과 나윤환
// 프로그램설계 방법론 중간고사
// 2015년 4월 25일 토요일

class Score{

	static int no = 0; // 일련번호를 조금 더 쉽게 부여하기 위해서

	int num;
	String date;
	int home_num;
	int arr_num;
	int home_score;
	int arr_score;

	Score(String input_date, int home_team_num, int arr_team_num, int home_team_score, int arr_team_score){
		this.num = ++no;
		this.date = input_date;
		this.home_num =  home_team_num;
		this.arr_num = arr_team_num;
		this.home_score = home_team_score;
		this.arr_score = arr_team_score;
	}

	void print_score(String[] team_names){
		System.out.println("["+num+"]"+ " " + date + " " + team_names[home_num] + " " + home_score + " : " + arr_score + " " + team_names[arr_num]);
	}
}