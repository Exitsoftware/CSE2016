import java.util.*;
import java.io.*;

public class BookManager{
	Scanner s = new Scanner(System.in);
	ArrayList<Book> list = new ArrayList<Book>();
	
	public void save(){
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;        

		try{
			fout = new FileOutputStream("booklist.dat");
			oos = new ObjectOutputStream(fout);

			oos.writeObject(list);
			oos.reset();

			System.out.println("저장되었습니다");

		}catch(Exception ex){
		}finally{
			try{
				oos.close();
				fout.close();
			}catch(IOException ioe){}
        } // finally
    }


    public void load(){

    	FileInputStream fin = null;
    	ObjectInputStream ois = null;

    	try{
			// int count = 0;
    		fin = new FileInputStream("booklist.dat");
    		ois = new ObjectInputStream(fin);

    		list = (ArrayList)ois.readObject();
    		list_print();

			// for(int i = 0; i < list.size(); i++){
			// 	Book temp = (Book)list.get(i);
			// 	System.out.println(++count + ") " + temp.toString());

			// }

			// Book b1 = (Book)list.get(0);
			// Book b2 = (Book)list.get(1);
			// Book b3 = (Book)list.get(2);

			// System.out.println(b1.toString());
			// System.out.println(b2.toString());
			// System.out.println(b3.toString());

    	}catch(Exception ex){
    		System.out.println(ex);
    	}finally{
    		try{
    			ois.close();
    			fin.close();
    		}catch(IOException ioe){}
		} // finally
	} // main end

	public void add(){
		String isbn;
		String title;
		String author;
		String year;
		String company;
		int popular;
		int price;
		boolean sw = true;

		System.out.print("isbn : ");
		isbn = s.next();

		for(int i = 0; i < list.size(); i++){
			Book temp = (Book)list.get(i);
			if(temp.getIsbn().equals(isbn)){
				sw = false;
			}
		}
		if(sw){
			System.out.print("제목 : ");
			title = s.next();
			System.out.print("저자 : ");
			author = s.next();
			System.out.print("연도 : ");
			year = s.next();
			System.out.print("출판사 : ");
			company = s.next();
			System.out.print("인기도 : ");
			popular = s.nextInt();
			System.out.print("가격 : ");
			price = s.nextInt();

			list.add(new Book(isbn, title, author, year, company, popular, price));
			System.out.println("");
			save();
		}
		else{
			System.out.println("중복된 isbn입니다.");
		}

	}

	public void modify(){
		list_print();
		int select = 0;
		System.out.print("\n몇 번째 자료를 수정하시겠습니까? : ");
		select = s.nextInt();
		try{
			Book temp = (Book)list.get(select - 1);

			String isbn;
			String title;
			String author;
			String year;
			String company;
			int popular;
			int price;
			boolean sw = true;

			System.out.print("isbn : ");
			isbn = s.next();

			for(int i = 0; i < list.size(); i++){
				Book temp2 = (Book)list.get(i);
				if(temp2.getIsbn().equals(isbn)){
					sw = false;
				}
			}
			if(sw){
				temp.setIsbn(isbn);
				System.out.print("제목 : ");
				title = s.next();
				temp.setTitle(title);
				System.out.print("저자 : ");
				author = s.next();
				temp.setAuthor(author);
				System.out.print("연도 : ");
				year = s.next();
				temp.setYear(year);
				System.out.print("출판사 : ");
				company = s.next();
				temp.setCompany(company);
				System.out.print("인기도 : ");
				popular = s.nextInt();
				temp.setPopular(popular);
				System.out.print("가격 : ");
				price = s.nextInt();
				temp.setPrice(price);
				System.out.println("");
				save();
			}
			else{
				System.out.println("중복된 isbn입니다.");
			}
		}
		catch(Exception ex){
			System.out.println("잘못된 입력입니다.");
		}
	}

	public void remove(){
		list_print();
		int select = 0;
		System.out.print("\n몇 번째 자료를 수정하시겠습니까? : ");
		select = s.nextInt();
		try{
			Book temp = (Book)list.get(select - 1);
			list.remove(select - 1);
			System.out.println("");
			save();
		}
		catch(Exception ex){
			System.out.println("잘못된 입력입니다.");
		}
	}

	public void search(){
		int select;
		System.out.print("\n무엇으로 검색하시겠습니까?");
		System.out.print("\n1)저자명 2)단가 3)출판사 4)isbn 5)출판연도 : ");
		select = s.nextInt();

		if(select == 1){
			System.out.print("저자명을 입력하세요. ");
			String search_name = s.next();

			for(int i = 0; i < list.size(); i++){
				Book temp = (Book)list.get(i);
				if(temp.getAuthor().equals(search_name)){
					System.out.println(temp.toString());
				}
			}


		}
		else if(select == 2){
			System.out.print("단가를 입력하세요. ");
			int search_price = s.nextInt();

			for(int i = 0; i < list.size(); i++){
				Book temp = (Book)list.get(i);
				if(temp.getPrice() > search_price){
					System.out.println(temp.toString());
				}
			}

		}
		else if(select == 3){
			System.out.print("출판사를 입력하세요. ");
			String search_name = s.next();

			for(int i = 0; i < list.size(); i++){
				Book temp = (Book)list.get(i);
				if(temp.getCompany().equals(search_name)){
					System.out.println(temp.toString());
				}
			}
		}
		else if(select == 4){
			System.out.print("isbn를 입력하세요. ");
			String search_isbn = s.next();

			for(int i = 0; i < list.size(); i++){
				Book temp = (Book)list.get(i);
				if(temp.getIsbn().equals(search_isbn)){
					System.out.println(temp.toString());
				}
			}
		}
		else if(select == 5){
			System.out.print("출판연도를 입력하세요. ");
			String search_year = s.next();

			for(int i = 0; i < list.size(); i++){
				Book temp = (Book)list.get(i);
				if(temp.getIsbn().equals(search_year)){
					System.out.println(temp.toString());
				}
			}
		}
		else{
			System.out.println("잘못된 입력입니다.");
		}

	}

	public void start(){

		while(true){
			int select = 0;
			System.out.println("=================================================================");
			System.out.print("1) 도서추가 2) 정보 변경 3) 도서삭제 4) 도서검색 5) 파일읽기 6) 파일저장 : ");
			select = s.nextInt();
			System.out.println("=================================================================");

			if(select == 1){
				add();
			}
			else if(select == 2){
				modify();
			}
			else if(select == 3){
				remove();
			}
			else if(select == 4){
				search();
			}
			else if (select == 5) {
				load();

			}
			else if (select == 6){
				save();
			}
			else{
				System.out.println("잘못된 입력입니다.");
			}


		}
	}

	public void list_print(){
		int count = 0;
		for(int i = 0; i < list.size(); i++){
			Book temp = (Book)list.get(i);
			System.out.println(++count + ") " + temp.toString());		
		}
	}
	public static void main(String[] args) {
		BookManager bm = new BookManager();
		bm.start();
	}
}