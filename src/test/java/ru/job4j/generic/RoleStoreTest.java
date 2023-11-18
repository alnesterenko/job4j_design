package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRolenameIsDonkey() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "donkey"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("donkey");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Bear"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsBamby() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Bamby"));
        store.add(new Role("1", "Flafy"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Bamby");
    }

    @Test
    void whenReplaceThenRolenameIsFlafy() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Bamby"));
        store.replace("1", new Role("1", "Flafy"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Flafy");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Bamby"));
        store.replace("10", new Role("10", "Flafy"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Bamby");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Bamby"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsBamby() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Bamby"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Bamby");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Bamby"));
        boolean result = store.replace("1", new Role("1", "Flafy"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Bamby"));
        boolean result = store.replace("10", new Role("10", "Flafy"));
        assertThat(result).isFalse();
    }
}
