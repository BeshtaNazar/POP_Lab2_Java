public class ArrClass {
    private final int dim;
    private final int threadNum;
    public final int[] arr;
    public int minElem;
    public IndexValueOfMin [] indexValue;

    public ArrClass(int dim, int threadNum) {
        this.dim = dim;        
        this.threadNum = threadNum;
        arr = new int[dim];        
        for(int i = 0; i < dim; i++){
            arr[i] = i;          
        }
        arr[dim/4]=-10;    
        minElem=dim; 
        indexValue= new IndexValueOfMin[threadNum];
        for(int i = 0; i < threadNum; i++){
            indexValue[i]=new IndexValueOfMin();
        }
    }

    public int partMin(int id, int startIndex, int finishIndex){
        int min = arr[startIndex];
        indexValue[id].index=startIndex;
        indexValue[id].value=min;
        for(int i = startIndex; i < finishIndex; i++){
            if(min>arr[i])
            {
                min=arr[i];
                indexValue[id].index=i;
                indexValue[id].value=min;
            }
        }
        return min;
    }     

    synchronized private int getMin() {
        while (getThreadCount()<threadNum){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return minElem;
    }

    synchronized public void checkNum(int num){
        if(minElem>num){
            minElem=num;            
        }
    }

    private int threadCount = 0;
    synchronized public void incThreadCount(){
        threadCount++;
        notify();
    }

    private int getThreadCount() {
        return threadCount;
    }

    public int threadSum(){
        ThreadSum[] threadSums = new ThreadSum[threadNum];
        int divideNumOfThreads=dim/threadNum;       

        for (int i = 0; i < threadSums.length; i++) {
            if((i+1)!=threadSums.length)
            {
                threadSums[i] = new ThreadSum(i,divideNumOfThreads*i, divideNumOfThreads*(i+1), this);
            }
            else
            {
                threadSums[i] = new ThreadSum(i,divideNumOfThreads*i, dim, this);
            }
        }
        
        for (int i = 0; i < threadSums.length; i++) {
            threadSums[i].start();
        }

        return getMin();
    }
}