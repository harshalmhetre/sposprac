import java.util.Scanner;

public class pageReplacement {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int ch;
        
        System.out.println("Page Replacement:");
        System.out.println("Enter 1 for FIFO");
        System.out.println("Enter 2 for LRU");
        System.out.println("Enter 3 for Optimal");
        System.out.println("Enter your choice:");
        ch = sc.nextInt();
        
        int frame = 3;  // Set the frame size here
        int n;
        
        System.out.println("Enter reference string size:");
        n = sc.nextInt();
        int ref[] = new int[n];
        
        System.out.println("Enter reference string:");
        for (int i = 0; i < n; i++) {
            ref[i] = sc.nextInt();
        }
        
        switch (ch) {
            case 1:
                fifo(frame, ref);
                break;
            case 2:
                lru(frame, ref);
                break;
            case 3:
                optimal(frame, ref);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    static void fifo(int frame, int[] ref) {
        int temp[] = new int[frame];
        int hit = 0;
        int pointer = 0;
        
        for (int i = 0; i < frame; i++) {
            temp[i] = -1;
        }
        
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
            for (int j = 0; j < frame; j++) {
                System.out.print(temp[j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("Hits: " + hit);
        System.out.println("Misses: " + (ref.length - hit));
    }

    static void lru(int frame, int[] ref) {
        int[] temp = new int[frame];
        int[] lastUsed = new int[frame];
        int time = 0;
        int hit = 0;
        
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
            for (int j = 0; j < frame; j++) {
                System.out.print(temp[j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("Hits: " + hit);
        System.out.println("Misses: " + (ref.length - hit));
    }

    static void optimal(int frame, int[] ref) {
        int[] temp = new int[frame];
        int hit = 0;
        
        for (int i = 0; i < frame; i++) {
            temp[i] = -1;
        }
        
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
                int replaceIndex = -1;
                int farthest = i + 1;
                
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
            for (int j = 0; j < frame; j++) {
                System.out.print(temp[j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("Hits: " + hit);
        System.out.println("Misses: " + (ref.length - hit));
    }
}
