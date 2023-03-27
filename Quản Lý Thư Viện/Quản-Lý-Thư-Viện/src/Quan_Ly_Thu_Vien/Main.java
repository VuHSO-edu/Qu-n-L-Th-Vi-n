package Quan_Ly_Thu_Vien;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static List<Book> bookList;
    static Scanner scan;
    private static boolean add;
    public static void main(String[] args) {
        bookList = new ArrayList<>();
        scan = new Scanner(System.in);
        int choose;
        do
        {
            showMenu();
            choose = Integer.parseInt(scan.nextLine());
            switch(choose)
            {
                case 1:
                    input();

                    break;
                case 2:
                    display();
                    break;
                case 3:
                    sortByAuthorName();
                    display();
                    break;
                case 4:
                    saveObjectFile();
                    break;
                case 5:
                    saveTextFile();
                    break;
                case 6:
                    readObjectFile();
                    break;
                case 7:

                    System.out.println("Thoat!");
                    break;
                default:
                        System.out.println("Nhap sai:)))");
                        break;

            }
        }
        while(choose != 7);
        
    }
    static void showMenu()
    {
        System.out.println("1.Nhap so cuon sach");
        System.out.println("2.Hien thi thong tin sach");
        System.out.println("3.Sap xep theo ten tac gia");
        System.out.println("4.Luu thong tin Data.obj");
        System.out.println("5.luu vao Data.txt");
        System.out.println("6.Doc du lieu tu data.obj");
        System.out.println("7.Thoat");
        System.out.println("Chon: ");
    }
    private static void input()
    {
        System.out.println("Nhap so sach can them: ");
        int n = Integer.parseInt(scan.nextLine());

        for(int i = 0;i<n;i++)
        {
            Book inputBook = new Book();
            inputBook.input();

            bookList.add(inputBook);
        }
    }
    private static void display()
    {
        System.out.println("+++Hien thi thong tin sach+++");
        for(Book book : bookList)
        {
            book.display();
        }

    }
    private static void  sortByAuthorName()
    {
        Collections.sort(bookList,new Compaator<Book>() {
           
            public int compare(Book o1,Book o2)
            {
                return o1.getAuthorName().compareToIgnoreCase(o2.getAuthorName());

            }
        });
    }
    /**
     * 
     */
    private static void saveObjectFile()
    {
        FileOutputStream fos =  null;
        ObjectOutputStream oos = null;

        try{
            fos = new FileOutputStream("Data.obj");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(bookList);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(fos != null)
            {
                
                try{
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(oos != null)
            {
                try{
                   oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        

    }
    /**
     * 
     */
    private static void saveTextFile()
    {
        FileOutputStream fos = null;
        
        
        try{
            fos = new FileOutputStream("Data.txt");
            for(Book book: bookList)
            {
                String line = book.getFileLine();
                byte[] data = line.getBytes("utf8");

                fos.write(data);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            if(fos != null)
            {
                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
    }
    /**
     * 
     */
    private static void readObjectFile()
    {
        FileInputStream fis = null;
        ObjectInputStream ois  = null;
         
        
        try{
            fis = new FileInputStream("Data.obj");
            ois = new ObjectInputStream(fis);

            List<Book> DataList = (List<Book>) ois.readObject();
            for(Book book: DataList)
            {
                bookList.add(book);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            // TODO Auto-generated catch block
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if(fis != null)
            { 
                try{
                    fis.close();
                 } catch (IOException ex) {
                     Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
            }
            if(ois != null)
            {
                try{
                    ois.close();
                 } catch (IOException ex) {
                     Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
            }
        }

    }
    
}
