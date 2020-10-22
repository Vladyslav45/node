import service.ICompositeNode;
import service.IMyStructure;
import service.INode;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MyStructure implements IMyStructure {

    private List<INode> nodes;

    @Override
    public INode findByCode(String code) {
        if (code == null){
            throw new IllegalArgumentException("Code not found");
        }
        return findCodeOrRendererFromListByPredicate(c -> c.getCode().equals(code));
    }

    @Override
    public INode findByRenderer(String renderer) {
        if (renderer == null){
            throw new IllegalArgumentException("Renderer not found");
        }
        return findCodeOrRendererFromListByPredicate(r -> r.getRenderer().equals(renderer));
    }

    @Override
    public int count() {
        return (int) transformationListToStream(nodes).count();
    }

    public void setNodes(List<INode> nodes) {
        this.nodes = nodes;
    }

    private INode findCodeOrRendererFromListByPredicate(Predicate<INode> predicate){
        return transformationListToStream(nodes).filter(predicate).findFirst().orElse(null);
    }

    private Stream<INode> transformationListToStream(List<INode> nodes){
        return nodes.stream().flatMap(node -> node instanceof ICompositeNode ? Stream.concat(transformationListToStream(((ICompositeNode) node).getNodes()), Stream.of(node)) : Stream.of(node));
    }

}
