package datastructs.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        System.out.println("排序前：" + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    //选择排序，时间复杂度为O(n^2)
    public static void selectSort(int[] arr) {
        //使用逐步推导方式
        //第一轮
        //原始的数组：[101,34,119,1]
        //第一轮排序：1,34,119,101
        //先简单 -> 再复杂,复杂算法拆分成简单问题->逐步解决

        //推到的过程中已经发现规律
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if( min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
         /*   System.out.println("第"+ (i + 1) + "轮后");
            System.out.println(Arrays.toString(arr));*/
        }




/*        //第一轮
        int minIndex = 0;
        int min = arr[0];
        for (int j = 0 + 1; j < arr.length; j++) {
            if (min > arr[j]) {//说明假定最小值不是最小的
                min = arr[j];//重置min
                minIndex = j;//重置minIndex
            }
        }
        if (minIndex != 0) {
            //将最小值，放在arr[0],即交换
            arr[minIndex] = arr[0];
            arr[0] = min;
        }
        System.out.println("第一轮后");
        System.out.println(Arrays.toString(arr));
        //第二轮
        minIndex = 1;
        min = arr[1];
        for (int j = 1 + 1; j < arr.length; j++) {
            if (min > arr[j]) {//说明假定最小值不是最小的
                min = arr[j];//重置min
                minIndex = j;//重置minIndex
            }
        }
        if (minIndex != 1) {
            //将最小值，放在arr[1],即交换
            arr[minIndex] = arr[1];
            arr[1] = min;
        }

        System.out.println("第二轮后");
        System.out.println(Arrays.toString(arr));

        //第三轮
        minIndex = 2;
        min = arr[2];
        for (int j = 2 + 1; j < arr.length; j++) {
            if (min > arr[j]) {//说明假定最小值不是最小的
                min = arr[j];//重置min
                minIndex = j;//重置minIndex
            }
        }
        if (minIndex != 2) {
            //将最小值，放在arr[2],即交换
            arr[minIndex] = arr[2];
            arr[2] = min;
        }

        System.out.println("第三轮后");
        System.out.println(Arrays.toString(arr));*/
    }
}
