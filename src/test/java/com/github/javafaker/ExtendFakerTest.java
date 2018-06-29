package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Locale;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ExtendFakerTest {

    public static class ExtendFaker extends Faker {

        public ExtendFaker() {
            super();
        }

        public ExtendFaker(Locale locale) {
            super(locale);
        }

        public ExtendFaker(Random random) {
            super(random);
        }

        public ExtendFaker(Locale locale, Random random) {
            super(locale, random);
        }

        public String kitten(Integer width, Integer height, Boolean gray) {
            return String.format("https://placekitten.com/%s%s/%s", gray ? "g/" : StringUtils.EMPTY, width, height);
        }

        public String kitten() {
            String[] dimension = StringUtils
                    .split(this.fakeValuesService().resolve("internet.image_dimension", this, this), 'x');
            if (dimension.length == 0)
                return "";
            return kitten(Integer.valueOf(StringUtils.trim(dimension[0])),
                    Integer.valueOf(StringUtils.trim(dimension[1])), bool().bool());
        }
    }

    ExtendFaker faker;

    @Before
    public void setUp() throws Exception {

        Random random = new Random(1234);
        Random spy = Mockito.spy(random);
        
        doReturn(0).when(spy).nextInt(anyInt());
        doReturn(true).when(spy).nextBoolean();

        faker = new ExtendFaker(Locale.ENGLISH, spy);

    }

    @Test
    public void kitten() {
        
        assertThat(faker.kitten(), is(equalTo("https://placekitten.com/g/320/200")));

    }

}
