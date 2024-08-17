package modelTests;

import model.Epic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class EpicTest {
    Epic epic1 = new Epic(1,"Поехать в отпуск", "Организовать путешествие");
    Epic epic2 = new Epic(1,"Сделать ремонт", "Покрасить стены на балконе");

    @Test
    void epicsShouldBeEqualsIfIdEquals() {
        assertEquals(epic1, epic2, "Экземпляры не равны");
        }
    }