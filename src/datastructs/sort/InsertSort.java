package datastructs.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {101,34,119,1,89,-1,1,-90};
//        int[] arr = {2,3,4,5,6,1};
        insertSort2(arr);
        System.out.println("arr = " + Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
     //实现
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];
            int insertIndex = i-1;
            while(insertIndex >= 0 && insertValue < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex]; //{101,34,119,1}; => {101,101,119,1}
                insertIndex--;
            }
            //当推出while循环时，插入的位置找到，insertIndex + 1;
//            if(insertIndex + 1 != i){
                arr[insertIndex+1] = insertValue;
                System.out.println("排序后");
                System.out.println(Arrays.toString(arr));
//            }
        }

/*
        //使用逐步推导的方式来讲解
        //第一轮 {101,34,119,1}; => {34,101,119,1}

        //定义待插入的数
        int insertValue = arr[1];
        int insertIndex = 1 - 1;//即arr[1]前面这个数的下标
        //给insertValue找到插入位置
        //1.insertIndex >= 0 保证再给insertValue找插入位置，不越界
        //2.insertValue < arr[insertIndex] 说明待插入的数还没有找到适当位置
        //3.就需要将arr[insertIndex] 后移
        while(insertIndex >= 0 && insertValue < arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex]; //{101,34,119,1}; => {101,101,119,1}
            insertIndex--;
        }
        //当推出while循环时，插入的位置找到，insertIndex + 1;
        arr[insertIndex+1] = insertValue;
        System.out.println("第一轮插入后");
        System.out.println(Arrays.toString(arr));

        //第二轮
        insertValue = arr[2];
        insertIndex = 2-1;

        while(insertIndex >= 0 && insertValue < arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex]; //{101,34,119,1}; => {101,101,119,1}
            insertIndex--;
        }
        //当推出while循环时，插入的位置找到，insertIndex + 1;
        arr[insertIndex+1] = insertValue;
        System.out.println("第2轮插入后");
        System.out.println(Arrays.toString(arr));


        //第3轮
        insertValue = arr[3];
        insertIndex = 3-1;

        while(insertIndex >= 0 && insertValue < arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex]; //{101,34,119,1}; => {101,101,119,1}
            insertIndex--;
        }
        //当推出while循环时，插入的位置找到，insertIndex + 1;
        arr[insertIndex+1] = insertValue;
        System.out.println("第3轮插入后");
        System.out.println(Arrays.toString(arr));
*/

    }

    public static void insertSort2(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >=0 && arr[insertIndex] > insertValue){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex+1] = insertValue;
        }
    }
}
