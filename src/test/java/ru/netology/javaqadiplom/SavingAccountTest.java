package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    // Сберегательный счёт
// Может иметь баланс только в пределах от указанного минимального до указанного максимального включительно.
// Не может уходить в минус (минимальный баланс не может быть отрицательным).
// Имеет ставку - количество процентов годовых на остаток.
    @Test
    public void shouldPayWithinMinBalance() { // оплата в пределах мин
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(999);

        Assertions.assertEquals(2_000 - 999, account.getBalance());
    }

    @Test
    public void shouldPayLowThanMinBalance() { // оплата меньше мин
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(2_001);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayEqualToMinBalance() { // оплата до мин
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(1_000);

        Assertions.assertEquals(2000 - 1000, account.getBalance());
    }
    @Test
    public void paymentNegative() { // сумма платежа отрицательная
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(-4_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddLessThanMaxBalance() { // пополнение до макс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(7_999);

        Assertions.assertEquals(2_000 + 7_999, account.getBalance());
    }

    @Test
    public void shouldAddLessMaxBalanceOne() {  // пополнение до макс минус 1
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(7_999);
        Assertions.assertEquals(2_000 + 7_999, account.getBalance());

    }

    @Test
    public void shouldAddEqualMaxBalance() { // пополнение до макс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        Assertions.assertEquals(2000 + 8000, account.getBalance());
    }

    @Test
    public void shouldAddAboveThanMaxBalance() { // пополнение больше макс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_001);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddAboveThanMinBalance() { // пополнение до мин + 1
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        account.add(1);

        Assertions.assertEquals(1000 + 1, account.getBalance());
    }

    @Test
    public void shouldAddANegativeAmount() { // отрицательное пополнение
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(-1_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldYearChange() { // процент на остаток
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(2000 * 5 / 100, account.yearChange());
    }

    @Test
    public void shouldYearChangeBalanceLess100() { //  процент на сотаток меньше 100
        SavingAccount account = new SavingAccount(
                99,
                50,
                10_000,
                5
        );

        Assertions.assertEquals(99 * 5 / 100, account.yearChange());
    }

    @Test
    public void shouldYearChangeRateOne() { // 1 процент на остаток
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                1
        );

        account.yearChange();
        Assertions.assertEquals(2_000 * 1 / 100, account.yearChange());
    }

    @Test
    public void shouldYearChangeRateNull() { // 0 процент на остаток
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                0
        );

        account.yearChange();
        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldYearChangeRateNegative() { // отрицательный процент на остаток
        Assertions.assertThrows(java.lang.IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    10_000,
                    -1
            );
        });
    }

    // Если параметры некорректны (мин. баланс больше максимального и так далее), то
    // должно выкидываться исключения вида IllegalArgumentException.

    @Test
    public void shouldExceptionInitialBalanceLowMinBalance() {
        // исключение начальный баланс меньше мин
        Assertions.assertThrows(java.lang.IllegalArgumentException.class, () -> {
            new SavingAccount(500, 1_000, 10_000, 5);
        });
    }

    @Test
    public void shouldExceptionInitialBalanceMoreThanMaxBalance() {
        // исключение начальный баланс больше макс
        Assertions.assertThrows(java.lang.IllegalArgumentException.class, () -> {
            new SavingAccount(15_000, 1_000, 10_000, 5);
        });
    }

    @Test
    public void shouldExceptionMinBalanceMoreThanMaxBalance() {
        // исключение мин баланс больше макс баланс
        Assertions.assertThrows(java.lang.IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, 15_000, 10_000, 5);
        });
    }
    @Test
    public void shouldPayTrue() { // оплата в пределах мин
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        boolean actual = account.pay(999);
        Assertions.assertTrue(actual);
    }
    @Test
    public void shouldPayFalse() { // оплата меньше мин
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        boolean actual = account.pay(1_001);
        Assertions.assertFalse(actual);
    }
    @Test
    public void shouldAddTrue() { // пополнение в пределах макс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        boolean actual = account.add(7_999);
        Assertions.assertTrue(actual);
    }
    @Test
    public void shouldAddFalse() { // поплнение больше макс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        boolean actual = account.add(8_001);
        Assertions.assertFalse(actual);
    }
    @Test
    public void shouldGetMinBalance() { // текущий мин
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        int expected = 1_000;
        int actual = account.getMinBalance();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldGetInitialBalance() { // начальный баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        int expected = 2_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldGetMaxBalance() { // текущий макс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        int expected = 10_000;
        int actual = account.getMaxBalance();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldGetRate() { // текущий процент
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        int expected = 5;
        int actual = account.getRate();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldSetRate() { // установить процент
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                30
        );
        account.setRate(30);
        int expected = 30;
        int actual = account.getRate();
        Assertions.assertEquals(expected, actual);
    }
}