package structure.binarytree.d_BinarySearchTree;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author �
 * @school ����������ѧ
 * @date 2017/12/4 20:45
 * @desc �ļ���ز���
 **/
public class FileOperations {
    public static boolean readFile(String filename, Vector<String> words) {
        if (filename == null) {
            System.out.println("filename is null");
            return false;
        }

        //�ļ���ȡ
        Scanner scanner;
        try {
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Cannot open" + filename);
            return false;
        }

        //�򵥷ִ�
        //����ִʷ�ʽ��Լ�ª,û�п����ı������е���������
        //������ֻ��demoչʾ��
        if (scanner.hasNextLine()) {

            String contents = scanner.useDelimiter("\\A").next();

            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); )
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else
                    i++;
        }
        return true;
    }

    private static int firstCharacterIndex(String s, int start) {
        for (int i = start; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                return i;
            }
        }
        return s.length();
    }
}
