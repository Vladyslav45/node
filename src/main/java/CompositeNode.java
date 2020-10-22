import service.ICompositeNode;
import service.INode;

import java.util.List;

public class CompositeNode extends Node implements ICompositeNode {

    private List<INode> compositeNodes;

    public CompositeNode(String code, String renderer) {
        super(code, renderer);
    }

    @Override
    public List<INode> getNodes() {
        return compositeNodes;
    }

    public void setCompositeNodes(List<INode> compositeNodes) {
        this.compositeNodes = compositeNodes;
    }
}
