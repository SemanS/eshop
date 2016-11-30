package com.webinson.zuzka;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.el.El;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
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
                .addRule(Join.path("/items").to("/items.xhtml"))
                .addRule(Join.path("/").to("/index.xhtml"));
                /*.addRule()
                .when(Direction.isInbound().and(Path.matches("/index.xhtml")))
                .perform(Forward.to("/Nelahozeves"));*/
    }
}
