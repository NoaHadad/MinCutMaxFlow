package com.example.noa.flowgraph;

public class NodeEdge {
    protected Edge e;
    NodeEdge prev;
    NodeEdge next;


    NodeEdge() {
        e=null;
        prev=null;
        next=null;
    }

    NodeEdge (Edge e , NodeEdge prev){
        this.e=e;
        this.prev=prev;
        this.next=null;
        if (prev!=null) prev.next=this;
    }

    int getSource(){
        return e.source;
    }

    int getdestination(){
        return e.destination;
    }

    int getLen(){
        return e.length;
    }

    static NodeEdge addEdge(NodeEdge head, Edge e){
        if (head==null) return  new NodeEdge(e, null);
        NodeEdge n=head;
        NodeEdge prev=n;
        while (n!=null) {
            prev=n;
            n=prev.next;
        }
        return  new NodeEdge(e, prev);
    }

    static NodeEdge search(NodeEdge head, int i) {
        while (head != null) {
            if (head.e.destination == i) return head;
            head = head.next;
        }
        return null;

    }


    public void delete(){
        if (prev!=null){ prev.next=next;}
        if (prev==null){
            this.e=next.e;
            this.next=next.next;}
    }

}
