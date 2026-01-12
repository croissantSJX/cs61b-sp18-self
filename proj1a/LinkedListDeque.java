public class LinkedListDeque<T> {

    public class TNode {
        public T item;
        public TNode next;
        public TNode prev;

        public TNode(TNode p, T item, TNode n){
            this.item = item;
            prev = p;
            next = n;
        }

        public TNode() {
            this(null, null, null);
        }

        public TNode(T item) {
            this(null, item, null);
        }

    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque() {
        // Create the sentinel. Item is null.
        // Crucial: For an empty list, prev and next point to ITSELF.
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;

        size = 0;
    }

    public void addFirst(T item){
        /* Create new Node and put it at the first of the List**/
        TNode newFirstTNode = new TNode(sentinel, item, sentinel.next);

        /* Change the prev of the original first node**/
        sentinel.next.prev = newFirstTNode;

        /* Change the next of sentinel**/
        sentinel.next = newFirstTNode;

        size += 1;
    }

    public void addLast(T item){
        /* Create new Node and put it at the end of the List**/
        TNode newLastNode = new TNode(sentinel.prev, item, sentinel);

        /* Change the next of the original last node**/
        sentinel.prev.next = newLastNode;

        /* Change the prev of the sentinel**/
        sentinel.prev = newLastNode;

        size += 1;
    }

    public boolean isEmpty(){
        if(sentinel.prev == sentinel && sentinel.next == sentinel){
            return true;
        }
        else{
            return  false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        /* Create a intermediate node to print**/
        TNode currNode = new TNode(sentinel, sentinel.next.item, sentinel.next.next);

        /* Move to the next Node after printing**/
        while(currNode != sentinel){
            System.out.print(currNode.item + " ");
            currNode = currNode.next;
        }
    }

    public T removeFirst(){
        /* If the list is empty, return null**/
        if(isEmpty()){
            return null;
        }
        T removeitem = sentinel.next.item;

        /* Replace the prev of the second node with sentinel**/
        sentinel.next.next.prev = sentinel;

        /* Replace the next of the sentinel with the second node**/
        sentinel.next = sentinel.next.next;

        size -= 1;

        return removeitem;
    }

    public T removeLast(){
        /* If the list is empty, return null**/
        if(isEmpty()){
            return null;
        }
        T removeitem = sentinel.prev.item;

        /* Replace the next of The second to last node with sentinel**/
        sentinel.prev.prev.next = sentinel;

        /* Replace the prev of the sentinel with the second to last node**/
        sentinel.prev = sentinel.prev.prev;

        size -= 1;
        return  removeitem;
    }

    /* Get the item by the index with iteration**/
    public T get(int index){
        /* Boundary condition check**/
        if(index >= size){
            return null;
        }
        T returnitem = null;
        TNode currNode = new TNode(sentinel, sentinel.next.item, sentinel.next.next);
        for(int i = 0; i <= index; i++){
            returnitem = currNode.item;
            currNode = currNode.next;
        }
        return returnitem;
    }

    /* Get the item by the index with recursion**/
    public T getRecursive(int index) {
        /* Boundary condition check*/
        if (index < 0 || index >= size) {
            return null;
        }
        /* Call the helper method**/
        return getRecursiveHelper(sentinel, index);
    }

    /* Helper of the recursive get method**/
    private T getRecursiveHelper(TNode currP, int index) {
        /* Stop the circumstance when index decrease to 0;**/
        if (index == 0) {
            return currP.item;
        }
        /* Keep calling the helper method until the index decrease to 0. **/
        return (T) getRecursiveHelper(currP.next, index - 1);
    }

    /* Deep copy the input LinkListDeque.**/
    public LinkedListDeque(LinkedListDeque<T> other){
        /* Create new sentinel**/
        this.sentinel = new TNode(null, null, null);
        this.sentinel.prev = sentinel;
        this.sentinel.next = sentinel;

        /* Make a intermediate pointer and item**/
        TNode currP = other.sentinel;
        T curritem = null;

        /* Use iteration to copy the list one by one**/
        for(int i = 0; i < other.size; i++){
            curritem = currP.next.item;
            this.addLast(curritem);
            currP = currP.next;
        }
    }

}
