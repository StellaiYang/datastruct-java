package datastructs;

import java.io.*;

/**
 *  稀疏数组
 * @Author yangxing
 * @Date 2020/05/24
 *
 */
public class SparseArray {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //创建一个原始的二维数组 11 * 11
        //0，表示没有棋子，1表示黑子，2表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[5][6] = 7;
        //输出原始的二维数组
        System.out.println("原始的二维数组---");
        for (int[] row: chessArr1) {
            for (int data: row) {
                System.out.printf("%d \t",data);
            }
            System.out.println();
        }
        //将二维数组转为稀疏数组
        //1.先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        //遍历二维数组，将非0的值存放到sparseArray中
        int count = 0;//用于记录第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();

        System.out.println("得到的稀疏数组为如下形式---");

        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }
        System.out.println();

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("map.data"));
        outputStream.writeObject(sparseArray);
        outputStream.close();

        sparseArray = null;

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("map.data"));
        Object object = inputStream.readObject();

        sparseArray = (int[][]) object;

        //将稀疏数组恢复为原始的二维数组
        /**
         * 1.先读取稀疏数组的第一行数据，根据第一行数据得到原始的二维数组，比如上面的chessArr1 = int[11][11];
         * 2.读取稀疏数组的后几行数据，并赋值给原始数据即可
         */
        //1.先读取稀疏数组第一行数据
        int[][] chessArr2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        System.out.println("恢复后的二维数组---");
        for (int[] row: chessArr2) {
            for (int data: row) {
                System.out.printf("%d \t",data);
            }
            System.out.println();
        }

    }
}
