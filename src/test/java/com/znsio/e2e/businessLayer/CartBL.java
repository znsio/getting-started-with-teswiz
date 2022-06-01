package com.znsio.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.CartScreen;
import com.znsio.e2e.screen.RestaurantListScreen;
import com.znsio.e2e.screen.RestaurantScreen;
import com.znsio.e2e.screen.SwiggyHomeScreen;
import org.assertj.core.api.SoftAssertions;

public class CartBL {
    private final TestExecutionContext context;
    private final SoftAssertions softly;
    private final String currentUserPersona;
    private final Platform currentPlatform;

    public CartBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        this.currentUserPersona = userPersona;
        this.currentPlatform = forPlatform;
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public CartBL selectLocation(String location) {
        //verify after entering location and selecting it browser navigates to restaurant list
        SwiggyHomeScreen.get().setRestaurantLocation(location);
        return this;
    }

    public CartBL sortByRating() {
        //verify if browser is on restaurant list page and then click in sort by rating
        RestaurantListScreen.get().sortRestaurants();
        return this;
    }

    public CartBL selectRestaurant() {
        RestaurantListScreen.get().selectRestaurantFromList();
        return this;
    }

    public CartBL addToCart(int noOfItems) {
        context.addTestState("ExpectedCartCount", noOfItems);
        RestaurantScreen.get().addItem(noOfItems);
        System.out.println("After add to cart");
        return this;
    }

    public CartBL verifyCartContentAdd() {
        //get method that returns the object to be asserted softly
        softly.assertThat(CartScreen.get().verifyContentAdd()).as("Cart content does not match").isEqualTo(true);
        return this;
    }

    public CartBL verifyCartCounter() {
//        System.out.println(CartScreen.get().getCartCounter()+"  <==Cart counter==>  "+context.getTestState("CartItemCountNew"));
        softly.assertThat(CartScreen.get().getCartCounter()).as("Cart counter is not updated").isEqualTo(context.getTestState("ExpectedCartCount").toString());
        return this;
    }

    public CartBL removeFromCart() {
        RestaurantScreen.get().removeItem();
        System.out.println("After remove from cart");
        return this;
    }

    public CartBL verifyCartContentRemove(){
        softly.assertThat(CartScreen.get().verifyContentRemove()).as("Cart content does not match").isEqualTo(true);
        return this;
    }

    public CartBL verifyEmptyCart() {
        softly.assertThat(CartScreen.get().getCartCounter()).as("Cart counter is not updated").isEqualTo("0");
        return this;
    }

}
