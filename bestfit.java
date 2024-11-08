import java.lang.*;
import java.util.*;

public class bestfit{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n,m;
        System.out.println("enter number of blocks:");
        n=sc.nextInt();
        int blockSize[]=new int[n];
        int originalBlockSize[]=new int[n];
        for(int i=0;i<n;i++){
            System.out.println("enter memory size:");
            blockSize[i]=sc.nextInt();
            originalBlockSize[i]=blockSize[i];
        }
        System.out.println("enter number of processes:");
        m=sc.nextInt();
        int processSize[]=new int[m];
        for(int i=0;i<m;i++){
            System.out.println("enter process size:");
            processSize[i]=sc.nextInt();
        }
        implementFirstFit(blockSize,originalBlockSize,processSize);
    }
    static void implementFirstFit(int blockSize[],int originalBlockSize[],int processSize[])
    {
        int allocation[]=new int[processSize.length];
        for(int i=0;i<processSize.length;i++)
        {
            allocation[i]=-1;
        }
        for(int i=0;i<processSize.length;i++)
        {
            int bestIndex=-1;
            for(int j=0;j<blockSize.length;j++)
            {
                if(processSize[i]<=blockSize[j])
                {
                    if(bestIndex==-1 || blockSize[j]<blockSize[bestIndex])
                    {
                        bestIndex=j;
                    }
                }   
                
            }
            if(bestIndex!=-1)
            {
                    allocation[i]=bestIndex;
                    blockSize[bestIndex]=blockSize[bestIndex]-processSize[i];
            }
        }
        System.out.println("process number"+"\t"+"process size"+"\t"+"allocated block");
        for(int i=0;i<processSize.length;i++)
        {
            System.out.print((i+1)+"\t\t"+processSize[i]+"\t\t");
            if(allocation[i]!=-1)
            {
                System.out.println(originalBlockSize[allocation[i]]);
            }
            else
            {
                System.out.println("not allocated");
            }
        }
    }
}
