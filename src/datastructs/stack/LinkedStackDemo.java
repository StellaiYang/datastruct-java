package datastructs.stack;

public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

class LinkedStack{
    //定义带头节点的栈
    Node head = new Node();

    public void push(int e){
        Node cur = head;
        while (true){
            if(cur.next == null){
                break;
            }
            cur = cur.next;
        }
        cur.next = new Node(e,null);
    }

    public int pop(){
        if(head.next == null){
            throw new RuntimeException("栈空");
        }
        Node cur = head;
        while (true){
            if(cur.next.next == null){
                break;
            }
            cur = cur.next;
        }
        int element = cur.next.element;
        cur.next = null;
        return element;
    }
}

class Node{
    public int element;
    public Node next;

    public Node() { }

    public Node(int element, Node next) {
        this.element = element;
        this.next = next;
    }
}
