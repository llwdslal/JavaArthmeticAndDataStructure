package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author Rock Lee
 * @Date 2019/5/28 0028 16:43
 * 快排
 */
public class QuickSort {

    public static void quickSort(int[] arr){
        quickSort(0,arr.length - 1, arr);
    }


    private static void quickSort(int left,int right,int[] arr){

        if (left > right )return;

        swap(left + (int)(Math.random() * (right - left + 1)),right,arr );

        int[] equalRange = partition(left,right,arr);

        quickSort(left,equalRange[0] - 1,arr);
        quickSort(equalRange[1] + 1,right,arr);
    }

    private static int[] partition(int left, int right, int[] arr) {
        int less = left - 1;
        int more = right;
        int current = left;

        while (current < more){
            if (arr[current] < arr[right]){
                swap(current ++,++ less,arr);
            }else if (arr[current] > arr[right]){
                swap(current,-- more,arr);
            }else{
                current ++;
            }
        }
        swap(more,right,arr);

        return new int[]{less + 1,more};
    }

    private static void swap(int index1,int index2,int[] arr){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[random.nextInt(20)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }

        quickSort(arr);

        Arrays.stream(arr).forEach(System.out::println);

    }
}
