

import java.util.*;

public class Test
{
    //Scanner sc=new Scanner(System.in);
    static JobsHeap pq;
    public static void main(String args[])
    {
        pq=new JobsHeap();
        Job a=new Job(5,1);
        Job b=new Job(9,2);
        Job c=new Job(1,3);
        Job d=new Job(2,4);
        Job e=new Job(3,5);
        Job f=new Job(4,6);
        Job g=new Job(5,7);
        Job h=new Job(2,8);
        Job i=new Job(1,8);
        Job j=new Job(99,8);
        pq.add(a);
        pq.add(b);
        pq.add(c);
        pq.add(d);
        pq.add(e);
        pq.add(f);
        pq.add(g);
        pq.add(h);
        pq.add(j);
        pq.add(i);


        for(int i1=0;i1<pq.jobarray.size();i1++)
        {
            System.out.print(i1+"\t");
        }
        System.out.println();
        for(int i1=0;i1<pq.jobarray.size();i1++)
        {
            System.out.print(pq.jobarray.get(i1).getFinalPriority()+"\t");
        }
        System.out.println();
        Job[] sortedarr=new Job[10];
        for (int cou=0;cou<sortedarr.length;cou++)
        {
            sortedarr[cou]=pq.popFirst();
            System.out.print(sortedarr[cou].getFinalPriority()+" ");
            System.out.print(sortedarr[cou].getJobPriority()+"\t\t");
        }
        System.out.println();


    }
}
