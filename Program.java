import java.util.ArrayList;
import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        var irina = new Person("Irina");
        var vasya = new Person("Vasya");
        var masha = new Person("Masha");
        var jane = new Person("Jane");
        var ivan = new Person("Ivan");
        var dima = new Person("Dima");
        GeoTree gt = new GeoTree();
        gt.appendParentChildren(irina, vasya);
        gt.appendParentChildren(irina, masha);
        gt.appendBrotherSister(vasya, masha);
        gt.appendParentChildren(vasya, jane);
        gt.appendParentChildren(vasya, ivan);
        gt.appendParentChildren(vasya, dima);
        gt.appendBrotherBrother(ivan, dima);


        new Reserch(gt).spend(masha, Relationship.sister);


    }


    @Override
    public String toString() {
        return super.toString();
    }
}

/**
 * перечисление отношений
 */
enum Relationship {
    parent,
    children,
    brother,
    sister
}

class Person {
    public String fullName;
    public String getFullName() {
        return fullName;
    }

    public Person(String fullName) {
        this.fullName = fullName;
    }


}

/**
 * Узел древа, представлен в виде связки человек-отношение-человек
 */
class Node {
    public Node(Person p1, Relationship re, Person p2) {
        this.p1 = p1;
        this.re = re;
        this.p2 = p2;
    }

    Person p1;
    Relationship re;
    Person p2;

}

class GeoTree {
    private ArrayList<Node> tree = new ArrayList<>();
    public ArrayList<Node> getTree() {
        return tree;
    }

    public void appendParentChildren(Person parent, Person children) {
        tree.add(new Node(parent, Relationship.parent, children));
        tree.add(new Node(children, Relationship.children, parent));
    }
    public void appendBrotherBrother(Person brother1, Person brother2) {
        tree.add(new Node(brother1, Relationship.brother, brother2));
        tree.add(new Node(brother2, Relationship.brother, brother1));
    }
    public void appendBrotherSister(Person brother, Person sister) {
        tree.add(new Node(brother, Relationship.brother, sister));
        tree.add(new Node(sister, Relationship.sister, brother));
    }
}

class Reserch {
    ArrayList<Node> tree;
    public Reserch(GeoTree geoTree) {
        tree = geoTree.getTree();
    }
    public void spend(Person p, Relationship re) {
        ArrayList<Person> result = new ArrayList<>();
        for (Node t : tree) {
            if (t.p1.fullName == p.fullName && t.re == re) {
                result.add(t.p2);
            }
        }
        Printer.Print(p, re, result);
    }


}

class Reserch2 {
// ...
}

class Reserch3 {
// ...
}

class Printer {
    public static void Print (Person p, Relationship re, ArrayList<Person> serchRes) {
        System.out.print(p.getFullName() + " is " + re + " of: ");
        for (int i = 0; i < serchRes.size(); i++) {
            System.out.print(serchRes.get(i).getFullName() + " ");
        }
    }
}

