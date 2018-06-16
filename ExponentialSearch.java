import java.util.concurrent.TimeUnit;

/*
Exponential Search involves two steps
1) Find the range of the number. This would be the index of the number larger than the element to be searched during step increase
and the other index is the half of the previous index since we increase the steps in powers of 2.
2) Run a binary search in the range defined by step 1.

Interpolation search performs well in the situations where the bounds of the array are not known. 
This is the case in Poison distribution where the arrays could be quite large and we can search continiously without knowing
the bounds.
The range is i and i/2 because the number at index i is found to be greater than the element to be searched and since we have
already checked that number at index i/2 was smaller than the element to be searched, we can restrict our search in this domain.
Time Complexity : O(Log n)
Auxiliary Space : The above implementation of Binary Search is recursive and requires O(Log n) space. With iterative Binary Search, we need only O(1) space.
*/
public class ExponentialSearch {

	public static void main(String args[]) {
		int arr[] = new int[] {1,5,6,7,9,12,14,15,16,45,47,48,49,51,53,55,56,57,59,72,85,92,99};
		int uniformArr[] = {10,20,30,40,50,60,70,80,90,100};
		int arrSame[] = new int[] {10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 24, 33, 35, 42, 47};
		int sameArr[] = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4};
		
		
		int largeArr[] = new int[99999999];
		for(int i=0;i<largeArr.length;i++) {
			largeArr[i] = i;
		}
		
		
		long start = System.nanoTime();
		int foundIndex = linearSearch(largeArr,largeArr.length-1);
		long end = System.nanoTime();
		long milis = TimeUnit.NANOSECONDS.toMicros(end - start);
		System.out.println(foundIndex+" in "+milis+" microseconds.");
		
	}
	
  public static int binarySearchIndices(int[] arr,int low,int high,int element) {
		
		int mid=0;
		while(low <= high) {
			//System.out.println("Mid="+mid+" Low="+low+" High="+high);
			mid = low + ((high - low) / 2);
			if(arr[mid] == element)
				return mid;
			else if(arr[mid] > element)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
		
	}
  
	public static int exponentialSearch(int[] arr,int element) {
		
		int low = 0;
		int high = arr.length - 1;
		int step = 1;
		
		if(arr[low] == element)
			return low;
		
		while(step < high && arr[step] < element)
			step = 2*step;
		
		return binarySearchIndices(arr,step/2,Math.min(step, high),element);
	}
	
}
