package com.webinson.eurofood;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

import javax.servlet.ServletContext;

/**
 * Created by Slavo on 23.09.2016.
 */

@RewriteConfiguration
public class RewriteConfigApp extends HttpConfigurationProvider {

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public Configuration getConfiguration(final ServletContext context) {
        return ConfigurationBuilder.begin()
                .addRule(Join.path("/dashboard").to("/dashboard.xhtml"))
                .addRule(Join.path("/").to("/index.xhtml"))
                .addRule(Join.path("/registration").to("/register.xhtml"))
                .addRule(Join.path("/store").to("/eshop.xhtml"))
                .addRule(Join.path("/user-login").to("/userLogin.xhtml"))
                .addRule(Join.path("/registration").to("/register.xhtml"))
                .addRule(Join.path("/file").to("/file.xhtml"))
                .addRule(Join.path("/checkoutCart").to("/checkoutCart.xhtml"));
    }
}
