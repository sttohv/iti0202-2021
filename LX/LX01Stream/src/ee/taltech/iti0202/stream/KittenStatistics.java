package ee.taltech.iti0202.stream;

import java.util.*;
import java.util.stream.Collectors;

public class KittenStatistics {

    private List<Kitten> kittens;

    public void setKittens(List<Kitten> kittens) {
        this.kittens = kittens;
    }

    public OptionalDouble findKittensAverageAge() {
        return kittens.stream().mapToDouble(Kitten::getAge).average();

    }

    public Optional<Kitten> findOldestKitten() {
        return kittens.stream().max(Comparator.comparingInt(Kitten::getAge));
    }

    public List<Kitten> findYoungestKittens() {
        int age = kittens.stream().min(Comparator.comparingInt(Kitten::getAge)).get().getAge();
        return kittens.stream().filter(i -> i.getAge() == age).collect(Collectors.toList());
    }

    public List<Kitten> findKittensAccordingToGender(Kitten.Gender gender) {
        return kittens.stream().filter(o -> o.getGender().equals(gender)).collect(Collectors.toList());
    }

    public List<Kitten> findKittensBetweenAges(int minAge, int maxAge) {

        return kittens.stream()
                .filter(i -> i.getAge() > minAge - 1)
                .filter(i -> i.getAge() < maxAge + 1)
                .collect(Collectors.toList());
    }

    public Optional<Kitten> findFirstKittenWithGivenName(String givenName) {

        return kittens.stream().filter(i -> i.getName().toUpperCase().equals(givenName.toUpperCase())).findFirst();
    }

    public List<Kitten> kittensSortedByAgeYoungerFirst() {
        return kittens.stream().sorted(Comparator.comparingInt(Kitten::getAge)).collect(Collectors.toList());
    }

    public List<Kitten> kittensSortedByAgeOlderFirst() {
        return kittens.stream().sorted(Comparator.comparingInt(Kitten::getAge).reversed()).collect(Collectors.toList());
    }

}