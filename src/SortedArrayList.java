import java.util.ArrayList;

/* Creating a SortedArrayList which extends ArrayList Class */
public class SortedArrayList<E> extends ArrayList<E>{

    private static final long serialVersionUID = 1L;

    public SortedArrayList(){

        super();
    }
      // creating a insert method which will insert the element in SortedArrayList class in sorted manner
    public void insert(E value){
        super.add(value);
        if(super.size()>0){
            for (int x=0; x<super.size(); x++) // bubble sort outer loop
            {
                for (int i=0; i < super.size()-x-1; i++) {
                    Comparable<E> cmp = (Comparable<E>) super.get(i);

                    if (cmp.compareTo(super.get(i+1)) > 0)
                    {
                        E tempname = super.get(i);
                        super.set(i,super.get(i+1));
                        super.set(i+1, tempname);
                    }
                }
            }


        }
    }


}
