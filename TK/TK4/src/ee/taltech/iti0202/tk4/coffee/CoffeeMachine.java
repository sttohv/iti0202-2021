package ee.taltech.iti0202.tk4.coffee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CoffeeMachine {
    private Map<String, Integer> resources = new HashMap<>();
    private Map<String, List<String>> flavourRequirement = new HashMap<>();
    private List<Coffee> producedCoffee = new ArrayList<>();

    /**
     * Lisatakse ressurss nimega name sellises koguses kohvimasinasse.
     * Kui selline ressurss on juba olemas, siis lisandub märgitud
     * kogus olemasolevatel kogusele.
     *
     * @param name   resource name
     * @param amount resource amount
     */
    public void addResource(String name, int amount) {
        //kui sama nimega ressurssi pole masinal
        if (!resources.containsKey(name)) {
            resources.put(name, amount);
        }
        //kui on sama nimega
        else {
            resources.put(name, resources.get(name) + amount);
        }

    }

    /**
     * Selleks, et teatud tüüpi (flavour) kohvi teha, on vaja teatud ressursse.
     * Selle meetodiga saab määrata, et milliseid ressursse ja kui palju on vaja,
     * et flavour tüüpi jooki teha.
     * Siin samamoodi, kui selline ressurss on juba lisatud eelnevalt,
     * siis kogus lisandub (mitte ei kirjutata üle).
     *
     * @param flavour  a
     * @param resource s
     * @param amount   s
     */
    public void addFlavourResourceRequirement(String flavour, String resource, int amount) {


    }

    /**
     * Loob võimalusel vastavat tüüpi kohvi.
     * Kui sellist tüüpi (flavour) kohvi jaoks pole ressursse märgitud (eelmise meetodiga),
     * siis seda kohvi valmistada ei saa.
     * Kui seda tüüpi kohvi jaoks mõnda vajalikku ressurss pole piisavas koguses, siis kohvi valmistada ei saa.
     * Kui kohvi saab valmistada, siis tuleb see lisada valmistatud jookide nimekirja,
     * vastavad ressursid maha võtta masinast ja tagastada loodud kohvi objekt (Optional-i sees).
     *
     * @param flavour a
     * @return a
     */
    public Optional<Coffee> makeCoffee(String flavour) {
        return Optional.empty();
    }

    public List<Coffee> getProducedCoffee() {
        return producedCoffee;
    }
}
