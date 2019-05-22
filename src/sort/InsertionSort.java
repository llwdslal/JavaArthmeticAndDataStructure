package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序
 *
 * @Author Rock Lee
 * @Date 2019/5/22 0022 16:32
 */
public class InsertionSort {
     /** 升序情况：
            * 数组索引为 n ( n 从 1 开始) 的元素k   ，将k与它前一个元素m 进行比较 。
            * 如果 k < m , k 与 m 互换位置， k 继续与它前一个元素进行比较，直到 k 的前一个元素索引为0 。
     */
    public static <T extends Comparable> void insertionSortVersion1(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T temp = array[i];
            System.out.println("===========" + i + "=============");
            for (int j = i; j > 0; j--) {
                if (temp.compareTo(array[j - 1]) < 0) {
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
                printArray(array);
            }
            System.out.println(">>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<");
        }
    }

    public static <T extends Comparable> void insertionSortVersion2(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T temp = array[i];
            System.out.println("===========" + i + "=============");
            // j之前元素已经排过序  如果 array[j] 大于 array[j-1]  那么 j 位置元素不需要移动位置，内循环没有必要继续进行
            // insertionSortVersion2 将判断移动到循环判断条件中
            for (int j = i; j > 0 && temp.compareTo(array[j - 1]) < 0; j--) {
                array[j] = array[j - 1];
                array[j - 1] = temp;
                printArray(array);
            }
            System.out.println(">>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<");
        }
    }

    public static <T extends Comparable> void insertionSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T temp = array[i];

            for (int j = i; j > 0 && temp.compareTo(array[j - 1]) < 0; j--) {
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
    }

    private static <T extends Comparable> void printArray(T[] array) {
        Arrays.stream(array).forEach(element -> System.out.print(element.toString() + " "));
        System.out.println();
    }

    public static void main(String[] args) {
        Random random = new Random();
        int arraySize = random.nextInt(20);

        Integer[] array = new Integer[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(100);
        }

        System.out.println("原数组：");
        printArray(array);

        System.out.println("-----------");

        insertionSort(array);

        System.out.println("排序后：");
        printArray(array);
    }


}
