import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Trie {
    Node root;

    public class Node {
        public Node[] next;
        public boolean isword;

        public Node() {
            next = new Node[27];
            isword = false;
        }
        public void branchsearch(String path, String wrd, String[] wordsArray) {
            char[] charPath = path.toCharArray();
            if (charPath.length == 0 && this.isword == true) {
                newword(wrd, wordsArray);
            }
            for (int i = 0; i < 3 & charPath.length != 0; i++) {
                int index = index(charPath[0]);
                int cod = index * 3 + i;
                if (this.next[cod] != null) {
                    char letter = code_inv(cod);
                    wrd = wrd + String.valueOf(letter);
                    this.next[cod].branchsearch(path.substring(1), wrd, wordsArray);
                    wrd = wrd.substring(0, wrd.length() - 1);
                }
            }
        }
    }

    public Trie() {
        root = new Node();
    }
    public static int code(char ch) {
        int code;
        switch (ch) {
            case 'a':
                code = 0;
                break;
            case 'b':
                code = 1;
                break;
            case 'c':
                code = 2;
                break;
            case 'd':
                code = 3;
                break;
            case 'e':
                code = 4;
                break;
            case 'f':
                code = 5;
                break;
            case 'g':
                code = 6;
                break;
            case 'h':
                code = 7;
                break;
            case 'i':
                code = 8;
                break;
            case 'j':
                code = 9;
                break;
            case 'k':
                code = 10;
                break;
            case 'l':
                code = 11;
                break;
            case 'm':
                code = 12;
                break;
            case 'n':
                code = 13;
                break;
            case 'o':
                code = 14;
                break;
            case 'p':
                code = 15;
                break;
            case 'r':
                code = 16;
                break;
            case 's':
                code = 17;
                break;
            case 't':
                code = 18;
                break;
            case 'u':
                code = 19;
                break;
            case 'v':
                code = 20;
                break;
            case 'x':
                code = 21;
                break;
            case 'y':
                code = 22;
                break;
            case 'z':
                code = 23;
                break;
            case 'ä':
                code = 24;
                break;
            case 'ö':
                code = 25;
                break;
            default:
                code = 26;
        }
        return code;
    }
    public static char code_inv ( int n){
        char code;
        switch (n) {
            case 0:
                code = 'a';
                break;
            case 1:
                code = 'b';
                break;
            case 2:
                code = 'c';
                break;
            case 3:
                code = 'd';
                break;
            case 4:
                code = 'e';
                break;
            case 5:
                code = 'f';
                break;
            case 6:
                code = 'g';
                break;
            case 7:
                code = 'h';
                break;
            case 8:
                code = 'i';
                break;
            case 9:
                code = 'j';
                break;
            case 10:
                code = 'k';
                break;
            case 11:
                code = 'l';
                break;
            case 12:
                code = 'm';
                break;
            case 13:
                code = 'n';
                break;
            case 14:
                code = 'o';
                break;
            case 15:
                code = 'p';
                break;
            case 16:
                code = 'r';
                break;
            case 17:
                code = 's';
                break;
            case 18:
                code = 't';
                break;
            case 19:
                code = 'u';
                break;
            case 20:
                code = 'v';
                break;
            case 21:
                code = 'x';
                break;
            case 22:
                code = 'y';
                break;
            case 23:
                code = 'z';
                break;
            case 24:
                code = 'ä';
                break;
            case 25:
                code = 'ö';
                break;
            default:
                code = 'å';
        }
        return code;
    }
    public static char code_inv_asci(int n){
        if (n < 16){
            return (char) (n+97);
        } else if (n>=16 && n<21) {
            return (char) (n+98);
        } else if (n >= 21 && n<24) {
            return (char) (n+99);
        } else if (n == 24) {
            return 'ä';
        }else if (n == 25){
            return 'å';
        } else if (n == 26) {
            return 'ö';
        }else {
            return 'ñ';
        }
    }
    public static char key (char ch){
        int cod = code(ch);
        char keys = '-';
        if (cod>=0 && cod < 3){
            keys = '1';
        } else if (cod >=3 && cod < 6){
            keys = '2';
        }else if (cod >=6 && cod < 9){
            keys = '3';
        }else if (cod >=9 && cod < 12){
            keys = '4';
        }else if (cod >=12 && cod < 15){
            keys = '5';
        }else if (cod >=15 && cod < 18){
            keys = '6';
        }else if (cod >=18 && cod < 21){
            keys = '7';
        }else if (cod >=21 && cod < 24){
            keys = '8';
        }else{
            keys = '9';
        }
        return keys ;
    }
    //as '1' corresponds 49 in ASCII
    public static String encode ( String word){
        String encoded = null;
        char [] characters = word.toCharArray();
        for (int i = 0; i < characters.length; i++){
            char cod = key(characters[i]);
            if (encoded == null)
                encoded = String.valueOf(cod);
            else
                encoded = encoded + String.valueOf(cod);
        }
        return encoded;
    }
    public static int index(char key){
        return ((int) key)-49;
    }

//    public static int [] pos_index (char key) {
//        int [] pos_index = {(int) key-49,(int) key-48, (int) key-47};
//        return pos_index;
//    }

    //this function will add a new word to a current list of words
    public static void newword (String newword, String [] existing){
        for (int i = 0; i < existing.length; i++){
            if (existing[i] == null){
                existing[i] = newword;
                break;
            }
        }
    }
    public void add(String word){
        Node nxt = this.root;
        for (int i = 0 ; i < word.length();i++) {
            char ch = word.charAt(i);
            int code = code(ch);
            if (nxt.next[code] == null){
                nxt.next[code] = new Node();
            }
            nxt = nxt.next[code];
        }
        nxt.isword = true;
    }

    public String[] search(String word) {
        if (root == null) {
            return new String[]{""};
        }
        String new_word = "";
        //because of how the tree is structured, the maximum ammount of words formed is
        String[] wordsArray = new String[3 ^ word.length()];
        root.branchsearch(word, new_word, wordsArray);
        return wordsArray;
    }
    public void swedish_dictionary(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                this.add(line);
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("the file " + file + " does not exist");
        }
    }
    public void test_dictionary(String file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String code = encode(line);
                System.out.println(Arrays.toString(search(code)));
            }
        } catch (Exception e) {
            System.out.println("the file " + file + "does not exist");
        }

    }

    public static void main(String[] args){
        Trie t = new Trie();
        t.swedish_dictionary("words.txt");
        t.test_dictionary("words.txt");
        String[] list = t.search("1234");
        for (int i = 0; i < list.length; i++){
            System.out.println(list[i]);
        }
    }

//
//    public void nodeSearch(String path, String wrd, String[] wordsArray) {
//        char[] charPath = path.toCharArray();
//        if (charPath.length == 0 && this.word == true) {
//            newword(wrd, wordsArray);
//        }
//        for (int i = 0; i < 3 & charPath.length != 0; i++) {
//            int index = index(charPath[0]);
//            int code = index * 3 + i;
//            if (this.next[code] != null) {
//                char letter = decode(code);
//                wrd = wrd + String.valueOf(letter);
//                this.next[code].nodeSearch(path.substring(1), wrd, wordsArray);
//                wrd = wrd.substring(0, wrd.length() - 1);
//            }
//        }
//    }
//
//    public String[] search(String path) {
//        if (root == null) {
//            return null;
//        }
//        String wrd = "";
//        String[] wordsArray = new String[8000];
//        root.nodeSearch(path, wrd, wordsArray);
//        return wordsArray;
//    }
}