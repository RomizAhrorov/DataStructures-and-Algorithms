import ArrayADT.Array;

public class Main {
    public static void main(String[] args) {
        Array obj = new Array(15);
        for(int i = 0; i < 5; i++) {
            obj.append(2*i+3);
        }
        obj.display();
        int[] test = {1,7,9,10,11};
        obj.merge(test, test.length);
        obj.display();
        obj.findDuplicates();
    }
}