import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.INode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MyStructureTest {

    private Node node1 = new Node("CODE1","RENDERER1");
    private Node node2 = new Node("CODE2","RENDERER2");
    private Node node3 = new Node("CODE3","RENDERER3");
    private Node node4 = new Node("CODE4","RENDERER4");
    private CompositeNode compositeNode1 = new CompositeNode("COMPOSITE_NODE1", "COMPOSITE_RENDERER1");
    private CompositeNode compositeNode2 = new CompositeNode("COMPOSITE_NODE2", "COMPOSITE_RENDERER2");

    private MyStructure myStructure;

    @BeforeEach
    void setUp(){
        myStructure = new MyStructure();
        myStructure.setNodes(Collections.singletonList(compositeNode1));
        compositeNode1.setCompositeNodes(Arrays.asList(node1, node2, compositeNode2));
        compositeNode2.setCompositeNodes(Arrays.asList(node3, node4));
    }

    @Test
    void shouldReturnNullWhenNotFoundByCode(){
        assertNull(myStructure.findByCode("CODE"));
    }

    @Test
    void shouldReturnNullWhenNotFoundByRenderer(){
        assertNull(myStructure.findByRenderer("RENDERER"));
    }

    @Test
    void shouldFindCompositeNodeByCode(){
        assertThat(myStructure.findByCode("COMPOSITE_NODE2"), is(compositeNode2));
    }

    @Test
    void shouldFindCompositeNodeByRenderer(){
        assertThat(myStructure.findByRenderer("COMPOSITE_RENDERER1"), is(compositeNode1));
    }

    @Test
    void shouldFindNodeByCode(){
        assertThat(myStructure.findByCode("CODE3"), is(node3));
    }

    @Test
    void shouldFindNodeByRenderer(){
        assertThat(myStructure.findByRenderer("RENDERER2"), is(node2));
    }

    @Test
    void shouldInvolveExceptionWhenCodeIsNull(){
        assertThrows(IllegalArgumentException.class, () -> myStructure.findByCode(null));
    }

    @Test
    void shouldInvolveExceptionWhenRendererIsNull(){
        assertThrows(IllegalArgumentException.class, () -> myStructure.findByRenderer(null));
    }

    @Test
    void shouldReturn0WhenStructureIsEmpty(){
        List<INode> nodes = new ArrayList<>();
        myStructure.setNodes(nodes);
        assertThat(myStructure.count(), is(0));
    }

    @Test
    void shouldReturnStructureCount(){
        assertThat(myStructure.count(), is(6));
    }
}