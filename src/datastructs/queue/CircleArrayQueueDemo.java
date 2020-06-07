package datastructs.queue;

/**
 * 环形队列演示
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        CircleArrayQueue2 circleArrayQueue = new CircleArrayQueue2(4);//设置为4，有效空间为3
        circleArrayQueue.add("1");
        circleArrayQueue.add("2");
        circleArrayQueue.add("3");
        circleArrayQueue.add("4");
//        System.out.println(circleArrayQueue.show());
        System.out.println(circleArrayQueue.get());
        System.out.println(circleArrayQueue.get());
        System.out.println(circleArrayQueue.get());
        System.out.println(circleArrayQueue.get());

        circleArrayQueue.add("1");
        circleArrayQueue.add("2");
        circleArrayQueue.add("3");
        circleArrayQueue.add("4");
//        System.out.println(circleArrayQueue.show());
        System.out.println(circleArrayQueue.get());
        System.out.println(circleArrayQueue.get());
        System.out.println(circleArrayQueue.get());
        System.out.println(circleArrayQueue.get());

    }
}

class CircleArrayQueue{
    private int maxSize;//数组的最大容量
    //默认值为front = 0；
    private int front; //指向队列第一个元素
    //默认值为rear = 0；
    private int rear; //指向队列的最后一个元素的后一个位置，空出一个位置作为约定
    private Object[] container;//数组用遇存放数据，模拟队列

    public CircleArrayQueue(int maxSize){
        this.maxSize = maxSize;
        this.container = new Object[maxSize];
    }

    //判断队列是否已满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
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
        //直接将数据加入
        container[rear] = e;
        //将rear 后移，这里必须考虑取模
        rear = ( rear + 1 ) % maxSize;
        return true;
    }

    //获取队列的数据，出队列
    public Object get(){
        //如果队列为空，return null
        if(isEmpty()){
            return null;
        }

        //这里需要分析front指向队列第一个元素
        //1.先把front对应的值保留在一个临时变量
        //2.将front后移,考虑取模
        //3.将临时保存的变量返回
        Object e = container[front];
        front = (front + 1) % maxSize;
        return e;
    }

    //显示队列的所有数据
    public String show(){
        if(isEmpty()){
            return "[]";
        }
        //从front开始遍历，遍历多少个元素就可以了
        String str = "[";
        for (int i = front; i < front + size(); i++) {
            str += container[i % maxSize] + ", ";
            if(i == front + size() - 1){
                str = str.substring(0,str.length() - 2);
                str += "]";
            }
        }
        return str;
    }

    //求出当前队列有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }


    //显示队列的头数据，注意不是取出数据
    public Object peek(){
        //判断是否为空
        if(isEmpty()){
            return null;
        }
        return container[front];
    }
}

class CircleArrayQueue2{
    private int maxSize;//数组的最大容量
    private int front; //队列头
    private int rear; //队列尾
    private Object[] container;//数组用遇存放数据，模拟队列

    public CircleArrayQueue2(int maxSize){
        this.maxSize = maxSize;
        this.container = new Object[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty(){
        System.out.println("rear=" + rear);
        System.out.println("front=" + front);
        return rear == front ;
    }

    public boolean add(Object e){
        if(isFull()){
            return false;
        }
        rear = (rear + 1) % maxSize;
        container[rear] = e;
        return true;
    }

    public Object get(){
        if(isEmpty()){
            return null;
        }
        front = (front + 1) % maxSize;
        Object e =  container[front];
        return e;
    }


}