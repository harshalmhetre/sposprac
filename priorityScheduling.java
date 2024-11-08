import java.util.Scanner;

public class priorityScheduling {
    public static void main(String[] args) {
        int process[] = new int[10];
        int at[] = new int[10];
        int bt[] = new int[10];
        int ct[] = new int[10];
        int tat[] = new int[10];
        int wt[] = new int[10];
        int priority[] = new int[10];
        int f[] = new int[10];
        int n;
        int tot = 0, st = 0;
        int tot_tat = 0, tot_wt = 0;
        float avg_tat, avg_wt;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the number of processes:");
        n = sc.nextInt();
        
        for (int i = 0; i < n; i++) {
            process[i] = i + 1;
            f[i] = 0;
            System.out.println("Enter arrival time for process " + (i + 1) + ":");
            at[i] = sc.nextInt();
            System.out.println("Enter burst time for process " + (i + 1) + ":");
            bt[i] = sc.nextInt();
            System.out.println("Enter priority for process " + (i + 1) + ":");
            priority[i] = sc.nextInt();
        }
        
        while (true) {
            int minPriority = Integer.MAX_VALUE, c = n;
            if (tot == n) {
                break;
            }
            
            for (int i = 0; i < n; i++) {
                if ((at[i] <= st) && (f[i] == 0) && (priority[i] < minPriority)) {
                    minPriority = priority[i];
                    c = i;
                }
            }
            
            if (c == n) {
                st++;
            } else {
                st += bt[c];
                ct[c] = st;
                tat[c] = ct[c] - at[c];
                wt[c] = tat[c] - bt[c];
                f[c] = 1;
                tot++;
            }
        }
        
        for (int i = 0; i < n; i++) {
            tot_tat += tat[i];
            tot_wt += wt[i];
        }
        
        avg_tat = (float) tot_tat / n;
        avg_wt = (float) tot_wt / n;
        
        System.out.println("Process\tArrival Time\tBurst Time\tPriority\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (int i = 0; i < n; i++) {
            System.out.println(process[i] + "\t" + at[i] + "\t\t" + bt[i] + "\t\t" + priority[i] + "\t\t" + ct[i] + "\t\t" + tat[i] + "\t\t" + wt[i]);
        }
        
        System.out.println("Average Turnaround Time is: " + avg_tat);
        System.out.println("Average Waiting Time is: " + avg_wt);
    }
}
