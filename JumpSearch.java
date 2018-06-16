/*
Jump search is an searching algorithm that runs in O(sqrt(n)) time. 
It performs better than linear search but slower than binary search.
Jump search would be ideal in cases where traversing back in an array is a costly operation. 
A binary search could perform lot of back traversals to indexes if the element is present at the beginning or if the searched element is even smaller than the starting element.
Hence a jump search would only require one jump as compared to binary search that would take O(log(n)) jumps in the worst case
*/
public class JumpSearch {

	public static void main(String args[]) {
		int arr[] = new int[] {1,5,6,7,9,12,14,16,45,47,48,49,51,53,55,56,57,59,72,85,92,99};
		int foundIndex = jumpSearch(arr,59);
		System.out.println(foundIndex);
	}
	
	public static int jumpSearch(int[] arr,int element) {
		int optimalStep = (int) Math.sqrt(arr.length);
		int step = optimalStep;
		int prev = 0;
		while(arr[Math.min(step, arr.length)-1] < element) { 
			prev = step;
			step += optimalStep;
			if(prev > arr.length)
				return -1;	
		}
		
		while(prev < Math.min(step,arr.length)) {
			if(arr[prev] == element)
				return prev;
			prev++;
		}
		return -1;	
	}
	
}
