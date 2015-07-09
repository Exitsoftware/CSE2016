import java.util.*;

class BankAccount{
	static int count = 0;
	int account_num;
	String name;
	int money;
	int grade;

	BankAccount(String name, int money, int grade){
		this.account_num = ++count;
		this.name = name;
		this.money = money;
		this.grade = grade;
	}

	void save(int money){
		this.money += money;
	}
	void draw(int money){
		this.money -= money;
	}

	int getAmount(){
		return money;
	}
	int getGrade(){
		return grade;
	}

}
public class Account_manager{
	static Scanner s = new Scanner(System.in);

	String name;
	int money;
	int grade;

	// Account_manager(){

	// }

	void addAcount(BankAccount[] arr, int size){
		System.out.print("이름을 입력해주세요. ");
		name = s.next();
		System.out.print("입금 금액을 입력해주세요. ");
		money = s.nextInt();
		System.out.print("신용등급을 입력해주세요. ");
		grade = s.nextInt();
		try{
			arr[size] = new BankAccount(name, money, grade);
			System.out.println("\n정상적으로 처리되었습니다.");
		}
		catch (Exception e) {
			System.out.println("입력 잘못되었습니다.");
		}
	}

	void print_account(BankAccount[] arr, int size){
		System.out.println("통장번호\t이름\t금액\t신용등급");
		for(int i = 0; i < size; i++){
			System.out.println(Integer.toString(arr[i].account_num) + "\t\t" + arr[i].name + "\t" + Integer.toString(arr[i].money) + "\t" + Integer.toString(arr[i].grade));
		}
	}

	void save_money(BankAccount[] arr, int size, Account_manager m){
		int select;
		int money;
		System.out.println("------------------------------------------------------------------------------");	
		m.print_account(arr, size);
		System.out.println("------------------------------------------------------------------------------");	
		System.out.print("어떤 계좌에 입금 하시겠습니까? ");
		select = s.nextInt() - 1;
		System.out.print("얼마를 입금하시겠습니까? ");
		money = s.nextInt();
		try{
			arr[select].save(money);
			System.out.println("\n정상적으로 처리되었습니다.");
			System.out.println("현재 "+ arr[select].name + "님의 계좌의 잔액은 " + Integer.toString(arr[select].money) + "원 입니다.");
		}
		catch (Exception e) {
			System.out.println("범위가 잘못되었습니다.");
		}
	}

	void draw_money(BankAccount[] arr, int size, Account_manager m){
		int select;
		int money;
		System.out.println("------------------------------------------------------------------------------");	
		m.print_account(arr, size);
		System.out.println("------------------------------------------------------------------------------");	
		System.out.print("어떤 계좌에서 출금 하시겠습니까? ");
		select = s.nextInt() - 1;
		System.out.print("얼마를 출금하시겠습니까? ");
		money = s.nextInt();
		try{
			if(arr[select].money < money && arr[select].grade > 1){
				System.out.println("잔액이 부족합니다.");
			}
			else{
				arr[select].draw(money);
				System.out.println("\n정상적으로 처리되었습니다.");
				System.out.println("현재 "+ arr[select].name + "님의 계좌의 잔액은 " + Integer.toString(arr[select].money) + "원 입니다.");		
			}
		}
		catch(Exception e){
			System.out.println("범위가 잘못되었습니다.");
		}
	}

	void transfer_money(BankAccount[] arr, int size, Account_manager m){
		int my_account;
		int your_account;
		int money;
		System.out.println("------------------------------------------------------------------------------");	
		m.print_account(arr, size);
		System.out.println("------------------------------------------------------------------------------");	
		System.out.print("당신의 계좌를 선택해 주세요. ");
		my_account = s.nextInt() - 1;
		System.out.print("송금할 계좌를 선택해 주세요. ");
		your_account = s.nextInt() - 1;
		System.out.print("얼마를 송금하시겠습니까? ");
		money = s.nextInt();
		try{
			if(arr[my_account].money < money && arr[my_account].grade > 1){
				System.out.println("잔액이 부족합니다.");
			}
			else{
				arr[my_account].draw(money);
				arr[your_account].save(money);
				System.out.println("\n정상적으로 처리되었습니다.");
				System.out.println("현재 "+ arr[my_account].name + "님의 계좌의 잔액은 " + Integer.toString(arr[my_account].money) + "원 입니다.");
				System.out.println("현재 "+ arr[your_account].name + "님의 계좌의 잔액은 " + Integer.toString(arr[your_account].money) + "원 입니다.");		
			}
		}
		catch(Exception e){
			System.out.println("범위가 잘못되었습니다.");
		}

	}

	void putInterest(BankAccount[] arr, int size){
		double[] interest = {0.03, 0.02, 0.01};
		System.out.println("모든 계좌에 이자를 지급합니다.");
		for(int i = 0; i < size; i++){
			int grade = arr[i].getGrade() - 1;
			try{
				if(!(grade == 0 && arr[i].money < 0)){
					arr[i].money += (int)arr[i].money * interest[grade];
				}
				else{
					System.out.println(arr[i].name+"님은 마이너스 통장이기에 이자를 받지 못하셨습니다.");
				}
			}
			catch(Exception e){
				System.out.println("범위가 잘못되었습니다.");
			}
		}
		System.out.println("\n정상적으로 처리되었습니다.");
	}

	void viewAccount(BankAccount[] arr, int size){
		int select;
		System.out.print("몇 번째 계좌를 확인하시겠습니까? ");
		select = s.nextInt()-1;
		try{
			if(select >= 0 && select < size){
				System.out.println("통장번호\t이름\t금액\t신용등급");
				System.out.println(Integer.toString(arr[select].account_num) + "\t\t" + arr[select].name + "\t" + Integer.toString(arr[select].money) + "\t" + Integer.toString(arr[select].grade));
			}
			else{
				System.out.println("잘못된 입력입니다.");
			}
		}
		catch(Exception e){
			System.out.println("범위가 잘못되었습니다.");
		}

	}

	static void start(Account_manager m){
		int size = 0;
		int answer;
		BankAccount[] arr = new BankAccount[5];
		while(true){
			System.out.println("------------------------------------------------------------------------------");
			System.out.print("1)개설 2)입금 3)출금 4)송금 5)이자지급 6)통장내역출력 7)특정계좌조희 8)종료 : ");
			answer = s.nextInt();
			System.out.println("------------------------------------------------------------------------------");
			try{
				if(answer == 1){
					if(size < 5){
						m.addAcount(arr, size);
						size++;
					}
					else{
						System.out.println("통장 최대 한도입니다.");
					}
				}
				else if (answer == 2) {
					m.save_money(arr, size, m);
				}
				else if (answer == 3) {
					m.draw_money(arr, size, m);
				}
				else if (answer == 4) {
					m.transfer_money(arr, size, m);
				}
				else if (answer == 5) {
					m.putInterest(arr, size);
				}
				else if (answer == 6) {
					m.print_account(arr, size);
				}
				else if (answer == 7) {
					m.viewAccount(arr, size);
				}
				else if (answer == 8) {
					System.out.println("시스템을 종료합니다.");
					break;
				}
			}
			catch (Exception e) {
				System.out.println("잘못입력되었습니다.");
			}
		}

	}

	public static void main(String[] args) {
		Account_manager m = new Account_manager();
		m.start(m);
	}
}

