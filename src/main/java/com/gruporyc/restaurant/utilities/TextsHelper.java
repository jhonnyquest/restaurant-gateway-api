package com.gruporyc.restaurant.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.lang.System.getProperty;

@Component
public class TextsHelper {

    @Value("${locale}")
    private String locale;

    public String getTranslation(String key) {
        return ResourceBundle.getBundle("texts", new Locale(locale)).getString(key);
    }
}