package structure.heap.heap_print;


/**
 * @author �
 * @school ����������ѧ
 * @date 2017/12/3 16:33
 * @desc ��ӡ��
 **/
public class HeapPrint<T extends Comparable> {
    private T[] data;
    private int size;
    private int capacity;

    public HeapPrint(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.data = (T[]) new Comparable[capacity + 1];
    }

    public HeapPrint(T[] arr) {//heapify,���齨��
        capacity = arr.length;
        data = (T[]) new Comparable[capacity + 1];
        System.arraycopy(arr, 0, data, 1, arr.length);
        size = arr.length;
        for (int i = size / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    public int size() {
        return this.size;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T seekMax() {
        return data[1];
    }

    public void swap(int i, int j) {
        if (i != j) {
            T temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
    }

    public void insert(T item) {
        size++;
        data[size] = item;
        shiftUp(size);
    }

    public T popMax() {
        swap(1, size--);
        shiftDown(1);
        return data[size + 1];
    }

    public void shiftUp(int child) {
        while (child > 1 && data[child].compareTo(data[child / 2]) > 0) {
            swap(child, child / 2);
            child /= 2;
        }
    }

    /**
     * @param a data������ĳ��Ԫ�ص��½Ǳ�
     * @param b data������ĳ��Ԫ�ص��½Ǳ�
     * @return �ĸ�Ԫ�ش�ͷ����ĸ����½Ǳ�
     */
    private int max(int a, int b) {
        if (data[a].compareTo(data[b]) < 0) {//���data[b]��
            return b;//����b
        } else {//���data[a]��
            return a;//����a
        }
    }

    /**
     * @param a data������ĳ��Ԫ�ص��½Ǳ�
     * @param b data������ĳ��Ԫ�ص��½Ǳ�
     * @param c data������ĳ��Ԫ�ص��½Ǳ�
     * @return �ĸ�Ԫ�ش�ͷ����ĸ����½Ǳ�
     */
    private int max(int a, int b, int c) {
        int biggest = max(a, b);
        biggest = max(biggest, c);
        return biggest;
    }

    public void shiftDown(int father) {
        while (true) {
            int lchild = father * 2;
            int rchild = father * 2 + 1;
            int newFather = father;//���︳����ֵ����ν��������������return�ĳ�break,�Ǿͱ��븳ֵ��

            if (lchild > size) {//���û�����Һ���
                return;
            } else if (rchild > size) {//���û���Һ���
                newFather = max(father, lchild);
            } else {//��������Һ���
                newFather = max(father, lchild, rchild);
            }

            if (newFather == father) {//���ԭ������������������ü����������
                return;
            } else {//���ڵ㲻�������Ѵ�ĺ��ӽ���������Ȼ��������¶ѵ�����ֱ����������Ϊֹ
                swap(newFather, father);
                father = newFather;//�൱�ڼ���shiftDown(newFather)������newFatherԭ����father�����ӣ��Ǿ��൱��shiftDown(2*father)
            }
        }
    }

    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        int len = arr.length;
        HeapPrint<T> maxHeap = new HeapPrint<>(arr);
        maxHeap.printHeap();
        for (int i = len - 1; i >= 0; i--) {
            arr[i] = maxHeap.popMax();
        }
    }

    public static void printArr(Object[] arr) {
        for (Object o : arr) {
            System.out.print(o);
            System.out.print("\t");
        }
        System.out.println();
    }

    public void printSpace(int n) {//��ӡn���ո�(�������á�\t��������)
        for (int i = 0; i < n; i++) {
            System.out.printf("%3s", "");
        }
    }


    //�����ӡ��
    public void printHeap() {
        int lines = (int) (Math.log(size) / Math.log(2)) + 1;//lines�Ƕѵ��ܲ���
        int spaceNum = (int) (Math.pow(2, lines) - 1);//��һ��ѿո���
        //line:ÿ��Ĳ���
        for (int i = 1, line = 1; i <= size; line++, spaceNum /= 2) {
            for (int j = (int) Math.pow(2, line - 1); j <= Math.min(size, (int) Math.pow(2, line) - 1); j++) {
                printSpace(spaceNum);
                System.out.printf("%3s", data[j]);//��ӡ����
                System.out.printf("%3s", "");    //Ҫ�ǰ�%3s��������\t���ر��ɢ,���ÿ�
                printSpace(spaceNum);
                i++;//ÿ��ӡһ��Ԫ�ؾ� + 1
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 5, 9, 7, 3, 4, 8, 6, 2, 11, 15, 19, 17, 18, 16, 13, 14, 10, 20};
        sort(arr);
    }
}
