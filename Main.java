import java.util.*;
public class Main {
    public class Family {
        private Strange myRoot = null;
        public int myDepth = 0;
        public int mySize = 0;

        public Family(String name) {

            myRoot = new Strange(name, null);
        }


        public class Strange {
            public String myName; // имя
            public String myParent; // родитель
            public ArrayList<Strange> myChildren; // дети

            public Strange(String name, String parent) {
                myName = name;
                myParent = parent;
                myChildren = new ArrayList<Strange>();
            }

            public String toString() {

                return myName;
            }

            // Добавляет ребенка с заданным именем
            public void addChild(String childName) {
                Strange child = new Strange(childName, this);
                myChildren.add(child);
            }
        }

        public void addChildHelper(String parentName, String childName, Strange currentStrange) {
            if (currentStrange.myName.equals(parentName)) {
                currentStrange.addChild(childName); // если родитель соответствует, то добаляем
            } else {
                Iterator<Strange> iter = currentStrange.myChildren.iterator();
                while (iter.hasNext()) {
                    Family.addChildHelper(parentName, childName, iter.next());
                }

            }
        }

        //Добавляет ребенка по имени родителя
        public void addChild(String parentName, String childName) {
            if (myRoot != null) {
                Family.addChildHelper(parentName, childName, myRoot);
            }
        }

        public void printFlatHelper(Strange currentStrange) {
            if (currentStrange.myChildren == null)
                System.out.println(currentStrange.myName);
            else {
                System.out.println(currentStrange.myName);
                Iterator<Strange> iter = currentStrange.myChildren.iterator();
                while (iter.hasNext()) {
                    Family.printFlatHelper(iter.next());
                }
            }
        }

        // Выводит имена всех членов семьи
        public void print() {
            if (myRoot != null) {
                Family.printHelper(myRoot);
            }
        }

        public void printHelper(Strange currentStrange) {
            if (currentStrange.myChildren == null) {
                depthIndenter(myDepth);
                System.out.println(currentStrange.myName);
            } else {
                depthIndenter(myDepth);
                System.out.println(currentStrange.myName);
                myDepth++;
                Iterator<Strange> iter = currentStrange.myChildren.iterator();
                while (iter.hasNext()) {
                    depthIndenter(myDepth);
                    Family.printHelper(iter.next());
                }
                myDepth--;
            }
        }

        public void depthIndenter(int myDepth) {
            while (myDepth > 0) {
                System.out.print("    ");
                myDepth--;
            }
        }
    }
    public void main(String[] args) {

        System.out.println("Hello world!");
        Family family = new Family("Иванов");
        family.addChild("Иванов", "мама/отец");
        family.addChild("Иванов", "тетя");
        family.addChild("мама/отец", "я");
        family.addChild("мама/отец", "Василий");
        family.addChild("мама/отец", "Настя");
        family.addChild("я", "Михаил");
        family.addChild("я", "Анатолий");
        family.addChild("я", "Мария");
        family.addChild("Михаил", "Костя");
        family.addChild("Михаил", "Лиза");
        family.addChild("Мария", "Петр");
        family.addChild("Мария", "Катерина");

        System.out.println("Вот такая семья:");
        family.print();
    }
}