package kds.lds.map;

import kds.pds.linkedlist.KdsNode;

public class KdsHashMapNode extends KdsNode {
    public String tag;

    public KdsHashMapNode(String data, String tag) {
        super(data);
        this.tag = tag;
    }

}
