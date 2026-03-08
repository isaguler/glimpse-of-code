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

@RestController
@RequestMapping
public class IcuController {

    @PostMapping("/convert")
    public String convert(@RequestParam BigDecimal amount,
                          @RequestParam String currencyCode,
                          @RequestParam String localeCode) {
        try {
            Locale locale = Locale.of(localeCode);

            RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(locale, RuleBasedNumberFormat.SPELLOUT);
            Currency currency = Currency.getInstance(currencyCode);

            BigDecimal scaled = amount.setScale(2, RoundingMode.HALF_UP);
            int integerPart = scaled.intValue();
            int fractionPart = scaled
                    .remainder(BigDecimal.ONE)
                    .movePointRight(2)
                    .intValue();

            String numberInWords = formatter.format(integerPart);

            boolean[] integer = new boolean[integerPart];

            String currencyName = currency.getName(locale, Currency.LONG_NAME, integer);

            if (fractionPart == 0) {
                return numberInWords + " " + currencyName;
            }

            String fractionNumber = formatter.format(fractionPart);

            boolean[] fraction = new boolean[fractionPart];

            String fractionCurrency = currency.getName(locale, Currency.LONG_NAME, fraction);

            return numberInWords + " " + currencyName +
                    " " + fractionNumber + " " + fractionCurrency;

        } catch (Exception e) {
            System.out.println("exception : "  + e.getMessage());

            return e.getMessage();
        }

    }
}
