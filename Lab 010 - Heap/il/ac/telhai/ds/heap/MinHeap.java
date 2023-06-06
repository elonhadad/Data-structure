package il.ac.telhai.ds.heap;

public class MinHeap<T extends Comparable<T>> {

	private final T[] array;
	int size;
	int maxsize;
	@SuppressWarnings({"unchecked","rawtypes"})
	public MinHeap(int length) {
		this.maxsize = length;
		this.array = (T[]) new Comparable[length + 1];
		this.size = 0;

	}
	@SuppressWarnings({"unchecked","rawtypes"})
	public MinHeap(T[] arr) {
		this.size = arr.length;
		this.maxsize = arr.length+1;
		this.array = (T[]) new Comparable[arr.length+1];
		System.arraycopy(arr, 0, array, 1, arr.length);
		build_min_heap();
	}

	public boolean isFull() {
		return size == maxsize;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void insert(T element) {
		if (isFull())
			throw new IllegalArgumentException();
		array[++size] = element;
		sift_up(size);
	}

	public T getMin() {
		if (isEmpty())
			throw new IllegalArgumentException();
		return array[1];
	}

	public T deleteMin() {
		if (isEmpty())
			return null;

		T temp = array[1];
		array[1] = array[size--];
		sift_down(1);
		return temp;
	}
	public void sift_up(int index){
		int parent = index / 2;

		while (index > 1 && array[index].compareTo(array[parent]) < 0){
			T tmp = array[index];
			array[index] = array[parent];
			array[parent] = tmp;
			index = parent;
			parent /= 2;
		}
	}
	public void sift_down(int index){
		if (index == 0)
			return;

		int min;
		int left = 2 * index;
		int right = (2 * index) + 1;

		if (left <= size  && array[left].compareTo(array[index]) < 0)
			min = left;
		else
			min = index;
		if (right <= size && array[right].compareTo(array[min]) < 0)
			min = right;

		if (array[min] != null && min != index){
			T tmp = array[index];
			array[index] = array[min];
			array[min] = tmp;
			sift_down(min);
		}
	}
	public void build_min_heap(){
		int current = (array.length / 2);

		for (int i = current; i >= 1; i--)
			sift_down(i);
	}

	public String toString() {
		StringBuilder s = new StringBuilder("[");
		for (int i = 1; i < size+1; i++) {
			s.append(array[i]);
			if (i != size)
				s.append(",");
		}
		s.append("]");
		return s.toString();
	}
}
