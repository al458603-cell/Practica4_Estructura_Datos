import java.util.Collection;
import java.util.NoSuchElementException;

public class LinkedListWithCursor<T> {
    //Importante: No cambiar ni los nombres ni la visibilidad
    Node<T> left;
    Node<T> right;
    int sizeLeft;
    int sizeRight;

    public LinkedListWithCursor() {
        left = null;
        right = null;
        sizeLeft = 0;
        sizeRight = 0;
    }


    public LinkedListWithCursor(Collection<T> col, int index) {

        if(index < 0 || index > col.size()){
            throw new IndexOutOfBoundsException();
        }

        for(T x : col){
            left = new Node<>(x,left);
            sizeLeft ++;
        }

        for(int i = 0; i< col.size()-index; i++){
            Node<T> n = left;
            left = n.next;
            n.next = right;
            right = n;

            sizeLeft--;
            sizeRight++;
        }
    }


    public int index() {
        return sizeLeft;
    }

    public int size() {
        return sizeLeft + sizeRight;
    }


    public T current() {
        if (right == null) {
            return null;
        }

        return right.data;
    }


    public void add(T item) {
        Node<T> nuevo = new Node<>(item,left);
        left  = nuevo;

        sizeLeft++;

    }


    public T remove() {

        if(right == null){
            throw new NoSuchElementException();
        }

        T dato = right.data;

        right = right.next;
        sizeRight --;

        return dato;

    }


    public T next() {}


    public T previous() {
        // TODO Ejercicio 8
        throw new UnsupportedOperationException("Ejercicio 8: por implementar");
    }


    public T moveTo(int index) {
        // TODO Ejercicio 9
        throw new UnsupportedOperationException("Ejercicio 9: por implementar");
    }

    /**
     * Devuelve la lista con el formato [izquierda][*derecha], por ejemplo
     * "[l, u][*n, a]", "[][*s, o]", "[s, o][*]" o "[][*]".
     *  NO LO MODIFIQUES.
     */
    @Override
    public String toString() {
        // La cadena izquierda está en orden inverso: se copia a un array al revés
        Object[] leftItems = new Object[sizeLeft];
        Node<T> n = left;
        for (int i = sizeLeft - 1; i >= 0 && n != null; i--) {
            leftItems[i] = n.data;
            n = n.next;
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < leftItems.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(leftItems[i]);
        }
        sb.append("][*");
        n = right;
        boolean first = true;
        while (n != null) {
            if (!first) sb.append(", ");
            sb.append(n.data);
            first = false;
            n = n.next;
        }
        return sb.append("]").toString();
    }
}
