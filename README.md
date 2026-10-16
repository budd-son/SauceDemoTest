# SauceDemo UI Tests

Автотесты для сайта [SauceDemo](https://www.saucedemo.com/) — учебный проект по UI-автоматизации на Java + Selenium + TestNG.

## Стек

- **Java 25**
- **Selenium WebDriver 4.50.0**
- **TestNG 7.10.2**
- **Maven**
- **WebDriverManager 6.4.0**
- **Log4j2** — логирование
- **Google Chrome** (тестируется в Chrome)

## Архитектура

Проект построен по трёхслойной схеме:

```
Тесты  →  Steps  →  Page Objects  →  BasePage  →  Selenium
```

| Слой | Отвечает за | Не знает про |
|---|---|---|
| **Тесты** | сценарии + ассерты | `By`, `WebElement`, `driver` |
| **Steps** | композиция сценариев (`loginAs`, `addToCart`, `proceedToCheckout`) | `By`, `WebElement` |
| **Page Objects** | элементы страницы, атомарные действия | сценарии, ассерты |
| **BasePage** | технический слой (`click`, `fillElement`, `getText`, `waitForUrl`) | бизнес-логику |

Правила:
- Тест вызывает **только Steps** и делает `Assert`.
- Steps вызывает **только методы Page Object**, не работает с `By`/`WebElement` напрямую.
- Page Object возвращает `this` или следующую страницу, никогда не делает ассертов.
- Ассерты живут **только в тестах**.

## Структура проекта

```
src
├── main/java/com/nekhaichik
│   ├── component/
│   │   └── HeaderComponent          
│   ├── pages/
│   │   ├── BasePage                 
│   │   ├── BaseLoggedInPage        
│   │   ├── LoginPage
│   │   ├── InventoryPage
│   │   ├── CartPage
│   │   ├── CheckoutStepOnePage
│   │   ├── CheckoutStepTwoPage
│   │   └── CheckoutCompletePage
│   └── steps/
│       ├── LoginSteps
│       ├── InventorySteps
│       ├── CartSteps
│       ├── CheckoutSteps
│       └── BurgerMenuSteps
├── main/resources/
│   └── log4j2.xml
└── test/java/com/nekhaichik/tests
    ├── BaseTest                    
    ├── LoginTest
    ├── InventoryPageTest
    ├── CartPageTest
    ├── CheckoutStepOneTest
    ├── CheckoutFlowTest
    └── BurgerMenuTest
```

## Что покрыто тестами

| № | Сценарий | Тест-класс |
|---|---|---|
| 1 | Позитивный логин | `LoginTest` |
| 2 | Негативный логин (неверный пароль) | `LoginTest` |
| 3 | Сортировка Price (low→high, high→low) | `InventoryPageTest` |
| 4 | Добавление товара → бейдж корзины = 1 | `InventoryPageTest` |
| 5 | Кнопка Add to cart ↔ Remove | `InventoryPageTest` |
| 6 | Товар отображается в корзине | `CartPageTest` |
| 7 | Удаление товара → корзина пуста | `CartPageTest` |
| 8 | Обязательные поля в Checkout (3 кейса) | `CheckoutStepOneTest` |
| 9 | Полный checkout-flow с проверкой суммы | `CheckoutFlowTest` |
| 10 | About → переход на Sauce Labs | `BurgerMenuTest` |
| 11 | Reset App State очищает корзину | `BurgerMenuTest` |

## Запуск

### Требования

- **JDK 25**
- **Maven 3.8+**
- **Google Chrome** (последняя версия)
- ChromeDriver подтягивается автоматически через WebDriverManager

### Все тесты (браузер виден)

```bash
mvn clean test
```

### Headless-режим

```bash
mvn clean test -Dheadless=true
```

### Только один класс

```bash
mvn test -Dtest=LoginTest
```
## Review


### Только один метод

```bash
mvn test -Dtest=LoginTest#userCanLoginWithValidCredentials
```

### Несколько классов

```bash
mvn test -Dtest="LoginTest,CartPageTest"
```

