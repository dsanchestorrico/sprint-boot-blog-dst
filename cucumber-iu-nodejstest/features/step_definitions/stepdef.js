const assert = require('assert');
const { Given, When, Then } = require('cucumber');
const { Builder, By, Key, untill } = require('selenium-webdriver')

const browser = new Builder()
    .forBrowser('firefox')
    .build()
const browserLogin = new Builder()
    .forBrowser('firefox')
    .build()
const browserRegister = new Builder()
    .forBrowser('firefox')
    .build()


Given('Abrir home', { timeout: 10 * 1000 }, function () {
    return browser.get('http://localhost:8080/home')
})


Then('El titulo debe decir Spring Boot Thymeleaf', function () {
    browser.getTitle().then(function (title) {
        assert.equal(title, "Spring Boot Thymeleaf")
        return title
    })
})

Given('Abrir login form', { timeout: 20 * 1000 }, function () {
    return browserLogin.get('http://localhost:8080')
})

Given('Ingresar credenciales', function () {
    browserLogin.findElement(By.name('username')).sendKeys('admin', Key.TAB)
    browserLogin.findElement(By.name('password')).sendKeys('admin', Key.TAB)
    return browserLogin.findElement(By.className('btn btn-primary btn-block')).click()
})

Given('Abrir REGISTER', function () {
    browserRegister.get('http://localhost:8080/registration')
});

Given('Ingresar datos de Usuario', { timeout: 20 * 1000 }, function () {
    browserRegister.findElement(By.name('name')).sendKeys('testuser', Key.TAB)
    browserRegister.findElement(By.name('lastName')).sendKeys('testuser Last Name', Key.TAB)
    browserRegister.findElement(By.name('email')).sendKeys('testuser@admin.com', Key.TAB)
    browserRegister.findElement(By.name('password')).sendKeys('testuser', Key.TAB)
    browserRegister.findElement(By.name('username')).sendKeys('testuser', Key.TAB)
    return browserRegister.findElement(By.className('btn btn-primary btn-block')).click()
});

Then('Usuario registrado', function () {
    // Write code here that turns the phrase above into concrete actions
    console.log('OK')
});
