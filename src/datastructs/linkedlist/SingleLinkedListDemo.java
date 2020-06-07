package datastructs.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //创建链表
        SingleLinkedList linkedList = new SingleLinkedList();
        //加入数据
        linkedList.addAndSort(hero1);
        linkedList.addAndSort(hero4);
        linkedList.addAndSort(hero3);
        linkedList.addAndSort(hero2);

        linkedList.show();
//        System.out.println("-------------修改后-------------------");
//        HeroNode hero5 = new HeroNode(5, "鲁智深", "花和尚");
//        linkedList.update(hero5);
//        linkedList.show();

//        System.out.println("--------------------删除后----");
//        linkedList.delete(2);
//        linkedList.delete(1);
//        linkedList.show();

 /*       //测试一下，求单链表中有效节点的个数
        System.out.println("有效的节点个数=" + getLength(linkedList.getHead()));
        //测试一下，看是否得到倒数第K个节点
        HeroNode res = findLastNode(linkedList.getHead(),2);
        System.out.println("倒数第2个节点为：" + res);
*/
        //测试一下，看链表是否反转
        linkedList.show();
        System.out.println("-------------------反转后-----------------");
        reverseLinkedList(linkedList.getHead());
        linkedList.show();

        /*        linkedList.show();
        System.out.println("-----------------测试逆序打印单链表---------");
        reversePrint(linkedList.getHead());*/
    }

    //查找单链表中的倒数第K个节点【新浪面试题】
    //1.编写方法接收head节点，同时接收一个index
    //2.index 表示倒数第index个节点
    //3.先把链表从头到尾遍历，得到链表的总的长度getLength
    //4.得到size后，我们从链表的第一个开始遍历（size - index）个
    //5.如果找到了，则返回该节点，否则返回null
    public static HeroNode findLastNode(HeroNode head,int index){
        //如果链表为空，则返回null
        if(head.next == null){
            return null;
        }
        //第一次遍历得到链表的长度
        int size = getLength(head);
        //第二次遍历 size-index 位置，就是我们倒数的第K个节点
        //先做index 的校验
        if(index <= 0 || index > size){
            return null;
        }

        //定义一个辅助变量,for 循环定位到倒数的index个
        HeroNode cur = head.next;
        for (int i = 0;i < size - index;i++){
            cur = cur.next;
        }
        return cur;
    }

    //利用栈的数据结构，将各个节点压入栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return;//空链表
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈中
        while (cur != null){
            stack.push(cur);
            cur = cur.next; //cur后移
        }

        //将栈中的节点进行打印
        while (stack.size() > 0){
            System.out.println(stack.pop());//stack 特点，先进后厨
        }
    }


    //单链表的反转
    public static void reverseLinkedList(HeroNode head){
        //当前链表为空，或者只有一个节点，无需反转，直接返回
        if(head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助变量，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        //指向当前节点【cur】的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,null,null);

        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur != null){
            next= cur.next; //先保存当前节点的下一个节点
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将新链表连接到cur上
            cur = next; //让cur后移
        }
        //将head.next 指向reverseHead.next ,实现单链表的反转
        head.next = reverseHead.next;
    }



    //方法：获取到单链表的节点的个数（如果是带头结点链表，需要不统计头节点）
    /**
     * @param head 链表的头节点
     * @return  返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head){
        if(head.next == null){//空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量,这里没有统计头节点
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;//遍历
        }
        return length;
    }


}
//定义SingleLinkedList管理我们的英雄
class SingleLinkedList{
    //初始化一个头节点，头节点不要动,不存放数据
    private HeroNode head = new HeroNode(0,null,null);
    //返回头节点
    public HeroNode getHead(){
        return head;
    }


    //添加节点到单项列表
    //当不考虑编号的顺序时
    // 1.找到当前链表的最后节点
    // 2.将这个最后节点的next指向新的节点
    public void add(HeroNode heroNode){
        //因为head节点不能动，需要辅助指针 temp
        HeroNode temp = head;
        //遍历链表找到最后
        while (true){
            //找到链表的最后
            if(temp.next == null){
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        //退出while循环时，temp指向了链表的最后
         temp.next = heroNode;
    }
    //按照no顺序来插入，如果no重复则给提示信息
    public void addAndSort(HeroNode heroNode){
        //因为head节点不能动，需要辅助指针 temp,
        //因为是单链表，因此我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        //用于标识添加的节点是否存在，默认false
        boolean flag = false;
        //遍历链表找到编号小于 参数编号的节点
        while (true){
            //说明temp已经在链表的最后
            if(temp.next == null){
                break;
            }
            //位置找到，就在temp的后面插入
            if(temp.next.no > heroNode.no){
                break;
            }else if(temp.next.no == heroNode.no){
                //将要添加的heroNode的编号已然存在
                flag = true;
                break;
            }
            //后移就是遍历当前链表
            temp = temp.next;
        }
        //判断flag的值，如果flag = true，说明编号存在，不能添加
        if(flag){
            System.out.printf("待插入的英雄的编号%d已经存在，不能添加\n", heroNode.no);
        }else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //修改节点的信息，根据no来修改，即no不能改
    //1.根据参数的no来修改即可
    public void update(HeroNode newHeroNode){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //如果链表不为空，遍历链表，找出需要修改的节点
        HeroNode temp = head.next;
        //表示是否找到该节点
        boolean flag = false;
        while (true){
            //已经遍历完链表了
            if(temp == null){
                break;
            }
            if(temp.no == newHeroNode.no){
                //找到
               flag = true;
                break;
            }
            temp = temp.next;
        }

        //根据flag判断是否找到要修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else {
            //没有找到要修改的节点
            System.out.printf("没有找到编号为%d的节点，转为新增链表节点",newHeroNode.no);
            addAndSort(newHeroNode);
        }

    }

    //删除节点：
    //1.head 不能动，因此我们需要找一个temp辅助节点用于指向待删除节点的前一个节点
    //2.我们比较的是temp.next.no 与要删除节点的no
    public void delete(int no){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //如果链表不为空，遍历链表，找出需要删除的节点
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            //已经遍历完链表了
            if(temp.next == null){
                break;
            }
            //找到要删除节点的上一个节点
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else {
            System.out.printf("未找到要编号为%d的节点\n",no);
        }

    }

    //显示链表
    public void show(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为head节点不能动，需要辅助指针 temp
        HeroNode temp = head.next;
        while (true){
            //判断是否到链表最后
            if(temp == null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }

    }



}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
    //为了显示方便，我们重写toString


    @Override
    public String toString() {
        return "HeroNode[" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ']';
    }

}
