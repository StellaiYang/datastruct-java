package datastructs.linkedlist;

/**
 * 单链表实现约瑟夫环问题
 * 1...n 个小孩，从 第k个小孩开始报数，数到 m个小孩出圈，求出圈顺序
 */
public class Joseph {

    public static void main(String[] args) {
        //测试构建环形链表和遍历是否正确
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.buildCircleBoys(5);//加入5个小孩
        circleSingleLinkedList.showCircleBoy();


        //测试小孩出圈是否正确
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点,当前没有节点
    private Boy first = null;

    //添加小孩节点，构建环形链表
    public void addBoy(int nums) {
        //nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums 的值不正确");
            return;
        }

        Boy curBoy = null;//辅助指针，帮助构建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                boy.next = first;//构成环
                curBoy = boy;//让curBoy指向第一个元素
            } else {
                curBoy.next = boy;
                boy.next = first;
                curBoy = curBoy.next;
            }

        }
    }


    public void buildCircleBoys(int num) {
        if (num <= 0) {
            System.out.println("零个节点");
        }
        Boy curBoy = null;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                boy.next = first;
                curBoy = first;
            } else {
                curBoy.next = boy;
                boy.next = first;
                curBoy = boy;
            }
        }
    }

    public void showCircleBoy(){
        if(first == null){
            System.out.println("链表为空");
            return;
        }

        Boy curBoy = first;
        while (true){
            System.out.printf("当前节点编号%d \n",curBoy.no);
            if(curBoy.next == first){
                break;
            }
            curBoy = curBoy.next;
        }
    }

    public void showBoy() {
        //判断链表为null
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.no);
            if (curBoy.next == first) { //说明已经遍历完毕
                break;
            }
            curBoy = curBoy.next;//curBody后移
        }
    }


    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //先让helper，指向环形链表的最后这个节点既（helper.next == first）
        while (true) {
            if (helper.next == first) { //说明指向最后小孩节点
                break;
            }
            helper = helper.next;
        }
        //小孩报数前，先让first 和 helper 移动startNo - 1 次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.next;
            helper = helper.next;
        }
        //当小孩报数时，让first 和 helper指针同时的移动m - 1 次，然后出圈
        //循环出圈，直到圈中只有一个节点
        while (true) {
            if (helper == first) {
                //说明圈中只有一个人
                break;
            }
            //让first 和 helper指针同时的移动countNum - 1 次
            for (int j = 0; j < countNum - 1; j++) {
                first = first.next;
                helper = helper.next;
            }
            //此时first指向的节点就是要出圈的节点
            System.out.printf("小孩%d出圈\n", first.no);
            //此时first指向的小孩节点出圈
            first = first.next;
            helper.next = first;

        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.no);

    }
}

//创建一个boy类，表示一个节点
class Boy {
    public int no;//编号
    public Boy next;//指向下一个节点，默认为null

    public Boy(int no) {
        this.no = no;
    }

}