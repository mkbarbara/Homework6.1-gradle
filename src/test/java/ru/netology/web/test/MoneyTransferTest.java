package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

  @Test
  void shouldTransferMoneyBetweenOwnCards() {
    var loginPage = open("http://localhost:9999", LoginPage.class);
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    var dashboardPage = verificationPage.validVerify(verificationCode);
    var card1Number = DataHelper.getInfoCard1().getCardNumber();
    var card2Number = DataHelper.getInfoCard2().getCardNumber();
    var amountTransfer = DataHelper.getTransferAmount(dashboardPage.getCardBalance(card1Number)).getAmountTransfer();
    var transferPage = dashboardPage.addToCard1();
    transferPage.transfer(amountTransfer, card2Number);
  }
}

