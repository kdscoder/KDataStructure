package kds.lds.map;

import kds.util.BaseUtil;

public class KdsHashMap implements  KdsHashMapAbility{
    public static final int DEFAULT_LENGTH = 10;
    // 初始化的HashMap内部数组
    KdsHashMapNode[] innerArray;
    // 用于更新的HashMap内部数组
    KdsHashMapNode[] newInnerArray;
    int length;
    int hashNum;
    int counter = -1;
    KdsHashMapNode possibleNode;
    public KdsHashMap(int length){
        this.innerArray = new KdsHashMapNode[length];
        this.length = 0;
        this.hashNum = innerArray.length;
    }
    @Override
    public Object get(Object key) {
        int index = BaseUtil.simpleHash(this.hashNum, key.hashCode());
        if (this.innerArray[index] == null) {
            return null;
        }
        KdsHashMapNode temp = this.innerArray[index];
        while (temp != null) {
            if (temp.tag.equals(key)) {
                return temp.data;
            }
            temp = (KdsHashMapNode) temp.next;
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        int index = BaseUtil.simpleHash(this.hashNum, key.hashCode());
        this.length++;
        KdsHashMapNode newNode =  new KdsHashMapNode((String) value, (String) key);
        if (this.innerArray[index] == null) {
            this.innerArray[index] = newNode;
        }
        // 如果已经存在了一样的值，则更新
        else if (containsKey(key)) {
            this.possibleNode.data = (String) value;
        } else {
            // 否则进行头插入
            newNode.next = this.innerArray[index].next;
            this.innerArray[index].next = newNode;
        }
        reHash();
    }

    private void put(Object key, Object value, KdsHashMapNode[] newArray) {
        int index = BaseUtil.simpleHash(this.hashNum, key.hashCode());
        KdsHashMapNode newNode =  new KdsHashMapNode((String) value, (String) key);
        if (newArray[index] == null) {
            newArray[index] = newNode;
            return;
        }
        // 如果已经存在了一样的值，则更新
        if (containsKey(key, newArray)) {
            this.possibleNode.data = (String) value;
            return;
        }
        // 否则进行头插入
        newNode.next = newArray[index].next;
        newArray[index].next = newNode;
    }

    public boolean remove(Object key){
        if (containsKey(key)) {
            int index = BaseUtil.simpleHash(this.hashNum, possibleNode.tag.hashCode());
            KdsHashMapNode temp = this.innerArray[index];
            if (temp.tag.equals(key)) {
                // 头指针需要进行修改
                this.innerArray[index] = (KdsHashMapNode) temp.next;
                return true;
            }
            while (temp.next != null) {
                if (((KdsHashMapNode)temp.next).tag.equals(key)) {
                    KdsHashMapNode deletedNode = (KdsHashMapNode) temp.next;
                    temp.next = deletedNode.next;
                    deletedNode.next = null;
                    return true;
                }
                temp = (KdsHashMapNode) temp.next;
            }
        }
        return false;
    }

    // 阻塞式rehash
    private void reHash() {
        if ((this.length + 1) * 4 < this.innerArray.length * 3) {
            return;
        }
        this.newInnerArray = new KdsHashMapNode[this.length * 2 + 1];
        this.hashNum = this.newInnerArray.length;
        for (KdsHashMapNode beginNode:this.innerArray) {
            if (beginNode == null) {
                continue;
            }
            KdsHashMapNode temp = beginNode;
            while (temp != null) {
                this.put(temp.tag, temp.data, this.newInnerArray);
                temp = (KdsHashMapNode) temp.next;
            }
        }
        this.innerArray = this.newInnerArray;
        this.newInnerArray = null;
    }

    @Override
    public boolean containsKey(Object key) {
        if (isEmpty()) {
            return false;
        }
        int index = BaseUtil.simpleHash(this.hashNum, key.hashCode());
        if (this.innerArray[index] == null) {
            return false;
        }
        KdsHashMapNode temp = this.innerArray[index];
        while (temp != null) {
            if (temp.tag.equals(key)) {
                this.possibleNode = temp;
                return true;
            }
            temp = (KdsHashMapNode) temp.next;
        }
        return false;
    }

    private boolean containsKey(Object key, KdsHashMapNode[] array) {
        if (isEmpty()) {
            return false;
        }
        int index = BaseUtil.simpleHash(this.hashNum, key.hashCode());
        if (array[index] == null) {
            return false;
        }
        KdsHashMapNode temp = array[index];
        while (temp != null) {
            if (temp.tag.equals(key)) {
                this.possibleNode = temp;
                return true;
            }
            temp = (KdsHashMapNode) temp.next;
        }
        return false;
    }

    public boolean isEmpty(){
        return this.length == 0;
    }

    public int getLength(){
        return this.length;
    }

    public void traversal(){
        for (KdsHashMapNode beginNode:this.innerArray) {
            if (beginNode == null) {
                continue;
            }
            KdsHashMapNode temp = beginNode;
            while (temp != null) {
                System.out.print("(" + temp.tag + ":" + temp.data + ")");
                temp = (KdsHashMapNode) temp.next;
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        KdsHashMap map = new KdsHashMap(3);
/*
        for (int i = 0; i < 500; i++) {
            map.put(String.valueOf(i), "a" + i);
        }
*/
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        map.put("5", "e");
        map.put("6", "f");
        map.traversal();
        map.remove("1");
        map.remove("2");
        map.remove("3");
        map.remove("4");
        map.traversal();
    }
}
