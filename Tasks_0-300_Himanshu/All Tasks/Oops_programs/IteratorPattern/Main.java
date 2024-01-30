interface Iterator {
    String next();

    boolean hasNext();
}

interface Collection {
    Iterator getIterator();
}

class CustomCollection implements Collection {
    private String[] elements;

    public CustomCollection(String[] elements) {
        this.elements = elements;
    }

    private class CustomIterator implements Iterator {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < elements.length;
        }

        @Override
        public String next() {
            if (hasNext()) {
                return elements[index++];
            }
            return null;
        }
    }

    @Override
    public Iterator getIterator() {
        return new CustomIterator();
    }
}

public class Main {
    public static void main(String[] args) {
        String[] data = { "Element 1", "Element 2", "Element 3", "Element 4" };

        Collection collection = new CustomCollection(data);

        Iterator iterator = collection.getIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}