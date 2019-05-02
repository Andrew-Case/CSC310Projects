/*
Andrew Case
CSC 310
HW 3
2/13/19
implement a stack class in java and then use your stack class to evaluate aritmetic equations and postfix notation equations
 */
package csc310hw3;

import java.util.*;

//array for number 1
class MinStack{
     //declare arraylists used to store data
     private List<Integer> stack = new ArrayList<>();
     private List<Integer> min = new ArrayList<>();
    
     //adds a passed int to stack, if it is the lowest value adds it to min stack
     void push(int x){
         stack.add(x);
         if(min.isEmpty() || x <= min.get(min.size()-1)){
             min.add(x);
         }
     }
     
     //removes and returns last entry in stack, if it is the lowest value removes it from min stack
     int pop(){
         if(stack.isEmpty()){
             throw new java.lang.RuntimeException("Array is empty");
         }
         int popped = stack.get(stack.size()-1);
         stack.remove(stack.size()-1);
         if(popped == min.get(min.size()-1)){
             min.remove(min.size()-1);
         }
         return popped;
     }
     
     //checks and returns last entry in stack
     int top(){
         if(stack.isEmpty()){
             throw new java.lang.RuntimeException("Array is empty");
         }
         return stack.get(stack.size()-1);
     }
     
     //returns top value from min
     int min(){
         if(stack.isEmpty()){
             throw new java.lang.RuntimeException("Array is empty");
         }
         return min.get(min.size()-1);
         
     }
     
}

class ValStack{
     List<Double> stack = new ArrayList<>();
    //adds passed int to stack
     void push(Double x){
         stack.add(x);
     }
     //removes and returns from stack
     Double pop(){
         if(stack.isEmpty()){
             throw new java.lang.RuntimeException("Array is empty");
         }
         Double popped = stack.get(stack.size()-1);
         stack.remove(stack.size()-1);
         return popped;
     }
     //returns last item in list
     Double top(){
         if(stack.isEmpty()){
             throw new java.lang.RuntimeException("Array is empty");
         }
         return stack.get(stack.size()-1);
     }
     //returns size of stack, used for other classes where stack.size() can't be called from arraylist imports
     int size(){
         return stack.size();
     }
     
}

class OpStack{
     List<String> stack = new ArrayList<>();
     
    //adds passed string to list
     void push(String x){
         stack.add(x);
         
     }
     //removes and returns last value in list
     String pop(){
         if(stack.isEmpty()){
             System.out.println("Stack is empty");
         }
         String popped = stack.get(stack.size()-1);
         stack.remove(stack.size()-1);
         return popped;
     }
     //returns last value in stack
     String top(){
         if(stack.isEmpty()){
             System.out.println("Stack is empty");
         }
         return stack.get(stack.size()-1);
     }
     //used to check size where array list imports can't be used
     int size(){
         return stack.size();
     }
     
}

public class Csc310HW3 {

    static ValStack valstack = new ValStack();
    static OpStack opstack = new OpStack();
    static OpStack resultstack = new OpStack();
    static MinStack minstack =new MinStack();
    
    //does operations
    static void doOp(){
        //pop values and op from respective stacks
        Double x = valstack.pop();
        Double y = valstack.pop();
        String op = opstack.pop();
        //checks what op and either pushes to valstack or result stack depending on return type needed
        switch(op){
            case "+":
                valstack.push(y+x);
                break;
            case "-":
                valstack.push(y-x);
                break;
            case "*":
                valstack.push(y*x);
                break;
            case "/":
                valstack.push(y/x);
                break;
            case ">":
                resultstack.push(compOp(x, y, op));
                break;
            case "<":
                resultstack.push(compOp(x, y, op));
                break;
            
            case "<=":
                resultstack.push(compOp(x, y, op));
                break;
            
            case ">=":
                resultstack.push(compOp(x, y, op));
                break;
            
            default:
                System.out.println("you done goofed");
        }
                
    }
    
    //returns comparison between two ints as a string for ease of output and storage
    static String compOp(Double x, Double y, String op){
        switch(op){
            case ">":
                if(y > x){
                    return "true";
                }
                else{
                    return "false";
                }
            case "<":
                if(y < x){
                    return "true";
                }
                else{
                    return "false";
                }
            
            case "<=":
                if(y <= x){
                    return "true";
                }
                else{
                    return "false";
                }
            
            case ">=":
                if(y >= x){
                    return "true";
                }
                else{
                    return "false";
                }
        }
        return "false";
    }
    
    //returns a value representing order of operations
    static int prec(String op){
        switch(op){
            case "<":
                return 0;
            case ">":
                return 0;
            case "<=":
                return 0;
            case ">=":
                return 0;
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
        }
        return 0;
    }
    
    //repeat ops
    static void repOp(String refOp){
        while(valstack.size() > 1 && prec(refOp) <= prec(opstack.top())){
            doOp();
        }
    }
    
    //checks if passed token is a number by eliminating all possible non-number strings
    static boolean isNumber(String x){
        switch(x){
            case "<=":
                return false;
            case ">=":
                return false;
            case "+":
                return false;
            case "-":
                return false;
            case "*":
                return false;
            case "/":
                return false;
        }
        return true;
    }
    
    static String evalExpression(String ex){
        //tokenize that string
        StringTokenizer e = new StringTokenizer(ex);
        //continue to push string into stacks while there are tokens left or until an op needs to be done
        while(e.hasMoreTokens()){
            String tok = e.nextToken(" ");
            if(isNumber(tok)){
                valstack.push(Double.parseDouble(tok));
            }
            else{
                repOp(tok);
                opstack.push(tok);
            }
        }
        //call end of repeats
        repOp("$");
        //check if a comparison was made leaving valstack empty
        if(valstack.size() != 0){
            return Double.toString(valstack.pop());
            
        }
        //return the result of the comparison if it reaches here
        return resultstack.pop();
    }
    
    static String postfix(String ex){
        StringTokenizer e = new StringTokenizer(ex);
        while(e.hasMoreTokens()){
            String tok = e.nextToken(" ");
            if(isNumber(tok)){
                valstack.push(Double.parseDouble(tok));
            }
            else{
                opstack.push(tok);
                doOp();
            }
        }
        //call end of repeats
        repOp("$");
        //check if a comparison was made leaving valstack empty
        if(valstack.size() != 0){
            return Double.toString(valstack.pop());
        }
        //return the result of the comparison if it reaches here
        return resultstack.pop();
    }
    
    public static void main(String[] args) {
        
        
        minstack.push(-2);
        minstack.push(0);
        minstack.push(-3);
        System.out.println(minstack.min());
        minstack.pop();
        System.out.println(minstack.top());
        System.out.println(minstack.min());
        //any input for eval expression NEEDS TO HAVE SPACES BETWEEN EACH NUMBER AND OPERATOR
        System.out.println(evalExpression("4 - 3 * 2 + 7"));
        System.out.println(evalExpression("14 <= 4 - 3 * 2 + 7"));
        
        System.out.println(postfix("5 2 + 8 3 - * 4 /"));
    }
    
}