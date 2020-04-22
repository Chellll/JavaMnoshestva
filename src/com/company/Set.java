package com.company;

import java.util.ArrayList;
import java.util.List;

public class Set<T>{

    private ArraySupplier<T> supplier;

    private T[] items;
    private int size;
    private int count;

    public Set(ArraySupplier<T> supplier, int Size){
        this.size = Size;
        this.supplier = supplier;

        items = supplier.get(Size);
    }

    public boolean Contains(T item){
        for(int i = 0; i < count; i++){
            if(items[i].equals(item))
                return true;
        }
        return false;
    }

    public void Add(T item){

        if(!Contains(item)){
            if(count == size){
                T[] copyItems = supplier.get(size);
                for(int i = 0; i < size; i++){
                    copyItems[i] = items[i];
                }

                items = supplier.get(size + 10);

                for(int i = 0; i < size; i++){
                    items[i] =copyItems[i];
                }
                size += 10;
            }

            items[count] = item;
            count++;
        }
    }

    public Set<T> Copy(){
        Set<T> arr = new Set<T>(supplier, size);

        for(int i = 0; i < count; i++){
            arr.Add(items[i]);
        }

        return arr;
    }

    public void AddRange(T[] t){
        for(T e : t){
            Add(e);
        }
    }

    public void Print(){
        System.out.print("{ ");
        for (T t : items){
            System.out.print(t + " ");
        }
        System.out.print("}");
    }

    public boolean Remove(T item){
        if(Contains(item)){
            int indRemove = GetIndex(item);
            for(int i = indRemove; i < count-1; i++ ){
                items[i] = items[i+1];
            }

            count--;
            return true;
        }
        return false;
    }

    public int GetIndex(T elem){
        for(int i = 0; i < count; i++){
            if(elem.equals(items[i]))
                return i;
        }
        return -1;
    }

    //Объединение 1
    public static <E> Set<E> UnionFirst(Set<E> first, Set<E> second){
        Set<E> arr = first.Copy();
        for(int i = 0; i < second.count; i++){
            if(!first.Contains(second.items[i]))
                arr.Add(second.items[i]);
        }
        return arr;
    }

    //Объединение 2
    public Set<T> UnionSecond(Set<T> second){
        Set<T> result = this.Copy();
        for(int i = 0; i < second.count; i++){
            if(!Contains(second.items[i]))
                result.Add(second.items[i]);
        }
        return result;
    }

    //пересечение
    public Set<T> Intersection(Set<T> other){
        Set<T> arr = this.Copy();
        for(int i = 0; i < other.count; i++){
            if(!other.Contains(items[i]))
                arr.Remove(other.items[i]);
        }
        return arr;
    }

    //дополнение
    public Set<T> Difference(Set<T> other){
        Set<T> result = this.Copy();
        for(int i = 0; i < other.count; i++){
            result.Remove(other.items[i]);
        }
        return result;
    }

    //Симметрическая разность
    public Set<T> SymmitricalDifference(Set<T> other){
        Set<T> union = UnionSecond(other);
        Set<T> intersection = Intersection(other);
        return union.Difference(intersection);
    }

    public int Factorial(int a,  int b){
        if (b > 1){
            return a * Factorial(a * b, b-1);
        }
        else {
            return 1;
        }
    }

    public int[] Sochet(int[] objects, int k){
        int count = 0;
        int[] a = new int[k];


        for (int i = 0; i < k; i++)
        {
            a[i] = 0;
        }

        while (a[k - 1] != objects.length)
        {
            for (int i = k - 1; i >= 0; i--)
            {
                System.out.print(a[i]);
            }

            a[0]++;

            for (int i = 0; i < k; i++)
            {
                if (a[i] == objects.length)
                {
                    if (a[i + 1] == objects.length)


                        a[i + 1]++;

                    for (int j = 0; j <= count; j++)
                    {
                        a[j] = 0;
                    }

                    count++;
                    break;
                }
            }

            System.out.println();
        }
        return a;
    }

    public List<Set<T>> Sochi(int k)
    {
        List<Set<T>> list = new ArrayList<>();
        int[] a = new int[k];

        for (int i = 0; i < k; i++)
        {
            a[i] = i;
        }

        int p = k - 1;

        while (p >= 0)
        {
            Set<T> set = new Set<T>(supplier, k);

            for (int i = 0; i < k; i++)
            {
                set.Add(items[a[i]]);
            }

            set.Print();

            if (a[k - 1] == count)
            {
                p = p - 1;
            }
            else
            {
                p = k - 1;
            }

            if (p >= 0)
            {
                for (int i = k - 1; i >= p; i--)
                {
                    a[i] = a[p] + i - p + 1;
                }
            }
        }

        return list;
    }


    public T[] getItems() {
        return items;
    }

    public void setItems(T[] items) {
        this.items = items;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
