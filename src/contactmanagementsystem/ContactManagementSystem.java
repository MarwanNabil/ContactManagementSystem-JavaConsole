package contactmanagementsystem;

import java.util.*;

public class ContactManagementSystem {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        
        doublyLinkedList dll = new doublyLinkedList();
        while(true){
            System.out.print("Please Choose a Command [1:7]\n"
                    + "1 - Insert\n" 
                    + "2 - Update\n" 
                    + "3 - Search\n" 
                    + "4 - Sort\n" 
                    + "5 - Delete\n" 
                    + "6 - Print Contact List\n"
                    + "7 - Exit\n" 
                    + "Write Your Number : ");
            int command = entry.nextInt();
            entry.nextLine();
            if(command == 1){
                System.out.print("Name : ");
                String name = entry.nextLine();
                System.out.print("Phone Number : ");
                String phoneNumber = entry.nextLine();
                dll.insert(name, phoneNumber);
            } else if(command == 2){
                System.out.print("     Please Choose a Command [1:2]\n"
                                + "     1 - Update Via Name\n" 
                                + "     2 - Update Via Phone Number\n"
                                + "     Write your command : ");
                int searchCommand = entry.nextInt();
                entry.nextLine();
                if(searchCommand == 1){
                    System.out.print("     Please Enter Target Name : ");
                    String name = entry.nextLine();
                    System.out.print("     Please Enter New Phone Number : ");
                    String phoneNumber = entry.nextLine();
                    
                    System.out.println(dll.updateViaName(name, phoneNumber) ? "     Updated Successfully!" : "     Not Found!");
                } else {
                    System.out.print("     Please Enter Target Phone Number : ");
                    String phoneNumber = entry.nextLine();
                    System.out.print("     Please Enter New Name : ");
                    String name = entry.nextLine();
                    System.out.println(dll.updateViaPhoneNumber(phoneNumber , name) ? "     Updated Successfully!" : "     Not Found!");
                }
            } else if(command == 3){
                System.out.print("     Please Choose a Command [1:2]\n"
                                + "     1 - Via Name\n" 
                                + "     2 - Via Phone Number\n"
                                + "     Write your command : ");
                int searchCommand = entry.nextInt();
                entry.nextLine();
                if(searchCommand == 1){
                    System.out.print("     Please Enter Desired Name : ");
                    String name = entry.nextLine();
                    System.out.println("     Name : " + dll.searchViaName(name));
                } else {
                    System.out.print("     Please Enter Desired Phone Number : ");
                    String phoneNumber = entry.nextLine();
                    System.out.println("     Phone Number : " + dll.searchViaPhoneNumber(phoneNumber));
                }
            } else if(command == 4){
                doublyLinkedList sortedOne = new doublyLinkedList();
                while(!dll.isEmpty()){
                    node future = dll.extractMinimum();
                    sortedOne.insert(future.getName() , future.getPhoneNumber());
                }
                dll = sortedOne;
                System.out.println("Sorted Successfully!");
            } else if(command == 5){
                System.out.print("     Please Choose a Command [1:2]\n"
                    + "     1 - Via Name\n" 
                    + "     2 - Via Phone Number\n"
                    + "     Write your command : ");
                int deleteCommand = entry.nextInt();
                entry.nextLine();
                if(deleteCommand == 1){
                    System.out.print("     Please Enter Desired Delete Name : ");
                    String name = entry.nextLine();
                    System.out.println(dll.deleteViaName(name) ? "     Deleted Successfully!" : "     Not Found!");
                } else {
                    System.out.print("     Please Enter Desired Delete Phone Number : ");
                    String phoneNumber = entry.nextLine();
                    System.out.println(dll.deleteViaPhoneNumber(phoneNumber) ? "     Deleted Successfully!" : "     Not Found!");
                }
            } else if(command == 6){
                dll.printAll();
            } else if(command == 7){
                break;
            } else {
                System.out.println("Invalid Input..");
            }
        }
    }
}

