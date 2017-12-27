package structure.binarytree.c_BinarySearchTreeInsert;

/**
 * @author �
 * @school ����������ѧ
 * @date 2017/12/4 17:06
 * @desc ����������--����ڵ�
 **/
public class BST<Key extends Comparable<? super Key>, Value> {
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    //������������в���ڵ�
    private Node insert(Node root, Key key, Value value) {
        //�����ǰrootΪ��ʱ,˵���Ѿ��ҵ�Ӧ�ò����λ��,���Ҷ�������û�����Ԫ��,��ʱӦ���½�һ���ڵ�,�����������
        if (root == null) {
            size++;
            root = new Node(key, value);
        }
        if (key.compareTo(root.key) == 0) {//����ҵ���ȵ�ֵ,���串��
            root.value = value;
        } else if (key.compareTo(root.key) < 0) {//���keyֵС��rootֵ,��root=root.left,ȥ������������
            root = insert(root.left, key, value);
        } else { //���keyֵ����rootֵ,root=root.right,��ȥ������������
            root = insert(root.right, key, value);
        }
        return root;
    }

    public static void main(String[] args) {

    }
}
