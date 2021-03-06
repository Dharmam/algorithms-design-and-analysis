package challenges.leetcode;

import java.util.HashMap;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 146. LRU Cache
 * 
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and put.
 * 
 * 		get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * 		put(key, value) - Set or insert the value if the key is not already present. 
 * 
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * 
 * Follow up: Could you do both operations in O(1) time complexity?
 * 
 * @author Hxkandwal
 */
public class LRUCache extends AbstractCustomTestRunner {

	class Node {
        int key, value;
        Node prev, next;
        public Node () {}
        public Node (int key, int value) { this.key = key; this.value = value; }
    }
    
    private Map <Integer, Node> map = new HashMap<>();
    private int capacity;
    private Node head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node ();
        this.tail = new Node ();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node node = null;
        if ((node = map.get (key)) != null) {
            update (node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (map.containsKey (key)) delete (map.get (key));
        else if (map.size() == capacity) delete (head.next);
        add (new Node (key, value));    
    }
    
    public void update (Node node) {
        delete (node);
        add (node);
    }
    
    public void delete (Node node) {
        map.remove (node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = node.prev = null;
    }
    
    public void add (Node node) {
        map.put (node.key, node);
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }
    
	// driver method
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
		cache.put(2, 1);
		cache.put(1, 1);
		cache.put(2, 3);
		cache.put(4, 1);
		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
	}
	
}
