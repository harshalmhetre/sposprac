import java.util.Scanner;

public class OptimalPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int frame, n;

        System.out.println("Optimal Page Replacement");
        System.out.print("Enter frame size: ");
        frame = sc.nextInt();

        System.out.print("Enter reference string size: ");
        n = sc.nextInt();

        int ref[] = new int[n];
        System.out.println("Enter reference string:");
        for (int i = 0; i < n; i++) {
            ref[i] = sc.nextInt();
        }

        optimal(frame, ref);
    }

    static void optimal(int frame, int[] ref) {
        int[] temp = new int[frame];
        int hit = 0;

        for (int i = 0; i < frame; i++)
            temp[i] = -1;

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
                int replaceIndex = -1, farthest = i + 1;

                for (int j = 0; j < frame; j++) {
                    int k;
                    for (k = i + 1; k < ref.length; k++) {
                        if (temp[j] == ref[k]) {
                            if (k > farthest) {
                                farthest = k;
                                replaceIndex = j;
                            }
                            break;
                        }
                    }

                    if (k == ref.length) {
                        replaceIndex = j;
                        break;
                    }
                }

                if (replaceIndex == -1) {
                    replaceIndex = 0;
                }

                temp[replaceIndex] = ref[i];
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
