package datastructs.queue;



public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个队列对象
        ArrayQueue arrayQueue = new ArrayQueue(3);
        arrayQueue.add("1");
        arrayQueue.add("2");
        arrayQueue.add("3");
        arrayQueue.add("4");
        System.out.println(arrayQueue.show());
        System.out.println(arrayQueue.get());
        System.out.println(arrayQueue.get());
        System.out.println(arrayQueue.get());
        System.out.println(arrayQueue.get());


    }
}

/**
 * 使用数组模拟队列-编写一个ArrayQueue类
 */
class ArrayQueue{
    private int maxSize;//数组的最大容量
    private int front; //队列头
    private int rear; //队列头
    private Object[] container;//数组用遇存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.container = new Object[maxSize];
        this.front = -1;//指向队列头部，分析front是指向队列头的前一个位置
        this.rear = -1; //指向队列尾部，指向队列尾部的数据（既就是队列最后一个数据）
    }
    //判断队列是否已满
    public boolean isFull(){
        return rear == maxSize - 1;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }
    //添加数据到队列
    public boolean add(Object e){
        if(null == e){
            throw new NullPointerException("队列无法加入null");
        }
        //判断队列是否已满，已满返回false
        if(isFull()){
            return false;
        }
        rear++; //让rear后移
        container[rear] = e;
        return true;
    }
    //获取队列的数据，出队列
    public Object get(){
        //如果队列为空，return null
        if(isEmpty()){
            return null;
        }
        front++;//让front后移
        return container[front];
    }

    //显示队列的所有数据
    public String show(){
        if(isEmpty()){
            return "[]";
        }
        String str = "[";
        for (int i = 0; i < container.length; i++) {
            str += container[i] + ", ";
            if(i == container.length - 1){
                str = str.substring(0,str.length() - 2);
                str += "]";
            }
        }
        return str;
    }

    //显示队列的头数据，注意不是取出数据
    public Object peek(){
        //判断是否为空
        if(isEmpty()){
            return null;
        }
        return container[front+1];
    }

}
