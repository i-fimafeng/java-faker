package com.github.javafaker.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.javafaker.AbstractFakerTest;

public class ResourceProviderTest extends AbstractFakerTest {

    public static class DummyProvider implements ResourceProvider {

        @Override
        public List<InputStream> getResources(String filename) {
            List<InputStream> list = new ArrayList<InputStream>();
            InputStream in = getClass().getClassLoader().getResourceAsStream("test/" + filename + ".yml");
            if (in != null) {
                list.add(in);
            }
            return list;
        }

    }
    
    public static class DummyProvider2 implements ResourceProvider {

        @Override
        public List<InputStream> getResources(String filename) {
            List<InputStream> list = new ArrayList<InputStream>();
            InputStream in = getClass().getClassLoader().getResourceAsStream("test2/" + filename + ".yml");
            if (in != null) {
                list.add(in);
            }
            return list;
        }
        
    }

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

    @Test
    public void loadNormalContent() {

        assertThat(fakeValuesService.fetchString("job.key_skills"), is("Teamwork"));
    }

}
