package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author Rock Lee
 * @Date 2019/5/28 0028 10:06
 * 归并排序
 */
public class MergeSort {

    public static void mergeSort(int[] arr){
        if (arr == null || arr.length < 2) return;

        sortProgress(0,arr.length-1,arr);
    }

    private static void sortProgress(int left, int right,int[] arr) {
        if (left == right) return;

        int middle = (left + right) / 2;
        
        sortProgress(left,middle,arr);
        sortProgress(middle + 1,right,arr);
        
        merge(left,middle,right,arr);
    }

    private static void merge(int left, int middle, int right,int[] arr ){
        int[] temp = new int[right - left + 1];

        int lp = left;
        int rp = middle + 1;
        int index = 0;

        while (lp <= middle && rp <= right){
            temp[index ++] = arr[lp] < arr[rp] ? arr[lp ++] : arr[rp ++];
        }

        while (lp <= middle){
            temp[index ++] = arr[lp ++];
        }

        while (rp <= right){
            temp[index ++] = arr[rp ++];
        }

        for (int i = 0;i < temp.length; i++){
            arr[left ++] = temp[i];
        }
    }


    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[random.nextInt(20)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }

        mergeSort(arr);

        Arrays.stream(arr).forEach(System.out::println);

    }


}
