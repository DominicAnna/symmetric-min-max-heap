package binomialheap;

import java.util.*;

class Heap {
    int[] items,tarray;//tarray contains SMMH
    int last;
    int c;

  public Heap(int[] x) {
      items=x;
      c=items.length;
      tarray = new int[c+1];
      for(int i=0;i<c;i++){
          tarray[i]=-2;      
      }
      tarray[0]=-1;
      last=1;
   // System.out.println("Here"+Arrays.toString(items));
  }
  
  public void insertSmmh(int x){
      
      int gp,lcgp,rcgp,current=last;
      boolean done;
      
      //checking p1
      if(((last+1)%2==1)&&(x<tarray[last-1])){
          tarray[last]=tarray[last-1];
          current--;
          
      }
      
      //Checking p2 and p3
      done=false;
      while(!done&&current>=3){
          
          gp=((current+1)/4)-1;
          lcgp=(2*gp)+1;
          rcgp=lcgp+1;
          if(x<tarray[lcgp]){
              //p2 violated
              tarray[current]=tarray[lcgp];
              current=lcgp;
          }
          else if(x>tarray[rcgp]){
              //p3 violated
              tarray[current]=tarray[rcgp];
              current=rcgp;
          }
          else
              done=true; // neither p2 or p3 violated
      }
      
      //Finally inserting x into smmh
      tarray[current]=x;
      last++;
      
      System.out.println("SMMH after inserting element"+x);
      for(int i=0;i<last;i++){
        System.out.print(tarray[i]+"\t");
      }
      System.out.println();
  }
  
  public void buildSmmh(){
      
      for(int i=0;i<c;i++){
          insertSmmh(items[i]);
      
      }
      //Final smmh
      System.out.println("SMMH : "+Arrays.toString(tarray));
      //System.out.println("Last after tree construction"+last);

  
  }
  
  public void deleteMin(){
      
    boolean done;
    int current=1,gp,gc1,gc2;
    
    if(last==1){
                    System.out.println("Tree Empty");
                    return;
    }
    
    if(current!=last){
    int x=tarray[last-1];
    tarray[last-1]=-1;
    last--;
  
    done=false;
    while(!done&&(current<=(last-2)/2)){
        gp=((current+1)/2)-1;
        gc1=((gp+1)*4)-1;
        gc2=gc1+2;
  if(gc2<last){
        if(tarray[gc1]>tarray[gc2]){
          //to get minimum of possible left children of grandparent
            if(tarray[gc2]!=-1)
            gc1=gc2;
        }
  }
        if(tarray[gc1]<x){
            System.out.println("gc1 value "+tarray[gc1]);
            System.out.println("x "+x);

            tarray[current]=tarray[gc1];
            current=gc1;
            System.out.println("here ");

        }
        else{
            done=true;
        }
    }
  
    tarray[current]=x;
      //  System.out.println("current "+current);

    System.out.println("After DeleteMin");
    for(int i=0;i<last;i++){
    System.out.print(tarray[i]+"\t");
    }
        System.out.println();

    }
  }
  
  public void deleteMax(){
      
    boolean done;
    if(last==2){
        System.out.println("Cannot perform deleteMax");
        return;
    }
    int current=2,gp,gc1,gc2,x;
        x=tarray[last-1];    

    tarray[last-1]=-1;
    last--;
    if(current!=last){
        
  
    done=false;
    while(!done&&(current<=(last-2)/2)){
        gp=(current/2)-1;
        gc1=((gp+1)*4);
        gc2=gc1+2;
  
        if(tarray[gc1]<tarray[gc2]){
          //to get maximun of possible right children of grandparent
            if(tarray[gc2]!=-1)
            gc1=gc2;
        }
  
        if(tarray[gc1]>x){
            tarray[current]=tarray[gc1];
            current=gc1;
        }
        else{
            done=true;
        }
    }
  
    tarray[current]=x;
    System.out.println("After DeleteMax:");
    
    for(int i=0;i<last;i++){
    System.out.print(tarray[i]+"\t");
    }
        System.out.println();

    } 
  }
}


public class Smmh {

    
    
  public static void main(String[] args) {
      int choice;
    Scanner in = new Scanner(System.in);
    System.out.println("Enter the number of Elements:");
    int count = in.nextInt();
    int[] numbers = new int[count];
        System.out.println("Enter the numbers");
    for (int i = 0; i < numbers.length; i++)
    {
        numbers[i] = in.nextInt();

    }
    
    Heap smm = new Heap(numbers);
    smm.buildSmmh();
    
    do{
            System.out.println("Enter your choice:");
            System.out.println("1. Delete minimum element");
            System.out.println("2. Delete maximum element");
            System.out.println("3. Exit");
            choice = in.nextInt();
            
            if(choice==1){
                smm.deleteMin();            
            }
            if(choice==2){
                smm.deleteMax();            
            }
    }while(choice<3);
    
  }
}
