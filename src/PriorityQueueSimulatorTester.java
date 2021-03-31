import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
public class PriorityQueueSimulatorTester {
    static Random rng=new Random();

    /**
     * Creates arrays filled with 1000 and 10,000 jobs randomly generated and outputs the results to a file "SimulatorPerformanceResults.txt" with
     * runtime information.
     * @param args
     */
    public static void main(String args[])
    {
        int maxNumberOfJobs;
        maxNumberOfJobs=1000;
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream("SimulatorPerformanceResults.txt", false));
            ps = new PrintStream(new FileOutputStream("SimulatorPerformanceResults.txt", true));

        } catch (Exception e) {
            System.out.println("File could not open; Terminating program.");
            System.exit(0);
        }

        for(maxNumberOfJobs=1000;maxNumberOfJobs<=10000;maxNumberOfJobs=maxNumberOfJobs*10) {
            JobsHeap pq=new JobsHeap();
            Job[] jobsInputArray=new Job[maxNumberOfJobs];
            Job[] jobsInputArray1=new Job[maxNumberOfJobs];
            String jn;
            int jobsexecuted=0;
            double avgwait=0;
            int prichanges=0;
            int jl;
            int jp;
            int et,wt,ent;
            int currenttime=0;
            long cycle=0;
            int starvecount=0;
            for (int i = 0; i < maxNumberOfJobs; i++) {
                jn = "JOB_" + (i + 1);
                jl = rng.nextInt(70) + 1;
                jp = rng.nextInt(40) + 1;
                et = 0;
                wt = 0;
                ent = 0;
                jobsInputArray[i] = new Job(jn, jl, jl, jp, jp, et, ent, wt);
                jobsInputArray1[i] = new Job(jn, jl, jl, jp, jp, et, ent, wt);
            }

            for (int i = 0; i < maxNumberOfJobs; i++) {
                jobsInputArray[i].setEntryTime(i + 1);
                pq.add(jobsInputArray[i]);
                currenttime++;
            }
            long start = System.nanoTime();
            while (pq.jobarray.size() != 0) {
                Job currentJob;
                cycle++;
                starvecount++;
                currenttime++;
                if (starvecount == 30) {
                    pq.fixStarved();
                    starvecount = 0;
                    prichanges++;
                }
                currentJob = pq.popFirst();
                currentJob.setCurrentJobLength(currentJob.getCurrentJobLength() - 1);
                System.out.println("Now executing " + currentJob.getJobName() + ". Job Length: " + currentJob.getJobLength() + " cycles. Current remaining Job Length: " + currentJob.getCurrentJobLength() + ". Initial Priority: " + currentJob.getJobPriority() + ". Current Priority: " + currentJob.getFinalPriority() + ".");

                if (currentJob.getCurrentJobLength() == 0) {
                    currentJob.setEndTime(currenttime);
                    currentJob.setWaitTime(currenttime - currentJob.getEntryTime() - currentJob.getJobLength());
                    jobsexecuted++;
                    avgwait += currentJob.getWaitTime();
                } else {
                    pq.add(currentJob);
                }
            }
            long end = System.nanoTime();
            avgwait /= jobsexecuted;

            ps.append("Heap as an ArrayList:-\n");
            ps.append("Current system time (cycles): " + cycle + "\nTotal number of jobs executed: " + jobsexecuted + " jobs\nAverage process waiting time: " + avgwait + " cycles\nTotal number of priority changes: " + prichanges + "\nActual system time needed to execute all jobs: " + ((end - start) / 1e+6)+"\n\n\n");
            System.out.println("\n\nHeap Done, now LinkedList\n\n");
             jn="";
             jobsexecuted=0;
             avgwait=0;
             prichanges=0;
             jl=0;
             jp=0;
             et=0;wt=0;ent=0;
             currenttime=0;
             cycle=0;
             starvecount=0;
            PQSortedList pq1=new PQSortedList();

            for (int i = 0; i < maxNumberOfJobs; i++) {
                jobsInputArray1[i].setEntryTime(i + 1);
                pq1.add(jobsInputArray1[i]);
                currenttime++;
            }
             start = System.nanoTime();
            while (pq1.head != null) {
                Job currentJob;
                cycle++;
                starvecount++;
                currenttime++;
                if (starvecount == 30) {
                    pq1.fixStarved();
                    starvecount = 0;
                    prichanges++;
                }
                currentJob = pq1.popFirst();
                int curpr=currentJob.getCurrentJobLength();
                if(curpr>0){
                currentJob.setCurrentJobLength(--curpr);
                }
                System.out.println("Now executing " + currentJob.getJobName() + ". Job Length: " + currentJob.getJobLength() + " cycles. Current remaining Job Length: " + currentJob.getCurrentJobLength() + ". Initial Priority: " + currentJob.getJobPriority() + ". Current Priority: " + currentJob.getFinalPriority() + ".");
                if (currentJob.getCurrentJobLength() == 0) {
                    currentJob.setEndTime(currenttime);
                    currentJob.setWaitTime(currenttime - currentJob.getEntryTime() - currentJob.getJobLength());
                    jobsexecuted++;
                    avgwait += currentJob.getWaitTime();
                } else {
                    pq1.add(currentJob);
                }
            }
             end = System.nanoTime();
            avgwait /= jobsexecuted;

            ps.append("Priority Queue as an LinkedList:-\n");
            ps.append("Current system time (cycles): " + cycle + "\nTotal number of jobs executed: " + jobsexecuted + " jobs\nAverage process waiting time: " + avgwait + " cycles\nTotal number of priority changes: " + prichanges + "\nActual system time needed to execute all jobs: " + ((end - start) / 1e+6)+"\n\n\n");
        }
        ps.close();
    }

}