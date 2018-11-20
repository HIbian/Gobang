package com.hibian.tree;

public class TreeImpl implements Tree {

    private Node root;
    public Node getRoot() {
        return root;
    }

    public Node find(Integer key) {
        Node next= root;
        while (next!=null){
            if (next.getData()>key){//搜索值小于根节点,去左节点找
                    next = next.getLeftNode();
            }else if (next.getData()<key){
                    next = next.getRightNode();
            }else{
                return next;
            }
        }
        return null;
    }
    public Node findparent(Integer key){
        Node next= root;
        Node parent = null;
        while (next!=null){
            if (next.getData()>key){//搜索值小于根节点,去左节点找
                parent = next;//当前为父节点
                next = next.getLeftNode();
            }else if (next.getData()<key){
                parent = next;//当前为父节点
                next = next.getRightNode();
            }else{//找到值了,返回父节点
                return parent;
            }
        }
        return null;
    }

    public boolean insert(Integer key) {
        Node newNode = new Node(key);//创建新的节点
        if (root==null){//根节点没有的话,在根节点添加
            root=newNode;
            return true;
        }

        Node nextNode = root;//根节点不为空
        //根节点有东西,比较大小,判断需要在左节点添加还是右节点,为空的话直接添加,相等的话返回false
        while (nextNode!=null){
            //判断是否相等
            if(nextNode.getData().equals(key)){//有重复值,可以选择count+1,也可以选择直接返回false,这里返回false,添加失败
                return false;
            }else if (nextNode.getData()>key){//节点值大于KEY,应该添加到左节点,为空的话直接添加,不为空的话,继续判断
                if (nextNode.getLeftNode()==null){
                    nextNode.setLeftNode(newNode);
                    return true;
                }
                nextNode = nextNode.getLeftNode();
            }else{
                if (nextNode.getRightNode()==null){
                    nextNode.setRightNode(newNode);
                    return true;
                }
                nextNode = nextNode.getRightNode();
            }
        }
        return true;
    }

    //遍历
    public void infixOrder(Node current){//中序遍历
        if (current!=null){
            infixOrder(current.getLeftNode());
            System.out.print(current.getData()+" ");
            infixOrder(current.getRightNode());
        }
    }
    public void preOrder(Node current){//前序遍历
        if (current!=null){
            System.out.print(current.getData()+" ");
            preOrder(current.getLeftNode());
            preOrder(current.getRightNode());
        }
    }
    public void postOrder(Node current){//后序遍历
        if (current!=null){
            postOrder(current.getLeftNode());
            postOrder(current.getRightNode());
            System.out.print(current.getData()+" ");
        }
    }

    //查找最大最小值
    public Integer getMax(){
        if (root==null){
            return null;
        }
        Node current = root;
        while (current.getRightNode()!=null){
            current = current.getRightNode();
        }
        return current.getData();
    }
    public Integer getMin(){
        if (root==null){
            return null;
        }
        Node current = root;
        while (current.getLeftNode()!=null){
            current = current.getLeftNode();
        }
        return current.getData();
    }

    //删除
    public boolean delete(Integer key) {
        if (root==null){
            return false;
        }
        //找到该节点,以及父节点
        Node target = find(key);
        Node parent = findparent(key);//为空的话表示该节点为根节点
        if (target==null){        //判断是否为空
            return false;
        }

        //判断类型
        int count = 0;
        if (target.getLeftNode()!=null){
            count++;
        }
        if (target.getRightNode()!=null){
            count++;
        }

        if (count==0){//1.目标没有子节点,父节点将其置为空
            //若是根节点只需要置为空即可
            if (parent==null){
                root=null;
                return true;
            }
            if (parent.getLeftNode().getData().equals(key)){
                parent.setLeftNode(null);
            }else{
                parent.setRightNode(null);
            }
            return true;
        }else if (count==1){//2.目标有一个子节点
            Node targetChild = target.getLeftNode()==null?target.getRightNode():target.getLeftNode();//获取目标节点子节点
            //接到目标父节点的子节点上
            if (parent.getLeftNode().getData().equals(key)){
                parent.setLeftNode(targetChild);
            }else{
                parent.setRightNode(targetChild);
            }
            return true;
        }else{//目标有两个子节点
            //获取后继节点
        }

        return false;
    }
    public Node getNextValue(Node target){//获取中序遍历后继节点
        if (target == null) {//输入值为空
            return null;
        }
        if (target.getRightNode()==null){//没有右子叶,那么就没有中序后继节点
            return null;
        }
        Node nextValue = target.getRightNode();//目标的右节点
        while (nextValue!=null){//循环条件其实没啥用
            if (nextValue.getLeftNode()==null){//当前节点左子叶为空,则为后继节点
                return nextValue;
            }
            nextValue = nextValue.getLeftNode();//获取当前节点左子叶
        }
        return null;
    }
}
