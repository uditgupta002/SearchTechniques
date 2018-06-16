import java.util.concurrent.TimeUnit;

/*
Running Time O(logn)
*/
public class BinarySearch {

	
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
		int foundIndex = binarySearch(largeArr,largeArr.length-1);
		long end = System.nanoTime();
		long milis = TimeUnit.NANOSECONDS.toMicros(end - start);
		System.out.println(foundIndex+" in "+milis+" microseconds.");
		
	}
	
	public static int binarySearch(int[] arr,int element) {
		return binarySearchIndices(arr,0,arr.length-1,element);
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
}
