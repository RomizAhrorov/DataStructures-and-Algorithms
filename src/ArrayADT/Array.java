package ArrayADT;

import java.awt.*;

public class Array {
    private int[] array;
    private final int size;
    private int length;

    private void swap(int index1, int index2) {
        int temp = this.array[index1];
        this.array[index1] = this.array[index2];
        this.array[index2] = temp;
    }

    public Array() {
        this.size = 100;
        this.array = new int[this.size];
        this.length = 0;
    }

    public Array(int size) {
        if(size < 0)
            throw new IllegalArgumentException("Initial capacity cannot be negative: " + size);

        this.size = size;
        this.array = new int[this.size];
        this.length = 0;
    }

    public void display() {
        System.out.println("Elements of the array are:");
        for(int i = 0; i < this.length; i++) {
            if(i == this.length-1) System.out.println(this.array[i]);
            else System.out.print(this.array[i] + " ");
        }
    }

    public void append(int newValue) {
        this.array[this.length] = newValue;
        this.length++;
    }

    public void insert(int index, int newValue) {
        if(index < 0 || index > this.length)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + this.length);

        for(int i = this.length - 1; i >= index; i--) {
            this.array[i+1] = this.array[i];
        }
        this.array[index] = newValue;
        this.length++;
    }

    public int delete(int index) {
        if(index < 0 || index >= this.length)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + this.length);

        int deletedValue = this.array[index];
        for(int i = index; i < this.length - 1; i++) {
            this.array[i] = this.array[i+1];
        }
        this.length--;

        return deletedValue;
    }

    public int searchLinear(int searchedValue) {
        int foundIndex = -1;
        for(int i = 0; i < this.length; i++) {
            if(searchedValue == this.array[i]) {
                foundIndex = i;
                break;
            }
        }

        return foundIndex;
    }

    public int searchBinary(int searchedValue) {
        int low = 0, high = this.length - 1, mid;

        while (low <= high) {
            mid = (low + high) / 2;
            if (searchedValue == this.array[mid])
                return mid;
            else if (searchedValue > this.array[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    public int get(int index) {
        if(index < 0 || index >= this.length)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + this.length);
        return this.array[index];
    }

    public void set(int index, int updatedValue) {
        if(index < 0 || index >= this.length)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + this.length);
        this.array[index] = updatedValue;
    }

    public int max() {
        int maxValue = this.array[0];
        for(int i = 0; i < this.length; i++) {
            if(this.array[i] > maxValue)
                maxValue = this.array[i];
        }

        return maxValue;
    }

    public int min() {
        int minValue = this.array[0];
        for(int i = 0; i < this.length; i++) {
            if(this.array[i] < minValue)
                minValue = this.array[i];
        }

        return minValue;
    }

    public void reverse() {
        int i = 0, j = this.length - 1;
        while(i < j)
            swap(i++, j--);
    }

    public void shiftLeft(int shiftCount) {
        if (shiftCount > this.length) {
            throw new IllegalArgumentException("The number of shifts cannot be greater than the length of the array");
        }
        for(int i = 0; i < this.length; i++) {
            if(i >= this.length - shiftCount)
                this.array[i] = 0;
            else
                this.array[i] = this.array[i+shiftCount];
        }
    }

    public void shiftRight(int shiftCount) {
        if (shiftCount > this.length) {
            throw new IllegalArgumentException("The number of shifts cannot be greater than the length of the array");
        }
        for(int i = this.length - 1; i >= 0; i--) {
            if(i < shiftCount)
                this.array[i] = 0;
            else
                this.array[i] = this.array[i-shiftCount];
        }
    }

    public void rotateLeft(int rotateCount) {
        int rotates = rotateCount % this.length;
        int[] temp = new int[this.length];
        for(int i = 0; i < this.length; i++) {
            if(i >= this.length - rotates)
                temp[i] = this.array[i+rotates-this.length];
            else
                temp[i] = this.array[i+rotates];
        }
        System.arraycopy(temp, 0, this.array, 0, this.length);
    }

    public void rotateRight(int rotateCount) {
        int rotates = rotateCount % this.length;
        int[] temp = new int[this.length];
        for(int i = this.length - 1; i >= 0; i--) {
            if(i < rotates)
                temp[i] = this.array[i-rotates+this.length];
            else
                temp[i] = this.array[i-rotates];
        }
        System.arraycopy(temp, 0, this.array, 0, this.length);
    }

    public int sum() {
        int sumValue = 0;
        for(int i = 0; i < this.length; i++)
            sumValue += this.array[i];

        return sumValue;
    }

    public float average() {
        return (float) this.sum() / (float) this.length;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    public boolean isSorted() {
        if(this.isEmpty() || this.length == 1) {
            return true;
        }

        boolean isAscending = this.array[0] < this.array[1];

        for(int i = 0; i < this.length - 1; i++) {
            if(isAscending && this.array[i] > this.array[i+1]) {
                return false;
            }
            if(!isAscending && this.array[i] < this.array[i+1]) {
                return false;
            }
        }

        return true;
    }

    public void append(int[] arr, int length) {
        if(this.length + length > this.size)
            throw new IllegalArgumentException("There is no enough space in the array to append given array.");

        if (length >= 0) System.arraycopy(arr, 0, this.array, this.length, length);
        this.length += length;
    }
}
