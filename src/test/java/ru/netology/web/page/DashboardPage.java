package ru.netology.web.page;

import com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
  private static final SelenideElement heading = $(".heading_size_xl");
  private static final SelenideElement addButtonCard1 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button__text");
  private static final SelenideElement addButtonCard2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button__text");

  private final ElementsCollection cards = $$(".list__item div");
  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";

  public DashboardPage() {
    heading.shouldBe(visible).shouldHave(text("Ваши карты"));
  }

  public int getCardBalance(String cardNumber) {
    val text = cards.find(text(cardNumber.substring(15, 19))).getText();
    return extractBalance(text);
  }

  private int extractBalance(String text) {
    val start = text.indexOf(balanceStart);
    val finish = text.indexOf(balanceFinish);
    val value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }

  public TransferPage addToCard1() {
    addButtonCard1.click();
    return new TransferPage();
  };

  public TransferPage addToCard2() {
    addButtonCard2.click();
    return new TransferPage();
  };
}
