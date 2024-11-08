//import java.lang.*;
import java.util.*;

public class srjf{
      public static void main(String args[]){
          int process[]=new int[10];
          int at[]=new int[10];
          int et[]=new int[10];
          int ct[]=new int[10];
          int tat[]=new int[10];
          int wt[]=new int[10];
          int f[]=new int[10];
          int k[]=new int[10];
          int n;
          //int temp;
          int tot=0,st=0;
          int tot_tat=0,tot_wt=0;
          float avg_tat,avg_wt;
          Scanner sc=new Scanner(System.in);
          System.out.println("enter the number of processes:");
          n=sc.nextInt();
          for(int i=0;i<n;i++)
          {
              process[i]=i+1;
              f[i]=0;
              System.out.println("enter arrival time for process"+(i+1));
              at[i]=sc.nextInt();
              System.out.println("enter execution time for process"+(i+1));
              et[i]=sc.nextInt();
              k[i]=et[i];
          }
          
          while(true){
              int min=999,c=n;
              if(tot==n){
                break;   
              }
              for(int i=0;i<n;i++)
              {
                if((at[i]<=st)&&(f[i]==0)&&(et[i]<min))
                {
                    min=et[i];
                    c=i;
                }
             }
             if(c==n){
                 st++;
             }
             else{
                 et[c]--;
                 st++;
                 if(et[c]==0){
                     ct[c]=st;
                     f[c]=1;
                     tot++;
                 }
             }
          }
          for(int i=0;i<n;i++){
              tat[i]=ct[i]-at[i];
              wt[i]=tat[i]-k[i];
          }
          for(int i=0;i<n;i++){
              tot_tat=tot_tat+tat[i];
              tot_wt=tot_wt+wt[i];
          }
          avg_tat=tot_tat/n;
          avg_wt=tot_wt/n;
          System.out.println("Process/tarrival time/texecution time/tcompletion time/ttat/twaiting time");
          for(int i=0;i<n;i++){
              System.out.println(process[i]+"\t"+at[i]+"\t"+k[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
          }
          System.out.println("average turnaround time is:"+avg_tat);
          System.out.println("average waiting time is:"+avg_wt);
          
      }
}