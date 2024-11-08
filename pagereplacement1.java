import java.lang.*;
import java.util.*;

public class pagereplacement1{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int ch;
        System.out.println("page replacement:");
        System.out.println("enter 1 for FIFO");
        System.out.println("enter 2 for LRU");
        System.out.println("enter your choice:");
        ch=sc.nextInt();
        int frame=3;
        int n;
        System.out.println("enter reference string size:");
        n=sc.nextInt();
        int ref[]=new int[n];
        System.out.println("enter reference string:");
        for(int i=0;i<n;i++){
            ref[i]=sc.nextInt();
        }
        if(ch==1){
            fifo(frame,ref);
        }
      else if(ch==2){
            lru(frame,ref);
        }
        else{
            System.out.println("invalid choice");
        }
    }
    static void fifo(int frame,int[] ref){
        int temp[]=new int[frame];
        int hit=0;
        int pointer=0;
        for(int i=0;i<frame;i++){
            temp[i]=-1;
        }
        for(int i=0;i<ref.length;i++)
        {
            boolean isHit=false;
            for(int j=0;j<frame;j++)
            {
                if(temp[j]==ref[i])
                {
                    isHit=true;
                    hit++;
                    break;
                }
            }
            if(!isHit){
                temp[pointer]=ref[i];
                pointer=(pointer+1)%frame;
            }
            
            System.out.println("frame after reference"+ref[i]+":");
            for(int j=0;j<frame;j++){
                System.out.print(temp[j]+" ");
            }
            System.out.println();    
        }
        System.out.println("hits:"+hit);
        System.out.println("misses:"+(ref.length-hit));
            
        
    }
    static void lru(int frame,int[] ref){
        int[] temp=new int[frame];
        int[] lastUsed=new int[frame];
        int time=0;
        int hit=0;
        for(int i=0;i<frame;i++){
            temp[i]=-1;
            lastUsed[i]=-1;
        }
        for(int i=0;i<ref.length;i++){
            boolean isHit=false;
            for(int j=0;j<frame;j++){
                if(ref[i]==temp[j]){
                    isHit=true;
                    hit++;
                    
                    lastUsed[j]=time++;
                    break;
                }
            }
            if(!isHit){
                int lruIndex=0;
                for(int j=1;j<frame;j++){
                    if(lastUsed[j]<lastUsed[lruIndex]){
                        lruIndex=j;
                    }
                }
            
            temp[lruIndex]=ref[i];
            lastUsed[lruIndex]=time++;
            }
            System.out.println("frame after reference "+ref[i]+":");
            for(int j=0;j<frame;j++){
                System.out.print(temp[j]+" ");
            }
            System.out.println();
        }
        System.out.println("hits:"+hit);
        System.out.println("misses:"+(ref.length-hit));
    }
}