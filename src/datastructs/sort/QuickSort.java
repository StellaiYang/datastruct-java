package datastructs.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-567,0,70};
        quickSort(arr,0,arr.length -1);
        System.out.println("arr=" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left,int right){
        int l = left;//左边下标
        int r = right;//右边下标
        //中轴值
        int pivot = arr[(left+right) / 2];
        //临时变量，交换时使用
        int temp = 0;
        //循环的目的是让比pivot小的值放到左边，比pivot大的值放到右边
        while (l < r){
            //在pivot左边一直找，找到大于等于pivot的值，才退出
            while (arr[l] < pivot){
                l += 1;
            }
            //在pivot右边一直找，找到小于于等于pivot的值，才退出
            while (arr[r] > pivot){
                r -= 1;
            }
            //l >= r 成立，说明pivot的左右两边的值，已经按照左边全部是小于等于pivot值
            //右边全部是大于等于pivot的值
            if(l >= r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //交换完后，发现这个arr[l]==pivot 相等,r--前移
            if(arr[l] == pivot){
                r -= 1;
            }
            //交换完后，发现这个arr[r]==pivot 相等,l++后移移
            if(arr[r] == pivot){
                l += 1;
            }
        }

        //如果l == r,必须l++，r--，否则出现栈溢出
        if(l == r){
            l += 1;
            r -= 1;
        }
        //向左递归
        if(left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right > l){
            quickSort(arr,l,right);
        }
    }

}
