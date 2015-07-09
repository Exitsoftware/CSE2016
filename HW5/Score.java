public class Score {
	int play_no_;
	String play_date_;
	int home_no_;
	int away_no_;
	int home_score_;
	int away_score_;

	public Score(int play_no, String play_date, int home_no, int away_no,
			int home_score, int away_score) {
		play_no_ = play_no;
		play_date_ = play_date;
		home_no_ = home_no;
		away_no_ = away_no;
		home_score_ = home_score;
		away_score_ = away_score;
	}

	public int get_play_no() {
		return play_no_;
	}

	public String get_play_date() {
		return play_date_;
	}

	public int get_home_no() {
		return home_no_;
	}

	public int get_away_no_() {
		return away_no_;
	}

	public int get_home_score() {
		return home_score_;
	}

	public int get_away_score() {
		return away_score_;
	}

	public void set_play_no(int a) {
		play_no_ = a;
	}

	public void set_play_date(String a) {
		play_date_ = a;
	}

	public void set_home_no(int a) {
		home_no_ = a;
	}

	public void set_away_no_(int a) {
		away_no_ = a;
	}

	public void set_home_score(int a) {
		home_score_ = a;
	}

	public void set_away_score(int a) {
		away_score_ = a;
	}
}
