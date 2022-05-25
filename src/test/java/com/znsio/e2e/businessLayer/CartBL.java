package com.znsio.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
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

    public CartBL selectLocation(String location){
        //verify after entering location and selecting it browser navigates to restaurant list
        SwiggyHomeScreen.get().setRestaurantLocation(location);
        return this;
    }

    public CartBL sortByRating(){
        //verify if browser is on restaurant list page and then click in sort by rating
        RestaurantListScreen.get().sortRestaurants();
        return this;
    }

    public CartBL selectRestaurant() {
        RestaurantListScreen.get().selectRestaurantFromList();
        return this;
    }

    public CartBL addToCart(int count){
        RestaurantScreen.get().addItem();
        context.addTestState("Cart Items",count);
        return this;
    }

    public CartBL verifyCartContent(){
        return this;
    }

    public CartBL verifyCartCounter(){
        return this;
    }

    public CartBL removeFromCart(){
        RestaurantScreen.get().removeItem();
        return this;
    }

    public CartBL verifyEmptyCart(){
        return this;
    }

}
