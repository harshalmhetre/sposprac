//import java.lang.*;
import java.util.*;

public class fcfs{
      public static void main(String args[]){
          int process[]=new int[10];
          int at[]=new int[10];
          int et[]=new int[10];
          int ct[]=new int[10];
          int tat[]=new int[10];
          int wt[]=new int[10];
          int n;
          int temp;
          //int index=0;
          int tot_tat=0,tot_wt=0;
          float avg_tat,avg_wt;
          Scanner sc=new Scanner(System.in);
          System.out.println("enter the number of processes:");
          n=sc.nextInt();
          for(int i=0;i<n;i++)
          {
              process[i]=i+1;
              System.out.println("enter arrival time for process"+(i+1));
              at[i]=sc.nextInt();
              System.out.println("enter execution time for process"+(i+1));
              et[i]=sc.nextInt();
          }
          for(int i=0;i<n;i++){
              for(int j=0;j<n-(i+1);j++){
                if(at[j]>at[j+1]){
                    temp=at[j+1];
                    at[j+1]=at[j];
                    at[j]=temp;
                    temp=et[j+1];
                    et[j+1]=et[j];
                    et[j]=temp;
                    temp=process[j+1];
                    process[j+1]=process[j];
                    process[j]=temp;
                }
              }
          }
          
          for(int i=0;i<n;i++){
              if(i==0){
              ct[i]=at[i]+et[i];
              }
              else{
                  if(at[i]>ct[i-1])
                  {
                      ct[i]=at[i]+et[i];
                      }
                      else{
                          ct[i]=ct[i-1]+et[i];
                      }
              }
              tat[i]=ct[i]-at[i];
              wt[i]=tat[i]-et[i];
          }
          for(int i=0;i<n;i++){
              tot_tat=tot_tat+tat[i];
              tot_wt=tot_wt+wt[i];
          }
          avg_tat=tot_tat/n;
          avg_wt=tot_wt/n;
          System.out.println("Process/tarrival time/texecution time/tcompletion time/ttat/twaiting time");
          for(int i=0;i<n;i++){
              System.out.println(process[i]+"\t"+at[i]+"\t"+et[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
          }
          System.out.println("average turnaround time is:"+avg_tat);
          System.out.println("average waiting time is:"+avg_wt);
          
      }
}