import java.util.*;

public class FIFOPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int frame, n;

        System.out.println("FIFO Page Replacement");
        System.out.print("Enter frame size: ");
        frame = sc.nextInt();
        
        System.out.print("Enter reference string size: ");
        n = sc.nextInt();
        
        int ref[] = new int[n];
        System.out.println("Enter reference string:");
        for (int i = 0; i < n; i++) {
            ref[i] = sc.nextInt();
        }

        fifo(frame, ref);
    }

    static void fifo(int frame, int[] ref) {
        int[] temp = new int[frame];
        int hit = 0, pointer = 0;

        for (int i = 0; i < frame; i++) temp[i] = -1;

        for (int i = 0; i < ref.length; i++) {
            boolean isHit = false;

            for (int j = 0; j < frame; j++) {
                if (temp[j] == ref[i]) {
                    isHit = true;
                    hit++;
                    break;
                }
            }

            if (!isHit) {
                temp[pointer] = ref[i];
                pointer = (pointer + 1) % frame;
            }

            System.out.println("Frame after reference " + ref[i] + ":");
            for (int j : temp) System.out.print(j + " ");
            System.out.println();
        }

        System.out.println("Hits: " + hit);
        System.out.println("Misses: " + (ref.length - hit));
    }
} 
    

