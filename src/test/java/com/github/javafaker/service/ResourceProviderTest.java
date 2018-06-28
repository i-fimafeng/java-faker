package com.github.javafaker.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.javafaker.AbstractFakerTest;

public class ResourceProviderTest extends AbstractFakerTest {

    @Mock
    private RandomService randomService;

    private FakeValuesService fakeValuesService;

    @Before
    public void before() {
        super.before();
        MockitoAnnotations.initMocks(this);

        // return the first element then second
        when(randomService.nextInt(anyInt())).thenReturn(0).thenReturn(1);

        fakeValuesService = spy(new FakeValuesService(new Locale("en"), randomService));
    }

    @Test
    public void loadNewContent() {

        assertThat(fakeValuesService.fetchString("testing.quotes"), is("Quality is not an act, it is a habit."));
    }

    @Test
    public void mergeArrayContent() {

        assertThat(fakeValuesService.fetchString("nasa.quotes"), is("Test what you fly, fly what you test."));
        assertThat(fakeValuesService.fetchString("nasa.quotes"), is("Failure is not an option."));
    }
}
