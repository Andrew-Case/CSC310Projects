/*
Andrew Case
HW 4
2/23/19
 */
package csc310hw4;

//import for linked list functionality
import java.util.*;

//#1
class MyCircularDeque{
    //declare array for queue
    private int[] queue;
    //int to track index
    private int ind;
    
    //constructor, x sets size
    public MyCircularDeque(int x){
        queue = new int[x];
    }
    //checks if array is full, if not adds passed int to front
    public boolean insertFront(int x){
        if(this.isFull()){
            System.out.println("Queue is full");
            return false;
        }
        for (int i = queue.length-1; i > 0; i--) {
            queue[i] = queue[i-1];
        }
        queue[0] = x;
        ind ++;
        return true;
    }
    
    //checks if array is full if not adds passed int to back
    public boolean insertLast(int x){
        if(this.isFull()){
            System.out.println("Queue is full");
            return false;
        }
        queue[ind] = x;
        ind ++;
        return true;
    }
    
    //checks if the queue is empty if not remove first entry
    public boolean deleteFront(){
        if(this.isEmpty()){
            System.out.println("Queue is empty");
            return false;
        }
        for (int i = 0; i < ind-1; i++) {
            queue[i] = queue[i+1];
        }
        queue[queue.length-1] = 0;
        ind--;
        return true;
    }
    
    //checks if queue isempty if not remove last entry
    public boolean deleteLast(){
        if(this.isEmpty()){
            System.out.println("Queue is empty");
            return false;
        }
        ind--;
        queue[ind] = 0;
        return true;
    }
    
    //prints all entries in the queue
    public void print(){
        for (int i = 0; i < ind-1; i++) {
            System.out.print(queue[i] + ", ");
        }
        System.out.println(queue[ind-1]);
    }
    
    //returns first entry in queue
    public int getFront(){
        if(this.isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }
        return queue[0];
    }
    
    //returns last entry in queue
    public int getLast(){
        if(this.isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }
        return queue[ind-1];
    }
    //checks if queue is empty
    public boolean isEmpty(){
        if(ind == 0){
            return true;
        }
        return false;
    }
    //checks if queue is at max size
    public boolean isFull(){
        if(ind==queue.length){
            return true;
        }
        return false;
    }
    
}

//#2
class merge{
    //list to be returned
    LinkedList<Integer> r = new LinkedList();
    
    public LinkedList<Integer> merge(LinkedList<Integer> a, LinkedList<Integer> b){
        //checks to see if there are unmerged entries
        while(!(a.isEmpty()) || !(b.isEmpty())){
            //if either list is empty simply puts remaining entries into r, 
            //otherwise compare to see which top entry is smaller and add that one to r, if tied add the entry from a
            if(b.isEmpty()){
                r.add(a.get(0));
                a.remove();
            }
            else if(a.isEmpty()){
                r.add(b.get(0));
                b.remove();
            }
            else{
                if(b.get(0) < a.get(0)){
                    r.add(b.get(0));
                    b.remove();
                }
                else{
                    r.add(a.get(0));
                    a.remove();
                }
            }
        }
        return r;
    }
}

//#3
class Queue{
    //initialize list to be used as queue
    LinkedList<Integer> queue = new LinkedList();
    
    //adds x to queue
    public void enqueue(int x){
        queue.add(x);
    }
    
    //checks if queue is empty if not removes first entry in queue
    public int dequeue(){
        if(queue.isEmpty()){
            System.out.println("queue is empty");
        }else{
            int r = queue.get(0);
            queue.remove();
            return r; 
        }
        return -9999;
    }
    
    //returns queue[0]
    public int first(){
        return queue.get(0);
    }
    
    //returns size of queue
    public int len(){
        return queue.size();
    }
    
    //checks if queue is empty
    public boolean isEmpty(){
        return queue.isEmpty();
    }
    
    //iterates through queue to search for a passed int
    public boolean search(int x){
        boolean found = false;
        for (int i = 0; i < queue.size(); i++) {
            if(queue.get(i) == x){
                found = true;
            }
        }
        return found;
    }
    
}

public class CSC310HW4 {

    
    public static void main(String[] args) {
        //#1 testing 
        MyCircularDeque deque = new MyCircularDeque(3);
        deque.deleteLast();
        deque.insertFront(1);
        deque.print();
        deque.insertFront(2);
        deque.print();
        deque.insertLast(3);
        deque.print();
        System.out.println("Front value: " + deque.getFront());
        System.out.println("Back value: " + deque.getLast());
        System.out.println(deque.isFull());
        deque.insertFront(4);
        deque.print();
        deque.deleteFront();
        deque.print();
        deque.deleteLast();
        deque.print();
        
        
        //#2 testing
        LinkedList<Integer> a = new LinkedList();
        a.add(1);
        a.add(2);
        a.add(4);
        LinkedList<Integer> b = new LinkedList();
        b.add(1);
        b.add(3);
        b.add(4);
        merge g = new merge();
        LinkedList<Integer> res = g.merge(a, b);
        while(!(res.isEmpty())){
            System.out.print(res.get(0) + ", ");
            res.remove();
        }
        System.out.println("");
        
        //#3 testing
        Queue q = new Queue();
        q.enqueue(2);
        q.enqueue(1);
        q.len();
        q.dequeue();
        q.len();
        q.enqueue(4);
        System.out.println(q.search(4));
        System.out.println(q.search(75));
        System.out.println(q.first());
        System.out.println(q.isEmpty());
    }
    
}
