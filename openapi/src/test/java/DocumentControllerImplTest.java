import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import ru.zhilin.example.model.dao.DocumentDao;
import ru.zhilin.example.model.entity.DocumentEntity;
import ru.zhilin.example.openapi.controller.DocumentControllerImpl;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class DocumentControllerImplTest {
    private MockMvc mockMvc;

    @Mock
    private DocumentDao documentDao;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(new DocumentControllerImpl(documentDao)).build();
    }

    @Test
    public void testGetDocumentOkResponseStatus() throws Exception {
        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setId(1L);
        when(documentDao.getDocumentById(1)).thenReturn(documentEntity);

        mockMvc.perform(get("/documents/{documentId}", 1).accept(APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(xpath("/document/id").string("1"));
    }

    @Test
    public void testGetDocumentNotFoundResponseStatus() throws Exception {
        when(documentDao.getDocumentById(1)).thenReturn(null);

        mockMvc.perform(get("/documents/{documentId}", 1).accept(APPLICATION_XML))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddDocumentCreatedResponseStatus() throws Exception {
        // TODO
    }

    @Test
    public void testUpdateDocumentNoContentResponseStatus() throws Exception {
        // TODO
    }

    @Test
    public void testDeleteDocumentNoContentResponseStatus() throws Exception {
        // TODO
    }

}
