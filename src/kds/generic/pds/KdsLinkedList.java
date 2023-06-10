package kds.generic.pds;

import java.util.HashMap;

/**
 * @ClassName KdsLinkedList
 * @Description TODO
 * @Author {maybe a function name}
 * @Date 2023/6/10 7:27
 **/
public class KdsLinkedList <DATA>{
    public KdsNode<DATA> head;
    int length;
    public KdsLinkedList (){
        this.head = new KdsNode<>();
        this.length = 0;
    }
    public void insertHead(DATA newItem) {
        KdsNode<DATA> newNode = new KdsNode<>(newItem);
        newNode.next = this.head.next;
        this.head.next = newNode;
    }

    public boolean isEmpty() {
        return this.head.next == null;
    }

    public void traversal(){
        if (isEmpty()) {
            System.out.println("empty");
            return;
        }
        KdsNode<DATA> temp = this.head.next;
        while (temp != null) {
            if (temp.next == null) {
                System.out.println(temp.data);
            } else {
                System.out.print(temp.data + " -> ");
            }
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        KdsLinkedList<String> kdsLinkedList = new KdsLinkedList<>();
        kdsLinkedList.insertHead("a");
        kdsLinkedList.insertHead("b");
        kdsLinkedList.insertHead("c");
        KdsLinkedList<Object> list1 = new KdsLinkedList<>();
        list1.insertHead("a");
        list1.insertHead("b");
        list1.insertHead("c");
        list1.insertHead("d");
        list1.insertHead(1);
        list1.insertHead(new HashMap<>());
        list1.traversal();
//        kdsLinkedList.traversal();
    }
}
