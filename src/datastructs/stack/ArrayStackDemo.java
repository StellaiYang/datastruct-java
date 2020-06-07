package datastructs.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        stack.show();

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());



    }
}

//定义一个 ArrayStack 表示栈结构
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//数组 容器
    private int top = -1;//top 表示栈顶，初始化为-1

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int e){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = e;
    }

    //出栈，将栈顶的数据取出
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int val = stack[top];
        top--;
        return val;
    }
    //显示栈的情况【遍历栈】，遍历时需要从栈顶开始显示
    public void show(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        //从栈顶开始显示
        for (int i = top; i >= 0 ;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

}
