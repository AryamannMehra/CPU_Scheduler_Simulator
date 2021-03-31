import java.util.ArrayList;

/**
 * This class represents the Priority Queue as an ArrayList based Heap
 */
public class JobsHeap {


    public ArrayList<Job> jobarray=new ArrayList<Job>();//actually holds the Jobs


    /**
     * Adds the job passed to the Heap of jobs starting at the end and swapping till heap properties are satisfied
     * @param j
     */
    public void add(Job j) {

        jobarray.add(j);


            int toinsert = jobarray.size() ;
            int index = toinsert-1;
            int parentindex;
            int finalpos=0;
            while (index > 0) {
                parentindex = (index - 1) / 2;

                if (jobarray.get(parentindex).getFinalPriority() > jobarray.get(index).getFinalPriority()) {
                    Job temp = jobarray.get(parentindex);
                    jobarray.set(parentindex, jobarray.get(index));
                    jobarray.set(index, temp);
                    finalpos=index;
                    index = parentindex;
                } else {
                    break;
                }
            }


    }

    /**
     * Recursive method takes the arraylist as a parameter and in turn arranges all its elements in heap order.
     * @param jobArrayList
     * @param ind
     * @param par
     */
    public void heapify(ArrayList<Job> jobArrayList,int ind, int par) {
        int size = jobArrayList.size();
        int parent = par;
        int lc = 2 * parent + 1;
        int rc = 2 * parent + 2;
        if ((lc < ind) && (jobArrayList.get(lc).getFinalPriority() < jobArrayList.get(parent).getFinalPriority())) {
            parent = lc;
        }
        if ((rc < ind) && (jobArrayList.get(rc).getFinalPriority() < jobArrayList.get(parent).getFinalPriority())) {
            parent = rc;
        }
        if (parent != par)
        {
            Job temp=jobArrayList.get(parent);
            jobArrayList.set(parent,jobArrayList.get(par));
            jobArrayList.set(par,temp);
            heapify(jobArrayList,ind,parent);
        }
        else
        {
            return;
        }
    }

    /**
     * Removes first Job from the heap and returns it
     * @return
     */
    public Job popFirst()
    {
        int indtofront=jobarray.size()-1;
        Job toFront=jobarray.get(indtofront);
        Job toret=jobarray.get(0);
        jobarray.set(0,toFront);
        jobarray.remove(indtofront);
        heapify(jobarray,indtofront,0);
        return toret;
    }

    /**
     * fixes any Starved jobs(jobs that have lower priorities than current chain will still recieve execution time on the cpu to ensure no job is starved
     */
    public void fixStarved()
    {
        int lowpoint=0;
        int lowest=0;
        for(int counter=0;counter<jobarray.size();counter++)
        {
            if((jobarray.get(counter).getJobLength()==jobarray.get(counter).getCurrentJobLength())&&(jobarray.get(counter).getEntryTime()<jobarray.get(lowest).getEntryTime()))
            {
                lowest=counter;
            }
        }
        Job templow=jobarray.get(lowest);
        templow.setFinalPriority(1);
        jobarray.remove(lowest);
        heapify(jobarray,lowest,0);
        jobarray.add(templow);

    }
}
