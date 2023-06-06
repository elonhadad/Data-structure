package il.ac.telhai.ds.hash;

import il.ac.telhai.ds.linkedlist.DLinkedList;

public class HashTable<V> {
	public static final int DEF_MAX_HASH_SIZE = 10;

	private DLinkedList<V>[] hash;
	private int size;


	/**
	 * c'tor
	 * construct a hash-table of max-size "DEF_MAX_HASH_SIZE".
	 */
	public HashTable() {
		// TODO add your implementation.
		this(DEF_MAX_HASH_SIZE);
	}
	
	/**
	 * construct a hash-table of size 'hashSize'.
	 * @param hashSize, the size of the constructed hash-table.
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public HashTable (int hashSize) {
		// TODO add your implementation.
		this.hash = new DLinkedList[hashSize];
		this.size = hashSize;
	}
	
	/**
	 * @param val
	 * @return true if the hash-table contains val, otherwise return false
	 */
	public boolean contains(V val) {
		// TODO add your implementation.
		int i = Math.abs(val.hashCode() % size);
		if(hash[i] != null) {
			hash[i].goToBeginning();
			while(hash[i].getCursor() != null) {
				if(hash[i].getCursor().equals(val)) {
					return true;
				}
				if(hash[i].getNext() == null) {
					break;
				}
			}
		}
		return false;
	}

	/**
	 * Add val to the hash-table.
	 * 
	 * @param val
	 * @return If the val has already exist in the the hash-table, it will not be
	 *         added again. Return true if the val was added successfully. Otherwise
	 *         return false.
	 */
	public boolean add(V val) {
		// TODO add your implementation.
		if(contains(val)) {
			return false;
		}
		int i = Math.abs(val.hashCode() % size);
		if(hash[i] == null) {
			hash[i] = new DLinkedList<>();
		}
		hash[i].goToBeginning();
		hash[i].insert(val);
		return true;
	}

	/**
	 * Remove val from the hash-table.
	 * 
	 * @param val
	 * @return Return true if the val was removed successfully. Otherwise return
	 *         false.
	 */
	public boolean remove(V val) {
		// TODO add your implementation.
		if(!contains(val) || isEmpty()) {
			return false;
		}
		int i = Math.abs(val.hashCode() % size);
		hash[i].remove(val);
		return true;
	}

	/**
	 * clear al the data in the hash-table
	 */
	public void clear() {
		// TODO add your implementation.
		if(!isEmpty()) {
			for(int i = 0; i < size; i++) {
				if(hash[i] != null) {
					hash[i].clear();
				}
			}
		}
	}

	/**
	 * @return true if the hase-table is empty, otherwise return false.
	 */
	public boolean isEmpty() {
		// TODO add your implementation.
		for(int i =0; i < size; i++) {
			if(hash[i] != null) {
				if(!hash[i].isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
}