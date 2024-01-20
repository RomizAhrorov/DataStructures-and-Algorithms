package ArrayADT;

public class Array<T extends Comparable<T>> {
    private T[] array;
    private final int size;
    private int length;

    private void swap(int index1, int index2) {
        T temp = this.array[index1];
        this.array[index1] = this.array[index2];
        this.array[index2] = temp;
    }

    public Array() {
        this.size = 100;
        this.array = (T[]) new Comparable[this.size];
        this.length = 0;
    }

    public Array(int size) {
        if(size < 0)
            throw new IllegalArgumentException("Initial capacity cannot be negative: " + size);

        this.size = size;
        this.array = (T[]) new Comparable[this.size];
        this.length = 0;
    }

    public void display() {
        System.out.println("Elements of the array are:");
        for(int i = 0; i < this.length; i++) {
            if(i == this.length-1) System.out.println(this.array[i]);
            else System.out.print(this.array[i] + " ");
        }
    }

    public void append(T newValue) {
        this.array[this.length] = newValue;
        this.length++;
    }

    public void insert(int index, T newValue) {
        if(index < 0 || index > this.length)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + this.length);

        for(int i = this.length - 1; i >= index; i--) {
            this.array[i+1] = this.array[i];
        }
        this.array[index] = newValue;
        this.length++;
    }

    public T delete(int index) {
        if(index < 0 || index >= this.length)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + this.length);

        T deletedValue = this.array[index];
        for(int i = index; i < this.length - 1; i++) {
            this.array[i] = this.array[i+1];
        }
        this.length--;

        return deletedValue;
    }

    public int searchLinear(T searchedValue) {
        int foundIndex = -1;
        for(int i = 0; i < this.length; i++) {
            if(searchedValue == this.array[i]) {
                foundIndex = i;
                break;
            }
        }

        return foundIndex;
    }

    public int searchBinary(T searchedValue) {
        int low = 0, high = this.length - 1, mid, comparison;

        while (low <= high) {
            mid = (low + high) / 2;
            comparison = searchedValue.compareTo(this.array[mid]);
            if(comparison == 0)
                return mid;
            else if (comparison > 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    public T get(int index) {
        if(index < 0 || index >= this.length)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + this.length);
        return this.array[index];
    }

    public void set(int index, T updatedValue) {
        if(index < 0 || index >= this.length)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + this.length);
        this.array[index] = updatedValue;
    }

    public T max() {
        T maxValue = this.array[0];
        int comparison;
        for(int i = 0; i < this.length; i++) {
            comparison = this.array[i].compareTo(maxValue);
            if(comparison > 0)
                maxValue = this.array[i];
        }

        return maxValue;
    }

    public T min() {
        T minValue = this.array[0];
        int comparison;
        for(int i = 0; i < this.length; i++) {
            comparison = this.array[i].compareTo(minValue);
            if(comparison < 0)
                minValue = this.array[i];
        }

        return minValue;
    }

    public void reverse() {
        int i = 0, j = this.length - 1;
        while(i < j) {
            swap(i++, j--);
        }
    }


}
