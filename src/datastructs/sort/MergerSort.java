package datastructs.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergerSort {
    static int count = 0; //计数器

    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];//归并排序需要一个额外空间
        mergerSort(arr, 0, arr.length - 1, temp);
        System.out.println("归并排序后=" + Arrays.toString(arr));
    }

    /**
     * 分解数组 + 合并数组
     *
     * @param arr   原始数组
     * @param left  左边有序序列的初始索引
     * @param right 右边有序序列的初始索引
     * @param temp  做中转的临时数组
     */
    public static void mergerSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            //向左递归进行分解
            mergerSort(arr, left, mid, temp);
            //向右递归进行分解
            mergerSort(arr, mid + 1, right, temp);
            //合并
            merger(arr, left, mid, right, temp);
        }
    }


    /**
     * 合并方法
     *
     * @param arr   原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间有序序列的初始索引
     * @param right 右边有序序列的初始索引
     * @param temp  做中转的临时数组
     */
    public static void merger(int[] arr, int left, int mid, int right, int[] temp) {
        System.out.println("合并" + (++count) + "次");
        //初始化i，左边有序序列的初始索引
        int i = left;
        //初始化j，右边有序序列的初始索引
        int j = mid + 1;
        //指向temp数组的当前索引
        int t = 0;

        //（一）
        //先把左右两边有序数组按照规则填充到temp数组，
        //直到左右两边的有序数组有一边处理完毕为止
        while (i <= mid && j <= right) {//继续
            //左边有序数组的当前元素，小于等于右边有序数组的当前元素
            //将左边的元素，拷贝到temp数组中t的位置，然后t后移，i后移
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {//反之，先将右边的元素拷贝到临时数组中去，然后t后移，j后移
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //（二）
        //把有剩余数据的一边的数据依次全部填充至temp中去
        while (i <= mid) {//左边的有序数组还有剩余元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {//右边的有序数组还有剩余元素，就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //(三)
        //将temp数据的元素拷贝到arr
        //并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;

        System.out.println("tempLeft = " + tempLeft);
        System.out.println("right = " + right);
        //第一次合并时，tempLeft = 0，right = 1//tempLeft = 2，right = 3
        //最后一次tempLeft = 0 right = 7
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
