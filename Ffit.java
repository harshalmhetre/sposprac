import java.util.Scanner;

public class Ffit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of blocks: ");
        int n = sc.nextInt();
        int[] blockSize = new int[n];
        int[] originalBlockSize = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter memory size for block " + (i + 1) + ": ");
            blockSize[i] = sc.nextInt();
            originalBlockSize[i] = blockSize[i];
        }

        System.out.print("Enter number of processes: ");
        int m = sc.nextInt();
        int[] processSize = new int[m];

        for (int i = 0; i < m; i++) {
            System.out.print("Enter size for process " + (i + 1) + ": ");
            processSize[i] = sc.nextInt();
        }

        firstFit(blockSize, originalBlockSize, processSize);
    }

    static void firstFit(int[] blockSize, int[] originalBlockSize, int[] processSize) {
        int[] allocation = new int[processSize.length];
        for (int i = 0; i < processSize.length; i++)
            allocation[i] = -1;

        for (int i = 0; i < processSize.length; i++) {
            for (int j = 0; j < blockSize.length; j++) {
                if (processSize[i] <= blockSize[j]) {
                    allocation[i] = j;
                    blockSize[j] -= processSize[i];
                    break;
                }
            }
        }

        System.out.println("Process No.\tProcess Size\tBlock Allocated");
        for (int i = 0; i < processSize.length; i++) {
            System.out.print((i + 1) + "\t\t" + processSize[i] + "\t\t");
            System.out.println(allocation[i] != -1 ? originalBlockSize[allocation[i]] : "Not Allocated");
        }
    }
}
