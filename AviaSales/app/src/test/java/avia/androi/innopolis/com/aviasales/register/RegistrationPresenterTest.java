package avia.androi.innopolis.com.aviasales.register;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import avia.androi.innopolis.com.aviasales.database.IAviaSalesDatabase;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationPresenterTest {


    @Mock
    private IAviaSalesDatabase db;

    @Mock
    private IRegistrationView iView;

    private RegistrationPresenter presenter;

    @Before
    public void setUp() throws Exception {

        presenter = new RegistrationPresenter(iView, db);
    }

    @Test
    public void testRegister() throws Exception {


    }

    @Test
    public void testDb(){

        assertNotNull(db);
    }

    @Test
    public void testView(){

        assertNotNull(iView);
    }
}