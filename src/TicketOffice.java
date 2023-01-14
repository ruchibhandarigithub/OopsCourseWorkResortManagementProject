import java.io.BufferedWriter;
import java.util.Scanner;

public class TicketOffice {
    SortedArrayList<Customer> customers;
    SortedArrayList<Activity> activities;
    static Scanner sc = new Scanner(System.in);
    // this method will  print all the customer
    public TicketOffice(SortedArrayList<Customer> customers ,SortedArrayList<Activity> activities)
    {
        this.customers= customers;
        this.activities=activities;
    }
    // this method will  print all the customer

    public static void  informationAboutAllCustomer(SortedArrayList<Customer>customers){
        for(Customer customer: customers){
            customer.getFullName();
            customer.printChoosenActivities();
        }
    }
    // this method will  print all the activites

    public static void informationAboutAllActivities(SortedArrayList<Activity> activities){
        for(Activity activity : activities){
            System.out.println(activity.getActivityName()+" "+activity.getNoOfTicket());
        }
    }

    public void bookTicket(BufferedWriter f, String clerkName) // this method will the book a ticket for a person
    {
        System.out.println("Enter Your First Name");
        String firstName = sc.next();
        System.out.println("Enter Your LastName");
        String lastName=sc.next();
        if(checkValidCustomer(firstName, lastName)){
            sc.nextLine();
            System.out.println("Enter the name of activity whose  you want to buy");
            String activityName = sc.nextLine();
            if(checkValidActivity(activityName)){
                System.out.println("Enter the number of ticket you want ot buy for this activity");
                int noOfTicket = sc.nextInt();
                if(ticketAvailiability(activityName,noOfTicket))
                {
                   updateDataAfterBooking(firstName,lastName,activityName,noOfTicket);
                }
                else
                {
                   try {
                       f.write("Dear \n");
                       f.write(firstName + " " + lastName);
                       f.write(",");
                       f.write("Sorry there are no such number of ticket aviliable for this activity right now Please connect after sometime.\n");
                       f.write("Yours sincerely, \n");
                       f.write(clerkName);
                       f.flush();
                       f.close();
                   }
                   catch(Exception e){
                       e.getStackTrace();
                   }

                }
            }
            else{
                System.out.println("Such Activity does not exist");
            }
        }
        else{
            System.out.println("Sorry you are not a valid customer");
        }
    }
    public void cancelTicket()
    {
        System.out.println("Enter Your First Name");
        String firstName = sc.next();
        System.out.println("Enter Your LastName");
        String lastName=sc.next();
        if(checkValidCustomer(firstName, lastName)){
            sc.nextLine();
            System.out.println("Enter the name of activity whose  you want to cancel");
            String activityName = sc.nextLine();
            if( checkCustomerHasTicketThisActivity(firstName,lastName,activityName)){
                System.out.println("Enter the number of ticket you want ot cancel");
                int noOfTicket = sc.nextInt();
                updateDataAfterCancelTicket(firstName,lastName,activityName,noOfTicket);
            }
            else{
                System.out.println("Sorry /n You can't cancel ticket for this activity because you had not  bought any  ticket for this activity  so far/n thankyou");
            }
        }
        else{
            System.out.println("Sorry \n Your are not our registered customer \n thank you !!");
        }
    }

    // it will check whether the cust
    public boolean checkCustomerHasTicketThisActivity(String firstname , String lastName,String activityName){
        for(Customer customer : customers){
            if(customer.getFirstName().equalsIgnoreCase(firstname) && customer.getSurname().equalsIgnoreCase(lastName)){
                return customer.hasActivitity(customer.proper(activityName));
            }
        }
        return false;
    } //

     // this will uodate the data
    public void updateDataAfterBooking(String customerName , String customerLastName,String activity,int noOfticket){
            for(Customer customer:customers){
                if(customer.getFirstName().equalsIgnoreCase(customerName) && customer.getSurname().equalsIgnoreCase(customerLastName)){
                    customer.addActivity(customer.proper(activity),noOfticket);
                }
            }
            for(Activity activity1:activities){
                if(activity1.getActivityName().equalsIgnoreCase(activity)){
                    activity1.setNoOfTicket((activity1.getNoOfTicket()-noOfticket));
                }
            }
            System.out.println("Ticket has been booked successfully ");
    }

    public void updateDataAfterCancelTicket(String customerName , String customerLastName,String activity,int noOfTicket){
        int ticket=0;
        for(Customer customer:customers){
            if(customer.getFirstName().equalsIgnoreCase(customerName) && customer.getSurname().equalsIgnoreCase(customerLastName)){

                if(customer.hasActivitity(customer.proper(activity))) {
                   ticket= customer.getNoOfTicket(customer.proper(activity));
                   if(ticket<noOfTicket){
                       System.out.println("You have "+ticket+" number of ticket for this activity but you want to cancel "+noOfTicket+" number of ticket so your request is no accepted ");
                        return;
                   }
                   else if(ticket==noOfTicket){
                       customer.removeActivity(customer.proper(activity));
                   }
                   else if(ticket>noOfTicket){
                       int left = ticket-noOfTicket;
                       customer.removeActivity(customer.proper(activity));
                       customer.addActivity(customer.proper(activity),left);
                   }
                }
            }
        }
        System.out.println("Ticket has been cancelled successfully!!");

        for(Activity activity1:activities){
            if(activity1.getActivityName().equalsIgnoreCase(activity)){
                activity1.setNoOfTicket(activity1.getNoOfTicket()+noOfTicket);
            }
        }
    }
    public boolean ticketAvailiability(String activityName,int noOfTicket){
        for(Activity activity : activities){
            if(activity.getActivityName().equalsIgnoreCase(activityName)){
                if(activity.getNoOfTicket()>= noOfTicket){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;

    }
    public boolean checkValidCustomer(String firstName,String lastName ){
        for(Customer customer : customers){
            if(customer.getFirstName().equalsIgnoreCase(firstName) && customer.getSurname().equalsIgnoreCase(lastName)){
                return true;
            }
        }
        return false;
    }
    public boolean checkValidActivity(String activity){
        for(Activity activity1: activities){
            if(activity1.getActivityName().equalsIgnoreCase(activity))  {
                return true;
            }
        }
        return false;
    }



}
