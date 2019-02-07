package com.github.jsontemplate.main;

import com.jayway.jsonpath.DocumentContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static com.github.jsontemplate.test.TestUtils.*;

@RunWith(JUnit4.class)
public class DefaultTypeTest {

    @Test
    public void test_simpleDefaultType() {
        DocumentContext document = parse("@s{fieldA, fieldB}");
        assertThat(document.read("$.fieldA", String.class), is(notNullValue()));
        assertThat(document.read("$.fieldB", String.class), is(notNullValue()));
    }

    @Test
    public void test_overwriteDefaultType() {
        DocumentContext document = parse("@s{fieldA, fieldB : @i { fieldC }}");
        assertThat(document.read("$.fieldA", String.class), is(notNullValue()));
        assertThat(document.read("$.fieldB.fieldC", Integer.class), is(notNullValue()));
    }

    @Test
    public void test_simpleParamerizedDefaultType() {
        DocumentContext document = parse("@s(size=10){fieldA, fieldB: @s(size=20)}");
        assertThat(document.read("$.fieldA", String.class).length(), is(10));
        assertThat(document.read("$.fieldB", String.class).length(), is(20));
    }
}