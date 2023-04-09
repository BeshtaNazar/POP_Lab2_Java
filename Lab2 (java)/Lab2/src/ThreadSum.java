public class ThreadSum extends Thread{
    private final int startIndex;
    private final int finishIndex;
    private final ArrClass arrClass;
    private int Id;

    public ThreadSum(int id, int startIndex, int finishIndex, ArrClass arrClass) {
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
        this.arrClass = arrClass;
        this.Id=id;
    }

    @Override
    public void run() {
        int num= arrClass.partMin(Id, startIndex, finishIndex);
        arrClass.checkNum(num);
        arrClass.incThreadCount(); 
                
    }
}