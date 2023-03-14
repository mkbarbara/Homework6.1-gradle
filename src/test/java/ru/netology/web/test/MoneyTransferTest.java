package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
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
    var dashboardPageBefore = verificationPage.validVerify(verificationCode);
    var card1Number = DataHelper.getInfoCard1().getCardNumber();
    var card2Number = DataHelper.getInfoCard2().getCardNumber();
    var card1BalanceBefore = dashboardPageBefore.getCardBalance(card1Number);
    var card2BalanceBefore = dashboardPageBefore.getCardBalance(card2Number);
    var amountTransfer = DataHelper.getTransferAmount(card1BalanceBefore).getAmountTransfer();
    var transferPage = dashboardPageBefore.addToCard1();

    var dashboardPageAfter = transferPage.transfer(amountTransfer, card2Number);
    var card1BalanceAfter = dashboardPageAfter.getCardBalance(card1Number);
    var card2BalanceAfter = dashboardPageAfter.getCardBalance(card2Number);

    Assertions.assertEquals(card1BalanceBefore + Integer.parseInt(amountTransfer), card1BalanceAfter);
    Assertions.assertEquals(card2BalanceBefore - Integer.parseInt(amountTransfer), card2BalanceAfter);
  }
}

