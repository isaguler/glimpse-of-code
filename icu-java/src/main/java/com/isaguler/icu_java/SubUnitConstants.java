package com.isaguler.icu_java;

import java.util.Map;

public class SubUnitConstants {

    public SubUnitConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final Map<String, String> SUBUNIT_MAP = Map.ofEntries(
            Map.entry("TRY_tr", "kuruş"),
            Map.entry("TRY_en", "kurus"),
            Map.entry("USD_en", "cent"),
            Map.entry("USD_tr", "sent"),
            Map.entry("EUR_en", "cent"),
            Map.entry("EUR_tr", "sent"),
            Map.entry("EUR_de", "Cent"),
            Map.entry("EUR_fr", "centime"),
            Map.entry("GBP_en", "penny"),
            Map.entry("JPY_ja", "銭"),
            Map.entry("CHF_de", "Rappen"),
            Map.entry("CHF_fr", "centime"),
            Map.entry("CAD_en", "cent"),
            Map.entry("CAD_fr", "cent"),
            Map.entry("AUD_en", "cent"),
            Map.entry("RUB_ru", "копейка"),
            Map.entry("CNY_zh", "分")
    );

    public static final String DEFAULT_SUBUNIT = "cent";
}
