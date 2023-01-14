import java.util.HashMap;

public class Customer  implements Comparable<Customer>{
    private String firstName; // it will store the first name of customer
    private String surname; // it will store the surname of the customer
    private String activityName; // it will store the name of the activty related to customer
    private int noOfTicket; // it will store the number of ticket per activity
    private HashMap<String,Integer> chosenActivities = new HashMap<>(); // it will store the activities and no of ticket related to each activity

    public Customer(String firstName,String surname){ // constructor of customer class

        this.firstName= firstName;
       this. surname = surname;
    }
    // creating a method which will set the  first and surname of a customer
    public void setName(String name1,String name2){
        firstName=name1;
        surname=name2;
    }
    public String getFirstName(){ // it will return the value of first name of customer

        return firstName;
    }
    public String getSurname(){ // it will return the value the surname of a customer
        return surname;
    }
    public void getFullName(){ // it will return the full name of the customer
        System.out.println(firstName+" "+surname);
    }
    // it will comoare two object

    public boolean equals(Customer otherCustomer){
        return (firstName.equals(otherCustomer));
    }
    public void  printChoosenActivities(){// it will print all the activities
        for(HashMap.Entry m : chosenActivities.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
    }
    public void setActivityAndTicket(String activityName,int noOfTicket){ // it will set the activity and no of ticket related to the activity
        this.activityName= activityName;
        this.noOfTicket = noOfTicket;
    }
    public void addActivity(String activityName,int noOfTicket) // it will add activity name and no of ticket in hashmap
    {
        if(chosenActivities.containsKey(activityName)){
            int value = chosenActivities.get(activityName);
            chosenActivities.put(activityName,noOfTicket+value);
        }
        else {
            chosenActivities.put(activityName, noOfTicket);
        }
    }
    public void removeActivity(String activityName){ // it will the activity when customer cacelled any ticket
        chosenActivities.remove(activityName);
    }
    public int getNoOfTicket(String activity){
        return chosenActivities.get(activity);
    }
    public boolean hasActivitity(String activity){
        return chosenActivities.containsKey(activity);
    }


    @Override
    public int compareTo(Customer customer) {
        int lnCmp = surname.compareTo(customer.surname);
        if (lnCmp !=0) return lnCmp;
        int fnCmp = firstName.compareTo(customer.firstName);
        if (fnCmp != 0) return fnCmp;
        else return 1;

    }
    // this method  will set the first letter of the string in to complete
    public String proper(String value){
        String values[]= value.split(" ");
        String ans ="";
        for(int i=0;i<values.length;i++){
            char ch  = values[i].charAt(0);
            ans += String.valueOf(ch).toUpperCase() + values[i].substring(1)+" ";

        }
        return  ans;
    }
    // it will return the number of ticket corresponding to a activity
    public int getNoOfTicketCorresponding(String activityName){
        return chosenActivities.get(activityName);

    }


}
