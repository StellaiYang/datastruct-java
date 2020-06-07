package datastructs.sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 冒泡排序  时间复杂度为：O(n^2)
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};
        System.out.println("排序前："+ Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后："+ Arrays.toString(arr));

   /*     //测试冒泡排序的速度O(n^2),给8万个数据
        //创建80000个随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000); //生成一个[0-8000000]数
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("排序前时间："+ localDateTime);
        bubbleSort(arr);
        LocalDateTime localDateTime2 = LocalDateTime.now();
        localDateTime2.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("排序后时间："+ localDateTime2);*/


/*        //第二趟排序，就是将第二大的数排到倒数第二位
        for (int j = 0; j < arr.length - 1 -1; j++) {
            //如果前面的数比后面的大，则交换
            if(arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }

        System.out.println("第二趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第三趟排序，就是将第三大的数排到倒数第三位
        for (int j = 0; j < arr.length - 1 -2; j++) {
            //如果前面的数比后面的大，则交换
            if(arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }

        System.out.println("第三趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第四趟排序，就是将第四大的数排到倒数第四位
        for (int j = 0; j < arr.length - 1 -3; j++) {
            //如果前面的数比后面的大，则交换
            if(arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }

        System.out.println("第四趟排序后的数组");
        System.out.println(Arrays.toString(arr));*/
    }

    //将前面的冒泡排序封装成一个方法
    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;//标识变量，标识是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前面的数比后面的大，则交换
                if(arr[j] > arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
//            System.out.println("第"+ (i + 1) +"趟排序后的数组");
//            System.out.println(Arrays.toString(arr));
            if (flag == false){//在一趟排序中一次也没有发生过
                break;
            }else {
                flag = false;//重置flag，进行下次判断
            }
        }
    }
}
