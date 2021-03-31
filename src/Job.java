public class Job {
    private String jobName;
    private int jobLength;
    private int currentJobLength;
    private int jobPriority;
    private int finalPriority;
    private long entryTime;
    private long endTime;
    private long waitTime;
    private int repCount=0;

    public int getRepCount() {
        return repCount;
    }

    public void setRepCount(int repCount) {
        this.repCount = repCount;
    }

    public int getEntryIndex() {
        return entryIndex;
    }

    public void setEntryIndex(int entryIndex) {
        this.entryIndex = entryIndex;
    }

    private int entryIndex;
    public String getJobName() {
        return jobName;
    }

    public int getJobLength() {
        return jobLength;
    }

    public int getCurrentJobLength() {
        return currentJobLength;
    }

    public int getJobPriority() {
        return jobPriority;
    }

    public int getFinalPriority() {
        return finalPriority;
    }
    public void setFinalPriority(int n) {
        finalPriority=n;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public Job(String jobName, int jobLength, int currentJobLength, int jobPriority, int finalPriority, long entryTime, long endTime, long waitTime) {
        this.jobName = jobName;
        this.jobLength = jobLength;
        this.currentJobLength = currentJobLength;
        this.jobPriority = jobPriority;
        this.finalPriority = finalPriority;
        this.entryTime = entryTime;
        this.endTime = endTime;
        this.waitTime = waitTime;

    }
    public Job(int curpr,int finpr)
    {
        this.jobPriority=curpr;
        this.finalPriority=finpr;
    }

    public void setCurrentJobLength(int currentJobLength) {
        this.currentJobLength = currentJobLength;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setJobLength(int jobLength) {
        this.jobLength = jobLength;
    }

    public void setJobPriority(int jobPriority) {
        this.jobPriority = jobPriority;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

    public Job getNew()
    {
        return new Job(this.jobName,this.jobLength,this.currentJobLength,this.jobPriority,this.finalPriority,this.entryTime,this.endTime,this.waitTime);
    }
}
