package oving_03;

import java.util.Date;
import java.util.Random;
public class Talltabell {

    //hjelpemetode quicksort
    int partition(int arr[], int left, int right)
    {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }

    void quickSort(int arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
    }

    public static void sort(int[] a) {
        sort(a, 0, a.length);
    }
    public static void sort(int[] a, int fromIndex, int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
        dualPivotQuicksort(a, fromIndex, toIndex - 1, 3);
    }
    private static void rangeCheck(int length, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex > toIndex");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > length) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void dualPivotQuicksort(int[] a, int left, int right, int div) {
        int len = right - left;
        //System.out.println(right);
        if (len < 27) { // insertion sort for tiny array
            for (int i = left + 1; i <= right; i++) {
                for (int j = i; j > left && a[j] < a[j - 1]; j--) {
                    swap(a, j, j - 1);
                }
            }
            return;
        }
        int third = len / div;
        // "medians"
        int m1 = left + third;
        int m2 = right - third;
        if (m1 <= left) {
            m1 = left + 1;
        }
        if (m2 >= right) {
            m2 = right - 1;
        }
        if (a[m1] < a[m2]) {
            swap(a, m1, left);
            swap(a, m2, right);
        }
        else {
            swap(a, m1, right);
            swap(a, m2, left);
        }
        // pivots
        int pivot1 = a[left];
        int pivot2 = a[right];
        // pointers
        int less = left + 1;
        int great = right - 1;
        // sorting
        for (int k = less; k <= great; k++) {
            if (a[k] < pivot1) {
                swap(a, k, less++);
            }
            else if (a[k] > pivot2) {
                while (k < great && a[great] > pivot2) {
                    great--;
                }
                swap(a, k, great--);
                if (a[k] < pivot1) {
                    swap(a, k, less++);
                }
            }
        }
        // swaps
        int dist = great - less;
        if (dist < 13) {
            div++;
        }
        swap(a, less - 1, left);
        swap(a, great + 1, right);
        // subarrays
        dualPivotQuicksort(a, left, less - 2, div);
        dualPivotQuicksort(a, great + 2, right, div);

        // equal elements
        if (dist > len - 13 && pivot1 != pivot2) {
            for (int k = less; k <= great; k++) {
                if (a[k] == pivot1) {
                    swap(a, k, less++);
                }
                else if (a[k] == pivot2) {
                    swap(a, k, great--);
                    if (a[k] == pivot1) {
                        swap(a, k, less++);
                    }
                }
            }
        }
        // subarray
        if (pivot1 < pivot2) {
            dualPivotQuicksort(a, less, great, div);
        }
    }

    public int[] generateList(int length) {
        int[] list = new int[length];
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            int rand = random.nextInt(100);
            list[i] = rand;
        }
        return list;
    }



    public static void main(String a[]){

        Talltabell sorter = new Talltabell();
        int[] input = sorter.generateList(1000000);
        Date endTime;
        Date startTime = new Date();
        //sorter.dualPivotQuicksort(input, 0, input.length - 1, 1);
        //sorter.quickSort(input, 0, input.length - 1);
        endTime = new Date();

        System.out.println("Tid valgt sortering: " + ((endTime.getTime() - startTime.getTime()) + "ms"));


    }








}


