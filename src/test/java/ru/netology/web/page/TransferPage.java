package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");

    public DashboardPage transfer(String amount, String cardNumber) {
        amountField.setValue(amount);
        fromField.setValue(cardNumber);
        transferButton.click();
        return new DashboardPage();
    };

    public DashboardPage cancelTransfer() {
        return new DashboardPage();
    };
}
