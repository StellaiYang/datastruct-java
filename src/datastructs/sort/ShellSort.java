package datastructs.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSortMove(arr);
    }

    //使用逐步推导的方式(交换法)
    public static void shellSortSwap(int[] arr) {
        //根据前面的分析，使用循环处理
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素（共有gap组元素），步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮= " + Arrays.toString(arr));
        }

/*
        int temp = 0;
        //希尔排序的第一轮排序
        //因为第一轮排序是将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素（共有5组元素，每组有2个元素），步长为5
            for (int j = i - 5; j >=0 ; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if(arr[j] > arr[j + 5]){
                    temp = arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = temp;
                }
            }
        }
        System.out.println("第一轮希尔排序后");
        System.out.println(Arrays.toString(arr));

        //第二轮
        //第二轮将10个数据分成5/2 = 2组
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >=0 ; j -= 2) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if(arr[j] > arr[j + 2]){
                    temp = arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = temp;
                }
            }
        }
        System.out.println("第二轮希尔排序后");
        System.out.println(Arrays.toString(arr));


        //第三轮
        //第三轮将10个数据分成2/2 = 1组
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >=0 ; j --) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if(arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("第三轮希尔排序后");
        System.out.println(Arrays.toString(arr));*/
    }

    //使用逐步推导的方式(移位法)
    public static void shellSortMove(int[] arr) {
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从gap个元素开始，对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当推出while循环后，找到插入位置
                    arr[j] = temp;
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮= " + Arrays.toString(arr));
        }
    }

}
