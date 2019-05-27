package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author Rock Lee
 * @Date 2019/5/27 0027 14:53
 *  冒泡排序 O(n²)
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2) return;

        for (int end = arr.length - 1; end > 0; end --){
            for (int i = 0; i < end; i++){
                if (arr[i] > arr[i + 1]){
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[random.nextInt(20)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }

        bubbleSort(arr);

        Arrays.stream(arr).forEach(System.out::println);

    }






}
