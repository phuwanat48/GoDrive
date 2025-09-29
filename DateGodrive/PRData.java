package DateGodrive;
import java.io.*;
import java.util.Scanner;

public class PRData {
    public PRData(){
        File f = null; //ประกาศตัวแปร
        FileWriter fw = null; //ประกาศตัวแปร
        BufferedWriter bw = null; //ประกาศตัวแปร
        int n=1; //ประกาศตัวแปร
        try{
            f = new File("./DateGodrive/datedata.txt"); //เรียกใช้ไฟล์
            fw = new FileWriter(f); //เปิดไฟล์
            bw = new BufferedWriter(fw); //เขียนไฟล์
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Please input name :");
            String name = input.nextLine();
           
            if (name.equals("Q")) { //ถ้าเป็นQจะหลุดloopทันที
                break;
            }
                bw.write("Name["+n+"] :"+name+"\n"); //เขียนค่าลงในไฟล์
                n++; 
        }
    }catch(Exception e){ //ตั้งแต่ตรงนี้
        System.out.println(e);
    }finally{
        try{
            bw.close();fw.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }//ถึงตรงนี้ จารย์ต่อบอกว่าแทบจะตายตัว(ถ้าไม่มีคอมไพล์ไม่ได้)
}}