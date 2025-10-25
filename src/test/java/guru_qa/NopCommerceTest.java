package guru_qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


@DisplayName("Тесты nopCommerce")
public class NopCommerceTest {

    @BeforeEach
    void setUp() {

        open("https://demo.nopcommerce.com/");
    }

     @Tag("Smoke")
        @ParameterizedTest(name = "Поиск товара в категории: {0}")
        @CsvSource({
                "Computers, Lenovo",
                "Electronics, Phone",
                "Apparel, Shoes"

        })
        void searchProductsInCategories(String category, String searchTerm) {
            $$(".top-menu li a").findBy(text(category)).click();
            $("#small-searchterms").setValue(searchTerm).pressEnter();
            $(".search-results").shouldBe(visible);
            $$(".product-item").shouldHave(sizeGreaterThan(0));
            $$(".product-item h2 a").first().shouldHave(text(searchTerm));
        }

    @Tag("Regress")
    @ParameterizedTest(name = "Добавление в избранное: {0}")
    @CsvSource({
            "HTC One Mini Blue",
            "Nokia Lumia 1020",
            "Samsung Galaxy S24 256GB"
    })
    void addProductsToWishlist(String productName) {
        $("#small-searchterms").setValue(productName).pressEnter();
        $$(".product-item").findBy(text(productName))
                .$(".add-to-wishlist-button").click();
        $(".bar-notification.success").shouldBe(visible)
                .shouldHave(text("The product has been added to your wishlist"));
        $(".bar-notification.success a[href='/wishlist']").click();
        $(".wishlist-content").scrollTo().shouldHave(text(productName));
        $(".remove-from-cart button").click();
        $(".no-data").shouldHave(text("The wishlist is empty!"));
    }

    @Tag("Smoke")
    @ParameterizedTest(name = "Переход к категории: {0}")
    @ValueSource(strings = {"Computers", "Electronics", "Apparel"})
    void basicNavigationTest(String category) {
        $$(".top-menu li a").findBy(text(category)).click();
        $(".page-title h1").shouldHave(text(category));
        $$(".sub-category-item").shouldHave(sizeGreaterThan(0));
    }
    }
