import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        int count = (int) persons.stream()
                .filter(integer -> integer.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних " + count);

        List<String> joinTheArmy = persons.stream()
                .filter(s -> s.getSex().equals(Sex.MAN))
                .filter(age -> age.getAge() >= 18)
                .filter(age -> age.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println("Призывники");
        System.out.println(joinTheArmy);

        List<Person> goToWork = persons.stream()
                .filter(e -> e.getEducation().equals(Education.HIGHER))
                .filter(age -> age.getAge() >= 18)
                .filter(Person -> isMan(Person))
                .filter(Person -> isWoman(Person))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Потенциально работоспособные");
        //System.out.println(goToWork);

        for (Person p : goToWork) {
            System.out.print(p.getFamily() + " "
                    + p.getName() + " "
                    + p.getAge() + " "
                    + p.getEducation() +
                    " " + p.getSex());
            System.out.println();
        }


    }


    public static boolean isMan(Person person) {
        if (person.getSex().equals(Sex.MAN)) {
            if (person.getAge() <= 65) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWoman(Person person) {
        if (person.getSex().equals(Sex.MAN)) {
            if (person.getAge() <= 60) {
                return true;
            }
        }
        return false;
    }


}