//import java.lang.*;
import java.util.*;

public class Roundrobin{
    public static void main(String args[]){
        int n;
         System.out.println("enter number of processes:");
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
       
        int process[]=new int[n];
        int at[]=new int[n];
        int et[]=new int[n];
        int temp_et[]=new int[n];
        int ct[]=new int[n];
        int tat[]=new int[n];
        int wt[]=new int[n];
        for(int i=0;i<n;i++)
        {
            process[i]=i+1;
            System.out.println("enter arrival time for process "+(i+1));
            at[i]=sc.nextInt();
            System.out.println("enter execution time for process "+(i+1));
            et[i]=sc.nextInt();
            temp_et[i]=et[i];
        }
        
        int completed_process=0;
        int current_time=0;
        int q;
        System.out.println("enter quantum time:");
        q=sc.nextInt();
        while(completed_process<n){
            boolean processfound=false;
            for(int i=0;i<n;i++){
                if(at[i]<=current_time && temp_et[i]>0){
                    processfound=true;
                    int executionTime=Math.min(temp_et[i],q);
                    temp_et[i]=temp_et[i]-executionTime;
                    current_time=current_time+executionTime;
                    
                    if(temp_et[i]==0){
                        ct[i]=current_time;
                        tat[i]=ct[i]-at[i];
                        wt[i]=tat[i]-et[i];
                        completed_process++;
                    }
                }
            }
            if(!processfound){
                current_time++;
            }
        }
        System.out.println("process"+"\t"+"arrival time"+"\t"+"execution time"+"\t"+"completion time"+"\t"+"turnaround time"+"\t"+"waiting time");
        for(int i=0;i<n;i++){
            System.out.println((i+1)+"\t"+at[i]+"\t"+et[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
    }
    int total_tat=0;
    float avg_tat;
    int total_wt=0;
    float avg_wt;
    for(int i=0;i<n;i++){
        total_tat=total_tat+tat[i];
        total_wt=total_wt+wt[i];
    }
        avg_tat=total_tat/n;
        avg_wt=total_wt/n;
        System.out.println("average turnaround tim is:"+avg_tat);
        System.out.println("average waiting time is:"+avg_wt);
        
    }
}