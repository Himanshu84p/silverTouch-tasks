package prac54;

public class Main {
	public static void main(String[] args) {
		int n = 6;
		PersistentUnionFind puf = new PersistentUnionFind(n);
		System.out.println("Hii1");

		puf.union(0, 1);
		puf.union(1, 2);
		System.out.println("Hii2");

		puf.persist(0);
		puf.printState(0);
		System.out.println("Hii3");

		puf.union(3, 4);
		puf.union(4, 5);
		System.out.println("Hii4");

		puf.persist(1);
		puf.printState(1);
		System.out.println("Hii5");

		System.out.println("Version 0:");
		System.out.println(puf.isConnected(0, 2, 0));
		System.out.println(puf.isConnected(3, 5, 0));
		System.out.println("Hii6");
		System.out.println("Version 1:");
		System.out.println(puf.isConnected(0, 2, 1));
		System.out.println(puf.isConnected(3, 5, 1));

		System.out.println("Hii7");
	}
}
