/**
 * This is the Data Structure with the Priority Queue as a one-way linked list
 */
public class PQSortedList {
    JobNode head=null;
    JobNode tail=null;
    public class JobNode {
        Job thisJob;
        JobNode next;

        JobNode()
        {
            thisJob=null;
            next=null;
        }

        JobNode(Job s, JobNode sn)
        {
            thisJob=s.getNew();
            if(sn==null)
            {
                next=null;
            }
            else {
                next=sn;
            }

        }

        JobNode(JobNode jn)
        {
            thisJob=jn.thisJob.getNew();
            next=jn.next;
        }
    }

    /**
     * Adds a node after the last node of the same priority
     * @param j
     */
    public void add(Job j)
    {   int reps=0;
        if(head==null)
        {
            JobNode jn=new JobNode(j,null);
            head=jn;

        }
        else
        {
            JobNode tn,temp,tracker;
            tn=head;
            tracker=head;
            if(head.thisJob.getFinalPriority()>j.getFinalPriority())
            {
                JobNode jn= new JobNode(j,head);
                head=jn;
            }
            else {
                while ((tn != null) && (tn.thisJob.getFinalPriority() <= j.getFinalPriority())) {

                    if (tn.thisJob.getFinalPriority() == j.getFinalPriority()) {
                        reps = 1;
                        while (tn.thisJob.getFinalPriority() == j.getFinalPriority()) {
                            if (tn.thisJob.getRepCount() == 0) {
                                tn.thisJob.setRepCount(1);
                            }
                            reps++;
                            tracker = tn;
                            tn = tn.next;
                            if (tn == null) {
                                break;
                            }
                        }
                    } else {

                        tracker = tn;
                        tn = tn.next;
                    }
                }

                if (tn == null) {
                    tracker.next = new JobNode(j, null);

                } else {
                    temp = tracker.next;
                    tracker.next = new JobNode(j, temp);
                    tracker.next.thisJob.setRepCount(reps);
                }
            }

        }
    }
    public Job popFirst()
    {
        Job tmp=head.thisJob;
        head=head.next;
        return tmp;

    }

    /**
     * searches and removes a passed node from the PQ
     * @param d
     */
    public void removeThis(JobNode d)
    {
        JobNode tempnode=head;
        JobNode prevnode=null;
        while(tempnode!=null)
        {

            if(tempnode==d)
            {
                if(tempnode==head)
                {
                    head=head.next;
                }
                else
                {
                    prevnode.next=tempnode.next;
                }
            }
            prevnode=tempnode;
            tempnode=tempnode.next;
        }
    }

    /**
     * Method to find a starved job and make it list head(priority=0)
     */
    public void fixStarved()
    {
        JobNode tn=head;
        JobNode lowest=head;
        while(tn!=null)
        {
            if((tn.thisJob.getJobLength()==tn.thisJob.getCurrentJobLength())&&(tn.thisJob.getEntryTime()<lowest.thisJob.getEntryTime()))
            {
                lowest=tn;
            }
            tn=tn.next;
        }
        JobNode temp=lowest;
        temp.thisJob.setFinalPriority(1);
        removeThis(lowest);
        JobNode newhead=new JobNode(lowest.thisJob,head);
        head=newhead;
    }
    public void showListContents()
    {
        JobNode tn=head;
        while(tn!=null)
        {
            System.out.print(tn.thisJob.getJobPriority()+"__"+tn.thisJob.getFinalPriority()+"("+tn.thisJob.getRepCount()+")"+" ==> \t");
            tn=tn.next;
        }
        System.out.println("X");
    }


}
