import CarCard.VehicleManager;
import CarCard.*;
import GoDrive.*;

public class main {
    public static void main(String[] args) {
       // ระบุตำแหน่งไฟล์ CSV
        String csvFilePath = "./Lib/data/UserRentalHistory.csv"; 
        
        
        

         VehicleManager manager = new VehicleManager();

         new Addnewcar(manager, null, null);


        // // 1. สร้าง "สมองกลาง" (VehicleManager) ขึ้นมาก่อน
          //  VehicleManager manager = new VehicleManager();
        //     // 2. สร้างหน้าต่างแสดงรายการรถ (CarCard) และส่ง manager เข้าไป
           //  CarCard carCardWindow = new CarCard(manager);

        
     
        
      


                

       
       


       
    }
}
