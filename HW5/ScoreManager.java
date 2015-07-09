import java.util.ArrayList;
import java.util.Scanner;

public class ScoreManager {

	ArrayList<Score> sl_ = new ArrayList<Score>();
	String[] team_names_;
	int[] no_team_plays_;
	int[] no_team_wins_;
	int[] no_team_loses_;
	int[] no_team_draws_;
	double[] no_team_ratios_;

	public ScoreManager(ArrayList<Score> sl, String[] team_names,
			int[] no_team_plays, int[] no_team_wins, int[] no_team_loses,
			int[] no_team_draws, double[] no_team_ratios) {
		sl_ = sl;
		team_names_ = team_names;
		no_team_plays_ = no_team_plays;
		no_team_wins_ = no_team_wins;
		no_team_loses_ = no_team_loses;
		no_team_draws_ = no_team_draws;
		no_team_ratios_ = no_team_ratios;
	}

	public void findScores(int team_no) {
		ArrayList<Score> temp = new ArrayList<Score>();
		for (int i = 0; i < sl_.size(); i++) {
			if ((sl_.get(i).get_home_no() == team_no)
					|| (sl_.get(i).get_away_no_() == team_no)) {
				temp.add(sl_.get(i));
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			int play_number = i + 1;
			String play_date = temp.get(i).get_play_date();
			String home_name = get_team_name(temp.get(i).get_home_no());
			String away_name = get_team_name(temp.get(i).get_away_no_());
			int home_score = temp.get(i).get_home_score();
			int away_score = temp.get(i).get_away_score();
			System.out.println(play_number + ". " + play_date + " " + home_name
					+ " " + home_score + " : " + away_score + " " + away_name);
		}
	}

	public void findScores(String team_name) {
		ArrayList<Score> temp = new ArrayList<Score>();
		for (int i = 0; i < sl_.size(); i++) {
			if ((sl_.get(i).get_home_no() == get_team_number(team_name))
					|| (sl_.get(i).get_away_no_() == get_team_number(team_name))) {
				temp.add(sl_.get(i));
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			int play_number = i + 1;
			String play_date = temp.get(i).get_play_date();
			String home_name = get_team_name(temp.get(i).get_home_no());
			String away_name = get_team_name(temp.get(i).get_away_no_());
			int home_score = temp.get(i).get_home_score();
			int away_score = temp.get(i).get_away_score();
			System.out.println(play_number + ". " + play_date + " " + home_name
					+ " " + home_score + " : " + away_score + " " + away_name);
		}
	}

	public void viewMatchScore() {
		Scanner s = new Scanner(System.in);
		System.out.println("ÆÀ µÎ°³¸¦ ÀÔ·ÂÇÏ¼¼¿ä:");
		String team_a = s.next();
		String team_b = s.next();
		ArrayList<Score> ab_team_plays = new ArrayList<Score>();
		for (int i = 0; i < sl_.size(); i++) {
			if (((sl_.get(i).get_home_no() == get_team_number(team_a)) && (sl_
					.get(i).get_away_no_() == get_team_number(team_b)))
					|| ((sl_.get(i).get_home_no() == get_team_number(team_b)) && (sl_
							.get(i).get_away_no_() == get_team_number(team_a)))) {
				ab_team_plays.add(sl_.get(i));
			}
		}

		int wins = 0;
		int lose = 0;
		int draws = 0;

		for (int i = 0; i < ab_team_plays.size(); i++) {
			int play_number = i + 1;
			String play_date = ab_team_plays.get(i).get_play_date();
			String home_name = get_team_name(ab_team_plays.get(i).get_home_no());
			String away_name = get_team_name(ab_team_plays.get(i)
					.get_away_no_());
			int home_score = ab_team_plays.get(i).get_home_score();
			int away_score = ab_team_plays.get(i).get_away_score();
			System.out.println(play_number + ". " + play_date + " " + home_name
					+ " " + home_score + " : " + away_score + " " + away_name);
		}

	}

	public void start() {

	}

	public void add_score(Score a) {
		sl_.add(a);
	}

	public void modify_score(Score a) {

	}

	public void listScores() {
		for (int i = 0; i < sl_.size(); i++) {
			int play_number = i + 1;
			String play_date = sl_.get(i).get_play_date();
			String home_name = get_team_name(sl_.get(i).get_home_no());
			String away_name = get_team_name(sl_.get(i).get_away_no_());
			int home_score = sl_.get(i).get_home_score();
			int away_score = sl_.get(i).get_away_score();
			System.out.println(play_number + ". " + play_date + " " + home_name
					+ " " + home_score + " : " + away_score + " " + away_name);
		}
	}

	public String get_team_name(int team_no) {
		if (team_no == 1) {
			return "삼성";
		} else if (team_no == 2) {
			return "두산";
		} else if (team_no == 3) {
			return "롯데";
		} else if (team_no == 4) {
			return "SK";
		} else if (team_no == 5) {
			return "한화";
		} else if (team_no == 6) {
			return "넥센";
		} else if (team_no == 7) {
			return "LG";
		} else if (team_no == 8) {
			return "KIA";
		} else if (team_no == 9) {
			return "NC";
		} else if (team_no == 10) {
			return "KT";
		} else {
			return null;
		}
	}

	public int get_team_number(String team_name) {
		if (team_name.equals("삼성")) {
			return 1;
		} else if (team_name.equals("두산")) {
			return 2;
		} else if (team_name.equals("롯데")) {
			return 3;
		} else if (team_name.equals("SK")) {
			return 4;
		} else if (team_name.equals("한화")) {
			return 5;
		} else if (team_name.equals("넥센")) {
			return 6;
		} else if (team_name.equals("LG")) {
			return 7;
		} else if (team_name.equals("KIA")) {
			return 8;
		} else if (team_name.equals("NC")) {
			return 9;
		} else if (team_name.equals("KT")) {
			return 10;
		} else {
			return 1;
		}
	}

	public ArrayList<Score> get_sl() {
		return sl_;
	}

}