class node{
    private String name;
    private String phoneNumber;
    private node next , prev; 
    public node(String name , String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getName(){
        return this.name;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setNextNode(node target){
        this.next = target;
    }
    public void setPrevNode(node target){
        this.prev = target;
    }
    public node nextNode(){
        return this.next;
    }
    public node prevNode(){
        return this.prev;
    }
}


class doublyLinkedList{
    private node head;
    private node tail;
    private int size;
    //instructors..
    public doublyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public void insert(String name , String phoneNumber){
        node future = new node(name , phoneNumber);
        if(this.size == 0){
            head = tail = future;
        } else {
            tail.setNextNode(future);
            future.setPrevNode(tail);
            tail = future;
        }
        this.size ++;
    }
    public void printAll(){
        int tmpSize = this.size;
        node tmpHead = head; //the head which is moving.
        while(tmpSize >= 1){
            System.out.println("Name : " + tmpHead.getName() + ", Phone Number : " + tmpHead.getPhoneNumber());
            tmpHead = tmpHead.nextNode();
            tmpSize--;
        }
    }
    
    //for deleting
    public boolean deleteViaName(String targetName){
        int tmpSize = this.size;
        node tmpHead = head; //the head which is moving.
        if(tmpSize == 1 && tmpHead.getName().equals(targetName)){
            head = tail = null;
            this.size--;
            return true;
        }
        while(tmpSize >= 1){
            if(tmpHead.getName().equals(targetName)){
                if(tmpHead == head){
                    head = tmpHead.nextNode();
                    
                    this.size--;
                    return true;
                } else if(tmpHead == tail){
                    tail = tail.prevNode();
                    this.size--;
                    return true;
                } else {
                    (tmpHead.prevNode()).setNextNode( tmpHead.nextNode() );
                    (tmpHead.nextNode()).setPrevNode( tmpHead.prevNode() );
                    this.size--;
                }
                return true;
            }
            tmpSize--;
            tmpHead = tmpHead.nextNode();
        }
        return false;
    }
    public boolean deleteViaPhoneNumber(String targetPhoneNumber){
        int tmpSize = this.size;
        node tmpHead = head; //the head which is moving.
        if(tmpSize == 1 && tmpHead.getName().equals(targetPhoneNumber)){
            head = tail = null;
            this.size--;
            return true;
        }
        while(tmpSize >= 1){
            if(tmpHead.getName().equals(targetPhoneNumber)){
                if(tmpHead == head){
                    head = tmpHead.nextNode();
                    
                    this.size--;
                    return true;
                } else if(tmpHead == tail){
                    tail = tail.prevNode();
                    this.size--;
                    return true;
                } else {
                    (tmpHead.prevNode()).setNextNode( tmpHead.nextNode() );
                    (tmpHead.nextNode()).setPrevNode( tmpHead.prevNode() );
                    this.size--;
                }
                return true;
            }
            tmpSize--;
            tmpHead = tmpHead.nextNode();
        }
        return false;
    }
    
    //for searching
    public String searchViaName(String targetName){
        int tmpSize = this.size;
        node tmpHead = head; //the head which is moving.
        while(tmpSize >= 1){
            if(tmpHead.getName().equals(targetName)){
                return tmpHead.getPhoneNumber();
            }
            tmpSize--;
            tmpHead = tmpHead.nextNode();
        }
        String s = new String("Not Found!");
        return s;
    }
    public String searchViaPhoneNumber(String targetPhoneNumber){
        int tmpSize = this.size;
        node tmpHead = head; //the head which is moving.
        while(tmpSize >= 1){
            if(tmpHead.getPhoneNumber().equals(targetPhoneNumber)){
                return tmpHead.getName();
            }
            tmpSize--;
            tmpHead = tmpHead.nextNode();
        }
        String s = new String("Not Found!");
        return s;
    }
    
    //for updating..
    public boolean updateViaName(String name , String targetNumber){
        int tmpSize = this.size;
        node tmpHead = head; //the head which is moving.
        while(tmpSize >= 1){
            if(tmpHead.getName().equals(name)){
                tmpHead.setPhoneNumber(targetNumber);
                return true;
            }
            tmpSize--;
            tmpHead = tmpHead.nextNode();
        }
        return false;
    }
    public boolean updateViaPhoneNumber(String phoneNumber , String targetName){
        int tmpSize = this.size;
        node tmpHead = head; //the head which is moving.
        while(tmpSize >= 1){
            if(tmpHead.getPhoneNumber().equals(phoneNumber)){
                tmpHead.setName(targetName);
                return true;
            }
            tmpSize--;
            tmpHead = tmpHead.nextNode();
        }
        return false;
    }
    
    //sort
    public boolean isEmpty(){
        return this.size == 0;
    }
    public node extractMinimum(){
        int tmpSize = this.size;
        node tmpHead = head;
        node bst = new node("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ" , "9999999999999999999999999999999");
        //for extracting the minimum.....
        while(tmpSize >= 1){
            if(bst.getName().compareTo(tmpHead.getName()) > 0){
                bst = tmpHead;
            }
            tmpHead = tmpHead.nextNode();
            tmpSize--;
        }
        
        
        //for deleting.......
        tmpSize = this.size;
        tmpHead = head;
        while(tmpSize >= 1){
            if(bst == tmpHead){
                //found here..
                //delete that node and link two . or . one nodes to each others.
                if(tmpHead == head){
                    head = tmpHead.nextNode();
                } else if(tmpHead == tail){
                    tail = tail.prevNode();
                } else {
                    (tmpHead.prevNode()).setNextNode( tmpHead.nextNode() );
                    (tmpHead.nextNode()).setPrevNode( tmpHead.prevNode() );
                }
                this.size--;
                break;
            }
            tmpHead = tmpHead.nextNode();
            tmpSize--;
        }
        
        return bst;
    }
}
