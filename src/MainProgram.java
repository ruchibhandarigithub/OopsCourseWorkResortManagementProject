import java.io.*;
import java.util.Scanner;


public class MainProgram{
    static Scanner sc = new Scanner(System.in);
    static String clerkName ="Harsh Rohilla";
    static SortedArrayList<Customer> customers = new SortedArrayList<>();
    static SortedArrayList<Activity> activities = new SortedArrayList<>();
    static File inputFile = new File("./src/input.txt");

     // read the input from input filw
    private static void readFile()
    {
        String line = "";
        BufferedReader reader = null;
        try {

            reader = new BufferedReader(new FileReader(inputFile));
            reader.readLine();
            String data="";
            while ((line = reader.readLine()) != null){
                 data+=(line +",");
            }
            String[] datas = data.split(",");
            int i=0;
            for(i=0;i<datas.length;i+=2){

                if(datas[i].length()>1){
                    Activity a = new Activity(datas[i],Integer.parseInt(datas[i+1]));
                    activities.insert(a);

                }
                else{
                    break;
                }

            }
           for(i=i+1;i<datas.length;i++){
               String name[] = datas[i].split(" ");
               Customer c = new Customer(name[0],name[1]);
               customers.insert(c);
           }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }
    // menu list
    private static void printMenu()
    {
        System.out.println("-------------------------------------------------");
        System.out.println("Menu");
        System.out.println("f-  finish the program");
        System.out.println("a - to display on the screen information about all the activities.");
        System.out.println("c - to display on the screen information about all the customers.");
        System.out.println("t - to update the stored data when tickets are bought by one of the registered customers.");
        System.out.println("r - to update the stored data when a registered customer cancels tickets for a booking.");
    }

  public static void main(String args[]) throws IOException {
      readFile();

      FileWriter file = new FileWriter("./src/letter.txt");
      BufferedWriter buffer = new BufferedWriter(file);

      TicketOffice t = new TicketOffice(customers, activities);
      printMenu();
      char ch = sc.next().charAt(0);
      sc.nextLine();
      do{
           if(ch=='a'){
               t.informationAboutAllActivities(activities);
           }
           else if(ch=='c'){
               t.informationAboutAllCustomer(customers);
           }
          else if(ch=='t'){
              try {
                  t.bookTicket(buffer, clerkName);

              }
              catch(Exception e){
                  e.printStackTrace();
              }
           }
           else if(ch=='r'){
               t.cancelTicket();
           }
           printMenu();
           ch = sc.next().charAt(0);

      }
      while(ch!='f');

     }
  }







