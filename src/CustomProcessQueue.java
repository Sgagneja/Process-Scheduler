//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P10 SJF PROCESS SCHEDULER
// Files:           NA
// Course:          121148, 1st Semester, 2018
//
// Author:          Shaan Gagneja
// Email:           sgagneja@wisc.edu
// Lecturer's Name: Alexi Brooks
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Will Ryan
// Partner Email:   wrryan@wisc.edu
// Partner Lecturer's Gary Dahl
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NA
// Online Sources:  NA
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Arrays;

/**
 * Queue for the processes, allows for actions to be done using the processes
 * @author Shaan and Will
 */
public class CustomProcessQueue implements WaitingQueueADT<CustomProcess> {
  private static final int INITIAL_CAPACITY = 20; // the initial capacity of the heap
  private static int capacity;
  private CustomProcess[] heap; // array-based min heap storing the data. This is an oversize array
  private int size; // number of CustomProcesses present in this CustomProcessQueue
  
  /**
   * Constructor for the CustomProcessQueue class 
   */
  public CustomProcessQueue() {
	this.heap = new CustomProcess[CustomProcessQueue.INITIAL_CAPACITY];
	this.size = 0;
	capacity = INITIAL_CAPACITY;
  }
  
  /**
   * getter for the left child's index
   * 
   * @param parentIndex the parent's index
   * @return the left child's index
   */
  private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex; }
  
  /**
   * getter for the right child's index
   * 
   * @param parentIndex the parent's index
   * @return the right child's index
   */
  private int getRightChildIndex(int parentIndex) { return 2 * parentIndex + 1; }
  
  /**
   * getter for the parent's index
   * 
   * @param childIndex a child's index
   * @return the parent's index
   */
  private int getParentIndex(int childIndex) { return (childIndex) / 2; }
  
  /**
   * Checks if the current index has a left child
   * @param index the index at which we are checking if there is a left child
   * @return true if there is a left child, false otherwise
   */
  private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
  
  /**
   * Checks if the current index has a right child
   * @param index the index at which we are checking if there is a right child
   * @return true if there is a right child, false otherwise
   */
  private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }
  
  /**
   * Checks if the current index has a parent
   * @param index the index at which we are checking if there is a parent
   * @return true if there is a parent, false otherwise
   */
  private boolean hasParent(int index) { return getParentIndex(index) >= 1; }
  
  /**
   * gets the left child
   * @param index the index from which we are getting the leftChild
   * @return the left child
   */
  private CustomProcess leftChild(int index) {
	return heap[getLeftChildIndex(index)];
  }
  
  /**
   * gets the right child
   * @param index the index from which we are getting the right child
   * @return the right child
   */
  private CustomProcess rightChild(int index) {
	return heap[getRightChildIndex(index)];
  }
  
  /**
   * gets the parent
   * @param index the index from which we are getting the parent
   * @return the left parent
   */
  private CustomProcess parent(int index) {
	return heap[getParentIndex(index)];
  }
  
  /**
   * swaps the processes at index1 and index2
   * @param index1 the first index
   * @param index2 the second index
   */
  private void swap(int index1, int index2) {
	CustomProcess temp = heap[index1];
	heap[index1] = heap[index2];
	heap[index2] = temp;
  }
  
  /**
   * Ensures that there is enough space in the array
   */
  private void ensureExtraCapacity() {
	if(size == CustomProcessQueue.capacity) {
	  this.heap = Arrays.copyOf(heap, capacity * 2);
	  capacity *= 2;
	}
  }
  
  /**
   * bubble up the element at the given index in the heap
   * @param index the given index
   */
  private void minHeapPercolateUp(int index) {
	while(hasParent(index) && parent(index).compareTo(heap[index]) > 0) {
      swap(getParentIndex(index), index);
      index = getParentIndex(index);
	}
  }
  
  /**
   * bubble down the element at the given index in the heap
   * @param index the given index
   */
  private void minHeapPercolateDown(int index) {
	while(hasLeftChild(index)) {
	  int smallerChildIndex = getLeftChildIndex(index);
	  if(hasRightChild(index) && rightChild(index).compareTo(leftChild(index)) < 0) {
		smallerChildIndex = getRightChildIndex(index);
		//swap(getLeftChildIndex(index), smallerChildIndex);
	  }if(heap[index].compareTo(heap[smallerChildIndex]) < 0) {
		break;
	  }else {
		swap(index, smallerChildIndex);
	  }
	  index = smallerChildIndex;
	}
  }
  
  /**
   * inserts a newObject in the priority queue
   * @param newObject the newObject being added to the heap
   */
  @Override
  public void enqueue(CustomProcess newObject) {
    ensureExtraCapacity();
    heap[size+1] = newObject;
    size++;
    minHeapPercolateUp(size);
  }
  
  /**
   * removes and returns the item with the highest priority
   * @return the item with the highest priority 
   */
  @Override
  public CustomProcess dequeue() { 
    if(isEmpty()) {
    	  return null;
    }
    CustomProcess element = heap[1];
    for(int i = 1; i< size; i++) {
    	  heap[i] = heap[i+1];
    }
    size--;
    minHeapPercolateDown(1);
    return element;
  }
  
  /**
   * returns without removing the item with the highest priority
   * @return  the item with the highest priority
   */
  @Override
  public CustomProcess peek() { 
    if(isEmpty()) {
    	  return null;
    }
    return heap[1];
  }
  
  /**
   * returns size of the waiting queue
   * @return the size
   */
  @Override
  public int size() { 
    return this.size;
  }
  
  /**
   * checks if the waiting queue is empty
   * @return true if it is, false otherwise
   */
  @Override
  public boolean isEmpty() { 
	if(size == 0) {
	  return true;
	}
    return false;
  }
  
  /**
   * getter for the heap
   * @return the heap
   */
  public CustomProcess[] getHeap() {
	return this.heap;
  }
}
