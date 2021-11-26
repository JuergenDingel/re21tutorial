import io.cucumber.java.en.*;
import org.mockito.Mockito;

import java.util.List;
import static org.junit.Assert.*;

public class StepDefinitions {

    private Order o;

    @Given("{word} who wants to create an Order")
    public void creating_an_order(String who){
        o = new Order();
        o.setOwner(who);
    }

    @When("{word} is declared as recipient")
    public void declaring_recipient(String who){
        o.setRecipient(who);
    }

    @Then("the order does not contain any drinks")
    public void check_emptiness() {
        List<Order.Drink> drinks = o.getDrinks();
        assertEquals(0, drinks.size());
    }

    @When("a(nother?) {string} is added to the order")
    public void add_drink_to_the_order(String drinkName){
        o.getDrinks().add(new Order.Drink(drinkName));
    }

    @Then("the order contains {int} {string}")
    public void check_order_contents(int size, String drink) {
        long count = 0L;
        for (Order.Drink d : o.getDrinks()) {
            if (d.getName().equals(drink)) {
                long l = count++;
            }
        }
        assertEquals(size, count);
    }
    @Then("the order contains {int} drink(s?)")
    public void check_order_size(int size) {
        assertEquals(size, o.getDrinks().size());
    }

    private Catalogue catalogue = Mockito.<Catalogue>mock(Catalogue.class);

    @Given("the price of a {string} being {double} dollars")
    public void the_price_of_a_being_$(String drink, Double price) {
        Mockito.when(catalogue.getPrice(drink)).thenReturn(price);
    }

    @Given("taxes in {word} being {double}%")
    public void taxes_being(String place, double rate) {
        o.setTaxes(1 + rate/100);
    }

    @Then("the price without taxes is {double} dollars")
    public void the_price_without_taxes_is_$(Double expected) {
        assertEquals(expected, o.computePrice(catalogue), 0.01);
    }

    @Then("the price including taxes is {double} dollars")
    public void the_price_including_taxes_is_$(Double expected) {
        assertEquals(expected, o.computePriceWithTaxes(catalogue), 0.01);
    }
}