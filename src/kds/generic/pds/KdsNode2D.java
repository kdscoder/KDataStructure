package kds.generic.pds;

/**
 * @ClassName KdsNode2D
 * @Description 双向链表节点
 * @Author maybe a function name
 * @Date 2023/6/10 7:25
 **/
public class KdsNode2D<DATA> extends KdsNode<DATA> {
    public KdsNode2D<DATA> prev;
    public KdsNode2D(DATA data) {
        super(data);
        this.prev = null;
    }
    public KdsNode2D(){
        super();
        this.prev = null;
    }
}
