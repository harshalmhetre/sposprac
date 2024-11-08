import java.util.Scanner;

public class nextfit {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n, m;
        
        System.out.println("Enter number of blocks:");
        n = sc.nextInt();
        int blockSize[] = new int[n];
        int originalBlockSize[] = new int[n];
        
        for (int i = 0; i < n; i++) {
            System.out.println("Enter memory size for block " + (i + 1) + ":");
            blockSize[i] = sc.nextInt();
            originalBlockSize[i] = blockSize[i];
        }
        
        System.out.println("Enter number of processes:");
        m = sc.nextInt();
        int processSize[] = new int[m];
        
        for (int i = 0; i < m; i++) {
            System.out.println("Enter process size for process " + (i + 1) + ":");
            processSize[i] = sc.nextInt();
        }
        
        implementNextFit(blockSize, originalBlockSize, processSize);
    }

    static void implementNextFit(int blockSize[], int originalBlockSize[], int processSize[]) {
        int allocation[] = new int[processSize.length];
        
        // Initialize all allocations to -1 (meaning no block allocated)
        for (int i = 0; i < processSize.length; i++) {
            allocation[i] = -1;
        }
        
        int j = 0; // Start from the first block
        
        for (int i = 0; i < processSize.length; i++) {
            // Search for the next suitable block from the last allocated position
            while (j < blockSize.length) {
                if (processSize[i] <= blockSize[j]) {
                    allocation[i] = j;
                    blockSize[j] -= processSize[i]; // Reduce available size in the block
                    break;
                }
                j = (j + 1) % blockSize.length; // Move to the next block in a circular fashion
            }
        }

        System.out.println("Process Number\tProcess Size\tAllocated Block Size");
        for (int i = 0; i < processSize.length; i++) {
            System.out.print((i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1) {
                System.out.println(originalBlockSize[allocation[i]]);
            } else {
                System.out.println("Not Allocated");
            }
        }
    }
}
