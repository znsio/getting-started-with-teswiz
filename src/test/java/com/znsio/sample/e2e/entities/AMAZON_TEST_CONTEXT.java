package com.znsio.sample.e2e.entities;

import com.znsio.e2e.entities.TEST_CONTEXT;

import java.util.Arrays;
import java.util.List;

public class AMAZON_TEST_CONTEXT extends TEST_CONTEXT {

    public static final String GUEST_USER = "guestUser";
    public static final String ITEM_NAME = "itemName";
    public static final String ITEM_TITLE = "itemTitle";
    public static final List<String> CART_CREATION_SUCCESS_TEXT = Arrays.asList("added to cart","added to your cart");
    public static final String CART_SCREEN_TITLE = "Amazon.in Shopping Cart";
}
