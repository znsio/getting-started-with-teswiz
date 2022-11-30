package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.GiftVoucherScreen;
import com.znsio.sample.e2e.screen.indigo.IndigoHomeScreen;

public class IndigoHomeScreenAndroid extends IndigoHomeScreen {
    public IndigoHomeScreenAndroid(Driver driver, Visual visually) {
        super();
    }

    @Override
    public GiftVoucherScreen goToGiftVoucherSection() {
        return GiftVoucherScreen.get();
    }
}
