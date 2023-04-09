import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int dim = 10000000;        
        Scanner scan =new Scanner(System.in);
        System.out.println("Enter number of threads: ");
        int threadNum = scan.nextInt();
        ArrClass arrClass = new ArrClass(dim, threadNum);
        /* System.out.println(arrClass.partSum(0,dim)); */
        
        arrClass.threadSum();
        for(int i = 0; i<threadNum;i++)
        {
            if(arrClass.minElem==arrClass.indexValue[i].value)
            System.out.println("index: " + arrClass.indexValue[i].index + " value: " + arrClass.minElem);
        }        
    }
}