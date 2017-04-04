package com.webinson.eurofood;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Invoke;
import org.ocpsoft.rewrite.el.El;
import org.ocpsoft.rewrite.faces.config.PhaseBinding;
import org.ocpsoft.rewrite.faces.config.PhaseOperation;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

import javax.faces.event.PhaseId;
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
                .addRule(Join.path("/checkout").to("/checkout.xhtml"))
                /*.addRule(Join.path("/store").to("/eshop.xhtml"))*/
                .addRule(Join.path("/user-login").to("/userLogin.xhtml"))
                .addRule(Join.path("/checkoutCart").to("/checkoutCart.xhtml"))
                .addRule(Join.path("/o-nas").to("/aboutUs.xhtml"))
                .addRule(Join.path("/rozvoz").to("/rozvoz.xhtml"))
                .addRule(Join.path("/kontakt").to("/contact.xhtml"))
                .addRule(Join.path("/nase-akcie").to("/ourDiscounts.xhtml"));

                /*.addRule(Join.path("/store/{itemUrl}/").to("/itemDetail.xhtml"))
                .where("itemUrl")
                .bindsTo(PhaseBinding.to(El.property("languagesBean.language"))
                        .after(PhaseId.RESTORE_VIEW));*/

                /*.addRule(Join.path("/store/{itemUrl}/").to("/itemDetail.xhtml"))
                .where("itemUrl").bindsTo(PhaseBinding.to(El.property("itemView.itemUrl")).after(PhaseId.RESTORE_VIEW))
                .addRule(
                        Join.path("/store/#{itemUrl}/").to("/itemDetail.xhtml")
                ).perform(
                        PhaseOperation.enqueue(
                                Invoke.binding(El.retrievalMethod("itemView.loadItem"))
                        ).after(PhaseId.RESTORE_VIEW)
                );*/
    }
}
