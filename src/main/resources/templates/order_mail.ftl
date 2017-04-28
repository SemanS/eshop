<table id="bodyTable" border="0" width="100%" cellspacing="0" cellpadding="0">
    <tbody>
    <tr>
        <td align="center" valign="top">
            <table id="emailContainer" border="0" width="800" cellspacing="0" cellpadding="20">

                <p>Dobrý deň,</p>
                <p>ďakujeme za Váš nákup na <a href="www.eurofood.sk">www.eurofood.sk</a></p>
                <p>Týmto e-mailom potvrdzujeme, že sme prijali Vašu objednávku a zasielame upresňujúce
                    informácie.</p>
                <p><b>Objednávka číslo:${orderId}</b></p>
                <p><b>Fakturačné údaje:</b></p>
                <p>${facturationAddress}</p>
                <p><b>Doručovacie údaje:</b></p>
                <p>${deliveryAddress}</p>
                <tbody>
                <tr>
                    <td align="center" valign="top">
                        <table id="emailHeader" border="1" width="100%" cellspacing="0" cellpadding="10">
                            <tbody>
                            <tr>
                                <td align="center" valign="top">Názov</td>
                                <td align="center" valign="top">Množstvo</td>
                                <td align="center" valign="top">Jednotková cena s DPH</td>
                                <td align="center" valign="top">Celkom bez DPH</td>
                                <td align="center" valign="top">Celkom s DPH</td>
                            </tr>
                            <#list cartItems as cartItem>
                            <tr>
                                <#if cartItem.itemDto.discount == true>
                                    <td align="center" valign="top">${cartItem.itemDto.header}</td>
                                    <td align="center" valign="top">${cartItem.quantity}</td>
                                    <td align="center" valign="top">${cartItem.itemDto.priceNettoDiscount}</td>
                                    <td align="center"
                                        valign="top">${cartItem.itemDto.priceBruttoDiscount * cartItem.quantity}</td>
                                    <td align="center"
                                        valign="top">${cartItem.itemDto.priceNettoDiscount * cartItem.quantity}</td>
                                </#if>
                                <#if cartItem.itemDto.discount == false>
                                    <td align="center" valign="top">${cartItem.itemDto.header}</td>
                                    <td align="center" valign="top">${cartItem.quantity}</td>
                                    <td align="center" valign="top">${cartItem.itemDto.priceNetto}</td>
                                    <td align="center"
                                        valign="top">${cartItem.itemDto.priceBrutto * cartItem.quantity}</td>
                                    <td align="center"
                                        valign="top">${cartItem.itemDto.priceNetto * cartItem.quantity}</td>
                                </#if>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>

                <p><b>Daňová rekapitulácia:</b></p>
                <tbody>
                <tr>
                    <td align="left" valign="top">
                        <table id="emailContainer" border="0" width="500" cellspacing="0" cellpadding="20">
                            <tbody>
                            <tr>
                                <td align="center" valign="top">
                                    <table id="emailHeader" border="1" width="100%" cellspacing="0" cellpadding="20">
                                        <tbody>
                                        <tr>
                                            <td align="center" valign="top">Celkom bez DPH</td>
                                            <td align="center" valign="top">DPH</td>
                                            <td align="center" valign="top">Celkom s DPH</td>
                                        </tr>
                                        <tr>
                                            <td align="center" valign="top">${shoppingCartView.counterBrutto}</td>
                                            <td align="center"
                                                valign="top">${shoppingCartView.counterNetto - shoppingCartView.counterBrutto}</td>
                                            <td align="center" valign="top">${shoppingCartView.counterNetto}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>

                <p><b>Spôsob dopravy a platba:</b></p>
                <p>Doprava: Našou prepravou, Platba: Hotovosť</p>
                <p><b>Rozvoz: pondelok, streda, piatok</b></p>
                <p>V deň doručenia objednaného tovaru budeme Vás kontaktovať , aby sme Vás včas informovali o doručení
                    tovaru na Vašu zvolenú adresu.</p>
                <p>V prípade otázok ohľadom vybavenia a doručenia Vašej objednávky nás kontaktujte telefonicky 09xx xxx
                    xxx alebo napíšte nám na objednavky@eurofood.sk

                    Ešte raz ďakujeme za Váš nákup a prajeme príjemný deň.

                    S priateľským pozdravom

                    Prevádzkovateľ internetového obchodu :
                    EUROFOOD, s.r.o.
                    Jazerná 5
                    040 12 Košice
                    IČO:36817791
                    DIČ: SK2022428804
                </p>
                <p><b>Tento e-mail je generovaný automaticky, prosíme, neodpovedajte na neho.</b></p>
            </table>
        </td>
    </tr>
    </tbody>


</table>