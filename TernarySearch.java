
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/*
Ternary Search algorithm is similar to binary search with an additional middle element index check.
Ternary search performs slower as compared to binary search.
The overall running time complexity is 4clog3n + O(1)
*/
public class TernarySearch {

	public static void main(String args[]) {
		Class<SearchTechniques> currentClass = SearchTechniques.class;
		
		ArrayList<String> methodsList = new ArrayList<String>();
		methodsList.add("ternarySearch");
    
		int largeArr[] = new int[99999999];
		for (int i = 0; i < largeArr.length; i++) {
			largeArr[i] = i;
		}
		
	
		for (Method method : currentClass.getDeclaredMethods()) {
			if (!methodsList.contains(method.getName()))
				continue;
			try {
				long start = System.nanoTime();
				int foundIndex = (int) method.invoke(null, largeArr, largeArr[(largeArr.length) - 1]);
				long end = System.nanoTime();
				long milis = TimeUnit.NANOSECONDS.toMicros(end - start);
				System.out.println(method.getName() + " found " + foundIndex + " in " + milis + " microseconds.");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	public static int ternarySearch(int arr[],int element) {
		
		int low = 0;
		int high = arr.length - 1;
		int mid1;
		int mid2;
		
		while(low <= high) {
			mid1 = low + (high - low)/3;
			mid2 = mid1 + (high - low)/3;
			
			if(arr[mid1] == element)
				return mid1;
			if(arr[mid2] == element)
				return mid2;
			
			if(arr[mid1] > element)
				high = mid1 - 1;
			else if(arr[mid2] < element)
				low = mid2 + 1;
			else {
				low = mid1 + 1;
				high = mid2 - 1;
			}
		}
		return -1;
	}
}
