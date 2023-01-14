public class Activity implements Comparable<Activity>{
    private String activityName;
    private int noOfTicket;
    Activity(String name , int ticket){
        activityName=name;
        noOfTicket = ticket;
    }
    // it will set   the  name of the activity
    public void setActivityName(String name){
        activityName = name;
    }
    // it will the set the number  of the ticket  activity
    public void setNoOfTicket(int ticket){
        noOfTicket = ticket;
    }
    public String getActivityName(){// it will return the activity name
        return activityName;
    }
    public int getNoOfTicket(){
        return noOfTicket;
    } // it will get no of ticket

    public int compareTo(Activity activity){
        return activityName.compareTo(activity.activityName);

    }
}
