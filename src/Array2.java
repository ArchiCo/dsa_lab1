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
  public int maxOdd() {
    return -1;
  }

  // Remove the element of index i from the array.
  public void remove(int i) { //Question 3: O(n) because one for loop
    --size;

    for (; i < size; ++i) {
      arr[i] = arr[i+1];
    }
  }

  // Remove the element of index i from the array.
  // This method may change the order of the other
  // elements of the array.
  public void removeFast(int i) { //Question 3: O(1)
    arr[i] = arr[--size];
  }

  // Return the index of the first occurrence of x in the array,
  // or -1 if x does not occur.
  public int find(int x) {
    return 0;
  }

  // Find the length of the longest palindrome that is
  // a contiguous subsequence of the array. A palindrome
  // is a word of the form ABCBA (length 5) or ABCCBA
  // (length 6).
  public int maxPalindrome() {
    return 0;
  }

  // Return the maximum sum of all contiguous subarrays of the array.
  public int maxInterval() {
    return 0;
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

  // Return the median value of an array.
  public int median() {
    return 0;
  }

  public static void main (String[] args) {
    Array2 a = new Array2(3);
    a.set(0, 4);
    a.set(1, 2);
    System.out.println(a.toString());

    Array2 a2 = new Array2(5);
    a2.set(0,5);
    a2.set(1,5);
    a2.set(2,0);
    a2.set(3,2);
    a2.set(4,4);
    a2.set(5,4);
    System.out.println(a2.toString());
    //a2.reverse();
    //a2.removeFast(3);
    //System.out.println(a2.findSplice());
    System.out.println(a2.toString());
  }
}

/*
Question 1:
  reverse is O(n):
    One for loop that depends on the size of the array linearly.
    Stuff inside the loop is O(1).

Question 3:
  Assumes (i < n).
  remove is O(n):
    One for loop that depends on the size of the array linearly.
    Stuff inside the loop is O(1).
  remove2 is O(1):
    There is only stuff that is O(1).

Question 5:
  The memory needs to be reallocated when shrinking (and possibly also moved depending on the allocator).
  Therefore: May be inefficient when having few elements and removing.
  Shrinking when necessary (manually) would probably be the best because the user of the dynamic array will know how it is going to be used.

Question 7:
  I assume that (+), (*), (/) is O(1). (constant^n) is O(n). (n^constant) is O(1)
  O(n)
  O(2*n) = O(n)
  O(n*(n+1)/2 - 1) = O(n^2)
  O(lg(n)*n) = O(n log n)
  O(n)

n=5
2
+2*2
+2*2*2
+2*2*2*2
+2*2*2*2*2

Question 9:
  Algorithm 1:
    (a,x) -> {
      int result = 0;
      int xTmp = 1;
      for(int i=0; i<=length(a); ++i){
        result += a[i]*xTmp;
        xTmp *= x;
      }
      return result;
    }

    Saves the previous value of the x power.

    The function is O(n):
      One for loop that depends on the size of the array linearly.
      Stuff inside the loop is O(1).

  Algorithm 2: O(n)
    (a,x) -> {
      int result = 0;
      for(int i=n-1; i>0; --i){
        result = x*(result + a[i]);
      }
      return a[0] + result;
    }

    This is the formula: a0+x*(a1+x*(a2+x*(..))).

    The function is O(n):
      One for loop that depends on the size of the array linearly.
      Stuff inside the loop is O(1).

Question 11:
  T(n) = T(n−1) + 𝛩(1/n)
  T(n)−T(n−1) = 𝛩(1/n)
  T(n) = 𝛩(1/n²)
  T(n) = 𝛩(n⁻²)

  T(n) = 3⋅T(n/2) + 𝛩(n²)
  L = log₂(3) ≈ 1.585
  c = 1.6
  nᶜ is 𝑂(n²)
  3⋅(n/2)² ≤ k⋅n²
  3⋅n²/4 ≤ k⋅n²
  (3/4)⋅n² ≤ k⋅n²
  (3/4)⋅n² ≤ 1⋅n²
  (3/4)⋅n² ≤ n²
  T is 𝛩(n²)

  T(n) = 4⋅T(n/2) + 𝛩(n²)
  L = log₂(4) = 2
  n² ⋅ log(n)⁰ is 𝛩(n²)
  T is 𝛩(n² ⋅ log(n))

  T(n) = 5⋅T(n/2) + 𝛩(n²)
  L = log₂(5) ≈ 2.322
  n² is 𝑂(nᴸ)
  T is 𝛩(nᴸ) and also 𝑂(n³)

Question 13:
  Assumes a non-empty arr.
 */
