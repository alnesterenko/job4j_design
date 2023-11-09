package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import org.assertj.core.data.Percentage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 4);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .doesNotContain("Sinhrofasotron");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(7, 52);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotNull()
                .startsWith("Unknown")
                .contains(" ", "Unknown", "object")
                .endsWith("object")
                .doesNotContain("Sinhrofasotron");
    }

    @Test
    void if8Vertex() {
        Box box = new Box(8, 17);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(1)
                .isLessThan(9)
                .isEqualTo(8);
    }

    @Test
    void if0Vertex() {
        Box box = new Box(0, 17);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEven()
                .isGreaterThan(-1)
                .isLessThan(9)
                .isEqualTo(0)
                .isNotNull()
                .isZero();
    }

    @Test
    void isExistTrue() {
        Box box = new Box(4, 4);
        boolean exist = box.isExist();
        assertThat(exist).isEqualTo(true)
                .isTrue();
    }

    @Test
    void isExistFalse() {
        Box box = new Box(7, 52);
        boolean exist = box.isExist();
        assertThat(exist).isEqualTo(false)
                .isFalse();
    }

    @Test
    void checkAreaOfSphere() {
        Box box = new Box(0, 4);
        double area = box.getArea();
        assertThat(area).isEqualTo(201d, withPrecision(0.1d))
                .isCloseTo(200.95d, withPrecision(0.12d))
                .isCloseTo(200.95d, Percentage.withPercentage(1.0d))
                .isGreaterThan(5.25d)
                .isLessThan(202d);
    }

    @Test
    void areaOfTetrahedron() {
        Box box = new Box(4, 4);
        double area = box.getArea();
        assertThat(area).isEqualTo(27.7d, withPrecision(0.1d))
                .isCloseTo(27d, withPrecision(1.0d))
                .isCloseTo(28d, Percentage.withPercentage(2.0d))
                .isGreaterThan(5.25d)
                .isLessThan(202d);
    }
}