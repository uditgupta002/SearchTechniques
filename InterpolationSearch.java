	/*
	 * Interpolation search is a fancy search technique that performs better than binary search 
	 * for uniformly distributed arrays.
	 * The interpolation formula describes the linear relation between the indexes and the
	 * elements in the array such that if we know the element to search, we can predict the 
	 * approximate position of the searched element.
	 * Running time of interpolation search is O(loglog(n)) for uniform arrays and could go
	 * up to O(n) for non uniform arrays.
	 * 
   */

public class InterpolationSearch {

	public static void main(String args[]) {
		int arr[] = new int[] {1,5,6,7,9,12,14,15,16,45,47,48,49,51,53,55,56,57,59,72,85,92,99};
		int uniformArr[] = {10,20,30,40,50,60,70,80,90,100};
		int arrSame[] = new int[] {10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 24, 33, 35, 42, 47};
		int sameArr[] = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4};
		int foundIndex = interpolationSearch(sameArr,3);
		System.out.println(foundIndex);
	}
	
	/**
	 * @param arr
	 * @param element
	 * @return integer
	 */
	public static int interpolationSearch(int[] arr,int element) {
		
		int low = 0;
		int high = arr.length - 1;
		int mid;
		while(low <= high && element >= arr[low] && element <= arr[high]) {
			
			mid = low + ((((high - low)*(element - arr[low])))/(arr[high] - arr[low] == 0 ? 1:arr[high] - arr[low]));
			//System.out.println("Mid="+mid+" Low="+low+" High="+high);
			if(arr[mid] == element)
				return mid;
			
			if(element < arr[mid])
				high = mid - 1;
			else
				low = mid + 1;
			//System.out.println("Mid="+mid+" Low="+low+" High="+high);
		}
		return -1;	
	}	
}
