package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void addToNegativeBalance() {
        CreditAccount account = new CreditAccount(1000, 5_000, 15);

        account.add(3_000);

        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    public void payWhenBalanceBecomesNegative() {
        CreditAccount account = new CreditAccount(1000, 5_000, 15);

        account.pay(3_000);
        Assertions.assertEquals(-2_000, account.getBalance());
    }

    @Test
    public void payWhenBalanceBecomesPositive() {
        CreditAccount account = new CreditAccount(5_000, 5_000, 15);

        account.pay(3_000);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void negativeLendingRate() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(0, 5_000, -15);
        });
    }

    @Test
    public void negativeCreditLimit() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(0, -900, 15);
        });
    }

    @Test
    public void negativeInitialBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(-5_000, 5_000, 15);
        });
    }

    @Test
    public void purchaseAmountIsLessThanZero() {
        CreditAccount account = new CreditAccount(0, 500, 15);
        account.add(1000);

        Assertions.assertEquals(false, account.pay(0));
    }

    @Test
    public void amountLessThanLimit() {
        CreditAccount account = new CreditAccount(0, 5000, 15);
        account.add(1000);

        Assertions.assertEquals(true, account.pay(5_000));
    }

    @Test
    public void amountExceedsLimit() {
        CreditAccount account = new CreditAccount(0, 5000, 15);
        account.add(1000);

        Assertions.assertEquals(false, account.pay(10_000));
    }

    @Test
    public void interestOnNegativeBalance() {
        CreditAccount account = new CreditAccount(-1000, 5000, 15);

        Assertions.assertEquals(-150, account.yearChange());
    }

    @Test
    public void interestOnPositiveBalance() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void addCreditLimit() {
        CreditAccount account = new CreditAccount(-1000, 5000, 15);

        Assertions.assertEquals(5_000, account.getCreditLimit());
    }


    @Test
    public void payExceedingLimitCredit() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);
        account.pay(3000);

        Assertions.assertEquals(-2000, account.getBalance());
    }

}