package com.isaguler.icu_java;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.Currency;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

import static com.isaguler.icu_java.SubUnitConstants.DEFAULT_SUBUNIT;
import static com.isaguler.icu_java.SubUnitConstants.SUBUNIT_MAP;

@RestController
@RequestMapping
public class IcuController {

    @PostMapping("/convert")
    public String convert(@RequestParam BigDecimal amount,
                          @RequestParam String currencyCode,
                          @RequestParam String localeCode) {
        try {
            if (amount == null || currencyCode == null || localeCode == null) {
                return "Error: amount, currencyCode and localeCode are required.";
            }

            Locale locale = Locale.of(localeCode);
            RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(locale, RuleBasedNumberFormat.SPELLOUT);
            Currency currency = Currency.getInstance(currencyCode.toUpperCase());

            BigDecimal scaled = amount.setScale(2, RoundingMode.HALF_UP);

            int integerPart = scaled.intValue();
            int fractionPart = scaled
                    .remainder(BigDecimal.ONE)
                    .movePointRight(2)
                    .abs()
                    .intValue();

            boolean[] isChoiceFormat = new boolean[1];
            String mainCurrencyName = currency.getName(locale, Currency.LONG_NAME, isChoiceFormat);
            String subunitName = getSubunitName(currencyCode.toUpperCase(), localeCode);

            String integerInWords = formatter.format(integerPart);

            StringBuilder result = new StringBuilder();
            result.append(integerInWords)
                    .append(" ")
                    .append(mainCurrencyName);

            if (fractionPart > 0) {
                String fractionInWords = formatter.format(fractionPart);
                result.append(" ")
                        .append(fractionInWords)
                        .append(" ")
                        .append(subunitName);
            }

            return result.toString();

        } catch (IllegalArgumentException e) {
            return "Invalid currency or locale code: " + e.getMessage();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    private String getSubunitName(String currencyCode, String localeCode) {
        String exactKey = currencyCode + "_" + localeCode;
        if (SUBUNIT_MAP.containsKey(exactKey)) {
            return SUBUNIT_MAP.get(exactKey);
        }

        String lang = localeCode.split("[_-]")[0];
        String fallbackKey = currencyCode + "_" + lang;
        if (SUBUNIT_MAP.containsKey(fallbackKey)) {
            return SUBUNIT_MAP.get(fallbackKey);
        }

        return DEFAULT_SUBUNIT;
    }
}
