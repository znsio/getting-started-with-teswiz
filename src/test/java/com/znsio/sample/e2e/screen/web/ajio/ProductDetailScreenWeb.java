package com.znsio.sample.e2e.screen.web.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.ProductDetailScreen;
import org.apache.log4j.Logger;

public class ProductDetailScreenWeb extends ProductDetailScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductDetailScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public ProductDetailScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }
}
