package kds.generic.pds;

/**
 * @ClassName KdsNode
 * @Description 单向链表节点
 * @Author maybe a function name
 * @Date 2023/6/10 7:25
 **/
public class KdsNode <DATA>{
    public DATA data;
    public KdsNode<DATA> next;
    public KdsNode(DATA data){
        this.data = data;
        this.next = null;
    }
    public KdsNode(){
        this.data = null;
    }
}
