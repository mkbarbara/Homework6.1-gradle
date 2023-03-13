package ru.netology.web.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {
  private DataHelper() {}

  @Value
  public static class AuthInfo {
    private String login;
    private String password;
  }

  public static AuthInfo getAuthInfo() {
    return new AuthInfo("vasya", "qwerty123");
  }

  public static AuthInfo getOtherAuthInfo(AuthInfo original) {
    return new AuthInfo("petya", "123qwerty");
  }

  @Value
  public static class VerificationCode {
    private String code;
  }

  public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
    return new VerificationCode("12345");
  }

  @Value
  public static class CardInfo {
    private String cardNumber;
  };

  public static CardInfo getInfoCard1() {
    return new CardInfo("5559 0000 0000 0001");
  }

  public static CardInfo getInfoCard2() {
    return new CardInfo("5559 0000 0000 0002");
  }

  @Value
  public static class AmountTransfer {
    private String amountTransfer;
  }

  public static AmountTransfer getTransferAmount(int cardBalance) {
    Random random = new Random();
    int amountTransfer = random.nextInt(cardBalance);
    return new AmountTransfer(Integer.toString(amountTransfer));
  }
}

