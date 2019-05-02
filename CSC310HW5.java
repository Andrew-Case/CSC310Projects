/*
Andrew Case
CSC 310
HW 5
3/20/19
*/
package csc310hw5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class stack{
     //declare arraylists used to store data
     private List<Integer> stack = new ArrayList<>();
     
    
     //adds a passed int to stack, if it is the lowest value adds it to min stack
     void push(int x){
         stack.add(x);
     }
     
     //removes and returns last entry in stack, if it is the lowest value removes it from min stack
     int pop(){
         if(stack.isEmpty()){
             throw new java.lang.RuntimeException("Array is empty");
         }
         int popped = stack.get(stack.size()-1);
         stack.remove(stack.size()-1);
         return popped;
     }
     
     //checks and returns last entry in stack
     int top(){
         if(stack.isEmpty()){
             throw new java.lang.RuntimeException("Array is empty");
         }
         return stack.get(stack.size()-1);
     }
     
     void print(){
         for (int i = stack.size()-1; i >= 0; i--) {
             System.out.println(stack.get(i));
         }
     }
     
}

public class CSC310HW5 {
    
    public static void main(String[] args) {
        //declare Scanner
        Scanner in = new Scanner(System.in);
        //declare stacks
        stack hanoiA = new stack();
        stack hanoiB = new stack();
        stack hanoiC = new stack();
        //take user input for number of disks
        System.out.println("Enter number of disks: ");
        int n = in.nextInt();
        //set up the starting position of the disks
        for (int i = n; i >= 1; i--) {
            hanoiA.push(i);
        }
        //call hanoi
        hanoi(n, hanoiA, hanoiC, hanoiB, 'A', 'C', 'B');
        //print result
        hanoiC.print();
        //hard code tree value as array
        int[] tree = {1, 0, 2, 0, 0, 3};
        //call in and pre order
        System.out.println("In-Order Traversal: ");
        inOrder(tree, 0);
        System.out.println("");
        System.out.println("Pre-Order Traversal");
        preOrder(tree, 0);
        System.out.println("");
        
    }
    
    //three char variables keep track of which peg is which for printing moves
    static void hanoi(int n, stack from, stack to, stack aux, char fromN, char toN, char auxN){
        //base case
         if (n == 1) {
             //print what moves where
             System.out.println("Moved disk " + from.top() + " from peg " + fromN + " to peg " + toN);
             //move disks from from stack to to stack
             to.push(from.pop());
        }
         else{
             //recursive call
             hanoi(n-1, from, aux, to, fromN, auxN, toN);
             //print what moves where
             System.out.println("Moved disk " + from.top() + " from peg " + fromN + " to peg " + toN);
             //move from from stack to to stack
             to.push(from.pop());
             //recursive call
             hanoi(n-1, aux, to, from, auxN, toN, fromN);
         }
     }
    
    public static void preOrder(int[] tree, int pos){
        //check if null
        if(tree[pos] != 0){
            //looke through tree based on rule that chilren are stored at pos 2x+1 and 2x+2
            if(pos < tree.length){
                System.out.print(tree[pos] + " ");

                if((pos*2 + 1) < tree.length){
                    if(tree[pos] != 0)
                        preOrder(tree, (pos*2 + 1));
                }

                if((pos*2+2) < tree.length){
                    if(tree[pos] != 0)
                        preOrder(tree, (pos*2 + 2));
                }
            }
        }
    }
    
    public static void inOrder(int[] tree, int pos){
        //check if null
        if(tree[pos] != 0){
            //looke through tree based on rule that chilren are stored at pos 2x+1 and 2x+2
            if(pos<tree.length){
                if((pos*2 + 1) < tree.length){
                    if(tree[pos] != 0)
                        inOrder(tree, (pos*2 + 1));
                }                
                if(tree[pos] != 0){
                    System.out.print(tree[pos] + " ");
                } 
                if((pos*2+2) < tree.length){
                    if(tree[pos] != 0)
                        inOrder(tree, (pos*2 + 2));
                }
            }
        }
    }    
}
