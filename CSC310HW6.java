package csc310hw6;

import java.util.*;

//priority queue class
class pQueue{
    private List<Integer> queue = new LinkedList<>();
    
    //adds to list iterating through to compare keys and place entry in correct spot
    void add(int x){
        if(queue.isEmpty()){
            queue.add(x);
        }
        else{
            for (int i = 0; i < queue.size(); i++) {
                if (x <= queue.get(i)) {
                    queue.add(i, x);
                    break;
                }
                else if(i == queue.size()-1){
                    queue.add(x);
                    break;
                }
            }
        }
    }
    
    //returns min which is always at index 0
    int min(){
        return queue.get(0);
    }
    
    //returns and removes min which is always at 0
    int removeMin(){
        int rem = queue.get(0);
        queue.remove(0);
        return rem;
    }
    
    //prints out all entries in list
    void sort(){
        while(queue.size() > 0){
            System.out.print(queue.get(0) + " ");
            queue.remove(0);
        }
        System.out.println();
    }
}

//binary heap class
class binHeap{
    private List<Integer> heap = new ArrayList<>();
    
    //swaps two entries used in downheap and upheap
    void swap(int x, int y){
        Integer temp = heap.get(y);
        heap.set(y, heap.get(x));
        heap.set(x, temp);
        
    }
    
    //finds parent
    int parent(int x){
        return (x-1)/2;
    }
    
    //finds left child
    int left(int x){
        return 2*x+1;
    }
    
    //finds right child
    int right(int x){
        return 2*x+2;
    }
    
    //checks if left child exists
    boolean hasLeft(int x){
        return 2*x+1 < heap.size() && heap.get(2*x+1) != null;
    }
    
    //checks if right child exists
    boolean hasRight(int x){
        return 2*x+2 < heap.size() && heap.get(2*x+2) != null;
    }
    
    //inserts into list then calls upheap
    void insert(int x){
        heap.add(x);
        upheap(heap.size()-1);
    }
    
    //returns min value always at 0
    int find_min(){
        return heap.get(0);
    }
    
    //removes and returns min value always at 0
    int remove_min(){
        int ret = heap.get(0);
        swap(0, heap.size()-1);
        heap.remove(heap.size()-1);
        downheap(0);
        return ret;
    }
    
    //checks if list is empty
    boolean is_empty(){
        if(heap.size() == 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    //returns size
    int size(){
        return heap.size();
    }
    
    //used to keep heap properties after insert
    void upheap(int pos){
        if(pos != 0){
            if(heap.get(pos) < heap.get(parent(pos))){
                swap(pos, parent(pos));
                upheap(parent(pos));
            }
        }
    }
    
    //used to keep heap properties after removal
    void downheap(int pos){
        Integer childL = null;
        Integer childR = null;
        if(hasLeft(pos))
            childL = left(pos);
        if(hasRight(pos)) {
            childR = right(pos);
        }
        int smallest = -1;
        if(childL != null && childR!= null ) {
            smallest = heap.get(childL) < heap.get(childR) ? childL : childR;
        }
        else if(childL!= null )
            smallest = childL;
        else if(childR!= null )
            smallest = childR;
        else
            return;
        if(heap.get(smallest) < heap.get(pos))
        {
            swap(pos, smallest);
            downheap(smallest);
        }
    }
    
    void build_heap(ArrayList l){
        heap.clear();
        for (int i = 0; i < l.size(); i++) {
            heap.add((Integer) l.get(i));
            upheap(heap.size()-1);
        }
    }
}

public class CSC310HW6 {
    
    static void heapIt(int[] heap, int size, int i){
        int largest = i;
        int l = 2*i+1;
        int r = 2*i+2;
        
        if(l<size && heap[l] > heap[largest]){
            largest = l;
        }
        if(r<size && heap[r] > heap[largest]){
            largest = r;
        }
        if(largest != i){
            int swap = heap[i];
            heap[i] = heap[largest];
            heap[largest] = swap;
            
            heapIt(heap, size, largest);
        }
    }
    
    static void heapSort(int[] heap){
        int n = heap.length;
        
        for (int i = (n/2); i >= 0; i--) {
            heapIt(heap, n, i);
        }
        
        for (int i = n-1; i >= 0; i--) {
            int temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;
            heapIt(heap, i, 0);
        }
        for (int i = 0; i < heap.length; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        pQueue p = new pQueue();
        int[] a = {1,6,3,7,5,9};
        for (int i = 0; i < a.length; i++) {
            p.add(a[i]);
        }
        p.sort();
        
        int[] heap = {1,7,5,2,6,4};
        
        heapSort(heap);
        
        
        binHeap min = new binHeap();
        min.insert(5);
        min.insert(7);
        min.insert(3);
        min.insert(11);
        
        System.out.println(min.remove_min());
        System.out.println(min.remove_min());
        System.out.println(min.remove_min());
        System.out.println(min.remove_min());
    }
    
}
