package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author Rock Lee
 * @Date 2019/5/27 0027 15:07
 * 选择排序  O(n²)
 */
public class SelectionSort {

    public static void selectionSort(int[] arr){
        if (arr == null || arr.length < 2) return;

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < arr.length; j ++){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }


    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[random.nextInt(20)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }

        selectionSort(arr);

        Arrays.stream(arr).forEach(System.out::println);

    }



}
