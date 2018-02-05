import java.math.BigInteger;

class Array2 {
  private final int max_elements = 200;
  private int size = 0;
  private int[] arr;

  public Array2(int n) {
    if (n < 0 || n > max_elements)
      throw new IllegalArgumentException("Array size must be non-negative");
    arr = new int[max_elements];
    size = n;
  }

  public int size() {
    return size;
  }

  // Set the i-th element to x
  // We are not checking whether the index is
  // in bounds, because dereferencing the array
  // element does it for us.
  public void set(int i, int x) {
    arr[i] = x;
  }

  // Get the i-th element
  // Again, we are not checking if the index
  // is in bounds.
  public int get(int i) {
    return arr[i];
  }

  // Represent the contents of the array as a String
  public String toString() {
    StringBuilder res = new StringBuilder("{");
    if (size > 0) {
      res.append(arr[0]);
      for (int i = 1; i < size; ++i) {
        res.append(", ");
        res.append(arr[i]);
      }
    }
    res.append("}");
    return res.toString();
  }

  // Reverse the array
  public void reverse() {
    int tmp;
    for (int i = 0; i < size/2; ++i) {
      tmp = arr[i];
      arr[i] = arr[size-1-i];
      arr[size-1-i] = tmp;
    }
  }

  // Find the maximum odd number in the array. Return -1
  // if there are no odd numbers.
  // Time complexity O(N)
  public int maxOdd() {
	  int foundIndex = -1;
	  for (int i = 0; i < size; i++) {
		  if (arr[i] % 2 > 0) {
			  if (foundIndex == -1) {
				  foundIndex = i;
			  } else if (arr[i] > arr[foundIndex]) {
				  foundIndex = i;
			  }
		  }
	  }
	  if (foundIndex > -1) {
		  return arr[foundIndex];
	  }
    return 0;
  }

  // Remove the element of index i from the array.
//Question 3: O(n) because one for loop
  public void remove(int i) { 
    --size;

    for (; i < size; ++i) {
      arr[i] = arr[i+1];
    }
  }

  // Remove the element of index i from the array.
  // This method may change the order of the other
  // elements of the array.
  //Question 3: O(1)
  public void remove2(int i) { 
    arr[i] = arr[--size];
  }

  // Return the index of the first occurrence of x in the array,
  // or -1 if x does not occur.
  // Time complexity: O(N)
  public int find(int x) {
	  for (int i = 0; i < size; i++) {
		  if (arr[i] == x) 
			  return i;
	  }
    return -1;
  }

  // Find the length of the longest palindrome that is
  // a contiguous subsequence of the array. A palindrome
  // is a word of the form ABCBA (length 5) or ABCCBA
  // (length 6).
  // Time complexity O(N^2)
  public int maxPalindrome() {
	  if (size < 2) {
		  return size;
	  }
	  
      int left, right, maxPalindromeLength = 1;
      for (int i = 1; i < size; ++i) {
          left = i - 1; right = i;
          while (left >= 0 && right < size && arr[left] == arr[right]) {
              if ((right - left + 1) > maxPalindromeLength) {
                  maxPalindromeLength = right - left + 1;
              }
              left--; right++;
          }
          left = i - 1; right = i + 1;
          while (left >= 0 && right < size && arr[left] == arr[right]) {
              if (right - left + 1 > maxPalindromeLength) {
                  maxPalindromeLength = right - left + 1;
              }
              left--; right++;
          }
      }
      return maxPalindromeLength;
  }

  // Return the maximum sum of all contiguous subarrays of the array.
  // Time complexity: O(Nlog(N))
  public int maxInterval() {
	  return maxInterval(arr, 0, size);
  }
  
  public int maxInterval(int[] array, int lo, int hi) {
	  if (hi - lo == 1) return array[lo];
	  int mid = lo + (hi - lo) / 2;
	  int loRes = maxInterval (array, lo, mid);
	  int hiRes = maxInterval (array, mid, hi);
	  int maxBorder = 0;
	  // Find the maximum subarray that crosses the border
	  // ...
	  int loSum = 0, hiSum = 0, toSum = 0;
      for (int i = mid - 1; i >= lo; i--) {
    	  loSum = Math.max(loSum, toSum);
    	  toSum += array[i];
      }
      toSum = 0;
      for (int i = mid + 1; i <= hi; i++) {
          hiSum = Math.max(hiSum, toSum);
    	  toSum += array[i];
      }
      maxBorder = loSum + array[mid] + hiSum;
	  return Math.max(loRes, Math.max(hiRes, maxBorder));  
  }

  // Return the index of the lowest element of the array,
  // assuming that the array contains a cyclic shift of
  // a non-decreasing sequence.
  public int findSplice() {
    int left   = 0;
    int right  = size-1;
    int middle = size/2;

    while(right-left > 1){
      if(arr[middle] <= arr[right]){
        right = middle;
      }else{
        left = middle;
      }

      middle = (right-left)/2 + left;
    }

    return arr[left] <= arr[right]? left : right;
  }
  
  // Optimized solution to the fibbonaci problem
  // Time complexity of the old solution: O(2^N) - exponential
  // Time complexity of this solution: O(N) - linear
  public BigInteger fib(int n) {
	    if (n <= 1) {
	    	return BigInteger.valueOf(n);
	    }
	    BigInteger x = BigInteger.ZERO, y = BigInteger.ONE, prevSum;
	    for (int i = 2; i <= n; i++) {
	        prevSum = x.add(y);
	        x = y; y = prevSum;
	    }
	    return y;
	}

  // Return the median value of an array.
  public int median() {
	  int n = size / 2, left = 0, right = size - 1;
	  if (left == right) {
		  return arr[left];
	  }
	  while(true) {
		  int pivotIndex = left + (int) Math.floor(Math.random() * (right - left + 1)); // randomize pivot selection
		  pivotIndex = partition(arr, left, right, pivotIndex);
		  if (n == pivotIndex) {
			  return arr[n];
		  } else if(n < pivotIndex) {
			  right = pivotIndex - 1;
		  } else {
			  left = pivotIndex + 1;
	      }
	  }
  }
  
  public static int partition(int[] array, int left, int right, int pivotIndex) {
	  int pivot = array[pivotIndex];
	  swap(array, pivotIndex, right);
	  for (int i = left; i < right; i++) {
		  if (array[i] < pivot) {
			  swap(array, left, i);
			  left++;
		  }
	  }
	  swap(array, right, left);
	  return left;
  }

  public static void swap(int[] array, int left, int right) {
	  int swap = array[left];
	  array[left] = array[right];
	  array[right] = swap;
  }

  public static void main (String[] args) {
    Array2 a = new Array2(10);
    a.set(0, 3);
    a.set(1, 4);
    a.set(2, 2);
    a.set(3, 5);
    a.set(4, 3);
    a.set(5, 1);
    a.set(6, 3);
    a.set(7, 4);
    a.set(8, 3);
    a.set(9, 1);
    System.out.println(
    		"     toString():  "   + a.toString() +
    		"\n         find():  " + a.find(-3) +
    		"\n       maxOdd():  " + a.maxOdd() +
    		"\nmaxPalindrome():  " + a.maxPalindrome() +
    		"\n  maxInterval():  " + a.maxInterval() +
    		"\n          fib():  "     + a.fib(99) +
    		"\n       median():  " + a.median());
  }
}

