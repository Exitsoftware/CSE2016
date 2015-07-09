import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BookObjectOutputTest{
    
    public static void main(String[] args) {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        
        ArrayList list = new ArrayList();
        Book b1 = new Book("a0001", "자바완성", "간디", 10000);
        Book b2 = new Book("a0002", "파이썬완성", "톨스토이", 20000);
        Book b3 = new Book("a0003", "씨완성", "간달프", 25000);
        list.add(b1);
        list.add(b2);
        list.add(b3);
        
        try{
            fout = new FileOutputStream("booklist.dat");
            oos = new ObjectOutputStream(fout);
            
            oos.writeObject(list);//�뙆�씪 �엯�젰�쓽 踰뺤튃�뿉 �뵲�씪 �몢踰� �븳 寃껋엫.
            oos.reset();
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
    } // main end
} // class end
