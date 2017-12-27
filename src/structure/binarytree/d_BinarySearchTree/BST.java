package structure.binarytree.d_BinarySearchTree;

/**
 * @author �
 * @school ����������ѧ
 * @date 2017/12/4 17:50
 * @desc
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

    public boolean contain(Key key) {
        return contain(root, key);
    }

    public Value search(Key key) {
        return search(root, key);
    }

    //��rootΪ���ڵ�Ķ��������Ƿ���ڼ�Ϊkey�Ľ��,���ؽ���value
    private Value search(Node root, Key key) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.key) == 0) {
            return root.value;
        } else if (key.compareTo(root.key) < 0) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    //��rootΪ���ڵ�Ķ��������Ƿ���ڼ�Ϊkey�Ľ��,����true/false
    private boolean contain(Node root, Key key) {
        if (root == null) {
            return false;
        }
        if (root.value == key) {
            return true;
        } else if (key.compareTo(root.key) < 0) {
            return contain(root.left, key);
        } else {
            return contain(root.right, key);
        }
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
        int N = 1000000;

        // ����һ�����飬����[0...N)������Ԫ��
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++)
            arr[i] = new Integer(i);

        // ��������˳��
        for (int i = 0; i < N; i++) {
            int pos = (int) (Math.random() * (i + 1));
            Integer t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = arr[pos];
        }
        // ��������ʵ�ֵĶ�������������ƽ���������
        // �����������˳�����һ�����ݣ����ǵĶ������������˻���Ϊһ������
        // ƽ���������ʵ�֣�����������γ���û���漰��
        // ����Ȥ��ͬѧ���Բ鿴������ѧ����������ʵ��
        // �Ժ��л��ᣬ�һ��ڱ�Ŀγ������ҽ���ƽ���������ʵ�ֵģ���


        // ���ǲ����õĵĶ����������ļ�����ΪInteger��ֵ����ΪString
        // ��ֵ�Ķ�Ӧ��ϵΪÿ�����Ͷ�Ӧ����������͵��ַ���
        BST<Integer, String> bst = new BST<Integer, String>();
        for (int i = 0; i < N; i++)
            bst.insert(new Integer(arr[i]), Integer.toString(arr[i]));

        // ��[0...2*N)���������Ͳ����ڶ����������в���
        // ��i��[0...N)֮�䣬���ܲ��ҵ���������Ӧ���ַ���
        // ��i��[N...2*N)֮�䣬����Ϊnull
        for (int i = 0; i < 2 * N; i++) {
            String res = bst.search(new Integer(i));
            if (i < N)
                assert res.equals(Integer.toString(i));
            else
                assert res == null;
        }
    }
}
