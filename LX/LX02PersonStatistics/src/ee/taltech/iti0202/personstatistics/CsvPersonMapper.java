package ee.taltech.iti0202.personstatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvPersonMapper {
    /**
     * meetod, mis saab sisendiks csv faili asukoha ning
     * loeb failist inimeste infot stream'i abil (vihje - Files.lines),
     * tekitades jooksvalt builderit kasutades
     * Person objektid, millest moodustatakse list.
     * Person info tuleb salvestada listi,
     * seda läheb statistika arvutamisel vaja.
     * Vea korral tuleb visata CsvToPersonMappingException.
     *
     * @param path
     * @return
     */
    public List<Person> mapToPersons(String path) throws CsvToPersonMappingException {
        List<Person> persons = new ArrayList<>();
        Path tra = Path.of(path);
        String data = null;
        try {
            Stream<String> lines = Files.lines(tra);
            data = lines.collect(Collectors.joining("\n"));
            lines.close();
        } catch (IOException e) {
            throw new CsvToPersonMappingException();
        }
        List<String> linesList = Arrays.asList(data.split("\n").clone());
        for (String line : linesList
        ) {
            List<String> list = Arrays.asList(line.split(","));

            Person person = new PersonBuilder()
                    .setFirstName(list.get(0))
                    .setLastName(list.get(1))
                    .setAge(Integer.parseInt(list.get(2)))
                    .setGender(Gender.valueOf(list.get(3)))
                    .setHeightInMeters(Double.parseDouble(list.get(4)))
                    .setOccupation(list.get(5))
                    .setNationality(list.get(6))
                    .createPerson();
            persons.add(person);
        }
        return persons;
    }
}
