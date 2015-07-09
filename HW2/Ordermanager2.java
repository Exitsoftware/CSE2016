import java.util.*;

class Order{
	// 후라이드 : 10000, 앙념: 13000, 간장 14000, 파닭 16000
	// 0: 후라이드, 1: 양념, 2: 간장, 3: 파닭

	static int[] menu_price = {10000, 13000, 14000, 16000};
	static int no = 0;

	int num;
	int[] menu_count;
	String name;
	int price = 0;

	Order(String name, int[] count){
		this.num = ++no;
		this.name = name;
		this.menu_count = count;
		for(int i = 0; i < menu_count.length; i++){
			this.price += menu_count[i] * menu_price[i];
		}
	}

	void print_order(){
		System.out.print(num + "\t" + name + "\t");
		for(int i = 0; i < menu_count.length; i++){
			System.out.print(menu_count[i] + "  ");
		}
		System.out.println(price);
	}

	
}
public class Ordermanager2{
	ArrayList<Order> order_list = new ArrayList<Order>();
	HashMap<String, Integer> map = new HashMap<String, Integer>();

	Scanner s = new Scanner(System.in);
	String[] menu_name = {"후라이드", "양념", "간장", "파닭"};

	void addTester(){

		String[] tester_name = {"나윤환","이영재","이용철","안주현","김건기","주민건","조용일","김태형","권준형","하민수"};
		for (int i = 0; i < tester_name.length; i++) {

			int[] menu_count = new int[menu_name.length];
			
			for(int j = 0; j < menu_count.length; j++){
				menu_count[j] = (int)(Math.random()*10);
			}

			order_list.add(new Order(tester_name[i], menu_count));

			if(map.containsKey(tester_name[i])){
				map.put(tester_name[i], map.get(tester_name[i]) + order_list.get(order_list.size()-1).price);
			}
			else{
				map.put(tester_name[i], order_list.get(order_list.size()-1).price);
			}
		}
	}
	void addOrder(){
		String name;
		int[] count = new int[menu_name.length];

		System.out.print("주문자 이름을 입력해주세요. ");
		name = s.next();
		for(int i = 0; i < count.length; i++){
			System.out.print(menu_name[i] + "의 개수를 입력해주세요. : ");
			count[i] = s.nextInt();
		}
		order_list.add(new Order(name, count));

		if(map.containsKey(name)){
			map.put(name, map.get(name) + order_list.get(order_list.size()-1).price);
		}
		else{
			map.put(name, order_list.get(order_list.size()-1).price);
		}

	}
	void cancleOrder(int select){
		try{
			String name = order_list.get(select).name;

			map.put(name, map.get(name) - order_list.get(select).price);

			order_list.remove(select);
			System.out.println("성공적으로 삭제하였습니다.");
		}
		catch (Exception e) {
			System.out.println("삭제하는데 오류가 발생하였습니다.");
		}
	}
	void print_all(){
		for (int i = 0; i < order_list.size() ; i++) {
			order_list.get(i).print_order();
		}
	}
	void findOrder(String search_name){
		System.out.println("");
		int search_count = 0;
		for(int i = 0; i < order_list.size(); i++){
			if(order_list.get(i).name.equals(search_name)){
				search_count++;
				order_list.get(i).print_order();
			}
		}
		System.out.println("\n총 " + search_count + "명의 " + search_name + "님 데이터를 찾았습니다.");
	}

	void findOrder(int search_int){
		if(search_int >= 1 && search_int <= 4){
			for (int i = 0; i < order_list.size(); i++) {
				if (order_list.get(i).menu_count[search_int-1] != 0){
					order_list.get(i).print_order();
				}
			}
		}
		else{
			for (int i = 0; i < order_list.size(); i++) {
				if(order_list.get(i).price >= search_int){
					order_list.get(i).print_order();
				}
			}
		}
	}

	void viewStatus(){
		System.out.println("\n총 주문서 개수 : " + order_list.size());
		for (int i = 0; i < menu_name.length; i++) {
			int total = 0;
			for(int j = 0; j < order_list.size(); j++){
				total += order_list.get(j).menu_count[i];
			}
			System.out.println(menu_name[i] + "의 개수 : " + total);
		}
		System.out.println("");
	}
	void findBest(){
		Iterator<String> it = map.keySet().iterator();
		String max_name = "";
		int max = 0;

		while(it.hasNext()){
			String name = it.next();
			if(map.get(name) > max){
				max = map.get(name);
				max_name = name;
			}
		}

		System.out.println("\n베스트 고객은 " + max_name + "님 입니다.\n");

	}
	void start(){
		int select;
		String search;

		while(true){
			System.out.println("===============================================================================");
			System.out.print("1)주문추가, 2)주문취소, 3)주문서보기, 4)주문서찾기\n5)판매현황보기, 6)베스트고객, 7)종료 : ");
			select = s.nextInt();
			System.out.println("===============================================================================");

			if(select == 1){
				addOrder();
			}
			else if(select == 2){
				System.out.print("몇 번째 주문을 삭제하시겠습니까? : ");
				select = s.nextInt() - 1;
				cancleOrder(select);
			}
			else if (select == 3) {
				System.out.println("\n번호\t이름\t후 양 간 파 총");
				print_all();	
				System.out.println("");
			}
			else if (select == 4) {
				boolean is_num = true;
				
				System.out.print("검색할 내용을 입력해주세요. (1.후라이드 2.양념 3.간장 4.파닭) : ");
				search = s.next();
				for(int i = 0; i < search.length(); i++){
					if(!Character.isDigit(search.charAt(i))){
						is_num = false;
						break;
					}
				}

				if(is_num){
					int search_int = Integer.parseInt(search);
					findOrder(search_int);
				}
				else{
					findOrder(search);
				}

			}
			else if (select == 5) {
				viewStatus();
			}
			else if (select == 6) {
				findBest();
			}
			else if (select == 8) {
				addTester();
				System.out.println("\n10명의 테스터 입력완료.\n");
			}


		}
	}
	public static void main(String[] args) {
		Ordermanager2 manager = new Ordermanager2();
		manager.start();
	}
}