import java.util.*;

public class LRUPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int frame, n;

        System.out.println("LRU Page Replacement");
        System.out.print("Enter frame size: ");
        frame = sc.nextInt();

        System.out.print("Enter reference string size: ");
        n = sc.nextInt();

        int ref[] = new int[n];
        System.out.println("Enter reference string:");
        for (int i = 0; i < n; i++) {
            ref[i] = sc.nextInt();
        }

        lru(frame, ref);
    }

    static void lru(int frame, int[] ref) {
        int[] temp = new int[frame];
        int[] lastUsed = new int[frame];
        int time = 0, hit = 0;

        for (int i = 0; i < frame; i++) {
            temp[i] = -1;
            lastUsed[i] = -1;
        }

        for (int i = 0; i < ref.length; i++) {
            boolean isHit = false;

            for (int j = 0; j < frame; j++) {
                if (ref[i] == temp[j]) {
                    isHit = true;
                    hit++;
                    lastUsed[j] = time++;
                    break;
                }
            }

            if (!isHit) {
                int lruIndex = 0;
                for (int j = 1; j < frame; j++) {
                    if (lastUsed[j] < lastUsed[lruIndex]) {
                        lruIndex = j;
                    }
                }

                temp[lruIndex] = ref[i];
                lastUsed[lruIndex] = time++;
            }

            System.out.println("Frame after reference " + ref[i] + ":");
            for (int j : temp)
                System.out.print(j + " ");
            System.out.println();
        }

        System.out.println("Hits: " + hit);
        System.out.println("Misses: " + (ref.length - hit));
    }
}
