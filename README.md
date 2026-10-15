# SauceDemo UI Tests

Автотесты для сайта [SauceDemo](https://www.saucedemo.com/) — учебный проект по UI-автоматизации на Java + Selenium + TestNG.

##  Стек

- **Java**
- **Selenium WebDriver 4.50.0**
- **TestNG 7.10.2**
- **Maven**
- **WebDriverManager 6.4.0**
- **Google Chrome** (тестируется в Chrome)

## Структура проекта

```
src
├── main/java/com/nekhaichik
│   ├── component/        
│   └── pages/            
│       ├── BasePage
│       ├── LoginPage
│       ├── InventoryPage
│       ├── CartPage
│       ├── CheckoutStepOnePage
│       ├── CheckoutStepTwoPage
│       └── CheckoutCompletePage
└── test/java/com/nekhaichik/tests
    ├── BaseTest          
    ├── LoginTest         
    ├── InventoryPageTest 
    ├── CartPageTest      
    ├── CheckoutFlowTest  
    ├── CheckoutStepOneTest 
    └── BurgerMenuTest  
```

## Что покрыто тестами

| № | Сценарий | Тест-класс |
|---|---|---|
| 1 | Позитивный логин | `LoginTest` |
| 2 | Негативный логин (неверный пароль) | `LoginTest` |
| 3 | Сортировка Price (low→high, high→low) | `InventoryPageTest` |
| 4 | Добавление товара → бейдж корзины = 1 | `InventoryPageTest` |
| 5 | Удаление товара из корзины | `CartPageTest` |
| 6 | Кнопка Add to cart ↔ Remove | `InventoryPageTest` |
| 7 | Полный checkout-flow | `CheckoutFlowTest` |
| 8 | Обязательные поля в Checkout | `CheckoutStepOneTest` |
| 9 | About → переход на Sauce Labs | `BurgerMenuTest` |
| 10 | Reset App State | `BurgerMenuTest` |

## Запуск

### Требования

- JDK 25
- Maven 3.8+
- Google Chrome (последняя версия)

### Обычный режим (браузер виден)

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

