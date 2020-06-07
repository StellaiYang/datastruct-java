package datastructs.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleHeroNode hero1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode hero2 = new DoubleHeroNode(2, "卢俊义", "玉麒麟");
        DoubleHeroNode hero3 = new DoubleHeroNode(3, "吴用", "智多星");
        DoubleHeroNode hero4 = new DoubleHeroNode(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);

        doubleLinkedList.show();
        System.out.println("----------------待更新-------------");
        DoubleHeroNode hero5 = new DoubleHeroNode(4, "鲁智深", "花和尚");
        doubleLinkedList.update(hero5);
        doubleLinkedList.show();
        System.out.println("------------------待删除----------------");

        doubleLinkedList.delete(4);
        doubleLinkedList.show();
    }
}
//定义一个双向链表
class DoubleLinkedList {
    //初始化一个头节点，头节点不要动,不存放数据
    private DoubleHeroNode head = new DoubleHeroNode(0,null,null);
    //返回头节点
    public DoubleHeroNode getHead(){
        return head;
    }

    //添加方法节点至链表最后
    public void add(DoubleHeroNode heroNode){
        DoubleHeroNode temp = head;
        while (true){
            if(temp.next == null){ //链表结尾
                break;
            }
            temp = temp.next;
        }
        //形成双向链表
        temp.next = heroNode;
        heroNode.prev = temp;
    }

    //按照no大小进行添加
    public void addByOrder(DoubleHeroNode heroNode){
        DoubleHeroNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){ //链表结尾
                break;
            }
            if(temp.next.no > heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //将节点插入temp 与 temp.next中间;
        if(flag){
            temp.next.prev = heroNode;
            heroNode.next = temp.next;
            temp.next = heroNode;
            heroNode.prev = temp;
        }else {
            //形成双向链表
            temp.next = heroNode;
            heroNode.prev = temp;
        }
    }



    //更新方法
    public void update(DoubleHeroNode newNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        DoubleHeroNode temp = head.next;
        boolean flag = false;//找到需要更新的节点
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == newNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            temp.next.name = newNode.name;
            temp.next.nickName = newNode.nickName;
        }else {
            System.out.printf("未找到编号%d相同的节点\n",newNode.no);
        }
    }

    //删除方法
    public void delete(int no){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        DoubleHeroNode temp = head.next; //辅助节点
        boolean flag = false;//找到需要删除的节点
        while (true){
            if(temp == null){//已经到了链表最后
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            temp.prev.next = temp.next;
            //如果最后一节点，无需执行最后一句话
            if(temp.next != null){
                temp.next.prev = temp.prev;
            }
        }else {
            System.out.printf("未找到编号%d相同的节点\n",no);
        }
    }



    //遍历双向链表的方法
    public void show(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        DoubleHeroNode temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            temp = temp.next;
        }

    }

}
//定义DoubleHeroNode，每个HeroNode对象就是一个节点
class DoubleHeroNode{
    public int no;
    public String name;
    public String nickName;
    public DoubleHeroNode prev;//指向前一个节点，默认为null
    public DoubleHeroNode next;//指向后一个节点，默认为null

    //构造器
    public DoubleHeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
    //为了显示方便，我们重写toString


    @Override
    public String toString() {
        return "DoubleHeroNode[" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ']';
    }

}