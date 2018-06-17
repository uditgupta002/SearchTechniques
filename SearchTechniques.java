import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;



/*
This class compares the running time of different search algorithms on a large array.
The comparisons are run on searching three elements : The first element, the middle element and the last element.

Results :

First element in array : 0
Last element in array : 99999998
Middle element in array : 49999999

On last element
linearSearch found 99999998 in 40790 microseconds.
binarySearch found 99999998 in 409 microseconds.
exponentialSearch found 99999998 in 42 microseconds.
interpolationSearch found 99999998 in 9 microseconds.
jumpSearch found 99999998 in 1591 microseconds.

On First Element
linearSearch found 0 in 6 microseconds.
binarySearch found 0 in 406 microseconds.
exponentialSearch found 0 in 5 microseconds.
interpolationSearch found 0 in 11 microseconds.
jumpSearch found 0 in 41 microseconds.

On middle element
linearSearch found 49999999 in 20395 microseconds.
binarySearch found 49999999 in 395 microseconds.
exponentialSearch found 49999999 in 88 microseconds.
interpolationSearch found 49999999 in 11 microseconds.
jumpSearch found 49999999 in 884 microseconds.

*/
public class SearchTechniques {

	public static void main(String args[]) {

		Class<SearchTechniques> currentClass = SearchTechniques.class;

		ArrayList<String> methodsList = new ArrayList<String>();
		methodsList.add("linearSearch");
		methodsList.add("binarySearch");
		methodsList.add("jumpSearch");
		methodsList.add("exponentialSearch");
		methodsList.add("interpolationSearch");

		int largeArr[] = new int[99999999];
		for (int i = 0; i < largeArr.length; i++) {
			largeArr[i] = i;
		}

		for (Method method : currentClass.getDeclaredMethods()) {
			if (!methodsList.contains(method.getName()))
				continue;
			try {
				long start = System.nanoTime();
				int foundIndex = (int) method.invoke(null, largeArr, largeArr[(largeArr.length) / 2]);
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

	public static int linearSearch(int[] arr, int element) {

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == element)
				return i;
		}
		return -1;
	}

	public static int jumpSearch(int[] arr, int element) {
		int optimalStep = (int) Math.sqrt(arr.length);
		int step = optimalStep;
		int prev = 0;
		while (arr[Math.min(step, arr.length) - 1] < element) {
			prev = step;
			step += optimalStep;
			if (prev > arr.length)
				return -1;
		}

		while (prev < Math.min(step, arr.length)) {
			if (arr[prev] == element)
				return prev;
			prev++;
		}
		return -1;
	}

	public static int interpolationSearch(int[] arr, int element) {

		int low = 0;
		int high = arr.length - 1;
		int mid;
		while (low <= high && element >= arr[low] && element <= arr[high]) {
			double interpolate = ((double) (high - low) / (arr[high] - arr[low] == 0 ? 1 : arr[high] - arr[low]))
					* (element - arr[low]);
			mid = low + (int) (interpolate);
			if (arr[mid] == element)
				return mid;

			if (element < arr[mid])
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}

	public static int binarySearch(int[] arr, int element) {
		return binarySearchIndices(arr, 0, arr.length - 1, element);
	}

	public static int binarySearchIndices(int[] arr, int low, int high, int element) {

		int mid = 0;
		while (low <= high) {
			mid = low + ((high - low) / 2);
			if (arr[mid] == element)
				return mid;
			else if (arr[mid] > element)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;

	}

	public static int exponentialSearch(int[] arr, int element) {

		int low = 0;
		int high = arr.length - 1;
		int step = 1;

		if (arr[low] == element)
			return low;

		while (step < high && arr[step] < element)
			step = 2 * step;
		return binarySearchIndices(arr, step / 2, Math.min(step, high), element);

	}

}
